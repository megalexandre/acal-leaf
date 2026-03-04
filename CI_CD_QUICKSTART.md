# 🚀 Guia Rápido - CI/CD Acal Left

## ⚡ Resumo Executivo

Seu projeto agora tem **3 workflows GitHub Actions** que compilam **automaticamente quando você cria uma tag** e deixam os artefatos disponíveis para download.

## 📋 Workflows Criados

| Workflow | Trigger | Função |
|----------|---------|--------|
| **build.yml** | Tag `v*` ou Manual | Compila e faz upload de JARs |
| **release.yml** | Tag `v*` ou Manual | Cria Release com artefatos |
| **quality.yml** | Tag `v*` ou Manual | Verificação de qualidade |

## 🎯 Como Funciona

### Scenario 1: Criar uma Release/Build
```bash
# Criar uma tag vX.Y.Z
git tag v1.0.0
git push origin v1.0.0

# ✅ GitHub Actions executa automaticamente (todos os 3 workflows)
# → Compila o projeto
# → Verifica qualidade
# → Cria Release no GitHub com todos os JARs
# → Disponível para download
```

### Scenario 2: Trigger Manual
No GitHub:
1. **Actions** → **Build e Upload Artefatos** (ou outro workflow)
2. **Run workflow**
3. Esperar completar

## 📥 Download dos Artefatos

### Opção 1: Via GitHub Actions
1. Acesse a aba **Actions**
2. Clique no workflow que rodou
3. Role até **Artifacts**
4. Clique em `jar-artifacts` para baixar

### Opção 2: Via Releases (automático)
1. Acesse **Releases**
2. Procure pela versão (ex: `v1.0.0`)
3. Baixe os JARs direto

## 📁 O Que é Compilado

Cada workflow gera:
- ✅ `acal-left-shared-*.jar`
- ✅ `acal-left-persistence-*.jar`
- ✅ `acal-left-core-*.jar`
- ✅ `acal-left-ui-*.jar`
- ✅ `acal-left-app-*.jar`

## 🔧 Configurações Incluídas

- **Java 21** (Temurin)
- **Maven cache** habilitado (builds mais rápidos)
- **Ubuntu latest** (runner)
- **Retenção de 30 dias** para artifacts
- **Trigger**: Apenas em tags `v*`

## ❌ Troubleshooting

### Build falhou?
1. Acesse **Actions** → clique no workflow falhado
2. Role para ver o erro
3. Ajuste o código e crie uma nova tag (ex: v1.0.1)

### Artefatos não aparecem?
1. Verifique se o workflow completo foi bem-sucedido (✅ verde)
2. Se foi, role até a seção "Artifacts"

### Quer testar localmente antes?
```bash
mvn clean package
```

## 📚 Arquivos Criados

```
.github/
├── workflows/
│   ├── build.yml          ← Build quando tagueia
│   ├── release.yml        ← Release automático
│   └── quality.yml        ← Verificação de qualidade
└── WORKFLOWS_README.md    ← Documentação completa
```

## 🎓 Fluxo Recomendado

1. **Desenvolva normalmente** (commits, pushes)
2. **Quando pronto para release**: 
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```
3. **GitHub Actions roda automaticamente** ✨
4. **Artefatos aparecem em Releases**
5. **Pronto para distribuir!** 🎉

---

**✨ CI/CD está pronto para uso!** Crie uma tag e veja o GitHub compilar seu projeto. 🚀


