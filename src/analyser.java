//      @Rampage


import java.lang.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class analyser extends reader{
    String data[][];
    public analyser() throws Exception{}

    //turns string date to localdate format
    public LocalDate getLocalDate(String Date){
        String dateString =Date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }

    public String getStringDate(LocalDate LDdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString=LDdate.format(formatter);
        return dateString;
    }

    //Gets the total from a starting date to ending date,total for day wise is also displayed
    public String[][] getDataFromDateRange(String startDate,String endDate) throws Exception{
        
        String dummy[][]=new String[1][1];
        if(startDate.equals(endDate))
            return dummy;
        
        
        String date=startDate;
        LocalDate startDateLDFormat=getLocalDate(startDate);
        LocalDate endDateLDFormat=getLocalDate(endDate);
        ArrayList<ExpenseData> expenseData=new ArrayList<>();
        ExpenseData dataForList;

        while(!startDateLDFormat.equals(endDateLDFormat)){

            startDate=getStringDate(startDateLDFormat);
            String stringDate=processDate(startDate);
            System.out.println(stringDate);
            
            if(tableExists(stringDate)){
                dataForList=new ExpenseData(startDate,getTotalFromDate(startDate));
                expenseData.add(dataForList);
                System.out.println("Table exists "+startDate);
            }
            startDateLDFormat=startDateLDFormat.plusDays(1);
            System.out.print(".");
        }
        System.out.println("Data acquired "+expenseData.size());
        int length=expenseData.size();
        data=new String[length+1][2];
        Float total=new Float(0);

        //Insert the data from arrayList into string list

        for(int i=0;i<length;i++){
            data[i][0]=expenseData.get(i).getName();
            data[i][1]=expenseData.get(i).getAmount().toString();
            total+=expenseData.get(i).getAmount();
        }
        data[length][0]="Total";
        data[length][1]=total.toString();

        return data;

    }
}