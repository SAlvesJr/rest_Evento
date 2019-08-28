# Desafio Java Web Junior

Para Api foi utilizado o banco MySQL com o nome de "db_evento".

Na API constam três entity: Evento, Usuario, Inscricao.
Classes: Evento e Usuario, contém além dos atributos pedidos uma lista de inscrições, lista esta que contém as inscrições feitas.

As operações são feitas no pacote controller:

# Realizar a criação de um evento
EventoController realiza a criação de um evento, a rota utilizada é /eventos, usasse esta rota para fazer GET, PUT, POST e DELETE.

#Listar os inscritos em um evento
Para listagem dos usuários inscritos em evento, a rota /eventos/{id}/listInsc, retorna uma lista de inscrições feitas neste evento, contendo os nomes dos usuarios na lista.

# Realizar a criação de um usuário
UsuarioController realizar a criação de um usuário, a rota é /usuarios, usasse esta rota para fazer GET, PUT, POST e DELETE.

# Listar as inscrições de um usuário
Para listagem das inscrições feitas, a rota é /usuarios/{id}/listInsc, retorna uma lista de inscrições feitas pelo usuário, contendo os nomes dos eventos na lista.

# Realizar a operação de inscrição de um usuário em um evento
A inscrição de um usuário em um evento é feitas na classe UsuarioController, com a rota /usuarios/inscricao, com o método POST tendo no corpo da requisição
"idEvent" e "idUser". "idEvent" sendo o identificador do evento, e "idUser", o identificador do usuário. Com isso cria-se uma inscrição, contendo  o identificador do evento e usuário. O retorno desta operação é o usuário com a adição na lista de inscrição. Com esta operação na classe Evento também é adicionado em sua lista a inscrição feita.

# Realizar o cancelamento de uma inscrição de um usuário em um evento
O cancelamento de uma inscrição de um usuário em um evento, é feita na classe UsuarioController com a rota /usuarios/{id}/delInsc/{insc_id}, com o método DELETE.
{id}, sendo o identificador de usuário, {insc_id} a identificação de qual inscrição esta sendo deletada. Com esta operação se deleta a inscrição, ou seja, o item da lista de inscrição obtida em Evento e Usuario.