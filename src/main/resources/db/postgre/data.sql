DELETE FROM orders_items;
DELETE FROM customer_orders;
DELETE FROM items;
DELETE FROM users_roles;
DELETE FROM roles;
DELETE FROM tasks;
DELETE FROM users;

INSERT INTO users (id, logon_name, password, name, surname, address, phone, email, department, description) VALUES
  (100001, 'user1', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Сергей', 'Синицын', '05096 Lotheville Crossing', '+7 (152) 446-1962', 'tbridge0@howstuffworks.com', 'OFFICE', 'description'),
  (100002, 'user2', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Егор', 'Майоров', '09570 Mcguire Trail', '+234 (282) 175-0310', 'cundrell1@go.com', 'STORAGE', 'description'),
  (100003, 'user3', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Петров', '615 Elka Parkway', '+7 (744) 572-8930', 'wconeybeer2@cbc.ca', 'OFFICE', 'description'),
  (100004, 'user4', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Клыков', '020 Namekagon Circle', '+992 (137) 353-5351', 'sianitti3@domainmarket.com', 'CUSHIONED', 'description'),
  (100005, 'user5', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Павел', 'Соболев', '6 Carioca Street', '+86 (317) 469-2821', 'elohde6@booking.com', 'STORAGE', 'description'),
  (100006, 'user6', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Армен', 'Гугаров', '68 Logan Point', '+353 (757) 489-9946', 'asimmens4@freewebs.com', null, 'description');
/*insert into users (name, surname, address, phone, email) values
 ('Thatch', 'Bridge', '05096 Lotheville Crossing', '+7 (152) 446-1962', 'tbridge0@howstuffworks.com'),
 ('Carie', 'Undrell', '09570 Mcguire Trail', '+234 (282) 175-0310', 'cundrell1@go.com'),
 ('Worthy', 'Coneybeer', '615 Elka Parkway', '+7 (744) 572-8930', 'wconeybeer2@cbc.ca'),
 ('Spense', 'Ianitti', '020 Namekagon Circle', '+992 (137) 353-5351', 'sianitti3@domainmarket.com'),
 ('Amaleta', 'Simmens', '68 Logan Point', '+353 (757) 489-9946', 'asimmens4@freewebs.com'),
 ('Bale', 'Peskin', '95076 Magdeline Crossing', '+33 (234) 384-1287', 'bpeskin5@cnet.com'),
 ('Edmund', 'Lohde', '6 Carioca Street', '+86 (317) 469-2821', 'elohde6@booking.com'),
 ('Natividad', 'Benez', '741 Atwood Pass', '+54 (191) 886-2984', 'nbenez7@vimeo.com'),
 ('Paolina', 'Rontsch', '69927 Columbus Crossing', '+372 (707) 125-7820', 'prontsch8@last.fm'),
 ('Olenka', 'Chadwick', '100 Maple Street', '+420 (885) 578-5759', 'ochadwick9@who.int'),
 ('Ximenez', 'Macura', '18 Melrose Pass', '+63 (108) 540-5854', 'xmacuraa@blogger.com'),
 ('Morey', 'Valencia', '148 Susan Place', '+961 (174) 639-9524', 'mvalenciab@unicef.org'),
 ('Michale', 'Statter', '636 Mcbride Alley', '+62 (594) 112-3225', 'mstatterc@cornell.edu'),
 ('Sadella', 'Erbe', '517 Jana Avenue', '+7 (874) 486-1653', 'serbed@homestead.com'),
 ('Lorenzo', 'Wentworth', '00065 Valley Edge Crossing', '+86 (341) 563-1047', 'lwentworthe@parallels.com'),
 ('Melly', 'Rubel', '9 Orin Street', '+86 (498) 241-0156', 'mrubelf@cnn.com'),
 ('Pren', 'VanBrugh', '6 Fallview Circle', '+225 (112) 783-8176', 'pvanbrughg@so-net.ne.jp'),
 ('Nettie', 'Preto', '0643 Sunfield Way', '+81 (892) 411-5408', 'npretoh@miitbeian.gov.cn'),
 ('Willem', 'Scolli', '7146 Eggendart Crossing', '+63 (887) 460-8213', 'wscollii@google.de'),
 ('Galina', 'Pilgram', '63409 Garrison Terrace', '+62 (957) 815-7615', 'gpilgramj@examiner.com'),
 ('Xever', 'Bonnyson', '4430 Kings Drive', '+964 (650) 149-1792', 'xbonnysonk@xing.com'),
 ('Leighton', 'Drance', '519 Chive Circle', '+1 (466) 675-7707', 'ldrancel@odnoklassniki.ru'),
 ('Nola', 'Carrel', '85259 Clarendon Center', '+27 (925) 317-6947', 'ncarrelm@csmonitor.com'),
 ('Christian', 'Hayes', '5 School Drive', '+62 (767) 150-7230', 'chayesn@nature.com'),
 ('Shay', 'Moxsom', '55092 Red Cloud Lane', '+86 (436) 322-4093', 'smoxsomo@china.com.cn'),
 ('Rania', 'Semper', '49415 Helena Road', '+62 (319) 372-3882', 'rsemperp@jimdo.com'),
 ('Nicole', 'Monelli', '0999 Johnson Circle', '+62 (297) 617-4227', 'nmonelliq@instagram.com'),
 ('Bebe', 'Noot', '2 Crownhardt Center', '+46 (998) 578-3098', 'bnootr@cdc.gov'),
 ('Sarge', 'Devonport', '6266 Melody Avenue', '+62 (766) 716-6432', 'sdevonports@yandex.ru'),
 ('Eugenie', 'Bussel', '61243 Dixon Parkway', '+63 (732) 320-6001', 'ebusselt@ask.com');*/


INSERT INTO items (id, name, description, image_url, cost) VALUES
  (100007, 'Книжний шкаф', 'Книжный шкаф из красного дерева. Имееет выдвижные ящички по бокам и 3 полки', '/link/sfasfa/fasdf.jpg', 2343.12),
  (100008, 'Журнальный столик черный', 'Журнальный столик с добавлением пластиковых вставок и кожанной отделкой', '/link/sfasfa/fasdf.jpg', 1233.86),
  (100009, 'Стул', 'Стул удобный, с регулируемой спинкой и высотой посадки', '/link/sfasfa/fasdf.jpg', 856.32);

INSERT INTO customer_orders (id, name, due_date, status, description, user_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO orders_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100009);

INSERT INTO tasks (id, name, due_date, status, priority, user_id, department, description) VALUES
  (100011, 'Книжный шкаф задание', '2018-12-19 08:17:00', 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание шкафа'),
  (100012, 'Стул офисный', '2018-12-19 08:17:00', 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стула');

INSERT INTO roles (id, name) VALUES
  (100013, 'ROLE_USER'),
  (100014, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
  (100001, 100013),
  (100001, 100014),
  (100002, 100013),
  (100003, 100013),
  (100004, 100013),
  (100005, 100013),
  (100006, 100013);

