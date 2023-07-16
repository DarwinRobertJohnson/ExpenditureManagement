

import java.lang.*;
import java.sql.ResultSet;
import java.util.ArrayList;


class ExpenseData{
    String ExpenseName;
    Float ExpenseAmount;
    
    public ExpenseData(String ExpenseName,Float ExpenseAmount){
        this.ExpenseName=ExpenseName;
        this.ExpenseAmount=ExpenseAmount;
    }

    public void setData(String ExpenseName,Float ExpenseAmount){
        this.ExpenseName=ExpenseName;
        this.ExpenseAmount=ExpenseAmount;
    }

    public String getName(){
        return ExpenseName;
    }

    public Float getAmount(){
        return ExpenseAmount;
    }
}



public class reader extends dbConnect{

    ResultSet readData;
    ArrayList<ExpenseData> expData;
    ExpenseData dummyExpData;
    String data[][];



    public reader(String date) throws Exception{
        expData=new ArrayList<>();
        date=processDate(date);
        String query="select * from "+date;
        readerStatement=con.prepareStatement(query);
        readData=readerStatement.executeQuery();
        this.loadData();
    }

    //Loads a particular date's expense data into readData
    public void getDataFromDate(String date){
        expData=new ArrayList<>();
        date=processDate(date);
        String query="select * from "+date;
        readerStatement=con.prepareStatement(query);
        readData=readerStatement.executeQuery();
        this.loadData();
    }

    //Loads the data from readData into expData arrayList
    public void loadData() throws Exception{
        while(readData.next()){
            String name=readData.getString("ExpenditureName");
            Float amount=readData.getFloat("ExpAmount");
            dummyExpData=new ExpenseData(name,amount);          
            expData.add(dummyExpData);
        }
        
    }


    //Returns a 2D String array with expense name and amount with last row being the total
    public String[][] getData() throws Exception{
        int length=expData.size();
        Float totalAmount=new Float(0);
        data=new String[length+1][2];

        for(int i=0;i<length;i++){
            data[i][0]=(expData.get(i)).getName();
            data[i][1]=(expData.get(i)).getAmount().toString();
            totalAmount+=(expData.get(i)).getAmount();
        }
        data[length][0]="Total";
        data[length][1]=totalAmount.toString();

        return data;
    }
}