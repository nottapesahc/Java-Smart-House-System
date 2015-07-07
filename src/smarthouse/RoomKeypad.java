package smarthouse;

import smarthouse.RoomGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RoomKeypad extends RoomGUI {

    private JFrame keyPad;

    public static String entry = "";
    public static JTextField enterID;
    public static JLabel message;
    public static JPanel keypadPanel, IDpanel, panel123, panel456, panel789,
            panelC0E;
    public static JButton zero, one, two, three, four, five, six, seven, eight,
            nine, enter, clear;
    private static Admin admin;
    //RoomGUI room = new RoomGUI();

    public RoomKeypad() {
        final int WIDTH = 1000;
        final int HEIGHT = 625;
        keyPad = new JFrame("Room Controls");
        Dimension d = new Dimension(WIDTH, HEIGHT);
        keyPad.setPreferredSize(d);

        keyPad.setLayout(new BorderLayout());
        keyPad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildKeypad();
        keyPad.add(options, BorderLayout.CENTER);
        keyPad.add(functions, BorderLayout.EAST);
        keyPad.add(keypadPanel, BorderLayout.WEST);
        keyPad.add(notificationBar, BorderLayout.SOUTH);
        keyPad.pack();
        keyPad.setVisible(true);
    }

    public void buildKeypad() {
        admin = new Admin();
        message = new JLabel("Enter User ID: ");

        // message.setFont(message.getFont().deriveFont(32.0f));
        message.setFont(new Font("Dialog", Font.BOLD, 30));

        enterID = new JTextField(7);
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");
        enter = new JButton("Enter");
        clear = new JButton("Clear");

        one.setFont(new Font("Dialog", Font.BOLD, 30));
        two.setFont(new Font("Dialog", Font.BOLD, 30));
        three.setFont(new Font("Dialog", Font.BOLD, 30));
        four.setFont(new Font("Dialog", Font.BOLD, 30));
        five.setFont(new Font("Dialog", Font.BOLD, 30));
        six.setFont(new Font("Dialog", Font.BOLD, 30));
        seven.setFont(new Font("Dialog", Font.BOLD, 30));
        eight.setFont(new Font("Dialog", Font.BOLD, 30));
        nine.setFont(new Font("Dialog", Font.BOLD, 30));
        zero.setFont(new Font("Dialog", Font.BOLD, 30));
        enter.setFont(new Font("Dialog", Font.BOLD, 20));
        clear.setFont(new Font("Dialog", Font.BOLD, 20));

        one.setPreferredSize(new Dimension(100, 78));
        two.setPreferredSize(new Dimension(100, 78));
        three.setPreferredSize(new Dimension(100, 78));
        four.setPreferredSize(new Dimension(100, 78));
        five.setPreferredSize(new Dimension(100, 78));
        six.setPreferredSize(new Dimension(100, 78));
        seven.setPreferredSize(new Dimension(100, 78));
        eight.setPreferredSize(new Dimension(100, 78));
        nine.setPreferredSize(new Dimension(100, 78));
        zero.setPreferredSize(new Dimension(100, 78));
        enter.setPreferredSize(new Dimension(100, 78));
        clear.setPreferredSize(new Dimension(100, 78));
        enterID.setPreferredSize(new Dimension(30, 30));

        keypadPanel = new JPanel();
        IDpanel = new JPanel();
        panel123 = new JPanel();
        panel456 = new JPanel();
        panel789 = new JPanel();
        panelC0E = new JPanel();

        keypadPanel.setLayout(new GridLayout(5, 1));
        keypadPanel.setBackground(Color.GREEN);

        IDpanel.setBackground(Color.GREEN);
        panel123.setBackground(Color.GREEN);
        panel456.setBackground(Color.GREEN);
        panel789.setBackground(Color.GREEN);
        panelC0E.setBackground(Color.GREEN);

        IDpanel.add(message);
        IDpanel.add(enterID);

        panel123.add(one);
        panel123.add(two);
        panel123.add(three);

        panel456.add(four);
        panel456.add(five);
        panel456.add(six);

        panel789.add(seven);
        panel789.add(eight);
        panel789.add(nine);

        panelC0E.add(clear);
        panelC0E.add(zero);
        panelC0E.add(enter);

        keypadPanel.add(IDpanel);
        keypadPanel.add(panel123);
        keypadPanel.add(panel456);
        keypadPanel.add(panel789);
        keypadPanel.add(panelC0E);
        keypadPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
                if (admin.validateID(entry)) {
                    ID = entry;
                    entry = "";
                    roomModel.setCurrentUser(ID);
                    enterID.setText("");
                    update();
                    functions.setVisible(true);
                    options.setVisible(true);
                    notificationBar.setVisible(true);
                    roomLabelPanel.setVisible(true);

                } else {
                    entry = "";
                    enterID.setText("");
                }
                enterID.setText("");
            }

        }

    }
}
