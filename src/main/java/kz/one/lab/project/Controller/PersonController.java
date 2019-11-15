package kz.one.lab.project.Controller;

import io.swagger.annotations.ApiOperation;
import kz.one.lab.project.Entity.Person;
import kz.one.lab.project.PersonRepository.PersonRepository;
import kz.one.lab.project.RandomGenerator.RandomGeneratorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("onelab")
@Slf4j
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RandomGeneratorImpl randomGenerator;

    @ApiOperation("save Persons")
    @RequestMapping(value = "/persons/",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public Person save(@RequestParam String name,@RequestParam int age){
        Person person = new Person(null,name,age);
        personRepository.save(person);
        log.info("добавлен пользователь " + person + " в бд");
        return person;
    }

    @ApiOperation("Get Persons by name")
    @GetMapping(value = "/getPersonByName/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByName(@RequestParam String name){
        List<Person> persons = personRepository.findAllByName(name);
        for(Person person : persons)
            log.info(person.getName() + " " + person.getAge());
        return persons;
    }

    @ApiOperation("Get Persons by age")
    @GetMapping(value = "/getPersonByAgeBetween/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByAge(@RequestParam int start ,@RequestParam int end){
        List<Person> persons = personRepository.findAllByAgeBetween(start,end);
        for(Person person : persons)
            log.info(person.getName() + " " + person.getAge());
        return persons;
    }

    @ApiOperation("Get Persons by Name and Age")
    @GetMapping(value = "/getPersonByNameAndAge/" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonByNameAndAge(@RequestParam int age,@RequestParam String name){
        List<Person> persons = personRepository.findAllByNameAndAge(name,age);
        for(Person person : persons)
            log.info(person.getName() + " " + person.getAge());
        return persons;
    }

    @ApiOperation("Generate persons")
    @PutMapping(value = "/generateUsers/" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateUsers(){
        List<Object> names = randomGenerator.generateValue(100,"NAME");
        List<Object> ages = randomGenerator.generateValue(100,"AGE");
        for(int i=0;i<100;i++){
            personRepository.save(new Person(null,(char)names.get(i) + "",(int)ages.get(i)));
        }
    }
}
