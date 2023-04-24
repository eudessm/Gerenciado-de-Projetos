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
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author eudes
 */
public class TaskController {
 
    // Criar os metodos para as operações básicas que vai ser preciso fazer 
    
    public void save(Task task){
        String sql = "INSERT INTO tasks (idProject,name,description,completed,notes,deadline,createdAt,updatedAt) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //Fszendo a conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Preparando para setar/mudar o sql
            statement = conn.prepareStatement(sql);
           //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "1" coresponde ao 1º "?"
            statement.setInt(1, task.getIdProject());
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "2" coresponde ao 2º "?"
            statement.setString(2, task.getName());
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "3" coresponde ao 3º "?"
            statement.setString(3, task.getDescription());
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "4" coresponde ao 4º "?"
            statement.setBoolean(4, task.isIsCompleted());
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "5" coresponde ao 5º "?"
            statement.setString(5, task.getNotes());
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "6" coresponde ao 6º "?"
            // Como o banco não aceita o tipo de data util do java temos que fazer a converção para a dataTime do sql
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            // Como o banco não aceita o tipo de data util do java temos que fazer a converção para a dataTime do sql
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "7" coresponde ao 7º "?"
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            // Como o banco não aceita o tipo de data util do java temos que fazer a converção para o pacote data do sql
            //Setando o valor que quero add/alterar no banco de acordo com a posisção informado
           //no caso "8" coresponde ao 8º "?"
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            //Executar os comandos no sql
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar salvar "+ex.getMessage(),ex);
        } finally {
            //Fechar conexão com o banco
            ConnectionFactory.closeConnection(conn,statement);
        }
        
    }
    public void update(Task task){
        String sql = "UPDATE tasks SET idPorject=?,name=?,description=?,completed=?,"
                + "notes=?,deadline=?,createdAt=?,updatedAt=? WHERE id=?";
        Connection conn = null;
        PreparedStatement statement =  null;
        
        try {
             //Fszendo a conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Preparando para setar/mudar o sql
            statement = conn.prepareStatement(sql);
            /*Os comandos são as mesma como se force para salvar*/
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.setInt(9,task.getId() );
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar fazer alteração "+ ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(conn,statement);           
        }        
    }
    
    public void removeById (int taskId){
        // criando uma String atribuindo o comando DELETE de slq para ir ate a tabale "FROM tasks" que eu quero
        // apagar a informação passando "WHERE" o campo 
        String sql = "DELETE FROM tasks WHERE id=?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        // Tratar o erro caso acontersa 
        try {
            conn = ConnectionFactory.getConnection();
            // Ajuda a preparar o comando de SQL que vai ser executado
            statement = conn.prepareStatement(sql);
            //SetInt estou informando que eu quero setar/mudar um valor no sql "String sql" passado antes
            // Eu quero mudar/setar a ? do comando sql pelo valor "1" pelo ida da terafa que recebir por parametro
            statement.setInt(1, taskId);
            // Estamos solicitando que execute o comando no banco
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa");
        }finally{
            //O bloco "finally" sempre vai ser executado ao final do try independente e utilizamos para encerrar a conexão
            ConnectionFactory.closeConnection(conn,statement);
        }
    }
    
    public List<Task> getAll (int idProjet){
        //Segue a mesma logica só alterar é o comando para o slq
        String sql ="SELECT * FROM tasks WHERE idProject = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        // ResultSet vai guarda as informações que vinherem do banco 
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try {
            //Segue a mesma logica para fazer a conexão e preparar para fazer os comando no slq
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareCall(sql);
            statement.setInt(1,idProjet);
            resultSet = statement.executeQuery();
            // Para pegar as informações que estão no banco e guarda dentro do atriuto resultSet 
            //O .next() é para enquando tiver valor eu vou pegando
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                //Agora temos que colocar dentro da lista 
                tasks.add(task);
            }            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao tentar fazer a listagem " + ex.getMessage(),ex);
        } finally {
           ConnectionFactory.closeConnection(conn,statement);
        }
        //Devolve a lista de tarefas
        return tasks;
    }
}
