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
public class Endereco {
    private int id;
    private String titulo;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean status;
    private int tipo;

    public Endereco() {
    }
    
    public Endereco(String titulo, String cep, String logradouro, int numero, String complemento, String bairro, String cidade, String uf, boolean status) {
        this.titulo = titulo;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.status = status;
    }
    
    public boolean isEntrega(){
        return this.tipo == 1;
    }
    
    public boolean isCobranca(){
        return this.tipo == 2;
    }
}
