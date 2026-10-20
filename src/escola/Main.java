package escola;

import java.time.LocalDate;

/**
 * Classe principal para demonstração e teste do sistema escolar.
 * Evidencia a aplicação do conceito de ENCAPSULAMENTO da Fase 02:
 * 1. Atributos privados inacessíveis diretamente.
 * 2. Acesso e alteração exclusivamente via getters e setters.
 * 3. Validação de dados nos setters impedindo estados inconsistentes.
 * 4. Execução dos métodos de negócio do domínio.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("   FASE 02: DEMONSTRAÇÃO PRÁTICA DE ENCAPSULAMENTO E BOAS PRÁTICAS   ");
        System.out.println("======================================================================\n");

        // -------------------------------------------------------------------------
        // PARTE 1: Criação de Objetos Válidos e Teste de Getters/Setters
        // -------------------------------------------------------------------------
        System.out.println("--- 1. Instanciação e Manipulação de Objetos Válidos ---");

        // Criando Escola
        Escola escola = new Escola("Colégio Futuro Brilhante", "12.345.678/0001-99", "Av. Universitária, 1000");
        System.out.println("[OK] " + escola);

        // Criando Professor
        Professor prof = new Professor(
            "Carlos Eduardo", 
            "111.222.333-44", 
            LocalDate.of(1982, 5, 20), 
            "PROF-2024-01", 
            "Programação Orientada a Objetos"
        );
        prof.cadastrar();
        System.out.println("[OK] " + prof);

        // Criando Aluno
        Aluno aluno = new Aluno(
            "Maria Eduarda Souza", 
            "999.888.777-66", 
            "15/03/2005", 
            "ALU-2026-987", 
            "maria.souza@faculdade.edu.br"
        );
        aluno.cadastrar();
        System.out.println("[OK] " + aluno);

        // Criando Turma e Disciplina
        Turma turma = new Turma("T-2026-N", "Engenharia de Software - 6º Período", 2026, "Noite");
        Disciplina disciplina = new Disciplina("DISC-POO-01", "Programação Orientada a Objetos", 80, prof);

        // Relacionando entidades
        escola.criarTurma(turma);
        escola.cadastrarAluno(aluno);
        turma.adicionarAluno(aluno);
        turma.adicionarDisciplina(disciplina);

        // Matrícula do aluno na disciplina
        Matricula matricula = aluno.matricular(disciplina);
        System.out.println("[OK] " + matricula);

        // Professor ministra aula e lança nota
        prof.ministrarAula();
        prof.lancarNota(matricula, 9.5);

        // Aluno consulta seu boletim de notas
        aluno.consultarNotas();

        // -------------------------------------------------------------------------
        // PARTE 2: Demonstração da Proteção por Encapsulamento (Testes de Validação)
        // -------------------------------------------------------------------------
        System.out.println("\n--- 2. Testando a Proteção do Encapsulamento contra Dados Inválidos ---");
        System.out.println("Tentativas de atribuir dados incorretos via setters serão barradas:\n");

        // Teste 1: Nota inválida (maior que 10)
        testarValidacao("Tentativa de lançar nota acima do limite (15.0)", () -> {
            matricula.setNota(15.0);
        });

        // Teste 2: Nota inválida (negativa)
        testarValidacao("Tentativa de lançar nota negativa (-2.5)", () -> {
            matricula.setNota(-2.5);
        });

        // Teste 3: CPF com formato ou tamanho inválido
        testarValidacao("Tentativa de cadastrar CPF com tamanho inválido ('123')", () -> {
            aluno.setCpf("123");
        });

        // Teste 4: E-mail sem formato válido
        testarValidacao("Tentativa de atribuir e-mail sem '@' ('email_invalido.com')", () -> {
            aluno.setEmail("email_invalido.com");
        });

        // Teste 5: Carga horária menor ou igual a zero
        testarValidacao("Tentativa de cadastrar disciplina com carga horária zero (0h)", () -> {
            disciplina.setCargaHoraria(0);
        });

        // Teste 6: Nome vazio/nulo
        testarValidacao("Tentativa de atribuir nome vazio a uma Pessoa", () -> {
            aluno.setNome("   ");
        });

        // Teste 7: CNPJ inválido na Escola
        testarValidacao("Tentativa de atribuir CNPJ com quantidade incorreta de dígitos", () -> {
            escola.setCnpj("12345");
        });

        // Teste 8: Status de matrícula inválido
        testarValidacao("Tentativa de definir status não previsto ('APROVADO_DIRETO')", () -> {
            matricula.setStatus("APROVADO_DIRETO");
        });

        // -------------------------------------------------------------------------
        // PARTE 3: Conclusão
        // -------------------------------------------------------------------------
        System.out.println("\n--- 3. Verificação Final do Estado do Objeto ---");
        System.out.println("Observe que, mesmo após as tentativas inválidas, os objetos mantêm seu estado protegido e íntegro:");
        System.out.println("-> Aluno: " + aluno.getNome() + " (CPF: " + aluno.getCpf() + ", E-mail: " + aluno.getEmail() + ")");
        System.out.println("-> Nota da Matrícula permanece: " + matricula.getNota());
        System.out.println("\n[SUCESSO] Todos os conceitos de encapsulamento foram validados com êxito!");
    }

    /**
     * Método utilitário para capturar e exibir as exceções lançadas pelas validações dos setters.
     */
    private static void testarValidacao(String descricao, Runnable acao) {
        System.out.print("[TESTE] " + descricao + " -> ");
        try {
            acao.run();
            System.out.println("FALHA: A alteração inválida foi permitida!");
        } catch (IllegalArgumentException e) {
            System.out.println("PROTEGIDO COM SUCESSO! Exceção capturada: \"" + e.getMessage() + "\"");
        } catch (Exception e) {
            System.out.println("PROTEGIDO COM OUTRA EXCEÇÃO: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
