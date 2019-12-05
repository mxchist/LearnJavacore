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

create table client_session (
    client_session_id integer primary key autoincrement
    , server_session_id integer references server_session(server_session_id)
    , creation_time text not null constraint DF_creation_time default (datetime('now', 'localtime'))
    , client_name text
);

select datetime('now', 'localtime');

insert into server_session default values;
select * from server_session