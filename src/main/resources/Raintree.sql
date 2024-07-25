CREATE DATABASE raintree
USE raintree


CREATE TABLE patient(_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
pn VARCHAR(11) DEFAULT NULL,
first VARCHAR(15) DEFAULT NULL,
last VARCHAR(25) DEFAULT NULL,
dob DATE DEFAULT NULL,
PRIMARY KEY (_id));

 

CREATE TABLE insurance (_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
patient_id INT(10) UNSIGNED NOT NULL,
iname VARCHAR(40) DEFAULT NULL,
from_date DATE DEFAULT NULL,
to_date DATE DEFAULT NULL,
PRIMARY KEY (_id),
FOREIGN KEY(patient_id) REFERENCES patient(_id));


INSERT INTO patient(pn, first ,last, dob)
VALUES ('000000001','John', 'Smith', '1987-03-12'),
       ('000000002','Carolina ', 'Beasley', '1991-05-17'),
       ('000000003','Jorge', 'Beasley', '1996-11-03'),
       ('000000004','Bishop ', 'Farmer', '1968-04-19'),
       ('000000005','Scarlet  ', 'Henry', '1968-04-19');
 

INSERT INTO insurance(patient_id, iname, from_date, to_date)
VALUES	('1', 'IF', '2023-05-21', '2024-05-21'),
	    ('1', 'IF', '2024-05-21', '2025-05-21'),
        ('2', 'ERGO', '2021-05-21', '2022-05-21'),
        ('2', 'ERGO', '2022-05-21', '2023-05-21'),
        ('3', 'ERGO', '2019-07-16', '2020-07-16'),
        ('3', 'IF', '2024-07-16', '2025-01-16'),
        ('4', 'PZU', '2022-07-16', '2023-04-16'),
        ('4', 'SEB', '2023-04-16', '2024-04-16'),
        ('5', 'LHV', '2024-01-23', '2025-01-23'),
        ('5', 'LHV', '2022-05-02', '2023-05-02');

