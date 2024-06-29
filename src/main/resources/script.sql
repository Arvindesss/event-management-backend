CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE APP_USER(
    id_user UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    UNIQUE(username)
);

CREATE TABLE CATEGORY(
    id_category UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE LOCATION(
    id_location UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    address VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    city VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE EVENT(
    id_event UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    start_date TIMESTAMPTZ NOT NULL,
    end_date TIMESTAMPTZ,
    id_category UUID NOT NULL,
    id_location UUID NOT NULL,
    organizer UUID NOT NULL,
    FOREIGN KEY(id_category) REFERENCES CATEGORY(id_category),
    FOREIGN KEY(id_location) REFERENCES LOCATION(id_location),
    FOREIGN KEY(organizer) REFERENCES APP_USER(id_user)
);

CREATE TABLE EVENT_PARTICIPATION(
    id_user UUID,
    id_event UUID,
    feedback TEXT,
    rating DOUBLE PRECISION,
    PRIMARY KEY(id_user, id_event),
    FOREIGN KEY(id_user) REFERENCES APP_USER(id_user),
    FOREIGN KEY(id_event) REFERENCES EVENT(id_event),
    CHECK (rating >= 0 AND rating <= 5)
);