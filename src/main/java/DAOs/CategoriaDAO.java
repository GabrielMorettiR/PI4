/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Categoria;
import Utils.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class CategoriaDAO {

    public static Map<Integer, Categoria> getCategorias() {

        Map<Integer, Categoria> categorias = new HashMap<>();

        try {
            String query = "select * from categoria";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id"), rs.getString("titulo"));

                categorias.put(c.getId(), c);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    public static Categoria getCategoriaById(int id) {

        Categoria categoria = new Categoria();

        try {
            String query = "select * from categoria where id = ?";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria = new Categoria(rs.getInt("id"), rs.getString("titulo"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }
    
    public static Categoria getCategoriaByTitulo(String titulo) {

        Categoria categoria = new Categoria();

        try {
            String query = "select * from categoria where titulo = ?";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, titulo);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria = new Categoria(rs.getInt("id"), rs.getString("titulo"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }
}
