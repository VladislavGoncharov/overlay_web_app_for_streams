INSERT INTO picture_of_drop (id, numbers_of_drop, address_picture)
VALUES ( 1, 1
       , '01.webp'),
       ( 2, 2
       , '02.webp'),
       ( 3, 3
       , '03.webp'),
       ( 4, 4
       , '04.webp'),
       ( 5, 5
       , '05.webp');

ALTER SEQUENCE seq_picture_of_drop RESTART WITH 6;

INSERT INTO picture_of_rank (id, numbers_of_rank, address_picture)
VALUES ( 1, 1
       , '1.gif'),
       ( 2, 2
       , '2.gif'),
       ( 3, 3
       , '3.gif'),
       ( 4, 4
       , '4.gif'),
       ( 5, 5
       , '5.gif'),
       ( 6, 6
       , '6.gif'),
       ( 7, 7
       , '7.gif'),
       ( 8, 8
       , '8.gif'),
       ( 9, 9
       , '9.gif'),
       ( 10, 10
       , '10.gif'),
       ( 11, 11
       , '11.gif'),
       ( 12, 12
       , '12.gif'),
       ( 13, 13
       , '13.gif'),
       ( 14, 14
       , '14.gif'),
       ( 15, 15
       , '15.gif'),
       ( 16, 16
       , '16.gif'),
       ( 17, 17
       , '17.gif'),
       ( 18, 18
       , '18.gif'),
       ( 19, 19
       , '19.gif'),
       ( 20, 20
       , '20.gif');

ALTER SEQUENCE seq_picture_of_rank RESTART WITH 21;


INSERT INTO character_picture (id, address_of_small_picture, address_of_big_picture)
VALUES ( 1, '01.webp'
       , '01 DWIGHT FAIRFIELD.webp'),
       ( 2, '02.webp'
       , '02 MEG THOMAS.webp'),
       ( 3, '03.webp'
       , '03 CLAUDETTE MOREL.webp'),
       ( 4, '04.webp'
       , '04 JAKE PARK.webp'),
       ( 5, '05.webp'
       , '05 WILLIAM BILL OVERBECK.webp'),
       ( 6, '06.webp'
       , '06 NEA KARLSSON.webp'),
       ( 7, '07.webp'
       , '07 DAVID KING.webp'),
       ( 8, '08.webp'
       , '08 LAURIE STRODE.webp'),
       ( 9, '09.webp'
       , '09 ACE VISCONTI.webp'),
       ( 10, '10.webp'
       , '10 FENG MIN.webp'),
       ( 11, '11.webp'
       , '11 QUENTIN SMITH.webp'),
       ( 12, '12.webp'
       , '12 DETECTIVE DAVID TAPP.webp'),
       ( 13, '13.webp'
       , '13 KATE DENSON.webp'),
       ( 14, '14.webp'
       , '14 ADAM FRANCIS.webp'),
       ( 15, '15.webp'
       , '15 JEFFREY JEFF JOHANSEN.webp'),
       ( 16, '16.webp'
       , '16 JANE ROMERO.webp'),
       ( 17, '17.webp'
       , '17 ASHLEY J. WILLIAMS.webp'),
       ( 18, '18.webp'
       , '18 YUI KIMURA.webp'),
       ( 19, '19.webp'
       , '19 ZARINA KASSIR.webp'),
       ( 20, '20.webp'
       , '20 CHERYL MASON.webp'),
       ( 21, '21.webp'
       , '21 FELIX RICHTER.webp'),
       ( 22, '22.webp'
       , '22 ELODIE RAKOTO.webp'),
       ( 23, '23.webp'
       , '23 YUN-JIN LEE.webp'),
       ( 24, '24.webp'
       , '24 JILL VALENTINE.webp'),
       ( 25, '25.webp'
       , '25 LEON S. KENNEDY.webp'),
       ( 26, '26.webp'
       , '26 MIKAELA REID.webp'),
       ( 27, '27.webp'
       , '27 JONAH VASQUEZ.webp'),
       ( 28, '28.webp'
       , '28 YOICHI ASAKAWA.webp'),
       ( 29, '29.webp'
       , '29 HADDIE KAUR.webp');

ALTER SEQUENCE seq_character_picture RESTART WITH 30;