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
        // TODO: change to 0; this is a bug
        assertEquals(0, result);
    }

    @Test
    public void testSaveStudent_emptyId() {
        // Arrange
        String id = "";
        String nume = "John Doe";
        int grupa = 123;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_nullId() {
        // Arrange
        String nume = "John Doe";
        int grupa = 123;

        // Act
        int result = service.saveStudent(null, nume, grupa);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_emptyName() {
        // Arrange
        String id = "2";
        String nume = "";
        int grupa = 123;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_nullName() {
        // Arrange
        String id = "2";
        int grupa = 123;

        // Act
        int result = service.saveStudent(id, null, grupa);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_wrongGroup_belowMin() {
        // Arrange
        String id = "2";
        String nume = "John Doe";
        int grupa = 110;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_wrongGroup_aboveMax() {
        // Arrange
        String id = "2";
        String nume = "John Doe";
        int grupa = 938;

        // Act
        int result = service.saveStudent(id, nume, grupa);

        // Assert
        assertEquals(1, result);
    }
}
