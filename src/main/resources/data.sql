INSERT INTO ROLES (name) VALUES('ROLE_ADMIN');

INSERT INTO ROLES (name) VALUES('ROLE_PASSENGER');

INSERT INTO ROLES (name) VALUES('ROLE_DRIVER');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'pera.peric@email.com', 'Bulevar Oslobodjenja 74', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'PASSENGER');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Marko', 'Markovic', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'marko.markovic@email.com', 'Bulevar Oslobodjenja 75', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'ADMIN');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Darko', 'Darkovic', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'darko.darkovic@email.com', 'Bulevar Oslobodjenja 76', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'PASSENGER');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Petar', 'Petrovic', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'petar.petrovic@email.com', 'Bulevar Oslobodjenja 77', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'PASSENGER');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Perica', 'Petkovic', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'parica.petkovic@email.com', 'Bulevar Oslobodjenja 78', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'PASSENGER');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/June_odd-eyed-cat.jpg', '+381123123', 'perislav.peric@email.com', 'Bulevar Oslobodjenja 74', '$2a$12$T1i/9on6Eq.PW6FlDo1HUOqV9GNmJ1Sp24LbE0J5OrLg.f06BcapG', false, true, 'DRIVER');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 2);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3);

INSERT INTO USER_ACTIVATIONS (date_created, life_span, user_id)
VALUES ('2022-12-05 23:35:33.172279', TIMESTAMP '2022-09-03 12:47:00.000000' - TIMESTAMP '2022-09-03 13:13:00.000000', '1');

INSERT INTO Vehicles(are_babies_allowed,are_pets_allowed,licence_plate_number,model,number_of_seats,vehicle_type,driver_id)
VALUES ('0','0','SM074HZ','Skoda Octavia','5','1','6');

UPDATE USERS SET vehicle_id=1 WHERE ID=6;

INSERT INTO VEHICLE_TYPE_PRICES (type,price_per_km, image_path)
VALUES ('VAN','4.49', 'vehicle_type/car_model.png');
INSERT INTO VEHICLE_TYPE_PRICES (type,price_per_km, image_path)
VALUES ('LUX','6.99', 'vehicle_type/car_model.png');
INSERT INTO VEHICLE_TYPE_PRICES (type,price_per_km, image_path)
VALUES ('STANDARD','2.49', 'vehicle_type/car_model.png');

INSERT INTO LOCATIONS (address, latitude, longitude) VALUES ('Djure Danicica 82', '44.975980', '19.583750');
UPDATE vehicles SET location_id=1;

INSERT INTO LOCATIONS (address, latitude, longitude) VALUES ('Matije Hudji 50', '44.997770', '19.573220');

INSERT INTO LOCATIONS (address, latitude, longitude) VALUES ('Radnicka 54', '44.979348', '19.582567');

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-05 23:35:33.172279','10','0','1','2022-12-05 23:35:33.172279','ACTIVE','100.0','6',null,null);

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-06 23:35:33.172279','15','0','1','2022-12-06 23:35:33.172279','ACTIVE','13.0','6',null,null);

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-07 23:35:33.172279','15','0','1','2022-12-07 23:35:33.172279','ACTIVE','90.0','6',null,null);

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-08 23:35:33.172279','15','0','1','2022-12-08 23:35:33.172279','ACTIVE','50.0','6',null,null);

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-09 23:35:33.172279','15','0','1','2022-12-09 23:35:33.172279','ACTIVE','10.0','6',null,null);

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-10 23:35:33.172279','15','0','1','2022-12-10 23:35:33.172279','ACTIVE','150.0','6',null,null);

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('1', '2', '1');

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('1', '3', '2');

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('2', '3', '3');

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('3', '1', '4');

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('1', '3', '5');

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('2', '1', '6');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','1');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('3','1');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','2');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','3');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','4');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','5');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','6');


INSERT INTO PANICS (reason, time_pressed, ride_id, user_id) VALUES ('testtest', '2022-12-05 23:35:33.172279', '1', '1');

INSERT INTO MESSAGES (message_content, message_type, sending_time, receiver_id, ride_id, sender_id) VALUES ('testtest', 'RIDE', '2022-12-05 23:35:33.172279', '1', '1', '6');

INSERT INTO NOTES (date, message, user_id) VALUES ('2022-12-05 23:35:33.172279', 'testtest', 1);

INSERT INTO DOCUMENTS (image, name, driver_id) VALUES ('saobracajna_dozovola_img', 'saobracajna dozvola', 2);

INSERT INTO work_times (end_time, start_time, driver_id) VALUES ('2022-12-05 23:35:33.172279', '2022-12-05 23:35:33.172279', '2');

INSERT INTO RIDE_REVIEWS(comment,rating,type,passenger,ride) VALUES ('Vozilo je u losem stanju, popravite ga.', 3, 'DRIVER',1,1);
INSERT INTO RIDE_REVIEWS(comment,rating,type,passenger,ride) VALUES ('Sve super, svaka cast za cistocu vozila.', 5, 'VEHICLE',1,1);
INSERT INTO RIDE_REVIEWS(comment,rating,type,passenger,ride) VALUES ('Vozac je veoma korektan.', 4, 'DRIVER',3,1);
INSERT INTO RIDE_REVIEWS(comment,rating,type,passenger,ride) VALUES ('Vozilo se cuje kao traktor, neprijatni zvukovi. Nije mi dobro.', 4, 'VEHICLE',3,1);