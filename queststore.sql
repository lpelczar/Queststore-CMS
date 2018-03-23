BEGIN TRANSACTION;
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
INSERT INTO `users` (ID,name,login,email,password,phone_number,role) VALUES (1,'Admin','Admin','admin@codecool.com','admin123','123-456-789','Admin'),
 (2,'Mentor','Mentor','mentor@codecool.com','mentor123','123-456-799','Mentor'),
 (3,'Student','Student','student@codecool.com','student123','123-456-798','Student'),
 (4,'nikodem','nikodem','nikodem@codecool.com','nikodem123','123-456-000','Student'),
 (5,'ryszard','ryszard','ryszard@codecool.com','ryszard123','123-456-556','Student'),
 (6,'janusz','janusz','janusz@codecool.com','janusz123','123-456-223','Student'),
 (7,'kacperek','kacperek','kacperek@codecool.com','kacperek123','123-888-223','Student');
CREATE TABLE IF NOT EXISTS `tasks` (
	`ID`	INTEGER,
	`name`	TEXT NOT NULL UNIQUE,
	`points`	INTEGER NOT NULL,
	`description`	TEXT,
	`category`	TEXT,
	PRIMARY KEY(`ID`)
);
INSERT INTO `tasks` (ID,name,points,description,category) VALUES (1,'dungeon',100,'Finishing a Teamwork week','Basic'),
 (2,'puzzle',100,'Finishing an SI assignment','Basic'),
 (3,'dragon',500,'Passing a Checkpoint','Basic'),
 (4,'Spot trap',50,'Spot a major mistake in the assignment','Extra'),
 (5,'pet',100,'Doing a demo about a pet project','Extra'),
 (6,'Recruiting',100,'Taking part in the student screening process','Extra'),
 (7,'Forging',400,'Organizing a workshop for other students','Extra'),
 (8,'Master the mornings',300,'Attend 1 months without being late','Extra'),
 (9,'Fast as an unicorn',500,'deliver 4 consecutive SI week assignments on time','Extra'),
 (10,'Achiever',500,'set up a SMART goal accepted by a mentor, then achieve it','Extra'),
 (11,'Fortune',500,'students choose the best project of the week. Selected team scores','Extra'),
 (12,'enchanted scroll',500,'Creating extra material for the current TW/SI topic','Extra'),
 (13,'arena',500,'Do a presentation on a meet-up','Extra');
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
INSERT INTO `students_items` (id_student,id_item,is_used) VALUES (7,2,0),
 (3,7,0),
 (5,3,0),
 (5,4,0),
 (5,2,0),
 (7,5,0),
 (4,6,0),
 (4,1,0),
 (3,4,0);
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
INSERT INTO `students_data` (id_user,id_group,team_name,level,balance,experience) VALUES (3,1,'Not assigned','Beginner',1000,0),
 (4,2,'Not assigned','Beginner',1000,0),
 (5,2,'Not assigned','Beginner',1000,0),
 (6,3,'Not assigned','Beginner',1000,0),
 (7,4,'Not assigned','Beginner',1000,0);
CREATE TABLE IF NOT EXISTS `mentors_groups` (
	`id_mentor`	INTEGER,
	`id_group`	INTEGER,
	FOREIGN KEY(`id_group`) REFERENCES `groups`(`ID`) ON DELETE CASCADE,
	PRIMARY KEY(`id_mentor`,`id_group`),
	FOREIGN KEY(`id_mentor`) REFERENCES `users`(`ID`) ON DELETE CASCADE
);
INSERT INTO `mentors_groups` (id_mentor,id_group) VALUES (2,2),
 (2,3),
 (2,4);
CREATE TABLE IF NOT EXISTS `items` (
	`ID`	INTEGER,
	`item_name`	TEXT NOT NULL,
	`description`	TEXT NOT NULL,
	`price`	INTEGER NOT NULL,
	`category`	TEXT,
	PRIMARY KEY(`ID`)
);
INSERT INTO `items` (ID,item_name,description,price,category) VALUES (1,'Combat training','Private mentoring',50,'Basic'),
 (2,'Sanctuary','You can spend a day in home office',300,'Basic'),
 (3,'Time Travel','extend SI week assignment deadline by one day',500,'Basic'),
 (4,'Sorcery','60 min workshop by a mentor(s) of the chosen topic',1000,'Extra'),
 (5,'Summon Code Elemental','mentor joins a students'' team for a one hour',1000,'Extra'),
 (6,'Tome','Extra material for the current topic',500,'Extra'),
 (7,'Transform','All mentors should dress up as pirates (or just funny) for the day',5000,'Extra'),
 (8,'Teleport','The whole course goes to an off-school program instead for a day',30000,'Extra');
CREATE TABLE IF NOT EXISTS `groups` (
	`ID`	INTEGER,
	`group_name`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);
INSERT INTO `groups` (ID,group_name) VALUES (1,'No group'),
 (2,'Masters'),
 (3,'Dragons'),
 (4,'Kings');
CREATE TABLE IF NOT EXISTS `experience_levels` (
	`name`	TEXT UNIQUE,
	`level`	INTEGER NOT NULL,
	PRIMARY KEY(`name`)
);
INSERT INTO `experience_levels` (name,level) VALUES ('Low',200),
 ('Medium',1000),
 ('Pro',2000),
 ('God',5000);
COMMIT;
