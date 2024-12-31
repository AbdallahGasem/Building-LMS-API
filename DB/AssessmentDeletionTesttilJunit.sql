USE LMS

SELECT * FROM Assessment;

-- reseting the identity!
DBCC CHECKIDENT ('Assessment', RESEED, 0);

INSERT INTO Assessment (CrsID, [Type], Deadline,Topic)
VALUES 
(1, 'Quiz', '2024-11-29 23:59:59','Integration-1'),
(2, 'Assign', '2024-12-10 23:59:59','DC Circuts'),
(1, 'Assign', '2024-12-29 23:59:59','Conic Sections'),
(1, 'Assign', '2024-01-05 23:59:59','Integration-2');

