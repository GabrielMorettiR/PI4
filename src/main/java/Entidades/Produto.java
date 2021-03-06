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
public class Produto {

    private int id;
    private String nomeproduto;
    private String nomeextenso;
    private int estrelas;
    private boolean status;
    private int quantidade;
    private double preco;

    public Produto() {
    }

    public Produto(int id, String nomeproduto, String nomeextenso, int estrelas, boolean status, int quantidade, double preco) {
        this.id = id;
        this.nomeproduto = nomeproduto;
        this.nomeextenso = nomeextenso;
        this.estrelas = estrelas;
        this.status = status;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
