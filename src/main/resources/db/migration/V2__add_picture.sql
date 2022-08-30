INSERT INTO picture_of_drop (id, numbers_of_drop, address_picture)
VALUES ( 0, 0
       , ''),
       ( 1, 1
       , '/image/drops/01.webp'),
       ( 2, 2
       , '/image/drops/02.webp'),
       ( 3, 3
       , '/image/drops/03.webp'),
       ( 4, 4
       , '/image/drops/04.webp'),
       ( 5, 5
       , '/image/drops/05.webp');

ALTER SEQUENCE seq_picture_of_drop RESTART WITH 6;

INSERT INTO picture_of_rank (id, numbers_of_rank, address_picture)
VALUES ( 1, 1
       , '/image/rank/1.gif'),
       ( 2, 2
       , '/image/rank/2.gif'),
       ( 3, 3
       , '/image/rank/3.gif'),
       ( 4, 4
       , '/image/rank/4.gif'),
       ( 5, 5
       , '/image/rank/5.gif'),
       ( 6, 6
       , '/image/rank/6.gif'),
       ( 7, 7
       , '/image/rank/7.gif'),
       ( 8, 8
       , '/image/rank/8.gif'),
       ( 9, 9
       , '/image/rank/9.gif'),
       ( 10, 10
       , '/image/rank/10.gif'),
       ( 11, 11
       , '/image/rank/11.gif'),
       ( 12, 12
       , '/image/rank/12.gif'),
       ( 13, 13
       , '/image/rank/13.gif'),
       ( 14, 14
       , '/image/rank/14.gif'),
       ( 15, 15
       , '/image/rank/15.gif'),
       ( 16, 16
       , '/image/rank/16.gif'),
       ( 17, 17
       , '/image/rank/17.gif'),
       ( 18, 18
       , '/image/rank/18.gif'),
       ( 19, 19
       , '/image/rank/19.gif'),
       ( 20, 20
       , '/image/rank/20.gif');

ALTER SEQUENCE seq_picture_of_rank RESTART WITH 21;


INSERT INTO character_picture (id, address_of_small_picture, address_of_big_picture, is_died)
VALUES ( 1, '/image/character-small/01.webp'
       , '/image/character-big/01 DWIGHT FAIRFIELD.webp', false),
       ( 2, '/image/character-small/02.webp'
       , '/image/character-big/02 MEG THOMAS.webp', false),
       ( 3, '/image/character-small/03.webp'
       , '/image/character-big/03 CLAUDETTE MOREL.webp', false),
       ( 4, '/image/character-small/04.webp'
       , '/image/character-big/04 JAKE PARK.webp', false),
       ( 5, '/image/character-small/05.webp'
       , '/image/character-big/05 WILLIAM BILL OVERBECK.webp', false),
       ( 6, '/image/character-small/06.webp'
       , '/image/character-big/06 NEA KARLSSON.webp', false),
       ( 7, '/image/character-small/07.webp'
       , '/image/character-big/07 DAVID KING.webp', false),
       ( 8, '/image/character-small/08.webp'
       , '/image/character-big/08 LAURIE STRODE.webp', false),
       ( 9, '/image/character-small/09.webp'
       , '/image/character-big/09 ACE VISCONTI.webp', false),
       ( 10, '/image/character-small/10.webp'
       , '/image/character-big/10 FENG MIN.webp', false),
       ( 11, '/image/character-small/11.webp'
       , '/image/character-big/11 QUENTIN SMITH.webp', false),
       ( 12, '/image/character-small/12.webp'
       , '/image/character-big/12 DETECTIVE DAVID TAPP.webp', false),
       ( 13, '/image/character-small/13.webp'
       , '/image/character-big/13 KATE DENSON.webp', false),
       ( 14, '/image/character-small/14.webp'
       , '/image/character-big/14 ADAM FRANCIS.webp', false),
       ( 15, '/image/character-small/15.webp'
       , '/image/character-big/15 JEFFREY JEFF JOHANSEN.webp', false),
       ( 16, '/image/character-small/16.webp'
       , '/image/character-big/16 JANE ROMERO.webp', false),
       ( 17, '/image/character-small/17.webp'
       , '/image/character-big/17 ASHLEY J. WILLIAMS.webp', false),
       ( 18, '/image/character-small/18.webp'
       , '/image/character-big/18 YUI KIMURA.webp', false),
       ( 19, '/image/character-small/19.webp'
       , '/image/character-big/19 ZARINA KASSIR.webp', false),
       ( 20, '/image/character-small/20.webp'
       , '/image/character-big/20 CHERYL MASON.webp', false),
       ( 21, '/image/character-small/21.webp'
       , '/image/character-big/21 FELIX RICHTER.webp', false),
       ( 22, '/image/character-small/22.webp'
       , '/image/character-big/22 ELODIE RAKOTO.webp', false),
       ( 23, '/image/character-small/23.webp'
       , '/image/character-big/23 YUN-JIN LEE.webp', false),
       ( 24, '/image/character-small/24.webp'
       , '/image/character-big/24 JILL VALENTINE.webp', false),
       ( 25, '/image/character-small/25.webp'
       , '/image/character-big/25 LEON S. KENNEDY.webp', false),
       ( 26, '/image/character-small/26.webp'
       , '/image/character-big/26 MIKAELA REID.webp', false),
       ( 27, '/image/character-small/27.webp'
       , '/image/character-big/27 JONAH VASQUEZ.webp', false),
       ( 28, '/image/character-small/28.webp'
       , '/image/character-big/28 YOICHI ASAKAWA.webp', false),
       ( 29, '/image/character-small/29.webp'
       , '/image/character-big/29 HADDIE KAUR.webp', false);

ALTER SEQUENCE seq_character_picture RESTART WITH 30;