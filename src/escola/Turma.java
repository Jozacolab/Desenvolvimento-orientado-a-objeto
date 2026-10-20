package escola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa uma Turma no sistema escolar.
 * Aplica encapsulamento com atributos privados, proteção de coleções e validação nos setters.
 */
public class Turma {
    private String codigo;
    private String nome;
    private int ano;
    private String turno;
    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Disciplina> disciplinas = new ArrayList<>();

    public Turma() {
    }

    public Turma(String codigo, String nome, int ano, String turno) {
        setCodigo(codigo);
        setNome(nome);
        setAno(ano);
        setTurno(turno);
    }

    // Métodos de negócio do diagrama
    public void adicionarAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Erro de validação: Aluno não pode ser nulo.");
        }
        if (this.alunos.contains(aluno)) {
            System.out.println("[Turma " + this.codigo + "] Aluno já presente na turma: " + aluno.getNome());
            return;
        }
        this.alunos.add(aluno);
        System.out.println("[Turma " + this.codigo + "] Aluno adicionado com sucesso: " + aluno.getNome());
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Erro de validação: Disciplina não pode ser nula.");
        }
        if (!this.disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
            System.out.println("[Turma " + this.codigo + "] Disciplina vinculada à turma: " + disciplina.getNome());
        }
    }

    // --- Getters e Setters com Encapsulamento ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O código da turma não pode ser nulo ou vazio.");
        }
        this.codigo = codigo.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O nome da turma não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if (ano < 2000 || ano > 2100) {
            throw new IllegalArgumentException("Erro de validação: O ano letivo informado (" + ano + ") é inválido. Esperado entre 2000 e 2100.");
        }
        this.ano = ano;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O turno não pode ser nulo ou vazio.");
        }
        this.turno = turno.trim();
    }

    public List<Aluno> getAlunos() {
        // Encapsulamento de coleções: retorna visão imutável
        return Collections.unmodifiableList(alunos);
    }

    public List<Disciplina> getDisciplinas() {
        return Collections.unmodifiableList(disciplinas);
    }

    @Override
    public String toString() {
        return String.format("Turma: %s [%s] | Ano: %d | Turno: %s | Qtd. Alunos: %d | Qtd. Disciplinas: %d",
                             nome, codigo, ano, turno, alunos.size(), disciplinas.size());
    }
}
