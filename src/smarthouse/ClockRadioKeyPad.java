package smarthouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class ClockRadioKeyPad extends ClockRadioGUI {

    public JLabel enterIDLabel;
    private JLabel notValid;
    private JFrame jFrame3;
    //  public JTextField enterIDTextBox;
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
    private JButton clear, tryAgain;
    private JPanel Again;
    //Admin ADomain = new Admin();

    public ClockRadioKeyPad() {

        enterIDLabel = new JLabel("Enter User ID: ");
        notValid = new JLabel("ID Not Valid");
        jFrame3 = new JFrame("Access Denied");
        final int WIDTH3 = 300;
        final int HEIGHT3 = 150;
        Dimension d3 = new Dimension(WIDTH3, HEIGHT3);
        jFrame3.setPreferredSize(d3);
        jFrame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        tryAgain = new JButton("Try Again");
        enterIDLabel.setFont(enterIDLabel.getFont().deriveFont(24.0f));
        notValid.setFont(notValid.getFont().deriveFont(24.0f));

        enterIDTextBox = new JTextField(8);
        enterIDTextBox.setFont(enterIDTextBox.getFont().deriveFont(16.0f));

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
        enter.setPreferredSize(new Dimension(100, 100));
        clear.setPreferredSize(new Dimension(100, 100));
        enterIDTextBox.setPreferredSize(new Dimension(200, 50));
        tryAgain.setPreferredSize(new Dimension(100, 50));

        one.setFont(one.getFont().deriveFont(32.0f));
        two.setFont(two.getFont().deriveFont(32.0f));
        three.setFont(three.getFont().deriveFont(32.0f));
        four.setFont(four.getFont().deriveFont(32.0f));
        five.setFont(five.getFont().deriveFont(32.0f));
        six.setFont(six.getFont().deriveFont(32.0f));
        seven.setFont(seven.getFont().deriveFont(32.0f));
        eight.setFont(eight.getFont().deriveFont(32.0f));
        nine.setFont(nine.getFont().deriveFont(32.0f));
        zero.setFont(zero.getFont().deriveFont(32.0f));
        tryAgain.setFont(tryAgain.getFont().deriveFont(10.0f));

        IDEntryPanel = new JPanel();
        IDEntryPanel.setLocation(10, 5);
        IDEntryPanel.setSize(350, 60);
        IDEntryPanel.add(enterIDLabel);
        IDEntryPanel.add(enterIDTextBox);
        // enterIDLabel.setVisible(true);
        //enterIDTextBox.setVisible(true);
        IDEntryPanel.setBackground(Color.GRAY);
        //IDEntryPanel.setVisible(true);

        key123Panel = new JPanel();
        key123Panel.setBackground(Color.GRAY);
        key123Panel.setLocation(10, 60);
        key123Panel.setSize(350, 110);
        key123Panel.add(one);
        key123Panel.add(two);
        key123Panel.add(three);
        // one.setVisible(true);
        //two.setVisible(true);
        //three.setVisible(true);

        key456Panel = new JPanel();
        key456Panel.setBackground(Color.GRAY);
        key456Panel.setLocation(10, 165);
        key456Panel.setSize(350, 110);
        key456Panel.add(four);
        key456Panel.add(five);
        key456Panel.add(six);
        // four.setVisible(true);
        //five.setVisible(true);
        //six.setVisible(true);

        key789Panel = new JPanel();
        key789Panel.setBackground(Color.GRAY);
        key789Panel.setLocation(10, 270);
        key789Panel.setSize(350, 110);
        key789Panel.add(seven);
        key789Panel.add(eight);
        key789Panel.add(nine);
        // seven.setVisible(true);
        //eight.setVisible(true);
        //nine.setVisible(true);

        keyC0SPanel = new JPanel();
        keyC0SPanel.setBackground(Color.GRAY);
        keyC0SPanel.setLocation(10, 375);
        keyC0SPanel.setSize(350, 110);
        keyC0SPanel.add(clear);
        keyC0SPanel.add(zero);
        keyC0SPanel.add(enter);
        // clear.setVisible(true);
        //zero.setVisible(true);
        //enter.setVisible(true);

        addToMainPanel(IDEntryPanel);
        addToMainPanel(key123Panel);
        addToMainPanel(key456Panel);
        addToMainPanel(key789Panel);
        addToMainPanel(keyC0SPanel);

        Again = new JPanel();
        // switchPanel.setLayout(null);
        Again.add(notValid);
        Again.add(tryAgain);

        jFrame3.add(Again);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame3.setLocation(dim.width / 2 - jFrame3.getSize().width / 2, dim.height / 2 - jFrame3.getSize().height / 2);
        jFrame3.pack();
        jFrame3.setVisible(false);

        /*
         IDEntryPanel.setVisible(true);
         key123Panel.setVisible(true);
         key456Panel.setVisible(true);
         key789Panel.setVisible(true);
         keyC0SPanel.setVisible(true);

         */
        one.addActionListener(new keyListener());
        two.addActionListener(new keyListener());
        three.addActionListener(new keyListener());
        four.addActionListener(new keyListener());
        five.addActionListener(new keyListener());
        six.addActionListener(new keyListener());
        seven.addActionListener(new keyListener());
        eight.addActionListener(new keyListener());
        nine.addActionListener(new keyListener());
        zero.addActionListener(new keyListener());
        clear.addActionListener(new keyListener());
        enter.addActionListener(new keyListener());
        tryAgain.addActionListener(new keyListener());

        makeVisible();
        //jFrame.update(jFrame.getGraphics());

    }

    public final void makeVisible() {
        enterIDLabel.setVisible(true);
        enterIDTextBox.setVisible(true);
        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        four.setVisible(true);
        five.setVisible(true);
        six.setVisible(true);
        seven.setVisible(true);
        eight.setVisible(true);
        nine.setVisible(true);
        clear.setVisible(true);
        zero.setVisible(true);
        enter.setVisible(true);

        IDEntryPanel.setVisible(true);
        key123Panel.setVisible(true);
        key456Panel.setVisible(true);
        key789Panel.setVisible(true);
        keyC0SPanel.setVisible(true);

    }

    // @Override

    private class keyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == one) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "1";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "1";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "1";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "1";
                    snoozeLengthTextBox.setText(snooze);
                }

            } else if (ae.getSource() == two) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "2";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "2";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "2";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "2";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == three) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "3";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "3";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "3";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "3";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == four) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "4";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "4";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "4";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "4";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == five) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "5";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "5";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "5";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "5";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == six) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "6";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "6";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "6";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "6";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == seven) {
                entry = entry + "7";
                if (IDEntryPanel.isVisible()) {
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "7";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "7";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "7";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == eight) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "8";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "8";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "8";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "8";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == nine) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "9";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "9";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "9";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "9";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == zero) {
                if (IDEntryPanel.isVisible()) {
                    entry = entry + "0";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = hours + "0";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = mins + "0";
                    minTextBox.setText(mins);

                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = snooze + "0";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == clear) {
                if (IDEntryPanel.isVisible()) {
                    entry = "";
                    enterIDTextBox.setText(entry);
                } else if (hourTextBox.isVisible()) {
                    hours = "";
                    hourTextBox.setText(hours);
                } else if (minTextBox.isVisible()) {
                    mins = "";
                    minTextBox.setText(mins);
                } else if (snoozeLengthTextBox.isVisible()) {
                    snooze = "";
                    snoozeLengthTextBox.setText(snooze);
                }
            } else if (ae.getSource() == enter) {
                if (IDEntryPanel.isVisible()) {

                    boolean a = Admin.validateID(entry);
                    if (Admin.validateID(entry) == true) {
                        domain.readFile(file);
                        setCurrentAlarmLabel();
                        ID = entry;
                        entry = enterIDTextBox.getText();
                        entry = "";
                        enterIDTextBox.setText(entry);

                        IDEntryPanel.setVisible(false);
                        systemTitlePanel.setVisible(false);
                        setAlarmPanel.setVisible(true);
                        checkAlarmPanel.setVisible(true);
                        enterIDTextBox.setVisible(false);
                        IDEntryPanel.remove(enterIDTextBox);
                            // setAlarm.addActionListener(new keypadListener());
                        // alarmDisablePanel.setVisible(true);
                    } else {
                        jFrame3.setVisible(true);
                    }
                }
            } else if (ae.getSource() == tryAgain) {
                entry = "";
                enterIDTextBox.setText(entry);
                jFrame3.setVisible(false);
            }

        }
    }
}
