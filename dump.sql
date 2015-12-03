-- Создание именованного типа
CREATE TYPE human_typ AS OBJECT 
( 
	age NUMBER,
	f_name VARCHAR(50),
	l_name VARCHAR(50) 
);

-- Создание таблицы содержащая именованный тип
CREATE TABLE human_table
(
	id NUMBER PRIMARY KEY,
	name human_typ NOT NULL
);

-- Создание создание последовательности от 1 с шагом 1
CREATE SEQUENCE id_seq
START WITH 1 
INCREMENT BY 1 
NOCACHE
NOCYCLE;

-- Создание тригера и присвоение последовательности id_seq таблице human_typ
CREATE OR REPLACE TRIGGER hunam_id_trg 
BEFORE INSERT ON human_table 
FOR each row 
BEGIN
  SELECT id_seq.NEXTVAL
  INTO :new.id
  FROM dual;
END;

-- Пример вставки тестовых данных PL/SQL
BEGIN
	INSERT INTO human_table (name)
		VALUES(human_typ(24, 'Dima', 'Busugin'));
	INSERT INTO human_table (name)
		VALUES(human_typ(23, 'Ket', 'Kat'));
END;



