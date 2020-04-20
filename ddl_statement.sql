CREATE TABLE user (
    user_id       INTEGER PRIMARY KEY AUTOINCREMENT,
    login    TEXT    UNIQUE,
    password INTEGER,
    nickname TEXT    UNIQUE
);

PRAGMA foreign_keys = on;

CREATE TABLE student (student_id integer primary key autoincrement
    , student_name varchar(100)
    , score tinyint);


create table server_session (
    server_session_id integer primary key autoincrement
    , creation_time text not null constraint DF_creation_time default (datetime('now'))
);

CREATE TABLE user_session (
    user_session_id   INTEGER,
    server_session_id INTEGER REFERENCES server_session (server_session_id),
    creation_time     TEXT    NOT NULL
                              CONSTRAINT DF_creation_time DEFAULT (datetime('now', 'localtime') ),
    nickname          TEXT,
    constraint PK_user_session PRIMARY KEY (user_session_id, server_session_id)
);

create table messages_broadcast (
    id integer primary key autoincrement
    , creation_time     TEXT    NOT NULL
                              CONSTRAINT DF_creation_time DEFAULT (datetime('now', 'localtime') )
    , server_session_id integer
    , message text
);

create table messages_personal (
    id integer primary key autoincrement
    , creation_time     TEXT    NOT NULL
                              CONSTRAINT DF_creation_time DEFAULT (datetime('now', 'localtime') )
    , sent_user_session_id integer
    , recieved_user_session_id integer
    , message text
)
