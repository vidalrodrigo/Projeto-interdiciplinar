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

/**
 *
 * @author Roger
 */
public class FornecedoresDAO {
    
    private Connection con;
    
    public FornecedoresDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarFornecedores(Fornecedores obj){
        
        try {
            
            String sql ="INSERT INTO tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                                       + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            
            stmt.setString(2, obj.getCnpj());
            
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8,obj.getNumero());
            stmt.setString(9,obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public void excluirFornecedor(Fornecedores obj){
        
        try {
            
            String sql ="DELETE FROM tb_fornecedores WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            
  
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public void alterarFornecedor(Fornecedores obj){
        
        try {
            
            String sql ="UPDATE tb_fornecedores SET nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? WHERE id=?";
                                       
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            
            stmt.setString(2, obj.getCnpj());
            
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8,obj.getNumero());
            stmt.setString(9,obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            
            
            stmt.setInt(13,obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public List<Fornecedores>listarFornecedores(){
        
        try {
            
            List<Fornecedores> lista = new ArrayList();
            
            String sql = "SELECT * FROM tb_fornecedores";          
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getShort("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            
            return lista;
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
            return null;
        }
        
    }
    
    
    public List<Fornecedores>listarFornecedoresPorNome(String nome){
        
        try {
            
            List<Fornecedores> lista = new ArrayList();
            
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";          
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getShort("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            
            return lista;
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
            return null;
        }
        
    }
    
    
    public Fornecedores consultaPorNome(String nome){
        try {
            
            String sql = "SELECT * FROM tb_fornecedores WHERE nome = ?";          
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();
            
            if(rs.next()){                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                
                obj.setCnpj(rs.getString("cnpj"));
                
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getShort("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
        
            }
            return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Fornecedor não encontrado");
            return null;
        }
    }
    
}
