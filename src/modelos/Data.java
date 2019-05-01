/*
   Trabalho de PLP
   Hospital Sirio Japones
   Copyright 2018 by Andrew Takeshi, Gabriel Amorim, Gustavo Sousa
   Modelo da classe Data.
*/
package modelos;

public class Data {
    private final int dia;
    private final int mes;
    private final int ano;
    
    // @Construtor da classe Data.
    // @Param: int dia, int mes, int ano.
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    // @Sobrecarga de construtor utiliza string sepada em dia, mes e ano.
    // @Param: String data.
    // @Return: Data.
    public Data(String data) {
        String termos[] = data.split("/");
        this.dia = new Integer(termos[0]);
        this.mes = new Integer(termos[1]);
        this.ano = new Integer(termos[2]);
    }

    // @Return: Int.
    public int getDia() {
        return dia;
    }

    // @Return: Int.
    public int getMes() {
        return mes;
    }

    // @Return: Int.
    public int getAno() {
        return ano;
    }
    
    @Override
    // @Faz conversao para string.
    // @Return: String.
    public String toString() {
        return String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(ano);
    }
    
}
