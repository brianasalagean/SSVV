package org.example;

import org.example.domain.Nota;
import org.example.domain.Pair;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    private TemaXMLRepository temaXMLRepo;
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private Service service;

    @BeforeEach
    public void setUp() {
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), "studentiTest.xml");
        temaXMLRepo = new TemaXMLRepository(new TemaValidator(), "temeTest.xml");
        notaXMLRepository = new NotaXMLRepository(new NotaValidator(), "noteTest.xml");

        service = new Service(studentXMLRepository, temaXMLRepo, notaXMLRepository);
    }

    @Test
    public void testStudent_nullId() {
        String nume = "John Doe";
        int grupa = 123;

        int result = service.saveStudent(null, nume, grupa);

        assertEquals(1, result);
    }

    @Test
    public void testTema_Success() {
        String id = "1";
        String description = "in progress";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(0, result);
    }

    @Test
    public void testNota_invalidNota() {
        String idStudent = "1";
        String idNota = "1";
        double nota = 11.0;
        int sapt = 7;
        String feedback = "good";

        int result = service.saveNota(idStudent, idNota, nota, sapt, feedback);

        assertEquals(1, result);
    }

    @Test
    public void testAll() {
        service.saveStudent("1", "eu", 111);
        service.saveTema("1", "abc", 3, 1);
        service.saveNota("1", "1", 10.0, 7, "abc");
        assertEquals(1, service.saveNota("1", "1", 10.0, 7, "abc"));
    }
}
