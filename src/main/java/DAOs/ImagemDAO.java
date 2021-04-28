/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Imagem;
import java.io.File;
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

        if (capa) {
            String query = "update imagens set capa = false where imagens.idproduto = ? and capa";
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(query);

                ps.setInt(1, idproduto);

                ps.execute();

            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
            String query = "select i.IDPRODUTO, i.DIR, i.ID from imagens as i join produto as p on i.IDPRODUTO = p.id where capa";
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

    public static Imagem getCapa(int idProd) {

        Imagem img = new Imagem();

        try {
            String query = "select i.id, i.DIR from produto as p join\n"
                    + " imagens as i on i.IDPRODUTO = p.id where p.ID = ? and capa";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idProd);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String dir = rs.getString("DIR");
                img.setDir(dir);
                img.setId(id);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return img;
    }

    public static List<Imagem> getProdImagens(int idProd) {

        List<Imagem> imgs = new ArrayList();

        try {
            String query = "select i.id, i.DIR from produto as p join\n"
                    + " imagens as i on i.IDPRODUTO = p.id where p.ID = ? and not capa";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idProd);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String dir = rs.getString("DIR");
                Imagem i = new Imagem();
                i.setDir(dir);
                i.setId(id);
                imgs.add(i);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imgs;
    }

    public static Imagem getImgById(int idImg) {

        Imagem i = new Imagem();

        try {
            String query = "select i.DIR, i.id from produto as p join\n"
                    + " imagens as i on i.IDPRODUTO = p.id where i.ID = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idImg);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String dir = rs.getString("DIR");
                i.setDir(dir);
                i.setId(id);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return i;
    }

    public static void deleteImg(Imagem i) {
        File myObj = new File(i.getDir());
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        desvincularImg(i);
    }

    public static void desvincularImg(Imagem i) {
        try {
            String query = "delete from imagens where id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i.getId());
            ps.executeQuery();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int nextId() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoBD.getConexao();
        String query = "select id from imagens order by id desc fetch first row only";

        PreparedStatement ps;

        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        int prox = 0;
        if (rs.next()) {
            prox = rs.getInt("id");
        }
        return prox + 1;
    }
}
