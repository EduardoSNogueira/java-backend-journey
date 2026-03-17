package com.nogueira.enums;

public enum StudentStatus {
    FAIL(0.0, 50.00),
    RECOVERY(50.0, 60.00),
    PASSED(60.0, 79.9),
    EXCELLENCE(80.0, 100.0);

    private final double minGrade;
    private final double maxGrade;

    StudentStatus(double minGrade, double maxGrade) {
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
    }

    public static StudentStatus fromGrade(double grade) {
        for (StudentStatus status : StudentStatus.values()) {
            if (grade >= status.minGrade && grade < status.maxGrade) {
                return status;
            }
        }
        throw new IllegalArgumentException("Grade out of range");
    }

}