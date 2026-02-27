@echo off
REM Script para executar Acal Left no Windows

REM Configurações para AWT/Swing no Windows
set _JAVA_OPTIONS=-Djava.awt.headless=false

echo Iniciando Acal Left...
echo Variáveis de ambiente configuradas para Windows

REM Executa com Maven
mvn spring-boot:run

REM Ou, se preferir executar o JAR já construído:
REM java -Djava.awt.headless=false -jar target/acal-left-0.0.1-SNAPSHOT.jar

