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

public class IncrementalIntegrationTest {
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

    public void addStudent() {
        String id = "1";
        String nume = "John Doe";
        int grupa = 123;

        int result = service.saveStudent(id, nume, grupa);

        assertEquals(0, result);
    }

    public void addAssignment() {
        String id = "1";
        String description = "in progress";
        int deadline = 9;
        int start = 7;

        int result = service.saveTema(id, description, deadline, start);

        assertEquals(0, result);
    }

    public void addGrade() {
        String idStudent = "1";
        String idNota = "1";
        double nota = 8.0;
        int sapt = 9;
        String feedback = "good";

        int result = service.saveNota(idStudent, idNota, nota, sapt, feedback);

        assertEquals(0, result);
    }

    @Test
    public void addStudent_integration() {
        addStudent();
    }

    @Test
    public void addAssignment_integration() {
        addStudent();
        addAssignment();
    }

    @Test
    public void addGrade_integration() {
        addStudent();
        addAssignment();
        addGrade();
    }
}
