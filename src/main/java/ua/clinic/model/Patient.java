package ua.clinic.model;

import ua.common.BaseEntity;
import ua.clinic.util.ClinicUtils;
import java.time.LocalDate;
import java.util.Objects;

public class Patient extends BaseEntity {
    private final String nationalId;
    private final String fullName;
    private final LocalDate birthDate;

    public Patient(String nationalId, String fullName, LocalDate birthDate) {
        super();
        nationalId = ClinicUtils.formatString(nationalId);
        fullName = ClinicUtils.formatString(fullName);
        ClinicUtils.validatePatient(nationalId, fullName, birthDate);

        this.nationalId = nationalId;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getNationalId() { return nationalId; }
    public String getFullName() { return fullName; }
    public LocalDate getBirthDate() { return birthDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(nationalId, patient.nationalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "nationalId='" + nationalId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                '}';
    }
}