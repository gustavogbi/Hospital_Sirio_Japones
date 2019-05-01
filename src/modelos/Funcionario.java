/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Funcionario que herda de Pessoa
*/
package modelos;

public class Funcionario extends Pessoa {
    
    protected String codFun;
    protected float salario;
    protected Data dataAdmissao;
    protected String tipoFunc;
    
    // Constrututor de Funcionario.
    public Funcionario(String cpf, String nome, Data nascimento, boolean genero,
            float salario, Data dataAdmissao, String tipoFunc, String codFun) {
        super(cpf, nome, nascimento, genero);
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.tipoFunc = tipoFunc;
        this.codFun = codFun;
    }

    // @Return: String.
    public String getCodFun() {
        return codFun;
    }

    // @Return: float.
    public float getSalario() {
        return salario;
    }

    // @Return: Data.
    public Data getDataAdmissao() {
        return dataAdmissao;
    }

    // @Return: String.
    public String getTipoFunc() {
        return tipoFunc;
    }
    
    
}
