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
public class Imagem {
    private int id;
    private String dir;
    private int idprod;
    private boolean capa;
    private boolean status;

    public Imagem(int id, String dir, int idprod, boolean capa, boolean status) {
        this.id = id;
        this.dir = dir;
        this.idprod = idprod;
        this.capa = capa;
        this.status = status;
    }

    public Imagem() {
    }
}
