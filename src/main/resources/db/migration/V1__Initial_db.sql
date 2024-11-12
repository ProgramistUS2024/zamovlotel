CREATE TABLE IF NOT EXISTS Client
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    email VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS Room
(
    id BIGSERIAL PRIMARY KEY,
    room_number VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    room_class VARCHAR(50) NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Administrator
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(255),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Request (
   id BIGSERIAL PRIMARY KEY,
   client_id BIGINT NOT NULL,
   room_capacity INT NOT NULL,
   room_class VARCHAR(50) NOT NULL,
   check_in_date DATE NOT NULL,
   check_out_date DATE NOT NULL,
   status VARCHAR(50) NOT NULL,
   FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Invoice (
    id BIGSERIAL PRIMARY KEY,
    request_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (request_id) REFERENCES Request(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Room(id) ON DELETE SET NULL
);



