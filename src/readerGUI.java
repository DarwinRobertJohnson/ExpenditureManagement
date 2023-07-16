//      @Rampage

import java.lang.*;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;


public class readerGUI extends JFrame implements ActionListener{

    JTable contentTable;
    String data[][];
    JButton submitButton;
    reader dataReader;
    JDateChooser selectDate;

    public readerGUI() throws Exception{

        submitButton=new JButton("submit");
        submitButton.addActionListener(this);
        selectDate=new JDateChooser();

        add(selectDate);
        add(submitButton);

        setLayout(new GridLayout(1,2));
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //when submit button is clicked data of the selected date is loaded and displayed
    public void actionPerformed(ActionEvent e){
        Date selectedDate=selectDate.getDate();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String date=dateFormat.format(selectedDate);

        try{
        dataReader=new reader(date);

        data=dataReader.getData();
        }
        catch(Exception R){
            System.out.println("Error creating dataReader object or getting data");
        }
        String titles[]={"Expense Name","Expense Amount"};
    
        contentTable=new JTable(data,titles);
        JFrame sheetFrame=new JFrame();
        sheetFrame.add(contentTable);
        sheetFrame.setVisible(true);
        sheetFrame.pack();
        // sheetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception{
        readerGUI myGUI=new readerGUI();
    }
}