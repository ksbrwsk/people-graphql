package de.ksbrwsk.people;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @QueryMapping
    List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @QueryMapping
    Optional<Person> findOne(@Argument Long id) {
        return this.personRepository.findById(id);
    }

    @MutationMapping
    Person updatePerson(@Argument PersonInput input) {
        Optional<Person> old = this.personRepository.findById(input.getId());
        Person person = old.orElseThrow();
        person.setName(input.getName());
        person.setVorname(input.getVorname());
        return this.personRepository.save(person);
    }

    @MutationMapping
    Person createPerson(@Argument PersonInput input) {
        Person person = new Person(null, input.getName(), input.getVorname());
        return this.personRepository.save(person);
    }

    @MutationMapping
    Response deletePerson(@Argument Long id) {
        Optional<Person> personOptional = this.personRepository.findById(id);
        var person = personOptional.orElseThrow();
        this.personRepository.delete(person);
        return new Response(Status.SUCCESS, "successfully deleted!");
    }
}
