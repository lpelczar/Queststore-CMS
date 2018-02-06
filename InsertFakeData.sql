INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Admin","Admin","admin@codecool.com","admin123","123-456-789","Admin");

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Mentor","Mentor","mentor@codecool.com","mentor123","123-456-799","Mentor");

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Student","Student","student@codecool.com","student123","123-456-798","Student");

INSERT INTO students_data VALUES (3,1,"not assign yet","beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("nikodem","nikodem","nikodem@codecool.com","nikodem123","123-456-000","Student");

INSERT INTO students_data VALUES (4,1,"not assign yet","beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("ryszard","ryszard","ryszard@codecool.com","ryszard123","123-456-556","Student");

INSERT INTO students_data VALUES (5,1,"not assign yet","beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("janusz","janusz","janusz@codecool.com","janusz123","123-456-223","Student");

INSERT INTO students_data VALUES (6,1,"not assign yet","beginner",1000,0);

INSERT INTO groups (group_name) VALUES ("No group");

INSERT INTO tasks (name,points,description,category) VALUES ("dungeon", 100, "Finishing a Teamwork week", "Basic");
INSERT INTO tasks (name,points,description,category) VALUES ("puzzle", 100, "Finishing an SI assignment", "Basic");
INSERT INTO tasks (name,points,description,category) VALUES ("dragon", 500, "Passing a Checkpoint", "Basic");

INSERT INTO tasks (name,points,description,category) VALUES ("Spot trap", 50, "Spot a major mistake in the assignment", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("pet", 100, "Doing a demo about a pet project", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Recruiting", 100, "Taking part in the student screening process", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Forging", 400, "Organizing a workshop for other students", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Master the mornings", 300, "Attend 1 months without being late", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Fast as an unicorn", 500, "deliver 4 consecutive SI week assignments on time", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Achiever", 500, "set up a SMART goal accepted by a mentor, then achieve it", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("Fortune", 500, "students choose the best project of the week. Selected team scores", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("enchanted scroll", 500, "Creating extra material for the current TW/SI topic", "Extra");
INSERT INTO tasks (name,points,description,category) VALUES ("arena", 500, "Do a presentation on a meet-up", "Extra");
