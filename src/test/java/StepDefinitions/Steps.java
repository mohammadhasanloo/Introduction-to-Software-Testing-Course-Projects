package StepDefinitions;

import io.cucumber.java.en.*;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.student.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {

    private Course getPrerequisite(){
        Course design_logic = null;
        try {
            design_logic = new Course("1234567","Design Logic",3,"Undergraduate");
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }

        return design_logic;
    }
    private Student returnStudentPassedPrerequisite()  {
        try {
            Course design_logic = getPrerequisite();
            Student student = new Student("Mohsen","Undergraduate");
            student.setGrade("00001",design_logic,13);
            return student;
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

    private Student returnStudentHasNotPassedPrerequisite()  {
        try {
            Student student = new Student("Ali","Undergraduate");
            return student;
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }



    private Course returnCourse()  {
        try {
            Course design_logic = getPrerequisite();
            Course comp_arch = new Course("7654321","Computer Architecture",3,"Undergraduate");

            comp_arch.withPre(design_logic);

            return comp_arch;
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

    private Student s;
    private Course c;
    private List<EnrollmentRuleViolation> violations;

    // Course can be taken
    @Given("student reference that has passed the prerequisite course")
    public void student_reference_that_has_passed_the_prerequisite_course() {
       s = returnStudentPassedPrerequisite();
    }

    @Given("the course")
    public void the_course() {
        c = returnCourse();
    }

    @When("I ask can the student can take the course")
    public void i_ask_can_the_student_can_take_the_course() {
        violations = c.canBeTakenBy(s);
    }

    @Then("I should be told no violation {string}")
    public void i_should_be_told_no_violation(String string) {
        assertEquals(Integer.parseInt(string),violations.size());
    }

    // Course can be taken

    @Given("student reference that has not passed the prerequisite course")
    public void student_reference_that_has_not_passed_the_prerequisite_course() {
        s = returnStudentHasNotPassedPrerequisite();
    }
    @Then("I should be told one violation {string}")
    public void i_should_be_told_one_violation(String string) {
        assertEquals(Integer.parseInt(string),violations.size());
    }
}