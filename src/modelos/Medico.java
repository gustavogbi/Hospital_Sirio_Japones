/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Medico que herda de Funcionario
*/
package modelos;

import java.util.Date;

public class Medico extends Funcionario {
    
    protected String especialidade;
    protected String crm;
    
    // @Construtor de médico.
    public Medico(String cpf, String nome, Data nascimento, boolean genero,
            float salario, Data dataAdmissao, String especialidade, String crm) {
        super(cpf, nome, nascimento, genero, salario, dataAdmissao, "Medico(a)", null);
        this.especialidade = especialidade;
        this.crm = crm;
        
    }

    // @Return: String.
    public String getEspecialidade() {
        return especialidade;
    }

    /// @Return String.
    public String getCrm() {
        return crm;
    }
    
    
}