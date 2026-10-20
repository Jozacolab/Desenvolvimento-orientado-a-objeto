# -*- coding: utf-8 -*-
with open('Desenvolvimento_POO.md', 'r', encoding='utf-8') as f:
    content = f.read()

target = """### 9.4 Estrutura de Arquivos Criada"""

replacement = """### 9.4 Diagramas UML da Fase 02

#### A) Diagrama de Classes UML Atualizado
Representa a arquitetura estática da Fase 02 com encapsulamento rigoroso (atributos `- private`, métodos `+ public`), herança de `Pessoa` para `Aluno` e `Professor`, e multiplicidades formais nas associações:

```mermaid
classDiagram
    direction TB

    class Pessoa {
        -String nome
        -String cpf
        -LocalDate dataNascimento
        +cadastrar() void
        +atualizar() void
        +getNome() String
        +setNome(String nome) void
        +getCpf() String
        +setCpf(String cpf) void
        +getDataNascimento() LocalDate
        +setDataNascimento(LocalDate data) void
    }

    class Aluno {
        -String matricula
        -String email
        +matricular(Disciplina d) Matricula
        +consultarNotas() void
        +getMatricula() String
        +setMatricula(String matricula) void
        +getEmail() String
        +setEmail(String email) void
    }

    class Professor {
        -String registro
        -String especialidade
        +ministrarAula() void
        +lancarNota(Matricula m, double nota) void
        +getRegistro() String
        +setRegistro(String registro) void
        +getEspecialidade() String
        +setEspecialidade(String esp) void
    }

    class Escola {
        -String nome
        -String cnpj
        -String endereco
        +cadastrarAluno(Aluno a) void
        +criarTurma(Turma t) void
        +getNome() String
        +getCnpj() String
        +getEndereco() String
    }

    class Turma {
        -String codigo
        -String nome
        -int ano
        -String turno
        +adicionarAluno(Aluno a) void
        +adicionarDisciplina(Disciplina d) void
        +getCodigo() String
        +getAno() int
        +getTurno() String
    }

    class Disciplina {
        -String codigo
        -String nome
        -int cargaHoraria
        +cadastrarNota(Matricula m, double nota) void
        +getCodigo() String
        +getNome() String
        +getCargaHoraria() int
    }

    class Matricula {
        -LocalDate data
        -String status
        -double nota
        +efetivar() void
        +cancelar() void
        +getStatus() String
        +setStatus(String status) void
        +getNota() double
        +setNota(double nota) void
    }

    %% Generalizacao
    Pessoa <|-- Aluno : herda de
    Pessoa <|-- Professor : herda de

    %% Associacoes
    Escola "1" o-- "1..*" Turma : possui
    Escola "1" o-- "0..*" Aluno : cadastra
    Turma "1" o-- "0..*" Aluno : contem
    Turma "1" o-- "1..*" Disciplina : oferece
    Professor "1" --> "1..*" Disciplina : leciona
    Aluno "1" --> "0..*" Matricula : possui
    Disciplina "1" --> "0..*" Matricula : vinculada
```

#### B) Diagrama de Sequência UML (Validação de Nota)

```mermaid
sequenceDiagram
    autonumber
    actor Main as Main (Classe de Teste)
    participant Prof as prof : Professor
    participant Mat as matricula : Matricula
    participant Aluno as aluno : Aluno

    Note over Main,Mat: Fluxo Nominal: Lançamento de Nota Válida
    Main->>Prof: lancarNota(matricula, 9.5)
    activate Prof
    Prof->>Mat: setNota(9.5)
    activate Mat
    Note over Mat: Validação Interna:<br/>0.0 <= nota <= 10.0 (Válido)
    Mat-->>Prof: Nota aceita
    deactivate Mat
    Prof-->>Main: Confirmação do lançamento
    deactivate Prof

    Main->>Aluno: consultarNotas()
    activate Aluno
    Aluno->>Mat: getNota()
    Mat-->>Aluno: 9.5
    Aluno-->>Main: Exibe boletim com nota 9.5
    deactivate Aluno

    Note over Main,Mat: Teste de Defesa: Atribuição Inválida
    Main->>Mat: setNota(15.0)
    activate Mat
    Note over Mat: Validação:<br/>nota > 10.0 (Inválido!)
    Mat-->>Main: Lança IllegalArgumentException ("Nota fora do intervalo")
    deactivate Mat
```

---

### 9.5 Estrutura de Arquivos Criada"""

content = content.replace("### 9.5 Como Compilar", "### 9.6 Como Compilar")
content = content.replace("### 9.6 Resultados da", "### 9.7 Resultados da")

if target in content:
    content = content.replace(target, replacement)
    with open('Desenvolvimento_POO.md', 'w', encoding='utf-8') as f:
        f.write(content)
    print("Atualizado com sucesso!")
else:
    print("Target não encontrado!")
