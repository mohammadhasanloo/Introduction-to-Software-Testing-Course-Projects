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

    @DisplayName("Test1")
    @ParameterizedTest
    @MethodSource("studyRecordList")
    public void isPassed(StudyRecord studyRecord) {

    }

    private static List<StudyRecord> studyRecordList()
    {
        try {
            Course software_testing = new Course("01","Software Testing",3,"Undergraduate");
            return Arrays.asList(
                    new StudyRecord(
                    "0001",
                    software_testing,
                    10),
                    new StudyRecord(
                            "0002",
                            software_testing,
                            8),
                    new StudyRecord(
                            "0005",
                            software_testing,
                            15)
            );

        } catch (ExceptionList e) {
            throw new RuntimeException(e);
        }

    }

}