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

    public static void cadImagem(String dir, int idproduto, boolean capa, boolean status) throws ClassNotFoundException, SQLException {

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

        String query = "insert into imagens(dir, idproduto, capa, status) values (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, dir);
            ps.setInt(2, idproduto);
            ps.setBoolean(3, capa);
            ps.setBoolean(4, status);

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Imagem> getImagens() {

        List<Imagem> imgs = new ArrayList();

        try {
            String query = "select i.* from imagens as i join produto as p on i.IDPRODUTO = p.id where capa";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idprod = rs.getInt("IDPRODUTO");
                String dir = rs.getString("DIR");
                int id = rs.getInt("ID");
                boolean status = rs.getBoolean("status");
                boolean capa = rs.getBoolean("capa");
                imgs.add(new Imagem(id, dir, idprod, capa, status));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imgs;
    }

    public static Imagem getCapa(int idProd) {

        Imagem img = new Imagem();

        try {
            String query = "select i.id, i.DIR, i.capa from produto as p join\n"
                    + " imagens as i on i.IDPRODUTO = p.id where p.ID = ? and capa";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idProd);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String dir = rs.getString("DIR");
                boolean capa = rs.getBoolean("capa");
                img.setDir(dir);
                img.setId(id);
                img.setCapa(capa);;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return img;
    }

    public static List<Imagem> getImgByProd(int idProd, int cond) {

        String q = "";
        
        switch (cond) {
            case 1:
                q = " and capa";
                break;
            case 2:
                q = " and not capa";
                break;
            case 0:
                q = " and status";
                break;
        }

        List<Imagem> imgs = new ArrayList();

        try {
            String query = "select * from imagens where idproduto = ?" + q + " order by capa desc";
            Connection con = ConexaoBD.getConexao();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idProd);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String dir = rs.getString("DIR");
                boolean capa = rs.getBoolean("capa");
                boolean status = rs.getBoolean("status");
                Imagem i = new Imagem();
                i.setDir(dir);
                i.setId(id);
                i.setCapa(capa);
                i.setStatus(status);
                i.setIdprod(idProd);
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
            String query = "select * from imagens where id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, idImg);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i.setDir(rs.getString("DIR"));
                i.setId(Integer.parseInt(rs.getString("id")));
                i.setIdprod(Integer.parseInt(rs.getString("idproduto")));
                i.setCapa(rs.getBoolean("capa"));
                i.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return i;
    }

    public static void desvincularImg(Imagem i) {
        try {
            String query = "update imagens set status = ? where id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setBoolean(1, i.isStatus());
            ps.setInt(2, i.getId());
            ps.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void toggleCapa(Imagem i){
        
        try {
            String query = "update imagens set capa = false where idproduto = ? and capa and status";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i.getIdprod());
            ps.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String query = "update imagens set capa = true where idproduto = ? and id = ?";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i.getIdprod());
            ps.setInt(2, i.getId());
            ps.execute();

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
