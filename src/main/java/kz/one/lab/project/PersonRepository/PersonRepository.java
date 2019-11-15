package kz.one.lab.project.PersonRepository;

import kz.one.lab.project.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findAll();
    List<Person> findAllByName(String name);
    List<Person> findAllByNameAndAge(@Param("name") String name,@Param("age") int age);
    List<Person>findAllByAgeBetween(int start,int end);
}
