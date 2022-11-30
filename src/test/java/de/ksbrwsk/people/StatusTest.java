package de.ksbrwsk.people;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusTest {

    @Test
    void success() {
        Status success = Status.valueOf("SUCCESS");
        assertEquals(success, Status.SUCCESS);
    }

    @Test
    void failed() {
        Status failed = Status.valueOf("FAILED");
        assertEquals(failed, Status.FAILED);
    }

    @Test
    void unknown() {
        Status unknown = Status.valueOf("UNKNOWN");
        assertEquals(unknown, Status.UNKNOWN);
    }
}