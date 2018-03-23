CREATE TABLE IF NOT EXISTS `users` (
	`ID`	INTEGER,
	`name`	TEXT NOT NULL,
	`login`	TEXT NOT NULL UNIQUE,
	`email`	TEXT NOT NULL UNIQUE,
	`password`	TEXT NOT NULL,
	`phone_number`	TEXT NOT NULL UNIQUE,
	`role`	TEXT,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `tasks` (
	`ID`	INTEGER,
	`name`	TEXT NOT NULL UNIQUE,
	`points`	INTEGER NOT NULL,
	`description`	TEXT,
	`category`	TEXT,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `students_tasks` (
	`id_student`	INTEGER,
	`id_task`	INTEGER,
	PRIMARY KEY(`id_student`,`id_task`),
	FOREIGN KEY(`id_task`) REFERENCES `tasks`(`ID`) ON DELETE CASCADE,
	FOREIGN KEY(`id_student`) REFERENCES `users`(`ID`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `students_items` (
	`id_student`	INTEGER,
	`id_item`	INTEGER,
	`is_used`	BOOLEAN,
	FOREIGN KEY(`id_student`) REFERENCES `users`(`ID`) ON DELETE CASCADE,
	PRIMARY KEY(`id_student`,`id_item`),
	FOREIGN KEY(`id_item`) REFERENCES `items`(`ID`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `students_data` (
	`id_user`	INTEGER,
	`id_group`	INTEGER DEFAULT 1,
	`team_name`	TEXT,
	`level`	TEXT,
	`balance`	INTEGER,
	`experience`	INTEGER,
	FOREIGN KEY(`id_group`) REFERENCES `groups`(`ID`) ON DELETE SET DEFAULT,
	PRIMARY KEY(`id_user`,`id_group`),
	FOREIGN KEY(`id_user`) REFERENCES `users`(`ID`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `mentors_groups` (
	`id_mentor`	INTEGER,
	`id_group`	INTEGER,
	FOREIGN KEY(`id_group`) REFERENCES `groups`(`ID`) ON DELETE CASCADE,
	PRIMARY KEY(`id_mentor`,`id_group`),
	FOREIGN KEY(`id_mentor`) REFERENCES `users`(`ID`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `items` (
	`ID`	INTEGER,
	`item_name`	TEXT NOT NULL,
	`description`	TEXT NOT NULL,
	`price`	INTEGER NOT NULL,
	`category`	TEXT,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `groups` (
	`ID`	INTEGER,
	`group_name`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `experience_levels` (
	`name`	TEXT UNIQUE,
	`level`	INTEGER NOT NULL,
	PRIMARY KEY(`name`)
);


INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Admin","Admin","admin@codecool.com","admin123","123-456-789","Admin");

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Mentor","Mentor","mentor@codecool.com","mentor123","123-456-799","Mentor");

INSERT INTO groups (group_name) VALUES ("No group");
INSERT INTO groups (group_name) VALUES ("Masters");
INSERT INTO groups (group_name) VALUES ("Dragons");
INSERT INTO groups (group_name) VALUES ("Kings");

INSERT INTO mentors_groups (id_mentor, id_group) VALUES (2,2);
INSERT INTO mentors_groups (id_mentor, id_group) VALUES (2,3);
INSERT INTO mentors_groups (id_mentor, id_group) VALUES (2,4);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("Student","Student","student@codecool.com","student123","123-456-798","Student");

INSERT INTO students_data VALUES (3,1,"Not assigned","Beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("nikodem","nikodem","nikodem@codecool.com","nikodem123","123-456-000","Student");

INSERT INTO students_data VALUES (4,2,"Not assigned","Beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("ryszard","ryszard","ryszard@codecool.com","ryszard123","123-456-556","Student");

INSERT INTO students_data VALUES (5,2,"Not assigned","Beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("janusz","janusz","janusz@codecool.com","janusz123","123-456-223","Student");

INSERT INTO students_data VALUES (6,3,"Not assigned","Beginner",1000,0);

INSERT INTO users (name,login,email,password,phone_number,role)
VALUES ("kacperek","kacperek","kacperek@codecool.com","kacperek123","123-888-223","Student");

INSERT INTO students_data VALUES (7,4,"Not assigned","Beginner",1000,0);

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

INSERT INTO experience_levels (name, level) VALUES ("Low", 200);
INSERT INTO experience_levels (name, level) VALUES ("Medium", 1000);
INSERT INTO experience_levels (name, level) VALUES ("Pro", 2000);
INSERT INTO experience_levels (name, level) VALUES ("God", 5000);

INSERT INTO items (item_name, description, price, category)
VALUES ("Combat training", "Private mentoring", 50, "Basic");
INSERT INTO items (item_name, description, price, category)
VALUES ("Sanctuary", "You can spend a day in home office", 300, "Basic");
INSERT INTO items (item_name, description, price, category)
VALUES ("Time Travel", "extend SI week assignment deadline by one day", 500, "Basic");
INSERT INTO items (item_name, description, price, category)
VALUES ("Sorcery", "60 min workshop by a mentor(s) of the chosen topic", 1000, "Extra");
INSERT INTO items (item_name, description, price, category)
VALUES ("Summon Code Elemental", "mentor joins a students' team for a one hour", 1000, "Extra");
INSERT INTO items (item_name, description, price, category)
VALUES ("Tome", "Extra material for the current topic", 500, "Extra");
INSERT INTO items (item_name, description, price, category)
VALUES ("Transform", "All mentors should dress up as pirates (or just funny) for the day", 5000, "Extra");
INSERT INTO items (item_name, description, price, category)
VALUES ("Teleport", "The whole course goes to an off-school program instead for a day", 30000, "Extra");

INSERT INTO students_items (id_student, id_item, is_used) VALUES (7,2,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (3,7,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (5,3,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (5,4,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (5,2,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (7,5,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (4,6,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (4,1,0);
INSERT INTO students_items (id_student, id_item, is_used) VALUES (3,4,0);