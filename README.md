# Secure Task Manager

A Spring Boot web application for personal task tracking with role-based access control for users and administrators.

## Features

- Session-based login and logout using Spring Security form login.
- Two roles: `USER` and `ADMIN`.
- Users create tasks with a title and description and see only their own tasks.
- Users mark their own tasks as completed. Completed tasks are shown with a completed style and status badge.
- Admins see every task across all users, including the owner and current status.
- Admins delete any task. The delete action asks for confirmation in the browser.
- Route protection: `/user/**` requires the `USER` role, `/admin/**` requires the `ADMIN` role, and protected routes redirect unauthenticated visitors to the login page.
- A custom access denied page is shown for authenticated requests to routes the current role cannot use.
- Method-level authorization with `@PreAuthorize` on service methods.
- Passwords are hashed with BCrypt.
- Server-rendered pages with Thymeleaf and a responsive CSS layout.

## Tech stack

- Java 17
- Spring Boot 3.2.3
- Spring MVC
- Spring Security
- Thymeleaf with `thymeleaf-extras-springsecurity6`
- Maven
- HTML and CSS
- In-memory storage (Java collections)

## Architecture

```
src/main/java/com/taskmanager/
  TaskManagerApplication.java     Application entry point and method security
  config/SecurityConfig.java      Users, password encoding, route rules, login and logout
  controller/
    HomeController.java           Landing page, dashboard redirect, access denied page
    UserController.java           User dashboard, task creation, task completion
    AdminController.java          Admin dashboard and task deletion
  model/Task.java                 Task data structure
  service/TaskService.java        In-memory task store and method-level authorization

src/main/resources/
  application.properties          Application name and port
  templates/                      Thymeleaf pages (index, user, admin, access denied)
  static/css/style.css            Page styling
```

## Getting started

Requirements: JDK 17 and Maven. The project does not include a Maven wrapper, so use the `mvn` command.

Build the application:

```bash
mvn clean package
```

Run it with the Spring Boot plugin:

```bash
mvn spring-boot:run
```

Or run the packaged jar:

```bash
java -jar target/task-manager-0.0.1-SNAPSHOT.jar
```

Open `http://localhost:8080` in a browser.

The app runs on port 8080 as set in `src/main/resources/application.properties`. It does not require a database, environment variables, or an `.env` file. The login accounts are defined in `src/main/java/com/taskmanager/config/SecurityConfig.java`. The usernames are `user1` and `user2` with the `USER` role, and `admin` with the `ADMIN` role. Their passwords are the demo values set in that file. Replace these accounts before using the app outside local development.

## Tests

Run the test suite with:

```bash
mvn test
```

There are currently no test classes in the repository, so Maven reports `No tests to run.` The command was run for this README and completed with `BUILD SUCCESS`, compiling all 7 main source files. There is no automated coverage of controllers, services, security rules, or templates yet.

## Limitations and roadmap

- Tasks live in an in-memory `ArrayList` and are lost when the application stops. There is no database or persistence layer.
- User accounts are hardcoded in memory. There is no registration, password reset, or user administration.
- Users cannot edit or delete their own tasks. Admins can delete tasks but cannot create or complete them.
- Tasks have only a title, description, owner, and completed flag. There are no due dates, priorities, categories, search, or pagination.
- The application is server-rendered only and does not expose a REST API.
- There are no automated tests.
- The demo accounts and passwords are intended for local development and must be replaced before any real deployment.

Natural next steps are database-backed storage, editable tasks, user registration, and automated tests covering the service and security rules.
