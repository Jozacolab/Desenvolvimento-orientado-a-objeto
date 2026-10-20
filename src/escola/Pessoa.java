package escola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Superclasse que representa uma Pessoa no sistema escolar.
 * Aplica encapsulamento com atributos privados, getters e setters com validação.
 */
public class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    // Construtor padrão
    public Pessoa() {
    }

    // Construtor parametrizado que utiliza os setters para garantir a validação inicial
    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
    }

    // Construtor de conveniência aceitando data como String (formato dd/MM/yyyy ou yyyy-MM-dd)
    public Pessoa(String nome, String cpf, String dataNascimentoStr) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimentoStr);
    }

    // Métodos de negócio definidos no diagrama
    public void cadastrar() {
        System.out.println("[Pessoa] Cadastro realizado com sucesso para: " + this.nome);
    }

    public void atualizar() {
        System.out.println("[Pessoa] Dados atualizados com sucesso para: " + this.nome);
    }

    // --- Getters e Setters com Encapsulamento e Validação ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: O nome não pode ser nulo ou vazio.");
        }
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Erro de validação: O nome deve conter ao menos 2 caracteres.");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("Erro de validação: O CPF não pode ser nulo.");
        }
        // Remove caracteres não numéricos
        String apenasDigitos = cpf.replaceAll("\\D", "");
        if (apenasDigitos.length() != 11) {
            throw new IllegalArgumentException("Erro de validação: O CPF deve conter exatamente 11 dígitos numéricos.");
        }
        this.cpf = apenasDigitos;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Erro de validação: A data de nascimento não pode ser nula.");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Erro de validação: A data de nascimento não pode ser uma data futura.");
        }
        this.dataNascimento = dataNascimento;
    }

    public void setDataNascimento(String dataNascimentoStr) {
        if (dataNascimentoStr == null || dataNascimentoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro de validação: A data de nascimento não pode ser vazia.");
        }
        try {
            if (dataNascimentoStr.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                setDataNascimento(LocalDate.parse(dataNascimentoStr.trim(), formatter));
            } else {
                setDataNascimento(LocalDate.parse(dataNascimentoStr.trim()));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Erro de validação: Formato de data inválido. Use dd/MM/yyyy ou yyyy-MM-dd.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = (dataNascimento != null) ? dataNascimento.format(formatter) : "Não informada";
        return String.format("Nome: %s | CPF: %s | Data Nasc.: %s", nome, cpf, dataFormatada);
    }
}
