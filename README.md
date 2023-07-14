# Exam Generator
Automatic and manual exam generator with an sql intergrated data base using MVC design pattern.
This project was a course project for "OOP introduction" where we were asked to develop a software for generating tests from a pool of questions.
The pool of questions is made by the user, and the test generator can be manual where the user chooses the questions manually, or automatic where the questions are chose randomally.
In the original project the pool of questions and tests generated were saved in data structers, and JAVAFX was used for user experience.
The original project is in the folder "Original project"

In the second year we were asked in the course "Data Bases" to intergrate an SQL data base into our original project.
I implemented this intergration by directing all contruction of questions and tests to the SQL data base, every action that was made through the objects classes in the original project were now made directly with the SQL data base, this include adding questions, creating tests, changing wording of questions or answers, verifying that prerequisites are met before adding new data to the data base and so on.

----Prerequisites----

No duplicateed questions, this mean wording of questions can't be identical unless the questions are different types (Open/close questions)
For close questions : must have at least 4 answers, no duplicated answers, and 2 more answers are always added to every close questions whice are "all answers are correct" and "None of the answers are correct".


----Important information for running the software----

This software works with a local MySql Data base, so to run the software independently one must first create the data base using MySql workbench with the the code in the "MySqlCode" folder.
Query class found in the folder "module" is the class with all related functions to the DB.  the connection with DB is created in the function "createConnector".(you'll need to change the information there to your mysql local information)

Program flow with data base:

from the controller class in controller folder we are calling functions in the "operating system module" class ---->  "operating system module" functions related to the DB are calling the relevant functions in the Query class.

This program is using the JavaFX library.
