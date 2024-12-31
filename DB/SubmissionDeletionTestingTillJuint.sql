SELECT * FROM Submission;

-- to be deleted (delete a specific sub) --done
INSERT INTO Submission
VALUES (1,5,'http:/quiz1/tototototo.pdf','2024-12-26 5:00:00.000','submitted for grading',100,'fnan wallah!');


-- to be deleted (DELETE a assessmet's subs)	-- done
INSERT INTO Submission (AssessID, [SID], FileURL, SubTime, [Status], Score, FeedBack)
VALUES 
(1, 3, 'http://example.com/submissions/student1/quiz1.pdf', '2024-12-01 10:00:00', 'Graded', 90, 'Good job'),
(1,5,'http:/quiz1/tototototo.pdf','2024-12-26 5:00:00.000','submitted for grading',100,'fnan wallah!');


-- to be deleted (delete stud subs)	--done
INSERT INTO Submission (AssessID, [SID], FileURL, SubTime, [Status], Score, FeedBack)
VALUES 
(2,5,'http://example.com/submissions/abdallah/assignment1.pdf','2024-12-26 5:00:00.000','Graded',100,'fnan wallah!'),
(1,5,'http:/quiz1/tototototo.pdf','2024-12-26 5:00:00.000','submitted for grading',100,'fnan wallah!');


-- to be deleted (DELETE ALL)	--done
INSERT INTO Submission (AssessID, [SID], FileURL, SubTime, [Status], Score, FeedBack)
VALUES 
(1, 3, 'http://example.com/submissions/student1/quiz1.pdf', '2024-12-01 10:00:00', 'Graded', 90, 'Good job'),
(2,5,'http://example.com/submissions/abdallah/assignment1.pdf','2024-12-26 5:00:00.000','Graded',100,'fnan wallah!'),
(2, 4, 'http://example.com/submissions/student2/assignment1.pdf', '2024-12-02 11:30:00', 'Submitted for grading', NULL, NULL),
(1,5,'http:/quiz1/tototototo.pdf','2024-12-26 5:00:00.000','submitted for grading',100,'fnan wallah!');



