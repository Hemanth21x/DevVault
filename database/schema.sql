-- ============================================================
-- DevVault — Complete Database Schema
-- Run this whole file in MySQL Workbench to set up the database.
-- ============================================================

DROP DATABASE IF EXISTS devvault;
CREATE DATABASE devvault;
USE devvault;

-- ============ 1. ROLES ============
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- ============ 2. USERS ============
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- ============ 3. LOOKUP TABLES ============
CREATE TABLE technologies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE tags (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ============ 4. ERRORS ============
CREATE TABLE errors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    error_message TEXT NOT NULL,
    description TEXT,
    cause TEXT,
    technology_id INT,
    category_id INT,
    posted_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (technology_id) REFERENCES technologies(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (posted_by) REFERENCES users(id)
);

CREATE INDEX idx_errors_title ON errors(title);

-- ============ 5. SOLUTIONS ============
CREATE TABLE solutions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    error_id INT NOT NULL,
    posted_by INT NOT NULL,
    solution_text TEXT NOT NULL,
    code_example TEXT,
    is_accepted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (error_id) REFERENCES errors(id) ON DELETE CASCADE,
    FOREIGN KEY (posted_by) REFERENCES users(id)
);

-- ============ 6. ERROR_TAGS (many-to-many) ============
CREATE TABLE error_tags (
    error_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (error_id, tag_id),
    FOREIGN KEY (error_id) REFERENCES errors(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- ============ 7. VOTES ============
CREATE TABLE votes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    solution_id INT NOT NULL,
    user_id INT NOT NULL,
    vote_type ENUM('UP','DOWN') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_vote (solution_id, user_id),
    FOREIGN KEY (solution_id) REFERENCES solutions(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ============ 8. DEBUG JOURNALS ============
CREATE TABLE debug_journals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    project_name VARCHAR(150),
    problem TEXT NOT NULL,
    root_cause TEXT,
    solution TEXT,
    time_taken_minutes INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ============ 9. BOOKMARKS ============
CREATE TABLE bookmarks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    error_id INT,
    solution_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (error_id) REFERENCES errors(id) ON DELETE CASCADE,
    FOREIGN KEY (solution_id) REFERENCES solutions(id) ON DELETE CASCADE
);

-- ============ 10. NOTIFICATIONS ============
CREATE TABLE notifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    message VARCHAR(255) NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ============ 11. REPORTS ============
CREATE TABLE reports (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reported_by INT NOT NULL,
    error_id INT,
    solution_id INT,
    reason TEXT NOT NULL,
    status ENUM('PENDING','REVIEWED','DISMISSED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reported_by) REFERENCES users(id),
    FOREIGN KEY (error_id) REFERENCES errors(id) ON DELETE CASCADE,
    FOREIGN KEY (solution_id) REFERENCES solutions(id) ON DELETE CASCADE
);

-- ============ 12. USER ACTIVITY ============
CREATE TABLE user_activity (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    activity_type VARCHAR(50) NOT NULL,
    activity_detail VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


-- ============================================================
-- SEED DATA
-- ============================================================

INSERT INTO roles (id, name) VALUES (1, 'USER'), (2, 'MODERATOR'), (3, 'ADMIN');

INSERT INTO technologies (name) VALUES
    ('Java'), ('Spring'), ('SQL'), ('Maven'), ('Tomcat'),
    ('JDBC'), ('JavaScript'), ('MySQL'), ('JSP'), ('Hibernate');

INSERT INTO categories (name) VALUES
    ('Runtime'), ('Compile-time'), ('Configuration'), ('Database'),
    ('Dependency'), ('Deployment'), ('Security');

INSERT INTO tags (name) VALUES
    ('beginner'), ('classpath'), ('null-safety'), ('connection'),
    ('annotation'), ('build'), ('servlet');

-- Demo admin account.
-- Email:    admin@devvault.com
-- Password: admin123
INSERT INTO users (name, email, password, role_id) VALUES
('Admin User', 'admin@devvault.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 3);

-- Sample errors
INSERT INTO errors (title, error_message, description, cause, technology_id, category_id, posted_by) VALUES
('ClassNotFoundException',
 'java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver',
 'Thrown when the JVM tries to load a class by its name but cannot find it on the classpath.',
 'The required JAR is missing from the classpath, or the fully qualified class name is misspelled.',
 1, 1, 1),

('NullPointerException',
 'java.lang.NullPointerException: Cannot invoke "String.length()" because "str" is null',
 'Occurs when your code tries to use a reference that points to no object.',
 'A variable was never initialised, or a method returned null and the result was used without checking.',
 1, 1, 1),

('BeanCreationException in Spring',
 'org.springframework.beans.factory.BeanCreationException: Error creating bean with name ''userService''',
 'Spring could not construct one of the beans in the application context during startup.',
 'A dependency could not be autowired - usually the target class is outside the component-scan path.',
 2, 3, 1),

('Table doesn''t exist',
 'java.sql.SQLSyntaxErrorException: Table ''devvault.user'' doesn''t exist',
 'MySQL rejected the query because the referenced table is not present in the selected schema.',
 'Table name typo (singular vs plural), or the schema was never created / the wrong database is selected.',
 3, 4, 1);

-- Sample solutions
INSERT INTO solutions (error_id, posted_by, solution_text, code_example, is_accepted) VALUES
(1, 1,
 'Add the MySQL connector dependency to your pom.xml and run a Maven update so the driver JAR lands on the classpath.',
 '<dependency>\n  <groupId>com.mysql</groupId>\n  <artifactId>mysql-connector-j</artifactId>\n  <version>8.4.0</version>\n</dependency>',
 TRUE),

(2, 1,
 'Check for null before calling methods on an object, or use Optional to make the absence explicit.',
 'if (str != null) {\n    System.out.println(str.length());\n}',
 FALSE),

(3, 1,
 'Make sure the package containing the service is covered by @ComponentScan, and that the class is annotated with @Service.',
 '@Configuration\n@ComponentScan("com.devvault")\npublic class AppConfig { }',
 TRUE);

SELECT 'DevVault database created successfully!' AS status;
