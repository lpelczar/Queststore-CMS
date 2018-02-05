CREATE TABLE users (
  ID INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  login TEXT NOT NULL UNIQUE,
  email TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  phone_number TEXT NOT NULL UNIQUE,
  role TEXT
  );

CREATE TABLE mentors_data (
  id_user INTEGER,
  FOREIGN KEY (id_user) REFERENCES users(ID)
  );

CREATE TABLE students_data (
  id_user INTEGER,
  id_group INTEGER,
  team_name TEXT,
  level TEXT,
  balance INTEGER,
  experience INTEGER,
  PRIMARY KEY (id_user, id_group),
  FOREIGN KEY (id_user) REFERENCES users(ID),
  FOREIGN KEY (id_group) REFERENCES groups(ID)
  );

CREATE TABLE groups (
  ID INTEGER PRIMARY KEY,
  group_name TEXT NOT NULL UNIQUE
  );

CREATE TABLE mentors_groups (
  id_mentor INTEGER,
  id_group INTEGER,
  PRIMARY KEY (id_mentor, id_group),
  FOREIGN KEY (id_mentor) REFERENCES users(ID) ON DELETE CASCADE,
  FOREIGN KEY (id_group) REFERENCES groups(ID) ON DELETE CASCADE
  );

CREATE TABLE students_tasks (
  id_student INTEGER,
  id_task INTEGER,
  PRIMARY KEY (id_student, id_task),
  FOREIGN KEY (id_student) REFERENCES students_data(id_user) ON DELETE CASCADE,
  FOREIGN KEY (id_task) REFERENCES tasks(ID) ON DELETE CASCADE
  );

CREATE TABLE tasks (
  ID INTEGER PRIMARY KEY,
  name TEXT NOT NULL UNIQUE,
  points INTEGER NOT NULL,
  description TEXT,
  category TEXT
  );

CREATE TABLE students_items (
  id_student INTEGER,
  id_item INTEGER,
  PRIMARY KEY (id_student, id_item),
  FOREIGN KEY (id_student) REFERENCES students_data(id_user) ON DELETE CASCADE,
  FOREIGN KEY (id_item) REFERENCES items(ID) ON DELETE CASCADE
  );

CREATE TABLE items (
  ID INTEGER PRIMARY KEY,
  item_name TEXT NOT NULL,
  description TEXT NOT NULL,
  price INTEGER NOT NULL,
  category TEXT
  );

CREATE TABLE experience_levels (
  name TEXT PRIMARY KEY UNIQUE,
  level INTEGER NOT NULL
  );

