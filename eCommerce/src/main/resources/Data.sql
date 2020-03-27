/* ---------------------- CREATION OF TABLES -------------------------------------- */

/* CUSTOMERS */
create table customers(id bigint auto_increment not null,name varchar(256) not null, email_id varchar(256) unique not null, address varchar(256), created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256), modified_on timestamp, primary key(id));

/* CATEGORIES */
create table categories(id bigint auto_increment not null,name varchar(256) not null, category_code varchar(256) unique not null, created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256), modified_on timestamp, primary key(id));

/* PRODUCTS */
create table products(id bigint auto_increment not null,name varchar(256) not null, product_code varchar(256) unique not null, category_id bigint not null, description varchar(256), created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256),
 modified_on timestamp, foreign key (category_id) references categories(id), primary key(id));

/* VENDORS */
create table vendors(id bigint auto_increment not null, name varchar(256) not null, vendor_code varchar(256) unique not null, location varchar(256), address varchar(256), created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256), modified_on timestamp, primary key(id));

/* PRODUCTS_RATINGS */
create table products_ratings(id bigint auto_increment not null, rating tinyint not null, comment varchar(256), product_id bigint not null, customer_id bigint not null, created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256),
 modified_on timestamp, foreign key (product_id) references products(id), foreign key (customer_id) references customers(id), primary key(id));

/* VENDORS_RATINGS */
create table vendors_ratings(id bigint auto_increment not null, rating tinyint not null, comment varchar(256), vendor_id bigint not null, customer_id bigint not null, created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256),
 modified_on timestamp, foreign key (vendor_id) references vendors(id), foreign key (customer_id) references customers(id), primary key(id));
 
/* INVENTORY */
create table inventory(id bigint auto_increment not null, product_id bigint not null, vendor_id bigint not null, price double not null, quantity bigint, created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256),
 modified_on timestamp, foreign key (product_id) references products(id), foreign key (vendor_id) references vendors(id), primary key(id));

/* ORDERS */
create table orders(id bigint auto_increment not null, customer_id bigint not null, inventory_id bigint not null, total_amount double not null, status tinyint not null, created_by varchar(256) not null, created_on timestamp not null, modified_by varchar(256),
 modified_on timestamp, foreign key (customer_id) references customers(id), foreign key (inventory_id) references inventory(id), primary key(id));

 
 
 
 
 /* ---------------------- Insertion of Values -------------------------------------- */
 
 
insert into customers (id,name,email_id,address,created_by, created_on, modified_by, modified_on) values ('1','Gaja','a@a.com','EC','Gaja', CURRENT_TIMESTAMP(), null, null);
insert into customers (id,name,email_id,address,created_by, created_on, modified_by, modified_on) values ('2','Roop','b@a.com','RT Nagar','Roop', CURRENT_TIMESTAMP(), null, null);
insert into customers (id,name,email_id,address,created_by, created_on, modified_by, modified_on) values ('3','sheet','c@a.com','Silk Board','sheet',CURRENT_TIMESTAMP(), null, null);
insert into customers (id,name,email_id,address,created_by, created_on, modified_by, modified_on) values ('4','Sheta','d@a.com','Maratalli','Sheta',CURRENT_TIMESTAMP(), null, null);
insert into customers (id,name,email_id,address,created_by, created_on, modified_by, modified_on) values ('5','Swetaa','e@a.com','BTM','Swetaa', CURRENT_TIMESTAMP(), null, null);


insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('1','House','HOME', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('2','Electricals','EE', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('3','Mobile','MOB', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('4','Grocery','GCY', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('5','SPORTS','SP', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('6','MEN','M', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into categories (id,name,category_code,created_by, created_on, modified_by, modified_on) values ('7','WOMEN','F', 'Admin', CURRENT_TIMESTAMP(), null, null);


insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('1','Galaxy m30','SGM30', '3', 'Samsung Galaxy M30', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('2','Redmi Note 8 Pro','REDN8P', '3', 'Redmi Note 8 Pro', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('3','Asirvad Wheat','ASHATA', '4', 'Ashirvadh Ata', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('4','Shakti Masala','SAKMAS', '4', 'Shakti Masala', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('5','Royal Oak Sofa','ROSOFA', '1', 'Royal Oak Sofa', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('6','Bajaj SOlar Bulb','BAJSB', '2', 'Bajaj SOlar Bulb', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('7','Rebook Shoe','RBK30', '5', null, 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into products (id,name,product_code, category_id, description, created_by, created_on, modified_by, modified_on) values ('8','Philips Trimmer','PHPTM30', '6', null, 'Admin', CURRENT_TIMESTAMP(), null, null);


insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('1','Annaa','SMSG', 'South Korea', '1-1 South Korea', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('2','Balaa','REDMI', 'Bengaluru', '1-20 Bengaluru', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('3','Cubu','RBK', 'FRANCE', 'France', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('4','Doo','SHK', 'Chennai', 'Tamil Nadu', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('5','Eyyy','RYLOAK', 'Bengaluru', 'Bengaluru', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('6','Fck','PHP', 'South Korea', 'Nagavara', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into vendors (id,name,vendor_code, location, address, created_by, created_on, modified_by, modified_on) values ('7','Guigui','BAJ', 'Mumbai', 'Mumbai', 'Admin', CURRENT_TIMESTAMP(), null, null);


insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('1','1','1', '13000', '7', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('2','2','1', '18000', '10', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('3','8','2', '3000', '19', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('4','8','1', '13000', '7', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('5','3','4', '18000', '10', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('6','5','5', '3000', '19', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('7','1','6', '13000', '7', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('8','2','7', '18000', '10', 'Admin', CURRENT_TIMESTAMP(), null, null);
insert into inventory (id, product_id, vendor_id, price, quantity, created_by, created_on, modified_by, modified_on) values ('9','4','7', '3000', '19', 'Admin', CURRENT_TIMESTAMP(), null, null);


insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('1','4', 'Good', '1', '1', 'Gaja', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('2','3', 'OK', '1', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('3','2', 'BAD', '2', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('4','3', 'OK', '4', '3', 'sheet', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('5','5', 'Good', '4', '4', 'Sheta', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('6','4', 'Good', '5', '5', 'Swetaa', CURRENT_TIMESTAMP(), null, null);
insert into products_ratings (id, rating, comment, product_id, customer_id, created_by, created_on, modified_by, modified_on) values ('7','4', 'Good', '3', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);


insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('1','4', 'Good', '1', '1', 'Gaja', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('2','3', 'OK', '1', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('3','2', 'BAD', '2', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('4','3', 'OK', '4', '3', 'sheet', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('5','5', 'Good', '6', '4', 'Sheta', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('6','4', 'Good', '5', '5', 'Swetaa', CURRENT_TIMESTAMP(), null, null);
insert into vendors_ratings (id, rating, comment, vendor_id, customer_id, created_by, created_on, modified_by, modified_on) values ('7','4', 'Good', '7', '2', 'Roop', CURRENT_TIMESTAMP(), null, null);