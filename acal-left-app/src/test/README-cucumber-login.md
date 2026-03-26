# Cucumber Login (Swing + Spring)

Este pacote adiciona um teste BDD inicial da tela de login com interacao real de UI Swing (campos e botao).

## O que foi coberto

- Login com credenciais validas
- Login com credenciais invalidas
- Validacao quando usuario/senha estao vazios

## Estrutura

- `src/test/resources/features/login/login.feature`
- `src/test/java/acal/com/acal_left/bdd/RunCucumberTest.java`
- `src/test/java/acal/com/acal_left/bdd/CucumberSpringConfiguration.java`
- `src/test/java/acal/com/acal_left/bdd/config/LoginBddTestConfig.java`
- `src/test/java/acal/com/acal_left/bdd/steps/LoginSteps.java`

## Executar

Recomendado em ambiente Linux com display ativo ou via Xvfb:

```bash
cd /home/alex/Workspace/acal-left
xvfb-run -a ./mvnw -pl acal-left-app -Dtest=RunCucumberTest test
```

Se voce ja estiver com `DISPLAY` configurado:

```bash
cd /home/alex/Workspace/acal-left
./mvnw -pl acal-left-app -Dtest=RunCucumberTest test
```

