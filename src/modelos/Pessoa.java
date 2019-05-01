/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Pessoa (classe base)
*/
package modelos;
import java.util.Date;

public abstract class Pessoa {
    
    protected String cpf;
    protected String nome;
    protected Data nascimento;
    protected boolean genero;

    // @Constrututor de Pessoa.
    public Pessoa (String cpf, String nome, Data nascimento, boolean genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.genero = genero;
    }

    // @Return: String. 
    public String getCpf() {
        return cpf;
    }

    // @Return: Data.
    public Data getNascimento() {
        return nascimento;
    }

    // @Return: String.
    public String getNome() {
        return nome;
    }
   
    // @Return: String-> Retorna o genero como string.
    public String getGenero () {
        if (genero)
              return "Masculino";
        return "Feminino";
    }
}
