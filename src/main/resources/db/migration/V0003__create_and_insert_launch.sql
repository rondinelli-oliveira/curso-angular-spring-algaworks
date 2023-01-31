CREATE TABLE launch (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payday DATE,
	value DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	id_category BIGINT(20) NOT NULL,
	id_person BIGINT(20) NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEIPT', 1, 1);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Top Club', '2017-06-10', null, 120, null, 'RECEIPT', 3, 3);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEIPT', 3, 4);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEIPT', 4, 6);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Bahamas', '2017-06-10', null, 500, null, 'RECEIPT', 1, 7);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEIPT', 5, 10);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Café', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO launch (description, due_date, payday, value, note, type, id_category, id_person) values ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);