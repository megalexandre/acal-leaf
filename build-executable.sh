#!/bin/bash

# Script para gerar executável com JPackage (Java nativo)
# Gera um instalador/executável com JRE incluído

echo "=== Building Acal Left Executable with JPackage ==="
echo ""

# Variáveis
JAR_FILE="target/acal-left-0.0.1-SNAPSHOT.jar"
OUTPUT_DIR="target/jpackage"
APP_NAME="AcalLeft"
APP_VERSION="0.0.1"

# Verificar se o JAR existe
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ Erro: JAR file não encontrado: $JAR_FILE"
    echo "Execute: mvn clean package primeiro"
    exit 1
fi

# Criar diretório de saída
mkdir -p "$OUTPUT_DIR"

# Executar jpackage
echo "🔨 Gerando executável com JPackage..."
echo ""

jpackage \
    --input target \
    --name "$APP_NAME" \
    --main-jar "$(basename $JAR_FILE)" \
    --main-class "acal.com.acal_left.AcalLeftApplication" \
    --app-version "$APP_VERSION" \
    --dest "$OUTPUT_DIR" \
    --type app-image \
    --vendor "Acal Left" \
    --description "Acal Left - Gerenciamento de Contas"

echo ""
echo "✅ Executável gerado com sucesso!"
echo ""
echo "Localização: $OUTPUT_DIR/AcalLeft"
echo ""
echo "Para Windows, gere o instalador com:"
echo "  jpackage --input target --name AcalLeft --main-jar acal-left-0.0.1-SNAPSHOT.jar --main-class acal.com.acal_left.AcalLeftApplication --app-version 0.0.1 --dest target/jpackage --type exe"
echo ""
echo "Para macOS, gere o DMG com:"
echo "  jpackage --input target --name AcalLeft --main-jar acal-left-0.0.1-SNAPSHOT.jar --main-class acal.com.acal_left.AcalLeftApplication --app-version 0.0.1 --dest target/jpackage --type dmg"
echo ""

