package org.example;

import org.example.repository.AbstractXMLRepository;
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

public class StudentServiceTest {

    private StudentXMLRepository xmlRepo;
    private Service service;

    @BeforeEach
    public void setUp() {
        xmlRepo = new StudentXMLRepository(new StudentValidator(), "studentiTest.xml");
        service = new Service(xmlRepo, new TemaXMLRepository(new TemaValidator(), "teme.xml"), new NotaXMLRepository(new NotaValidator(), "note.xml"));
    }

    @Test
    public void testSaveStudent_Success() {
        // Arrange
        String id = "1";
        String nume = "John Doe";
        int grupa = 123;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void testSaveStudent_Failure() {
        // Arrange
        String id = "1";
        String nume = "John Doe";
        int grupa = 123;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(1, result);
    }
}
