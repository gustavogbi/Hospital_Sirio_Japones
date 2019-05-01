/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Controlador do Hospital
*/
package controladores;

import java.util.ArrayList;
import java.util.List;
import modelos.*;

public class ControleHospital {
    protected List<Consulta> consultas;
    protected List<Pessoa> pessoas;
    
    //@Constrututor de ControleHospital
    public ControleHospital() {
        this.consultas = new ArrayList<Consulta>();
        this.pessoas = new ArrayList<Pessoa>();
    }
    
    
    /* @adiciona uma pessoa
        @Param: Pessoa pessoa
    */
    public void addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
        
    }
        
    // @adiciona consulta
    //@Param: Consulta consulta
    public void addConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
    
    // @Lista de Consulta
    // @Param: String n
    public void listaConsultas(String n) {
        if (consultas.isEmpty()) System.out.println("Nenhuma consulta realizada ate agora.");
        
        for(Consulta aux : consultas){
            if(aux.getCorem().equals(n)
            || aux.getCrm().equals(n)
            || aux.getDocSaude().equals(n)){
                aux.print();
            }
        }
    }
    
    // @busca uma Pessoa pelo cpf, nome, ou doc especifico
    // @Param: String id
    // @Return: Pessoa,throws Exception
    public Pessoa buscaPessoa(String id) throws Exception {
        for (Pessoa aux : pessoas) {
            if(id.equals(aux.getCpf()) || id.equals(aux.getNome())) {
                return aux;
            }
            if(aux instanceof Paciente) {
                if (((Paciente) aux).getDocSaude().equals(id))
                    return aux;
            } 
            if(aux instanceof Enfermeiro)
                if (((Enfermeiro) aux).getCorem().equals(id))
                    return aux;
            if(aux instanceof Medico)
                if (((Medico) aux).getCrm().equals(id))
                    return aux;
            if(aux instanceof Funcionario) {
                if (((Funcionario) aux).getCodFun() != null && ((Funcionario) aux).getCodFun().equals(id)) {
                    return aux;
                }
            }
        }
        System.out.println("Nenhuma pessoa encontrada!");
        throw new Exception("");
    }
    
    // @Verifica se possui no mínimo um médico, um enfermeiro e um paciente.
    // @Return: boolean.
    public boolean podeConsultar()  {
        int contPac = 0;
        int contEnf = 0;
        int contMed = 0;
        for (Pessoa aux : pessoas) {
 
            if(aux instanceof Paciente) {
               ++contPac;
            } 
            else if(aux instanceof Enfermeiro) {
                ++contEnf;
            }               
            else if(aux instanceof Medico) {
                ++contMed;
            }

        }
        if ((contPac > 0) && (contEnf > 0) && (contMed > 0))
            return true;
        else
            return false;
        
    }
    
    // @Remove da lista de Pessoa uma pessoa.
    // @Param: String n.
    // @Return: boolean.
    public boolean excluir(String n) {
        try {
            pessoas.remove(buscaPessoa(n));
            return true;
            
        } catch (Exception e) {
            System.out.println("Não existe esta pessoa no hospital");
        }
        return false;
    }

    // @Return: Uma lista de consulta.
    public List<Consulta> getConsultas() {
        return consultas;
    }

    // @Return: Uma lista de pessoa.
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
   
    // @Return: Int->A quantidade de médicos.
    public int getQntMed () {
        int qntMed = 0;
        for (Pessoa aux : pessoas) {
            if(aux instanceof Medico)
                ++qntMed;

        }
        return qntMed;
    }
    
    // @Return: Int->Quantidade de Enfermeiros.
    public int getQntEnf () {
        int qntEnf = 0;
        for (Pessoa aux : pessoas) {
            
            if(aux instanceof Enfermeiro)
                ++qntEnf;

        }
        return qntEnf;
    }
    
    // @Return; Int->Quantidade de Paciente.
    public int getQntPac () {
        int qntPac = 0;
        for (Pessoa aux : pessoas) {
 
            if(aux instanceof Paciente)
               ++qntPac;

        }
        return qntPac;
    }
    
    // @Imprimi todas as pessoas que estão na lista.
    public void listaTodasPessoas ()  {
        if (pessoas.isEmpty()) System.out.println("Ninguem cadastrado ainda");
        for (Pessoa aux : pessoas) {
            System.out.println("Nome : "+ aux.getNome());
            System.out.println("Cpf : "+ aux.getCpf());
            System.out.println("Nascimento : "+ aux.getNascimento());
            System.out.println("Genero : "+ aux.getGenero());
            
            if(aux instanceof Paciente) {
                System.out.println("Documento de saude: " + ((Paciente) aux).getDocSaude());
                System.out.println("Telefone do paciente: " + ((Paciente) aux).getTelefone());
                System.out.println("Endereco do paciente: " + ((Paciente) aux).getEndereco());
            }
            else {
                if(((Funcionario) aux).getCodFun() != null) {
                    System.out.println("Codigo do funcionario: " + ((Funcionario) aux).getCodFun());
                }
                System.out.println("Salario: " + ((Funcionario) aux).getSalario());
                System.out.println("Data de admissao: " + ((Funcionario) aux).getDataAdmissao());
                System.out.println("Cargo: " + ((Funcionario) aux).getTipoFunc());
                
                if(aux instanceof Enfermeiro) {
                    System.out.println("COREN: " + ((Enfermeiro) aux).getCorem());
                    System.out.print("Enfermeiro: ");
                    if (((Enfermeiro) aux).isChefe() == true) {
                        System.out.println("Chefe");
                    }
                    else {
                        System.out.println("Padrao");
                    }
                } else if (aux instanceof Medico) {
                System.out.println("Area de especialização: " + ((Medico) aux).getEspecialidade());
                System.out.println("CRM: " + ((Medico) aux).getCrm());
                }
            }
            System.out.println("---------------------------");
        }
       
    }
    
    // @Mostra todas as consultas na consulta.
    public void listaTodasConsultas ()  {
        if (consultas.isEmpty()) System.out.println("Nenhuma consula realizada ate agora.");
        for (Consulta aux : consultas) {
            aux.print();
        }
        
    }    
    
}