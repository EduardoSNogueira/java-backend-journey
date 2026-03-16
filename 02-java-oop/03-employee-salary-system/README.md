# Employee Salary Management System

## Objective
To implement an `Employee` entity with a focus on **immutability** and **encapsulation**, ensuring robust data validation and error handling.

## Key Concepts Applied
- **Immutability**: Salary adjustments return a new `Employee` instance rather than modifying the original state, ensuring thread safety and data integrity.
- **Fail-Fast Principle**: Business rules are enforced within the entity (e.g., preventing negative percentage increases), throwing exceptions immediately when invalid data is encountered.
- **Robustness**: Implemented `try-catch` blocks at the input layer to handle `InputMismatchException` and custom domain exceptions without crashing the application.
- **Separation of Concerns**: Isolated input logic into a helper method, keeping the `Main` class clean and focused on orchestration.


## How to Run
1. Ensure you have the **JDK 17+** installed.
2. Navigate to the project directory in your terminal.
3. Compile the code: `javac com/nogueira/application/Main.java com/nogueira/entities/Employee.java`
4. Run the application: `java com.nogueira.application.Main`
