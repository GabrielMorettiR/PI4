/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gabriel
 */
@Getter
@Setter
public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private boolean status;
    private int tipoCadastro;
    private String email;

    public Usuario() {
    }

    public Usuario(int id, String nome, String senha, boolean status, int tipoCadastro) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.status = status;
        this.tipoCadastro = tipoCadastro;
    }
    
    public String criptografar(String senha){   
        return BCrypt.withDefaults().hashToString(9, senha.toCharArray());
    }
    
    public boolean validar(String senha){   
        BCrypt.Result response = BCrypt.verifyer().verify(senha.toCharArray(),this.senha);
        return response.verified;
    }
    
    public boolean isEstoq(){
        return this.tipoCadastro == 1;
    }
    
    public boolean isAdmin(){
        return this.tipoCadastro == 2;
    }
    
    public boolean isCliente(){
        return this.tipoCadastro == 3;
    }
}
