package de.ksbrwsk.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class GraphQlTest extends AbstractIntegrationTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        this.personRepository.deleteAll();
        var people = new ArrayList<Person>();
        for (int i = 1; i <= 100; i++) {
            people.add(new Person(null, "Person@" + i, "Vorname@" + i));
        }
        this.personRepository.saveAll(people);
    }

    private Person fetchFirst() {
        return this.personRepository.findTopByOrderByIdAsc();
    }

    @Test
    void findAll() {
        List<Person> people = graphQlTester.documentName("findAll")
                .execute()
                .path("findAll")
                .entityList(Person.class)
                .get();
        assertEquals(100, people.size());
    }

    @Test
    void findOne() {
        var first = this.fetchFirst();
        graphQlTester.documentName("findOne")
                .variable("id", first.getId())
                .execute()
                .path("findOne")
                .entity(Person.class)
                .satisfies(data -> {
                    assertEquals("Person@1", data.getName());
                    assertEquals("Vorname@1", data.getVorname());
                });
    }

    @Test
    void createPerson() {
        graphQlTester.documentName("createPerson")
                .variable("input", new PersonInput(null, "Name", "Vorname"))
                .execute()
                .path("createPerson")
                .entity(Person.class)
                .satisfies(data -> {
                    assertNotNull(data.getId());
                    assertEquals("Name", data.getName());
                    assertEquals("Vorname", data.getVorname());
                });
    }

    @Test
    void updatePerson() {
        var person = this.fetchFirst();
        graphQlTester.documentName("updatePerson")
                .variable("input", new PersonInput(person.getId(), "Name", "Vorname"))
                .execute()
                .path("updatePerson")
                .entity(Person.class)
                .satisfies(data -> {
                    assertEquals(person.getId(), data.getId());
                    assertEquals("Name", data.getName());
                    assertEquals("Vorname", data.getVorname());
                });
    }
}
