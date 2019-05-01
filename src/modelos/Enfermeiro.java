/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Enfermeiro, herda de Funcionario
*/
package modelos;

import java.util.Date;

public final class Enfermeiro extends Funcionario{
    
    private final String corem;
    private boolean chefe;
    
    // @Construtor de Enfermeiro.
    public Enfermeiro(String cpf, String nome, Data nascimento, boolean genero,
            float salario, Data dataAdmissao, String corem, boolean chefe) {
        super(cpf, nome, nascimento, genero, salario, dataAdmissao, "Enfermeiro(a)", null);
        this.corem = corem;
        this.chefe = chefe;
        
    }
    
    // @Confere se é enfermeiro chefe.
    // @Return: boolean.
    public boolean isChefe () {
        return chefe;
    }
    
    // @Param: boolean.
    public void setChefe(boolean c){
        this.chefe = c;
    }
    
    // @Param: String.
    public String getCorem () {
        return corem;
    }
    
}
