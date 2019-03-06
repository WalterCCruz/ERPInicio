package br.com.wcruz.erp.DTO;

import br.com.wcruz.erp.domain.Fornecedor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class FornecedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @Email(message = "email inválido")
    @NotEmpty(message = "Preenchimento obrigatorio")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatorio")
    private String cnpj;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public FornecedorDTO(){}


    public FornecedorDTO(Fornecedor fornecedor){
        id = fornecedor.getId();
        nome = fornecedor.getNome();
        email = fornecedor.getEmail();
        cnpj = fornecedor.getCnpj();

    }
}
