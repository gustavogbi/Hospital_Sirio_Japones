/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Visao : Classe que imprimi e interação com o menu principal. 
*/
package visao;

import controladores.ControleHospital;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.*;

public class Hospital_Sirio_Japones {
    private static Scanner scanner;
    private static ControleHospital controle = new ControleHospital();
    private static final String separador = ","; //escolhe o separador pros arquivos aqui

    // @Programa principal.
    // @Param: String[] args.
    public static void main(String[] args) {
        Scanner scan;
        Consulta novaConsulta;
        System.out.println("começando...");
        String n;
        
        int opcoes = 0;
        do {
            menu();
            scan = new Scanner(System.in);
            opcoes = scan.nextInt();
            switch(opcoes) {
                case 1: {
                    cadastroPessoas();
                    break;
                }
                case 2: {
                    if (controle.podeConsultar() == true) {
                        if (cadastroConsulta() == true)
                            System.out.println("Consulta criada com sucesso!");
                        else System.out.println("Dados inválidos! Consulta nao realizada");
                    }
                    else {
                        System.out.println("A consulta nao pode ser realizada, pois está faltando: ");
                        System.out.println("ao menos um: ");
                        if (controle.getQntPac() < 1)
                            System.out.println("Paciente");
                        if (controle.getQntMed() < 1)
                            System.out.println("Médico");
                        if (controle.getQntEnf() < 1)
                            System.out.println("Enfermeiro");
                    }
                    break;
                }
                case 3: {
                    controle.listaTodasPessoas();
                    break;
                }
                case 4: {
                    System.out.println("Digite o CRM, COREN ou Documento de saude da pessoa: ");
                    n = scan.next();
                    controle.listaConsultas(n);
                    break;
                }
                case 5: {
                    System.out.println("Digite o Nome, CPF, CRM, COREN ou documento de saude da pessoa: ");
                    n = scan.next();
                try {
                    if ((controle.excluir(n) == true)) {
                        System.out.println("Pessoa excluida com sucesso");
                    }
                } catch (Exception e) {
                    System.out.println("Nao foi possivel excluir a pessoa");;
                }
                    break;
                }
                case 6: {
                    System.out.println("Digite o Nome, CPF, CRM, COREN ou documento de saude da pessoa: ");
                    n = scan.next();
                    try {
                        Pessoa p = controle.buscaPessoa(n);
                        System.out.println("Nome: " + p.getNome());
                        System.out.println("CPF: " + p.getCpf());
                        System.out.println("Data de nascimento: " + p.getNascimento().toString());
                        System.out.println(p.getGenero());
                        
                        if(p instanceof Paciente) {
                            System.out.println("Documento de saude: " + ((Paciente) p).getDocSaude());
                            System.out.println("Telefone do paciente: " + ((Paciente) p).getTelefone());
                            System.out.println("Endereco do paciente: " + ((Paciente) p).getEndereco());
                        }
                        else if(p instanceof Enfermeiro) {
                            System.out.println("COREN: " + ((Enfermeiro) p).getCorem());
                            System.out.print("Enfermeiro: ");
                            if (((Enfermeiro) p).isChefe()) {
                                System.out.println("Chefe");
                            }
                            else {
                                System.out.println("Padrao");
                            }
                        }
                        else if(p instanceof Medico) {
                            System.out.println("Area de especialização: " + ((Medico) p).getEspecialidade());
                            System.out.println("CRM: " + ((Medico) p).getCrm());
                        }
                        else {
                            System.out.println("Codigo do funcionario: " + ((Funcionario) p).getCodFun());
                            System.out.println("Salario: " + ((Funcionario) p).getSalario());
                            System.out.println("Data de admissao: " + ((Funcionario) p).getDataAdmissao());
                            System.out.println("Cargo: " + ((Funcionario) p).getTipoFunc());
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Busca nao sucedida");
                    }
                    break;
                }
                case 7: {
                    controle.listaTodasConsultas();
                    break;
                }
                case 8: {
                try {
                    salvaArquivos();
                } catch (IOException e) {
                    System.out.println("Nao foi possivel salvar os arquivos");
                }
                    break;
                }
                case 9: {
                    abreArquivos();
                    break;
                }
                case 0: {
                    System.out.println("Operacoes finalizadas");
                    break;
                }
                default: {
                    System.out.println("Opcao invalida! Tente novamente");
                    break;
                }
            }
        } while(opcoes != 0);
    }
    
    // @Abre todas os arquivos.
    private static void abreArquivos() {
        abreMedicos();
        abreEnfermeiros();
        abreFuncionarios();
        abrePacientes();
        abreConsultas(); 
    }
    
    // @Return: throws IOException.
    private static void salvaArquivos() throws IOException {
        Scanner scan = new Scanner(System.in);
        String nomeArq;
        FileWriter fw;
        File arq;
        
        // Medicos
        System.out.println("Escolha um nome para salvar a lista de Medicos: ");
        nomeArq = scan.nextLine();
        arq = new File("src/arquivosFonte/"+nomeArq+".txt");
        arq.delete(); // pra assegurar q nao tenha nada no arq
        fw = new FileWriter("src/arquivosFonte/"+nomeArq+".txt");
        
        List<Pessoa> pessoas = controle.getPessoas();
        for (Pessoa aux : pessoas) {
            if(aux instanceof Medico) {
                fw.write(aux.getCpf());
                fw.write(separador);
                fw.write(aux.getNome());
                fw.write(separador);
                fw.write(aux.getNascimento().toString());
                fw.write(separador);
                fw.write(aux.getGenero());
                fw.write(separador);
                fw.write(String.valueOf(((Medico) aux).getSalario()));
                fw.write(separador);
                fw.write(((Medico) aux).getDataAdmissao().toString());
                fw.write(separador);
                fw.write(((Medico) aux).getEspecialidade());
                fw.write(separador);
                fw.write(((Medico) aux).getCrm());
                fw.write("\n");
            }
        }
        fw.close();
        
        // Enfermeiros
        System.out.println("Escolha um nome para salvar a lista de Enfermeiros: ");
        nomeArq = scan.nextLine();
        arq = new File("src/arquivosFonte/"+nomeArq+".txt");
        arq.delete(); // pra assegurar q nao tenha nada no arq
        fw = new FileWriter("src/arquivosFonte/"+nomeArq+".txt");
        
        for (Pessoa aux : pessoas) {
            if(aux instanceof Enfermeiro) {
                fw.write(aux.getCpf());
                fw.write(separador);
                fw.write(aux.getNome());
                fw.write(separador);
                fw.write(aux.getNascimento().toString());
                fw.write(separador);
                fw.write(aux.getGenero());
                fw.write(separador);
                fw.write(String.valueOf(((Enfermeiro) aux).getSalario()));
                fw.write(separador);
                fw.write(((Enfermeiro) aux).getDataAdmissao().toString());
                fw.write(separador);
                fw.write(((Enfermeiro) aux).getCorem());
                fw.write(separador);
                String eh_chefe = "nao";
                if(((Enfermeiro) aux).isChefe()) eh_chefe = "chefe"; 
                fw.write(eh_chefe);
                fw.write("\n");
            }
        }
        fw.close();
        
        // Funcionarios
        System.out.println("Escolha um nome para salvar a lista de Funcionarios: ");
        nomeArq = scan.nextLine();
        arq = new File("src/arquivosFonte/"+nomeArq+".txt");
        arq.delete(); // pra assegurar q nao tenha nada no arq
        fw = new FileWriter("src/arquivosFonte/"+nomeArq+".txt");
        
        for (Pessoa aux : pessoas) {
            if((aux instanceof Funcionario) && !(aux instanceof Medico) && !(aux instanceof Enfermeiro)) {
                fw.write(aux.getCpf());
                fw.write(separador);
                fw.write(aux.getNome());
                fw.write(separador);
                fw.write(aux.getNascimento().toString());
                fw.write(separador);
                fw.write(aux.getGenero());
                fw.write(separador);
                fw.write(String.valueOf(((Funcionario) aux).getSalario()));
                fw.write(separador);
                fw.write(((Funcionario) aux).getDataAdmissao().toString());
                fw.write(separador);
                fw.write(((Funcionario) aux).getTipoFunc());
                fw.write(separador);
                fw.write(((Funcionario) aux).getCodFun());
                fw.write("\n");
            }
        }
        fw.close();
        
        // Pacientes
        System.out.println("Escolha um nome para salvar a lista de Pacientes: ");
        nomeArq = scan.nextLine();
        arq = new File("src/arquivosFonte/"+nomeArq+".txt");
        arq.delete(); // pra assegurar q nao tenha nada no arq
        fw = new FileWriter("src/arquivosFonte/"+nomeArq+".txt");
        
        for (Pessoa aux : pessoas) {
            if(aux instanceof Paciente) {
                fw.write(aux.getCpf());
                fw.write(separador);
                fw.write(aux.getNome());
                fw.write(separador);
                fw.write(aux.getNascimento().toString());
                fw.write(separador);
                fw.write(aux.getGenero());
                fw.write(separador);
                fw.write(((Paciente) aux).getDocSaude());
                fw.write(separador);
                fw.write(((Paciente) aux).getTelefone());
                fw.write(separador);
                fw.write(((Paciente) aux).getEndereco());
                fw.write("\n");
            }
        }
        fw.close();
        
        
        // Consultas
        System.out.println("Escolha um nome para salvar a lista de Consultas: ");
        nomeArq = scan.nextLine();
        arq = new File("src/arquivosFonte/"+nomeArq+".txt");
        arq.delete(); // pra assegurar q nao tenha nada no arq
        fw = new FileWriter("src/arquivosFonte/"+nomeArq+".txt");
        
        List<Consulta> consultas = controle.getConsultas();
        for (Consulta aux : consultas) {
            if(aux instanceof Consulta) {
                fw.write(aux.getCrm());
                fw.write(separador);
                fw.write(aux.getDocSaude());
                fw.write(separador);
                fw.write(aux.getCorem());
                fw.write(separador);
                fw.write(aux.getData().toString());
                fw.write(separador);
                fw.write(aux.getDiagnostico());
                fw.write("\n");
            }
        }
        fw.close();
        System.out.println("Arquivos salvos com sucesso!");
        
    }
    
    private static void cadastroPessoas() {
        System.out.println("Insira os dados da pessoa:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a data de nascimento: ");
        System.out.println("Dia: ");
        int dia = scanner.nextInt();
        System.out.println("Mês: ");
        int mes = scanner.nextInt();
        System.out.println("Ano: ");
        int ano = scanner.nextInt();
        Data nascimento = new Data(dia,mes,ano);
        System.out.println("Digite o genero: ");
        String gen = scanner.next().toLowerCase();
        gen = gen.toLowerCase();
        boolean genero = false;
        if (gen.equals("masculino")) genero = true;
        
        int opcoes = 0;
        
        menuCadastro();
        opcoes = scanner.nextInt();
        switch (opcoes) {
            case 1:
                System.out.println("Digite o salario do funcionario: [Use virgula]");
                float salario;
                salario = scanner.nextFloat();
                System.out.println("Digite a data de admissao: ");
                System.out.println("Dia: ");
                dia = scanner.nextInt();
                System.out.println("Mês: ");
                mes = scanner.nextInt();
                System.out.println("Ano: ");
                ano = scanner.nextInt();
                Data dataAdmissao = new Data(dia, mes, ano);

                int opcoesFunc = 0;
                menuCadastroFunc();
                opcoesFunc = scanner.nextInt();
                if (opcoesFunc == 1) {
                    cadastraMedico(cpf, nome, nascimento, genero, salario, dataAdmissao);
                }
                else if (opcoesFunc == 2) {
                    cadastraEnfermeiro(cpf, nascimento, nome, genero, salario, dataAdmissao);
                }
                else {
                    cadastraOutrosFuncionarios(cpf, nome, nascimento, genero, salario, dataAdmissao);
                }
                break;
            case 2: 
                cadastraPaciente(cpf, nome, nascimento, genero);
                break;
            }
    }
    
    private static void menuCadastro() {
        System.out.println("Escolha o tipo de cadastro:");
        System.out.println("1. Funcionario");
        System.out.println("2. Paciente");
    }
    
    private static void menuCadastroFunc() {
        System.out.println("Escolha o tipo de funcionario");
        System.out.println("1. Medico");
        System.out.println("2. Enfermeiro");
        System.out.println("3. Outros");

    }
    
    private static void menu() {
        System.out.println("                  [Escolha uma opcao]                   ");
        System.out.println("||####################################################||");
        System.out.println("||           1- Cadastrar uma pessoa                  ||");
        System.out.println("||           2- Fazer uma consulta medica             ||");
        System.out.println("||           3- Listar todas as pessoas cadastradas   ||");
        System.out.println("||           4- Imprimir uma consulta medica          ||");
        System.out.println("||           5- Remover uma pessoa cadastrada         ||");
        System.out.println("||           6- Buscar uma pessoa cadastrada          ||");
        System.out.println("||           7- Listagem de consultas medicas         ||");
        System.out.println("||           8- Salvar em arquivos                    ||");
        System.out.println("||           9- Leitura em arquivos                   ||");
        System.out.println("||           0- Sair                                  ||");
        System.out.println("||####################################################||");

    }
    
    private static void cadastraMedico(String cpf, String nome, Data nascimento, boolean genero, float salario,
                    Data dataAdmissao) {
           Scanner scanner = new Scanner(System.in);
           String esp, crm;
           System.out.println("Digite a especialidade do médico: ");
           esp = scanner.next();
           System.out.println("Digite o CRM: ");
           crm = scanner.next();
           
           Medico med = new Medico(cpf, nome, nascimento, genero,
           salario, dataAdmissao, esp, crm);
           controle.addPessoa(med);
           System.out.println("Medico cadastrado com sucesso!");
    }
    
    private static void cadastraEnfermeiro(String cpf, Data nascimento, String nome, boolean genero, float salario,
                    Data dataAdmissao) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite se é chefe [s/n]: ");
        char opcao = scanner.next().charAt(0);
        boolean chefe;
        if (opcao == 's')
            chefe = true;
        else
            chefe = false;
        
        System.out.print("Digite o Coren: ");
        String corem = scanner.next();

        Enfermeiro enf = new Enfermeiro(cpf, nome, nascimento, genero,
             salario, dataAdmissao, corem, chefe);
        controle.addPessoa(enf);
        System.out.println("Enfermeiro cadastrado com sucesso!");
        
    }

    private static void cadastraOutrosFuncionarios(String cpf, String nome, Data nascimento, boolean genero, float salario, 
            Data dataAdmissao) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a função do funcionário: ");
        String tipoFunc = scanner.nextLine();
        System.out.println("Digite o codigo do funcionario: ");
        String codFun = scanner.nextLine();
        Funcionario func = new Funcionario(cpf, nome, nascimento,
                genero, salario, dataAdmissao, tipoFunc, codFun);
        controle.addPessoa(func);
        System.out.println("Funcionario cadastrado com sucesso!");
    }

    private static void cadastraPaciente(String cpf, String nome, Data nascimento, boolean genero) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o documento de saude: ");
        String docSaude = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o endereco: ");
        String endereco = scanner.nextLine();
        
        Paciente pac = new Paciente(cpf, nome, nascimento, genero,
           docSaude, telefone, endereco);
        controle.addPessoa(pac);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void abreMedicos() {
        System.out.println("Digite o nome do arquivo para abrir a lista de Medicos: ");
        scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        
        FileReader fr;
        try {
            fr = new FileReader("src/arquivosFonte/"+str+".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            try {
                linha = br.readLine();
                while (linha != null) {
                   String termos[] = linha.split(separador);

                   String cpf = termos[0];
                   String nome = termos[1];
                   Data nascimento = new Data(termos[2]);
                   boolean genero = false;
                   if ("masculino".equals(termos[3].toLowerCase())) genero = true;
                   float salario = Float.valueOf(termos[4]);
                   Data dataAdmissao = new Data(termos[5]);
                   String especialidade = termos[6];
                   String crm = termos[7];

                   Medico medico = new Medico(cpf, nome, nascimento, genero, salario,
                                        dataAdmissao, especialidade, crm);
                   controle.addPessoa(medico);
                   linha = br.readLine();
                }
            } catch (IOException ex) {
                System.out.println("Formatação do arquivo não corresponde.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado. Tentar novamente? (s/n)");
            str = scanner.nextLine();
            if ("s".equals(str)) abreMedicos();
        }
    }

    private static void abreEnfermeiros() {
        System.out.println("Digite o nome do arquivo para abrir a lista de Enfermeiros: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        
        FileReader fr;
        try {
            fr = new FileReader("src/arquivosFonte/"+str+".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            try {
                linha = br.readLine();
                while (linha != null) {
                    String termos[] = linha.split(separador);

                    String cpf = termos[0];
                    String nome = termos[1];
                    Data nascimento = new Data(termos[2]);
                    boolean genero = false;
                    if ("masculino".equals(termos[3].toLowerCase())) genero = true;
                    float salario = Float.valueOf(termos[4]);
                    Data dataAdmissao = new Data(termos[5]);
                    String corem = termos[6];
                    boolean chefe = false;
                    if ("chefe".equals(termos[7])) chefe = true;

                    Enfermeiro enfermeiro = new Enfermeiro(cpf, nome, nascimento, genero, salario,
                                         dataAdmissao, corem, chefe);

                    controle.addPessoa(enfermeiro);
                    linha = br.readLine();
                }
            } catch (IOException ex) {
                System.out.println("Formatação do arquivo não corresponde.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado. Tentar novamente? (s/n)");
            str = scanner.nextLine();
            if ("s".equals(str)) abreEnfermeiros();
        }
    }

    private static void abreFuncionarios() {
        System.out.println("Digite o nome do arquivo para abrir a lista de Funcionarios: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        
        FileReader fr;
        try {
            fr = new FileReader("src/arquivosFonte/"+str+".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            try {
                linha = br.readLine();
                while (linha != null) {
                   String termos[] = linha.split(separador);

                   String cpf = termos[0];
                   String nome = termos[1];
                   Data nascimento = new Data(termos[2]);
                   boolean genero = false;
                   if ("masculino".equals(termos[3].toLowerCase())) genero = true;
                   float salario = Float.valueOf(termos[4]);
                   Data dataAdmissao = new Data(termos[5]);
                   String tipoFunc = termos[6];
                   String codFun = termos[7];
                   Funcionario funcionario = new Funcionario(cpf, nome, nascimento, genero, salario,
                                        dataAdmissao, tipoFunc, codFun);

                   controle.addPessoa(funcionario);
                   linha = br.readLine();
                }
            } catch (IOException ex) {
                System.out.println("Formatação do arquivo não corresponde.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado. Tentar novamente? (s/n)");
            str = scanner.nextLine();
            if ("s".equals(str)) abreFuncionarios();
        }
    }

    private static void abrePacientes() {
        System.out.println("Digite o nome do arquivo para abrir a lista de Pacientes: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        
        FileReader fr;
        try {
            fr = new FileReader("src/arquivosFonte/"+str+".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            try {
            linha = br.readLine();
            while (linha != null) {
               String termos[] = linha.split(separador);
               
               String cpf = termos[0];
               String nome = termos[1];
               Data nascimento = new Data(termos[2]);
               boolean genero = false;
               if ("masculino".equals(termos[3].toLowerCase())) genero = true;
               String docSaude = termos[4];
               String telefone = termos[5];
               String endereco = termos[6];
               
               Paciente paciente = new Paciente(cpf, nome, nascimento, genero,
                                        docSaude, telefone, endereco);
               
               controle.addPessoa(paciente);
               linha = br.readLine();
            }
            } catch (IOException ex) {
                System.out.println("Formatação do arquivo não corresponde.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado. Tentar novamente? (s/n)");
            str = scanner.nextLine();
            if ("s".equals(str)) abrePacientes();
        }
    }

    private static void abreConsultas() {
        System.out.println("Digite o nome do arquivo para abrir a lista de Consultas: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        
        FileReader fr;
        try {
            fr = new FileReader("src/arquivosFonte/"+str+".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            try {
                linha = br.readLine();
                while (linha != null) {
                    String termos[] = linha.split(separador);

                    String crm = termos[0];
                    String docSaude = termos[1];
                    String corem = termos[2];
                    Data data = new Data(termos[3]);
                    String diagnostico = termos[4];

                    controle.addConsulta(new Consulta(crm, docSaude, corem, data, diagnostico));

                    linha = br.readLine();
                }
            } catch (IOException ex) {
                System.out.println("Formatação do arquivo não corresponde.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado. Tentar novamente? (s/n)");
            str = scanner.nextLine();
            if ("s".equals(str)) abreConsultas();
        }
    }

    private static boolean verificaValidez(String dadosCons) {
        try {
            controle.buscaPessoa(dadosCons);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean cadastroConsulta() {
       
        boolean deuRuim = false;
        String docSaude = "";
        String corem = "";
        System.out.println("Digite o CRM do medico: ");
        String crm = scanner.next();
        if(!verificaValidez(crm)) {
            System.out.println("CRM inválido.");
            deuRuim = true;
        }
        
        if (!(deuRuim)) {
            System.out.println("Digite o documento de saude do paciente: ");
            docSaude = scanner.next();
            System.out.println("docSaude: " + docSaude);
            if( !(verificaValidez(docSaude)) ) {
                System.out.println("Documento inválido.");
                deuRuim = true;
            }
        }

        if (!(deuRuim)) {
            System.out.println("Digite o COREN do enfermeiro: ");
            corem = scanner.next();
            if(!verificaValidez(corem)&& !(deuRuim)) {
                System.out.println("COREN inválido.");
                deuRuim = true;
            }
        }

        if (!(deuRuim)) {
            System.out.println("Digite a data da consulta: ");
            System.out.println("Dia: ");
            int d = scanner.nextInt();
            System.out.println("Mês: ");
            int m = scanner.nextInt();
            System.out.println("Ano: ");
            int a = scanner.nextInt();
            Data dataConsulta = new Data(d,m,a);
            System.out.println("Entre com o diagnóstico do paciente: ");
            String diagnostico = scanner.next();
            Consulta novaConsulta = new Consulta(crm, docSaude, corem, dataConsulta, diagnostico);
            controle.addConsulta(novaConsulta);
            return true;
        }
    
        return false;    
    }
}