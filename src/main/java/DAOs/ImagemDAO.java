/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ImagemDAO {

    public static void cadImagem(String dir, int idproduto, boolean capa) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into imagens(dir, idproduto, capa) values (?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, dir);
            ps.setInt(2, idproduto);
            ps.setBoolean(3, capa);

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Imagem> getImagens() {

        List<Imagem> imgs = new ArrayList();

        try {
            String query = "select distinct i.IDPRODUTO, i.DIR, i.ID from imagens as i join produto as p on i.IDPRODUTO = p.id";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idprod = rs.getInt("IDPRODUTO");
                String dir = rs.getString("DIR");
                int id = rs.getInt("ID");
                imgs.add(new Imagem(id, dir, idprod));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imgs;
    }

    public static List<Imagem> getProdImagens(int idProd) {

        List<Imagem> imgs = new ArrayList();

        try {
            String query = "select i.DIR from produto as p join\n"
                    + " imagens as i on i.IDPRODUTO = p.id where p.ID = ? and not capa";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, idProd);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dir = rs.getString("DIR");
                Imagem i = new Imagem();
                i.setDir(dir);
                imgs.add(i);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imgs;
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select MAX(id) from imagens";

        PreparedStatement ps;

        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        int prox = 0;
        if (rs.next()) {
            prox = rs.getInt("1");
        }
        return prox + 1;
    }
}
