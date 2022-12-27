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
    void checkValidGPALimitMinCreditsRequiredNotMetForUndergraduate() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",3,"Undergraduate");
            Section s3 = new Section(c3,"26");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);

            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMinCreditsRequiredNotMetForMasters() {

        try {
            Student student = new Student("1111111","Masters");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",4,"Masters");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",3,"Masters");
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
    void checkValidGPALimitMinCreditsRequiredNotMetForPHD() {

        try {
            Student student = new Student("1111111","PHD");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",3,"PHD");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",2,"PHD");
            Section s2 = new Section(c2,"25");

            student.setGrade("00001",c1,18);
            student.setGrade("00001",c2, 18);

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMinCreditsRequiredMetForUndergraduate() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);

            assertTrue(el.checkValidGPALimit().size() == 0);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMinCreditsRequiredMetForMasters() {

        try {
            Student student = new Student("1111111","Masters");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",4,"Masters");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Masters");
            Section s2 = new Section(c2,"25");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkValidGPALimit().size() == 0);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMinCreditsRequiredMetForPHD() {

        try {
            Student student = new Student("1111111","PHD");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",3,"PHD");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",3,"PHD");
            Section s2 = new Section(c2,"25");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);

            assertTrue(el.checkValidGPALimit().size() == 0);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsLimitExceeded20ForNewStudent() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Calculus 1",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c5 = new Course("2000005","Calculus 2",4,"Undergraduate");
            Section s5 = new Section(c5,"28");

            Course c6 = new Course("2000006","Calculus 3",4,"Undergraduate");
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
    void checkValidGPALimitMaxCreditsLimitExceeded20ForGradeLessThan17() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Architecture",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c5 = new Course("2000005","Digital Design",4,"Undergraduate");
            Section s5 = new Section(c5,"28");

            Course c6 = new Course("2000006","Software Engineering Lab",1,"Undergraduate");
            Section s6 = new Section(c6,"29");

            student.setGrade("00001",c1,17);
            student.setGrade("00001",c2, 16.5);
            student.setGrade("00001",c3, 16.5);
            student.setGrade("00001",c4, 16.5);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);
            el.addSection(s5);
            el.addSection(s6);

            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsLimitExceeded14() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);
            Course c1 = new Course("2000000","Software Testing",3,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks",4,"Undergraduate");
            Section s4 = new Section(c4,"27");


            student.setGrade("00001",c1,11);
            student.setGrade("00001",c2, 11);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);

            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsExceededForMasters() {

        try {
            Student student = new Student("1111111","Masters");
            EnrollmentList el = new EnrollmentList("List 1",student);

            Course c1 = new Course("2000000","Software Testing",4,"Masters");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Masters");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Masters");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks Lab",1,"Masters");
            Section s4 = new Section(c4,"27");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);


            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsExceededForPHD() {

        try {
            Student student = new Student("1111111","PHD");
            EnrollmentList el = new EnrollmentList("List 1",student);

            Course c1 = new Course("2000000","Software Testing",4,"PHD");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"PHD");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"PHD");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks Lab",1,"PHD");
            Section s4 = new Section(c4,"27");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 20);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);


            assertTrue(el.checkValidGPALimit().size() == 1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsExceededAndGPALessThan12ForUndergraduate() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);

            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c7 = new Course("2000004","AI",4,"Undergraduate");
            Section s7 = new Section(c7,"27");

            Course c5 = new Course("2000004","Operating System",4,"Undergraduate");
            Section s5 = new Section(c5,"27");

            Course c6 = new Course("2000004","Computer Networks Lab",1,"Undergraduate");
            Section s6 = new Section(c6,"27");

            student.setGrade("00001",c1,11);
            student.setGrade("00001",c2, 11);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);
            el.addSection(s5);
            el.addSection(s6);
            el.addSection(s7);

            assertTrue(el.checkValidGPALimit().size() == 2);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void checkValidGPALimitMaxCreditsExceededForUndergraduate() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);

            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c7 = new Course("2000004","AI",4,"Undergraduate");
            Section s7 = new Section(c7,"27");

            Course c5 = new Course("2000004","Operating System",4,"Undergraduate");
            Section s5 = new Section(c5,"27");

            Course c6 = new Course("2000004","Computer Networks Lab",1,"Undergraduate");
            Section s6 = new Section(c6,"27");

            student.setGrade("00001",c1,20);
            student.setGrade("00001",c2, 19);

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

    @Test
    void checkValidGPALimitMaxCreditsExceededAndGPABetween12And17ForUndergraduate() {

        try {
            Student student = new Student("1111111","Undergraduate");
            EnrollmentList el = new EnrollmentList("List 1",student);

            Course c1 = new Course("2000000","Software Testing",4,"Undergraduate");
            Section s1 = new Section(c1,"22");

            Course c2 = new Course("2000002","Software Engineering",4,"Undergraduate");
            Section s2 = new Section(c2,"25");

            Course c3 = new Course("2000003","Data Base",4,"Undergraduate");
            Section s3 = new Section(c3,"26");

            Course c4 = new Course("2000004","Computer Networks",4,"Undergraduate");
            Section s4 = new Section(c4,"27");

            Course c7 = new Course("2000004","AI",4,"Undergraduate");
            Section s7 = new Section(c7,"27");

            Course c5 = new Course("2000004","Operating System",4,"Undergraduate");
            Section s5 = new Section(c5,"27");

            Course c6 = new Course("2000004","Computer Networks Lab",1,"Undergraduate");
            Section s6 = new Section(c6,"27");

            student.setGrade("00001",c1,13);
            student.setGrade("00001",c2, 14);

            el.addSection(s1);
            el.addSection(s2);
            el.addSection(s3);
            el.addSection(s4);
            el.addSection(s5);
            el.addSection(s6);
            el.addSection(s7);

            assertTrue(el.checkValidGPALimit().size() == 2);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}