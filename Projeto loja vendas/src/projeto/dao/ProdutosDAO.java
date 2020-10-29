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
import projeto.model.Fornecedores;
import projeto.model.Produtos;

/**
 *
 * @author Roger
 */
public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrar(Produtos obj) {

        try {
            String sql = "INSERT INTO tb_produtos(descricao,preco,qtd_estoque,for_id) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());

            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    public void alterar(Produtos obj) {

        try {
            String sql = "UPDATE  tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto alterado com Sucesso");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    public void excluir(Produtos obj) {

        try {
            String sql = "DELETE FROM  tb_produtos WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluido com Sucesso");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    public List<Produtos> listarProdutos() {
        List<Produtos> lista = new ArrayList();

        try {

            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p "
                    + "INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public List<Produtos> listarProdutosPorNome(String nome) {
        List<Produtos> lista = new ArrayList();

        try {

            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p "
                    + "INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id)WHERE p.descricao LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Produtos consultaPorNome(String nome) {

        try {

            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p "
                    + "INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id)WHERE p.descricao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

            }

            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Produto Nao encontrado");
            return null;
        }
    }

    //consulta produto por codigo
    public Produtos buscaPorCodigo(int id) {

        try {

            String sql = "SELECT * FROM tb_produtos WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

            }

            return obj;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Produto Nao encontrado");
            return null;
        }
    }

    //metodo atualiza estoque
    public void baixaEstoque(int id, int qtd_nova) {
        try {
            String sql = "UPDATE tb_produtos SET qtd_estoque = ? WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,qtd_nova);
            stmt.setInt(2, id);
            
            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }
    }
    //retorna estoque atual
    public int retornaEstoqueAtual(int id){
        int qtd_estoque = 0;
        try {
            String sql ="SELECT qtd_estoque FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);           
            stmt.setInt(1,id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Produtos p = new Produtos();
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //controle de estoque botao adicionar ao estoque
    
    public void adicionarEstoque(int id, int qtd_nova){
        try {
            String sql ="UPDATE tb_produtos SET qtd_estoque= ? WHERE id= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
    }

}
