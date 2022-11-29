package de.ksbrwsk.people;

import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person, Long> {
    Person findTopByOrderByIdAsc();
}
