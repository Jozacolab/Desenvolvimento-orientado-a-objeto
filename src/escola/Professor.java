package escola;

import java.time.LocalDate;

/**
 * Subclasse de Pessoa que representa um Professor.
 * Protege seus atributos via encapsulamento (private) e validações nos setters.
 */
public class Professor extends Pessoa {
    private String registro;
    private String especialidade;

    public Professor() {
        super();
    }

    public Professor(String nome, String cpf, LocalDate dataNascimento, String registro, String especialidade) {
        super(nome, cpf, dataNascimento);
        setRegistro(registro);
        setEspecialidade(especialidade);
    }

    public Professor(String nome, String cpf, String dataNascimentoStr, String registro, String especialidade) {
        super(nome, cpf, dataNascimentoStr);
        setRegistro(registro);
        setEspecialidade(especialidade);
    }

    // Métodos de negócio definidos no diagrama
    public void ministrarAula() {
        System.out.println("[Professor] O(a) Prof(a). " + getNome() + " está ministrando aula de " + this.especialidade + ".");
    }

    public void lancarNota(Matricula matricula, double nota) {
        if (matricula == null) {
            throw new IllegalArgumentException("Erro de validação: Matrícula não pode ser nula para lançamento de nota.");
        }
        matricula.setNota(nota);
        System.out.println("[Professor] O(a) Prof(a). " + getNome() + 
                           " lançou a nota " + nota + 
                           " para o(a) aluno(a) " + matricula.getAluno().getNome() + 
                           " na disciplina " + matricula.getDisciplina().getNome());
    }

    // --- Getters e Setters com Encapsulamento ---

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        if (registro == null || registro.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O registro funcional do professor não pode ser nulo ou vazio.");
        }
        this.registro = registro.trim();
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: A especialidade do professor não pode ser nula ou vazia.");
        }
        this.especialidade = especialidade.trim();
    }

    @Override
    public String toString() {
        return String.format("%s | Registro: %s | Especialidade: %s", super.toString(), registro, especialidade);
    }
}
