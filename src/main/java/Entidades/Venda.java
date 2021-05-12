/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

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
    private String entrega;
    private String cobranca;

    public Venda() {
    }

    public Venda(int idcliente, double preco, double frete, int pagamento, String entrega, String cobranca) {
        this.idcliente = idcliente;
        this.preco = preco;
        this.frete = frete;
        this.pagamento = pagamento;
        this.entrega = entrega;
        this.cobranca = cobranca;
    }
    
}
