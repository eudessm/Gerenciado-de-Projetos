/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp;

import java.sql.Connection;
import util.ConnectionFactory;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        
        //Na main chamamos a classe de conex�o
        Connection c = ConnectionFactory.getConnection();
        //depois de conectar e n�o precisamos mais utilizar fechamos
        ConnectionFactory.closeConnection(c);
    }
}
