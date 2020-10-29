/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projeto.jdbc.ConnectionFactory;
import projeto.model.ItemVenda;
import projeto.model.Produtos;

/**
 *
 * @author Roger
 */
public class ItemVendaDAO {
    private Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    // cadastra itemns
    public void cadastraItem(ItemVenda obj){
        try {
            String sql = "INSERT INTO tb_itensvendas(venda_id,produto_id,qtd,subtotal) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

            

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    
    // M lista item de una venda por id
    
    public List<ItemVenda> listaItensPorVenda(int venda_id){
        List<ItemVenda> lista = new ArrayList<>();
        
        try {
            String sql = "SELECT p.descricao, i.qtd, p.preco,i.subtotal FROM tb_itensvendas AS i "
                    + "INNER JOIN tb_produtos AS p ON(i.produto_id = p.id) WHERE i.venda_id = ? ;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, venda_id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
               
                prod.setDescricao(rs.getString("p.dercricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);
                
                lista.add(item);
            }
            
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    
}
