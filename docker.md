# Docker Commands — Spring Boot + PostgreSQL + Redis + RabbitMQ

## 1. Check Docker

```bash
docker --version
```

Check Docker Compose:

```bash
docker compose version
```

Check running containers:

```bash
docker ps
```

---

## 2. Go to Project

```bash
cd "/Users/testing/Documents/Project Testing/spring_testing_api"
```

Check current directory:

```bash
pwd
```

List files:

```bash
ls
```

---

## 3. Build Docker Image

Build the Spring Boot image:

```bash
docker build -t spring-testing-api .
```

List images:

```bash
docker images
```

---

## 4. Docker Compose

### Start all services

```bash
docker compose up -d
```

### Build and start

Use this after changing Java code, `pom.xml`, Dockerfile, etc.:

```bash
docker compose up -d --build
```

### Start without rebuilding

```bash
docker compose start
```

### Stop services

```bash
docker compose stop
```

### Stop and remove containers

```bash
docker compose down
```

### Stop and remove containers + volumes

⚠️ This deletes Docker database/Redis/RabbitMQ data stored in Compose volumes.

```bash
docker compose down -v
```

---

## 5. Check Services

Show all Compose containers:

```bash
docker compose ps
```

Show all Docker containers:

```bash
docker ps -a
```

Expected services:

```text
spring-api
postgres
redis
rabbitmq
```

---

## 6. Spring Boot Logs

Show Spring Boot logs:

```bash
docker compose logs spring-api
```

Follow logs:

```bash
docker compose logs -f spring-api
```

Show last 50 lines:

```bash
docker compose logs --tail=50 spring-api
```

Follow last 100 lines:

```bash
docker compose logs -f --tail=100 spring-api
```

---

## 7. Restart Spring Boot

```bash
docker compose restart spring-api
```

Restart all services:

```bash
docker compose restart
```

---

## 8. Rebuild Spring Boot

After changing source code:

```bash
docker compose up -d --build spring-api
```

Check:

```bash
docker compose ps
```

---

# PostgreSQL

## 9. PostgreSQL Container

Enter PostgreSQL:

```bash
docker exec -it postgres psql -U postgres
```

Connect directly to `dbpos`:

```bash
docker exec -it postgres psql -U postgres -d dbpos
```

List databases:

```bash
docker exec -it postgres psql -U postgres -l
```

---

## 10. PostgreSQL SQL Commands

Inside PostgreSQL:

```sql
\l
```

List databases.

Connect to database:

```sql
\c dbpos
```

List tables:

```sql
\dt
```

Show table structure:

```sql
\d tbl_category
```

Show table structure:

```sql
\d tbl_product
```

Exit PostgreSQL:

```sql
\q
```

---

## 11. Create Database

From Mac terminal:

```bash
docker exec -it postgres psql -U postgres -c "CREATE DATABASE dbpos;"
```

---

## 12. Change PostgreSQL Password

Enter PostgreSQL:

```bash
docker exec -it postgres psql -U postgres -d postgres
```

Then:

```sql
ALTER USER postgres WITH PASSWORD 'admin123';
```

Expected:

```text
ALTER ROLE
```

Exit:

```sql
\q
```

---

## 13. PostgreSQL Connection Information

Docker PostgreSQL:

```text
Host: postgres
Port: 5432
Database: dbpos
Username: postgres
Password: admin123
```

From your Mac:

```text
Host: localhost
Port: 5433
Database: dbpos
Username: postgres
Password: admin123
```

### Important

Inside Docker:

```text
jdbc:postgresql://postgres:5432/dbpos
```

From your Mac:

```text
localhost:5433
```

Do not use `localhost:5433` inside the Spring container.

---

# Redis

## 14. Redis Container

Check Redis:

```bash
docker exec -it redis redis-cli ping
```

Expected:

```text
PONG
```

Enter Redis:

```bash
docker exec -it redis redis-cli
```

---

## 15. Redis Commands

Show all keys:

```redis
KEYS *
```

Get a value:

```redis
GET key_name
```

Check whether key exists:

```redis
EXISTS key_name
```

Delete key:

```redis
DEL key_name
```

Check TTL:

```redis
TTL key_name
```

Exit:

```redis
exit
```

---

# RabbitMQ

## 16. RabbitMQ Management UI

Open in browser:

```text
http://localhost:15672
```

Development credentials:

```text
Username: guest
Password: guest
```

RabbitMQ AMQP connection:

```text
Host: rabbitmq
Port: 5672
Username: guest
Password: guest
```

---

## 17. RabbitMQ Container

Check RabbitMQ:

```bash
docker ps | grep rabbitmq
```

View logs:

```bash
docker compose logs rabbitmq
```

Follow logs:

```bash
docker compose logs -f rabbitmq
```

---

# Docker Networking

## 18. Important Docker Rule

When containers communicate with each other, use the **service name**.

Spring Boot → PostgreSQL:

```text
postgres:5432
```

Spring Boot → Redis:

```text
redis:6379
```

Spring Boot → RabbitMQ:

```text
rabbitmq:5672
```

Do NOT use:

```text
localhost:5433
localhost:6379
localhost:5672
```

from inside the Spring container.

---

## 19. Access from Mac

Your Mac/Postman connects through published ports:

Spring Boot:

```text
http://localhost:8080
```

PostgreSQL:

```text
localhost:5433
```

Redis:

```text
localhost:6379
```

RabbitMQ:

```text
localhost:5672
```

RabbitMQ Management:

```text
http://localhost:15672
```

---

# Docker Container Commands

## 20. Enter Spring Container

```bash
docker exec -it spring-api sh
```

Exit:

```bash
exit
```

---

## 21. Enter PostgreSQL Container

```bash
docker exec -it postgres bash
```

Exit:

```bash
exit
```

---

## 22. Enter Redis Container

```bash
docker exec -it redis sh
```

Exit:

```bash
exit
```

---

## 23. Inspect Containers

Inspect Spring:

```bash
docker inspect spring-api
```

Inspect PostgreSQL:

```bash
docker inspect postgres
```

Inspect Redis:

```bash
docker inspect redis
```

Inspect RabbitMQ:

```bash
docker inspect rabbitmq
```

---

# Docker Volumes

## 24. List Volumes

```bash
docker volume ls
```

Find PostgreSQL volume:

```bash
docker volume ls | grep postgres
```

Find Redis volume:

```bash
docker volume ls | grep redis
```

Find RabbitMQ volume:

```bash
docker volume ls | grep rabbitmq
```

---

# Troubleshooting

## 25. Spring Container Restarting

Check status:

```bash
docker compose ps
```

Check logs:

```bash
docker compose logs --tail=100 spring-api
```

Follow logs:

```bash
docker compose logs -f spring-api
```

---

## 26. PostgreSQL Password Error

Error:

```text
FATAL: password authentication failed for user "postgres"
```

Check PostgreSQL:

```bash
docker exec -it postgres psql -U postgres -d postgres
```

Change password:

```sql
ALTER USER postgres WITH PASSWORD 'admin123';
```

Exit:

```sql
\q
```

Restart Spring:

```bash
docker compose restart spring-api
```

---

## 27. Database Does Not Exist

Check databases:

```bash
docker exec -it postgres psql -U postgres -l
```

Create database:

```bash
docker exec -it postgres psql -U postgres -c "CREATE DATABASE dbpos;"
```

---

## 28. Port Already in Use

Check port 8080:

```bash
lsof -i :8080
```

Check PostgreSQL port:

```bash
lsof -i :5433
```

Check Redis:

```bash
lsof -i :6379
```

Check RabbitMQ:

```bash
lsof -i :5672
```

Check RabbitMQ UI:

```bash
lsof -i :15672
```

---

# Clean Rebuild

## 29. Full Docker Rebuild

Use when you need to recreate containers but keep database volumes:

```bash
docker compose down
docker compose up -d --build
```

Check:

```bash
docker compose ps
```

---

## 30. Full Reset

⚠️ This removes Compose volumes and therefore deletes the Docker PostgreSQL, Redis, and RabbitMQ persistent data.

```bash
docker compose down -v
```

Then:

```bash
docker compose up -d --build
```

Check:

```bash
docker compose ps
```

---

# Useful Daily Workflow

## 31. Start Project

```bash
cd "/Users/testing/Documents/Project Testing/spring_testing_api"
```

```bash
docker compose up -d
```

Check:

```bash
docker compose ps
```

---

## 32. After Code Changes

```bash
docker compose up -d --build spring-api
```

Check logs:

```bash
docker compose logs -f spring-api
```

---

## 33. Stop Project

```bash
docker compose stop
```

---

## 34. Remove Containers

```bash
docker compose down
```

---

# Current Project Architecture

```text
                    Mac / Postman
                         |
                         | localhost:8080
                         ↓
                ┌─────────────────┐
                │   spring-api    │
                │  Spring Boot    │
                │     :8080       │
                └───────┬─────────┘
                        |
          ┌─────────────┼─────────────┐
          ↓             ↓             ↓
     PostgreSQL       Redis       RabbitMQ
     postgres:5432   redis:6379  rabbitmq:5672
          |             |             |
        dbpos         Cache        Queues
```

## Current Docker Services

```text
spring-api
    ↓
Spring Boot API
Port: 8080

postgres
    ↓
PostgreSQL 16
Container port: 5432
Mac port: 5433
Database: dbpos
User: postgres
Password: admin123

redis
    ↓
Redis 8
Port: 6379

rabbitmq
    ↓
RabbitMQ 4
AMQP: 5672
Management UI: 15672
User: guest
Password: guest
```
