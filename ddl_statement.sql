CREATE TABLE user (
    user_id       INTEGER PRIMARY KEY AUTOINCREMENT,
    login    TEXT    UNIQUE,
    password INTEGER,
    nickname TEXT    UNIQUE
);

CREATE TABLE student (student_id integer primary key autoincrement
    , student_name varchar(100)
    , score tinyint);


create table server_session (
    server_session_id integer primary key autoincrement
    , creation_time text not null constraint DF_creation_time default (datetime('now'))
);

create table user_session (
    user_session_id integer primary key autoincrement
    , server_session_id integer references server_session(server_session_id)
    , creation_time text not null constraint DF_creation_time default (datetime('now', 'localtime'))
    , nickname text
);

PRAGMA foreign_keys = on;
