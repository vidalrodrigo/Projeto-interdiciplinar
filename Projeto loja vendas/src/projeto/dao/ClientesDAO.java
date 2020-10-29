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
import projeto.model.Clientes;
import projeto.model.WebServiceCep;

/**
 *
 * @author Roger
 */
public class ClientesDAO {
    
    private Connection con;
    
    public ClientesDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarCliente(Clientes obj){
        
        try {
            
            String sql ="INSERT INTO tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado) "
                                       + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public void alterarCliente(Clientes obj){
        
        try {
            
            String sql ="UPDATE tb_clientes SET nome=?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,"
                    + "endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? WHERE id=?";
                                       
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            
            
            stmt.setInt(14,obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public void excluirCliente(Clientes obj){
        
        try {
            
            String sql ="DELETE FROM tb_clientes WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            
  
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+erro);
        }
        
    }
    
    public List<Clientes>listarClientes(){
        
        try {
            
            List<Clientes> lista = new ArrayList();
            
            String sql = "SELECT * FROM tb_clientes";          
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    
    public Clientes consultaPorNome(String nome){
        try {
            
            String sql = "SELECT * FROM tb_clientes WHERE nome = ?";          
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();
            
            if(rs.next()){                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null,"Cliente não encontrado");
            return null;
        }
    }
    
    
    // busca cliente por cpf
    public Clientes buscaporcpf(String cpf){
        try {
            
            String sql = "SELECT * FROM tb_clientes WHERE cpf = ?";          
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            Clientes obj = new Clientes();
            
            if(rs.next()){                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            JOptionPane.showMessageDialog(null,"Cliente não encontrado");
            return null;
        }
    }
    
    
    
    public List<Clientes>buscaClientePorNome(String nome){
        
        try {
            
            List<Clientes> lista = new ArrayList();
            
            String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";          
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    //Busca Cep
    
    public Clientes buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Clientes obj = new Clientes();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }
    
    
}
