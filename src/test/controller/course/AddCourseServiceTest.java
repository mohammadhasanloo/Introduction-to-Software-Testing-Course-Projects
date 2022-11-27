package ir.proprog.enrollassist.domain.course;

import com.google.common.collect.Sets;
import com.sun.source.tree.AssertTree;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.course.CourseMajorView;
import ir.proprog.enrollassist.controller.student.StudentController;
import ir.proprog.enrollassist.domain.major.Major;
import ir.proprog.enrollassist.domain.program.Program;
import ir.proprog.enrollassist.repository.CourseRepository;
import ir.proprog.enrollassist.repository.ProgramRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.meta.When;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddCourseServiceTest {
        private AddCourseService addCourseService;
        @Mock
        private  CourseRepository courseRepository;
        @Mock
        private  ProgramRepository programRepository;

        @BeforeEach
        void setup() {
                MockitoAnnotations.initMocks(this);
                addCourseService = new AddCourseService(courseRepository, programRepository);
        }

        @AfterEach
        void teardown() {
                addCourseService = null;
        }

        @Test
        public void checkLoopTestForException() throws ExceptionList {
                Course a1 = new Course("0000001","a1",3,"Undergraduate");
                Course a2 = new Course("0000002","a2",3,"Masters");
                Course a3 = new Course("0000003","a3",3,"PHD");

                a2.setPrerequisites(new HashSet<>() {{
                        add(a3);
                }});
                a3.setPrerequisites(new HashSet<>() {{
                        add(a2);
                }});


                CourseMajorView c1 = new CourseMajorView(a1, new HashSet<>() {{
                        add(2L); }},
                        new HashSet<>() {{
                        add(1L); }}
                );
                ExceptionList exceptionList = new ExceptionList();
                exceptionList.addNewException(new Exception("a2 has made a loop in prerequisites."));
                try {
                        addCourseService.addCourse(c1);
                }
                catch (ExceptionList e) {
                        assertEquals(exceptionList, e);
                }


        }
}
