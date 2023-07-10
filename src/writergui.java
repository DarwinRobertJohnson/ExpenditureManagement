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





public class writergui extends JFrame implements ActionListener{

    JLabel ExpNameLabel;
    JLabel ExpAmountLabel;
    JTextField ExpNameTB;
    JTextField ExpAmountTB;
    JButton submitButton;
    JDateChooser selectDate;
    writer dataWriter;

    public writergui(){

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

        System.out.println(date);
    }   



    public static void main(String[] args){
        writergui gui=new writergui();
    }

}