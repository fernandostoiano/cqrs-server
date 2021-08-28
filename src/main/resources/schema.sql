CREATE TABLE if NOT EXISTS customer
(
    id                  integer      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name          varchar(30),
    last_name           varchar(30),
    full_name           varchar(50),
    document            varchar(20),
    created_date        timestamp,
    last_modified_date  timestamp
);

CREATE TABLE if NOT EXISTS orders
(
    id                  integer  NOT NULL PRIMARY KEY AUTO_INCREMENT,
    price               decimal,
    customer_id         integer NOT NULL,
    created_date        timestamp,
    last_modified_date  timestamp,
    CONSTRAINT FK_CUSTOMER_ORDER FOREIGN KEY (customer_id) references customer(id) ON DELETE CASCADE
);