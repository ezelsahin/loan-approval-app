-- Table Schemas
DROP TABLE IF EXISTS applicant;
DROP TABLE IF EXISTS request_result;

CREATE TABLE applicant
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    id_number VARCHAR(11) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    monthly_income INT NOT NULL,
    phone_number VARCHAR(10) NOT NULL
);

CREATE TABLE request_result
(
    id_number VARCHAR(11) PRIMARY KEY,
    request_status VARCHAR(15),
    loan_limit INT,
    rating INT
);

INSERT INTO applicant(id_number, first_name, last_name, monthly_income, phone_number)
VALUES ('12345678900', 'Ahmet', 'Yılmaz', 3500, '5351234560'),
       ('12345678901', 'Mehmet', 'Şahin', 5800, '5351234561'),
       ('12345678902', 'Hasan', 'Ali', 3700, '5351234562'),
       ('12345678903', 'Merve', 'Solmaz', 11000, '5351234563'),
       ('12345678904', 'Cansu', 'Kara', 4700, '5351234564'),
       ('12345678905', 'Aylin', 'Budak', 5200, '5351234565'),
       ('12345678906', 'Mert', 'Aydın', 8400, '5351234566'),
       ('12345678907', 'Kader', 'Arslan', 4300, '5351234567'),
       ('12345678908', 'Tolga', 'Sönmez', 3100, '5351234568'),
       ('12345678909', 'Canan', 'Sezgin', 6200, '5351234569');


INSERT INTO request_result(id_number, request_status, loan_limit, rating)
VALUES ('12345678900', 'DENIED', 0, 488),
       ('12345678901', 'APPROVED', 20000, 822),
       ('12345678902', 'DENIED', 0, 435),
       ('12345678903', 'APPROVED', 44000, 1348),
       ('12345678904', 'APPROVED', 18800, 755),
       ('12345678905', 'DENIED', 0, 387),
       ('12345678906', 'APPROVED', 20000, 925),
       ('12345678907', 'APPROVED', 17200, 1034),
       ('12345678908', 'APPROVED', 10000, 660),
       ('12345678909', 'APPROVED', 24800, 1130);