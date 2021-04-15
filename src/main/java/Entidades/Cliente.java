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
public class Cliente extends Usuario{
    private int idusuario;
    private String cpf;

    public Cliente() {
    }
    
    public Cliente(int idusuario, String cpf) {
        this.idusuario = idusuario;
        this.cpf = cpf;
    }
    
    
}
