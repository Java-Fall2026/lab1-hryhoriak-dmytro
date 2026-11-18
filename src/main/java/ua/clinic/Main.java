package ua.clinic;

import ua.clinic.model.*;
import ua.clinic.util.ClinicUtils;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Створення record-об'єктів і доступ до компонентів ===");
        Patient p1 = new Patient(" ID-12345 ", " Ivan Ivanov ", LocalDate.of(1990, 5, 15));
        Doctor d1 = new Doctor("LIC-987", "Dr. House", DoctorSpecialization.CARDIOLOGY);
        System.out.println("Пацієнт: " + p1.fullName() + ", ID: '" + p1.nationalId() + "'");
        System.out.println("Лікар: " + d1.name() + ", спеціальність: " + d1.specialization());

        System.out.println("\n=== 2. Enum: values(), поле константи, valueOf ===");
        for (DoctorSpecialization s : DoctorSpecialization.values()) {
            System.out.println(s + " -> коефіцієнт ціни " + s.getPriceFactor());
        }
        System.out.println("valueOf(\"NEUROLOGY\") = " + DoctorSpecialization.valueOf("NEUROLOGY"));
        try {
            DoctorSpecialization.valueOf("WRONG_SPEC");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка valueOf: " + e.getMessage());
        }
        System.out.println("Ціна консультації (база 500): "
                + ClinicUtils.consultationPrice(d1.specialization(), 500));

        System.out.println("\n=== 3. Обидва switch-вирази в роботі ===");
        for (AppointmentStatus st : AppointmentStatus.values()) {
            System.out.println(st + ": " + ClinicUtils.statusDescription(st)
                    + " | " + ClinicUtils.availableAction(st));
        }

        System.out.println("\n=== 4. Record з невалідними даними (виняток із компактного конструктора) ===");
        try {
            new Patient("", "John Doe", LocalDate.of(1990, 1, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 1: " + e.getMessage());
        }
        try {
            new Doctor("LIC-111", "Name", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 2: " + e.getMessage());
        }
        try {
            new MedicalRecord("REC-X", null, "Flu", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 3: " + e.getMessage());
        }

        System.out.println("\n=== 5. Два однакових record-об'єкти (equals і hashCode) ===");
        Patient p2 = new Patient("ID-12345", "Ivan Ivanov", LocalDate.of(1990, 5, 15));
        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        System.out.println("p1.hashCode() = " + p1.hashCode() + ", p2.hashCode() = " + p2.hashCode());
        Patient p3 = new Patient("ID-99999", "Petro Petrenko", LocalDate.of(1985, 3, 20));
        System.out.println("p1.equals(p3) : " + p1.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode() + ", p3.hashCode() = " + p3.hashCode());

        System.out.println("\n=== 6. Appointment (клас) та сетер зі статусом ===");
        Appointment app1 = new Appointment(p1, d1, LocalDate.now(),
                LocalTime.of(10, 0), LocalTime.of(11, 0), AppointmentStatus.SCHEDULED);
        System.out.println("Статус до: " + app1.getStatus());
        app1.setStatus(AppointmentStatus.COMPLETED);
        System.out.println("Статус після: " + app1.getStatus());
        try {
            app1.setStatus(AppointmentStatus.valueOf("FINISHED"));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
        System.out.println("Статус після невдалої спроби: " + app1.getStatus());
        System.out.println("Тривалість прийому (хв): " + ClinicUtils.durationMinutes(app1));
        System.out.println("Вік пацієнта (років): " + ClinicUtils.patientAge(p1));

        System.out.println("\n=== 6b. Форматування ===");
        MedicalRecord record = new MedicalRecord("REC-001", app1, "healthy", 150.50);
        System.out.println("Вартість лікування: " + ClinicUtils.formatTreatmentCost(record));
        System.out.println("Діагноз (capitalize): " + ClinicUtils.capitalizeText(record.diagnosis()));

        System.out.println("\n=== 7. Вивід record через згенерований toString() ===");
        System.out.println(p1);
        System.out.println(d1);
        System.out.println(record);
        System.out.println(app1);
    }
}