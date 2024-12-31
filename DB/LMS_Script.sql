----------------------------------- [DB creation]---------------------------------------
CREATE DATABASE LMS;

CREATE TABLE [UserTable] (
[UID] INT PRIMARY KEY IDENTITY(1,1),
[Name] VARCHAR(30),
[Email] VARCHAR(100) UNIQUE NOT NULL,
[Password] VARCHAR(8) NOT NULL,
[Role] VARCHAR(8) NOT NULL,
ProfilePic IMAGE NULL,
CreationAdminID INT NULL, 
CONSTRAINT chk_role CHECK ([Role] IN ('STUDENT', 'INSTRUCTOR', 'ADMIN')),
FOREIGN KEY (CreationAdminID) REFERENCES [UserTable]([UID]) -- <create> relationship Mapping
);


CREATE TABLE Course(
CrsID INT PRIMARY KEY IDENTITY(1,1),
Title VARCHAR(50) UNIQUE NOT NULL,
Syllabus TEXT ,
InstructorID INT NOT NULL FOREIGN KEY REFERENCES UserTable([UID]) -- <manages> RelationShip Mapping
);

-- Enroll RelationShip Between User and Course (m:n)
CREATE TABLE Enroll(
[SID] INT FOREIGN KEY REFERENCES UserTable([UID]),
CrsID INT FOREIGN KEY REFERENCES Course([CrsID])
PRIMARY KEY ([SID],CrsID)
);

-- weak entity
CREATE TABLE Lesson(
LessonID INT IDENTITY(1,1),
CrsID INT FOREIGN KEY REFERENCES Course(CrsID),
Title VARCHAR(50) NOT NULL,
[Description] TEXT,
MediaURL VARCHAR(255),
PRIMARY KEY (LessonID,CrsID)
);

CREATE TABLE Attendence(
AttID INT PRIMARY KEY IDENTITY(1,1),
[SID] INT NOT NULL FOREIGN KEY REFERENCES UserTable([UID]),
LessonID INT NOT NULL, --FOREIGN KEY REFERENCES Lesson([LessonID])
CrsID INT NOT NULL,
OTP VARCHAR(50) ,
[Status] BIT NOT NULL,
FOREIGN KEY (LessonID,CrsID) REFERENCES Lesson(LessonID,CrsID) 
);

CREATE TABLE Assessment(
AssessID INT PRIMARY KEY IDENTITY(1,1),
CrsID INT FOREIGN KEY REFERENCES Course(CrsID),
[Type] VARCHAR(6) NOT NULL,	
Deadline DateTime NOT NULL,
Topic VARCHAR(100) NOT NULL,
CONSTRAINT chk_AssessType CHECK ([Type] IN ('Assign','Quiz'))
);

CREATE TABLE Question(
QID INT PRIMARY KEY IDENTITY(1,1),
AssessID INT FOREIGN KEY REFERENCES Assessment(AssessID) null,
[Type] VARCHAR(5) NOT NULL,
QHeader TEXT NOT NULL,
Choices TEXT NOT NULL,
CorrectAns TEXT NOT NULL,
Topic VARCHAR(100) NOT NULL,
CONSTRAINT chk_QType CHECK ([Type] IN ('MCQ','T/F','SA'))
);

CREATE TABLE Submission(
AssessID INT FOREIGN KEY REFERENCES Assessment(AssessID),
[SID] INT FOREIGN KEY REFERENCES UserTable([UID]),
FileURL VARCHAR(255),
SubTime DATETIME NOT NULL DEFAULT getdate(),
[Status] VARCHAR(255), -- short sentence e.g. submitted for grading , graded ...
Score INT,
FeedBack TEXT,
PRIMARY KEY(AssessID,[SID])
);

CREATE TABLE Notifications(
NID INT PRIMARY KEY IDENTITY(1,1),
[UID] INT FOREIGN KEY REFERENCES UserTable([UID]),
[Type] VARCHAR(5),
[Message] Text Not Null,
[Status] BIT,	-- sent, not sent 
CONSTRAINT chk_NType CHECK ([Type] IN ('Sys','Email'))
);

 ------------------------------- [Inserting Data]---------------------------------------
-- USE THE DATABASE
USE LMS;

-- INSERT DATA INTO [UserTable]
INSERT INTO [UserTable] ([Name], [Email], [Password], [Role], [ProfilePic], [CreationAdminID])
VALUES 
('Admin1', 'admin1@example.com', 'Pass1234', 'ADMIN', NULL, NULL),
('Instructor1', 'instructor1@example.com', 'Instruc1', 'INSTRUC', NULL, 1), -- Created by Admin1
('Student1', 'student1@example.com', 'Stud1234', 'STUD', NULL, 1), -- Created by Admin1
('Student2', 'student2@example.com', 'Stud5678', 'STUD', NULL, 1); -- Created by Admin1

-- INSERT DATA INTO [Course]
INSERT INTO Course (Title, Syllabus, InstructorID)
VALUES 
('Math 101', 'Algebra, Geometry, Calculus', 2), -- Assigned to Instructor1
('Physics 101', 'Mechanics, Thermodynamics', 2);



-- INSERT DATA INTO [Enroll]
INSERT INTO Enroll ([SID], CrsID)
VALUES 
(3, 1), -- Student1 enrolled in Math 101
(3, 2), -- Student1 enrolled in Physics 101
(4, 1); -- Student2 enrolled in Math 101

-- INSERT DATA INTO [Lesson]
INSERT INTO Lesson (CrsID, Title, [Description], MediaURL)
VALUES 
(1, 'Introduction to Algebra', 'Basics of algebra and solving equations', 'http://example.com/media/algebra-intro.mp4'),
(2, 'Thermodynamics Basics', 'Introduction to thermodynamics concepts', 'http://example.com/media/thermo-basics.mp4');


-- INSERT DATA INTO [Attendence]
INSERT INTO Attendence ([SID], LessonID, CrsID, OTP, [Status])
VALUES 
(3, 1, 1, '12345', 1), -- Student1 attended Lesson1 in Math101
(4, 1, 1, '12345', 0); -- Student2 did not attend Lesson1 in Math101

-- INSERT DATA INTO [Assessment]
INSERT INTO Assessment (CrsID, [Type], Deadline,Topic)
VALUES 
(1, 'Quiz', '2024-11-29 23:59:59','Integration-1'),
(2, 'Assign', '2024-12-10 23:59:59','DC Circuts'),
(1, 'Assign', '2024-12-29 23:59:59','Conic Sections'),
(1, 'Assign', '2024-01-05 23:59:59','Integration-2');


-- INSERT DATA INTO [Question]
INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns,Topic)
VALUES 
(1, 'MCQ', 'What is 2+2?', 'A.3|B.4|C.5|D.6', 'B.4','Integration'),
(1, 'T/F', 'The earth is flat.', 'True|False', 'True','Physics');

-- Math101 Questions
INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns, Topic)
VALUES 
(NULL, 'MCQ', 'What is the square root of 16?', 'A.2|B.3|C.4|D.5', 'C.4', 'Algebra'),
(NULL, 'T/F', 'Is 0 an even number?', 'True|False', 'True', 'Numbers'),
(NULL, 'SA', 'Simplify: 5x - 3 = 2x + 9.', 'Answer the equation.', 'x=4', 'Equations'),
(NULL, 'MCQ', 'Which is the value of pi?', 'A.3.14|B.2.71|C.1.61|D.4.14', 'A.3.14', 'Geometry'),
(NULL, 'SA', 'Calculate the derivative of x^2 + 3x.', 'Provide the derivative.', '2x+3', 'Calculus');

-- Physics Questions
INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns, Topic)
VALUES 
(NULL, 'MCQ', 'What is the speed of light?', 'A.300,000 km/s|B.150,000 km/s|C.75,000 km/s|D.600,000 km/s', 'A.300,000 km/s', 'Electromagnetism'),
(NULL, 'T/F', 'Is gravity a contact force?', 'True|False', 'False', 'Mechanics'),
(NULL, 'SA', 'Explain Newton’s Second Law of Motion.', 'Write the formula and definition.', 'F=ma', 'Laws of Motion'),
(NULL, 'MCQ', 'What is the unit of force?', 'A.Newton|B.Joule|C.Watt|D.Pascal', 'A.Newton', 'Units & Measurements'),
(NULL, 'T/F', 'Does sound travel faster in water than in air?', 'True|False', 'True', 'Waves');

-- Additional Questions for Variety
INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns, Topic)
VALUES 
(NULL, 'MCQ', 'What is 5 + 5?', 'A.10|B.15|C.20|D.5', 'A.10', 'Algebra'),
(NULL, 'T/F', 'Energy can be created or destroyed.', 'True|False', 'False', 'Thermodynamics'),
(NULL, 'SA', 'Name the first element in the periodic table.', 'Provide the element.', 'Hydrogen', 'Chemistry Basics'),
(NULL, 'MCQ', 'What is the formula for acceleration?', 'A.v/t|B.d/t|C.dv/dt|D.F/m', 'C.dv/dt', 'Kinematics'),
(NULL, 'T/F', 'Does heat transfer from cold to hot naturally?', 'True|False', 'False', 'Heat Transfer');

INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns, Topic)
VALUES 
(NULL, 'MCQ', 'What is the solution to the equation 2x + 3 = 7?', 'A.1|B.2|C.3|D.4', 'B.2', 'Algebra'),
(NULL, 'MCQ', 'What is the value of x in the equation x^2 - 9 = 0?', 'A.3|B.-3|C.±3|D.0', 'C.±3', 'Algebra'),
(NULL, 'MCQ', 'Simplify: (x + 2)(x - 2).', 'A.x^2 - 4|B.x^2 + 4|C.x^2 - 2x|D.x^2 + 2x', 'A.x^2 - 4', 'Algebra'),
(NULL, 'MCQ', 'If y = 3x + 2, what is the value of y when x = 4?', 'A.12|B.13|C.14|D.15', 'D.14', 'Algebra'),
(NULL, 'MCQ', 'What is the slope of the line represented by the equation 2x - 3y = 6?', 'A.-2/3|B.2/3|C.-3/2|D.3/2', 'A.-2/3', 'Algebra'),
(NULL, 'MCQ', 'Solve for x: 5x - 7 = 3x + 9.', 'A.8|B.-8|C.16|D.-16', 'A.8', 'Algebra'),
(NULL, 'MCQ', 'Which of the following represents a quadratic equation?', 'A.2x + 3 = 0|B.x^2 - 4x + 4 = 0|C.3x - 5 = 2x + 1|D.4x^3 = 16', 'B.x^2 - 4x + 4 = 0', 'Algebra'),
(NULL, 'MCQ', 'What is the sum of the roots of the equation x^2 - 5x + 6 = 0?', 'A.3|B.-3|C.5|D.-5', 'C.5', 'Algebra'),
(NULL, 'MCQ', 'What is the product of the roots of the equation x^2 - 3x + 2 = 0?', 'A.2|B.-2|C.3|D.-3', 'A.2', 'Algebra'),
(NULL, 'MCQ', 'Simplify: (3x + 2)(2x - 1).', 'A.6x^2 + x - 2|B.6x^2 - x + 2|C.6x^2 - x - 2|D.6x^2 + x + 2', 'C.6x^2 - x - 2', 'Algebra'),
(NULL, 'MCQ', 'If 2x + y = 10 and x = 3, what is the value of y?', 'A.2|B.3|C.4|D.5', 'C.4', 'Algebra'),
(NULL, 'MCQ', 'What is the greatest common factor (GCF) of 12x^2 and 18x?', 'A.6x|B.12x|C.18x|D.2x', 'A.6x', 'Algebra'),
(NULL, 'MCQ', 'What is the value of x if x^2 = 25?', 'A.±5|B.5|C.-5|D.0', 'A.±5', 'Algebra'),
(NULL, 'MCQ', 'If f(x) = 2x + 3, what is f(4)?', 'A.7|B.9|C.11|D.13', 'D.11', 'Algebra'),
(NULL, 'MCQ', 'What is the factored form of x^2 - 6x + 9?', 'A.(x - 3)(x + 3)|B.(x - 3)(x - 3)|C.(x + 3)(x + 3)|D.x(x - 9)', 'B.(x - 3)(x - 3)', 'Algebra');


-- INSERT DATA INTO [Submission]
INSERT INTO Submission (AssessID, [SID], FileURL, SubTime, [Status], Score, FeedBack)
VALUES 
(1, 3, 'http://example.com/submissions/student1/quiz1.pdf', '2024-12-01 10:00:00', 'Graded', 90, 'Good job'),
(2, 4, 'http://example.com/submissions/student2/assignment1.pdf', '2024-12-02 11:30:00', 'Submitted for grading', NULL, NULL);


-- INSERT DATA INTO [Notifications]
INSERT INTO Notifications ([UID], [Type], [Message], [Status])
VALUES 
(3, 'Sys', 'You have a new quiz to complete in Math 101.', 1),
(4, 'Email', 'Your assignment in Physics 101 is due soon.', 0);


-------------------------------------------------------------
-- altering Question to specify set null on deletion!
ALTER TABLE Question 
DROP CONSTRAINT FK__Question__Assess__4316F928;

ALTER TABLE Question
ALTER COLUMN AssessID INT NULL;

ALTER TABLE Question
ADD CONSTRAINT FK__Question__Assess__4316F928
FOREIGN KEY (AssessID) REFERENCES Assessment(AssessID)
ON DELETE SET NULL;
