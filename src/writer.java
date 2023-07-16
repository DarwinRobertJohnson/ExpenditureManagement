import java.lang.*;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class writer extends dbConnect{
    String date;
    public writer() throws Exception{

    }


    //Creates a table of required date when needed
    public void createTable(String date) throws Exception{
        date=processDate(date);
        String query="create table "+date+"(ExpenditureName text,ExpAmount float)";

        writerStatement=con.prepareStatement(query);
        writerStatement.executeUpdate();
    }



    //Writes some default values into table         ---Test Purpose Only
    public void write() throws Exception{
        writerStatement.setString(1,"abcd");
        writerStatement.setFloat(2,53);

        writerStatement.executeUpdate();
    }


    //Converts the date to required form for database purposes
    public static String processDate(String date){
        return ("_"+date.substring(0,2)+"_"+date.substring(3,5)+"_"+date.substring(6,10));
    }



    //Writes the given data into the correct table  ---Actual Implementation 
    public void write(String expName,float expAmount,String date) throws Exception{
        //date="_09_07_2023";

        if(!tableExists(date)){
            createTable(date);
        }

        date=processDate(date);

        updateDate(date);
        writerStatement.setString(1,expName);
        writerStatement.setFloat(2,expAmount);

        writerStatement.executeUpdate();
    }

}