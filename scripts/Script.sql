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