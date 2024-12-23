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
CONSTRAINT chk_role CHECK ([Role] IN ('ADMIN', 'INSTRUC', 'STUD')),
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
QID INT  PRIMARY KEY IDENTITY(1,1),
AssessID INT FOREIGN KEY REFERENCES Assessment(AssessID),
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
Score INT ,
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
INSERT INTO Assessment (CrsID, [Type], Deadline)
VALUES 
(1, 'Quiz', '2024-12-31 23:59:59'),
(2, 'Assign', '2024-12-25 23:59:59');


-- INSERT DATA INTO [Question]
INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns,Topic)
VALUES 
(1, 'MCQ', 'What is 2+2?', 'A.3|B.4|C.5|D.6', 'B.4','Integration'),
(1, 'T/F', 'The earth is flat.', 'True|False', 'True','Physics');

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
