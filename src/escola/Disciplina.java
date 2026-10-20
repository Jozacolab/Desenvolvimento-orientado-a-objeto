package escola;

/**
 * Representa uma Disciplina oferecida pela instituição de ensino.
 * Aplica encapsulamento com atributos privados e validação de carga horária e nomes.
 */
public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;

    public Disciplina() {
    }

    public Disciplina(String codigo, String nome, int cargaHoraria) {
        setCodigo(codigo);
        setNome(nome);
        setCargaHoraria(cargaHoraria);
    }

    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor) {
        setCodigo(codigo);
        setNome(nome);
        setCargaHoraria(cargaHoraria);
        setProfessor(professor);
    }

    // Método do diagrama
    public void cadastrarNota(Matricula matricula, double nota) {
        if (matricula == null) {
            throw new IllegalArgumentException("Erro de validação: Matrícula inválida para cadastro de nota.");
        }
        matricula.setNota(nota);
        System.out.println("[Disciplina " + this.nome + "] Nota " + nota + " cadastrada para o aluno " + matricula.getAluno().getNome());
    }

    // --- Getters e Setters com Encapsulamento ---

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O código da disciplina não pode ser nulo ou vazio.");
        }
        this.codigo = codigo.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O nome da disciplina não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Erro de validação: A carga horária da disciplina deve ser maior que zero.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        String profStr = (professor != null) ? professor.getNome() : "Não atribuído";
        return String.format("Disciplina: %s [%s] | Carga Horária: %dh | Professor: %s", 
                             nome, codigo, cargaHoraria, profStr);
    }
}
