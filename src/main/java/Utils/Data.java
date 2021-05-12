/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gabriel
 */
public class Data {

    private String data;
    private String dia;
    private String mes;
    private String ano;

    public Data() {
        this.data = null;
    }
    
    public Data(String data) {
        this.data = data;
    }

    public void setData() {
        setDia();
        setMes();
        setAno();
    }

    public void setDataOrdem() {
        setDiaOrdem();
        setMesOrdem();
        setAnoOrdem();
    }

    public String getData() {
        return ano + "-" + mes + "-" + dia;
    }

    public String getDataOrdem() {
        return dia + "/" + mes + "/" + ano;
    }

    public void setDia() {
        String at;
        String a;

        a = "" + data.charAt(8);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(9);
        at += Integer.parseInt(a);
        this.dia = at;
    }

    public void setMes() {
        String at;
        String a;

        a = "" + data.charAt(5);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(6);
        at += Integer.parseInt(a);
        this.mes = at;
    }

    public void setAno() {
        String at;
        String a;

        a = "" + data.charAt(0);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(1);
        at += Integer.parseInt(a);

        a = "" + data.charAt(2);
        at += Integer.parseInt(a);

        a = "" + data.charAt(3);
        at += Integer.parseInt(a);
        this.ano = at;
    }

    public String getDia() { // retorna o dia em String
        return dia;
    }

    public String getMes() { // retorna o dia em String
        return mes;
    }

    public String getAno() { // retorna o dia em String
        return ano;
    }

    public void setDiaOrdem() {
        String at;
        String a;

        a = "" + data.charAt(0);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(1);
        at += Integer.parseInt(a);
        dia = at;
    }

    public void setMesOrdem() {
        String at;
        String a;

        a = "" + data.charAt(3);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(4);
        at += Integer.parseInt(a);
        mes = at;
    }

    public void setAnoOrdem() {
        String at;
        String a;

        a = "" + data.charAt(6);
        at = Integer.parseInt(a) + "";

        a = "" + data.charAt(7);
        at += Integer.parseInt(a);

        a = "" + data.charAt(8);
        at += Integer.parseInt(a);

        a = "" + data.charAt(9);
        at += Integer.parseInt(a);
        ano = at;
    }

    public String getDiaOrdem() { // retorna o dia em String
        return dia;
    }

    public String getMesOrdem() { // retorna o dia em String
        return mes;
    }

    public String getAnoOrdem() { // retorna o dia em String
        return ano;
    }

    public String toDB() {
        String dt = ano + "-";
        dt += mes + "-";
        dt += dia + "";
        return dt;
    }

    public boolean anoBissexto() {
        String y = String.valueOf(ano);
        int at = y.charAt(2) + y.charAt(3);
        int ano = Integer.parseInt(this.ano);
        return ano % 4 == 0 && at != 0;
    }
    
    public static Data getDataAtual(){
        System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String data = formatter.format(date);
        String aux = "";
        for(int i = 0; i < 10; i++){
            aux += data.charAt(i);
        }
        Data dat = new Data(aux);
        dat.setData();
        return dat;
    }

    public boolean validaData() {
        Data atual = getDataAtual();
        int dia = Integer.parseInt(this.dia);
        int mes = Integer.parseInt(this.mes);
        int ano = Integer.parseInt(this.ano);
        if (dia > 31) {
            return false;
        } else if (dia > 29 && mes == 2 && anoBissexto()) {
            return false;
        } else if (dia > 28 && mes == 2) {
            return false;
        } else if (mes > 12) {
            return false;
        } else if (ano > Integer.parseInt(atual.getAno())) {
            return false;
        }
        return true;
    }
}
