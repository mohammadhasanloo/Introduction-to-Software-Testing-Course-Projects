package ir.proprog.enrollassist.domain.enrollmentList;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.Section;
import ir.proprog.enrollassist.domain.student.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentListTest {

    @Test
    void checkExamTimeConflicts_AllExamTimeIsNull_Test() {
        EnrollmentList el = new EnrollmentList();
        try {
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",3,"Undergraduate");
            Section s2 = new Section(c2,"25");

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkExamTimeConflicts().size() == 0);
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void checkExamTimeConflicts_OneExamTimeIsNull_Test() {
        try {
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");
            s1.setExamTime(new ExamTime());

            Course c2 = new Course("2000002","Software Engineering",3,"Undergraduate");
            Section s2 = new Section(c2,"25");

            EnrollmentList el = new EnrollmentList();

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkExamTimeConflicts().size() == 0);
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkExamTimeConflicts_ExamTimeConflict_Test() {
        try {
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");
            s1.setExamTime(new ExamTime());

            Course c2 = new Course("2000002","Software Engineering",3,"Undergraduate");
            Section s2 = new Section(c2,"25");
            s2.setExamTime(new ExamTime());

            EnrollmentList el = new EnrollmentList();

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkExamTimeConflicts().size() == 1);
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkExamTimeConflicts_ExamTimeNotConflict_Test() {
        try {
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");
            s1.setExamTime(new ExamTime());

            Course c2 = new Course("2000002","Software Engineering",3,"Undergraduate");
            Section s2 = new Section(c2,"25");
            s2.setExamTime(new ExamTime("2021-07-21T08:00","2021-07-21T11:00"));

            EnrollmentList el = new EnrollmentList();

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkExamTimeConflicts().size() == 0);
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkExamTimeConflicts_ExamTimeConflict_sameSection_Test() {
        try {
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");
            s1.setExamTime(new ExamTime());

            Course c2 = new Course("2000002","Software Engineering",3,"Undergraduate");
            Section s2 = new Section(c2,"25");
            s2.setExamTime(new ExamTime("2021-07-21T08:00","2021-07-21T11:00"));

            EnrollmentList el = new EnrollmentList();

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s1);

            assertTrue(el.checkExamTimeConflicts().size() == 0);
        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkValidGPALimitMinCreditsRequiredNotMet() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",1,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",1,"Undergraduate");
            Section s2 = new Section(c2,"25");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMinCreditsRequiredMet() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Software Engineering2",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Software Engineering3",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);

            assertTrue(el.checkValidGPALimit().size() == 0);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsLimitExceeded20() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Software Engineering2",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Software Engineering3",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c5 = new Course("2000005","Software Engineering3",4,"Undergraduate");
            Section s5 = new Section(c5,"28");

            Course c6 = new Course("2000006","Software Engineering3",4,"Undergraduate");
            Section s6 = new Section(c6,"29");


            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);
            el.addSections(s5);
            el.addSections(s6);

            assertTrue(el.checkValidGPALimit().size() == 1);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitNotUndergrad() {

        try {
            Student student = new Student("1111111","Masters");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",3,"Masters");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",3,"Masters");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Software Engineering2",3,"Masters");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Software Engineering3",3,"Masters");
            Section s4 = new Section(c4,"27");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);

            assertTrue(el.checkValidGPALimit().size() == 0);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsLimitExceeded24() {

        try {
            Student student = new Student("1111111","Masters");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",4,"Masters");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Masters");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Software Engineering2",4,"Masters");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Software Engineering3",4,"Masters");
            Section s4 = new Section(c4,"27");

            Course c5 = new Course("2000005","Software Engineering3",4,"Masters");
            Section s5 = new Section(c5,"28");

            Course c6 = new Course("2000006","Software Engineering3",4,"Masters");
            Section s6 = new Section(c6,"29");

            Course c7 = new Course("2000007","Software Engineering3",4,"Masters");
            Section s7 = new Section(c7,"30");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);
            el.addSection(s5);
            el.addSection(s6);
            el.addSection(s7);

            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    //
}