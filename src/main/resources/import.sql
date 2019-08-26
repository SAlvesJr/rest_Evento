insert into evento (id, nome_evento, vagas) values (null, 'João', 10);
insert into evento (id, nome_evento, vagas) values (null, 'Marina', 5);

insert into usuario (id, nome, CONSTRAINT fk_EventUser FOREIGN KEY (id_Evento) REFERENCES evento (id)) values (null, 'Marina', 1);