package ua.clinic.model;

import ua.common.BaseEntity;
import ua.clinic.util.ClinicUtils;
import java.util.Objects;

public class Doctor extends BaseEntity {
    private final String licenseNumber;
    private final String name;
    private final String specialization;

    private Doctor(String licenseNumber, String name, String specialization) {
        super();
        licenseNumber = ClinicUtils.formatString(licenseNumber);
        name = ClinicUtils.formatString(name);
        specialization = ClinicUtils.formatEnum(specialization);

        ClinicUtils.validateDoctor(licenseNumber, name, specialization);

        this.licenseNumber = licenseNumber;
        this.name = name;
        this.specialization = specialization;
    }

    public static Doctor of(String licenseNumber, String name, String specialization) {
        return new Doctor(licenseNumber, name, specialization);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(licenseNumber, doctor.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}