/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

//import java.awt.BorderLayout;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import sun.audio.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import javax.swing.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Matt
 */
public class ClockRadioGUI {

    public String entry = "";
    public static String ID = "4444";
    public int counter = 0;

    public String hours = "";
    public String mins = "", days = "", snooze = "", sound = "";
    public File file;
    // private String theDay ="";
    private int hourAlarm = -1, tempMinAlarm = -1, secAlarm = 00, snoozeAlarm = 0;
    protected JTextField enterIDTextBox;
    private JTextField timeTextBox;
    public JTextField hourTextBox;
    public JTextField minTextBox;
    public JTextField snoozeLengthTextBox;
    // public  JLabel enterIDLabel;
    private JLabel clockMessageLabel;
    private JLabel currAlarmInfoLabel;
    private JLabel selectDays;
    private JLabel enterHourLabel;
    private JLabel enterMin;
    private JLabel soundLabel;
    private JLabel snoozeLabel, alarmLabel, currAlarmLabel, tempAlarmLabel = null, childLabel;

    private JPanel clockPanel, switchPanel, childPanel;
    public JPanel mainPanel; //row1 col1
    public JPanel IDEntryPanel;//row1 col2
    public JPanel key123Panel;//row1 col3
    public JPanel key456Panel;//row2
    public JPanel key789Panel;
    public JPanel keyC0SPanel;
    public JPanel setAlarmPanel;
    public JPanel checkAlarmPanel;
    public JPanel systemTitlePanel;
    private JPanel daysPanel;
    private JPanel setTimePanel;
    private JPanel soundsPanel;
    private JPanel snoozePanel, submitPanel, cancelPanel, alarmDisablePanel;
    private JFrame jFrame;
    private JFrame jFrame2, jFrame4;
    private JCheckBox mondayCButton;
    private JCheckBox tuesdayCButton;
    private JCheckBox wedsCButton;
    private JCheckBox thursdayCButton;
    private JCheckBox fridayCButton;
    private JCheckBox satCButton;
    private JCheckBox sunCButton;
    private JRadioButton buzzerRButton;
    private JRadioButton musicRButton;
    private ButtonGroup soundGroup;
    private JButton setAlarmButton;
    public File audio = new File("Buzzer.wav");
    public File audio2 = new File("Radio.wav");
    public InputStream in2;
    public InputStream in;
    public AudioStream as;
    public AudioStream as2;
    public AudioInputStream n;
    public AudioInputStream n2;

    private JButton submitButton, cancelButton, logoutButton, snoozeButton, disableButton, noAlarmButton, okButton, room1Button, room2Button, liveRoomButton, okChildButton;
    ClockRadio domain = new ClockRadio();

    Admin ADomain = new Admin();

    // public static ClockRadio domain;
    //constructer

    public ClockRadioGUI() {
        this.file = new File("ClockRadioLog.txt");
        domain.readFile(file);
        final int WIDTH = 900;
        final int HEIGHT = 525;
        final int WIDTH2 = 300;
        final int HEIGHT2 = 150;
        jFrame = new JFrame("Smart House GUI");
        jFrame2 = new JFrame("Select Room View");
        Dimension d1 = new Dimension(WIDTH2, HEIGHT2);

        //setTitle("Smart House GUI");
        Dimension d = new Dimension(WIDTH, HEIGHT);
        jFrame.setPreferredSize(d);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame2.setPreferredSize(d1);
        jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // jFrame4.setLocation(dim.width/2-jFrame4.getSize().width/2, dim.height/2-jFrame4.getSize().height/2);

        jFrame4 = new JFrame("Access Denied");
        jFrame4.setPreferredSize(d1);
        jFrame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //domain = new ClockRadio();
        buildPanel();
        jFrame.add(mainPanel);
        jFrame2.add(switchPanel);
        jFrame4.add(childPanel);
        jFrame4.setVisible(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame4.setLocation(dim.width / 2 - jFrame4.getSize().width / 2, dim.height / 2 - jFrame4.getSize().height / 2);
        jFrame2.setLocation(dim.width / 8 - jFrame2.getSize().width / 8, dim.height / 8 - jFrame2.getSize().height / 8);

        jFrame.setLocation(dim.width / 4 - jFrame4.getSize().width / 4, dim.height / 4 - jFrame4.getSize().height / 4);

       // jFrame2.add(room1Button);
        //jFrame2.add(room2Button);
        //jFrame2.add(liveRoomButton);
        jFrame.pack();
        jFrame2.pack();
        jFrame4.pack();
        jFrame.setVisible(true);
        jFrame2.setVisible(true);
        //ClockRadioKeyPad k = new ClockRadioKeyPad();
        // IDEntryPanel.getGraphics();
    }
    //builder

    private void buildPanel() {

        try {
            in = new FileInputStream(audio);
            as = new AudioStream(in);
            // AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClockRadioGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClockRadioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            in2 = new FileInputStream(audio2);
            as2 = new AudioStream(in2);
            // AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClockRadioGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClockRadioGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

         // n= AudioSystem.getAudioInputStream(new URL(89.47.247.59:8020));
        clockMessageLabel = new JLabel("Clock Alarm " + "\n" + "System");
        currAlarmInfoLabel = new JLabel("Current Alarm Time: ");
        selectDays = new JLabel("Select days:");
        enterHourLabel = new JLabel("<html>Enter Alarm Time Hour:<br> (Military Hours 0-24) </html>");
        enterMin = new JLabel("Enter Alarm Time Minute:");
        soundLabel = new JLabel("<html>Select Alarm Sound: </html>");
        snoozeLabel = new JLabel("<html>Set Snooze Length (mins): </html>");
        alarmLabel = new JLabel("<html>ALARM " + domain.getSound() + "<br>!!!!!!!WAKE UP!!!!!!!<br>.</html>");
        childLabel = new JLabel("Access Denied: Child User");

        if (domain.getAlarmMin() < 10) {
            currAlarmLabel = new JLabel("<html>Days: " + domain.getDays() + "<br>Time: " + domain.getAlarmHour()
                    + ":0" + domain.getAlarmMin() + "<br>Sound Setting: " + domain.getSound()
                    + "<br>Snooze Length:" + domain.getSnooze() + " Minutes</html>");
        } else {
            currAlarmLabel = new JLabel("<html>Days: " + domain.getDays() + "<br>Time: " + domain.getAlarmHour()
                    + ":" + domain.getAlarmMin() + "<br>Sound Setting: " + domain.getSound()
                    + "<br>Snooze Length:" + domain.getSnooze() + " Minutes</html>");
        }

        currAlarmInfoLabel.setFont(currAlarmInfoLabel.getFont().deriveFont(32.0f));
        currAlarmLabel.setFont(currAlarmLabel.getFont().deriveFont(24.0f));
        //
        clockMessageLabel.setFont(clockMessageLabel.getFont().deriveFont(32.0f));
        selectDays.setFont(selectDays.getFont().deriveFont(16.0f));
        enterHourLabel.setFont(selectDays.getFont().deriveFont(24.0f));
        enterMin.setFont(enterMin.getFont().deriveFont(24.0f));
        soundLabel.setFont(soundLabel.getFont().deriveFont(16.0f));
        snoozeLabel.setFont(snoozeLabel.getFont().deriveFont(16.0f));
        alarmLabel.setFont(alarmLabel.getFont().deriveFont(48.0f));
        childLabel.setFont(childLabel.getFont().deriveFont(20.0f));

        enterIDTextBox = new JTextField(8);
        enterIDTextBox.setFont(enterIDTextBox.getFont().deriveFont(16.0f));
        hourTextBox = new JTextField(2);
        hourTextBox.setPreferredSize(new Dimension(100, 25));
        minTextBox = new JTextField(2);
        snoozeLengthTextBox = new JTextField(2);

        setAlarmButton = new JButton("Set/Disable Alarm");
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        logoutButton = new JButton("Logout");
        disableButton = new JButton("Disable");
        snoozeButton = new JButton("Snooze");
        noAlarmButton = new JButton("Turn Off Alarm");
        okButton = new JButton("Ok");
        room1Button = new JButton("Bedroom 1");
        room2Button = new JButton("Bedroom 2");
        liveRoomButton = new JButton("Living Room");
        okChildButton = new JButton("Ok");

        buzzerRButton = new JRadioButton("Buzzer");
        buzzerRButton.setBackground(Color.GREEN);
        buzzerRButton.setActionCommand("Buzzer");
        musicRButton = new JRadioButton("Radio");
        musicRButton.setBackground(Color.GREEN);
        musicRButton.setActionCommand("Radio");

        submitButton.setFont(submitButton.getFont().deriveFont(16.0f));
        cancelButton.setFont(cancelButton.getFont().deriveFont(16.0f));
        logoutButton.setFont(logoutButton.getFont().deriveFont(16.0f));
        setAlarmButton.setFont(setAlarmButton.getFont().deriveFont(16.0f));
        disableButton.setFont(disableButton.getFont().deriveFont(16.0f));
        snoozeButton.setFont(snoozeButton.getFont().deriveFont(16.0f));
        noAlarmButton.setFont(noAlarmButton.getFont().deriveFont(16.0f));
        room1Button.setFont(room1Button.getFont().deriveFont(8.0f));
        room2Button.setFont(room2Button.getFont().deriveFont(8.0f));
        liveRoomButton.setFont(liveRoomButton.getFont().deriveFont(8.0f));

        setAlarmButton.setPreferredSize(new Dimension(300, 50));
        logoutButton.setPreferredSize(new Dimension(300, 50));
        hourTextBox.setPreferredSize(new Dimension(20, 20));
        submitButton.setPreferredSize(new Dimension(100, 50));
        cancelButton.setPreferredSize(new Dimension(100, 50));
        snoozeButton.setPreferredSize(new Dimension(100, 50));
        disableButton.setPreferredSize(new Dimension(100, 50));
        noAlarmButton.setPreferredSize(new Dimension(200, 50));
        okButton.setPreferredSize(new Dimension(70, 25));
        room1Button.setPreferredSize(new Dimension(100, 50));
        room2Button.setPreferredSize(new Dimension(100, 50));
        liveRoomButton.setPreferredSize(new Dimension(100, 50));

        mondayCButton = new JCheckBox("Monday");
        tuesdayCButton = new JCheckBox("Tuesday");
        wedsCButton = new JCheckBox("Wednesday");
        thursdayCButton = new JCheckBox("Thursday");
        fridayCButton = new JCheckBox("Friday");
        satCButton = new JCheckBox("Saturday");
        sunCButton = new JCheckBox("Sunday");

        mondayCButton.setBackground(Color.GREEN);
        tuesdayCButton.setBackground(Color.GREEN);
        wedsCButton.setBackground(Color.GREEN);
        thursdayCButton.setBackground(Color.GREEN);
        fridayCButton.setBackground(Color.GREEN);
        satCButton.setBackground(Color.GREEN);
        sunCButton.setBackground(Color.GREEN);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GREEN);
        childPanel = new JPanel();
        childPanel.add(childLabel);
        childPanel.add(okChildButton);

        switchPanel = new JPanel();
        // switchPanel.setLayout(null);
        switchPanel.add(room1Button);
        switchPanel.add(room2Button);
        switchPanel.add(liveRoomButton);

        clockPanel = new JPanel();
        timeTextBox = new JTextField(10);
        timeTextBox.setEditable(false);
        timeTextBox.setFont(new Font("Arial", Font.PLAIN, 48));
        // timeTextBox.setSize(500, 70);
        clockPanel.setLocation(380, 20);
        clockPanel.setSize(450, 70);
        clockPanel.setBackground(Color.GREEN);
        timeTextBox.setBackground(Color.GRAY);

        clockPanel.add(timeTextBox);

        Timer t = new Timer(1000, new Listener());
        t.start();

        daysPanel = new JPanel();
        daysPanel.setLocation(400, 140);
        daysPanel.setSize(100, 190);
        daysPanel.setBackground(Color.GREEN);
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));
        daysPanel.add(selectDays);
        daysPanel.add(mondayCButton);
        daysPanel.add(tuesdayCButton);
        daysPanel.add(wedsCButton);
        daysPanel.add(thursdayCButton);
        daysPanel.add(fridayCButton);
        daysPanel.add(satCButton);
        daysPanel.add(sunCButton);
        mainPanel.add(daysPanel);
        daysPanel.setVisible(false);

        alarmDisablePanel = new JPanel();
        alarmDisablePanel.setBackground(Color.RED);
        alarmDisablePanel.setLocation(400, 100);
        alarmDisablePanel.setSize(450, 350);
        alarmDisablePanel.add(alarmLabel);
        alarmDisablePanel.add(disableButton);
        alarmDisablePanel.add(snoozeButton);
        alarmDisablePanel.setVisible(false);
        mainPanel.add(alarmDisablePanel);

        soundsPanel = new JPanel();
        soundGroup = new ButtonGroup();
        soundsPanel.setLocation(400, 350);
        soundsPanel.setBackground(Color.GREEN);
        soundsPanel.setLayout(new BoxLayout(soundsPanel, BoxLayout.Y_AXIS));
        soundsPanel.setSize(100, 100);
        soundsPanel.add(soundLabel);
        soundsPanel.add(buzzerRButton);
        soundsPanel.add(musicRButton);
        soundGroup.add(buzzerRButton);
        soundGroup.add(musicRButton);
        mainPanel.add(soundsPanel);
        soundsPanel.setVisible(false);

        setTimePanel = new JPanel();
        setTimePanel.setLocation(490, 140);
        setTimePanel.setSize(370, 110);
        setTimePanel.setBackground(Color.GREEN);
        //setTimePanel.setLayout(new BoxLayout(setTimePanel, BoxLayout.Y_AXIS));
        mainPanel.add(setTimePanel);

        setTimePanel.add(enterHourLabel);
        setTimePanel.add(hourTextBox);
        //setTimePanel.add(okButton);
        setTimePanel.add(enterMin);
        setTimePanel.add(minTextBox);
        setTimePanel.add(okButton);
        enterMin.setVisible(false);
        //;
        minTextBox.setVisible(false);
        setTimePanel.setVisible(false);

        snoozePanel = new JPanel();
        snoozePanel.setLocation(525, 240);
        snoozePanel.setSize(250, 60);
        snoozePanel.setBackground(Color.GREEN);
        snoozePanel.add(snoozeLabel);
        snoozePanel.add(snoozeLengthTextBox);
        //snoozePanel.add(okButton);
        mainPanel.add(snoozePanel);
        snoozePanel.setVisible(false);

        systemTitlePanel = new JPanel();
        systemTitlePanel.setBackground(Color.GREEN);
        systemTitlePanel.setLocation(440, 200);
        systemTitlePanel.setSize(350, 100);
        systemTitlePanel.add(clockMessageLabel);
        systemTitlePanel.setVisible(true);

        submitPanel = new JPanel();
        submitPanel.setBackground(Color.GREEN);
        submitPanel.setLocation(500, 320);
        submitPanel.setSize(300, 150);
        submitPanel.add(submitButton);
        //submitPanel.add(noAlarmButton);
        submitPanel.add(cancelButton);
        submitPanel.add(noAlarmButton);
        mainPanel.add(submitPanel);
        submitPanel.setVisible(false);

        setAlarmPanel = new JPanel();
        setAlarmPanel.setBackground(Color.GREEN);
        setAlarmPanel.setLocation(400, 350);
        setAlarmPanel.setSize(325, 130);
        setAlarmPanel.add(setAlarmButton);
        setAlarmPanel.add(logoutButton);
        mainPanel.add(setAlarmPanel);
        setAlarmPanel.setVisible(false);

        checkAlarmPanel = new JPanel();
        checkAlarmPanel.setBackground(Color.GREEN);
        checkAlarmPanel.setLocation(400, 100);
        checkAlarmPanel.setSize(350, 200);
        checkAlarmPanel.add(currAlarmInfoLabel);
        checkAlarmPanel.add(currAlarmLabel);
        mainPanel.add(checkAlarmPanel);
        checkAlarmPanel.setVisible(false);


        /*  mainPanel.add(IDEntryPanel);
         mainPanel.add(key123Panel);
         mainPanel.add(key456Panel);
         mainPanel.add(key789Panel);
         mainPanel.add(keyC0SPanel); */
        mainPanel.add(systemTitlePanel);
        mainPanel.add(clockPanel);
        // mainPanel.add(setAlarmPanel);
        //mainPanel.add(checkAlarmPanel);

        setAlarmButton.addActionListener(new keypadListener());
        submitButton.addActionListener(new keypadListener());
        cancelButton.addActionListener(new keypadListener());
        logoutButton.addActionListener(new keypadListener());
        snoozeButton.addActionListener(new keypadListener());
        disableButton.addActionListener(new keypadListener());
        noAlarmButton.addActionListener(new keypadListener());
        okButton.addActionListener(new keypadListener());
        room1Button.addActionListener(new keypadListener());
        room2Button.addActionListener(new keypadListener());
        liveRoomButton.addActionListener(new keypadListener());
        okChildButton.addActionListener(new keypadListener());

        //this.update(this.getGraphics());
    }

    //inner class 
    private class keypadListener implements ActionListener {
          //  ClockRadio domain = new ClockRadio();

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == setAlarmButton) {
                if (ADomain.getPrivileges(ID, "Alarm") == true) {
                    checkAlarmPanel.setVisible(false);
                    daysPanel.setVisible(true);
                    setTimePanel.setVisible(true);
                    setAlarmPanel.setVisible(false);
                    soundsPanel.setVisible(true);
                    //snoozePanel.setVisible(true);
                    submitPanel.setVisible(true);
                    submitButton.setVisible(false);
                    enterHourLabel.setVisible(true);
                    hourTextBox.setVisible(true);
                    okButton.setVisible(true);
                } else {
                    jFrame4.setVisible(true);
                }
                // cancelPanel.setVisible(true);
            } else if (ae.getSource() == cancelButton) {
                // IDEntryPanel.setVisible(false);
                cancelButton();

            } // cancelPanel.setVisible(false);
            // setAlarm.addActionListener(new keypadListener());
            else if (ae.getSource() == logoutButton) {
                logoutButton();

            } else if (ae.getSource() == snoozeButton) {
                entry = enterIDTextBox.getText();
                entry = "";
                enterIDTextBox.setText(entry);
                AudioPlayer.player.stop(as);
                AudioPlayer.player.stop(as2);
                alarmDisablePanel.setVisible(false);
                IDEntryPanel.setVisible(true);
                enterIDTextBox.setVisible(true);
                systemTitlePanel.setVisible(true);
                setAlarmPanel.setVisible(false);
                checkAlarmPanel.setVisible(false);
                tempMinAlarm = domain.getAlarmMin() + domain.getSnooze();

                for (int i = 0; counter > i; i++) {
                    tempMinAlarm = tempMinAlarm + domain.getSnooze();
                }
                counter++;
                    // Calendar now = Calendar.getInstance();

                // alarmDisablePanel.setVisible(false);
            } else if (ae.getSource() == noAlarmButton) {
                domain.resetAlarm();
                days = "none";
                hourAlarm = -1;
                tempMinAlarm = -1;
                setAlarmPanel.setVisible(true);
                checkAlarmPanel.remove(currAlarmLabel);

                currAlarmLabel = new JLabel("<html>Days: " + domain.getDays() + "<br>Time: " + domain.getAlarmHour() + ":" + domain.getAlarmMin() + "<br>Sound Setting:" + domain.getSound()
                        + "<br>Snooze Setting:" + domain.getSnooze() + "</html>");
                currAlarmLabel.setFont(currAlarmLabel.getFont().deriveFont(24.0f));
                checkAlarmPanel.add(currAlarmLabel);
                checkAlarmPanel.setVisible(true);
                hours = "";
                hourTextBox.setText(hours);
                mins = "";
                minTextBox.setText(mins);

                daysPanel.setVisible(false);
                setTimePanel.setVisible(false);

                soundsPanel.setVisible(false);
                snoozePanel.setVisible(false);
                submitPanel.setVisible(false);

            } else if (ae.getSource() == disableButton) {
                entry = enterIDTextBox.getText();
                entry = "";
                enterIDTextBox.setText(entry);

                counter = 0;
                AudioPlayer.player.stop(as);
                AudioPlayer.player.stop(as2);
                alarmDisablePanel.setVisible(false);
                IDEntryPanel.setVisible(true);
                enterIDTextBox.setVisible(true);
                systemTitlePanel.setVisible(true);
                setAlarmPanel.setVisible(false);
                checkAlarmPanel.setVisible(false);
                enterIDTextBox.setText(entry);
                //alarmDisablePanel.setVisible(false);
            } else if (ae.getSource() == noAlarmButton) {
                domain.resetAlarm();
                daysPanel.setVisible(false);
                setTimePanel.setVisible(false);
                soundsPanel.setVisible(false);
                snoozePanel.setVisible(false);
                submitPanel.setVisible(false);
                setAlarmPanel.setVisible(true);
                checkAlarmPanel.setVisible(true);

                //  cancelPanel.setVisible(false);
            } else if (ae.getSource() == okButton) {
                int tempHour = 0;
                int tempMin = 0;

                try {
                    tempHour = Integer.parseInt(hourTextBox.getText());
                    tempMin = Integer.parseInt(minTextBox.getText());
                } catch (NumberFormatException e) {
                } // domain.setAlarmHour(tempHour);
                finally {
                    if (hourTextBox.isVisible() && tempHour <= 24) {
                        domain.setAlarmHour(tempHour);
                        hourTextBox.setVisible(false);
                        enterHourLabel.setVisible(false);
                        enterMin.setVisible(true);
                        minTextBox.setVisible(true);
                    } else if (minTextBox.isVisible() && tempMin <= 59) {
                        domain.setAlarmMin(tempMin);
                        minTextBox.setVisible(false);
                        enterMin.setVisible(false);
                        okButton.setVisible(false);
                        submitButton.setVisible(true);
                        if (tempMin < 10) {
                            tempAlarmLabel = new JLabel("<html> Alarm Time:<br>" + tempHour + ":" + "0" + tempMin);
                        } else {
                            tempAlarmLabel = new JLabel("<html> Alarm Time:<br>" + tempHour + ":" + tempMin);
                        }
                        tempAlarmLabel.setFont(tempAlarmLabel.getFont().deriveFont(32.0f));
                        setTimePanel.add(tempAlarmLabel);
                        tempAlarmLabel.setVisible(true);
                        snoozePanel.setVisible(true);

                    }

                }
            } else if (ae.getSource() == submitButton) {
                submitButton();
            } else if (ae.getSource() == room1Button) {
                domain.setRoom(2);
                domain.readFile(file);
                setCurrentAlarmLabel();
                logoutButton();
                      // currAlarmLabel.setVisible(true);
                // buildPanel();
            } else if (ae.getSource() == room2Button) {
                domain.setRoom(3);
                domain.readFile(file);
                setCurrentAlarmLabel();
                logoutButton();
                // currAlarmLabel.setVisible(true);  // buildPanel();
            } else if (ae.getSource() == liveRoomButton) {
                domain.setRoom(1);
                domain.readFile(file);
                setCurrentAlarmLabel();
                logoutButton();
                // currAlarmLabel.setVisible(true); // buildPanel();
            } else if (ae.getSource() == okChildButton) {
                jFrame4.setVisible(false);
            }

        }
    }
//inner class

    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            String theDay = "";
            int day = now.get(Calendar.DAY_OF_WEEK);
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int min = now.get(Calendar.MINUTE);
            int sec = now.get(Calendar.SECOND);

            switch (day) {
                case 1:
                    theDay = "Sunday";
                    break;
                case 2:
                    theDay = "Monday";
                    break;
                case 3:
                    theDay = "Tuesday";
                    break;
                case 4:
                    theDay = "Weds.";
                    break;
                case 5:
                    theDay = "Thursday";
                    break;
                case 6:
                    theDay = "Friday";
                    break;
                case 7:
                    theDay = "Saturday";
                    break;
            }

            timeTextBox.setText(theDay + " " + hour + ":" + min + ":" + sec);

            // timeTextBox.setBorder(null);
            // int d = Calendar.DAY_OF_WEEK;
            if (domain.getDays().contains("M") && now.get(Calendar.DAY_OF_WEEK) == 2) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();
                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }
            if (domain.getDays().contains("Tue") && now.get(Calendar.DAY_OF_WEEK) == 3) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }
            if (domain.getDays().contains("W") && now.get(Calendar.DAY_OF_WEEK) == 4) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    AudioPlayer.player.start(as);

                }
            }
            if (domain.getDays().contains("Thu") && now.get(Calendar.DAY_OF_WEEK) == 5) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }

            if (domain.getDays().contains("F") && now.get(Calendar.DAY_OF_WEEK) == 6) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }

            if (domain.getDays().contains("Sat") && now.get(Calendar.DAY_OF_WEEK) == 7) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }
            if (domain.getDays().contains("Sun") && now.get(Calendar.DAY_OF_WEEK) == 1) {
                if (domain.getAlarmHour() == now.get(Calendar.HOUR_OF_DAY) && domain.getAlarmMin() == now.get(Calendar.MINUTE) && secAlarm == now.get(Calendar.SECOND)) {
                    alarmGUI();

                    if (domain.getSound().contains("Buzzer")) {
                        AudioPlayer.player.start(as);
                    }
                    if (domain.getSound().contains("Radio")) {
                        AudioPlayer.player.start(as2);
                    }

                }
            }
            if (now.get(Calendar.MINUTE) == tempMinAlarm && secAlarm == now.get(Calendar.SECOND)) {
                if (tempMinAlarm != -1) {
                    alarmGUI();
                }

                if (domain.getSound().contains("Buzzer")) {
                    AudioPlayer.player.start(as);
                }
                if (domain.getSound().contains("Radio")) {
                    AudioPlayer.player.start(as2);
                }

                tempMinAlarm = -1;
                //tempMinAlarm=-100;
            }
        }
    }

    public void submitButton() {
        try {
                     //  hourAlarm = Integer.parseInt(hourTextBox.getText());
            // minAlarm = Integer.parseInt(minTextBox.getText());
            // snoozeAlarm =Integer.parseInt(snoozeLengthTextBox.getText());
        } catch (NumberFormatException e) {
        } finally {
            StringBuilder b = new StringBuilder();
            if (soundGroup.getSelection() != null) {
                domain.setSound(soundGroup.getSelection().getActionCommand().toString());
            }
            if (mondayCButton.isSelected()) {
                b.append("M ");
            }
            if (tuesdayCButton.isSelected()) {
                b.append("Tue ");
            }
            if (wedsCButton.isSelected()) {
                b.append("W ");
            }
            if (thursdayCButton.isSelected()) {
                b.append("Thu ");
            }
            if (fridayCButton.isSelected()) {
                b.append("F ");
            }
            if (satCButton.isSelected()) {
                b.append("Sat ");
            }
            if (sunCButton.isSelected()) {
                b.append("Sun");
            }
            domain.setDays(b.toString());
            if (!snoozeLengthTextBox.getText().equalsIgnoreCase("")) {
                domain.setSnooze(Integer.parseInt(snoozeLengthTextBox.getText()));
            } else {
                domain.setSnooze(0);
            }// days = b.toString();
            alarmLabel.setText("<html>ALARM " + domain.getSound() + "<br>!!!!!!!WAKE UP!!!!!!!<br>.</html>");
            setAlarmPanel.setVisible(true);
            checkAlarmPanel.remove(currAlarmLabel);
            setCurrentAlarmLabel();
            currAlarmLabel.setFont(currAlarmLabel.getFont().deriveFont(24.0f));
            checkAlarmPanel.add(currAlarmLabel);
            domain.writeToLog();
            checkAlarmPanel.setVisible(true);
            hours = "";
            hourTextBox.setText(hours);
            mins = "";
            minTextBox.setText(mins);
            snooze = "";
            snoozeLengthTextBox.setText(snooze);

            tempAlarmLabel.setVisible(false);
            daysPanel.setVisible(false);
            setTimePanel.setVisible(false);

            soundsPanel.setVisible(false);
            snoozePanel.setVisible(false);
            submitPanel.setVisible(false);
            if (!hourTextBox.isVisible()) {
                hours = "";
                hourTextBox.setText(hours);
            } else if (!minTextBox.isVisible()) {
                mins = "";
                minTextBox.setText(mins);
            } else if (!snoozeLengthTextBox.isVisible()) {
                snooze = "";
                snoozeLengthTextBox.setText(snooze);
            } else if (tempAlarmLabel.isVisible()) {
                tempAlarmLabel.setVisible(false);
                okButton.setVisible(true);
            } else if (snoozeLengthTextBox.isVisible()) {
                snooze = "";
                snoozeLengthTextBox.setText(snooze);
            }
        }
    }

    public void alarmGUI() {
        alarmDisablePanel.setVisible(true);
        IDEntryPanel.setVisible(false);
        systemTitlePanel.setVisible(false);
        IDEntryPanel.add(enterIDTextBox);

        setAlarmPanel.setVisible(false);
        checkAlarmPanel.setVisible(false);
        systemTitlePanel.setVisible(false);
        daysPanel.setVisible(false);
        setTimePanel.setVisible(false);
        soundsPanel.setVisible(false);
        snoozePanel.setVisible(false);
        submitPanel.setVisible(false);
    }

    public void addToMainPanel(JPanel p) {
        mainPanel.add(p);
    }

    public void cancelButton() {
        setAlarmPanel.setVisible(true);
        checkAlarmPanel.setVisible(true);

        daysPanel.setVisible(false);
        setTimePanel.setVisible(false);

        soundsPanel.setVisible(false);
        snoozePanel.setVisible(false);
        submitPanel.setVisible(false);
        if (hourTextBox.isVisible()) {
            hours = "";
            hourTextBox.setText(hours);
        } else if (minTextBox.isVisible()) {
            mins = "";
            minTextBox.setText(mins);
            minTextBox.setVisible(false);
            enterMin.setVisible(false);
        } else if (tempAlarmLabel.isVisible()) {
            tempAlarmLabel.setVisible(false);
            okButton.setVisible(true);
        } else if (snoozeLengthTextBox.isVisible()) {
            snooze = "";
            snoozeLengthTextBox.setText(snooze);
        }
    }

    public void logoutButton() {
        IDEntryPanel.add(enterIDTextBox);
        IDEntryPanel.setVisible(true);
        enterIDTextBox.setVisible(true);
        systemTitlePanel.setVisible(true);
        setAlarmPanel.setVisible(false);
        checkAlarmPanel.setVisible(false);
    }

    public void setCurrentAlarmLabel() {
        if (domain.getAlarmMin() < 10) {
            currAlarmLabel.setText("<html>Days: " + domain.getDays() + "<br>Time: " + domain.getAlarmHour() + ":0" + domain.getAlarmMin() + "<br>Sound Setting:" + domain.getSound()
                    + "<br>Snooze Setting:" + domain.getSnooze() + " Minutes </html>");
        } else {
            currAlarmLabel.setText("<html>Days: " + domain.getDays() + "<br>Time: " + domain.getAlarmHour() + ":" + domain.getAlarmMin() + "<br>Sound Setting:" + domain.getSound()
                    + "<br>Snooze Setting:" + domain.getSnooze() + " Minutes </html>");
        }

    }

}
//\\
