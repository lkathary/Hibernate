
create table course (
    id bigserial primary key,
    name varchar(64)
);

insert into course (name) values
    ('One'),
    ('Two'),
    ('Three'),
    ('Four');

create table students (
    id bigserial primary key,
    firstname varchar(64) not null,
    lastname varchar(64) not null,
    age int,
    course_id bigint
);

insert into students (firstname, lastname, age, course_id) values
    ('Ivanov', 'Alex', 23, 4),
    ('Lee', 'Woo', 20, 2),
    ('Smith', 'Tom', 26, 4),
    ('Petrov', 'Maksim', 21, 3);

create table progress (
    id bigserial,
    student_id bigint references students(id),
    grade int not null
);

insert into progress (student_id, grade) values
    (1, 4),
    (2, 5),
    (3, 6),
    (4, 7);

create table trainer (
    id bigserial primary key,
    name varchar(64) not null unique
);

insert into trainer (name) values
    ('Muhin'),
    ('Zorin'),
    ('Kapitsa');

create table trainer_course (
    id bigserial primary key,
    trainer_id bigint references trainer(id),
    course_id bigint references course(id),
    duration int
);

insert into trainer_course (trainer_id, course_id, duration) values
    (1, 1, 20),
    (1, 2, 30),
    (1, 3, 33),
    (2, 1, 10),
    (2, 4, 10),
    (3, 1, 20),
    (3, 2, 50),
    (3, 4, 50);


drop table if exists course cascade;
drop table if exists students cascade;
drop table if exists progress cascade;
drop table if exists trainer cascade;
drop table if exists trainer_course cascade;

select firstname, lastname, age, name as course, grade
from students
inner join course c on students.course_id = c.id
inner join progress p on students.id = p.student_id;

select t.name as traner, c.name as course, duration
from trainer_course
inner join trainer t on t.id = trainer_course.trainer_id
inner join course c on c.id = trainer_course.course_id