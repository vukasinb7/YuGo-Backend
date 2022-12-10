INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'pera.peric@email.com', 'Bulevar Oslobodjenja 74', 'Pasword123', false, true, '1');

INSERT INTO USER_ACTIVATIONS (date_created, life_span, user_id)
VALUES ('2022-12-05 23:35:33.172279', TIMESTAMP '2022-09-03 12:47:00.000000' - TIMESTAMP '2022-09-03 13:13:00.000000', '1');

INSERT INTO USERS (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'perislav.peric@email.com', 'Bulevar Oslobodjenja 74', 'Pasword123', false, true, '2');

INSERT INTO Vehicles(are_babies_allowed,are_pets_allowed,licence_plate_number,model,number_of_seats,vehicle_type,driver_id)
VALUES ('0','0','SM074HZ','Skoda Octavia','5','1','2');

UPDATE USERS SET vehicle_id=1 WHERE ID=2;

INSERT INTO VEHICLE_TYPE_PRICES (type,price_per_km)
VALUES ('VAN','20');

INSERT INTO LOCATIONS (address, latitude, longitude) VALUES ('Bulevar Oslobodjenja 213', '23.0', '42.31424');
UPDATE vehicles SET location_id=1;

INSERT INTO LOCATIONS (address, latitude, longitude) VALUES ('Bulevar Oslobodjenja 123', '122.0', '34.31424');

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-05 23:35:33.172279','10','0','1','2022-12-05 23:35:33.172279','ACTIVE','100.0','2',null,null);

INSERT INTO PATHS (starting_point, destination, path_id) VALUES ('1', '2', '1');

INSERT INTO PASSENGER_RIDES (passenger_id,ride_id)
VALUES ('1','1');

INSERT INTO PANICS (reason, time_pressed, ride_id, user_id) VALUES ('testtest', '2022-12-05 23:35:33.172279', '1', '1');

INSERT INTO MESSAGES (message_content, message_type, sending_time, receiver_id, ride_id, sender_id) VALUES ('testtest', 'RIDE', '2022-12-05 23:35:33.172279', '1', '1', '2');

INSERT INTO NOTES (date, message, user_id) VALUES ('2022-12-05 23:35:33.172279', 'testtest', 1);

INSERT INTO DOCUMENTS (image, name, driver_id) VALUES ('saobracajna_dozovola_img', 'saobracajna dozvola', 2);

INSERT INTO work_times (end_time, start_time, driver_id) VALUES ('2022-12-05 23:35:33.172279', '2022-12-05 23:35:33.172279', '2');