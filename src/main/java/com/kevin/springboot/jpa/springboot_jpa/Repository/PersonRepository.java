package com.kevin.springboot.jpa.springboot_jpa.Repository;

import com.kevin.springboot.jpa.springboot_jpa.Entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {


}
