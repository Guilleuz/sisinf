


INSERT INTO BiziStation (id, capacity, available, direction, localitation) VALUES (1, 20, 5, 'Calle Menendez Pelayo', ST_GeomFromText('POINT(-71.12 48.31)', 4326));
INSERT INTO TramStation (id, name, way, direction, localitation) VALUES (1, 'Casablanca', 'Avenida Academia', 'Calle emperador nr 9 ' ,ST_GeomFromText('POINT(-23.12 11.31)', 4326));


INSERT INTO BusStation (id, direction, localitation) VALUES (1, 'Paseo Calanda nr 4', ST_GeomFromText('POINT(11.12 23.31)', 4326));
INSERT INTO BusStation (id, direction, localitation) VALUES (2, 'Pilar nr 7', ST_GeomFromText('POINT(12.12 23.31)', 4326));
INSERT INTO BusStation (id, direction, localitation) VALUES (3, 'Don Jaime nr 3', ST_GeomFromText('POINT(13.12 23.31)', 4326));


INSERT INTO BusLine (id, name, way) VALUES (1, '24', 'norte');

INSERT INTO BusRoute (busStop, line, orden) VALUES (1, 1, 1);
INSERT INTO BusRoute (busStop, line, orden) VALUES (2, 1, 2);
INSERT INTO BusRoute (busStop, line, orden) VALUES (3, 1, 3);
