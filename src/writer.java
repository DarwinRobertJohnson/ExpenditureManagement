import java.lang.*;


public class writer extends dbConnect{

    public writer() throws Exception{

    }

    public void write() throws Exception{
        writerStatement.setString(1,"abcd");
        writerStatement.setFloat(2,53);

        writerStatement.executeUpdate();
    }

    public void write(String expName,float expAmount) throws Exception{
        writerStatement.setString(1,"nothing");
        writerStatement.setFloat(2,0);
    }

}