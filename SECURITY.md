# Security

## Overview

Secure Student Records is a Java console application designed with basic security principles in mind. The project demonstrates secure password storage, authentication, role-based authorization, input validation, and secure handling of sensitive authentication data.

## Password Security

Passwords are never stored in plain text. The application uses PBKDF2 with HMAC-SHA256 to hash passwords before storage.

Each password uses:
- A randomly generated 16-byte salt
- 100,000 PBKDF2 iterations
- A 256-bit derived key

The salt and resulting password hash are stored instead of the original password.

## Authentication and Authorization

The application requires users to authenticate before accessing student records.

Two roles are supported:

- `USER` — can view and search student records.
- `ADMIN` — can view, search, and add student records.

Role-based authorization prevents a standard user from performing administrator-only actions. Failed login attempts use a generic error message to avoid revealing whether a username exists.

## Input Validation

The application validates user and student input before processing it.

Validation includes:
- Rejecting empty or invalid usernames
- Requiring passwords to meet the application's password rules
- Preventing duplicate usernames
- Preventing duplicate student IDs
- Validating student names and email addresses
- Rejecting commas in fields that could corrupt the comma-separated file format

## Sensitive Data Handling

Authentication data is stored locally in `data/users.txt`. This file is excluded from Git using `.gitignore` to prevent password hashes and salts from being committed to the repository.

Generated Maven build files in `target/` are also excluded from version control.

Student records are stored locally in `data/students.txt`. Student data is not encrypted at rest, so this project should be treated as an educational demonstration rather than a production-ready student records system.

## Known Security Limitations

This project is intended for educational purposes and is not production-ready.

Current limitations include:
- Student records are stored in plain text and are not encrypted at rest.
- Authentication data is stored in a local file rather than a secure database.
- The application does not currently implement account lockout or login rate limiting.
- File permissions are not explicitly managed by the application.
- The application does not provide multi-factor authentication.

## Security Testing

The project includes automated JUnit tests for security-related behaviour, including:

- Password hashing
- Verification that plain-text passwords are not stored
- Different password salts producing different hashes
- Successful and unsuccessful authentication
- Unknown-user login rejection
- Duplicate username detection
- Case-insensitive username detection
- Role-based authorization
- Invalid and null-user authorization checks
- Username and password validation
- Student name and email validation
- Duplicate student ID detection

The test suite can be executed with:

```bash
mvn test