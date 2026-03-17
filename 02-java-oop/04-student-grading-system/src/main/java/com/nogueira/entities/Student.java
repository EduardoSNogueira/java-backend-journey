package com.nogueira.entities;

import com.nogueira.enums.StudentStatus;

public class Student {

    private String name;
    private double nota1;
    private double nota2;
    private double nota3;

    public Student(String name, double nota1, double nota2, double nota3) {
        this.name = name;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public double finalGrade() {
        return nota1 + nota2 + nota3;
    }

    public StudentStatus getStatus() {
        return StudentStatus.fromGrade(finalGrade());
    }

    public double missingPoints() {
        if (getStatus() == StudentStatus.RECOVERY) {
            return 60.0 - finalGrade();
        }
        return 0.0;
    }

    @Override
    public String toString() {
        String report = String.format("Nome: %s | Nota: %.2f | Status: %s", name, finalGrade(), getStatus());

        if (getStatus() == StudentStatus.RECOVERY) {
            report += String.format(" | MISSING %.2f POINTS TO PASS", missingPoints());
        }
        return report;
    }
}