/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author eudes
 */
public class ProjectContoller {
    //Metodo para salvar o projeto
    public void save(Project project){
     String sql = "INSERT INTO project (name,description, createdAt, updatedAt) values (?,?,?,?,?)";
     Connection conn = null;
     PreparedStatement statement = null;
     
        try {
            //Fazendo conexão com o banco
           conn = ConnectionFactory.getConnection();
           //preparando para setar/mudar o sql no banco
           statement = conn.prepareStatement(sql);
           // numero é o atributo que vai ser setado , o atributa do java que vai passar
           statement.setString(1,project.getName());
           statement.setString(2, project.getDiscription());
           statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
           statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
           // executar o comando no banco
           statement.execute();           
           
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar salvar " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }     
    }    
    //Metodo para alterção do projeto
    public void update(Project project){
        String sql = "UPFATE project SET name=?, description=?, createdAt=? , updatedAt=? WHERA id=?";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //Fazendo conexão com o banco
            conn = ConnectionFactory.getConnection();
            //preparando para fazer o comanado no banco
            statement = conn.prepareStatement(sql);
            //os comando são os mesmo de salvar, mas vai ser para alterar
            statement.setString(1, project.getName());
            statement.setString(2, project.getDiscription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            //executando os comando no banco
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar fazer alterção " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    //Metodo para deletar o projeto
    public void removeById (int projectId) throws Exception{
        String sql = "DELETE FROM project WHERE id=?";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //Fazendo conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Preparando para setar/alterar no banco
            statement = conn.prepareStatement(sql);
            //informado o atributo para qual vai ser o atributo a ser removido
            statement.setInt(1, projectId);
            //comando para executar no slq
            statement.execute();
        } catch (Exception e) {
            //Mensagem de erro caso ocorra
            throw  new SQLException("Erro ao tentar deletar projeto ");
        } finally {
            //Finalizando todas as conexão
            ConnectionFactory.closeConnection(conn,statement);
        }
    }
    //Metodo para lista os projeto
    public List<Project> getAll (){
        //Como é uma lista de projeto não precisa de filto, vai voltar todos os projetos
        String sql = "SELECT * FROM project";
        Connection conn = null;
        PreparedStatement statement = null;
        List<Project> projects = new ArrayList<Project>();
        // ResultSet vai guarda as informações que vinherem do banco
        ResultSet resultSet = null;
        
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            //statement.setInt(1, id);
            resultSet = statement.executeQuery();
            // Para pegar as informações que estão no banco e guarda dentro do atriuto resultSet 
            //O .next() é para enquando tiver valor eu vou pegando
            while(resultSet.next()){
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setDiscription(resultSet.getString("description"));
            project.setCreatedAt(resultSet.getDate("createdAt"));
            project.setUpdatedAt(resultSet.getDate("updatedAt"));
            //Agora temos que colocar dentro da lista
            projects.add(project);            
            }            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar fazer a listagem " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
        return projects;
    }
}






