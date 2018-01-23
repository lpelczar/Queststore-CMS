CREATE TABLE User (
    ID INTEGER PRIMARY KEY,
    name VARCHAR (20) NOT NULL,
    login VARCHAR (20) NOT NULL,
    password VARCHAR (20) NOT NULL,
    phone_number VARCHAR (20) NOT NULL
    );

CREATE TABLE BlankUserData (
    ID INTEGER PRIMARY KEY,
    id_user INTEGER FOREIGN KEY NOT NULL,
    role VARCHAR (10)
    );

CREATE TABLE AdminData (
    ID INTEGER PRIMARY KEY,
    id_user INTEGER FOREIGN KEY NOT NULL,
    role VARCHAR (10)
    );

CREATE TABLE MentorData (
    ID INTEGER PRIMARY KEY,
    id_user INTEGER FOREIGN KEY NOT NULL,
    role VARCHAR (10)
    );

CREATE TABLE StudentData (
    ID INTEGER PRIMARY KEY,
    id_user INTEGER FOREIGN KEY NOT NULL,
    id_group INTEGER FOREIGN KEY NOT NULL,
    team_name VARCHAR (20),
    level VARCHAR (20),
    balance INTEGER,
    role VARCHAR (10)
    );

CREATE TABLE Group (
    ID INTEGER PRIMARY KEY,
    group_name VARCHAR (20) NOT NULL
    );

CREATE TABLE MentorGroup (
    id_mentor INTEGER FOREIGN KEY NOT NULL,
    id_group INTEGER FOREIGN KEY NOT NULL
    );

CREATE TABLE StudentTask (
    id_student INTEGER FOREIGN KEY NOT NULL,
    id_task INTEGER FOREIGN KEY NOT NULL
    );

CREATE TABLE Task (
    ID INTEGER PRIMARY KEY,
    name VARCHAR (20) NOT NULL,
    points INTEGER NOT NULL,
    description TEXT,
    category VARCHAR (20)
    );

CREATE TABLE StudentItem (
    id_student INTEGER FOREIGN KEY NOT NULL,
    id_item INTEGER FOREIGN KEY NOT NULL
    );

CREATE TABLE Item (
    ID INTEGER PRIMARY KEY,
    item_name VARCHAR (20) NOT NULL,
    description VARCHAR (20) NOT NULL,
    price INTEGER NOT NULL,
    category VARCHAR (20)
    );

CREATE TABLE ExperienceLevel (
    name VARCHAR (20) PRIMARY KEY,
    level INTEGER NOT NULL
    );

