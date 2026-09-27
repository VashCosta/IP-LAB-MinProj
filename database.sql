CREATE DATABASE IF NOT EXISTS eventdb;
USE eventdb;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    college VARCHAR(150) NOT NULL,
    department VARCHAR(100) NOT NULL,
    year_of_study VARCHAR(30) NOT NULL,
    password_hash VARCHAR(64) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS events (
    id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(150) NOT NULL,
    category VARCHAR(80) NOT NULL,
    description VARCHAR(500) NOT NULL,
    event_date DATE NOT NULL,
    start_time TIME NOT NULL,
    venue VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    fee DECIMAL(10,2) NOT NULL DEFAULT 0,
    available_seats INT NOT NULL DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS registrations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    registration_code VARCHAR(30) NOT NULL UNIQUE,
    participation_type VARCHAR(20) NOT NULL DEFAULT 'Individual',
    team_name VARCHAR(100) NULL,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_registration_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_registration_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT uq_user_event UNIQUE (user_id, event_id)
) ENGINE=InnoDB;

DELETE FROM registrations;
DELETE FROM events;

INSERT INTO events (event_name,category,description,event_date,start_time,venue,city,fee,available_seats) VALUES
('Code Surge','Coding Challenge','Competitive coding round with algorithmic and problem-solving challenges.','2026-10-10','09:30:00','Main Auditorium','Chennai',150,80),
('AI Arena','AI Quiz','Rapid-fire AI and data science quiz with two qualifying rounds.','2026-10-10','11:30:00','Seminar Hall','Chennai',100,100),
('WebCraft','Web Challenge','Design a responsive web experience within the given symposium theme.','2026-10-10','14:00:00','Computer Laboratory','Chennai',150,60),
('RoboRift','Robotics Challenge','Sensor, control and automation challenge for engineering teams.','2026-10-11','10:00:00','Innovation Lab','Chennai',200,40),
('Paper Pulse','Paper Presentation','Present an original idea in AI, cybersecurity, IoT or emerging technology.','2026-10-11','14:30:00','Conference Hall','Chennai',100,70);
