#!/bin/bash

# Script para executar Acal Left com suporte a Wayland/X11

# Configurações para Wayland e X11
export _JAVA_AWT_WM_NONREPARENTING=1
export _JAVA_OPTIONS="-Djava.awt.headless=false -Dsun.awt.disablegrab=true"

# Para Wayland especificamente, pode usar:
# export GDK_BACKEND=x11
# ou
# export GDK_BACKEND=wayland

echo "Iniciando Acal Left..."
echo "Variáveis de ambiente configuradas para Wayland/X11"

# Executa com Maven
mvn spring-boot:run

# Ou, se preferir executar o JAR já construído:
# java \
#   -Djava.awt.headless=false \
#   -Dsun.awt.disablegrab=true \
#   -Dawt.toolkit=sun.awt.X11.XToolkit \
#   -jar target/acal-left-0.0.1-SNAPSHOT.jar

