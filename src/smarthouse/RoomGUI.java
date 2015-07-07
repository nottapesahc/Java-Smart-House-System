package smarthouse;

import smarthouse.Room;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RoomGUI {

    public static int currentLights, currentTemp;
    public static JSpinner spinner;
    public static JSlider slider;
    public File theFile;
    public static String ID = "4444";

    // Select room options GUI
    public JFrame GUI;
    public JPanel roomOptionPanel;
    public JButton room1;
    public JButton room2;
    public JButton livingRoom;
    public JLabel roomLabel;
    public JPanel roomLabelPanel;
    public String labelChanger;

    // OPTIONS Panel
    private JButton tempButton, lightButton;
    private JLabel fanLabel;

    // Notification Panel
    public static JPanel notificationBar;
    public static JPanel currentNotification, minNotification, maxNotification,
            logoutPanel;
    public static JLabel currentTempLabel, maxTempLabel, minTempLabel,
            currentTempNotification, minTempNotification, maxTempNotification;
    public static JButton logoutButton;

    // FUNCTIONS Panel
    public static JPanel options, functions, fanButtons, lightFunctions,
            fanFunctions, tempFunctions;
    public static JButton off, low, high;
    public static JLabel fanButtonLabel, tempLabel, lightLabel, emptyLabel1,
            emptyLabel2, emptyLabel3, emptyLabel4, emptyLabel5;

    Room roomModel = new Room();

    public RoomGUI() {
        this.theFile = new File("RoomLog.txt");
        roomModel.readFile(theFile);
        final int WIDTH = 350;
        final int HEIGHT = 300;
        GUI = new JFrame("Select Room");
        Dimension d = new Dimension(WIDTH, HEIGHT);
        GUI.setPreferredSize(d);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        roomOptionPanel.setLayout(new GridLayout(3, 1));
        GUI.add(roomOptionPanel);

        GUI.pack();
        GUI.setVisible(true);
    }

    public void buildPanel() {

        /*
         * Slider for the Light Control
         */
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, roomModel.getLights());

        /*
         * Spinner for temperature control
         */
        SpinnerNumberModel snm = new SpinnerNumberModel(roomModel.getTemp(),
                roomModel.getMinTemp(), roomModel.getMaxTemp(), 1);
        spinner = new JSpinner(snm);

        slider.setLabelTable(slider.createStandardLabels(25));
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        spinner.setFont(spinner.getFont().deriveFont(64.0f));
        spinner.setBackground(Color.GREEN);

        roomLabel = new JLabel(" " + roomModel.getRoom() + " ");
        room1 = new JButton("BedRoom One");
        room2 = new JButton("BedRoom Two");
        livingRoom = new JButton("LivingRoom");
        room1.setFont(new Font("Dialog", Font.BOLD, 30));
        room2.setFont(new Font("Dialog", Font.BOLD, 30));
        livingRoom.setFont(new Font("Dialog", Font.BOLD, 30));
        roomOptionPanel = new JPanel();
        roomLabelPanel = new JPanel();
        roomLabelPanel.add(roomLabel);
        roomLabelPanel.setVisible(false);
        roomOptionPanel.add(room1);
        roomOptionPanel.add(room2);
        roomOptionPanel.add(livingRoom);

        /*
         * Everything for the WEST Border
         */
        tempLabel = new JLabel("Temperature Control");
        fanButtonLabel = new JLabel("Set Fan Options");
        lightLabel = new JLabel("Lights Control");
        tempLabel.setHorizontalAlignment(JLabel.CENTER);
        fanButtonLabel.setHorizontalAlignment(JLabel.CENTER);
        lightLabel.setHorizontalAlignment(JLabel.CENTER);
        tempLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        fanButtonLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        lightLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        off = new JButton("Off");
        low = new JButton("Low");
        high = new JButton("High");
        off.setFont(new Font("Dialog", Font.BOLD, 20));
        low.setFont(new Font("Dialog", Font.BOLD, 20));
        high.setFont(new Font("Dialog", Font.BOLD, 20));

        options = new JPanel();
        functions = new JPanel();
        fanButtons = new JPanel();
        lightFunctions = new JPanel();
        fanFunctions = new JPanel();
        tempFunctions = new JPanel();

        fanButtons.setLayout(new FlowLayout());
        fanFunctions.setLayout(new GridLayout(2, 1));
        tempFunctions.setLayout(new GridLayout(2, 1));
        lightFunctions.setLayout(new GridLayout(2, 1));
        functions.setLayout(new GridLayout(3, 1));
        options.setLayout(new GridLayout(3, 1));

        fanButtons.add(off);
        fanButtons.add(high);
        fanButtons.add(low);
        fanButtons.setBackground(Color.GREEN);

        tempFunctions.add(tempLabel);
        tempFunctions.add(spinner);
        tempFunctions.setBackground(Color.GREEN);
        tempFunctions.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        fanFunctions.add(fanButtonLabel);
        fanFunctions.add(fanButtons);
        fanFunctions.setBackground(Color.GREEN);
        fanFunctions.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        lightFunctions.add(lightLabel);
        lightFunctions.add(slider);
        lightFunctions.setBackground(Color.GREEN);
        lightFunctions.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        functions.add(tempFunctions);
        functions.add(fanFunctions);
        functions.add(lightFunctions);
        functions.setBackground(Color.GREEN);

        /*
         * Everything for the Center
         */
        tempButton = new JButton("Set Temperature Control");
        fanLabel = new JLabel("Fan Control");
        lightButton = new JButton("Set Light Control");
        fanLabel.setHorizontalAlignment(JLabel.CENTER);

        tempButton.setFont(new Font("Dialog", Font.BOLD, 30));
        fanLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        lightButton.setFont(new Font("Dialog", Font.BOLD, 30));

        options.add(tempButton);
        options.add(fanLabel);
        options.add(lightButton);
        options.setBackground(Color.WHITE);

        functions.setVisible(false);
        options.setVisible(false);

        /*
         * Everything for the NOTIFICATIONS bar
         */
        currentTempLabel = new JLabel("Current Temperature ");
        maxTempLabel = new JLabel("Max. Temperature");
        minTempLabel = new JLabel("Min. Temperature");
        emptyLabel1 = new JLabel("       ");
        emptyLabel2 = new JLabel("       ");
        emptyLabel3 = new JLabel("       ");
        emptyLabel4 = new JLabel("       ");
        emptyLabel5 = new JLabel("       ");
        currentTempLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        maxTempLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        minTempLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        currentTempNotification = new JLabel(" " + roomModel.getTemp() + " ");
        minTempNotification = new JLabel(" " + roomModel.getMinTemp() + " ");
        maxTempNotification = new JLabel(" " + roomModel.getMaxTemp() + " ");
        currentTempNotification.setHorizontalAlignment(JLabel.CENTER);
        minTempNotification.setHorizontalAlignment(JLabel.CENTER);
        maxTempNotification.setHorizontalAlignment(JLabel.CENTER);
        currentTempNotification.setFont(new Font("Dialog", Font.BOLD, 20));
        minTempNotification.setFont(new Font("Dialog", Font.BOLD, 20));
        maxTempNotification.setFont(new Font("Dialog", Font.BOLD, 20));

        // Logout button positioned in the notification bar
        notificationBar = new JPanel();
        currentNotification = new JPanel();
        minNotification = new JPanel();
        maxNotification = new JPanel();
        logoutPanel = new JPanel();
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(200, 40));

        // Notification Bar Layouts
        notificationBar.setLayout(new FlowLayout());
        currentNotification.setLayout(new GridLayout(2, 1));
        minNotification.setLayout(new GridLayout(2, 1));
        maxNotification.setLayout(new GridLayout(2, 1));

        currentNotification.add(currentTempLabel);
        currentNotification.add(currentTempNotification);
        currentNotification.setBorder(BorderFactory
                .createLineBorder(Color.BLACK));

        minNotification.add(minTempLabel);
        minNotification.add(minTempNotification);
        minNotification.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        maxNotification.add(maxTempLabel);
        maxNotification.add(maxTempNotification);
        maxNotification.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        logoutPanel.add(logoutButton);
        logoutPanel.setBackground(Color.GREEN);

        notificationBar.add(currentNotification);
        notificationBar.add(emptyLabel1);
        notificationBar.add(minNotification);
        notificationBar.add(emptyLabel2);
        notificationBar.add(maxNotification);
        notificationBar.add(emptyLabel3);
        notificationBar.add(emptyLabel4);
        notificationBar.add(emptyLabel5);
        notificationBar.add(logoutPanel);
        notificationBar.setBackground(Color.GREEN);

        notificationBar.setVisible(false);

        tempButton.addActionListener(new FunctionListener());
        off.addActionListener(new FunctionListener());
        low.addActionListener(new FunctionListener());
        high.addActionListener(new FunctionListener());
        lightButton.addActionListener(new FunctionListener());
        room1.addActionListener(new FunctionListener());
        room2.addActionListener(new FunctionListener());
        livingRoom.addActionListener(new FunctionListener());
        logoutButton.addActionListener(new FunctionListener());

    }

    public void update() {
        spinner.setValue(roomModel.getTemp());
        currentTempNotification.setText(" " + roomModel.getTemp() + " ");
        maxTempNotification.setText(" " + roomModel.getMaxTemp() + " ");
        minTempNotification.setText(" " + roomModel.getMinTemp() + " ");
        slider.setValue(roomModel.getLights());
        if (roomModel.getFan().equals("low")) {
            roomModel.setFan("low");
            low.setBackground(Color.YELLOW);
            low.setContentAreaFilled(false);
            low.setOpaque(true);
            off.setBackground(new JButton().getBackground());
            off.setContentAreaFilled(true);
            off.setOpaque(false);
            high.setBackground(new JButton().getBackground());
            high.setContentAreaFilled(true);
            high.setOpaque(false);
        } else if (roomModel.getFan().equals("off")) {
            roomModel.setFan("off");
            off.setBackground(Color.YELLOW);
            off.setContentAreaFilled(false);
            off.setOpaque(true);
            low.setBackground(new JButton().getBackground());
            low.setContentAreaFilled(true);
            low.setOpaque(false);
            high.setBackground(new JButton().getBackground());
            high.setContentAreaFilled(true);
            high.setOpaque(false);
        } else if(roomModel.getFan().equals("high")){
            roomModel.setFan("high");
            high.setBackground(Color.YELLOW);
            high.setContentAreaFilled(false);
            high.setOpaque(true);
            low.setBackground(new JButton().getBackground());
            low.setContentAreaFilled(true);
            low.setOpaque(false);
            off.setBackground(new JButton().getBackground());
            off.setContentAreaFilled(true);
            off.setOpaque(false);
        }
    }

    public class FunctionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == tempButton) {
                currentTemp = Integer.parseInt(spinner.getValue().toString());
                roomModel.setTemp(currentTemp);
                currentTempNotification
                        .setText(" " + roomModel.getTemp() + " ");
                
            } else if (ae.getSource() == off) {
                roomModel.setFan("off");
                
                off.setBackground(Color.YELLOW);
                off.setContentAreaFilled(false);
                off.setOpaque(true);
                low.setBackground(new JButton().getBackground());
                low.setContentAreaFilled(true);
                low.setOpaque(false);
                high.setBackground(new JButton().getBackground());
                high.setContentAreaFilled(true);
                high.setOpaque(false);
            } else if (ae.getSource() == low) {
                roomModel.setFan("low");
                low.setBackground(Color.YELLOW);
                low.setContentAreaFilled(false);
                low.setOpaque(true);
                off.setBackground(new JButton().getBackground());
                off.setContentAreaFilled(true);
                off.setOpaque(false);
                high.setBackground(new JButton().getBackground());
                high.setContentAreaFilled(true);
                high.setOpaque(false);
            } else if ((ae.getSource() == high)) {
                roomModel.setFan("high");
                high.setBackground(Color.YELLOW);
                high.setContentAreaFilled(false);
                high.setOpaque(true);
                low.setBackground(new JButton().getBackground());
                low.setContentAreaFilled(true);
                low.setOpaque(false);
                off.setBackground(new JButton().getBackground());
                off.setContentAreaFilled(true);
                off.setOpaque(false);
            } else if (ae.getSource() == lightButton) {
                currentLights = slider.getValue();
                roomModel.setLights(currentLights);
            } else if (ae.getSource() == room1) {
                notificationBar.setVisible(false);
                functions.setVisible(false);
                options.setVisible(false);
				//roomLabelPanel.setVisible(false);
                //roomLabel.setVisible(false);
                roomModel.setRoom("BedRoom1");
                roomModel.readFile(theFile);
                roomLabel.setText(roomModel.getRoom());
                update();

            } else if (ae.getSource() == room2) {
                notificationBar.setVisible(false);
                functions.setVisible(false);
                options.setVisible(false);
				//roomLabelPanel.setVisible(false);
                //roomLabel.setVisible(false);
                roomModel.setRoom("BedRoom2");
                roomModel.readFile(theFile);
                update();
            } else if (ae.getSource() == livingRoom) {
                notificationBar.setVisible(false);
                functions.setVisible(false);
                options.setVisible(false);
				//roomLabelPanel.setVisible(false);
                //roomLabel.setVisible(false);
                roomModel.setRoom("Living Room");
                roomModel.readFile(theFile);
                update();
            } else if (ae.getSource() == logoutButton) {
                notificationBar.setVisible(false);
                functions.setVisible(false);
                options.setVisible(false);
				//roomLabelPanel.setVisible(false);
                //roomLabel.setVisible(false);
//				System.out.println(roomModel.getLights());
//				System.out.println(roomModel.getFan());
                roomModel.writeToLog();
            }
        }

    }
}
