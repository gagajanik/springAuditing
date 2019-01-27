package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.objects.PersonDTO;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PersonController {
    private PersonRepository personRepository;
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("save")
    public Person save(@RequestBody PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setAge(personDTO.getAge());
      return  personRepository.save(person);
    }
    @PutMapping("update")
    public Person update(@RequestBody PersonDTO personDTO, HttpServletResponse response) {
        try {
            Person person =  personRepository.getOne(personDTO.getId());
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());
            person.setAge(personDTO.getAge());
            return personRepository.save(person);
        } catch (Exception ex) {
            System.out.println(response.getStatus());
        }
        return null;
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            personRepository.deleteById(id);
        }catch (Exception ex) {System.out.println(response.getStatus());}


    }
    @GetMapping("persons")
    public List<Person> getAll() {
      List<Person> personList = personRepository.findAll();
      return personList;
    }
}
