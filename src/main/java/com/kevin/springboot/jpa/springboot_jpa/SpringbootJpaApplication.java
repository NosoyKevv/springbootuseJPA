package com.kevin.springboot.jpa.springboot_jpa;

import com.kevin.springboot.jpa.springboot_jpa.Entity.Person;
import com.kevin.springboot.jpa.springboot_jpa.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        findOne();
    }

    //BUSCAR UN SOLO OBJETO
    public void findOne() {
        Person person = null;
        Optional<Person> personOpt = personRepository.findById(19L);
        //USO IF
//        if (personOpt.isPresent()) {
//            person = personOpt.get();
//            System.out.println("encontrado la persona " + person);
//        } else {
//            System.out.println("Person no encontrado " + person);
//        }
        //USO TERNARIO OPERATOR
        System.out.println(personOpt.isPresent() ?
                "encontrado la persona " + personOpt.get() :
                "Person no encontrado " + person);

        //PRGRAMACION FUNCIONAL GOSU
//        personRepository.findById(1L).ifPresent(person -> System.out.println(person));


    }


    public void list() {
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
