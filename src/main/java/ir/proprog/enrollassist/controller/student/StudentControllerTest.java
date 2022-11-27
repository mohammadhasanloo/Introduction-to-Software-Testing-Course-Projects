package ir.proprog.enrollassist.controller.student;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.enrollmentList.EnrollmentListView;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.course.CourseNumber;
import ir.proprog.enrollassist.domain.program.Program;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.student.StudentNumber;
import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;
import ir.proprog.enrollassist.domain.user.User;
import ir.proprog.enrollassist.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class StudentControllerTest {

    private StudentController studyController;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private SectionRepository sectionRepository;
    @Mock
    private EnrollmentListRepository enrollmentListRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        studyController = new StudentController(studentRepository,courseRepository,sectionRepository,enrollmentListRepository,userRepository);

    }

    @AfterEach
    void teardown() {
        studyController = null;
    }

    @Test
    void testAll()
    {
        ArrayList<Student> sample_students = new ArrayList<>();
        sample_students.add(new Student("1"));
        sample_students.add(new Student("2"));
        sample_students.add(new Student("3"));
        sample_students.add(new Student("4"));

        when(studentRepository.findAll()).thenReturn(sample_students);

        Iterable<StudentView> all = studyController.all();

        int count = 0;
        for (StudentView sv : studyController.all())
            count++;
        assertEquals(sample_students.size(),count);
    }

    @Test
    void testOne()
    {
        ArrayList<Student> sample_students = new ArrayList<>();
        sample_students.add(new Student("1"));
        sample_students.add(new Student("2"));
        sample_students.add(new Student("3"));
        sample_students.add(new Student("4"));

        when(studentRepository.findByStudentNumber(new StudentNumber("3"))).thenReturn(Optional.of(new Student("3")));

        StudentView one = studyController.one("3");

        assertEquals("3",one.getStudentNo().getNumber());
    }

    @Test
    void testOneNotFound()
    {
        ArrayList<Student> sample_students = new ArrayList<>();
        sample_students.add(new Student("1"));
        sample_students.add(new Student("2"));
        sample_students.add(new Student("3"));
        sample_students.add(new Student("4"));

//        when(studentRepository.findByStudentNumber(new StudentNumber("5"))).thenReturn(Optional.empty());

        try {
            StudentView one = studyController.one("5");

            //Test has failed
        } catch (ResponseStatusException e){
            assertEquals("404 NOT_FOUND \"Student not found\"", e.getMessage());
        }

    }

    @Test
    void testAddStudentShouldPass()
    {
        ArrayList<User> sample_user = new ArrayList<>();
        sample_user.add(new User("Joe","1"));
        sample_user.add(new User("Rogan","2"));
        sample_user.add(new User("Diego","3"));
        sample_user.add(new User("Jota","4"));

        ArrayList<Student> sample_students = new ArrayList<>();
        try {
            sample_students.add(new Student("11","Undergraduate"));
            sample_students.add(new Student("22","Undergraduate"));
            sample_students.add(new Student("33","Masters"));
            sample_students.add(new Student("44", "PHD"));
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }

        StudentView studentView = new StudentView(sample_students.get(0));
        studentView.setUserId("1");
        when(userRepository.findByUserId(studentView.getUserId())).thenReturn(Optional.ofNullable(sample_user.get(0)));

        when(studentRepository.findByStudentNumber(new StudentNumber("1"))).thenReturn(Optional.of(new Student("1")));

        studyController.addStudent(studentView);
    }

    @Test
    void testAddStudentUserNotFoundException()
    {
        ArrayList<User> sample_user = new ArrayList<>();
        sample_user.add(new User("Joe","1"));
        sample_user.add(new User("Rogan","2"));
        sample_user.add(new User("Diego","3"));
        sample_user.add(new User("Jota","4"));

        ArrayList<Student> sample_students = new ArrayList<>();
        try {
            sample_students.add(new Student("11","Undergraduate"));
            sample_students.add(new Student("22","Undergraduate"));
            sample_students.add(new Student("33","Masters"));
            sample_students.add(new Student("44", "PHD"));
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }

        StudentView studentView = new StudentView(sample_students.get(0));
        studentView.setUserId("1");

        try {
            studyController.addStudent(studentView);

            //Test has failed
        } catch (ResponseStatusException e){
            assertEquals("404 NOT_FOUND \"User with id: 1 was not found.\"", e.getMessage());
        }
    }

    @Test
    void testAddStudentStudentExistsException()
    {
        ArrayList<User> sample_user = new ArrayList<>();
        sample_user.add(new User("Joe","1"));
        sample_user.add(new User("Rogan","2"));
        sample_user.add(new User("Diego","3"));
        sample_user.add(new User("Jota","4"));

        ArrayList<Student> sample_students = new ArrayList<>();
        try {
            sample_students.add(new Student("11","Undergraduate"));
            sample_students.add(new Student("22","Undergraduate"));
            sample_students.add(new Student("33","Masters"));
            sample_students.add(new Student("44", "PHD"));
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }

        StudentView studentView = new StudentView(sample_students.get(0));
        studentView.setUserId("1");
        when(userRepository.findByUserId(studentView.getUserId())).thenReturn(Optional.ofNullable(sample_user.get(0)));

        when(studentRepository.findByStudentNumber(new StudentNumber("11"))).thenReturn(Optional.of(new Student("11")));

        try {
            studyController.addStudent(studentView);

            //Test has failed
        } catch (ResponseStatusException e){
            assertEquals("400 BAD_REQUEST \"This student already exists.\"", e.getMessage());
        }
    }



    @Test
    void testFindTakeableSectionsByMajorShouldPass()
    {
        ArrayList<Student> sample_students = new ArrayList<>();
        try {
            sample_students.add(new Student("11","Undergraduate"));
            sample_students.add(new Student("22","Undergraduate"));
            sample_students.add(new Student("33","Masters"));
            sample_students.add(new Student("44", "PHD"));
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }


        when(studentRepository.findByStudentNumber(new StudentNumber("11"))).thenReturn(Optional.of(new Student("11")));

        studyController.findTakeableSectionsByMajor("11");
    }
}