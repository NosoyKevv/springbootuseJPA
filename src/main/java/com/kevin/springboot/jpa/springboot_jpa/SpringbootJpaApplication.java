package com.kevin.springboot.jpa.springboot_jpa;

import com.kevin.springboot.jpa.springboot_jpa.Entity.Person;
import com.kevin.springboot.jpa.springboot_jpa.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // List<Person> persons = (List<Person>) personRepository.findAll();

        //BUSCAR POR LENGUAGE DE PROGRAMACION
//        List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("PHP");
//        persons.stream().forEach(person -> System.out.println(person));

//      //BUSCAR POR NOMBRE
//        List<Person> personsName = (List<Person>) personRepository.findByName("LAURA");
//        personsName.stream().forEach(person -> System.out.println(person));
        //usando @QUERY
        List<Person> buscarName = (List<Person>) personRepository.buscarByName("ESTEBAN", "PHP", "ORTIZ");
        buscarName.stream().forEach(person -> System.out.println(person));

        //Otra consulta query
        List<Object[]> obtenerPersonData = personRepository.obtenerPersonData();
        obtenerPersonData.stream().forEach(person -> System.out.println(person[0] + " eres experto en " + person[1]));

    }
}
