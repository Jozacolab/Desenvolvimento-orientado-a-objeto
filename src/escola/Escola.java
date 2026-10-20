package escola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa a Instituição de Ensino (Escola).
 * Protege seus atributos via encapsulamento (private) e validações de CNPJ e dados cadastrais.
 */
public class Escola {
    private String nome;
    private String cnpj;
    private String endereco;
    private final List<Turma> turmas = new ArrayList<>();
    private final List<Aluno> alunos = new ArrayList<>();

    public Escola() {
    }

    public Escola(String nome, String cnpj, String endereco) {
        setNome(nome);
        setCnpj(cnpj);
        setEndereco(endereco);
    }

    // Métodos de negócio do diagrama
    public void cadastrarAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Erro de validação: Aluno não pode ser nulo.");
        }
        if (!this.alunos.contains(aluno)) {
            this.alunos.add(aluno);
            System.out.println("[Escola " + this.nome + "] Aluno cadastrado: " + aluno.getNome());
        }
    }

    public void criarTurma(Turma turma) {
        if (turma == null) {
            throw new IllegalArgumentException("Erro de validação: Turma não pode ser nula.");
        }
        if (!this.turmas.contains(turma)) {
            this.turmas.add(turma);
            System.out.println("[Escola " + this.nome + "] Turma criada: " + turma.getNome() + " (" + turma.getCodigo() + ")");
        }
    }

    // --- Getters e Setters com Encapsulamento ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O nome da escola não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("Erro de validação: O CNPJ não pode ser nulo.");
        }
        String apenasDigitos = cnpj.replaceAll("\\D", "");
        if (apenasDigitos.length() != 14) {
            throw new IllegalArgumentException("Erro de validação: O CNPJ deve conter exatamente 14 dígitos numéricos.");
        }
        this.cnpj = apenasDigitos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O endereço não pode ser nulo ou vazio.");
        }
        this.endereco = endereco.trim();
    }

    public List<Turma> getTurmas() {
        return Collections.unmodifiableList(turmas);
    }

    public List<Aluno> getAlunos() {
        return Collections.unmodifiableList(alunos);
    }

    @Override
    public String toString() {
        return String.format("Escola: %s | CNPJ: %s | Endereço: %s | Qtd. Turmas: %d",
                             nome, cnpj, endereco, turmas.size());
    }
}
