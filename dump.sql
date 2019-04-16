create table hib_user(
  id SERIAL PRIMARY KEY,
  login text UNIQUE NOT NULL,
  photo_path text,
  password text not null,
  name text,
  surname text,
  email text UNIQUE not null,
  city text,
  gender text,
  dateOfBirth date
);

create table message (
    id serial primary key,
    sender_id int references hib_user(id),
    reciever_id int references hib_user(id),
    message text,
    "date" timestamp
  );

create table question (
    id serial primary key,
    sender_id int references hib_user(id),
    reciever_id int references hib_user(id),
    message text,
    "date" timestamp,
    answer text
);

create table hib_like (
  id serial primary key,
  user_id int references hib_user(id),
  question_id int references question(id)
);

create table subscriptions (
  id serial primary key,
  subscriptor_id int references hib_user(id),
  subscriber_id int references hib_user(id)
);

insert into hib_user(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('kama', 'qwerty', 'Kamila', 'Nigmetzyanova', 'kama@mail.ru', 'NCh', 'female', '09/04/1999');
insert into hib_user(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('lesya', 'qwerty', 'Olesya', 'Nasibullina', 'lesya@mail.ru', 'Kazan', 'female', '06/05/1999');
insert into hib_user(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('tim', 'qwerty', 'Timur', 'Badretdinov', 'tim@mail.ru', 'NCh', 'male', '02/04/1999');
insert into hib_user(login, password, name, surname, email, city, gender, dateOfBirth)
  values ('aina', 'qwerty', 'Aina', 'Ardashirova', 'aina@mail.ru', 'Ufa', 'female', '14/02/1999');

insert into message (sender_id, reciever_id, message, "date")
  values (1, 2, 'hello!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (2, 3, 'how are you!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (1, 4, 'Beijing is cool!', now());
insert into message (sender_id, reciever_id, message, "date")
  values (4, 3, 'I like China!', now());

insert into question (sender_id, reciever_id, message, "date", answer)
  values (1, 2, 'how are you?', now(), 'Fine, thanks');
insert into question (sender_id, reciever_id, message, "date")
  values (3, 4, 'do you like Beijing?', now());
insert into question (sender_id, reciever_id, message, "date", answer)
  values (4, 1, 'heeey', now(), 'hi');
insert into question (sender_id, reciever_id, message, "date")
  values (2, 3, 'blablabla', now());

insert into hib_like (user_id, question_id) values (1,2);
insert into hib_like (user_id, question_id) values (2,1);
insert into hib_like (user_id, question_id) values (3,1);
insert into hib_like (user_id, question_id) values (4,3);

insert into subscriptions (subscriptor_id, subscriber_id) values (1, 2);
insert into subscriptions (subscriptor_id, subscriber_id) values (2, 1);
insert into subscriptions (subscriptor_id, subscriber_id) values (1, 3);
insert into subscriptions (subscriptor_id, subscriber_id) values (1, 4);
insert into subscriptions (subscriptor_id, subscriber_id) values (4, 2);
