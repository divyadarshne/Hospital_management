# Hospital_manament
---
## About
---
- The Hospital Management System is a Java-based backend application designed to manage hospital operations such as patients, doctors, appointments, and medical records.
- It provides REST-style APIs that allow clients (frontend or Postman) to perform CRUD operations efficiently.
---
## Tech stack Used
---
- Java
- Servlets & JSP
- JDBC
- MySQL
- Maven
- Apache Tomcat
- Jackson (JSON processing)
- Postman (API testing)
---
## API Endpoints and its methods
---
Post
- addpatient()   /patients
- addDoctors()   /doctors
- addAppointment()  /addAppointment
---
GET
- getallpatients()                 /getallpatients
- getpatientsbyId()                /patients
- getappointmentsbydoctorId()     /getappointmentsbydoctor
- getDoctorById()                 /doctors
- getAllAppointments()            /getallappointments
- 
---
PUT
- UpdatePatients()    /patients
- updateAppointment()   /addAppointment
---
Delete
- deleteAppointment()   /addAppointment
- 
