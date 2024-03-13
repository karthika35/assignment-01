package com.exam.orm.controller;

import com.exam.orm.entity.Contact;
import com.exam.orm.model.DataResponse;
import com.exam.orm.model.Posts;
import com.exam.orm.model.Users;
import com.exam.orm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    private final RestTemplate restTemplate;

    public ContactController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/app/contact", consumes = "application/json")
    ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        if (contactService.getContactById(contact.getContactId()) == null) {
            return new ResponseEntity<Contact>(new Contact(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Contact>(contactService.saveContact(contact), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/app/item/{contactId}", consumes = "application/json")
    ResponseEntity<Contact> replaceContact(@PathVariable("contactId") int itemId, @RequestBody Contact newContact) {

        Contact contact = contactService.updateContact(itemId, newContact);
        if (contactService.getContactById(contact.getContactId()) == null) {
            return new ResponseEntity<Contact>(new Contact(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Contact>(contact, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/app/item/{contactId}", consumes = "application/json")
    ResponseEntity<Contact> deleteContact(@PathVariable("contactId") int itemId) {

        Contact contact = contactService.deleteContact(itemId);
        if (contact == null) {
            return new ResponseEntity<Contact>(new Contact(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Contact>(contact, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/app/contact", consumes = "application/json")
    ResponseEntity deleteAllContact() {
        contactService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/app/contact/{contactId}", consumes = "application/json")
    ResponseEntity<Contact> getContact(@PathVariable("contactId") int itemId) {
       Contact contact = contactService.getContactById(itemId);
        if (contact == null) {
            return new ResponseEntity<Contact>(new Contact(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Contact>(contact, HttpStatus.OK);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/app/contact", consumes = "application/json")
    ResponseEntity<List<Contact>> getAllContact() {
        List<Contact> contact = contactService.getAllContact();
        return new ResponseEntity<List<Contact>>(contact, HttpStatus.OK);
    }

    @GetMapping("app/concurrently")
    public CompletableFuture<DataResponse> fetchDataConcurrently() {
        Instant start = Instant.now();

        CompletableFuture<Users[]> usersFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", Users[].class));
        System.out.println(usersFuture);

        CompletableFuture<Posts[]> postsFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Posts[].class));

        return usersFuture.thenCombine(postsFuture, (users, posts) -> {
            Instant end = Instant.now();
            long elapsedTime = Duration.between(start, end).toMillis();
            return new DataResponse( elapsedTime+" Milli seconds", users,posts);
        });
    }


}
