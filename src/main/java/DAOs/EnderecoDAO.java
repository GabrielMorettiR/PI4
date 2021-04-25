/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import BD.ConexaoBD;
import Entidades.Endereco;
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
public class EnderecoDAO {

    public static void cadEndereco(Endereco e) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoBD.getConexao();
        String query = "insert into endereco(titulo, cep, logradouro, numero, complemento, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);

            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getCep());
            ps.setString(3, e.getLogradouro());
            ps.setInt(4, e.getNumero());
            ps.setString(5, e.getComplemento());
            ps.setString(6, e.getBairro());
            ps.setString(7, e.getCidade());
            ps.setString(8, e.getUf());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Endereco> getEnderecos() {

        List<Endereco> imgs = new ArrayList();

        try {
            String query = "select distinct i.IDPRODUTO, i.DIR, i.ID from imagens as i join produto as p on i.IDPRODUTO = p.id";
            Connection con = ConexaoBD.getConexao();

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idprod = rs.getInt("IDPRODUTO");
                String dir = rs.getString("DIR");
                int id = rs.getInt("ID");
                imgs.add(new Endereco());
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return imgs;
    }
}
