

import java.lang.*;
import java.sql.ResultSet;
import java.util.ArrayList;






public class reader extends dbConnect{

    ResultSet readData;
    ArrayList<ExpenseData> expData;
    ExpenseData dummyExpData;
    String data[][];
    Float total;

    public reader() throws Exception{}

    public reader(String date) throws Exception{
        total=new Float(0);
        expData=new ArrayList<>();
        date=processDate(date);
        String query="select * from "+date;
        readerStatement=con.prepareStatement(query);
        readData=readerStatement.executeQuery();
        this.loadData();
    }

    //Loads a particular date's expense data into readData
    public void getDataFromDate(String date) throws Exception{
        total=new Float(0);
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
            total+=amount;
            dummyExpData=new ExpenseData(name,amount);          
            expData.add(dummyExpData);
        }
        
    }

    public Float getTotalFromDate(String date) throws Exception{
        getDataFromDate(date);

        return total;
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