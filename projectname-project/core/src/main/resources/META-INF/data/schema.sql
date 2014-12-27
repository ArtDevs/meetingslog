CREATE TABLE IF NOT EXISTS ml_roles(
  id INTEGER PRIMARY KEY AUTO_INCREMENT/*IDENTITY*/,
  name VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR(500),
  permissions INTEGER
);

CREATE TABLE IF NOT EXISTS ml_users(
  id INTEGER PRIMARY KEY AUTO_INCREMENT/*IDENTITY*/,
  ref_role INTEGER NOT NULL REFERENCES ml_roles(id) ON DELETE RESTRICT,
  login VARCHAR(25) UNIQUE NOT NULL,
  password VARCHAR(25) NOT NULL,
  firstName VARCHAR(50),
  secondName VARCHAR(50),
  email VARCHAR(255),
  comment VARCHAR(500),
  tmLastLogin TIMESTAMP,
  tmRegistered TIMESTAMP
);

CREATE OR REPLACE VIEW ml_users_roles AS
  SELECT
    Users.id AS id,
    Users.login AS login,
    Users.password AS password,
    Users.firstName AS firstName,
    Users.secondName AS secondName,
    Users.email AS email,
    Users.comment AS comment,
    Users.tmLastLogin AS tmLastLogin,
    Users.tmRegistered AS tmRegistered,
    Roles.id AS role_id,
    Roles.name AS role_name,
    Roles.description AS role_description,
    Roles.permissions AS role_permissions
  FROM
    ml_users AS Users
    LEFT JOIN
    ml_roles AS Roles
      ON Users.ref_role=Roles.id;

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