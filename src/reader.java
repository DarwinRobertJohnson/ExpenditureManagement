

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
}

public class reader extends dbConnect{

    ResultSet readData;
    ArrayList<ExpenseData> expData;
    ExpenseData dummyExpData;

    public reader(String date) throws Exception{
        expData=new ArrayList<>();
        dummyExpData=new ExpenseData();
        date=processDate(date);
        String query="select * from "+date;
        readerStatement=con.prepareStatement(query);
        readData=readerStatement.executeQuery();
    }

    public void loadData() throws Exception{
        while(readData.next()){
            dummyExpData.setData(readData.getString("ExpenditureName"),readData.getFloat("ExpAmount"));          
            expData.add(dummyExpData);
        }
        System.out.println("Data loaded");
    }
}