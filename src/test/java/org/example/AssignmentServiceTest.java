package org.example;

import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentServiceTest {
    private TemaXMLRepository xmlRepo;
    private Service service;

    @BeforeEach
    public void setUp() {
        xmlRepo = new TemaXMLRepository(new TemaValidator(), "temeTest.xml");
        service = new Service(new StudentXMLRepository(new StudentValidator(), "studenti.xml") , xmlRepo, new NotaXMLRepository(new NotaValidator(), "note.xml"));
    }

    @Test
    public void testSaveTema_Success() {
        String id = "1";
        String description = "in progress";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(0, result);
    }

    @Test
    public void testSaveTema_nullId() {
        String description = "in progress";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(null, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_emptyId() {
        String id = "";
        String description = "in progress";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_nullDescription() {
        String id = "1";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, null, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_emptyDescription() {
        String id = "1";
        String description = "";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_deadlineOverInterval() {
        String id = "1";
        String description = "in progress";
        int deadline = 15;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_deadlineUnderInterval() {
        String id = "1";
        String description = "in progress";
        int deadline = 0;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_startOverInterval() {
        String id = "1";
        String description = "in progress";
        int deadline = 9;
        int start = 15;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_startUnderInterval() {
        String id = "1";
        String description = "in progress";
        int deadline = 9;
        int start = 0;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_startGreaterThanDeadline() {
        String id = "1";
        String description = "in progress";
        int deadline = 7;
        int start = 9;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(1, result);
    }

}
