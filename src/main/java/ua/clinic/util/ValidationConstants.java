package ua.clinic.util;

import java.util.Set;

class ValidationConstants {
    static final Set<String> DOCTOR_SPECIALIZATIONS = Set.of(
            "CARDIOLOGY", "NEUROLOGY", "PEDIATRICS", "DENTISTRY"
    );

    static final Set<String> APPOINTMENT_STATUSES = Set.of(
            "SCHEDULED", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    );

    private ValidationConstants() {}
}