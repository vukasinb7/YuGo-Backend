INSERT INTO Users (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'pera.peric@email.com', 'Bulevar Oslobodjenja 74', 'Pasword123', false, true, '1');

INSERT INTO Users (name, surname, profile_picture, telephone_number, email, address, password, is_blocked, is_active, user_type)
VALUES ('Pera', 'Perić', 'U3dhZ2dlciByb2Nrcw==', '+381123123', 'pera.peric@email.com', 'Bulevar Oslobodjenja 74', 'Pasword123', false, true, '2');

INSERT INTO  Vehicles(are_babies_allowed,are_pets_allowed,licence_plate_number,model,number_of_seats,vehicle_category,driver_id)
VALUES ('0','0','SM074HZ','Toyota Yaris','5','1','2');

UPDATE USERS SET vehicle_id=1 WHERE ID=2;

INSERT INTO VEHICLE_CATEGORY_PRICES (category,price_per_km)
VALUES ('VAN','20');

INSERT INTO RIDES (includes_babies,end_time,estimated_time,is_panic_pressed,includes_pets,start_time,status,price,driver_id,rejection_id,vehicle_type_id)
VALUES ('0','2022-12-05 23:35:33.172279','10','0','1','2022-12-05 23:35:33.172279','ACTIVE','100.0','2',null,null);

INSERT INTO USERS_RIDES (passenger_id,rides_id)
VALUES ('1','1');