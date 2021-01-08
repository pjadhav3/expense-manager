# query to create meta data table : catagory which holds expense category details
CREATE TABLE category(
category_id TINYINT PRIMARY KEY AUTO_INCREMENT,
category_name VARCHAR(255) UNIQUE NOT NULL,
details TEXT
)
;

# query to create users table which holds basic information of system users
CREATE TABLE users(
user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
birth_year YEAR,
gender ENUM('male','female','others','unknown') DEFAULT 'unknown',
email VARCHAR(255) UNIQUE NOT NULL,
pass VARCHAR(255) NOT NULL,
phone CHAR(10) UNIQUE NOT NULL,
zip SMALLINT NOT NULL,
is_active TINYINT(1) DEFAULT 1,
create_date DATETIME DEFAULT  NOW(),
last_update_date DATETIME DEFAULT NOW()
)
;

#drop table users;


# query to create expense table which hold information about user expenses
CREATE TABLE expense(
expense_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT NOT NULL,
category_id TINYINT NOT NULL,
vendor_name VARCHAR(255) NOT NULL,
amount DECIMAL(10,2) NOT NULL,
is_active TINYINT(1) DEFAULT 1,
expense_date DATETIME NOT NULL,
create_date DATETIME DEFAULT NOW(),
last_update_date DATETIME DEFAULT NOW()
)
;
