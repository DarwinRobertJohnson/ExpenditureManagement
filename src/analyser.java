//      @Rampage


import java.lang.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class analyser extends reader{
    public analyser(){}

    //turns string date to localdate format
    public LocalDate getLocalDate(String Date){
        String dateString =Date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }

    //Gets the total from a starting date to ending date,total for day wise is also displayed
    public String[][] getDataFromDateRange(String startDate,String endDate){
        if(startDate.equals(endDate))
            return null;
        
        LocalDate startDateLDFormat=getLocalDate(startDate);
        LocalDate endDateLDFormat=getLocalDate(endDate);
        while(!startDateLDFormat.equals(endDateLDFormat)){
            startDateLDFormat=startDateLDFormat.plusDays(1);
        }

    }
}