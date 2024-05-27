CREATE DATABASE employeeDetails;
USE employeeDetails;

drop table Personal_Info;
drop table Employee_Info;
drop table Department;
drop table Position;

CREATE TABLE login (
    username VARCHAR(20),
    password VARCHAR(20)
);

CREATE TABLE Userlogin (
    username VARCHAR(20),
    password VARCHAR(20)
);

INSERT INTO Userlogin VALUES
('johndoe', '123456789'),
('janesmith', '987654321'),
('michaeljohnson', '555555555');

INSERT INTO login VALUES ('Admin', '123456789');

CREATE TABLE Personal_Info (
    Personal_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Gender VARCHAR(10),
    Birthday DATE
);

INSERT INTO Personal_Info (Name, Gender, Birthday) VALUES
('John Doe', 'Male', '1990-05-15'),
('Jane Smith', 'Female', '1985-08-20'),
('Michael Johnson', 'Male', '1992-12-10');

CREATE TABLE Employee_Info (
    Personal_ID INT,
    Employee_ID INT AUTO_INCREMENT PRIMARY KEY,
    Department_ID INT,
    Position_ID INT,
    Salary DECIMAL(10, 2),
    Address VARCHAR(255),
    Phone VARCHAR(20),
    Email VARCHAR(100),
    Landline_Number VARCHAR(20),
    Username VARCHAR(20)
);

INSERT INTO Employee_Info (Personal_ID, Department_ID, Position_ID, Salary, Address, Phone, Email, Landline_Number, Username) VALUES
(1, 1, 1, 60000.00, '123 Main St, Anytown', '123-456-7890', 'john.doe@example.com', '555-1234', 'johndoe'),
(2, 2, 2, 80000.00, '456 Elm St, Anytown', '987-654-3210', 'jane.smith@example.com', '555-5678', 'janesmith'),
(3, 3, 3, 55000.00, '789 Oak St, Anytown', '555-123-4567', 'michael.johnson@example.com', '555-9876', 'michaeljohnson');

CREATE TABLE Department (
    Department_ID INT AUTO_INCREMENT PRIMARY KEY,
    Department_Name VARCHAR(100),
    Department_Head VARCHAR(100)
);

INSERT INTO Department (Department_Name, Department_Head) VALUES
('Human Resources', 'Emily Johnson'),
('IT Department', 'Andrew Williams'),
('Marketing', 'Jennifer Lee');

CREATE TABLE Position (
    Position_ID INT AUTO_INCREMENT PRIMARY KEY,
    Position_Name VARCHAR(100)
);

INSERT INTO Position (Position_Name) VALUES
('HR Manager'),
('Software Engineer'),
('Marketing Coordinator');

ALTER TABLE Employee_Info
ADD CONSTRAINT fk_personal_id
FOREIGN KEY (Personal_ID) REFERENCES Personal_Info(Personal_ID);

ALTER TABLE Employee_Info
ADD CONSTRAINT fk_department_id
FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID);

ALTER TABLE Employee_Info
ADD CONSTRAINT fk_position_id
FOREIGN KEY (Position_ID) REFERENCES `Position`(Position_ID);

ALTER TABLE Employee_Info ADD HighestEducation VARCHAR(50);
UPDATE Employee_Info SET HighestEducation = 'Undergraduate' WHERE Employee_ID = 1;
UPDATE Employee_Info SET HighestEducation = 'High School Graduate' WHERE Employee_ID = 2;
UPDATE Employee_Info SET HighestEducation = 'College Graduate' WHERE Employee_ID = 3;

SELECT * FROM Personal_Info;
SELECT * FROM Employee_Info;
SELECT * FROM Department;
SELECT * FROM Position;
