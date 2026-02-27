# 📦 Guia de Distribuição - Acal Left

## Opção 1: JAR Executável (Recomendado para desenvolvimento)

O projeto gera automaticamente um **Fat JAR** que contém todas as dependências necessárias.

### Localização:
```
target/acal-left-0.0.1-SNAPSHOT.jar
```

### Como executar:
```bash
java -jar acal-left-0.0.1-SNAPSHOT.jar
```

### Requisitos:
- ✅ Java 21 ou superior instalado no sistema

### Tamanho:
- ~50MB (inclui todas as dependências)

---

## Opção 2: Executável Nativo com JPackage (Recomendado para distribuição)

Gera um **executável nativo com JRE incluído** - não precisa de Java instalado!

### Passos:

#### 1. Compilar o projeto (já feito):
```bash
./mvnw clean package
```

#### 2. Gerar o executável:
```bash
./build-executable.sh
```

Ou manualmente:

**Para Linux/macOS:**
```bash
jpackage \
    --input target \
    --name AcalLeft \
    --main-jar acal-left-0.0.1-SNAPSHOT.jar \
    --main-class acal.com.acal_left.AcalLeftApplication \
    --app-version 0.0.1 \
    --dest target/jpackage \
    --type app-image \
    --vendor "Acal Left"
```

**Para Windows (gera arquivo .exe):**
```bash
jpackage ^
    --input target ^
    --name AcalLeft ^
    --main-jar acal-left-0.0.1-SNAPSHOT.jar ^
    --main-class acal.com.acal_left.AcalLeftApplication ^
    --app-version 0.0.1 ^
    --dest target/jpackage ^
    --type exe ^
    --vendor "Acal Left"
```

**Para macOS (gera arquivo .dmg):**
```bash
jpackage \
    --input target \
    --name AcalLeft \
    --main-jar acal-left-0.0.1-SNAPSHOT.jar \
    --main-class acal.com.acal_left.AcalLeftApplication \
    --app-version 0.0.1 \
    --dest target/jpackage \
    --type dmg \
    --vendor "Acal Left"
```

### Resultado:
- ✅ Executável independente com JRE incluído
- ✅ Não precisa de Java instalado
- ✅ Pode ser distribuído como instalador
- ✅ Funciona em Windows, macOS e Linux

### Tamanho:
- ~100-150MB (inclui JRE 21)

### Localização:
```
target/jpackage/AcalLeft/  (Linux/macOS - pasta com executável)
target/jpackage/AcalLeft.exe  (Windows - instalador)
target/jpackage/AcalLeft.dmg  (macOS - instalador DMG)
```

---

## Comparação

| Característica | Fat JAR | JPackage |
|---|---|---|
| Tamanho | ~50MB | ~100-150MB |
| Requer Java | ✅ Sim | ❌ Não |
| Multiplataforma | ✅ Sim | ❌ Plataforma específica |
| Fácil de distribuir | ✓ Médio | ✓✓ Fácil |
| Aparência nativa | ❌ Não | ✅ Sim |
| Atalhos desktop | ❌ Não | ✅ Sim |
| Desinstalador | ❌ Não | ✅ Sim |

---

## 🔧 Troubleshooting

### JPackage não encontrado
Se receber erro "jpackage not found":
- Verificar se Java 21+ está instalado: `java -version`
- JPackage vem com Java 16+

### Erro ao executar o JAR
```bash
# Aumentar memória disponível
java -Xmx2G -jar acal-left-0.0.1-SNAPSHOT.jar
```

### Erro de banco de dados
Verificar se o arquivo `application.yml` está configurado corretamente com:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

---

## 📝 Notas

1. **Fat JAR** é ideal para ambientes onde Java já está instalado
2. **JPackage** é ideal para distribuição em produção para usuários finais
3. O aplicativo é uma aplicação Desktop Swing (não web)
4. Inclui integração com banco de dados MySQL
5. Tema Dark com FlatLaf para melhor aparência

---

## 🚀 Próximas melhorias

- [ ] Adicionar assinatura digital do executável (código signing)
- [ ] Configurar auto-updates
- [ ] Criar instalador mais robusto para Windows (MSI)
- [ ] Adicionar ícone customizado

