/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author eudes
 */
public class ConnectionFactory {
  
    //com.mysql = Estamos informado qual banco estamos utilizando caso seja outro banco vc
    //só alterar o "mysql" para o qual vc vai utilizar
    public static final String DRIVE ="com.mysql.jdbc.Driver";
    //localhost = a porte "3306" pode alterar dependendo da aplicação que vc 
    //pode esta utilizando 
    //no meu caso o wamp mostra a porta 
    // "/todoapp" é o nome do meu sistema, e vc pode alterar para o nome que 
    //foi dado para o seu 
    //public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    // "root" e o usuário do banco
    public static final String USER = "root";
    // PASS é a senha que vc utiliza
    public static final String PASS = "";
    
    //Fazendo a conexão com o banco de dados
    //Devolve uma conexão static
    public static Connection getConnection(){
        // try catch para tratamento de erro, para evitar o erro e para que podemos tratar 
        try {
            //Informa o drive que vai ser utilizado 
            Class.forName(DRIVE);
            // Passando as informações de acesso ao banco
            // Estou falando "DriverManager" faz uma conexão com esses parametros
            return DriverManager.getConnection(URL,USER,PASS);
            
           //Caso o try acontersa sem erro o catch não utilizado
        } catch (Exception ex) {
            //Mostra o erro quando o banco não for acessado
            throw new RuntimeException("Erro na conexão com o banco de dados",ex);
        }
    }
      public static void closeConnection(Connection connection){
        try {
            // Se conexão for difernete de nulo fechar 
            if(connection != null){
                connection.close();
            }          
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão com o banco de dados");
        }
    }
    //Fechando a conexão com o banco de dados
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try {
            // Se conexão for difernete de nulo fechar 
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão com o banco de dados");
        }
    }
    
}
