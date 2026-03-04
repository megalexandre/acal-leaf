# CI/CD - Acal Left

Este projeto inclui workflows GitHub Actions para automação de build e release **acionados por tags**.

## 🔄 Workflows Disponíveis

### 1. **Build Contínuo** (`build.yml`)
Executa automaticamente quando uma tag é criada (ex: `v1.0.0`) ou manualmente via GitHub.

**O que faz:**
- ✅ Compila o projeto com Maven
- ✅ Faz upload dos artefatos JAR/WAR
- ✅ Deixa disponível por 30 dias

**Trigger:**
- Tag `v*` (exemplo: `v1.0.0`, `v2.1.3`)
- Manual (via GitHub Actions)

### 2. **Release** (`release.yml`)
Executa quando uma tag é criada ou manualmente.

**O que faz:**
- ✅ Compila o projeto
- ✅ Cria uma Release no GitHub
- ✅ Faz upload de todos os artefatos compilados
- ✅ Gera notas de release

**Trigger:**
- Tag `v*`
- Manual (via GitHub Actions)

### 3. **Code Quality** (`quality.yml`)
Executa quando uma tag é criada ou manualmente.

**O que faz:**
- ✅ Executa Maven Verify
- ✅ Verifica vulnerabilidades
- ✅ Gera relatórios de teste

**Trigger:**
- Tag `v*`
- Manual (via GitHub Actions)

2. Acesse **GitHub > Releases** para baixar os artefatos

## 📦 Como Usar

### Compilação Local
```bash
mvn clean package
```

### Criar uma Release (Recomendado)
```bash
# 1. Criar uma tag
git tag v1.0.0

# 2. Push da tag
git push origin v1.0.0

# 3. GitHub Actions executa automaticamente
# → Todos os 3 workflows rodam
# → Artefatos aparecem em Releases
```

### Trigger Manual
No GitHub:
1. Vá para **Actions**
2. Selecione um workflow
3. Clique em **Run workflow**
4. Escolha a branch (normalmente `main` ou `develop`)
5. Clique **Run workflow**

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



