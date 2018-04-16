CREATE TABLE admins (
  admin_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL, email
  TEXT NOT NULL
);

CREATE TABLE artefacts (
  artefact_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  price INTEGER NOT NULL,
  default_status INTEGER
);

CREATE TABLE "group_names" (
  `group_name_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `signature` TEXT NOT NULL,
  `mentor_id` INTEGER
);

CREATE TABLE "groups" (
  `group_name_id` INTEGER NOT NULL,
  `student_id` INTEGER NOT NULL
);

CREATE TABLE "logins" (
  `user_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `login` TEXT NOT NULL UNIQUE,
  `password` TEXT NOT NULL UNIQUE,
  `role` TEXT
);

CREATE TABLE "mentors" (
  `mentor_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL,
  `lastname` TEXT NOT NULL,
  `email` TEXT
);

CREATE TABLE "quests" (
  `quest_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` TEXT,
  `description` TEXT NOT NULL,
  `price` INTEGER NOT NULL
);

CREATE TABLE `role` (
  `id_role` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL
);

CREATE TABLE "status" (
  `status_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `status_name` TEXT NOT NULL
);

CREATE TABLE "students" (
  `student_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name` TEXT NOT NULL,
  `lastname` TEXT NOT NULL,
  `email` TEXT NOT NULL
);

CREATE TABLE "students_artefacts" (
  `artefact_id` INTEGER,
  `status_id` INTEGER,
  `student_id` INTEGER
);

CREATE TABLE `students_quests` (
  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
  `quest_id` INTEGER NOT NULL,
  `status_id` INTEGER NOT NULL,
  `student_id` INTEGER NOT NULL
);

CREATE TABLE "students_wallets" (
  `student_id` INTEGER NOT NULL UNIQUE,
  `balance` INTEGER NOT NULL, `total_earned` INTEGER NOT NULL,
  PRIMARY KEY(student_id)
);