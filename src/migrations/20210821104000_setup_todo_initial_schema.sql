-- CREATE ROLE task_users PASSWORD 'task_passwords'
-- GRANT ALL ON SCHEMA tasks to tasks_user;
-- GRANT ALL ON ALL TABLES IN SCHEMA tasks TO tasks_user;

CREATE SCHEMA IF NOT EXISTS tasks;

CREATE TABLE IF NOT EXISTS tasks.task (
    id serial primary key,
    title text,
    detail text,
    due_date date,
    priority integer
);

-- TODO Lookup items, users, etc