@echo off
chcp 65001 >nul
echo ===================================================
echo     COMPILANDO E EXECUTANDO O PROJETO ESCOLA (POO)
echo ===================================================
echo.
echo [1/2] Compilando arquivos Java...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src/escola/*.java

if errorlevel 1 (
    echo.
    echo [ERRO] Ocorreu um erro durante a compilacao.
    pause
    exit /b 1
)

echo [2/2] Executando Main...
echo.
java "-Dfile.encoding=UTF-8" -cp bin escola.Main
echo.
echo ===================================================
echo     EXECUCAO CONCLUIDA COM SUCESSO!
echo ===================================================
pause

