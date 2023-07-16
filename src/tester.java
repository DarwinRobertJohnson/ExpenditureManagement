import java.lang.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class tester{

    public static void main(String args[]) throws Exception{
        analyser ann=new analyser();
        ann.getDataFromDateRange("01/07/2023","15/07/2023");
    }
}