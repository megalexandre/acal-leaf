# language: pt
Funcionalidade: Login no aplicativo Swing

  Como usuario do sistema
  Quero me autenticar pela tela de login
  Para acessar as funcionalidades do sistema

  Cenario: Login com sucesso
    Dado que a tela de login esta aberta
    Quando eu informo usuario "admin" e senha "acal123"
    E eu clico em entrar
    Entao o login deve ser realizado com sucesso

  Cenario: Login com credenciais invalidas
    Dado que a tela de login esta aberta
    Quando eu informo usuario "admin" e senha "senha-invalida"
    E eu clico em entrar
    Entao devo ver a mensagem de credenciais invalidas

  Cenario: Login sem preencher campos obrigatorios
    Dado que a tela de login esta aberta
    Quando eu nao preencho usuario e senha
    E eu clico em entrar
    Entao devo ver a mensagem de validacao de campos obrigatorios
