/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import static smarthouse.ClockRadioGUI.ID;

/**
 *
 * @author Matt
 */
public class ClockRadio {
    private int alarmTimeHour;
    private int alarmTimeMin;
    private String alarmDays;
    private String alarmSound;
    private int alarmSnooze;
    private String userID;
    private int room;
    private String roomString="Living Room";
    //
   // private String file;
    
    public ClockRadio(){
       // alarmTimeHour = hour;
    }
    
    public ClockRadio(int hour, int min, String days, String sound, int snooze, int r){
        alarmTimeHour = hour;
        alarmTimeMin = min;
        alarmDays = days;
        alarmSound = sound;
        alarmSnooze= snooze;
        room =r;
    }
    
    public void setRoom(int r){
        room = r;
    }
    public int getRoom(){
        return room;
    }
    
    public void setAlarmHour(int hour){          
        alarmTimeHour = hour;              
    }
    
    public int getAlarmHour(){
        return alarmTimeHour;
    }
    
    public String getUserID(){
        return userID;
    }
    public void setUserID(String x){
        userID = x;
    }
    
    public void setAlarmMin(int min){
        alarmTimeMin =min;        
    }
    
    public int getAlarmMin(){
        return alarmTimeMin;
    }
    
    public void setSound(String sound){
        alarmSound = sound;
    }
    
    public String getSound(){
        return alarmSound;
    }
    
    public void setDays(String days){
        alarmDays =days;
    }
    
    public String getDays(){
        return alarmDays;
    }
    
    public void setSnooze(int sTime){
        alarmSnooze = sTime;
    }
    
    public int getSnooze(){
        return alarmSnooze;
    }
    
    public void resetAlarm(){
        alarmTimeHour = -1;
        alarmTimeMin = -1;
        alarmDays = null;
        alarmSound = null;
        alarmSnooze= -1;
        writeToLog();
    }
    
    public void writeToLog(){
        
        //String roomString=null;
        setRoomString();
      
        //
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ClockRadioLog.txt", true)))) {
            Calendar now = Calendar.getInstance();
            out.println("\nID Code:"+userID + "("+ now.get(Calendar.MONTH) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" 
            + now.get(Calendar.YEAR) + " " + now.get(Calendar.HOUR_OF_DAY) + ":"
                     + now.get(Calendar.MINUTE) + ")" + roomString + ": " + "Alarm Time:" + alarmTimeHour + ":" + alarmTimeMin 
            + " Alarm Days:" + alarmDays + " Alarm Sound:" + alarmSound +
                     " Alarm Snooze:" + alarmSnooze + ".");
    }catch (IOException e) {
    }      
    }
    
    public void readFile(File file){
        setRoomString();
        Scanner x;
        String fileLine = null;
        String roomLine = null;
        
        int index;
        int index2;
        int index3;
        int index4;
        int index5;
        int index6;
        int index7;
        try {
            x = new Scanner(file);
            while (x.hasNextLine()) {
                
            fileLine = x.nextLine();
            if(fileLine.contains(roomString)){
                roomLine = fileLine;
            }
               // System.out.println(fileLine);
            
        }
            x.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClockRadio.class.getName()).log(Level.SEVERE, null, ex);
        }
       index = roomLine.indexOf("Time:")+5;
        //Scanner z = new Scanner(fileLine);
        
        String s = roomLine.substring(index);
         index2 = s.indexOf(":");
        
        String s2 = s.substring(0, index2);
        alarmTimeHour = Integer.parseInt(s2);
         index3 = s.indexOf(" Alarm Days");
        String s3 = s.substring(1+index2, index3);
        alarmTimeMin = Integer.parseInt(s3);
        if(roomLine.contains("Buzzer")){
            alarmSound = "Buzzer";
        }
        if(roomLine.contains("Radio")){
            alarmSound = "Radio";
        }
         index4 = roomLine.indexOf("Snooze:") +7;
        index5 = roomLine.indexOf(".");
        
        String s4 = roomLine.substring(index4, index5);
        alarmSnooze = Integer.parseInt(s4);
        index6 = roomLine.indexOf("Days:");
         index7 = roomLine.indexOf(" Alarm So");
        String s5 = roomLine.substring(5+index6, index7);
        alarmDays="";
        if(s5.contains("M")){
            alarmDays = alarmDays + "M ";
        }
         if(s5.contains("Tue")){
            alarmDays = alarmDays +"Tue ";
        }
         if(s5.contains("W")){
            alarmDays = alarmDays+"W ";
        }
         if(s5.contains("Thu")){
            alarmDays = alarmDays + "Thu ";
        }
        if(s5.contains("F")){
            alarmDays = alarmDays + "F ";
        }
        if(s5.contains("Sat")){
            alarmDays = alarmDays + "Sat ";
        }
        if(s5.contains("Sun")){
            alarmDays = alarmDays + "Sun ";
        }
    }
    
    public void setRoomString(){
        switch(room){
            case 1:
                roomString = "Living Room";
                break;
            case 2:
                roomString = "Bedroom1";
                break;
            case 3:
                roomString = "Bedroom2";
                break;
        }
    }
    }

