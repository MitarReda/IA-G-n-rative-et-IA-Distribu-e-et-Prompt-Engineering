package com.example.ensetchatbotrag.services;

import com.example.ensetchatbotrag.PersonRepository;
import com.example.ensetchatbotrag.entity.Person;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.CrudRepositoryService;

@BrowserCallable
@AnonymousAllowed
public class PersonService extends CrudRepositoryService<Person,Long, PersonRepository> {
}
