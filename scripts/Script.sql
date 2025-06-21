CREATE USER IF NOT EXISTS 'dbuser'@'%';

GRANT ALL PRIVILEGES ON sb_db.* TO 'dbuser'@'%' ;

ALTER USER 'dbuser'@'%' identified by 'password';

drop table boa_statement_summary;

drop table boa_transactions;

CREATE TABLE boa_statement_summary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100),
    summary_amount DECIMAL(15, 2)
);

CREATE TABLE boa_transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    txn_date DATE,
    description TEXT,
    amount DECIMAL(15, 2),
    running_balance DECIMAL(15, 2)
);

CREATE TABLE paypal_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    time TIME,
    time_zone VARCHAR(10),
    name VARCHAR(255),
    type VARCHAR(100),
    status VARCHAR(50),
    currency VARCHAR(10),
    gross DECIMAL(15,2),
    fee DECIMAL(15,2),
    net DECIMAL(15,2),
    from_email VARCHAR(255),
    to_email VARCHAR(255),
    transaction_id VARCHAR(100),
    shipping_address TEXT,
    address_status VARCHAR(50),
    item_title TEXT,
    item_id VARCHAR(100),
    shipping_and_handling_amount DECIMAL(15,2),
    insurance_amount DECIMAL(15,2),
    sales_tax DECIMAL(15,2),
    option1_name VARCHAR(100),
    option1_value VARCHAR(255),
    option2_name VARCHAR(100),
    option2_value VARCHAR(255),
    reference_txn_id VARCHAR(100),
    invoice_number VARCHAR(100),
    custom_number VARCHAR(100),
    quantity VARCHAR(50),
    receipt_id VARCHAR(100),
    balance DECIMAL(15,2),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    phone VARCHAR(50),
    subject TEXT,
    note TEXT,
    country_code VARCHAR(10),
    balance_impact VARCHAR(20)
);

CREATE TABLE SC_USER
(
    ID            INTEGER PRIMARY KEY AUTO_INCREMENT,
    USER_NAME     VARCHAR(1000),
    USER_PASSWORD VARCHAR(1000)
);

CREATE TABLE SC_ROLE
(
    ID        INTEGER PRIMARY KEY AUTO_INCREMENT,
    ROLE_NAME VARCHAR(1000)
);

CREATE TABLE SC_USER_ROLE
(
    ID         INTEGER PRIMARY KEY AUTO_INCREMENT,
    SC_USER_ID INTEGER,
    SC_ROLE_ID INTEGER,
    CONSTRAINT FK_SC_USER_SC_USER_ROLE FOREIGN KEY (SC_USER_ID) REFERENCES SC_USER (ID),
    CONSTRAINT FK_SC_ROLE_SC_USER_ROLE FOREIGN KEY (SC_ROLE_ID) REFERENCES SC_ROLE (ID)
);

-- USERS
INSERT INTO SC_USER(ID, USER_NAME, USER_PASSWORD)
VALUES (100, 'sheraz', '{noop}password');

INSERT INTO SC_USER(ID, USER_NAME, USER_PASSWORD)
VALUES (110, 'tariq', '{noop}password');

INSERT INTO SC_USER(ID, USER_NAME, USER_PASSWORD)
VALUES (120, 'chaudhry', '{noop}password');

-- ROLES & AUTHORITIES
INSERT INTO SC_ROLE(ID, ROLE_NAME)
VALUES (1000, 'ROLE_USER');

INSERT INTO SC_ROLE(ID, ROLE_NAME)
VALUES (1010, 'ROLE_ADMIN');

INSERT INTO SC_ROLE(ID, ROLE_NAME)
VALUES (1020, 'READ');

INSERT INTO SC_ROLE(ID, ROLE_NAME)
VALUES (1030, 'WRITE');

-- USER and ROLES & AUTHORITIES mapping
-- sheraz - ROLE_ADMIN ROLE_USER READ WRITE
INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2010, 100, 1000);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2020, 100, 1010);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2030, 100, 1020);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2040, 100, 1030);

-- tariq ROLE_USER READ WRITE
INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2050, 110, 1000);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2060, 110, 1020);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2070, 110, 1030);

-- chaudhry - ROLE_USER READ
INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2080, 120, 1000);

INSERT INTO SC_USER_ROLE(ID, SC_USER_ID, SC_ROLE_ID)
VALUES (2090, 120, 1020);

    select
        su1_0.id,
        su1_0.user_name,
        su1_0.user_password 
    from
        sc_user su1_0 ;

select *
from paypal_transaction pt1_0 where 
-- pt1_0.name<>null 
-- and 
trim(BOTH from pt1_0.name)<>'' 
and 
pt1_0.gross>0;


select
        bt1_0.id,
        bt1_0.amount,
        bt1_0.description,
        bt1_0.running_balance,
        bt1_0.txn_date 
    from
        boa_transaction bt1_0 
    where
        bt1_0.amount>0;


select * from boa_transaction
where upper(description) like '%NAJMUL KHAN%'
;


select * from boa_transaction
where upper(description) like '% CONF#%'
;

select * from boa_transaction
where lower(description) like 'zelle%'
and amount > 0

