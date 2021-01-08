# query to get all available categories
SELECT category_id,category_name,details FROM category;

# query to insert a new category in category table
INSERT INTO category(category_name) VALUES ('medicine');


# query to fetch all users details 
SELECT user_id,first_name,last_name,birth_year,gender,email,phone,zip,is_active,create_date,last_update_date FROM users;

# query to register a new user into the system
INSERT INTO users(first_name,last_name,birth_year,email,pass,phone,zip) 
VALUES ('p','j',1993,'pj@test.dev','qwerty','7049051666','28262');


#view all expenses 
SELECT expense_id,user_id,category_id,vendor_name,amount,is_active,create_date,update_date 
FROM expense;

# add new expense 
INSERT INTO expense(user_id,category_id,vendor_name,amount) 
VALUES (1,1,'Persis',15);

INSERT INTO expense(user_id,category_id,vendor_name,amount) 
VALUES (1,3,'Walgreens',25);

#view all expenses of particular user - way 1 
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
JOIN users u ON u.user_id = e.user_id
WHERE u.email = 'pj@test.dev'
;
#view all expenses of particular user - way 2
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
WHERE e.user_id = (SELECT u.user_id 
			FROM users u
			WHERE u.email ='pj@test.dev')
;
#view all expenses of particular user - way 3
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
WHERE e.user_id = 1
;

#view all expenses of particular category - way 1
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
WHERE e.category_id = 3
;

#view all active expenses
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
WHERE is_active = TRUE
;

#view all deleted expenses
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
WHERE is_active = FALSE
;

# get category id
SELECT category_id 
FROM category
WHERE category_name = 'medicine'
;

# get user id
SELECT user_id
FROM users
WHERE email = 'pj@test.dev'
;

# usecase 1 : create and save expense
INSERT INTO expense(user_id,category_id,vendor_name,amount) 
VALUES (1,3,'Walgreens',10);

SELECT * 
FROM expense;

# usecase 2 : edit expense : edit vendor
UPDATE expense
SET vendor_name = 'CVS'
WHERE expense_id = 1 
;

# usecase 2 : edit expense : edit amount
UPDATE expense
SET amount = 50
WHERE expense_id = 1 
;

# usecase 2 : edit expense : edit expense
UPDATE expense
SET category_id = 3,
vendor_name = 'CVS',
amount = 15
WHERE expense_id = 1 
;

# usecase 3 : delete expense soft delete
UPDATE expense
SET is_active = FALSE
WHERE expense_id = 1 
;

# usecase 4 : fetch monthly summary by category
SELECT e.expense_id,e.user_id,e.category_id,e.vendor_name,e.amount,e.is_active,e.create_date,e.update_date 
FROM expense e 
GROUP BY MONTH(e.create_date)
;


DESC users;