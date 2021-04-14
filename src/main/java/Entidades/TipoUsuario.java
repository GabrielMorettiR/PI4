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
public class TipoUsuario {
    private int id;
    private String titulo;

    public TipoUsuario() {
    }
    
    public TipoUsuario(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
