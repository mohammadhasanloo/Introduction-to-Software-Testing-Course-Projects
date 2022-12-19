
package ir.proprog.enrollassist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.course.CourseController;
import ir.proprog.enrollassist.controller.course.CourseMajorView;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.course.CourseNumber;
import ir.proprog.enrollassist.repository.CourseRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.annotation.Id;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private AddCourseService addCourseService;

    @Test
    void shouldReturnAllUsers() throws Exception {
        ArrayList<Course> all_courses = new ArrayList<>();
        all_courses.add(new Course("1234567","Software Testing",3,"Undergraduate"));
        when(courseRepository.findAll()).thenReturn(all_courses);
        mockMvc.perform(get("/courses/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].courseNumber.courseNumber", Matchers.is("1234567")))
                .andExpect(jsonPath("$[0].courseTitle", Matchers.is("Software Testing")))
                .andExpect(jsonPath("$[0].graduateLevel", Matchers.is("Undergraduate")))
                .andExpect(jsonPath("$[0].courseCredits", Matchers.is(3)))
                ;
    }



    @Test
    void shouldAddNewCourse() throws Exception {
        Course course = new Course("1234567","Software Testing",3,"Undergraduate");
        CourseMajorView courseMajorView = new CourseMajorView(course,null,null);

        when(courseRepository.findCourseByCourseNumber(new CourseNumber("1234567") )).thenReturn(null);

        mockMvc.perform(
                        post("/courses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(courseMajorView)))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnCourse() throws Exception {
        Course course = new Course("1234567","Software Testing",3,"Undergraduate");
        CourseMajorView courseMajorView = new CourseMajorView(course,null,null);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/courses/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseNumber.courseNumber", Matchers.is("1234567")))
                .andExpect(jsonPath("$.courseTitle", Matchers.is("Software Testing")))
                .andExpect(jsonPath("$.graduateLevel", Matchers.is("Undergraduate")))
                .andExpect(jsonPath("$.courseCredits", Matchers.is(3)));
    }

}