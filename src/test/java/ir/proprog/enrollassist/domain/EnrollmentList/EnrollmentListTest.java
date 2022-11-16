package ir.proprog.enrollassist.domain.EnrollmentList;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.enrollmentList.EnrollmentList;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.section.Section;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentListTest {

    @DisplayName("EnrollmentLists are correct")
    @ParameterizedTest
    @MethodSource("EnrollmentListPassed")
    public void isPassedShouldReturnTrue(EnrollmentList enrollmentList) {
        List<EnrollmentRuleViolation> emptyList = new ArrayList<>();
        assertEquals(emptyList, enrollmentList.checkEnrollmentRules());
        //assertTrue(enrollmentList.checkEnrollmentRules());
    }

    /*@DisplayName("EnrollmentLists are not correct")
    @ParameterizedTest
    @MethodSource("EnrollmentListNotPassed")
    public void checkHasPassedAllPrerequisitesShouldReturnFalse(EnrollmentList enrollmentList) {
        //asserFalse(enrollmentList.checkEnrollmentRules());
    }*/

    private static List<EnrollmentList> EnrollmentListPassed() throws Exception {
        try {

            Course software_testing = new Course("0000001","Software Testing",3,"Undergraduate");
            Course software_engineering = new Course("0000002","Software Engineering",3,"Undergraduate");
            Course neural_networks = new Course("0000003","Neural Networks",3,"Masters");
            Course machine_learning = new Course("0000004","Machine Learning",3,"Masters");
            Course advanced_math = new Course("0000005","Advanced Math",4,"PHD");
            Course advanced_science = new Course("0000006","Advanced Science",4,"PHD");

            ExamTime time1 = new ExamTime("2022-06-21T08:00", "2022-06-21T11:00");
            ExamTime time2 = new ExamTime("2022-06-21T14:00", "2022-06-21T17:00");
            ExamTime time3 = new ExamTime("2022-06-23T08:00", "2022-06-23T11:00");
            ExamTime time4 = new ExamTime("2022-06-23T14:00", "2022-06-23T17:00");
            ExamTime time5 = new ExamTime("2022-06-25T08:00", "2022-06-25T11:00");
            ExamTime time6 = new ExamTime("2022-06-25T14:00", "2022-06-25T17:00");

            PresentationSchedule ps1_1 = new PresentationSchedule(
                    "Saturday",
                    "10:30",
                    "12:00");

            PresentationSchedule ps1_2 = new PresentationSchedule(
                    "Monday",
                    "10:30",
                    "12:00");


            PresentationSchedule ps2_1 = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            PresentationSchedule ps2_2 = new PresentationSchedule(
                    "Monday",
                    "09:00",
                    "10:30");


            PresentationSchedule ps3_1 = new PresentationSchedule(
                    "Sunday",
                    "10:30",
                    "12:00");

            PresentationSchedule ps3_2 = new PresentationSchedule(
                    "Tuesday",
                    "10:30",
                    "12:00");


            PresentationSchedule ps4_1 = new PresentationSchedule(
                    "Sunday",
                    "09:00",
                    "10:30");

            PresentationSchedule ps4_2 = new PresentationSchedule(
                    "Tuesday",
                    "09:00",
                    "10:30");


            PresentationSchedule ps5_1 = new PresentationSchedule(
                    "Wednesday",
                    "09:00",
                    "10:30");

            PresentationSchedule ps5_2 = new PresentationSchedule(
                    "Thursday",
                    "09:00",
                    "10:30");



            PresentationSchedule ps6_1 = new PresentationSchedule(
                    "Wednesday",
                    "10:30",
                    "12:00");

            PresentationSchedule ps6_2 = new PresentationSchedule(
                    "Thursday",
                    "10:30",
                    "12:00");

            Set<PresentationSchedule> ps1 = new HashSet<>();
            ps1.add(ps1_1);
            ps1.add(ps1_2);
            Set<PresentationSchedule> ps2 = new HashSet<>();
            ps1.add(ps2_1);
            ps1.add(ps2_2);
            Set<PresentationSchedule> ps3 = new HashSet<>();
            ps1.add(ps3_1);
            ps1.add(ps3_2);
            Set<PresentationSchedule> ps4 = new HashSet<>();
            ps1.add(ps4_1);
            ps1.add(ps4_2);
            Set<PresentationSchedule> ps5 = new HashSet<>();
            ps1.add(ps5_1);
            ps1.add(ps5_2);
            Set<PresentationSchedule> ps6 = new HashSet<>();
            ps1.add(ps6_1);
            ps1.add(ps6_2);

            Student student = new Student("810198461", "PHD");
            EnrollmentList e1 = new EnrollmentList("exampleList1", student);
            EnrollmentList e2 = new EnrollmentList("exampleList2", student);
            EnrollmentList e3 = new EnrollmentList("exampleList3", student);
            EnrollmentList e4 = new EnrollmentList("exampleList4", student);

            e1.addSections(
                    new Section(software_testing, "34020", time1, ps1),
                    new Section(software_engineering, "34021", time2, ps2)
            );
            e2.addSections(
                    new Section(neural_networks, "34022", time3, ps3),
                    new Section(machine_learning, "34023", time4, ps4)
            );
            e3.addSections(
                    new Section(advanced_math, "34024", time5, ps5),
                    new Section(advanced_science, "34025", time6, ps6)
            );
            e4.addSections(
                    new Section(neural_networks, "34022", time3, ps3),
                    new Section(advanced_math, "34024", time5, ps5)
            );


            return Arrays.asList(
                    e1,
                    e2,
                    e3,
                    e4
            );

        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }
}