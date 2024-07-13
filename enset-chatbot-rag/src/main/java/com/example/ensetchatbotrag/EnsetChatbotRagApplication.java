package com.example.ensetchatbotrag;

import com.example.ensetchatbotrag.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class EnsetChatbotRagApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetChatbotRagApplication.class, args);
    }

    CommandLineRunner commandLineRunner(PersonRepository repository){
        return args -> {
                for (int i=0;i<100;i++){
                   Person person= Person.builder()
                            .nom(UUID.randomUUID().toString().substring(0,8))
                            .email(UUID.randomUUID().toString().substring(0,8)+"@gmail.com")
                            .build();
                   repository.save(person);
                }
        };
    }
}
