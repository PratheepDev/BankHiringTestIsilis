insert into CLIENT  values (1, 'steve.jobs');
insert into ACCOUNT values (1, 100, '14451', (select id from CLIENT where name = 'steve.jobs'));