package smarthouse;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A component that lets the user enter a number, using a button pad labeled
 * with digits
 */
public class AdminKeypad extends JPanel {

    /**
     * Constructs the keypad panel.
     */
    public AdminKeypad() {
        setLayout(new BorderLayout());

        // add display field
        display = new JTextField();
        display.setFont(new Font("Dialog", Font.BOLD, 28));
        add(display, "North");

        // make button panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new GridLayout(4, 3));

        // add digit buttons
        addButton("7");
        addButton("8");
        addButton("9");
        addButton("4");
        addButton("5");
        addButton("6");
        addButton("1");
        addButton("2");
        addButton("3");
        addButton("0");
        addButton(".");

        // add clear entry button
        clearButton = new JButton("CE");
        clearButton.setFont(new Font("Dialog", Font.BOLD, 28));
        buttonPanel.add(clearButton);

        class ClearButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                display.setText("");
            }
        }
        ActionListener listener = new ClearButtonListener();

        clearButton.addActionListener(new ClearButtonListener());

        add(buttonPanel, "Center");
    }

    /**
     * Adds a button to the button panel
     *
     * @param label the button label
     */
    private void addButton(final String label) {
        class DigitButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {

                // don't add two decimal points
                if (label.equals(".")
                        && display.getText().indexOf(".") != -1) {
                    return;
                }

                // append label text to button
                display.setText(display.getText() + label);
            }
        }

        JButton button = new JButton(label);
        if (!label.equals("ce")) {
            button.setFont(new Font("Dialog", Font.BOLD, 28));
        }
        buttonPanel.add(button);
        ActionListener listener = new DigitButtonListener();
        button.addActionListener(listener);
    }

    /**
     * Gets the value that the user entered.
     *
     * @return the value in the text field of the keypad
     */
    public String getValue() {
        return display.getText();
    }

    public void setDisplayValueNull() {
        display.setText("");
    }

    /**
     * Clears the dislay.
     */
    public void clear() {
        display.setText("");
    }

    private JPanel buttonPanel;
    private JButton clearButton;
    private JTextField display;
}
