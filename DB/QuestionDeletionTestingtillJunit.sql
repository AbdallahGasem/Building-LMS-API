select * from Question

-- when assessment is deleted all its related questions are deleted 
-- which is NOT correct the Question is an Entity itself that can have 
-- non Assessment Related Questions!! [it is a quistion bank itself]

DBCC CHECKIDENT ('Question',RESEED,0);


INSERT INTO Question (AssessID, [Type], QHeader, Choices, CorrectAns,Topic)
VALUES 
(5, 'MCQ', 'What is 2+2?', 'A.3|B.4|C.5|D.6', 'B.4','Integration'),
(5, 'T/F', 'The earth is flat.', 'True|False', 'True','Physics');

-- like ignore the case while comparison use instead of = !
SELECT * FROM Question WHERE Topic LIKE 'INTEGRA%'


select * from Assessment a, Question q
WHERE a.AssessID = q.AssessID;

DELETE FROM Question