# Sistema de Cadastro de Clientes

Este projeto é um sistema de cadastro de clientes desenvolvido em Java utilizando Swing para a interface gráfica. O sistema permite cadastrar, alterar, buscar, excluir e listar clientes. Os dados dos clientes são armazenados utilizando duas implementações diferentes de DAO (Data Access Object): `ClienteSetDAO` e `ClienteMapDAO`.

Este projeto foi desenvolvido como parte da atividade do módulo de backend no curso Fullstack Java da EBAC.


## Funcionalidades

- **Cadastro de Cliente**: Permite cadastrar clientes com nome, CPF, telefone, endereço, cidade, estado, etc.
- **Busca de Cliente**: Busca clientes pelo CPF.
- **Alteração de Cliente**: Atualiza as informações de um cliente já cadastrado.
- **Exclusão de Cliente**: Remove um cliente do sistema utilizando o CPF.
- **Listagem de Clientes**: Exibe uma lista de todos os clientes cadastrados.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Swing**: Biblioteca gráfica utilizada para criar a interface de usuário.
- **DAO Pattern**: Padrão de projeto utilizado para acessar os dados dos clientes, com duas implementações:
  - `ClienteSetDAO`: Utiliza um `HashSet` para armazenar clientes.
  - `ClienteMapDAO`: Utiliza um `HashMap` para armazenar clientes.
