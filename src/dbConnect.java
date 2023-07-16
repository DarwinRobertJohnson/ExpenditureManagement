import java.lang.*;
import java.sql.*;


class ExpenseData{
    String ExpenseName;
    Float ExpenseAmount;
    
    public ExpenseData(String ExpenseName,Float ExpenseAmount){
        this.ExpenseName=ExpenseName;
        this.ExpenseAmount=ExpenseAmount;
    }

    public void setData(String ExpenseName,Float ExpenseAmount){
        this.ExpenseName=ExpenseName;
        this.ExpenseAmount=ExpenseAmount;
    }

    public String getName(){
        return ExpenseName;
    }

    public Float getAmount(){
        return ExpenseAmount;
    }
}


public class dbConnect{

    Connection con;
    PreparedStatement writerStatement;
    PreparedStatement readerStatement;
    String query;

    public dbConnect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:../database/Expenditure");
        // query="insert into "+"_08_07_2023" +" values(?,?)";
        // writerStatement=con.prepareStatement(query);   

    }

    public void updateDate(String date) throws Exception{
        query="insert into "+date+" values(?,?)";
        writerStatement=con.prepareStatement(query);
    }

    //Converts the date to required form for database purposes
    public static String processDate(String date){
        return ("_"+date.substring(0,2)+"_"+date.substring(3,5)+"_"+date.substring(6,10));
    }


    //Checks if a table with given date exists
    public boolean tableExists(String date) throws Exception{

        DatabaseMetaData metaData=con.getMetaData();
        ResultSet tableList=metaData.getTables(null,null,null,new String[]{"TABLE"});

        while(tableList.next()){
        
        String tableName=tableList.getString("TABLE_NAME");

        if(tableName.equals(date))
            return true;
        
        }
        
        return false;
    }
}