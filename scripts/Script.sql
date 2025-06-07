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

CREATE TABLE boa_transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    txn_date DATE,
    description TEXT,
    amount DECIMAL(15, 2),
    running_balance DECIMAL(15, 2)
);