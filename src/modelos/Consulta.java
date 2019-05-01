/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo de Consulta
*/
package modelos;

public class Consulta {
  
    private final String crm;
    private final String docSaude;
    private final String corem;
    private final Data data;
    private final String diagnostico;
    
    // @Construtor da consulta.
    public Consulta(String crm, String docSaude, String corem, Data data, String diagnostico) {
        this.crm = crm;
        this.docSaude = docSaude;
        this.corem = corem;
        this.data = data;
        this.diagnostico = diagnostico;
    }

    // @Return: String.
    public String getCrm() {
        return crm;
    }

    // @Return: String.
    public String getDocSaude() {
        return docSaude;
    }

    // @Return; String.
    public String getCorem() {
        return corem;
    }

    // @Return: Data.
    public Data getData() {
        return data;
    }

    // @Return: String
    public String getDiagnostico() {
        return diagnostico;
    }

    // @Mostra todos os valores dos atributos da consulta.
    public void print() {
        System.out.println("Consulta: ");
        System.out.println("CRM do medico: " + crm);
        System.out.println("Documento de saude do paciente: " + docSaude);
        System.out.println("COREN do enfermeiro: " + corem);
        System.out.println("Data da consulta: " + data.toString());
        System.out.println("Diagnostico do paciente: " + diagnostico);
    }
}