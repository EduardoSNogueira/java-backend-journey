# 🎓 Student Grade Manager

A Java-based student management system developed to consolidate concepts of Object-Oriented Programming (OOP), Collections, and Data Validation.

---

## 🚀 Features

* **Dynamic Registration:** Register multiple students in a single execution using `ArrayList`.
* **Grade Validation:** Intelligent system that prevents negative grades or values above the allowed limits (**30.0** for the first grade and **35.0** for the others).
* **Error Handling:** Robust against invalid inputs (letters instead of numbers) using `try-catch` blocks.
* **Automatic Status (Enum):** Automatic student classification based on the final grade:
    * **EXCELLENCE:** 80 - 100 points.
    * **PASSED:** 60 - 79 points.
    * **RECOVERY:** 50 - 59 points (Calculates points needed to reach 60).
    * **FAIL:** Below 50 points.

## 🛠️ Technologies & Concepts

* **Java 17+**
* **Encapsulation:** Private attributes with controlled access via getters.
* **Enums:** Business logic and state management.
* **Collections (List/ArrayList):** Dynamic object storage.
* **Exception Handling:** Catching `InputMismatchException` to ensure system stability.
* **Clean Code:** Separated utility methods (`InputHelper`) to keep the code readable and reusable.

## 📸 Usage Example

Below is a demonstration of the application running, showing the dynamic student registration and the final report.

![Application Demo](demo.png)
---