package com.kevin.springboot.jpa.springboot_jpa;

import com.kevin.springboot.jpa.springboot_jpa.Dto.PersonDto;
import com.kevin.springboot.jpa.springboot_jpa.Entity.Person;
import com.kevin.springboot.jpa.springboot_jpa.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        findOne();
//        list();
//        create();
//        update();
//        delete2();
//        customMixQuery();
        custonQueryUseSQL();
    }

    @Transactional(readOnly = true)
    public void custonQueryUseSQL() {
        System.out.println("-------CONSULTA JPQL-------");
        List<String> names = personRepository.findAllNamesSQL();
        names.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void customMixQuery() {
        System.out.println("CONSULTA POR OBJETO PERSONA Y LENGUAJE PROGRAMACIO");
        List<Object[]> personRegs = personRepository.findAllMixPerson();

        personRegs.forEach(reg -> {
            System.out.println("programminLenguage  " + reg[1] + " person = " + reg[0]
            );
        });

        System.out.println("CONSULTA QUE PUEBLA Y DEVUELVA OBJETO ENTITY DE UNA INSTACIA PERSONALIZADA");
        List<Person> personList = personRepository.findAllConstructorNewCustomPerson();
//        personList.forEach(System.out::println);
        personList.forEach(person -> System.out.println(person));

        System.out.println("CONSULTA QUE PUEBLA Y DEVUELVA OBJETO DTO DE UNA CLASE PERSONALIZADA");
        List<PersonDto> personDtoList = personRepository.findAllPersonDto();
        personDtoList.forEach(personDto -> System.out.println(personDto));

    }

    @Transactional(readOnly = true)
    public void customQuery() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------CONSULTAR NOMBRE POR ID ------------");
        System.out.println("inserte el id de la persona que desea ver");

        Long idCustomQuery = scanner.nextLong();
        String name = personRepository.getNamePersonById(idCustomQuery);
        System.out.println(name);

        System.out.println("----------CONSULTAR ID-----------");
        System.out.println("el nombre de la persona para ver su id");

        String namePerson = scanner.next();
        Long id = personRepository.getIdByName(namePerson);
        System.out.println(id);

        System.out.println("----------CONSULTAR TODO POR ID CUSTOMQUERY-----------");
        System.out.println("Id dela persona a consultaar CUSTOMQUERY");
        Long idObjectById = scanner.nextLong();
        Optional<Object> optionalReg = personRepository.obtenerPersonById(idObjectById);
        if (optionalReg.isPresent()) {
            Object[] personReg = (Object[]) optionalReg.orElseThrow();
            System.out.println("ID " + personReg[0] + "  NAME  " + personReg[1] + "  LASTNAME  " + personReg[2] + "  PROGRAMMINGLANGUAGE  " + personReg[3]);
        }

        System.out.println("----------CONSULTAR TODO POR LIST CUSTOMQUERY-----------");
        List<Object[]> allList = personRepository.obtenerTodasPersonList();
        allList.forEach(allListObj -> System.out.println("ID " + allListObj[0] + "  NAME  " + allListObj[1] + "  LASTNAME  " + allListObj[2] + "  PROGRAMMINGLANGUAGE  " + allListObj[3]));
    }


    @Transactional
    public void delete2() {
        //Mostrar resultado PRE delete
        personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa id a eliminar: ");
        Long id = scanner.nextLong();

        Optional<Person> personDelete2 = personRepository.findById(id);
        personDelete2.ifPresentOrElse(
                person -> personRepository.delete(person),
                () -> System.out.println("No se encontro el el registro en la BD"));


        //Mostrar resultado Post delete
        personRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    public void delete() {
        //Mostrar resultado PRE delete
        personRepository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa id a eliminar: ");
        Long id = scanner.nextLong();
        personRepository.deleteById(id);

        //Mostrar resultado Post delete
        personRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    public void update() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("iNGRESE EL ID A EDITAR");
        long id = scanner.nextLong();

        Optional<Person> optionalPerson = personRepository.findById(id);
        optionalPerson.ifPresent(person -> {
            System.out.println(person);//Mostrar objeto completo
            System.out.println("Ingrese el lenguaje de programacio");
            String programmingLanguage = scanner.next();
            person.setProgrammingLanguage(programmingLanguage);
            Person personOb = personRepository.save(person);
            System.out.println(personOb);
        });
        scanner.close();
    }

    @Transactional
    public void create() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su Nombre: ");
        String name = scanner.next();
        System.out.println("Ingrese su Apellido: ");
        String lastName = scanner.next();
        System.out.println("Ingrese su lenguaje de programacion");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = new Person(null, name, lastName, programmingLanguage);
        Person personNew = personRepository.save(person);
        System.out.println(personNew);

        personRepository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
    }

    //BUSCAR UN SOLO OBJETO
    @Transactional(readOnly = true)
    public void findOne() {
        Person person = null;
        Optional<Person> personOpt = personRepository.findById(1L);
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

    @Transactional(readOnly = true)
    public void list() {
        // List<Person> persons = (List<Person>) personRepository.findAll();

        //BUSCAR POR LENGUAGE DE PROGRAMACION
//        List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("PHP");
//        persons.stream().forEach(person -> System.out.println(person));

//      //BUSCAR POR NOMBRE
//        List<Person> personsName = (List<Person>) personRepository.findByName("LAURA");
//        personsName.stream().forEach(person -> System.out.println(person));
        //usando @QUERY
        List<Person> buscarName = (List<Person>) personRepository.buscarByName("LUISA", "TYPESCRIPT", "GOMEZ");
        buscarName.stream().forEach(person -> System.out.println(person));

        //Otra consulta query
        List<Object[]> obtenerPersonData = personRepository.obtenerPersonData();
        obtenerPersonData.stream().forEach(person -> System.out.println(person[0] + " eres experto en " + person[1]));

        List<Object[]> diferentName = personRepository.findAllBy();
        diferentName.stream().forEach(person -> System.out.println(person[0] + " arreglo es diferentes a "));

        List<Person> traerDataDiferente = personRepository.diferenteName();
        traerDataDiferente.stream().forEach(person -> System.out.println(person + " Person traer diferente "));

    }

}
