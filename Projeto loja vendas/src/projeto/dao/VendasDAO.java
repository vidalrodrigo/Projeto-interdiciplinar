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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projeto.jdbc.ConnectionFactory;
import projeto.model.Clientes;
import projeto.model.Vendas;

/**
 *
 * @author Roger
 */
public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //cadastrar venda
    public void cadastrarVendas(Vendas obj) {

        try {
            String sql = "INSERT INTO tb_vendas(cliente_id,data_venda,total_venda,observacoes) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

            

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //retorna venda
    public int retornaUltimaVenda() {

        try {
            int idvenda = 0;
            
            String sql = "SELECT MAX(id) id FROM tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                
                idvenda = p.getId();
            }
            
            return idvenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    // filtra vendas por data

    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio,LocalDate data_fim) {
        List<Vendas> lista = new ArrayList();

        try {

            String sql = "SELECT v.id, date_format(v.data_venda,'%d/%m/%y') AS data_formatada, c.nome, v.total_venda, v.observacoes FROM tb_vendas AS v "
                    + "INNER JOIN tb_clientes AS c ON (v.cliente_id = c.id) WHERE v.data_venda BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));

                obj.setCliente(c);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
    
    
    //calula total de venda por data
    
    public double retornaTotalVendaPorData(LocalDate data_venda) {

        try {
            double totalvenda = 0;
            
            String sql = "SELECT SUM(total_venda) AS total FROM tb_vendas WHERE data_venda =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,data_venda.toString());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                totalvenda = rs.getDouble("total");
            }
            
            return totalvenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
}
