package com.exam.orm.service;

import com.exam.orm.repository.ContactRepository;
import com.exam.orm.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ContactService {
    @Autowired
    public ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        contactRepository.save(contact);
        return contact;
    }

    public Contact getContactById(int itemId) {
        return contactRepository.findById(itemId).orElse(null);
    }

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Contact updateContact(int itemId, Contact newContact) {
        return contactRepository.findById(itemId).map(contact -> {
            contact.setContactId(newContact.getContactId());
            contact.setName(newContact.getName());
            contact.setEmailAddress(newContact.getEmailAddress());
            contact.setPhone(newContact.getPhone());
            contact.setAddress(newContact.getAddress());
            return contactRepository.save(contact);
        }).orElseGet(null);
    }

    public Contact deleteContact(int itemId) {
        Contact contact = getContactById(itemId);
        if (contact != null) {
            contactRepository.delete(contact);
            return contact;
        } else return null;

    }

    public void deleteAll() {
        contactRepository.deleteAll();
    }
}
