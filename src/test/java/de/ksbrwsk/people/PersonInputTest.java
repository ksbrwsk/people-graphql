package de.ksbrwsk.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonInputTest {

    @Test
    void create() {
        PersonInput input = new PersonInput(1L, "Name", "Vorname");
        assertEquals(1L, input.getId());
        assertEquals("Name", input.getName());
        assertEquals("Vorname", input.getVorname());
    }
}