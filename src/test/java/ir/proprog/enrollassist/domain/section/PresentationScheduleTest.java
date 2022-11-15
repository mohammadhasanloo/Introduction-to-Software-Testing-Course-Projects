package ir.proprog.enrollassist.domain.section;

import ir.proprog.enrollassist.Exception.ExceptionList;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PresentationScheduleTest {

    @Test
    void startTimeBeforeEndTimeFun() {
        try {
            PresentationSchedule startTimeBeforeEndTime = new PresentationSchedule(
                    "Saturday",
                    "10:00",
                    "11:30");

            PresentationSchedule currentPs = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            assertTrue(currentPs.hasConflict(startTimeBeforeEndTime));
        } catch (ExceptionList ignored){
        }
    }

    @Test
    void startTimeAfterEndTimeImmediatelyFun() {
        try {

            PresentationSchedule startTimeAfterEndTimeImmediately = new PresentationSchedule(
                    "Saturday",
                    "10:30",
                    "12:00");

            PresentationSchedule currentPs = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            assertFalse(currentPs.hasConflict(startTimeAfterEndTimeImmediately));
        } catch (ExceptionList ignored){
        }
    }

    @Test
    void endTimeAfterStartTimeFun() {
        try {

            PresentationSchedule endTimeAfterStartTime = new PresentationSchedule(
                    "Saturday",
                    "08:00",
                    "09:30");

            PresentationSchedule currentPs = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            assertTrue(currentPs.hasConflict(endTimeAfterStartTime));
        } catch (ExceptionList ignored){
        }
    }

    @Test
    void endTimeAfterStartTimeAnotherDayFun() {
        try {

            PresentationSchedule endTimeAfterStartTimeAnotherDay = new PresentationSchedule(
                    "Sunday",
                    "08:00",
                    "09:30");

            PresentationSchedule currentPs = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            assertFalse(currentPs.hasConflict(endTimeAfterStartTimeAnotherDay));
        } catch (ExceptionList ignored){
        }
    }

    @Test
    void startTimeBeforeEndTimeAnotherDayFun() {
        try {

            PresentationSchedule startTimeBeforeEndTimeAnotherDay = new PresentationSchedule(
                    "Sunday",
                    "10:00",
                    "11:30");

            PresentationSchedule currentPs = new PresentationSchedule(
                    "Saturday",
                    "09:00",
                    "10:30");

            assertFalse(currentPs.hasConflict(startTimeBeforeEndTimeAnotherDay));
        } catch (ExceptionList ignored){
        }
    }
}