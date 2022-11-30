package de.ksbrwsk.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void create() {
        Response response = new Response(Status.SUCCESS, "successful");
        assertEquals(Status.SUCCESS, response.getStatus());
        assertEquals("successful", response.getMessage());
    }
}