package ua.clinic.model;

import ua.common.BaseEntity;
import ua.clinic.util.ClinicUtils;
import java.util.Objects;

public class MedicalRecord extends BaseEntity {
    private final String recordId;
    private final Appointment appointment;
    private final String diagnosis;
    private final double treatmentCost;

    private MedicalRecord(String recordId, Appointment appointment, String diagnosis, double treatmentCost) {
        super();
        recordId = ClinicUtils.formatString(recordId);
        diagnosis = ClinicUtils.formatString(diagnosis);

        ClinicUtils.validateMedicalRecord(recordId, appointment, diagnosis, treatmentCost);

        this.recordId = recordId;
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.treatmentCost = treatmentCost;
    }

    public static MedicalRecord of(String recordId, Appointment appointment, String diagnosis, double treatmentCost) {
        return new MedicalRecord(recordId, appointment, diagnosis, treatmentCost);
    }

    public String getRecordId() { return recordId; }
    public Appointment getAppointment() { return appointment; }
    public String getDiagnosis() { return diagnosis; }
    public double getTreatmentCost() { return treatmentCost; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", appointmentDate=" + appointment.getAppointmentDate() +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatmentCost=" + treatmentCost +
                ", createdAt=" + createdAt +
                '}';
    }
}