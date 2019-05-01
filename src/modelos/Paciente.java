/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Paciente que herda de Pessoa
*/
package modelos;

import java.util.Date;

public final class Paciente extends Pessoa{
    private String docSaude;
    private String telefone;
    private String endereco;
    
    // Construrtor de Paciente.
    public Paciente(String cpf, String nome, Data nascimento, boolean genero,
            String docSaude, String telefone, String endereco) {
        super(cpf, nome, nascimento, genero);
        this.docSaude = docSaude;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    // @Return: String.
    public String getDocSaude() {
        return docSaude;
    }

    // @Return: String.
    public String getTelefone() {
        return telefone;
    }

    // @Return: String.
    public String getEndereco() {
        return endereco;
    }
    
}
