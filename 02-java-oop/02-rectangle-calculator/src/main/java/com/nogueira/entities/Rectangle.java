package com.nogueira.entities;

public class Rectangle {

    // Atributos privados para proteger os dados
    private double width;
    private double height;

    // Contrutores
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public double diagonal() {
        return Math.sqrt(width * width + height * height);
    }

    @Override
    public String toString() {
        return String.format("AREA = %.2f%n", area())
                + String.format("PERIMETER = %.2f%n", perimeter())
                + String.format("DIAGONAL = %.2f%n", diagonal());
    }

}
