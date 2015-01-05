CREATE TABLE IF NOT EXISTS ml_roles(
  id INTEGER PRIMARY KEY AUTO_INCREMENT/*IDENTITY*/,
  name VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR(500),
  permissions INTEGER
);

CREATE TABLE IF NOT EXISTS ml_users(
  id INTEGER PRIMARY KEY AUTO_INCREMENT/*IDENTITY*/,
  login VARCHAR(25) UNIQUE NOT NULL,
  password VARCHAR(25) NOT NULL,
  firstName VARCHAR(50),
  secondName VARCHAR(50),
  email VARCHAR(255),
  comment VARCHAR(500),
  tmLastLogin TIMESTAMP,
  tmRegistered TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ml_user_role_ref (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id INTEGER NOT NULL REFERENCES ml_users(id),
  role_id INTEGER NOT NULL REFERENCES ml_roles(id),
  CONSTRAINT UNIQUE KEY ml_user_role_ref_key(user_id,role_id)
);

CREATE OR REPLACE VIEW ml_user_role_ref_vw AS
  SELECT
    User.id AS id,
    User.login AS login,
    User.password AS password,
    User.firstName AS firstName,
    User.secondName AS secondName,
    User.email AS email,
    User.comment AS comment,
    User.tmLastLogin AS tmLastLogin,
    User.tmRegistered AS tmRegistered,
    Refs.role_id AS role_id
  FROM
    ml_users AS User
    LEFT JOIN
    ml_user_role_ref AS Refs
      ON User.id=Refs.user_id;

CREATE OR REPLACE VIEW ml_users_roles_vw AS
  SELECT
    UserRoleRef.id AS user_id,
    UserRoleRef.login AS login,
    UserRoleRef.password AS password,
    UserRoleRef.firstName AS firstName,
    UserRoleRef.secondName AS secondName,
    UserRoleRef.email AS email,
    UserRoleRef.comment AS comment,
    UserRoleRef.tmLastLogin AS tmLastLogin,
    UserRoleRef.tmRegistered AS tmRegistered,
    UserRoleRef.role_id AS role_id,
    Role.name AS role_name,
    Role.description AS role_description,
    Role.permissions AS permissions

  FROM
    ml_user_role_ref_vw AS UserRoleRef
    LEFT JOIN
      ml_roles AS Role
    ON UserRoleRef.role_id=Role.id;

INSERT INTO ml_roles
  (name,description,permissions)
VALUES
  ('guest','Guest user role with standard permissions (predefined).',0x55)
ON DUPLICATE KEY UPDATE permissions=permissions;

INSERT INTO ml_roles
  (name,description,permissions)
VALUES
  ("admin","Administrative role with super user permissions (predefined).",0xFF)
ON DUPLICATE KEY UPDATE permissions=permissions;

INSERT INTO ml_users
(login, password, firstName, secondName, email, comment)
    VALUES
("admin","admin","","","","Default administartive account.")
ON DUPLICATE KEY UPDATE password=password;

INSERT INTO ml_user_role_ref
(user_id, role_id)
VALUES ((SELECT id FROM ml_users WHERE login LIKE "admin" LIMIT 1),
        (SELECT id FROM ml_roles WHERE name like "admin" LIMIT 1))
ON DUPLICATE KEY UPDATE role_id=role_id;