import java.lang.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class tester{

    public static void main(String args[]) throws Exception{
        reader newReader=new reader("12/07/2023");
        newReader.loadData();
        String data[][]=newReader.getData();
        System.out.println(data[0][0]+"|"+data[0][1]);
    }
}