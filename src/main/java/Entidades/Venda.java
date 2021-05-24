/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Utils.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel
 */
@Getter
@Setter
public class Venda {

    private int id;
    private int idcliente;
    private double preco;
    private double frete;
    private int pagamento;
    private int entrega;
    private int cobranca;
    private int status;
    private Data data;
    private String cliente;

    public Venda() {
    }

    public Venda(int idcliente, double preco, double frete, int pagamento, int entrega, int cobranca) {
        this.idcliente = idcliente;
        this.preco = preco;
        this.frete = frete;
        this.pagamento = pagamento;
        this.entrega = entrega;
        this.cobranca = cobranca;
    }

    public String date() {
        if (data != null) {
            return data.getDataOrdem();
        } else {
            return "data não encontrada";
        }
    }

    public String situacao() {
        switch (this.status) {
            case 0:
                return "Aguardando pagamento";
            case -1:
                return "Pagamento rejeitado";
            case 1:
                return "Pagamento confirmado";
            case 2:
                return "Aguardando retirada";
            case 3:
                return "Em trânsito";
            case 4:
                return "Entregue";
            default:
                return "erro";
        }
    }

    public String formaPagto() {

        switch (this.pagamento) {
            case 1:
                return "Cartão de Crédito";
            case 2:
                return "Boleto";
            case 3:
                return "Pix";
            default:
                return "forma de pagamento não escolhida";
        }
    }

}
