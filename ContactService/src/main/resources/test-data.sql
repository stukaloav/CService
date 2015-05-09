insert into contact (id, first_name, last_name, birth_date) values (1, 'John', 'Smith', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (2, 'Bruce', 'Willis', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (3, 'Liam', 'Nissen', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (4, 'Morgan', 'Freeman', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (5, 'John', 'Travolta', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (6, 'Al', 'Pachino', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (7, 'Colin', 'Farel', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (8, 'Lesly', 'Nilsen', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (9, 'Will', 'Smith', '2015-04-01');
insert into contact (id, first_name, last_name, birth_date) values (10, 'Mick', 'Jugger', '2015-04-01');

insert into hobby (id, title, description) values (1, 'basketball', 'the best game in the world');
insert into hobby (id, title, description) values (2, 'football', 'one of the most popular game');
insert into hobby (id, title, description) values (3, 'baseball', 'the most popular game in USA');
insert into hobby (id, title, description) values (4, 'tennis', 'interesting game for egoistic nature persons');
insert into hobby (id, title, description) values (5, 'swimming', 'useful for health');


insert into place (id, title, longitude, latitude) values (1, 'Dnipropetrovsk', 48.464717, 35.04618299999993);
insert into place (id, title, longitude, latitude) values (2, 'Baltimore', 39.2902778, -76.6125);
insert into place (id, title, longitude, latitude) values (3, 'Rome', 41.9, 12.4833333);
insert into place (id, title, longitude, latitude) values (4, 'Prague', 50.0878114, 14.420459800000003);
insert into place (id, title, longitude, latitude) values (5, 'Toronto', 43.67023, -79.38676);

insert into message (id, message_date, sender_id, receiver_id,content) values (1, '1986-02-27', 1, 2, 'Hello! How are You?');
insert into message (id, message_date, sender_id, receiver_id,content) values (2, '1986-02-28', 2, 1, 'I am fine, thanks)');


insert into contact_places (contact_id, place_id) values (1, 1);
insert into contact_places (contact_id, place_id) values (1, 2);
insert into contact_places (contact_id, place_id) values (2, 2);
insert into contact_places (contact_id, place_id) values (3, 2);
insert into contact_places (contact_id, place_id) values (4, 2);


insert into contact_hobbies (contact_id, hobby_id) values (1, 1);
insert into contact_hobbies (contact_id, hobby_id) values (2, 2);
insert into contact_hobbies (contact_id, hobby_id) values (3, 3);
insert into contact_hobbies (contact_id, hobby_id) values (4, 4);
insert into contact_hobbies (contact_id, hobby_id) values (5, 5);
insert into contact_hobbies (contact_id, hobby_id) values (6, 1);
insert into contact_hobbies (contact_id, hobby_id) values (7, 2);
insert into contact_hobbies (contact_id, hobby_id) values (8, 3);
insert into contact_hobbies (contact_id, hobby_id) values (9, 4);
insert into contact_hobbies (contact_id, hobby_id) values (10, 5);


insert into friendship (first_contact_id, second_contact_id) values (1, 2);
insert into friendship (first_contact_id, second_contact_id) values (1, 3);
insert into friendship (first_contact_id, second_contact_id) values (1, 4);
insert into friendship (first_contact_id, second_contact_id) values (1, 5);
insert into friendship (first_contact_id, second_contact_id) values (1, 6);
insert into friendship (first_contact_id, second_contact_id) values (1, 7);
insert into friendship (first_contact_id, second_contact_id) values (1, 8);
insert into friendship (first_contact_id, second_contact_id) values (1, 9);
insert into friendship (first_contact_id, second_contact_id) values (1, 10);