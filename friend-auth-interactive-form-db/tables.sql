DROP TABLE fdb_user;
DROP TABLE fdb_user_role;

CREATE TABLE fdb_user (
    user_id serial PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    pin TEXT NOT NULL,
    unique(username)
);

CREATE TABLE fdb_user_role (
    user_role_id serial PRIMARY KEY,
    username TEXT NOT NULL REFERENCES fdb_user(username),
    rolename TEXT NOT NULL,
    unique(username, rolename)
);