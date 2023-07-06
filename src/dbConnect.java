import java.lang.*;
import java.sql.*;

public class dbConnect{

    Connection con;

    public dbConnect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:../database/Expenditure");

    }
}