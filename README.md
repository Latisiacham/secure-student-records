# Secure Student Records

Secure Student Records is a Java console application that demonstrates secure software development principles through a simple student records management system.

The application includes user authentication, secure password hashing, role-based authorization, input validation, file-based data persistence, and automated security testing.

## Features

- Register and log in users
- Secure password hashing using PBKDF2 with HMAC-SHA256
- Random salt generation for passwords
- Role-based access control with `USER` and `ADMIN` roles
- Add student records with administrator authorization
- View student records
- Search for students
- Validate usernames, passwords, student names, and email addresses
- Prevent duplicate usernames and student IDs
- Store student records using file-based persistence
- Automated JUnit tests for application and security behaviour

## Technologies

- Java 21
- Maven
- JUnit 5
- PBKDF2 with HMAC-SHA256
- Git and GitHub

## Project Structure

```text
secure-student-records/
├── data/
│   ├── students.txt
│   └── users.txt
├── src/
│   ├── main/java/securestudent/
│   │   ├── Main.java
│   │   ├── Student.java
│   │   ├── StudentManager.java
│   │   ├── StudentFileManager.java
│   │   ├── User.java
│   │   ├── UserManager.java
│   │   ├── UserFileManager.java
│   │   └── PasswordHasher.java
│   └── test/java/securestudent/
│       └── StudentTest.java
├── .gitignore
├── pom.xml
├── README.md
└── SECURITY.md

## How to Run

### Requirements

Make sure Java 21 and Maven are installed.

Check your versions:

```bash
java -version
mvn -version
```

### Compile the Application

```bash
mvn compile
```

### Run the Application

```bash
java -cp target/classes securestudent.Main
```

### Run the Tests

```bash
mvn test
```

## User Roles

### USER

A standard user can:

- Log in to the application
- View student records
- Search for student records

### ADMIN

An administrator can:

- Log in to the application
- View student records
- Search for student records
- Add new student records

Administrative actions are protected by role-based authorization.

## Security

Security measures implemented in this project include:

- PBKDF2 password hashing with HMAC-SHA256
- Random 16-byte salts
- 100,000 password-hashing iterations
- Role-based authorization
- Generic login error messages
- Username and password validation
- Student input validation
- Protection against duplicate usernames and student IDs
- Authentication data excluded from Git

For more details about the security design and known limitations, see [SECURITY.md](SECURITY.md).

## Testing

The project includes automated JUnit tests covering core functionality and security-related behaviour.

The current test suite contains 23 tests covering areas such as:

- Student creation and management
- Student input validation
- Duplicate student ID detection
- Password hashing
- Password salt behaviour
- User registration and login
- Incorrect and unknown-user login attempts
- Username validation and duplicate detection
- Role-based authorization
- Invalid and null-user authorization
- Plain-text password protection

Run the complete test suite with:

```bash
mvn test
```

## Project Purpose

This project was created as a cybersecurity-focused Java portfolio project to practise secure software development principles.

It demonstrates how security can be considered throughout application development, including authentication, password protection, authorization, input validation, secure handling of sensitive data, and security testing.

The application is intended for educational and portfolio purposes and is not designed for production use.

# Verification Code
WTC-2VM4NLNX
