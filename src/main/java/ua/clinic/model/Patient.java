package ua.clinic.model;

import ua.clinic.util.ClinicUtils;
import java.time.LocalDate;

public record Patient(String nationalId, String fullName, LocalDate birthDate) {
    public Patient {
        nationalId = ClinicUtils.formatString(nationalId);
        fullName = ClinicUtils.formatString(fullName);
        ClinicUtils.validatePatient(nationalId, fullName, birthDate);
    }
}