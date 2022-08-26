INSERT INTO users (id, username, password, role, picture_id, rank_id, drop_id)
VALUES ( 1, 'admin'
       , '$2a$12$z2vmX20WkxzGs6x7cDndS.pl10a6NnDhIOKo.cFlodfxtgF/2v/fq'
       , 'ADMIN', null, null, null),
       ( 2, 'moder1'
       , '$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
       , 'USER', 1, 1, 2),
       ( 3, 'moder2'
       , '$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
       , 'USER', 2, 2, 2),
       ( 4, 'moder3'
       , '$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
       , 'USER', 3, 1, 1),
       ( 5, 'moder4'
       , '$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
       , 'USER', 4, 4, 5);

ALTER SEQUENCE seq_users RESTART WITH 6;