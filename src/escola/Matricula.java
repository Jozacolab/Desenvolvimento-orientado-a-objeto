package escola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Representa o vínculo de Matrícula entre um Aluno e uma Disciplina.
 * Demonstra encapsulamento protegendo notas, datas e status através de validações nos setters.
 */
public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private LocalDate data;
    private String status;
    private double nota;

    private static final List<String> STATUS_PERMITIDOS = 
        Arrays.asList("ATIVA", "CANCELADA", "CONCLUIDA", "TRANCADA");

    public Matricula() {
        this.data = LocalDate.now();
        this.status = "ATIVA";
        this.nota = 0.0;
    }

    public Matricula(Aluno aluno, Disciplina disciplina) {
        setAluno(aluno);
        setDisciplina(disciplina);
        this.data = LocalDate.now();
        this.status = "ATIVA";
        this.nota = 0.0;
        // Vincula a matrícula ao aluno internamente
        aluno.adicionarMatriculaInterna(this);
    }

    public Matricula(Aluno aluno, Disciplina disciplina, LocalDate data, String status, double nota) {
        setAluno(aluno);
        setDisciplina(disciplina);
        setData(data);
        setStatus(status);
        setNota(nota);
        aluno.adicionarMatriculaInterna(this);
    }

    // Métodos de negócio do diagrama
    public void efetivar() {
        this.status = "ATIVA";
        System.out.println("[Matricula] Matrícula de " + (aluno != null ? aluno.getNome() : "") + 
                           " na disciplina " + (disciplina != null ? disciplina.getNome() : "") + " foi EFETIVADA.");
    }

    public void cancelar() {
        this.status = "CANCELADA";
        System.out.println("[Matricula] Matrícula de " + (aluno != null ? aluno.getNome() : "") + 
                           " na disciplina " + (disciplina != null ? disciplina.getNome() : "") + " foi CANCELADA.");
    }

    // --- Getters e Setters com Encapsulamento ---

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Erro de validação: O aluno não pode ser nulo na matrícula.");
        }
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Erro de validação: A disciplina não pode ser nula na matrícula.");
        }
        this.disciplina = disciplina;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Erro de validação: A data da matrícula não pode ser nula.");
        }
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O status da matrícula não pode ser nulo ou vazio.");
        }
        String statusUpper = status.trim().toUpperCase();
        if (!STATUS_PERMITIDOS.contains(statusUpper)) {
            throw new IllegalArgumentException("Erro de validação: Status inválido ('" + status + 
                "'). Valores aceitos: " + STATUS_PERMITIDOS);
        }
        this.status = statusUpper;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        // Validação fundamental de regra de negócio
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("Erro de validação: A nota deve estar entre 0.0 e 10.0. Valor informado: " + nota);
        }
        this.nota = nota;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = (data != null) ? data.format(formatter) : "N/D";
        String alunoNome = (aluno != null) ? aluno.getNome() : "Sem aluno";
        String discNome = (disciplina != null) ? disciplina.getNome() : "Sem disciplina";
        return String.format("Matrícula [%s] Aluno: %s | Disciplina: %s | Data: %s | Nota: %.1f",
                             status, alunoNome, discNome, dataFormatada, nota);
    }
}
