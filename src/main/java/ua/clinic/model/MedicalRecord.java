package ua.clinic.model;

import ua.clinic.util.ClinicUtils;

public record MedicalRecord(String recordId, Appointment appointment, String diagnosis, double treatmentCost) {
    public MedicalRecord {
        recordId = ClinicUtils.formatString(recordId);
        diagnosis = ClinicUtils.formatString(diagnosis);
        ClinicUtils.validateMedicalRecord(recordId, appointment, diagnosis, treatmentCost);
    }
}