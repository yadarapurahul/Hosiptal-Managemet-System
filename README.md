# 🏥 Hospital Management System

A RESTful backend application developed using **Java 21, Spring Boot, and MySQL** to manage hospital departments, doctors, patients, and appointments.

## 📌 Project Overview

The Hospital Management System provides REST APIs for managing core hospital operations. It uses Spring Data JPA for database persistence, DTOs for request and response handling, and MapStruct for object mapping.

**Project Status:** In Progress — Core CRUD operations and Postman testing completed for all four modules.

## ✨ Features

### 1. Department Management
- Create, retrieve, update, and delete departments.
- Support parent and subdepartment relationships.
- Manage department categories and statuses.

### 2. Doctor Management
- Manage doctor information and profiles.
- Associate doctors with departments.
- Store doctor specialization and professional details.

### 3. Patient Management
- Register and manage patient records.
- Retrieve, update, and delete patient information.
- Validate patient data.

### 4. Appointment Management
- Book appointments for patients with doctors.
- Retrieve appointments by code and list all appointments.
- Update and delete appointments.
- Manage appointment dates, times, specialties, and statuses.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| MySQL | Relational database |
| MapStruct | Entity and DTO mapping |
| Lombok | Reduce boilerplate code |
| Maven | Build and dependency management |
| Postman | API testing |

## 🏗️ Project Structure

```text
HospitalManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── HospitalManagement/
│   │   │       └── Application/
│   │   │           ├── DTO/
│   │   │           ├── Enum/
│   │   │           ├── Exceptions/
│   │   │           ├── entity/
│   │   │           ├── repositry/
│   │   │           ├── services/
│   │   │           └── controller/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── pom.xml
└── README.md
```

*Note: Adjust package and folder names above to match the actual project structure.*

## 🗄️ Database Design

The application contains four core entities:

- **Department:** Stores department details and supports a department hierarchy.
- **Doctor:** Stores doctor details and references a department.
- **Patient:** Stores patient information.
- **Appointment:** Connects a patient and a doctor for a scheduled appointment.

### Entity Relationships

- One department can have multiple doctors.
- One department can contain multiple subdepartments.
- One patient can have multiple appointments.
- One doctor can have multiple appointments.

## ⚙️ Prerequisites

Install the following before running the application:

- JDK 21
- MySQL Server
- Maven (or use the Maven Wrapper if included)
- IntelliJ IDEA or another Java IDE
- Postman

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/yadarapurahul/Hosiptal-Managemet-System.git
cd Hosiptal-Managemet-System
```

### 2. Create the MySQL database

```sql
CREATE DATABASE hospital_management;
```

Use the database name configured for your application.

### 3. Configure database credentials

Set the following environment variables before running the application:

```text
DB_URL=jdbc:mysql://localhost:3306/hospital_management
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
```

Ensure your `application.properties` references these variables. Never commit actual database passwords or other secrets.

### 4. Run the application

From the project root, run:

```bash
mvn spring-boot:run
```

Alternatively, open the project in your IDE and run the main Spring Boot application class.

### 5. Test the APIs

Open Postman and test the CRUD endpoints for the Department, Doctor, Patient, and Appointment modules.

The exact endpoints depend on the mappings defined in the controllers.

## 🧪 Testing

API testing has been performed using Postman for the four core modules.

Further automated testing with JUnit and Mockito is planned.

## 🔮 Future Enhancements

- Appointment status workflow and transition validation.
- Appointment rescheduling and cancellation workflow.
- Double-booking prevention.
- Automated testing using JUnit and Mockito.
- API documentation using Swagger/OpenAPI.
- Authentication and authorization using Spring Security and JWT.
- Docker-based deployment.

## 👨‍💻 Author

**Yadarapu Rahul**

Java | Spring Boot | REST APIs | MySQL


