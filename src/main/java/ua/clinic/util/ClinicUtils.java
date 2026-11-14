package ua.clinic.util;

import ua.clinic.model.Appointment;
import ua.clinic.model.AppointmentStatus;
import ua.clinic.model.Doctor;
import ua.clinic.model.DoctorSpecialization;
import ua.clinic.model.MedicalRecord;
import ua.clinic.model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ClinicUtils {
    private ClinicUtils() {}

    // ---------- computed methods ----------

    public static long durationMinutes(Appointment appointment) {
        ValidationHelper.requireNotNull(appointment, "appointment");
        return ChronoUnit.MINUTES.between(appointment.getStartTime(), appointment.getEndTime());
    }

    public static int patientAge(Patient patient) {
        ValidationHelper.requireNotNull(patient, "patient");
        return (int) ChronoUnit.YEARS.between(patient.birthDate(), LocalDate.now());
    }

    /** Returns the consultation price: base price multiplied by the specialization factor. */
    public static double consultationPrice(DoctorSpecialization specialization, double basePrice) {
        ValidationHelper.requireNotNull(specialization, "specialization");
        ValidationHelper.requirePositiveOrZero(basePrice, "basePrice");
        return basePrice * specialization.getPriceFactor();
    }

    // ---------- switch expressions ----------

    /** Exhaustive switch over all constants, without default. */
    public static String statusDescription(AppointmentStatus status) {
        ValidationHelper.requireNotNull(status, "status");
        return switch (status) {
            case SCHEDULED -> "Прийом запланований";
            case IN_PROGRESS -> "Прийом триває";
            case COMPLETED -> "Прийом завершено";
            case CANCELLED -> "Прийом скасовано";
        };
    }

    /** Behavior choice: which action is available for a given status. */
    public static String availableAction(AppointmentStatus status) {
        ValidationHelper.requireNotNull(status, "status");
        return switch (status) {
            case SCHEDULED -> "Можна розпочати або скасувати";
            case IN_PROGRESS -> "Можна завершити";
            case COMPLETED, CANCELLED -> "Лише перегляд";
        };
    }

    // ---------- validation ----------

    public static void validatePatient(String nationalId, String fullName, LocalDate birthDate) {
        ValidationHelper.requireNotBlank(nationalId, "nationalId");
        ValidationHelper.requireNotBlank(fullName, "fullName");
        ValidationHelper.requireNotFutureDate(birthDate, "birthDate");
    }

    public static void validateDoctor(String licenseNumber, String name, DoctorSpecialization specialization) {
        ValidationHelper.requireNotBlank(licenseNumber, "licenseNumber");
        ValidationHelper.requireNotBlank(name, "name");
        ValidationHelper.requireNotNull(specialization, "specialization");
    }

    public static void validateAppointment(Patient patient, Doctor doctor, LocalDate appointmentDate,
                                           LocalTime startTime, LocalTime endTime) {
        ValidationHelper.requireNotNull(patient, "patient");
        ValidationHelper.requireNotNull(doctor, "doctor");
        ValidationHelper.requireNotNull(appointmentDate, "appointmentDate");
        ValidationHelper.requireValidTimeRange(startTime, endTime);
    }

    public static void validateAppointmentStatus(AppointmentStatus status) {
        ValidationHelper.requireNotNull(status, "status");
    }

    public static void validateMedicalRecord(String recordId, Appointment appointment,
                                             String diagnosis, double treatmentCost) {
        ValidationHelper.requireNotBlank(recordId, "recordId");
        ValidationHelper.requireNotNull(appointment, "appointment");
        ValidationHelper.requireNotBlank(diagnosis, "diagnosis");
        ValidationHelper.requirePositiveOrZero(treatmentCost, "treatmentCost");
    }

    // ---------- formatting ----------

    public static String formatString(String input) {
        return FormatHelper.normalizeString(input);
    }

    public static String formatEnum(String input) {
        return FormatHelper.normalizeEnum(input);
    }

    /** Returns the treatment cost of a medical record formatted as money. */
    public static String formatTreatmentCost(MedicalRecord record) {
        ValidationHelper.requireNotNull(record, "record");
        return FormatHelper.formatMoney(record.treatmentCost());
    }

    /** Returns the text with the first letter capitalized. */
    public static String capitalizeText(String text) {
        return FormatHelper.capitalize(text);
    }
}