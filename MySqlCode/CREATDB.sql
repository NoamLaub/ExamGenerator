
create database ExamGeneratorDB;

use ExamGeneratorDB;

CREATE TABLE OperatingSystemModule
(moduleSerialNum INT NOT NULL,
closeQuestionCounter INT NOT NULL,
openQuestionCounter INT NOT NULL,
testCounter INT NOT NULL,
answerCounter INT NOT NULL,
primary key (moduleSerialNum))
ENGINE = InnoDB;

CREATE TABLE Question
(SerialNum INT NOT NULL,
moduleSerialNum INT NOT NULL,
 QuestionText VARCHAR(250),
 QuestionType ENUM("Open","Closed") NOT NULL,
PRIMARY KEY (SerialNum,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum))
ENGINE = InnoDB;

CREATE TABLE AmericanQuestion
(SerialNum INT NOT NULL,
moduleSerialNum INT NOT NULL,
 NumOfAnswers INT,
PRIMARY KEY (SerialNum,moduleSerialNum),
FOREIGN KEY (SerialNum,moduleSerialNum) REFERENCES Question(SerialNum,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum))
ENGINE = InnoDB;



CREATE TABLE Answer
(AnswerID INT NOT NULL,
moduleSerialNum INT NOT NULL,
AnswerText VARCHAR(250),
AnswerCorrectness boolean,
QuestionID INT NOT NULL,
PRIMARY KEY (AnswerID,moduleSerialNum),
FOREIGN KEY (QuestionID,moduleSerialNum) REFERENCES Question(SerialNum,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum))
ENGINE = InnoDB;

CREATE TABLE Test
(TestID INT NOT NULL,
moduleSerialNum INT NOT NULL,
NumOfQuestions INT NOT NULL,
PRIMARY KEY (TestID,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum))
ENGINE = InnoDB;




CREATE TABLE ModulehasTestsAndQuestions
(TestID INT NOT NULL,
QuestionID INT NOT NULL,
moduleSerialNum INT NOT NULL,
QuestionIndex INT NOT NULL,
NumOfAnswers INT NOT NULL,

PRIMARY KEY (TestID,QuestionID,moduleSerialNum),
FOREIGN KEY (TestID,moduleSerialNum) REFERENCES test(TestID,moduleSerialNum),
FOREIGN KEY (QuestionID,moduleSerialNum) REFERENCES question(SerialNum,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum)
)
ENGINE = InnoDB;

CREATE TABLE ModulehasTestsAndAnswers
(TestID INT NOT NULL,
AnswerID INT NOT NULL,
moduleSerialNum INT NOT NULL,
AnswerIndex INT NOT NULL,
QuestionID INT NOT NULL,
AnswerCorrectness INT NOT NULL,

PRIMARY KEY (TestID,AnswerID,moduleSerialNum),
FOREIGN KEY (TestID,moduleSerialNum) REFERENCES test(TestID,moduleSerialNum),
FOREIGN KEY (QuestionID,moduleSerialNum) REFERENCES question(SerialNum,moduleSerialNum),
FOREIGN KEY (AnswerID,moduleSerialNum) REFERENCES answer(AnswerID,moduleSerialNum),
FOREIGN KEY (moduleSerialNum) REFERENCES OperatingSystemModule(moduleSerialNum),
FOREIGN KEY(TestID,moduleSerialNum,QuestionID) REFERENCES ModulehasTestsAndQuestions(TestID,moduleSerialNum,QuestionID)
)
ENGINE = InnoDB;
















