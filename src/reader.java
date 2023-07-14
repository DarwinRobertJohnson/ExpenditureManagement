

import java.lang.*;
import java.sql.ResultSet;
import java.util.ArrayList;


class ExpenseData{
    String ExpenseName;
    Float ExpenseAmount;
    
    public ExpenseData(){}

    public void setData(String ExpenseName,Float ExpenseAmount){
        this.ExpenseName=ExpenseName;
        this.ExpenseAmount=ExpenseAmount;
    }

    public String getName(){
        return ExpenseName;
    }

    public String getAmount(){
        return ExpenseAmount.toString();
    }
}

public class reader extends dbConnect{

    ResultSet readData;
    ArrayList<ExpenseData> expData;
    ExpenseData dummyExpData;
    String data[][];



    public reader(String date) throws Exception{
        expData=new ArrayList<>();
        dummyExpData=new ExpenseData();
        date=processDate(date);
        String query="select * from "+date;
        readerStatement=con.prepareStatement(query);
        readData=readerStatement.executeQuery();
        this.loadData();
    }

    public void loadData() throws Exception{
        while(readData.next()){
            dummyExpData.setData(readData.getString("ExpenditureName"),readData.getFloat("ExpAmount"));          
            expData.add(dummyExpData);
        }
        System.out.println("Data loaded");
    }

    public String[][] getData() throws Exception{
        int length=expData.size();
        data=new String[length][2];


        for(int i=0;i<length;i++){
            data[i][0]=expData.get(i).getName();
            data[i][1]=expData.get(i).getAmount();
        }

        return data;
    }
}