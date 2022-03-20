# Horns_and_Hooves

Spring Boot Rest JPA
=============================
Пользователь заходит в приложение, выбирает мебель, кладет в корзину, нажимает купить 

-> создается ордер в котором вся мебель из заказа делится на работников по депортаментам

-> они её делают и, когда всё готово, ордер завершается и отправляется смс или email

-> работники могут заходить и просматривать ордера, менять статус и оставлять коментарии

-> админ может менять ордера, создавать новую мебель

INSTALLATION
------------
В конфигурационном файле <b>application.properties</b> необходимо заполнить свойство spring.profiles.active=postgre (провайдер sql)
Для запуска необходимо создать базу данных и добавить её в конфигурационный файл 

(например <b>application-postgre.properties</b>)

SQL HELP
-----------
On command line, type in the following commands:

        select * from person;
        select * from customer_order;
        select * from item;

        delete from person where id=100001;
        delete from item where id=100007;
        delete from customer_order where id=100010;

        select * from task;
        select * from order_items;
        select o.name, i.name from order_items oi
        join customer_order o on oi.customer_order_id = o.id
        join item i on oi.item_id = i.id;

        select
          p.name,
          t.name
        from person p
          join task t on p.id = t.person_id;

        select * from customer_order co
        join order_items o on co.id = o.customer_order_id
        join item i on o.item_id = i.id
        where i.id = '100009';

WHAT'S NEXT
-----------
- прикрутить к tasks выбор на кого будет задача
- сделать авторизацию и фильтрацию записей из базы по юзеру при выборке(сквозная функциональность проверок)
- починить hsqldb, h2
- переделать angular-routing
- покрыть тестами
- логирование
- сделать валидацию
- добавить кодаси и прочую хрень
- таблицу заказов и айтемы разбить постранично с помощью спринг пейджинга


- при вытаскивании ордеров вытаскивать айдишники айтемов а не сущности, потому что это накладно вытаскивать одни и те же сущности, нужно их вытаскивать 1 раз и потом мапить (или если айтемов будет очень много, то вытаскивать позже при просмотре по айдишникам)
- написать генератор данных, который будет заполнять базу кучей данных

- hibernate N+1
- cascade = all

- create and edit сделать через модальные окна
- сделать несколько картинок у айтема
- переделать выбор количество записей на странице под директиву

ИНТЕРНЕТ МАГАЗИН ЧЕГО ЛИБО НАПИСАТЬ!!!!!!!!!!!!!!!!!!!!


Rusfrance inc.
