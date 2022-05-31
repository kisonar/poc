CREATE TABLE users
(
    userId       int(11) NOT NULL AUTO_INCREMENT,
    userName     varchar(15),
    userPassword varchar(15),
    userEmail    varchar(50),
    PRIMARY KEY (userId)
);
COMMIT;

INSERT INTO users(userId, userName, userPassword, userEmail)
VALUES (null, 'qcsAdmin', 'qcsAdmin', 'qcsAdmin@mail.com');
INSERT INTO users(userId, userName, userPassword, userEmail)
VALUES (null, 'qcsEngineer', 'qcsEngineer', 'qcsEngineer@mail.com');
INSERT INTO users(userId, userName, userPassword, userEmail)
VALUES (null, 'qcsClient', 'qcsClient', 'qcsClient@mail.com');
INSERT INTO users(userId, userName, userPassword, userEmail)
VALUES (null, 'Marcin', 'MarcinPass', 'marcinmigdal@wp.pl');
COMMIT;

CREATE TABLE roles
(
    roleId   int(11) NOT NULL AUTO_INCREMENT,
    roleName varchar(15),
    PRIMARY KEY (roleId)
);
COMMIT;

INSERT INTO roles(roleId, roleName)
VALUES (null, 'Administrator');
INSERT INTO roles(roleId, roleName)
VALUES (null, 'Engineer');
INSERT INTO roles(roleId, roleName)
VALUES (null, 'QCS Client');
INSERT INTO roles(roleId, roleName)
VALUES (null, 'User');
COMMIT;

CREATE TABLE users_roles
(
    userId int(11) NOT NULL,
    roleId int(11) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users (userId) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (roleId) REFERENCES roles (roleId) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (userId, roleId)
);
COMMIT;

INSERT INTO users_roles(userId, roleId)
VALUES (1, 1);
INSERT INTO users_roles(userId, roleId)
VALUES (2, 2);
INSERT INTO users_roles(userId, roleId)
VALUES (3, 3);
INSERT INTO users_roles(userId, roleId)
VALUES (4, 1);
INSERT INTO users_roles(userId, roleId)
VALUES (4, 2);
INSERT INTO users_roles(userId, roleId)
VALUES (4, 3);
INSERT INTO users_roles(userId, roleId)
VALUES (4, 4);
COMMIT;

CREATE TABLE tasks
(
    taskId     int(11) NOT NULL AUTO_INCREMENT,
    taskName   varchar(15) NOT NULL,
    taskStatus varchar(15) NOT NULL,
    PRIMARY KEY (taskId)
);
COMMIT;

INSERT INTO tasks(taskId, taskName, taskStatus)
VALUES (null, 'Task1', 'Ongoing');
INSERT INTO tasks(taskId, taskName, taskStatus)
VALUES (null, 'Task2', 'Done');
INSERT INTO tasks(taskId, taskName, taskStatus)
VALUES (null, 'Task3', 'Skipped');
INSERT INTO tasks(taskId, taskName, taskStatus)
VALUES (null, 'Task4', 'Cancelled');
COMMIT;

CREATE TABLE users_tasks
(
    userId int(11) NOT NULL,
    taskId int(11) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users (userId) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (taskId) REFERENCES tasks (taskId) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (userId, taskId)
);
COMMIT;

INSERT INTO users_tasks(userId, taskId)
VALUES (4, 1);
INSERT INTO users_tasks(userId, taskId)
VALUES (3, 2);
INSERT INTO users_tasks(userId, taskId)
VALUES (2, 3);
INSERT INTO users_tasks(userId, taskId)
VALUES (4, 4);
COMMIT;