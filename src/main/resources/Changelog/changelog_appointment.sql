-- liquibase formatted sql

-- changeset dev1:create-patient-table
CREATE TABLE patient (
patient_id int AUTO_INCREMENT PRIMARY KEY,
patient_name VARCHAR(50) NOT NULL,
gender VARCHAR(20),
dob DATE,
blood_group VARCHAR(20),
city VARCHAR(20),
diagnosis VARCHAR(50),
phone_number VARCHAR(50));

-- changeset dev1:create-doctor-table
CREATE TABLE doctor(
doctor_id int AUTO_INCREMENT PRIMARY KEY,
doctor_name varchar(50),
specialization varchar(100));

-- changeset dev1:create-Appointment-table
CREATE TABLE appointment(
appointment_id int AUTO_INCREMENT PRIMARY KEY,
patient_id int,
doctor_id int,
appointment_date date,
total_appointment int,
finished_appointment int,
FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id));
