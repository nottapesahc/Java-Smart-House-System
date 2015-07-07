package smarthouse;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.logging.Level;


public class doorModel{

    public char alarmStatus;
    private int ID;
    private boolean alarm;
    
    public doorModel(){
        alarmStatus = 'o';
        alarm = false;
    }
    
    public doorModel(char x){
        if(x == 's' || x == 'a' || x == 'o'){
            alarmStatus = x;
        }
        
        else
            alarmStatus = 'o';
        
        alarm = false;
    }
    
    public void setAlarmStatus(char x){
        alarmStatus = x;
    }
    
    public String getAlarmStatus(){
        if(alarmStatus == 's')
            return "stay";
        else if(alarmStatus == 'a')
            return "away";
        else
            return "off";
    }
    
    public void setID(int x){
        ID = x;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setAlarm(boolean x){
        alarm = x;
    }
    
    public boolean getAlarm(){
        return alarm;
    }
    
    public void raiseAlarm(){
        //What the fuck do I put here?
    }
    
     public void writeToLog(){
        //
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("doorLog.txt", true)))) {
            Calendar now = Calendar.getInstance();
            out.println("\nID Code:"+ ID + "("+ now.get(Calendar.MONTH) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" 
            + now.get(Calendar.YEAR) + " " + now.get(Calendar.HOUR_OF_DAY) + ":"
                     + now.get(Calendar.MINUTE) + "): " + "alarm status: " + alarmStatus);
    }
        catch (IOException e) {
            
    }      
        
    }
    
    public void readFile(File file){
        
        Scanner x;
        String fileLine = null;
        int index;
        
        try {
            
            x = new Scanner(file);
            while (x.hasNextLine()) {
            fileLine = x.nextLine();
            
        }
            
            x.close();
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(doorModel.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       index = fileLine.indexOf("alarm status:") + 13;
        //Scanner z = new Scanner(fileLine);
       
       String as = fileLine.substring(index);
               
       if(as.contains("s"))
           alarmStatus = 's';
       else if(as.contains("a"))
           alarmStatus = 'a';
       else
           alarmStatus = 'o';
    } 
    
}