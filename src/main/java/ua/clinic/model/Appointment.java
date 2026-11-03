package ua.clinic.model;

import ua.common.BaseEntity;
import ua.clinic.util.ClinicUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Appointment extends BaseEntity {
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDate appointmentDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private String status;

    public Appointment(Patient patient, Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, String status) {
        super();
        ClinicUtils.validateAppointment(patient, doctor, appointmentDate, startTime, endTime);

        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;

        setStatus(status);
    }

    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getStatus() { return status; }

    public final void setStatus(String status) {
        status = ClinicUtils.formatEnum(status);
        ClinicUtils.validateAppointmentStatus(status);
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(appointmentDate, that.appointmentDate) &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor, appointmentDate, startTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient.getNationalId() +
                ", doctor=" + doctor.getLicenseNumber() +
                ", appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}