DELETE FROM orders_items;
DELETE FROM tasks;
DELETE FROM customer_orders;
DELETE FROM item_images;
DELETE FROM items;
DELETE FROM users_roles;
DELETE FROM roles_permissions;
DELETE FROM roles;
DELETE FROM permissions;
DELETE FROM refresh_tokens;
DELETE FROM users;

INSERT INTO users (id, user_name, password, name, surname, address, phone, email, department, description) VALUES
  (100001, 'user1', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Сергей', 'Синицын', '05096 Lotheville Crossing', '+7 (152) 446-1962', 'tbridge0@howstuffworks.com', 'OFFICE', 'description'),
  (100002, 'user2', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Егор', 'Майоров', '09570 Mcguire Trail', '+234 (282) 175-0310', 'cundrell1@go.com', 'STORAGE', 'description'),
  (100003, 'user3', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Петров', '615 Elka Parkway', '+7 (744) 572-8930', 'wconeybeer2@cbc.ca', 'OFFICE', 'description'),
  (100004, 'user4', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Клыков', '020 Namekagon Circle', '+992 (137) 353-5351', 'sianitti3@domainmarket.com', 'CUSHIONED', 'description'),
  (100005, 'user5', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Павел', 'Соболев', '6 Carioca Street', '+86 (317) 469-2821', 'elohde6@booking.com', 'STORAGE', 'description'),
  (100006, 'user6', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Армен', 'Гугаров', '68 Logan Point', '+353 (757) 489-9946', 'asimmens4@freewebs.com', null, 'description');

INSERT INTO items (id, name, description, department, price) VALUES
  (100007, 'Стенка Версаль', 'Версаль СБ-1655 Стенка', 'STORAGE', 38490.90),
  (100008, 'Диван угловой', 'Диван угловой Атланта Классик со столом, 145, рогожка, дельфин', 'CUSHIONED', 26080),
  (100009, 'Комод Медея', 'Медея СБ-2454 Комод', 'STORAGE', 9490),
  (100017, 'Кухня Регина серия Mishel', 'Кухня Регина серия Mishel Беж песок/Ирландский ликер, Рустик серый 1,8-2,4 м (Мишель)', 'STORAGE', 110856.00),
  (100018, 'Шкаф-купе', 'Терра СБ-2545 Шкаф-купе 2-х дверный', 'STORAGE', 8590.00),
  (100019, 'Диван-кровать угловой', 'Диван-кровать угловой еврокнижка Модерн,велюр', 'CUSHIONED', 42990.99),
  (100020, 'Шкаф 2-х дверный', 'Мамбо СБ-2371 Шкаф 2-х дверный', 'STORAGE', 9890.00),
  (100021, 'Кухня Деми', 'Кухня Деми Белый / Дуб Сонома 1 м', 'STORAGE', 9990),
  (100022, 'Стол-тумба', 'Мамбо СБ-2886 Стол-тумба', 'OFFICE', 6890),
  (100023, 'Стенка Прадо', 'Стенка Прадо (Вегас) СБ-2224', 'OFFICE', 19790);

INSERT INTO customer_orders (id, name, due_date, status, description, user_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO orders_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100022);

INSERT INTO tasks (id, name, due_date, status, priority, user_id, department, description, item_id, order_id) VALUES
  (100011, 'Стенка', '2018-12-19 08:17:00', 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание стенки', 100007, 100010),
  (100012, 'Стол-тумба', '2018-12-19 08:17:00', 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стола-тумбы', 100022, 100010);

INSERT INTO roles (id, name) VALUES
  (100013, 'ROLE_USER'),
  (100014, 'ROLE_ADMIN');

INSERT INTO permissions (id, name) VALUES
  (100015, 'READ'),
  (100016, 'WRITE');

INSERT INTO roles_permissions (role_id, permission_id) VALUES
  (100013, 100015),
  (100014, 100015),
  (100014, 100016);

INSERT INTO users_roles (user_id, role_id) VALUES
  (100001, 100013),
  (100001, 100014),
  (100002, 100013),
  (100003, 100013),
  (100004, 100013),
  (100005, 100013),
  (100006, 100013);

INSERT INTO public.item_images (name, item_id)
VALUES ('4fc82d67-e19f-4a96-b267-73ff0afa5da7.00000149098detail.webp', 100007),
       ('b9baeefd-7d47-47a6-a3cf-a3538d341b21.versal_sb_1655_stenka_beliy_yasen_1.webp', 100007),
       ('b65a9a3f-18be-4393-b44f-6e68623af9c5.versal_sb_1655_stenka_beliy_yasen_2.webp', 100007),
       ('aafd31d3-7024-451f-9708-94a9b7b57242.versal_sb_1655_stenka_beliy_yasen_3.webp', 100007),
       ('58cdc3aa-164d-40ef-b429-54af72c2e0a2.versal_sb_1655_stenka_beliy_yasen_4.webp', 100007),
       ('445f4afd-9b0a-4fe5-9c97-3dc8be97cbed.divan_krovat_uglovoy_so_stolom_atlanta_klassik_145_rogogka_1.webp',
        100008),
       ('d951da09-fbc9-4967-92d6-f35ef4d7e3bc.divan_krovat_uglovoy_so_stolom_atlanta_klassik_145_rogogka_2.webp',
        100008),
       ('92e927da-e43c-4f54-90e6-b534104c6d9e.divan_krovat_uglovoy_so_stolom_atlanta_klassik_145_rogogka_3.webp',
        100008),
       ('4499090a-5e1b-451f-8b65-ed16efbb0a66.divan_krovat_uglovoy_so_stolom_atlanta_klassik_145_rogogka_4.webp',
        100008),
       ('966e3cdc-daf5-4d5a-a55d-bc5c123bc7b4.i0000257481-detail.webp', 100008),
       ('e7a567cb-a968-4c4b-86c3-773988e4e4e5.divan_krovat_uglovoy_modern_gray_seriy_5.webp', 100019),
       ('91dcc3bf-df1d-472c-a8de-5bb6b35faa4a.divan_krovat_uglovoy_modern_gray_seriy_6.webp', 100019),
       ('9d07b910-e317-4098-b9b7-fea0ad5b7646.divan_krovat_uglovoy_modern_gray_seriy_7.webp', 100019),
       ('eede15a7-5770-4d8e-954f-28aad76a5e85.divan_krovat_uglovoy_modern_gray_seriy_8.webp', 100019),
       ('ce3bb565-c63c-4631-b8bb-4fc12a891fc5.divan_krovat_uglovoy_modern_gray_seriy_9.webp', 100019),
       ('31fc4097-e34f-43ea-9f39-fbe0e62736ed.i0000236292_detail.webp', 100019),
       ('7e7551c4-1f18-4fdc-bb3a-c22990075901.00000268134-detail.webp', 100021),
       ('469eb8ac-19f1-48cb-91b5-adaff6f0c2c3.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_1.webp', 100021),
       ('f9b468fd-0744-46dc-952f-731ad5743390.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_2.webp', 100021),
       ('c340d11e-cfc1-461b-b4de-b3da3b9b5019.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_3.webp', 100021),
       ('90bfc694-04b2-45e0-b194-9572b50bfca2.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_4.webp', 100021),
       ('7ae253cf-53be-406a-8671-7f9565063200.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_5.webp', 100021),
       ('1ba47fff-ad6a-4db8-a4ab-5fd38eca4749.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_6.webp', 100021),
       ('a3fac35f-a663-4789-86cf-6a08bbd7177b.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_7.webp', 100021),
       ('fd7ad2f0-3f7d-4c18-a227-1f4444eb2eb5.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_8.webp', 100021),
       ('7a01ac30-786b-42bd-816d-64dddbfded56.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_9.webp', 100021),
       ('59a74228-1a57-4994-b409-7d2727572f1d.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_10.webp', 100021),
       ('7dae9ad5-8d50-4314-93c6-bafa99dad2c6.demin_kuhnya_beliy_dub_sonoma_metropolitan_r5_11.webp', 100021),
       ('db117920-c771-439c-ab4e-e50c54e15e13.00000277767-detail.webp', 100009),
       ('fe8b39e1-4ffb-40d6-ba5f-b9b3f6955fa6.medeya_sb_2454_1n_komod_beliy_beliy_glyanets_1.webp', 100009),
       ('336d3752-0cd2-4018-ae52-e52b0e136e57.medeya_sb_2454_1n_komod_beliy_beliy_glyanets_2.webp', 100009),
       ('0d4a9811-de55-43ec-98f5-7751f24965fe.medeya_sb_2454_1n_komod_beliy_beliy_glyanets_3.webp', 100009),
       ('3384ce31-9f07-410b-b6c5-73df84a06ff2.medeya_sb_2454_1n_komod_beliy_beliy_glyanets_4.webp', 100009),
       ('e89febc6-7aea-4197-b6ec-8fd53c364efc.00000291016-detail.webp', 100017),
       ('22f47535-3f36-4c6d-ad1e-e8759b8b69e2.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_3.webp',
        100017),
       ('8d69a35c-332b-4d5f-a0cb-37478e4e25eb.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_4.webp',
        100017),
       ('b654aceb-927f-4499-bc3a-dcac06dbb410.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_5.webp',
        100017),
       ('844c8966-c55c-42c1-bb33-7b2992370544.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_6.webp',
        100017),
       ('f21b4cba-c25b-4649-b5ed-9e7e04d8fa09.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_7.webp',
        100017),
       ('b84f89be-64f4-41b8-b0bb-be90a0566081.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_8.webp',
        100017),
       ('8a1f056c-7a27-4bdd-9ed4-b29414204a3c.mishel_kuhnya_beg_pesok_irlandskiy_liker_rustik_seriy_fasad_s_ruchkoy_9.webp',
        100017),
       ('5b97f5a1-13ec-408b-bebb-473a02db4159.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_1.webp', 100018),
       ('7dff068e-10b5-4270-8d9b-06a4930e9a63.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_2 (1).webp', 100018),
       ('f2699d7e-5008-4c44-a131-1602d85ca01f.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_2.webp', 100018),
       ('f05d8935-edf0-4b9a-9ba5-ed98f2714054.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_3.webp', 100018),
       ('c7b308dd-6d4c-49ea-9fb0-f9a7cc259148.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_7.webp', 100018),
       ('4292e7bd-9fe2-4f2d-b1e9-b4e198201cde.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_9.webp', 100018),
       ('439a92cf-e8b5-4b35-be22-f6cb9fffd8d9.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_00000252641_1.webp', 100018),
       ('771dc71c-da02-4cbc-ab83-86c20dcbc1ba.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_00000252641_2.webp', 100018),
       ('a5162275-9ecc-471a-b8c4-8691afe53395.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_00000252641_3.webp', 100018),
       ('25c00d44-5ac2-463f-aa58-810b6da5ff39.prado_sb_2224_stenka_dub_sonoma_beliy_1.webp', 100023),
       ('1a738620-ff31-4a89-b881-da1a25595a10.prado_sb_2224_stenka_dub_sonoma_beliy_2.webp', 100023),
       ('1dfa6cb0-b042-4adf-9a22-f6068cebf603.prado_sb_2224_stenka_dub_sonoma_beliy_3.webp', 100023),
       ('3f8ad8c9-8fcd-4b5e-bd27-fa519a966f8e.prado_sb_2224_stenka_dub_sonoma_beliy_4.webp', 100023),
       ('87eb8ae7-2556-4899-bcd5-3fafa46fb746.prado_sb_2224_stenka_dub_sonoma_beliy.webp', 100023),
       ('d08252d1-75df-4b12-90ac-4faf6ff193ed.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy_00000252641_4.webp', 100018),
       ('ddc49127-63f8-4f87-878a-6b6c96dac256.terra_sb_2545_shkaf_kupe_2_h_dverniy_beliy.webp', 100018),
       ('3623fccb-d9e9-4139-8ca2-c9689da98899.00000188026_detail.webp', 100020),
       ('a1bdb329-f817-49cd-abb9-e74594e122f6.mambo_sb_2371_shkaf_2_dverniy_dub_sonoma_beliy_1.webp', 100020),
       ('0065abdb-44f6-41f9-88bb-3df135679205.mambo_sb_2371_shkaf_2_dverniy_dub_sonoma_beliy_2.webp', 100020),
       ('8ec40e72-8c24-49fe-882b-a359167ebf75.mambo_sb_2371_shkaf_2_dverniy_dub_sonoma_beliy_3.webp', 100020),
       ('7ef0fe6a-5efc-4551-b779-fc86af5445be.mambo_sb_2371_shkaf_2_dverniy_dub_sonoma_beliy_4.webp', 100020),
       ('452f4174-fb8d-4879-9be1-24202768a7e4.mambo_sb_2371_shkaf_2_dverniy_dub_sonoma_beliy_5.webp', 100020),
       ('b07e6a3d-dc19-4d1d-80da-82f9d9541850.00000229021detail.webp', 100022),
       ('5ccb304c-3a49-4b61-bae0-f8621ae589d7.mambo_sb_2886_stol_tumba_dub_sonoma_beliy_1.webp', 100022),
       ('9030a3b2-4411-4a27-bffb-53fd10626fb9.mambo_sb_2886_stol_tumba_dub_sonoma_beliy_2.webp', 100022),
       ('ef083ef4-ccab-4181-8065-a27150da679a.mambo_sb_2886_stol_tumba_dub_sonoma_beliy_3.webp', 100022),
       ('6606822f-6eb8-431c-af81-77de094dfc59.mambo_sb_2886_stol_tumba_dub_sonoma_beliy_4.webp', 100022);



