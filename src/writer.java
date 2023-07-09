import java.lang.*;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class writer extends dbConnect{
    String date;
    public writer() throws Exception{

    }


    //Creates a table of required date when needed
    public void createTable(String date){

    }



    //Checks if a table with given date exists
    public boolean tableExists(String date) throws Exception{

        DatabaseMetaData metaData=con.getMetaData();
        ResultSet tableList=metaData.getTables(null,null,null,new String[]{"TABLE"});

        while(tableList.next()){
        
        String tableName=tableList.getString("TABLE_NAME");

        if(tableName==date)
            return true;
        }
        
        return false;
    }


    //Writes some default values into table         ---Test Purpose Only
    public void write() throws Exception{
        writerStatement.setString(1,"abcd");
        writerStatement.setFloat(2,53);

        writerStatement.executeUpdate();
    }


    //Writes the given data into the correct table  ---Actual Implementation 
    public void write(String expName,float expAmount,String date) throws Exception{
        date="_09_07_2023";

        if(!tableExists(date)){
            createTable(date);
        }

        updateDate(date);
        writerStatement.setString(1,expName);
        writerStatement.setFloat(2,expAmount);

        writerStatement.executeUpdate();
    }

}