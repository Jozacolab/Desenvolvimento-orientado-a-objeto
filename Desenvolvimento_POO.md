## 1. Contexto do projeto

Este projeto consiste na modelagem de um sistema de gestão escolar usando UML (Unified Modeling Language). A primeira fase concentrou-se na escolha do domínio, na identificação das classes principais, em seus atributos e métodos e na definição inicial dos relacionamentos.

O domínio escolhido foi **uma escola**, por ser um cenário familiar e adequado para demonstrar conceitos fundamentais de orientação a objetos e diagramas de classes.

Este documento registra o contexto atual para que a Fase 02 possa começar sem perder as decisões e os pontos que ainda precisam ser ajustados.

## 2. Objetivo

Construir um modelo de classes UML para representar elementos essenciais de uma escola, incluindo pessoas, alunos, professores, turmas, disciplinas e matrículas.

O diagrama deve ser compreensível, visualmente organizado e consistente com a notação UML, especialmente quanto a herança, associações e multiplicidades.

## 3. Escopo atual

As classes identificadas são:

- `Pessoa`
- `Aluno`
- `Professor`
- `Escola`
- `Turma`
- `Disciplina`
- `Matricula`

Não há necessidade de adicionar novas classes neste momento, a menos que os requisitos da Fase 02 indiquem isso.

## 4. Classes, atributos e métodos

### 4.1 `Pessoa`

Superclasse comum a alunos e professores.

**Atributos**
- `nome: String`
- `cpf: String`
- `dataNascimento: Date`

**Métodos**
- `cadastrar()`
- `atualizar()`

### 4.2 `Aluno`

Especialização de `Pessoa`.

**Atributos**
- `matricula: String`
- `email: String`

**Métodos**
- `matricular()`
- `consultarNotas()`

Observação: o atributo `matricula` em `Aluno` representa o número/código de matrícula do aluno. A classe `Matricula` representa o vínculo de matrícula em uma disciplina; são conceitos diferentes, embora seja possível renomear o atributo para `numeroMatricula` para evitar ambiguidade.

### 4.3 `Professor`

Especialização de `Pessoa`.

**Atributos**
- `registro: String`
- `especialidade: String`

**Métodos**
- `ministrarAula()`
- `lancarNota()`

### 4.4 `Escola`

Representa a instituição de ensino.

**Atributos**
- `nome: String`
- `cnpj: String`
- `endereco: String`

**Métodos**
- `cadastrarAluno()`
- `criarTurma()`

### 4.5 `Turma`

Representa uma turma de alunos em determinado ano e turno.

**Atributos**
- `codigo: String`
- `nome: String`
- `ano: int`
- `turno: String`

**Métodos**
- `adicionarAluno()`

O método `adicionarDisciplina()` pode ser incluído caso se queira explicitar essa responsabilidade, mas não é obrigatório se a associação com `Disciplina` já estiver representada no diagrama.

### 4.6 `Disciplina`

Representa uma matéria oferecida pela escola.

**Atributos**
- `codigo: String`
- `nome: String`
- `cargaHoraria: int`

**Métodos**
- `cadastrarNota()`

Observação: em um modelo mais rigoroso, o registro de uma nota poderia ser responsabilidade de `Matricula`, pois a nota pertence ao vínculo de um aluno com uma disciplina. Para manter o modelo simples, essa decisão pode ser revisada na Fase 02.

### 4.7 `Matricula`

Representa o vínculo entre um aluno e uma disciplina.

**Atributos**
- `data: Date`
- `status: String`
- `nota: double`

**Métodos**
- `efetivar()`
- `cancelar()`

## 5. Relacionamentos previstos

### Herança
- `Aluno` herda de `Pessoa`.
- `Professor` herda de `Pessoa`.

Na UML, a generalização deve ser desenhada com uma linha contínua e um triângulo vazado apontando para a superclasse `Pessoa`.

### Associações
- `Escola` possui turmas.
- `Aluno` pertence a uma turma.
- `Turma` possui/oferece disciplinas.
- `Professor` ministra disciplinas.
- `Aluno` se relaciona com `Disciplina` por meio de `Matricula`.

### Multiplicidades sugeridas para o modelo acadêmico simplificado

| Relacionamento | Multiplicidade sugerida | Interpretação |
|---|---|---|
| `Escola` — `Turma` | Escola `1`; Turma `1..*` | Uma escola possui uma ou mais turmas. |
| `Turma` — `Aluno` | Turma `1`; Aluno `0..*` | Uma turma pode ter vários alunos; para este modelo simplificado, cada aluno pertence a uma turma. |
| `Turma` — `Disciplina` | Turma `1`; Disciplina `1..*` | Uma turma oferece uma ou mais disciplinas. |
| `Professor` — `Disciplina` | Professor `1`; Disciplina `1..*` | Um professor pode ministrar uma ou mais disciplinas. Esta regra pode ser refinada se vários professores puderem lecionar a mesma disciplina. |
| `Aluno` — `Matricula` | Aluno `1`; Matricula `0..*` | Um aluno pode ter várias matrículas. |
| `Disciplina` — `Matricula` | Disciplina `1`; Matricula `0..*` | Uma disciplina pode estar associada a várias matrículas. |

**Importante:** as multiplicidades precisam aparecer junto às extremidades das associações no diagrama. As linhas comuns de associação não devem ser substituídas por setas simples sem intenção explícita de indicar navegabilidade.

## 6. Decisões visuais

O diagrama atual usa cores pastéis para diferenciar as classes:

| Classe | Cor sugerida | HEX |
|---|---|---|
| `Pessoa` | Azul claro | `#DCEBFF` |
| `Aluno` | Verde claro | `#DDF5E3` |
| `Professor` | Roxo claro | `#E8DDF5` |
| `Escola` | Azul médio claro | `#C9E4F6` |
| `Turma` | Amarelo claro | `#FFF0C2` |
| `Disciplina` | Laranja claro | `#FFE0C2` |
| `Matricula` | Rosa claro | `#F8DCE8` |

As cores são uma escolha visual para facilitar a leitura; não representam uma regra formal da UML. O diagrama deve manter contraste suficiente entre fundo, bordas e texto.

## 7. Ajustes pendentes identificados na Fase 01

1. **Herança:** trocar o triângulo preenchido por um triângulo vazado, apontando para `Pessoa`.
2. **Relacionamentos:** revisar as setas simples atuais. Para associações comuns, usar linhas contínuas e multiplicidades; utilizar setas apenas quando houver intenção de representar navegabilidade.
3. **Classe `Escola`:** mantê-la no diagrama, ligada a `Turma`.
4. **Aluno e turma:** ligar `Aluno` diretamente a `Turma`, sem usar `Escola` como intermediária nessa associação.
5. **Professor e disciplina:** criar uma associação direta entre `Professor` e `Disciplina`.
6. **Matrícula:** representar `Matricula` como classe associativa entre `Aluno` e `Disciplina`, e não como uma sequência abaixo de `Disciplina`.
7. **Nota:** considerar que `nota` está relacionada ao desempenho do aluno na disciplina e, portanto, pode permanecer em `Matricula`.
8. **Nomes:** padronizar acentuação e convenção dos identificadores. Em código, pode-se usar `Matricula` sem acento e `lancarNota()`; em texto explicativo, usar “Matrícula” e “lançar nota”.
9. **Atributo do aluno:** avaliar renomear `matricula` para `numeroMatricula`, para diferenciá-lo da classe `Matricula`.

## 8. Próxima fase (Concluída)

A **Fase 02** foi iniciada e implementada com sucesso com foco no conceito de **Encapsulamento** e **Boas Práticas de Programação Orientada a Objetos** em linguagem Java.

### Checklist de continuidade (Fase 02)

- [x] Ler o enunciado completo da Fase 02.
- [x] Confirmar domínio (Escola) e manter consistência com o diagrama da Fase 01.
- [x] Preservar e implementar todas as 7 classes (`Pessoa`, `Aluno`, `Professor`, `Escola`, `Turma`, `Disciplina`, `Matricula`).
- [x] Aplicar modificadores de acesso `private` a todos os atributos para garantir o encapsulamento.
- [x] Implementar métodos *getters* e *setters* com validações defensivas nos *setters*.
- [x] Criar classe executável de teste (`Main.java`) demonstrando manipulação válida e bloqueio de dados inválidos.
- [x] Documentar detalhadamente a Fase 02.

---

## 9. Fase 02: Implementação e Encapsulamento

### 9.1 Enunciado da Atividade

> Nesta fase do projeto, você trabalhará com o conceito de encapsulamento para proteger os atributos de uma classe. O encapsulamento é um dos principais conceitos da programação orientada a objetos e tem como objetivo garantir que os dados da classe sejam acessados e manipulados de forma controlada, oferecendo maior segurança e evitando modificações não autorizadas.
>
> Nessa atividade, você deverá criar uma classe que utilize o encapsulamento para proteger seus atributos, além de garantir que o acesso e a manipulação desses dados sejam realizados por meio de métodos específicos, conhecidos como getters e setters. Além disso, será necessário aplicar boas práticas de desenvolvimento orientado a objetos, como a definição da visibilidade dos atributos (privados ou protegidos), validações e o uso adequado de métodos para o acesso aos dados.
>
> **Instruções:**
> 1. **Criação da Classe:** Crie uma classe com atributos privados, de modo que esses dados não possam ser acessados diretamente fora da classe. Utilize o modificador de acesso `private` para garantir a proteção dos dados.
> 2. **Métodos Getters e Setters:** Implemente os métodos getters (para acessar os valores dos atributos) e setters (para modificar os valores dos atributos). Os setters devem realizar as validações necessárias antes de permitir que o valor seja alterado.

---

### 9.2 Princípios e Boas Práticas Adotadas

1. **Visibilidade Privada (`private`):**
   Todos os campos de estado das classes foram declarados com visibilidade `private`. Nenhuma propriedade interna pode ser alterada diretamente por código cliente externo.

2. **Acesso Controlado (*Getters*):**
   A leitura dos dados ocorre exclusivamente por métodos de acesso. Para coleções de dados (ex.: listas de alunos, turmas e matrículas), são retornadas visões imutáveis (`Collections.unmodifiableList`), impedindo a adição/remoção indevida fora dos métodos de domínio da classe.

3. **Modificação e Validação Defensiva (*Setters*):**
   Cada método *setter* atua como uma barreira de integridade de domínio (invariante). Caso um valor viole as regras de negócio ou de tipagem lógica, é lançada uma exceção `IllegalArgumentException` com mensagem clara e descritiva.

4. **Princípio Fail-Fast no Construtor:**
   Os construtores parametrizados invocam os próprios *setters*, garantindo que nenhum objeto possa ser instanciado já em estado inválido.

---

### 9.3 Tabela de Classes e Regras de Validação Implementadas

| Classe | Atributo | Tipo | Modificador | Regra de Validação no Setter |
|---|---|---|---|---|
| `Pessoa` | `nome` | `String` | `private` | Não pode ser nulo, vazio ou conter menos de 2 caracteres. |
| `Pessoa` | `cpf` | `String` | `private` | Remove caracteres especiais e exige exatamente 11 dígitos numéricos. |
| `Pessoa` | `dataNascimento` | `LocalDate` | `private` | Não pode ser nula nem ser uma data futura. Suporta formatos `dd/MM/yyyy` e ISO. |
| `Aluno` | `matricula` | `String` | `private` | Não pode ser nula nem vazia. |
| `Aluno` | `email` | `String` | `private` | Validação por expressão regular (formato `usuario@dominio.com`). |
| `Professor` | `registro` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Professor` | `especialidade` | `String` | `private` | Não pode ser nula nem vazia. |
| `Escola` | `nome` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Escola` | `cnpj` | `String` | `private` | Remove pontuações e valida obrigatoriedade de exatamente 14 dígitos numéricos. |
| `Escola` | `endereco` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Turma` | `codigo` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Turma` | `nome` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Turma` | `ano` | `int` | `private` | Ano letivo deve estar entre 2000 e 2100. |
| `Turma` | `turno` | `String` | `private` | Não pode ser nulo nem vazio (ex.: Manhã, Tarde, Noite, Integral). |
| `Disciplina` | `codigo` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Disciplina` | `nome` | `String` | `private` | Não pode ser nulo nem vazio. |
| `Disciplina` | `cargaHoraria` | `int` | `private` | Deve ser obrigatoriamente maior que 0. |
| `Matricula` | `aluno` | `Aluno` | `private` | Não pode ser nulo. |
| `Matricula` | `disciplina` | `Disciplina` | `private` | Não pode ser nula. |
| `Matricula` | `status` | `String` | `private` | Deve pertencer aos status aceitos: `ATIVA`, `CANCELADA`, `CONCLUIDA`, `TRANCADA`. |
| `Matricula` | `nota` | `double` | `private` | Deve ser um valor no intervalo fechado de `0.0` a `10.0`. |

---

### 9.4 Estrutura de Arquivos Criada

```text
Desenvolvimento orientado a objeto/
├── bin/                          # Bytecodes compilados (.class)
├── src/
│   └── escola/
│       ├── Pessoa.java           # Superclasse base com nome, cpf e dataNascimento
│       ├── Aluno.java            # Subclasse com matricula, email, lista de matrículas
│       ├── Professor.java        # Subclasse com registro, especialidade e lancarNota()
│       ├── Escola.java           # Gestão institucional com turmas e alunos
│       ├── Turma.java            # Turma com alunos, disciplinas, ano e turno
│       ├── Disciplina.java       # Disciplina com código, nome, carga horária e professor
│       ├── Matricula.java        # Vínculo associativo entre Aluno e Disciplina com nota
│       └── Main.java             # Demonstração executável de uso e validação de dados
├── Desenvolvimento_POO.md        # Documentação completa do projeto
├── diagrama de classes (Escola).png
├── diagrama de classes (Escola).xlsx
└── diagrama de classes.mdj
```

---

### 9.5 Como Compilar e Executar

No terminal do sistema operacional (na raiz do projeto):

1. **Compilação das classes:**
   ```powershell
   javac -encoding UTF-8 -d bin src/escola/*.java
   ```

2. **Execução do programa de teste:**
   ```powershell
   java "-Dfile.encoding=UTF-8" -cp bin escola.Main
   ```

---

### 9.6 Resultados da Demonstração (`Main.java`)

A classe `Main` realiza duas baterias de testes:

1. **Fluxo Válido:**
   - Cria instâncias de `Escola`, `Professor`, `Aluno`, `Turma` e `Disciplina`.
   - Executa a matrícula do aluno na disciplina.
   - O professor ministra aula e lança a nota `9.5` para o aluno.
   - O aluno consulta seu boletim por meio do método `consultarNotas()`.

2. **Testes de Bloqueio por Encapsulamento:**
   - Tentativa de atribuir nota `15.0` ou `-2.5`: **Bloqueada** (`A nota deve estar entre 0.0 e 10.0`).
   - Tentativa de atribuir CPF `"123"`: **Bloqueada** (`O CPF deve conter exatamente 11 dígitos numéricos`).
   - Tentativa de e-mail sem formato válido: **Bloqueada** (`O e-mail informado é inválido`).
   - Tentativa de carga horária zero (`0h`): **Bloqueada** (`A carga horária deve ser maior que zero`).
   - Tentativa de nome em branco: **Bloqueada** (`O nome não pode ser nulo ou vazio`).
   - Tentativa de CNPJ incorreto: **Bloqueada** (`O CNPJ deve conter exatamente 14 dígitos numéricos`).
   - Tentativa de status desconhecido: **Bloqueada** (`Status inválido. Valores aceitos: [ATIVA, CANCELADA, ...]`).
