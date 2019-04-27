CREATE TABLE University (
  id VARCHAR(60) PRIMARY KEY,
  name VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE Instructor (
  id VARCHAR(60) PRIMARY KEY,
  email VARCHAR(60) UNIQUE NOT NULL,
  firstName VARCHAR(60) NOT NULL,
  prefix VARCHAR(15),
  lastName VARCHAR(60) NOT NULL,
  ref_university VARCHAR(60) REFERENCES University(id)
);

CREATE TABLE Student (
  id VARCHAR(60) PRIMARY KEY,
  email VARCHAR(60) UNIQUE NOT NULL,
  firstName VARCHAR(60) NOT NULL,
  prefix VARCHAR(15),
  lastName VARCHAR(60) NOT NULL,
  ref_university VARCHAR(60) REFERENCES University(id)
);

CREATE TABLE Course (
  id VARCHAR(60) PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  code VARCHAR(60) UNIQUE NOT NULL,
  ref_university VARCHAR(60) REFERENCES University(id),
  ref_instructor VARCHAR(60) REFERENCES Instructor(id)
);

CREATE TABLE Student_Course (
  ref_course VARCHAR(60) REFERENCES Course(id),
  ref_student VARCHAR(60) REFERENCES Student(id),
  PRIMARY KEY (ref_course, ref_student)
);

CREATE TABLE Period (
  id VARCHAR(60) PRIMARY KEY,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  ref_course VARCHAR(60) REFERENCES Course(id)
);

CREATE TABLE FeedbackSession (
  id VARCHAR(60) PRIMARY KEY
);

CREATE TABLE Student_FeedbackSession (
  ref_student VARCHAR(60) REFERENCES Student(id),
  ref_feedbacksession VARCHAR(60) REFERENCES FeedbackSession(id),
  PRIMARY KEY (ref_student, ref_feedbacksession)
);

CREATE TABLE Instructor_FeedbackSession (
  ref_instructor VARCHAR(60) REFERENCES Instructor(id),
  ref_feedbacksession VARCHAR(60) REFERENCES FeedbackSession(id),
  PRIMARY KEY (ref_instructor, ref_feedbacksession)
);

CREATE TABLE Question (
  id VARCHAR(60) PRIMARY KEY,
  text VARCHAR(255) NOT NULL,
  ref_feedbacksession VARCHAR(60) REFERENCES FeedbackSession(id),
  ref_instructor VARCHAR(60) REFERENCES Instructor(id)
);

CREATE TABLE Response (
  id VARCHAR(60) PRIMARY KEY,
  text VARCHAR(255) NOT NULL,
  ref_question VARCHAR(60) REFERENCES Question(id),
  ref_student VARCHAR(60) REFERENCES Student(id)
);

CREATE TABLE Comment (
  id VARCHAR(60) PRIMARY KEY,
  text VARCHAR(255) NOT NULL,
  ref_response VARCHAR(60) REFERENCES Response(id),
  ref_instructor VARCHAR(60) REFERENCES Instructor(id),
  ref_student VARCHAR(60) REFERENCES Student(id)
);

CREATE TABLE Comment_Comment (
  parent_comment VARCHAR(60) REFERENCES Comment(id),
  child_comment VARCHAR(60) REFERENCES Comment(id),
  PRIMARY KEY (parent_comment, child_comment)
);