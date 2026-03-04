# CI/CD - Acal Left

Este projeto inclui workflows GitHub Actions para automação de build e release.

## 🔄 Workflows Disponíveis

### 1. **Build Contínuo** (`build.yml`)
Executa automaticamente em cada push para `main` ou `develop` e em PRs.

**O que faz:**
- ✅ Compila o projeto com Maven
- ✅ Executa testes (testes de unidade)
- ✅ Faz upload dos artefatos JAR/WAR
- ✅ Gera relatórios de build

**Artefatos disponíveis por 30 dias:**
```
- JAR files (de cada módulo)
- WAR files (se houver)
```

### 2. **Release** (`release.yml`)
Executa quando uma tag é criada (ex: `v1.0.0`) ou manualmente.

**O que faz:**
- ✅ Compila o projeto
- ✅ Cria uma Release no GitHub
- ✅ Faz upload de todos os artefatos compilados
- ✅ Gera notas de release

## 📦 Como Usar

### Compilação Local
```bash
mvn clean package
```

### Disparar Build no GitHub
Apenas faça push para as branches `main` ou `develop`:
```bash
git push origin main
```

### Criar Release
1. Crie uma tag:
```bash
git tag v1.0.0
git push origin v1.0.0
```

2. Acesse **GitHub > Releases** para baixar os artefatos

### Trigger Manual
No GitHub:
1. Vá para **Actions**
2. Selecione **Build Release**
3. Clique em **Run workflow**

## 📊 Estrutura de Artefatos

Os arquivos compilados são organizados por módulo:
```
acal-left-shared/target/
├── acal-left-shared-0.0.1-SNAPSHOT.jar

acal-left-persistence/target/
├── acal-left-persistence-0.0.1-SNAPSHOT.jar

acal-left-core/target/
├── acal-left-core-0.0.1-SNAPSHOT.jar

acal-left-ui/target/
├── acal-left-ui-0.0.1-SNAPSHOT.jar

acal-left-app/target/
├── acal-left-app-0.0.1-SNAPSHOT.jar
```

## 🔐 Variáveis de Ambiente

Nenhuma variável de ambiente secreta é necessária. O `GITHUB_TOKEN` é fornecido automaticamente.

## ⚙️ Configurações

### Java Version
- **Versão**: Java 21
- **Distribuição**: Temurin (OpenJDK)

### Cache Maven
O workflow usa cache automático para dependências Maven, acelerando builds subsequentes.

### Retenção de Artefatos
- **Builds normais**: 30 dias
- **Relatórios**: 7 dias
- **Releases**: Indefinido

## 📝 Dicas

1. **Ignorar testes em builds rápidos**: `-DskipTests`
2. **Compilar um módulo específico**: `mvn clean package -pl acal-left-app`
3. **Ver logs detalhados**: Acesse a aba "Actions" no GitHub

## 🚀 Próximos Passos

- [ ] Adicionar testes automáticos
- [ ] Adicionar sonarqube para análise de código
- [ ] Configurar deploy automático
- [ ] Adicionar verificação de vulnerabilidades (OWASP)

