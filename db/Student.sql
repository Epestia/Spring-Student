-- Supprime la base de donnée :
DROP DATABASE Student;

-- Création de la base de données Student :
CREATE DATABASE Student;

-- Utilisation de la base de donnée Student : 
USE Student;

-- Create Table Student : 
CREATE TABLE Student (
    StudentID INT PRIMARY KEY IDENTITY(1,1),
    FirstName NVARCHAR(200),
    LastName NVARCHAR(200),
    BirthDate DATE,
	Mail NVARCHAR(200) UNIQUE,
	PasswordHash NVARCHAR(255)
);

-- Create Table Courses :
CREATE TABLE Course (
    CourseID INT PRIMARY KEY IDENTITY(1,1),
    CourseName NVARCHAR(100),
    CourseDescription NVARCHAR(255)
);

-- Create the Enrollments table to link Students to Courses :
CREATE TABLE Enrollment (
    EnrollmentID INT PRIMARY KEY IDENTITY(1,1),
    StudentID INT,
    CourseID INT,
    EnrollmentDate DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);

-- Create the Address table :
CREATE TABLE Address (
    StudentID INT NOT NULL,
    Street VARCHAR(500),
    Number VARCHAR(50),
    PostalCode VARCHAR(50),
    City VARCHAR(200),
    PRIMARY KEY (StudentID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID) ON DELETE CASCADE
);

-- Index :
CREATE NONCLUSTERED INDEX IX_Students_LastName_FirstName ON Student (LastName, FirstName);
CREATE NONCLUSTERED INDEX IX_Courses_CourseName ON Course (CourseName);
CREATE NONCLUSTERED INDEX IX_Enrollments_CourseID ON Enrollment (CourseID);
CREATE NONCLUSTERED INDEX IX_Enrollments_StudentID ON Enrollment (StudentID);
CREATE INDEX idx_street ON Address (Street);
CREATE INDEX idx_postal_code ON Address (PostalCode);
CREATE INDEX idx_city ON Address (City);