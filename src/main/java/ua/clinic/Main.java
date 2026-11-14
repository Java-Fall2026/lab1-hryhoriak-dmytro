package ua.clinic;

import ua.clinic.model.*;
import ua.clinic.util.ClinicUtils;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Створення об'єктів конструктором і фабричним методом ===");
        Patient p1 = new Patient(" ID-12345 ", " Ivan Ivanov ", LocalDate.of(1990, 5, 15));
        Doctor d1 = Doctor.of("LIC-987", "Dr. House", " cardiology ");

        System.out.println(p1);
        System.out.println(d1);

        System.out.println("\n=== 2. Нормалізація в дії ===");
        System.out.println("Patient ID normal: '" + p1.getNationalId() + "'");
        System.out.println("Doctor Spec normal: '" + d1.getSpecialization() + "'");

        System.out.println("\n=== 3. Спрацювання винятків (невалідні дані) ===");
        try {
            new Patient("", "John Doe", LocalDate.of(1990, 1, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 1: " + e.getMessage());
        }

        try {
            Doctor.of("LIC-111", "Name", "WRONG_SPEC");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 2: " + e.getMessage());
        }

        try {
            new Appointment(p1, d1, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(9, 0), "SCHEDULED");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 3: " + e.getMessage());
        }

        System.out.println("\n=== 4. Коректний об'єкт та робота сетера ===");
        Appointment app1 = new Appointment(p1, d1, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), "scheduled");
        System.out.println("Статус до: " + app1.getStatus());
        app1.setStatus(" COMPLETED ");
        System.out.println("Статус після: " + app1.getStatus());

        System.out.println("\n=== 4b. Сетер з некоректним значенням ===");
        try {
            app1.setStatus("FINISHED");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
        System.out.println("Статус після невдалої спроби: " + app1.getStatus());

        System.out.println("\n=== 5. Два об'єкти з однаковими даними (== та equals) ===");
        Patient p2 = new Patient("ID-12345", "Ivan Ivanov", LocalDate.of(1990, 5, 15));
        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.equals(p2));

        System.out.println("\n=== 5b. hashCode() та різні об'єкти ===");
        System.out.println("p1.hashCode() = " + p1.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("Хеш-коди p1 і p2 рівні: " + (p1.hashCode() == p2.hashCode()));

        Patient p3 = new Patient("ID-99999", "Petro Petrenko", LocalDate.of(1985, 3, 20));
        System.out.println("p1.equals(p3) : " + p1.equals(p3));
        System.out.println("p1.hashCode() = " + p1.hashCode() + ", p3.hashCode() = " + p3.hashCode());

        System.out.println("\n=== 6. Виклик обчислюваних методів ===");
        System.out.println("Тривалість прийому (хв): " + ClinicUtils.durationMinutes(app1));
        System.out.println("Вік пацієнта (років): " + ClinicUtils.patientAge(p1));

        System.out.println("\n=== 6b. Форматування ===");
        MedicalRecord record = MedicalRecord.of("REC-001", app1, "healthy", 150.50);
        System.out.println("Вартість лікування: " + ClinicUtils.formatTreatmentCost(record));
        System.out.println("Діагноз (capitalize): " + ClinicUtils.capitalizeText(record.getDiagnosis()));
        System.out.println("Capitalize 'hEaLtHy': " + ClinicUtils.capitalizeText("hEaLtHy"));

        System.out.println("\n=== 7. Вивід усіх сутностей через toString() ===");
        System.out.println(p1);
        System.out.println(d1);
        System.out.println(app1);
        System.out.println(record);

        System.out.println("\n=== 8. Закоментований рядок (порушення доступу) ===");
        // ValidationHelper.requireNotNull(p1, "p1");
        // Comment: ValidationHelper is package-private (ua.clinic.util), so it is not visible from package ua.clinic.
    }
}