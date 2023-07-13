//  @Rampage

import java.lang.*;
import javax.swing.*;
import java.awt.GridLayout;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;


class writer extends dbConnect{
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


    // //Converts the date to required form for database purposes
    // public static String processDate(String date){
    //     return ("_"+date.substring(0,2)+"_"+date.substring(3,5)+"_"+date.substring(6,10));
    // }



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

public class writergui extends JFrame implements ActionListener{

    JLabel ExpNameLabel;
    JLabel ExpAmountLabel;
    JTextField ExpNameTB;
    JTextField ExpAmountTB;
    JButton submitButton;
    JDateChooser selectDate;
    writer dataWriter;
    error newError;

    public writergui() throws Exception{

        try{
        dataWriter=new writer();
        }
        catch(Exception E){
            System.out.println(E);
        }


        ExpNameLabel=new JLabel("Expenditure Name:");
        ExpNameTB=new JTextField();
        ExpAmountLabel=new JLabel("Expediture Amount:");
        ExpAmountTB=new JTextField();
        submitButton=new JButton("Submit");
        selectDate=new JDateChooser();

        submitButton.addActionListener(this);

        add(ExpNameLabel);
        add(ExpNameTB);
        add(ExpAmountLabel);
        add(ExpAmountTB);
        add(selectDate);
        add(submitButton);

        setLayout(new GridLayout(4,2));

        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    //Does the actions required when submit button is clicked
    public void actionPerformed(ActionEvent e){
        Date selectedDate=selectDate.getDate();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String date=dateFormat.format(selectedDate);
        String expenditurename=ExpNameTB.getText();
        Float expenditureamount=Float.parseFloat(ExpAmountTB.getText());

        //System.out.println(date);
        try{
            if(!expenditureamount.equals("") && !expenditureamount.equals("") && selectedDate!=null)
                dataWriter.write(expenditurename,expenditureamount,date);
            else{
                newError=new error("Fill all the details");
            }
        }
        catch(Exception E){
            System.out.println("Error writing data from gui module");
            System.out.println(E);
        }
    }   



    public static void main(String[] args) throws Exception{
        writergui gui=new writergui();
    }

}