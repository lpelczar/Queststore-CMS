CREATE TABLE User (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR (20) NOT NULL,
  login VARCHAR (20) NOT NULL,
  email VARCHAR (20) NOT NULL,
  password VARCHAR (20) NOT NULL,
  phone_number VARCHAR (20) NOT NULL,
  role VARCHAR (10)
  );

CREATE TABLE BlankUserData (
  id_user INTEGER,
  role VARCHAR (10),
  FOREIGN KEY (id_user) REFERENCES User(ID)
  );

CREATE TABLE AdminData (
  id_user INTEGER,
  FOREIGN KEY (id_user) REFERENCES User(ID)
  );

CREATE TABLE MentorData (
  id_user INTEGER,
  FOREIGN KEY (id_user) REFERENCES User(ID)
  );

CREATE TABLE StudentData (
  id_user INTEGER,
  id_group INTEGER,
  team_name VARCHAR (20),
  level VARCHAR (20),
  balance INTEGER,
  FOREIGN KEY (id_user) REFERENCES User(ID),
  FOREIGN KEY (id_group) REFERENCES GroupTable(ID)
  );

CREATE TABLE GroupTable (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  group_name VARCHAR (20) NOT NULL
  );

CREATE TABLE MentorGroup (
  id_mentor INTEGER,
  id_group INTEGER,
  PRIMARY KEY (id_mentor, id_group),
  FOREIGN KEY (id_mentor) REFERENCES User(ID) ON DELETE CASCADE,
  FOREIGN KEY (id_group) REFERENCES GroupTable(ID) ON DELETE CASCADE
  );

CREATE TABLE StudentTask (
  id_student INTEGER,
  id_task INTEGER,
  FOREIGN KEY (id_student) REFERENCES StudentData(id_user),
  FOREIGN KEY (id_task) REFERENCES Task(ID)
  );

CREATE TABLE Task (
  ID INTEGER PRIMARY KEY,
  name VARCHAR (20) NOT NULL,
  points INTEGER NOT NULL,
  description TEXT,
  category VARCHAR (20)
  );

CREATE TABLE StudentItem (
  id_student INTEGER,
  id_item INTEGER,
  FOREIGN KEY (id_student) REFERENCES StudentData(id_user),
  FOREIGN KEY (id_item) REFERENCES Item(ID)
  );

CREATE TABLE Item (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  item_name VARCHAR (20) NOT NULL,
  description VARCHAR (20) NOT NULL,
  price INTEGER NOT NULL,
  category VARCHAR (20)
  );

CREATE TABLE ExperienceLevel (
  name VARCHAR (20) PRIMARY KEY,
  level INTEGER NOT NULL
  );

