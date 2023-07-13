

import java.lang.*;
import javax.swing.*;
import java.awt.FlowLayout;

public class error extends JFrame{

    JLabel errorMessage;

    public error(String errorMess){
        errorMessage=new JLabel(errorMess);
        //errorMessage.setText(errorMess);
        add(errorMessage);

        setVisible(true);
        setSize(480,144);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}