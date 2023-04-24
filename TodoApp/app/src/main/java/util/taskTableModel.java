package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;
import org.checkerframework.common.reflection.qual.GetClass;

/**
 *
 * @author eudes
 */
public class taskTableModel extends AbstractTableModel {

    String [] columns = {"Nome"+"Descrição"+"Prazo"+"Tarefa Conlcuida"+"Editar"+"Excluir"};
    List<Task> tasks = new ArrayList();
    
    //Quantas linhas a tabela vai ter 
    @Override
    public int getRowCount() {
        //retorna a quantidade de tarefas 
        return tasks.size();
    }

    //Quantidade de colunas
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    //Faz uma pegunta se essa selula e essa linha são editaveis 
    public boolean isCellEditable(int rowIndex, int columnIndex){        
        if(columnIndex == 3){
            return true;
        }else{
            return false;
        }        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        //1º verificando se a minha lista de tarefa é vazia
        if(tasks.isEmpty()){
            //sendo vazia retorna um tipo objeto
            
            return Object.class;
        }else{
            //na coluna 0 vc vai ter um dado do tipo que foi definido na implementação  
            return this.getValueAt(0, columnIndex).getClass();
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex) {
            //Se a coluna for 1, vai retorna o nome 
            case 0:
                //retorna o nome da tarefa
                return tasks.get(rowIndex).getName();
            case 1:
                //retorna descrição
                return tasks.get(rowIndex).getDescription();
            case 2:
                //retorna a data formatada para dd/mm/aaaa
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3:
                //retorna se está concluida ou não 
                return tasks.get(rowIndex).isIsCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                throw new AssertionError();
        }
    }
    
    //Recebi o valor que foi setado na tabela 
    @Override
    public void setValueAt(Object aValue,int rewIndex, int columnIndex){
        //pega o valor recebido e seta se ele ta concluido ou não 
        tasks.get(rewIndex).setIsCompleted((boolean)aValue);
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
}
