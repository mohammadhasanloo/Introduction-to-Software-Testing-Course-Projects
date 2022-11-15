package ir.proprog.enrollassist.domain.studyRecord;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyRecordTest {

    @DisplayName("Students who have passed the course")
    @ParameterizedTest
    @MethodSource("studyRecordPassedList")
    public void isPassedShouldReturnTrue(StudyRecord studyRecord) {
        assertTrue(studyRecord.isPassed(studyRecord.getCourse().getGraduateLevel()));
    }

    @DisplayName("Students who have not passed the course")
    @ParameterizedTest
    @MethodSource("studyRecordNotPassList")
    public void isPassedShouldReturnFalse(StudyRecord studyRecord) {
        assertFalse(studyRecord.isPassed(studyRecord.getCourse().getGraduateLevel()));
    }

    private static List<StudyRecord> studyRecordPassedList()
    {
        try {
            Course software_testing = new Course("0000001","Software Testing",3,"Undergraduate");
            Course neural_networks = new Course("0000002","Neural Networks",3,"Masters");
            Course advanced_math = new Course("0000003","Advanced Math",4,"PHD");
            return Arrays.asList(
                    new StudyRecord(
                    "40001",
                    software_testing,
                    10),
                    new StudyRecord(
                            "40002",
                            software_testing,
                            10.1),
                    new StudyRecord(
                            "40003",
                            software_testing,
                            10.1),
                    new StudyRecord(
                            "40001",
                            software_testing,
                            15),
                    new StudyRecord(
                            "40002",
                            software_testing,
                            15),
                    new StudyRecord(
                            "40003",
                            neural_networks,
                            12),
                    new StudyRecord(
                            "40001",
                            neural_networks,
                            12.1),
                    new StudyRecord(
                            "40002",
                            neural_networks,
                            18),
                    new StudyRecord(
                            "40002",
                            advanced_math,
                            14),
                    new StudyRecord(
                            "40003",
                            advanced_math,
                            14.1),
                    new StudyRecord(
                            "40001",
                            advanced_math,
                            18)
            );

        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

    private static List<StudyRecord> studyRecordNotPassList()
    {
        try {
            Course software_testing = new Course("0000001","Software Testing",3,"Undergraduate");
            Course neural_networks = new Course("0000002","Neural Networks",3,"Masters");
            Course advanced_math = new Course("0000003","Advanced Math",4,"PHD");
            return Arrays.asList(
                    new StudyRecord(
                            "40001",
                            software_testing,
                            9.9),
                    new StudyRecord(
                            "40002",
                            software_testing,
                            1),
                    new StudyRecord(
                            "40003",
                            software_testing,
                            5),
                    new StudyRecord(
                            "40001",
                            software_testing,
                            9.9),
                    new StudyRecord(
                            "40002",
                            software_testing,
                            9),
                    new StudyRecord(
                            "40003",
                            neural_networks,
                            10),
                    new StudyRecord(
                            "40001",
                            neural_networks,
                            5),
                    new StudyRecord(
                            "40001",
                            neural_networks,
                            4),
                    new StudyRecord(
                            "40001",
                            advanced_math,
                            13.9),
                    new StudyRecord(
                            "40001",
                            advanced_math,
                            13.5),
                    new StudyRecord(
                            "40001",
                            advanced_math,
                            10)
            );

        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }
    }

}