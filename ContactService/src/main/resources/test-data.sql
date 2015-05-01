insert into contact (id, first_name, last_name, birth_date) values (1, 'John', 'Smith', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (2, 'Mark', 'Loren', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (3, 'Liam', 'Nissen', '2015-04-01');

insert into hobby (id, title, description) values (1, 'basketball', 'the best game in the world');

insert into place (id, title, longitude, latitude) values (1, 'Dnipropetrovsk', 0.23, 0.3);

insert into place (id, title, longitude, latitude) values (2, 'New York', 0.23, 0.3);


insert into message (id, message_date, sender_id, receiver_id,content) values (1, '1986-02-28', 1, 2, 'abc');

insert into contact_places (contact_id, place_id) values (1, 1);

insert into contact_places (contact_id, place_id) values (1, 2);

insert into contact_places (contact_id, place_id) values (2, 2);

insert into contact_hobbies (contact_id, hobby_id) values (1, 1);