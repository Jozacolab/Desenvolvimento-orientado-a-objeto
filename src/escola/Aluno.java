package escola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Subclasse de Pessoa que representa um Aluno.
 * Protege seus atributos via encapsulamento (private) e validações nos setters.
 */
public class Aluno extends Pessoa {
    private String matricula;
    private String email;
    private final List<Matricula> matriculas = new ArrayList<>();

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Aluno() {
        super();
    }

    public Aluno(String nome, String cpf, LocalDate dataNascimento, String matricula, String email) {
        super(nome, cpf, dataNascimento);
        setMatricula(matricula);
        setEmail(email);
    }

    public Aluno(String nome, String cpf, String dataNascimentoStr, String matricula, String email) {
        super(nome, cpf, dataNascimentoStr);
        setMatricula(matricula);
        setEmail(email);
    }

    // Métodos de negócio definidos no diagrama
    public Matricula matricular(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Erro de validação: A disciplina para matrícula não pode ser nula.");
        }
        Matricula novaMatricula = new Matricula(this, disciplina);
        System.out.println("[Aluno] " + getNome() + " matriculado na disciplina: " + disciplina.getNome());
        return novaMatricula;
    }

    public void matricular() {
        System.out.println("[Aluno] Processo de matrícula iniciado para o aluno: " + getNome());
    }

    public void consultarNotas() {
        System.out.println("=== Boletim de Notas: " + getNome() + " (Matrícula: " + this.matricula + ") ===");
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula/disciplina registrada no momento.");
            return;
        }
        for (Matricula m : matriculas) {
            System.out.println("Disciplina: " + m.getDisciplina().getNome() + 
                               " | Nota: " + m.getNota() + 
                               " | Status: " + m.getStatus());
        }
        System.out.println("=========================================================");
    }

    // --- Getters e Setters com Encapsulamento ---

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O código de matrícula do aluno não pode ser nulo ou vazio.");
        }
        this.matricula = matricula.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O e-mail não pode ser nulo ou vazio.");
        }
        String emailTrimmed = email.trim();
        if (!EMAIL_PATTERN.matcher(emailTrimmed).matches()) {
            throw new IllegalArgumentException("Erro de validação: O e-mail informado ('" + email + "') é inválido.");
        }
        this.email = emailTrimmed;
    }

    public List<Matricula> getMatriculas() {
        // Retorna lista imutável para não permitir manipulação direta sem passar pelos métodos da classe
        return Collections.unmodifiableList(matriculas);
    }

    protected void adicionarMatriculaInterna(Matricula matricula) {
        if (matricula != null && !this.matriculas.contains(matricula)) {
            this.matriculas.add(matricula);
        }
    }

    @Override
    public String toString() {
        return String.format("%s | Matrícula: %s | E-mail: %s", super.toString(), matricula, email);
    }
}
