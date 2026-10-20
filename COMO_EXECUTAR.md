# 📘 Guia Passo a Passo: Como Executar o Projeto Java

Este guia foi elaborado especialmente para você que está iniciando os estudos em **Java** e **Programação Orientada a Objetos (POO)**. Aqui você aprenderá como compilar e rodar o projeto do zero, entendendo o que cada comando faz.

---

## 🗂️ 1. Entendendo a Estrutura do Projeto

No Java, os arquivos com código-fonte terminam com a extensão `.java`. Quando o computador vai executá-los, primeiro precisamos **compilar** (traduzir de Java para *bytecodes* `.class`), e depois **executar** essa classe compilada.

Nossa estrutura de pastas está organizada assim:

```text
Desenvolvimento orientado a objeto/
├── src/
│   └── escola/               <- Seus arquivos de código Java (.java)
│       ├── Pessoa.java
│       ├── Aluno.java
│       ├── Professor.java
│       ├── Escola.java
│       ├── Turma.java
│       ├── Disciplina.java
│       ├── Matricula.java
│       └── Main.java         <- Classe que contém o ponto de partida (método main)
├── bin/                      <- Onde ficam os arquivos compilados (.class)
├── COMO_EXECUTAR.md          <- Este guia didático
└── executar.bat              <- Atalho rápido de 1 clique no Windows
```

---

## 🚀 Método 1: Executando pelo Terminal (Recomendado para Aprender)

Você pode usar o terminal do próprio VS Code (ou o **PowerShell** do Windows).

### Passo 1: Abrir o Terminal na Pasta do Projeto
1. Se estiver no **VS Code**, clique no menu superior em:  
   `Terminal` ➡️ `Novo Terminal` (ou pressione as teclas `Ctrl + '`).
2. Verifique se o caminho exibido no terminal é a pasta do projeto:  
   `D:\Faculdade\6 periodo\Desenvolvimento orientado a objeto>`

---

### Passo 2: Compilar o Projeto (`javac`)
A compilação lê seus arquivos `.java` e cria os arquivos `.class` na pasta `bin`.

Copie e cole este comando no terminal e aperte **Enter**:

```powershell
javac -encoding UTF-8 -d bin src/escola/*.java
```

> 💡 **O que significa cada parte desse comando?**
> - `javac`: É o compilador do Java (*Java Compiler*).
> - `-encoding UTF-8`: Garante que acentos e caracteres em português (como `ã`, `é`, `ç`) não dêem erro no Windows.
> - `-d bin`: Diz para o Java salvar os arquivos compilados dentro da pasta `bin`.
> - `src/escola/*.java`: Pede para compilar todas as classes que estão dentro da pasta `src/escola`.

*Se o comando não exibir nenhuma mensagem de erro e apenas pular para a próxima linha, significa que compilou com 100% de sucesso!*

---

### Passo 3: Executar o Programa (`java`)
Agora que o programa foi compilado, vamos pedir para a máquina virtual Java (JVM) executar a classe `Main`.

Copie e cole este comando e aperte **Enter**:

```powershell
java "-Dfile.encoding=UTF-8" -cp bin escola.Main
```

> 💡 **O que significa cada parte desse comando?**
> - `java`: Executa o programa Java.
> - `"-Dfile.encoding=UTF-8"`: Garante que os textos e mensagens exibidos no terminal saiam com a acentuação correta.
> - `-cp bin`: Significa *Classpath*. Diz ao Java para procurar as classes compiladas dentro da pasta `bin`.
> - `escola.Main`: O nome completo da classe que deve iniciar o programa (`pacote.NomeDaClasse`).

---

## ⚡ Método 2: Execução Rápida com 1 Clique (`executar.bat`)

Para facilitar o seu dia a dia, criamos um arquivo chamado `executar.bat` na pasta do projeto.

1. **Pelo Explorador de Arquivos:**  
   Basta dar um duplo clique sobre o arquivo `executar.bat`.
2. **Pelo Terminal:**  
   Basta digitar e apertar Enter:
   ```powershell
   .\executar.bat
   ```

Ele fará todo o processo de compilação e execução automaticamente para você!

---

## 🖥️ Método 3: Executando direto pelo VS Code (Botão Play)

Se você tem a extensão **Extension Pack for Java** instalada no VS Code:

1. No menu lateral esquerdo do VS Code, abra a pasta `src/escola/`.
2. Dê um clique duplo para abrir o arquivo [`Main.java`](src/escola/Main.java).
3. Localize no canto superior direito da tela o ícone triangular de **Play (Run Java)** ou logo acima da linha `public static void main(String[] args)` o texto clicável **`Run`**.
4. Clique em **Run**. O VS Code compilará e exibirá o resultado no painel inferior.

---

## 📋 4. O que esperar na tela (Resultado dos Testes)

Quando o programa rodar, você verá duas partes principais no terminal:

### 1ª Parte: Cenário Normal (Criação Válida)
O programa cria os objetos com sucesso usando os métodos *getters* e *setters*:
- Cadastra a escola, o professor e a aluna.
- Vincula o aluno à turma e matricula na disciplina de POO.
- O professor lança a nota `9.5`.
- A aluna consulta o boletim com suas notas.

### 2ª Parte: Demonstração de Encapsulamento (Proteção de Dados)
O programa tenta propositalmente atribuir valores inválidos para provar que os *setters* estão protegendo a classe:
- ❌ Tenta dar nota `15.0` ou `-2.5` ➡️ **O Java bloqueia** e avisa que a nota deve estar entre 0.0 e 10.0.
- ❌ Tenta cadastrar CPF com tamanho errado (`123`) ➡️ **O Java bloqueia** e avisa que precisa de 11 dígitos.
- ❌ Tenta cadastrar e-mail sem formato válido ➡️ **O Java bloqueia**.
- ❌ Tenta colocar carga horária `0` na disciplina ➡️ **O Java bloqueia**.
- ❌ Tenta deixar nome em branco ➡️ **O Java bloqueia**.

Isso comprova ao seu professor que o **encapsulamento da Fase 02** está funcionando perfeitamente!

---

## ❓ 5. Dúvidas Frequentes e Possíveis Erros

### Erro: `'javac' não é reconhecido como comando interno`
- **Causa:** O Java JDK não está adicionado na variável de ambiente `PATH` do Windows.
- **Como testar:** Digite `javac -version` no terminal. No seu computador o Java 17 já está instalado e funcionando. Se ocorrer em outro computador, verifique a instalação do JDK.

### Como alterar dados de teste?
- Quer testar outro nome ou outra nota? Basta abrir o arquivo [`Main.java`](src/escola/Main.java), alterar os valores desejados nos objetos e repetir os passos de compilar (`javac`) e executar (`java`).

