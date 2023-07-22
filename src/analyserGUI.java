//      @Rampage

import java.lang.*;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


class gui extends JFrame implements ActionListener{

    JLabel start_date;
    JLabel end_date;
    JDateChooser start_date_dc;
    JDateChooser end_date_dc;
    JButton submitButton;
    analyser Analyser;

    public gui(){
        
        Analyser=new analyser();

        start_date=new JLabel("Start Date:");
        end_date=new JLabel("End Date:");
        start_date_dc=new JDateChooser();
        end_date_dc=new JDateChooser();
        submitButton=new JButton("getAnalytics");
        submitButton.addActionListener(this);
        
        add(start_date);add(start_date_dc);
        add(end_date);add(end_date_dc);
        add(submitButton);


        setLayout(new GridLayout(3,2));
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent E){
        Date selectedDate=start_date_dc.getDate();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String startDate=dateFormat.format(selectedDate);

        selectedDate=end_date_dc.getDate();
        String endDate=dateFormat.format(selectedDate);

        System.out.println(startDate+" "+endDate);

        String data[][]=Analyser.getDataFromDateRange(startDate,endDate);
    }
}




public class analyserGUI{


    public static void main(String[] args){
        gui myGui=new gui();
    }
}
