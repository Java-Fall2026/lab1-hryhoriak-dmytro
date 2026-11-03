package ua.clinic.util;

import ua.clinic.model.Appointment;
import ua.clinic.model.Doctor;
import ua.clinic.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ClinicUtils {
    private ClinicUtils() {}

    public static long durationMinutes(Appointment appointment) {
        ValidationHelper.requireNotNull(appointment, "appointment");
        return ChronoUnit.MINUTES.between(appointment.getStartTime(), appointment.getEndTime());
    }

    public static int patientAge(Patient patient) {
        ValidationHelper.requireNotNull(patient, "patient");
        return (int) ChronoUnit.YEARS.between(patient.getBirthDate(), LocalDate.now());
    }

    public static void validatePatient(String nationalId, String fullName, LocalDate birthDate) {
        ValidationHelper.requireNotBlank(nationalId, "nationalId");
        ValidationHelper.requireNotBlank(fullName, "fullName");
        ValidationHelper.requireNotFutureDate(birthDate, "birthDate");
    }

    public static void validateDoctor(String licenseNumber, String name, String specialization) {
        ValidationHelper.requireNotBlank(licenseNumber, "licenseNumber");
        ValidationHelper.requireNotBlank(name, "name");
        ValidationHelper.requireValidEnum(
                FormatHelper.normalizeEnum(specialization),
                ValidationConstants.DOCTOR_SPECIALIZATIONS,
                "specialization"
        );
    }

    public static void validateAppointment(Patient patient, Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime) {
        ValidationHelper.requireNotNull(patient, "patient");
        ValidationHelper.requireNotNull(doctor, "doctor");
        ValidationHelper.requireNotNull(appointmentDate, "appointmentDate");
        ValidationHelper.requireValidTimeRange(startTime, endTime);
    }

    public static void validateAppointmentStatus(String status) {
        ValidationHelper.requireValidEnum(
                FormatHelper.normalizeEnum(status),
                ValidationConstants.APPOINTMENT_STATUSES,
                "status"
        );
    }

    public static void validateMedicalRecord(String recordId, Appointment appointment, String diagnosis, double treatmentCost) {
        ValidationHelper.requireNotBlank(recordId, "recordId");
        ValidationHelper.requireNotNull(appointment, "appointment");
        ValidationHelper.requireNotBlank(diagnosis, "diagnosis");
        ValidationHelper.requirePositiveOrZero(treatmentCost, "treatmentCost");
    }

    public static String formatString(String input) {
        return FormatHelper.normalizeString(input);
    }

    public static String formatEnum(String input) {
        return FormatHelper.normalizeEnum(input);
    }
}