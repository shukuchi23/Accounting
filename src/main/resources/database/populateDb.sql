-- Должность
insert into rank (rank_name, rank_salary) values ('first_rank', 20000.00);
insert into rank (rank_name, rank_salary) values ('second_rank', 10000.00);
insert into rank (rank_name, rank_salary) values ('Cool_rank', 20.00);

insert into DEPARTMENT (DEP_NAME, DEP_PHONE, DEP_EMAIL)
    values ('BANK', '8(930)-155-45-08', 'vaizerd23@sdf.com');
insert into DEPARTMENT (DEP_NAME, DEP_PHONE, DEP_EMAIL)
values ('Development1', '8(915)-543-20-21', 'stupid@dsa.com');


insert into EMPLOYEE (emp_lastname, emp_firstname, emp_patronymic, emp_dep_id, emp_rank_id)
    values ('Karimov', 'Evgeniy', 'Ravil', 1, 3);
insert into EMPLOYEE (emp_lastname, emp_firstname, emp_patronymic, emp_dep_id, emp_rank_id)
    values ('Aloha', 'Dance', 'MLG', 2, 2);
insert into EMPLOYEE (emp_lastname, emp_firstname, emp_patronymic, emp_dep_id, emp_rank_id)
    values ('Karimov', 'MVP', 'ASDQW', 1, 2);
