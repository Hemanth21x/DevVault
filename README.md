# DevVault — Developer Error Intelligence Platform

DevVault is a full-stack Java web application designed to help developers store, search, and share solutions to programming errors they encounter during development.

The idea behind DevVault came from a common problem developers face: spending a lot of time searching Stack Overflow, Google, and old forum discussions for errors they may have already solved before.

DevVault provides a centralized developer knowledge base where users can post errors, share solutions, search existing problems, vote on solutions, mark accepted answers, bookmark useful errors, maintain private debugging notes, and receive notifications.

The project was built **from scratch without Spring Boot** to understand how a traditional Spring MVC application works internally, including the request lifecycle, DispatcherServlet, dependency injection, interceptors, services, DAOs, database access, and JSP view resolution.

---

## Features

### Authentication

- User registration
- User login/logout
- BCrypt password hashing
- Session-based authentication
- Role-based authorization
- USER / MODERATOR / ADMIN roles
- Custom authentication interceptor

### Error Management

- Post programming errors
- Add error title and error message
- Add description and possible cause
- Select technology
- Select category
- Browse errors
- Search errors
- Filter errors by technology and category
- View detailed error information

### Solutions

- Submit solutions to existing errors
- Add explanations
- Add code examples
- Upvote solutions
- Downvote solutions
- Mark a solution as accepted

### Developer Features

- Debug Journal
- Bookmarks
- Notifications
- AJAX voting
- AJAX bookmarking
- Admin dashboard
- Centralized exception handling

---

# Technology Stack

| Layer | Technology |
|---|---|
| Programming Language | Java 17 |
| Web Framework | Spring MVC 6 |
| Spring Boot | Not Used |
| Data Access | Spring JDBC |
| JDBC API | JdbcTemplate |
| ORM | Not Used |
| View Technology | JSP |
| JSP Library | JSTL |
| Database | MySQL 8 |
| Web Server | Apache Tomcat 10 |
| Frontend | HTML5, CSS3, JavaScript |
| AJAX | JavaScript Fetch API |
| Security | BCrypt |
| Authentication | HTTP Session |
| Build Tool | Maven |
| IDE | Eclipse |

---

# Why I Built It Without Spring Boot

Most modern Spring applications use Spring Boot because it automatically configures many parts of the application.

For DevVault, I intentionally avoided Spring Boot so I could understand what happens behind the scenes in a traditional Spring MVC application.

I manually worked with:

- `DispatcherServlet`
- Spring MVC configuration
- Component scanning
- Dependency Injection
- IoC
- ViewResolver
- DataSource
- JdbcTemplate
- Interceptors
- HTTP sessions
- JSP/JSTL
- Exception handling
- Tomcat configuration

This helped me understand the complete request lifecycle instead of treating Spring as a black box.

---

# Application Architecture

DevVault follows a layered architecture.

```text
┌───────────────────────┐
│       Browser         │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│        Tomcat         │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│   DispatcherServlet   │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│   AuthInterceptor     │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│      Controller       │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│       Service         │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│         DAO           │
└───────────┬───────────┘
            ↓
┌───────────────────────┐
│        MySQL          │
└───────────────────────┘