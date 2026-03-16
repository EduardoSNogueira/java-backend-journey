package com.nogueira.entities;

public class Employee {

    private String name;
    private double grossSalary;
    private double tax;

    public Employee(String name, double grossSalary, double tax) {
        this.name = name;
        this.grossSalary = grossSalary;
        this.tax = tax;
    }

    public double netSalary() {
        return grossSalary - tax;
    }

    @Override
    public String toString() {
        return String.format(" %s, $ %.2f", name, netSalary());

    }

    public Employee applyIncrease(double percentage) {
        if (percentage <= 0) {
            throw new IllegalArgumentException("A porcentagem deve ser positiva!");
        }
        double newSalary = this.grossSalary + (this.grossSalary * (percentage / 100.00));
        return new Employee(this.name, newSalary, this.tax);

    }
}