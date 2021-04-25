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

    public Imagem(int id, String dir, int idprod) {
        this.id = id;
        this.dir = dir;
        this.idprod = idprod;
    }
}
