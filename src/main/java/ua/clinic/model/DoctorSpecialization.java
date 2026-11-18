package ua.clinic.model;


public enum DoctorSpecialization {
    CARDIOLOGY(2.0),
    NEUROLOGY(1.8),
    PEDIATRICS(1.2),
    DENTISTRY(1.5);

    private final double priceFactor;

    DoctorSpecialization(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public double getPriceFactor() {
        return priceFactor;
    }
}