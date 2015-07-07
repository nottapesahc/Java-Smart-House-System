package smarthouse;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.JTabbedPane;
import java.util.Scanner;

public class DoorKeypad extends JFrame {

    private String entry = "";
    private StringBuilder s;
    private JTextField enterID;
    private JLabel message;
    private JLabel alarmStatus;
    private JTextField aStat;
    private JPanel mainPanel;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;//system specific buttons
    private JPanel panel8;
    private JButton stay;
    private JButton away;
    private JButton off;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton zero;
    private JButton enter;
    private JButton clear, logout;
    private String door1Status = "closed";
    private String door2Status = "closed";
    private StringBuilder t;
    private JLabel message1;
    private JLabel message2;
    private JPanel main;
    private JPanel sPanel1;
    private JPanel sPanel2;
    private JTextField D1Stat;
    private JTextField D2Stat;
    private JButton toggleDoor1;
    private JButton toggleDoor2;
    private File doorLog;
    public JLabel buzzer;
    doorModel dm = new doorModel();
    Admin a = new Admin();
    
    public DoorKeypad() {
        doorLog = new File("doorLog.txt");
        dm.readFile(doorLog);
        //aStat.setText("alarm: " + dm.getAlarmStatus() + " mode");
        setLayout(new FlowLayout());
        JTabbedPane tab = new JTabbedPane();
        tab.setPreferredSize(new Dimension(1000,1000));   
        add(tab);
        buildPanel();
        buildSecondPanel();
        tab.addTab("keypad", mainPanel);
        tab.addTab("door toggle", main);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void buildPanel() {
        message = new JLabel("Enter User ID: ");
        message.setFont(message.getFont().deriveFont(32.0f));

        alarmStatus = new JLabel("Alarm: ");
        alarmStatus.setFont(alarmStatus.getFont().deriveFont(32.0f));
        
        buzzer = new JLabel("ALARM HAS BEEN RAISED");
        buzzer.setFont(buzzer.getFont().deriveFont(32.0f));

        enterID = new JTextField(8);
        enterID.setFont(new Font("Arial", Font.PLAIN, 15));
        aStat = new JTextField(17);
        aStat.setFont(new Font("Arial", Font.PLAIN, 40));
        one = new JButton("1");
        one.setFont(new Font("Arial", Font.PLAIN, 40));
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.PLAIN, 20));
        two = new JButton("2");
        two.setFont(new Font("Arial", Font.PLAIN, 40));
        three = new JButton("3");
        three.setFont(new Font("Arial", Font.PLAIN, 40));
        four = new JButton("4");
        four.setFont(new Font("Arial", Font.PLAIN, 40));
        five = new JButton("5");
        five.setFont(new Font("Arial", Font.PLAIN, 40));
        six = new JButton("6");
        six.setFont(new Font("Arial", Font.PLAIN, 40));
        seven = new JButton("7");
        seven.setFont(new Font("Arial", Font.PLAIN, 40));
        eight = new JButton("8");
        eight.setFont(new Font("Arial", Font.PLAIN, 40));
        nine = new JButton("9");
        nine.setFont(new Font("Arial", Font.PLAIN, 40));
        zero = new JButton("0");
        zero.setFont(new Font("Arial", Font.PLAIN, 40));

        enter = new JButton("Enter");
        enter.setFont(new Font("Arial", Font.PLAIN, 15));
        clear = new JButton("Clear");
        clear.setFont(new Font("Arial", Font.PLAIN, 15));

        stay = new JButton("Stay");
        stay.setFont(new Font("Arial", Font.PLAIN, 40));
        away = new JButton("Away");
        away.setFont(new Font("Arial", Font.PLAIN, 40));
        off = new JButton("Off");
        off.setFont(new Font("Arial", Font.PLAIN, 40));

        one.setPreferredSize(new Dimension(100, 100));
        two.setPreferredSize(new Dimension(100, 100));
        three.setPreferredSize(new Dimension(100, 100));
        four.setPreferredSize(new Dimension(100, 100));
        five.setPreferredSize(new Dimension(100, 100));
        six.setPreferredSize(new Dimension(100, 100));
        seven.setPreferredSize(new Dimension(100, 100));
        eight.setPreferredSize(new Dimension(100, 100));
        nine.setPreferredSize(new Dimension(100, 100));
        zero.setPreferredSize(new Dimension(100, 100));
         logout.setPreferredSize(new Dimension(100, 50));

        enter.setPreferredSize(new Dimension(100, 100));
        clear.setPreferredSize(new Dimension(100, 100));
        enterID.setPreferredSize(new Dimension(200, 50));

        stay.setPreferredSize(new Dimension(200, 100));
        away.setPreferredSize(new Dimension(200, 100));
        off.setPreferredSize(new Dimension(200, 100));
        alarmStatus.setPreferredSize(new Dimension(200, 50));
        buzzer.setPreferredSize(new Dimension(500,100));
        buzzer.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GRAY);
        panel2 = new JPanel();
        // panel2.setLayout(null);
        panel2.setLocation(10, 5);
        panel2.setSize(350, 60);
        panel2.add(message);
        panel2.add(enterID);
        panel2.setBackground(Color.GREEN);

        panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);
        panel3.setLocation(10, 60);
        panel3.setSize(350, 110);
        panel3.add(one);
        panel3.add(two);
        panel3.add(three);

        panel4 = new JPanel();
        panel4.setBackground(Color.GREEN);
        panel4.setLocation(10, 165);
        panel4.setSize(350, 110);
        panel4.add(four);
        panel4.add(five);
        panel4.add(six);

        panel5 = new JPanel();
        panel5.setBackground(Color.GREEN);
        panel5.setLocation(10, 270);
        panel5.setSize(350, 110);
        panel5.add(seven);
        panel5.add(eight);
        panel5.add(nine);

        panel6 = new JPanel();
        panel6.setBackground(Color.GREEN);
        panel6.setLocation(10, 375);
        panel6.setSize(350, 175);
        panel6.add(clear);
        panel6.add(zero);
        panel6.add(enter);
        panel6.add(logout);
        logout.setVisible(false);
        

        panel7 = new JPanel();
        panel7.setBackground(Color.GRAY);
        panel7.setLocation(360, 5);
        panel7.setSize(630, 110);
        panel7.add(stay);
        panel7.add(away);
        panel7.add(off);
        panel7.setVisible(false);

        panel8 = new JPanel();
        panel8.setBackground(Color.GRAY);
        panel8.setLocation(360, 110);
        panel8.setSize(630, 440);
        panel8.add(alarmStatus);
        panel8.add(aStat);
        panel8.add(buzzer);
        panel8.setVisible(false);

        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
        mainPanel.add(panel7);
        mainPanel.add(panel8);

        one.addActionListener(new keypadListener());
        two.addActionListener(new keypadListener());
        three.addActionListener(new keypadListener());
        four.addActionListener(new keypadListener());
        five.addActionListener(new keypadListener());
        six.addActionListener(new keypadListener());
        seven.addActionListener(new keypadListener());
        eight.addActionListener(new keypadListener());
        nine.addActionListener(new keypadListener());
        zero.addActionListener(new keypadListener());

        clear.addActionListener(new keypadListener());
        enter.addActionListener(new keypadListener());

        stay.addActionListener(new keypadListener());
        away.addActionListener(new keypadListener());
        off.addActionListener(new keypadListener());
        logout.addActionListener(new keypadListener());


    }

    private void buildSecondPanel() {

        message1 = new JLabel("Door one status: ");
        message1.setFont(message1.getFont().deriveFont(32.0f));

        message2 = new JLabel("Door two status: ");
        message2.setFont(message2.getFont().deriveFont(32.0f));

        toggleDoor1 = new JButton("Toggle 1");
        toggleDoor2 = new JButton("Toggle 2");

        toggleDoor1.setPreferredSize(new Dimension(100, 100));
        toggleDoor2.setPreferredSize(new Dimension(100, 100));

        D1Stat = new JTextField(10);
        D2Stat = new JTextField(10);

        main = new JPanel();
        main.setLayout(null);
        main.setBackground(Color.BLUE);

        sPanel1 = new JPanel();
        sPanel1.setLocation(10, 10);
        sPanel1.setSize(390, 240);
        sPanel1.add(message1);
        sPanel1.add(D1Stat);
        sPanel1.add(toggleDoor1);
        sPanel1.setBackground(Color.RED);

        sPanel2 = new JPanel();
        sPanel2.setLocation(410, 10);
        sPanel2.setSize(390, 240);
        sPanel2.add(message2);
        sPanel2.add(D2Stat);
        sPanel2.add(toggleDoor2);
        sPanel2.setBackground(Color.RED);

        main.add(sPanel1);
        main.add(sPanel2);

        toggleDoor1.addActionListener(new DoorKeypad.DoorListener());
        toggleDoor2.addActionListener(new DoorKeypad.DoorListener());

    }

    private class DoorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == toggleDoor1) {
                if (door1Status.equals("closed")) {
                    door1Status = "open";
                    D1Stat.setText(door1Status);
                } else {
                    door1Status = "closed";
                    D1Stat.setText(door1Status);
                }


                if (door1Status.equals("open")) {

                    if ((dm.getAlarmStatus().equals("stay")) || (dm.getAlarmStatus().equals("away"))) {
                             panel8.setVisible(true);
                    //buzzer.setVisible(true);
                        buzzer.setVisible(true);

                    }

                } else {
                    D1Stat.setText(door1Status);
                }



            }

            if (ae.getSource() == toggleDoor2) {
                if (door2Status.equals("closed")) {
                    door2Status = "open";
                    D2Stat.setText(door2Status);
                } else {
                    door2Status = "closed";
                    D2Stat.setText(door2Status);
                }

                D2Stat.setText(door2Status);

            }

            if (door2Status.equals("open")) {

                if ((dm.getAlarmStatus().equals("stay")) || (dm.getAlarmStatus().equals("away"))) {
                         panel8.setVisible(true);
                    buzzer.setVisible(true);
                   // buzzer.setVisible(true);
                }

            } else {
                D2Stat.setText(door2Status);
            }

        }
    }

    private class keypadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {


            if (ae.getSource() == one) {
                entry = entry + "1";
                enterID.setText(entry);

            } else if (ae.getSource() == two) {
                entry = entry + "2";
                enterID.setText(entry);
            } else if (ae.getSource() == three) {
                entry = entry + "3";
                enterID.setText(entry);
            } else if (ae.getSource() == four) {
                entry = entry + "4";
                enterID.setText(entry);
            } else if (ae.getSource() == five) {
                entry = entry + "5";
                enterID.setText(entry);
            } else if (ae.getSource() == six) {
                entry = entry + "6";
                enterID.setText(entry);
            } else if (ae.getSource() == seven) {
                entry = entry + "7";
                enterID.setText(entry);
            } else if (ae.getSource() == eight) {
                entry = entry + "8";
                enterID.setText(entry);
            } else if (ae.getSource() == nine) {
                entry = entry + "9";
                enterID.setText(entry);
            } else if (ae.getSource() == zero) {
                entry = entry + "0";
                enterID.setText(entry);
            } else if (ae.getSource() == clear) {
                entry = "";
                enterID.setText(entry);
            } else if (ae.getSource() == enter) {
                dm.setID(Integer.parseInt(entry));
                if(a.validateID(Integer.toString(dm.getID()))&& a.getPrivileges(entry, "login")){
                panel7.setVisible(true);
                panel8.setVisible(true);
                buzzer.setVisible(false);
                logout.setVisible(true);
                } else {
                    panel8.setVisible(true);
                    buzzer.setVisible(true);
                }
                
              
            }else if (ae.getSource()==logout){
                dm.writeToLog();
                 panel7.setVisible(false);
                panel8.setVisible(false);
                logout.setVisible(false);
                
            }
            else if (ae.getSource() == away || ae.getSource() == off || ae.getSource() == stay) {

                if(a.getPrivileges(Integer.toString(dm.getID()), ae.getSource().toString()) == true){
                String temp = entry;

                if (ae.getSource() == away) {
                    dm.setAlarmStatus('a');
                } else if (ae.getSource() == stay) {
                    dm.setAlarmStatus('s');
                } else {
                    dm.setAlarmStatus('o');
                }

                entry = "alarm: " + dm.getAlarmStatus() + " mode";
                aStat.setText(entry);
                dm.writeToLog();
                entry = temp;
                }
                
                else{
                
                    String temp = entry;
                    entry = "No permission; see admin";
                    aStat.setText(entry);
                    entry = temp;
                    
                }               
                
            }
        }
    }
}