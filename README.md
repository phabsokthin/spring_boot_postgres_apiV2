# Spring Boot + PostgreSQL Project

## 1. Check Java

```bash
java -version
```

Expected:

```text
openjdk version "21.x.x"
```

Check Maven:

```bash
./mvnw -version
```

---

## 2. Create Project with VS Code

Open VS Code:

```bash
code .
```

Press:

```text
⌘ + Shift + P
```

Select:

```text
Spring Initializr: Create a Maven Project
```

Choose:

```text
Maven
Java
Spring Boot 4.1.1
Java 21
Jar
```

Project information:

```text
Group: com.tutorial
Artifact: spring_boot_postgres_api
Name: spring_boot_postgres_api
Package: com.tutorial.spring_boot_postgres_api
```

Dependencies:

```text
Spring Web
Spring Data JPA
PostgreSQL Driver
Spring Boot DevTools
Validation
Spring Security
```

---

## 3. Open Project

```bash
cd spring_boot_postgres_api
```

Open in VS Code:

```bash
code .
```

---

## 4. Build Project

```bash
./mvnw clean install
```

---

## 5. Run Spring Boot

```bash
./mvnw spring-boot:run
```

Application:

```text
http://localhost:8080
```

---

## 6. Stop Application

Press:

```text
Ctrl + C
```

---

## 7. PostgreSQL

Check PostgreSQL:

```bash
psql --version
```

Connect:

```bash
psql -U postgres
```

Create database:

```sql
CREATE DATABASE db_testing;
```

List databases:

```sql
\l
```

Connect to database:

```sql
\c db_testing
```

List tables:

```sql
\dt
```

Exit PostgreSQL:

```sql
\q
```

---

## 8. PostgreSQL Configuration

File:

```text
src/main/resources/application.properties
```

```properties
spring.application.name=spring_boot_postgres_api

spring.datasource.url=jdbc:postgresql://localhost:5432/db_testing
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

---

## 9. Test API

Create:

```text
src/main/java/com/tutorial/spring_boot_postgres_api/controller/HelloController.java
```

```java
package com.tutorial.spring_boot_postgres_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }
}
```

Run:

```bash
./mvnw spring-boot:run
```

Test:

```text
GET http://localhost:8080/hello
```

Expected:

```text
Hello Spring Boot!
```

---

## 10. Useful Maven Commands

Clean:

```bash
./mvnw clean
```

Compile:

```bash
./mvnw compile
```

Run tests:

```bash
./mvnw test
```

Build:

```bash
./mvnw package
```

Clean and build:

```bash
./mvnw clean package
```

Skip tests:

```bash
./mvnw clean package -DskipTests
```

Run application:

```bash
./mvnw spring-boot:run
```

---

## 11. Check Java Home

```bash
echo $JAVA_HOME
```

Check installed Java versions:

```bash
/usr/libexec/java_home -V
```

Set Java 21:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

Check:

```bash
java -version
```

---

## 12. Project Structure

```text
spring_boot_postgres_api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/tutorial/spring_boot_postgres_api/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       ├── dto/
│   │   │       ├── config/
│   │   │       └── SpringBootPostgresApiApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

---

## 13. Development Workflow

Start application:

```bash
./mvnw spring-boot:run
```

Edit Java code.

Save:

```text
⌘ + S
```

Spring Boot DevTools automatically detects the change and restarts the application.

Look for:

```text
restartedMain
```

in the console.

---

## 14. Common Commands

Check running port:

```bash
lsof -i :8080
```

Kill process on port 8080:

```bash
kill -9 $(lsof -t -i:8080)
```

Check PostgreSQL:

```bash
brew services list
```

Start PostgreSQL:

```bash
brew services start postgresql
```

Stop PostgreSQL:

```bash
brew services stop postgresql
```

Restart PostgreSQL:

```bash
brew services restart postgresql
```

---

## 15. Recommended Development Order

```text
1. Spring Boot project
        ↓
2. PostgreSQL connection
        ↓
3. Entity
        ↓
4. Repository
        ↓
5. Service
        ↓
6. Controller
        ↓
7. DTO
        ↓
8. Validation
        ↓
9. Exception handling
        ↓
10. Spring Security
        ↓
11. JWT Authentication
        ↓
12. Role / Permission
        ↓
13. Production configuration
```
