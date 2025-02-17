package com.kevin.springboot.jpa.springboot_jpa.Repository;

import com.kevin.springboot.jpa.springboot_jpa.Entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {


    List<Person> findByProgrammingLanguage(String programmingLanguage);

    //List<Person> findByName(String name);

    //Cuando el metodo no cumple con el formato adecuado findby usamos un query metod para la consulta
    @Query("SELECT p FROM Person AS p WHERE p.name=?1 OR p.programmingLanguage=?2 OR p.lastName=?3")
    List<Person> buscarByName(String name, String programmingLanguage, String lastName);

    //uso query
    @Query("SELECT p.name, p.programmingLanguage ,p.lastName FROM Person AS p ")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p FROM Person AS p WHERE p.name !='LUISA'")
    List<Object[]> findAllBy();

    @Query("SELECT p FROM Person AS p WHERE p.name !='LUISA'")
    List<Person> diferenteName();

}
