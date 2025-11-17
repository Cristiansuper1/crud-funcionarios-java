# Changelog

Todas as alterações relevantes para o projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/).

## [Unreleased]
### Planejado
- Verificação por tipo de funcionário que irá acessar o sistema (admin e comum)
- Criação de usuários por meio de um banco de dados simples
- Admin fixo para manutenção
- Melhorias no visual gráfico
- Correção de bugs
- Criação do FEATURES.md e ARCHITECTURE.md, além de documento docs mostrando a regra de negócio conceituada

## [1.3.0] - 2025-11-17
### Adicionado
- Sistema de autenticação com login e senha
- Diferenciação entre tipos de usuários (admin e comum)
- Controle de permissões por nível de acesso
- Menu adaptado para usuário simples (apenas visualização)
- Três usuários de teste: admin, zezinho e clarinha
- Uso de Map e HashMap para armazenar usuários e permissões

### Modificado
- Substituição de ArrayList por HashMap para melhor performance na busca de usuários
- Menu principal agora mostra tipo de usuário logado

## [1.2.0] - 2025-11-14
### Adicionado
- Validação de nome para não aceitar números
- Validação para prevenir salários negativos e zerados

## [1.1.0] - 2025-11-10
### Adicionado
- Funcionalidades novas não pensadas na primeira versão

## [1.0.0] - 2025-11-06
### Adicionado
- Sistema CRUD inicial de funcionários
- Interface com JOptionPane
- Validação básica de campos
- README.md e .gitignore
- Licença MIT