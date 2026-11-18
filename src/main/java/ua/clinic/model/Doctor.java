package ua.clinic.model;

import ua.clinic.util.ClinicUtils;

public record Doctor(String licenseNumber, String name, DoctorSpecialization specialization) {
    public Doctor {
        licenseNumber = ClinicUtils.formatString(licenseNumber);
        name = ClinicUtils.formatString(name);
        ClinicUtils.validateDoctor(licenseNumber, name, specialization);
    }
}