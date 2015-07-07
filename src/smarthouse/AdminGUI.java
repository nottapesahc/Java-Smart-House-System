package smarthouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class AdminGUI extends JFrame {

    private JPanel mainPanel;

    //start fields for fail panel.
    private JPanel failPanel;
    private static int loginAttempt = 0;
    private JLabel failPanelLabel3;
    //end fields for fail panel.

    private AdminKeypad keypad;
    private JButton validateButton;

    //start fields for admin main menu
    private boolean loggedIn = false;
    private JPanel adminMainMenuPanel;
    private JButton manageUserCodesButton, ViewLogsButton;
    private JButton remoteOverrideButton, managePrivilegesButton;
    private JButton logoutButton;
    //end fields for admin main menu

    //start fields for the main logs panel
    private JPanel logsMainPanel;
    private ButtonGroup buttonGroup1;
    private JRadioButton radioButton1, radioButton2, radioButton3;
    private JButton logsMainSubmitButton, logsMainCancelButton;
    //end fields for the main logs panel

    //start fields for the view logs panel
    private JPanel viewLogsPanel;
    private JButton cancelViewLogsButton;
    JLabel logNameLabel;
    JTextArea logTextArea;
    //end fields for the view logs panel

    //start fields for manage user codes panel
    private JPanel userCodesMainPanel;
    private JButton userCodesButton1, removeUsersButton, userCodesButton3;
    private JButton viewUsersButton, returnFromUserCodesButton;
    //end fields for manage user codes panel

    //start fields for add user panel
    private JPanel addUserPanel;
    private JTextField addUserTextField, addUserTextField2;
    private JButton addUserCancelButton, addUserSubmitButton;
    private ButtonGroup addUserButtonGroup;
    private JRadioButton addUserRadioButton1, addUserRadioButton2;
    private JRadioButton addUserRadioButton3;
    //end fields for add user panel

    //start fields for remove user panel
    private JPanel removeUserPanel;
    private JComboBox removeUserComboBox;
    private JButton removeUserCancelButton, removeUserSubmitButton;
    //end fields for remove user panel

    //start fields for update user panel
    private JPanel updateUserPanel;
    private JComboBox updateUserComboBox;
    private JButton updateUserCancelButton, updateUserSubmitButton;
    private JTextField updateUserTextField, updateUserTextField2;
    //end fields for update user panel

    //start fields for view users panel
    private JPanel viewUsersPanel;
    private JComboBox viewUsersComboBox;
    private JButton viewUsersCancelButton;
    private JTextArea display;
    //end fields for view user panel

    //start fields for Override Main Panel
    private JPanel overrideMainPanel;
    private JButton overrideClockButton, overrideDoorsButton;
    private JButton overrideRoomButton, overrideCancelButton;
    //end fields for Override Main Panel

    //start fields for Override Clock Panel
    private JPanel overrideClockPanel;
    private JButton overrideClockCancelButton, overrideClockSubmitButton;
    private JTextField overrideHoursTextField, overrideMinutesTextField;
    private JTextField overrideSnoozeTextField;
    private JCheckBox clockCheckM, clockCheckT, clockCheckW, clockCheckR;
    private JCheckBox clockCheckF, clockCheckS, clockCheckSu;
    private ButtonGroup clockButtonGroup;
    private JRadioButton clockRadioButton1, clockRadioButton2;
    private ButtonGroup clockButtonGroup2;
    private JRadioButton clockRadioButton3, clockRadioButton4, clockRadioButton5;
    //end fields for Override Clock Panel

    //start fields for Override Door Panel
    private JPanel overrideDoorPanel;
    private JButton overrideDoorCancelButton, overrideDoorSubmitButton;
    private ButtonGroup doorButtonGroup, doorButtonGroup2;
    private JRadioButton doorRadioButton1, doorRadioButton2, doorRadioButton3;
    private JRadioButton doorRadioButton4, doorRadioButton5;
    //end fields for Override Door Panel

    //start fields for Override Room Panel
    private JPanel overrideRoomPanel;
    private JButton overrideRoomCancelButton, overrideRoomSubmitButton;
    private JTextField overrideRoomTempTextFieldLow, overrideRoomTempTextFieldHigh;
    private JButton fanButtonOff, fanButtonHigh, fanButtonLow;
    private ButtonGroup roomButtonGroup;
    private JRadioButton roomRadioButton1, roomRadioButton2, roomRadioButton3;
    private JSlider roomSlider;
    private JSpinner roomSpinner;

    //end fields for Override Room Panel
    //start fields for Manage Privileges Panel
    private JPanel managePrivilegesPanel;
    private JComboBox managePrivilegesComboBox;
    private ButtonGroup managePrivilegesButtonGroup;
    private JRadioButton managePrivilegesRadioButton1, managePrivilegesRadioButton2;
    private JRadioButton managePrivilegesRadioButton3;
    private JButton managePrivilegesCancelButton, managePrivilegesSubmitButton;

    private static Admin admin;
    private ClockRadio clockRadio;
    private Room room;
    private String currentUser;
    private doorModel door;

    //end fields for Manage Privileges Panel
    public AdminGUI() {
        final int WIDTH = 1000;
        final int HEIGHT = 525;

        setTitle("Administrator Toolset");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        admin = new Admin();
        clockRadio = new ClockRadio();
        room = new Room();

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GREEN);

        keypad = new AdminKeypad();
        keypad.setLocation(10, 10);
        keypad.setSize(350, 480);
        keypad.setBackground(Color.LIGHT_GRAY);

        buildFailPanel();
        buildAdminMainPanel();
        buildLogsMainPanel();
        buildViewLogsPanel();
        buildUserCodesMainPanel();
        buildAddUserPanel();
        buildRemoveUserPanel();
        buildUpdateUserPanel();
        buildViewUsersPanel();
        buildOverrideMainPanel();
        buildOverrideClockPanel();
        buildOverrideDoorPanel();
        buildOverrideRoomPanel();
        buildManagePrivilegesPanel();

        mainPanel.add(keypad);
        mainPanel.add(adminMainMenuPanel);
        mainPanel.add(logsMainPanel);
        mainPanel.add(viewLogsPanel);
        mainPanel.add(failPanel);
        mainPanel.add(userCodesMainPanel);
        mainPanel.add(addUserPanel);
        mainPanel.add(removeUserPanel);
        mainPanel.add(updateUserPanel);
        mainPanel.add(viewUsersPanel);
        mainPanel.add(overrideMainPanel);
        mainPanel.add(overrideClockPanel);
        mainPanel.add(overrideDoorPanel);
        mainPanel.add(overrideRoomPanel);
        mainPanel.add(managePrivilegesPanel);
        add(mainPanel);
        failPanel.setVisible(true);
        setResizable(false);
        setVisible(true);
        updateComboBoxes(admin.getUserList());
    }

    private void buildAdminMainPanel() {
        adminMainMenuPanel = new JPanel();

        JLabel label = new JLabel("Administrator Toolset");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 50));

        ViewLogsButton = new JButton("View Logs");
        manageUserCodesButton = new JButton("Manage User IDs");
        managePrivilegesButton = new JButton("Manage Privileges");
        remoteOverrideButton = new JButton("Override House Settings");
        logoutButton = new JButton("Logout");

        ViewLogsButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        manageUserCodesButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        managePrivilegesButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        remoteOverrideButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        logoutButton.setFont(new Font("Dialog", Font.PLAIN, 30));

        adminMainMenuPanel.setSize(620, 480);
        adminMainMenuPanel.setBackground(Color.LIGHT_GRAY);
        adminMainMenuPanel.setLocation(370, 10);
        adminMainMenuPanel.setLayout(new GridLayout(6, 1));
        adminMainMenuPanel.add(label);
        adminMainMenuPanel.add(managePrivilegesButton);
        adminMainMenuPanel.add(manageUserCodesButton);
        adminMainMenuPanel.add(remoteOverrideButton);
        adminMainMenuPanel.add(ViewLogsButton);
        adminMainMenuPanel.add(logoutButton);

        ViewLogsButton.addActionListener(new adminToolsetListener());
        manageUserCodesButton.addActionListener(new adminToolsetListener());
        remoteOverrideButton.addActionListener(new adminToolsetListener());
        logoutButton.addActionListener(new adminToolsetListener());
        managePrivilegesButton.addActionListener(new adminToolsetListener());

        adminMainMenuPanel.setVisible(false);

    }

    private void buildLogsMainPanel() {
        logsMainPanel = new JPanel();
        logsMainPanel.setSize(620, 480);
        logsMainPanel.setBackground(Color.LIGHT_GRAY);
        logsMainPanel.setLocation(370, 10);

        JPanel subPanel1 = new JPanel();
        subPanel1.setBackground(Color.LIGHT_GRAY);
        JLabel header = new JLabel("Manage Logs");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));
        subPanel1.add(header);

        JPanel subPanel4 = new JPanel();
        subPanel4.setBackground(Color.LIGHT_GRAY);
        JLabel label2 = new JLabel("Select the log you would like "
                + "to view and press submit.");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel4.add(label2);

        JPanel subPanel2 = new JPanel(new GridLayout(1, 3));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        buttonGroup1 = new ButtonGroup();
        radioButton1 = new JRadioButton("Clock Log");
        radioButton1.setHorizontalAlignment(JLabel.CENTER);
        radioButton1.setVerticalAlignment(JLabel.CENTER);
        radioButton1.setFont(new Font("Dialog", Font.PLAIN, 20));
        radioButton1.setSelected(true);

        radioButton2 = new JRadioButton("Door Log");
        radioButton2.setHorizontalAlignment(JLabel.CENTER);
        radioButton2.setVerticalAlignment(JLabel.CENTER);
        radioButton2.setFont(new Font("Dialog", Font.PLAIN, 20));

        radioButton3 = new JRadioButton("Room Log");
        radioButton3.setHorizontalAlignment(JLabel.CENTER);
        radioButton3.setVerticalAlignment(JLabel.CENTER);
        radioButton3.setFont(new Font("Dialog", Font.PLAIN, 20));

        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        buttonGroup1.add(radioButton3);
        subPanel2.add(radioButton1);
        subPanel2.add(radioButton2);
        subPanel2.add(radioButton3);

        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        logsMainSubmitButton = new JButton("Submit");
        logsMainSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        logsMainCancelButton = new JButton("Return to Previous Menu");
        logsMainCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        subPanel3.add(logsMainCancelButton);
        subPanel3.add(logsMainSubmitButton);
        subPanel3.setBackground(Color.LIGHT_GRAY);

        JPanel subPanel5 = new JPanel();
        subPanel5.setBackground(Color.LIGHT_GRAY);

        JPanel subPanel6 = new JPanel();
        subPanel6.setBackground(Color.LIGHT_GRAY);

        logsMainPanel.setLayout(new GridLayout(6, 1));
        logsMainPanel.add(subPanel1);
        logsMainPanel.add(subPanel5);
        logsMainPanel.add(subPanel4);
        logsMainPanel.add(subPanel2);
        logsMainPanel.add(subPanel6);
        logsMainPanel.add(subPanel3);
        logsMainPanel.setVisible(false);

        logsMainSubmitButton.addActionListener(new adminToolsetListener());
        logsMainCancelButton.addActionListener(new adminToolsetListener());
    }

    private void buildViewLogsPanel() {
        viewLogsPanel = new JPanel(new BorderLayout());
        viewLogsPanel.setSize(620, 480);
        viewLogsPanel.setBackground(Color.LIGHT_GRAY);
        viewLogsPanel.setLocation(370, 10);
        viewLogsPanel.setVisible(false);

        logTextArea = new JTextArea(16, 58);
        logTextArea.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(logTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        logTextArea.setLineWrap(false);
        logTextArea.setEditable(false);
        logTextArea.setText("A log will show here when they exist.");
        //logField.setDocument(logDocument);
        cancelViewLogsButton = new JButton("Return to Log Selection");
        cancelViewLogsButton.setHorizontalAlignment(JButton.CENTER);
        cancelViewLogsButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        viewLogsPanel.add(scroll, BorderLayout.CENTER);
        viewLogsPanel.add(cancelViewLogsButton, BorderLayout.SOUTH);

        cancelViewLogsButton.addActionListener(new adminToolsetListener());

    }

    private void buildFailPanel() {
        failPanel = new JPanel(new GridLayout(4, 1));
        failPanel.setSize(620, 480);
        failPanel.setBackground(Color.LIGHT_GRAY);
        failPanel.setLocation(370, 10);
        JLabel label = new JLabel("Enter an Administrator User ID");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 24));

        JLabel label2 = new JLabel(" and press VALIDATE to Continue.");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label2.setFont(new Font("Dialog", Font.BOLD, 24));

        validateButton = new JButton("VALIDATE");
        validateButton.setPreferredSize(new Dimension(400, 100));
        validateButton.setFont(new Font("Dialog", Font.PLAIN, 40));
        failPanelLabel3 = new JLabel();

        failPanel.add(label);
        failPanel.add(label2);
        failPanel.add(failPanelLabel3);
        failPanel.add(validateButton);
        failPanel.setVisible(false);

        validateButton.addActionListener(new adminToolsetListener());
    }

    private void buildUserCodesMainPanel() {
        userCodesMainPanel = new JPanel(new GridLayout(6, 1));
        userCodesMainPanel.setSize(620, 480);
        userCodesMainPanel.setBackground(Color.LIGHT_GRAY);
        userCodesMainPanel.setLocation(370, 10);

        JLabel header = new JLabel("Manage User IDs");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        userCodesButton1 = new JButton("Add User");
        userCodesButton1.setHorizontalAlignment(JButton.CENTER);
        userCodesButton1.setFont(new Font("Dialog", Font.PLAIN, 30));

        removeUsersButton = new JButton("Remove User");
        removeUsersButton.setHorizontalAlignment(JButton.CENTER);
        removeUsersButton.setFont(new Font("Dialog", Font.PLAIN, 30));

        userCodesButton3 = new JButton("Update User");
        userCodesButton3.setHorizontalAlignment(JButton.CENTER);
        userCodesButton3.setFont(new Font("Dialog", Font.PLAIN, 30));

        viewUsersButton = new JButton("View Users");
        viewUsersButton.setHorizontalAlignment(JButton.CENTER);
        viewUsersButton.setFont(new Font("Dialog", Font.PLAIN, 30));

        returnFromUserCodesButton = new JButton("Return to the Main Menu");
        returnFromUserCodesButton.setHorizontalAlignment(JButton.CENTER);
        returnFromUserCodesButton.setFont(new Font("Dialog", Font.PLAIN, 30));

        userCodesMainPanel.add(header);
        userCodesMainPanel.add(userCodesButton1);
        userCodesMainPanel.add(removeUsersButton);
        userCodesMainPanel.add(userCodesButton3);
        userCodesMainPanel.add(viewUsersButton);
        userCodesMainPanel.add(returnFromUserCodesButton);

        userCodesButton1.addActionListener(new adminToolsetListener());
        removeUsersButton.addActionListener(new adminToolsetListener());
        userCodesButton3.addActionListener(new adminToolsetListener());
        viewUsersButton.addActionListener(new adminToolsetListener());
        returnFromUserCodesButton.addActionListener(new adminToolsetListener());

        userCodesMainPanel.setVisible(false);
    }

    private void buildAddUserPanel() {
        addUserPanel = new JPanel(new GridLayout(6, 1));
        addUserPanel.setSize(620, 480);
        addUserPanel.setBackground(Color.LIGHT_GRAY);
        addUserPanel.setLocation(370, 10);

        JLabel header = new JLabel("Add User");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 40));

        JLabel label = new JLabel("User ID: ");
        label.setHorizontalAlignment(JButton.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 40));
        addUserTextField = new JTextField();
        addUserTextField.setFont(new Font("Dialog", Font.PLAIN, 40));
        JPanel subPanel1 = new JPanel(new GridLayout(1, 2));
        subPanel1.setBackground(Color.LIGHT_GRAY);
        subPanel1.add(label);
        subPanel1.add(addUserTextField);

        addUserCancelButton = new JButton("Return to the Previous Menu");
        addUserCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        addUserSubmitButton = new JButton("Submit User");
        addUserSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel2 = new JPanel(new GridLayout(1, 2));
        subPanel2.add(addUserCancelButton);
        subPanel2.add(addUserSubmitButton);
        subPanel2.setBackground(Color.LIGHT_GRAY);

        JLabel label2 = new JLabel("Enter a unique userID, username and select a privilege group then press \"Submit User\".");
        label2.setFont(new Font("Dialog", Font.PLAIN, 13));
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel subPanel3 = new JPanel();
        subPanel3.setBackground(Color.LIGHT_GRAY);
        subPanel3.add(label2);

        addUserTextField2 = new JTextField();
        addUserTextField2.setFont(new Font("Dialog", Font.PLAIN, 40));
        JLabel label7 = new JLabel("Username: ");
        label7.setHorizontalAlignment(JButton.CENTER);
        label7.setFont(new Font("Dialog", Font.PLAIN, 40));
        JPanel subPanel4 = new JPanel(new GridLayout(1, 2));
        subPanel4.setBackground(Color.LIGHT_GRAY);
        subPanel4.add(label7);
        subPanel4.add(addUserTextField2);

        //added
        addUserButtonGroup = new ButtonGroup();
        addUserRadioButton1 = new JRadioButton("Administrator");
        addUserRadioButton3 = new JRadioButton("Adult User");
        addUserRadioButton2 = new JRadioButton("Child User");
        addUserRadioButton2.setSelected(true);
        addUserButtonGroup.add(addUserRadioButton1);
        addUserButtonGroup.add(addUserRadioButton2);
        addUserButtonGroup.add(addUserRadioButton3);
        JPanel subPanel5 = new JPanel(new GridLayout(1, 4));
        subPanel5.setBackground(Color.LIGHT_GRAY);
        JLabel label99 = new JLabel("Privilege group: ");
        label99.setHorizontalAlignment(JButton.CENTER);
        label99.setFont(new Font("Dialog", Font.PLAIN, 16));
        subPanel5.add(label99);
        subPanel5.add(addUserRadioButton1);
        subPanel5.add(addUserRadioButton2);
        subPanel5.add(addUserRadioButton3);
        //added

        addUserPanel.add(header);
        addUserPanel.add(subPanel3);
        addUserPanel.add(subPanel1);
        addUserPanel.add(subPanel4);
        addUserPanel.add(subPanel5);
        addUserPanel.add(subPanel2);

        addUserCancelButton.addActionListener(new adminToolsetListener());
        addUserSubmitButton.addActionListener(new adminToolsetListener());

        addUserPanel.setVisible(false);
    }

    private void buildRemoveUserPanel() {
        removeUserPanel = new JPanel(new GridLayout(6, 1));
        removeUserPanel.setSize(620, 480);
        removeUserPanel.setBackground(Color.LIGHT_GRAY);
        removeUserPanel.setLocation(370, 10);

        JLabel header = new JLabel("Remove User ID");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 40));

        JPanel subPanel3 = new JPanel();
        subPanel3.setBackground(Color.LIGHT_GRAY);
        removeUserComboBox = new JComboBox();
        removeUserComboBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        removeUserComboBox.addItem("TEST");
        removeUserComboBox.setPreferredSize(new Dimension(200, 30));
        subPanel3.add(removeUserComboBox);

        JPanel subPanel1 = new JPanel();
        JLabel label = new JLabel("Select the user you wish to remove");
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel1.setBackground(Color.LIGHT_GRAY);
        subPanel1.add(label);

        JPanel subPanel2 = new JPanel();
        JLabel label2 = new JLabel("then press Remove User");
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        subPanel2.add(label2);

        JPanel subPanel4 = new JPanel();
        subPanel4.setBackground(Color.LIGHT_GRAY);

        JPanel subPanel5 = new JPanel(new GridLayout(1, 2));
        removeUserCancelButton = new JButton("Return to the Previous Menu");
        removeUserCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        removeUserSubmitButton = new JButton("Remove User");
        removeUserSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel5.setBackground(Color.LIGHT_GRAY);
        subPanel5.add(removeUserCancelButton);
        subPanel5.add(removeUserSubmitButton);

        removeUserPanel.add(header);
        removeUserPanel.add(subPanel1);
        removeUserPanel.add(subPanel3);
        removeUserPanel.add(subPanel2);
        removeUserPanel.add(subPanel4);
        removeUserPanel.add(subPanel5);

        removeUserPanel.setVisible(false);

        removeUserCancelButton.addActionListener(new adminToolsetListener());
        removeUserSubmitButton.addActionListener(new adminToolsetListener());

    }

    private void buildUpdateUserPanel() {
        updateUserPanel = new JPanel(new GridLayout(7, 1));
        updateUserPanel.setSize(620, 480);
        updateUserPanel.setBackground(Color.LIGHT_GRAY);
        updateUserPanel.setLocation(370, 10);

        JLabel header = new JLabel("Update User ID");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 40));

        JLabel label = new JLabel("Select the User ID You Wish to Change");
        label.setHorizontalAlignment(JButton.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(Color.LIGHT_GRAY);
        updateUserComboBox = new JComboBox();
        updateUserComboBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        updateUserComboBox.setPreferredSize(new Dimension(200, 30));
        subPanel2.add(updateUserComboBox);

        JLabel label4 = new JLabel("Next Enter a New User ID or Username and press update user.");
        label4.setHorizontalAlignment(JButton.CENTER);

        label4.setFont(new Font("Dialog", Font.PLAIN, 14));

        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        subPanel3.setBackground(Color.LIGHT_GRAY);
        JLabel label2 = new JLabel("  New User ID: ");
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));
        updateUserTextField = new JTextField();
        updateUserTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel3.add(label2);
        subPanel3.add(updateUserTextField);

        JPanel subPanel8 = new JPanel(new GridLayout(1, 2));
        subPanel8.setBackground(Color.LIGHT_GRAY);
        JLabel labl = new JLabel("  New Username: ");
        labl.setFont(new Font("Dialog", Font.PLAIN, 20));
        updateUserTextField2 = new JTextField();
        updateUserTextField2.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel8.add(labl);
        subPanel8.add(updateUserTextField2);

        JPanel subPanel4 = new JPanel(new GridLayout(1, 2));
        subPanel4.setBackground(Color.LIGHT_GRAY);
        updateUserCancelButton = new JButton("Return to the Previous Menu");
        updateUserCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        updateUserSubmitButton = new JButton("Update User");
        updateUserSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel4.add(updateUserCancelButton);
        subPanel4.add(updateUserSubmitButton);

        updateUserPanel.add(header);
        updateUserPanel.add(label);
        updateUserPanel.add(subPanel2);
        updateUserPanel.add(label4);
        updateUserPanel.add(subPanel3);
        updateUserPanel.add(subPanel8);
        updateUserPanel.add(subPanel4);

        updateUserPanel.setVisible(false);

        updateUserCancelButton.addActionListener(new adminToolsetListener());
        updateUserSubmitButton.addActionListener(new adminToolsetListener());
    }

    private void buildViewUsersPanel() {
        viewUsersPanel = new JPanel(new BorderLayout());
        viewUsersPanel.setSize(620, 480);
        viewUsersPanel.setBackground(Color.LIGHT_GRAY);
        viewUsersPanel.setLocation(370, 10);
        JPanel subPanel2 = new JPanel();
        //subPanel2.setBackground(Color.LIGHT_GRAY);
        viewUsersCancelButton = new JButton("Return to the Previous Menu");
        viewUsersCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        viewUsersCancelButton.setPreferredSize(new Dimension(400, 100));
        subPanel2.add(viewUsersCancelButton);

        display = new JTextArea(16, 58);
        display.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //display.append(admin.toString());
        JLabel header = new JLabel("View Users");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 40));

        viewUsersPanel.add(header, BorderLayout.NORTH);
        viewUsersPanel.add(scroll, BorderLayout.CENTER);
        viewUsersPanel.add(subPanel2, BorderLayout.SOUTH);
        viewUsersPanel.setVisible(false);

        viewUsersCancelButton.addActionListener(new adminToolsetListener());
    }

    private void buildOverrideMainPanel() {
        overrideMainPanel = new JPanel(new GridLayout(5, 1));
        overrideMainPanel.setSize(620, 480);
        overrideMainPanel.setBackground(Color.LIGHT_GRAY);
        overrideMainPanel.setLocation(370, 10);

        JLabel header = new JLabel("Override House Values");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        overrideClockButton = new JButton("Alarm Values");
        overrideClockButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        overrideDoorsButton = new JButton("Door Values");
        overrideDoorsButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        overrideRoomButton = new JButton("Room Values");
        overrideRoomButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        overrideCancelButton = new JButton("Return to the Previous Menu");
        overrideCancelButton.setFont(new Font("Dialog", Font.PLAIN, 30));

        overrideMainPanel.add(header);
        overrideMainPanel.add(overrideClockButton);
        overrideMainPanel.add(overrideDoorsButton);
        overrideMainPanel.add(overrideRoomButton);
        overrideMainPanel.add(overrideCancelButton);

        overrideClockButton.addActionListener(new adminToolsetListener());
        overrideDoorsButton.addActionListener(new adminToolsetListener());
        overrideRoomButton.addActionListener(new adminToolsetListener());
        overrideCancelButton.addActionListener(new adminToolsetListener());

        overrideMainPanel.setVisible(false);
    }

    private void buildOverrideClockPanel() {
        overrideClockPanel = new JPanel(new GridLayout(9, 1));
        overrideClockPanel.setSize(620, 480);
        overrideClockPanel.setBackground(Color.LIGHT_GRAY);
        overrideClockPanel.setLocation(370, 10);

        JLabel header = new JLabel("Override Alarm");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        JLabel labely = new JLabel("Change the Appropriate Values and press Submit Changes");
        labely.setFont(new Font("Dialog", Font.PLAIN, 14));
        labely.setHorizontalAlignment(JButton.CENTER);

        JPanel subPanel5 = new JPanel(new GridLayout(1, 8));
        subPanel5.setBackground(Color.LIGHT_GRAY);
        clockCheckM = new JCheckBox("Monday");
        clockCheckT = new JCheckBox("Tuesday");
        clockCheckW = new JCheckBox("Wednesday");
        clockCheckR = new JCheckBox("Thursday");
        clockCheckF = new JCheckBox("Friday");
        clockCheckS = new JCheckBox("Saturday");
        clockCheckSu = new JCheckBox("Sunday");
        JLabel l1 = new JLabel("  Day: ");
        l1.setFont(new Font("Dialog", Font.PLAIN, 20));
        //l1.setHorizontalAlignment(JButton.CENTER);
        subPanel5.add(l1);
        subPanel5.add(clockCheckM);
        subPanel5.add(clockCheckT);
        subPanel5.add(clockCheckW);
        subPanel5.add(clockCheckR);
        subPanel5.add(clockCheckF);
        subPanel5.add(clockCheckS);
        subPanel5.add(clockCheckSu);

        clockButtonGroup = new ButtonGroup();
        clockRadioButton1 = new JRadioButton("Buzzer");
        clockRadioButton2 = new JRadioButton("Radio");
        clockButtonGroup.add(clockRadioButton1);
        clockButtonGroup.add(clockRadioButton2);
        JPanel subPanel6 = new JPanel(new GridLayout(1, 3));
        subPanel6.setBackground(Color.LIGHT_GRAY);
        JLabel labe = new JLabel("  Alarm Sound: ");
        //labe.setHorizontalAlignment(JButton.CENTER);
        labe.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel6.add(labe);
        subPanel6.add(clockRadioButton1);
        subPanel6.add(clockRadioButton2);

        clockButtonGroup2 = new ButtonGroup();
        clockRadioButton3 = new JRadioButton("Bedroom1");
        clockRadioButton4 = new JRadioButton("Bedroom2");
        clockRadioButton5 = new JRadioButton("Living Room");
        clockButtonGroup2.add(clockRadioButton3);
        clockButtonGroup2.add(clockRadioButton4);
        clockButtonGroup2.add(clockRadioButton5);
        JPanel subPanel7 = new JPanel(new GridLayout(1, 4));
        subPanel7.setBackground(Color.LIGHT_GRAY);
        JLabel lbl = new JLabel("  Select Room: ");
        //lbl.setHorizontalAlignment(JButton.CENTER);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel7.add(lbl);
        subPanel7.add(clockRadioButton3);
        subPanel7.add(clockRadioButton4);
        subPanel7.add(clockRadioButton5);

        JLabel label = new JLabel("  Hour: (0-24)");
        //label.setHorizontalAlignment(JButton.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideHoursTextField = new JTextField();
        overrideHoursTextField.setFont(new Font("Dialog", Font.PLAIN, 30));
        JPanel subPanel1 = new JPanel(new GridLayout(1, 2));
        subPanel1.setBackground(Color.LIGHT_GRAY);
        subPanel1.add(label);
        subPanel1.add(overrideHoursTextField);

        JLabel label2 = new JLabel("  Minutes: (0-59)");
        //label2.setHorizontalAlignment(JButton.CENTER);
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideMinutesTextField = new JTextField();
        overrideMinutesTextField.setFont(new Font("Dialog", Font.PLAIN, 30));
        JPanel subPanel2 = new JPanel(new GridLayout(1, 2));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        subPanel2.add(label2);
        subPanel2.add(overrideMinutesTextField);

        JLabel label3 = new JLabel("  Snooze Length(MIN): ");
        //label3.setHorizontalAlignment(JButton.CENTER);
        label3.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideSnoozeTextField = new JTextField();
        overrideSnoozeTextField.setFont(new Font("Dialog", Font.PLAIN, 30));
        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        subPanel3.setBackground(Color.LIGHT_GRAY);
        subPanel3.add(label3);
        subPanel3.add(overrideSnoozeTextField);

        JPanel subPanel4 = new JPanel(new GridLayout(1, 2));
        subPanel4.setBackground(Color.LIGHT_GRAY);
        overrideClockCancelButton = new JButton("Return to the Previous Menu");
        overrideClockCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideClockSubmitButton = new JButton("Submit Changes");
        overrideClockSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel4.add(overrideClockCancelButton);
        subPanel4.add(overrideClockSubmitButton);

        overrideClockPanel.add(header);
        overrideClockPanel.add(labely);
        overrideClockPanel.add(subPanel7);
        overrideClockPanel.add(subPanel5);
        overrideClockPanel.add(subPanel6);
        overrideClockPanel.add(subPanel1);
        overrideClockPanel.add(subPanel2);
        overrideClockPanel.add(subPanel3);
        overrideClockPanel.add(subPanel4);

        overrideClockCancelButton.addActionListener(new adminToolsetListener());
        overrideClockSubmitButton.addActionListener(new adminToolsetListener());

        overrideClockPanel.setVisible(false);
    }

    private void buildOverrideDoorPanel() {
        overrideDoorPanel = new JPanel(new GridLayout(5, 1));
        overrideDoorPanel.setSize(620, 480);
        overrideDoorPanel.setBackground(Color.LIGHT_GRAY);
        overrideDoorPanel.setLocation(370, 10);

        JLabel header = new JLabel("Override Door Values");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        JLabel labely = new JLabel("Change the Appropriate Values and press \"Submit Changes\"");
        labely.setFont(new Font("Dialog", Font.PLAIN, 16));
        labely.setHorizontalAlignment(JButton.CENTER);

        JLabel label1 = new JLabel("Set Mode (OFF/STAY/AWAY): ");
        label1.setHorizontalAlignment(JButton.CENTER);
        label1.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel1 = new JPanel(new GridLayout(1, 2));
        subPanel1.setBackground(Color.LIGHT_GRAY);
        subPanel1.add(label1);
        doorButtonGroup2 = new ButtonGroup();
        doorRadioButton3 = new JRadioButton("Off");
        doorRadioButton4 = new JRadioButton("Stay");
        doorRadioButton5 = new JRadioButton("Away");
        doorButtonGroup2.add(doorRadioButton3);
        doorButtonGroup2.add(doorRadioButton4);
        doorButtonGroup2.add(doorRadioButton5);
        JPanel subPanel88 = new JPanel(new GridLayout(1, 3));
        subPanel88.setBackground(Color.LIGHT_GRAY);
        subPanel88.add(doorRadioButton3);
        subPanel88.add(doorRadioButton4);
        subPanel88.add(doorRadioButton5);
        subPanel1.add(subPanel88);

        JLabel label2 = new JLabel("Set Alarm (ON/OFF): ");
        label2.setHorizontalAlignment(JButton.CENTER);
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel2 = new JPanel(new GridLayout(1, 2));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        subPanel2.add(label2);
        doorButtonGroup = new ButtonGroup();
        doorRadioButton1 = new JRadioButton("Off");
        doorRadioButton2 = new JRadioButton("On");
        doorButtonGroup.add(doorRadioButton1);
        doorButtonGroup.add(doorRadioButton2);
        JPanel subPanel99 = new JPanel(new GridLayout(1, 2));
        subPanel99.setBackground(Color.LIGHT_GRAY);
        subPanel99.add(doorRadioButton2);
        subPanel99.add(doorRadioButton1);
        subPanel2.add(subPanel99);

        overrideDoorCancelButton = new JButton("Return to the Previous Menu");
        overrideDoorCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideDoorSubmitButton = new JButton("Submit Changes");
        overrideDoorSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideDoorCancelButton.addActionListener(new adminToolsetListener());
        overrideDoorSubmitButton.addActionListener(new adminToolsetListener());
        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        subPanel3.setBackground(Color.LIGHT_GRAY);
        subPanel3.add(overrideDoorCancelButton);
        subPanel3.add(overrideDoorSubmitButton);

        overrideDoorPanel.add(header);
        overrideDoorPanel.add(labely);
        overrideDoorPanel.add(subPanel2);
        overrideDoorPanel.add(subPanel1);
        overrideDoorPanel.add(subPanel3);

        overrideDoorPanel.setVisible(false);
    }

    private void buildOverrideRoomPanel() {
        overrideRoomPanel = new JPanel(new GridLayout(8, 1));
        overrideRoomPanel.setSize(620, 480);
        overrideRoomPanel.setBackground(Color.LIGHT_GRAY);
        overrideRoomPanel.setLocation(370, 10);

        JLabel header = new JLabel("Override Room Values");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        JLabel labely = new JLabel("Change the Appropriate Values and press Submit Changes");
        labely.setFont(new Font("Dialog", Font.PLAIN, 14));
        labely.setHorizontalAlignment(JButton.CENTER);

        roomButtonGroup = new ButtonGroup();
        roomRadioButton1 = new JRadioButton("Bedroom1");
        roomRadioButton2 = new JRadioButton("Bedroom2");
        roomRadioButton3 = new JRadioButton("Living Room");
        roomButtonGroup.add(roomRadioButton1);
        roomButtonGroup.add(roomRadioButton2);
        roomButtonGroup.add(roomRadioButton3);
        JPanel subPanel5 = new JPanel(new GridLayout(1, 4));
        subPanel5.setBackground(Color.LIGHT_GRAY);
        JLabel lbl = new JLabel("  Select Room: ");
        lbl.setFont(new Font("Dialog", Font.PLAIN, 20));
        subPanel5.add(lbl);
        subPanel5.add(roomRadioButton1);
        subPanel5.add(roomRadioButton2);
        subPanel5.add(roomRadioButton3);

        JLabel label1 = new JLabel("  Set Fan: ");
        label1.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel1 = new JPanel(new GridLayout(1, 4));
        subPanel1.setBackground(Color.LIGHT_GRAY);
        fanButtonOff = new JButton("Off");
        fanButtonOff.addActionListener(new adminToolsetListener());
        fanButtonLow = new JButton("Low");
        fanButtonLow.addActionListener(new adminToolsetListener());
        fanButtonHigh = new JButton("High");
        fanButtonHigh.addActionListener(new adminToolsetListener());
        subPanel1.add(label1);
        subPanel1.add(fanButtonOff);
        subPanel1.add(fanButtonLow);
        subPanel1.add(fanButtonHigh);

        JLabel label2 = new JLabel("  Set Lights (0-100): ");
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));
        roomSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        roomSlider.setLabelTable(roomSlider.createStandardLabels(25));
        roomSlider.setMinorTickSpacing(5);
        roomSlider.setMajorTickSpacing(25);
        roomSlider.setPaintTicks(true);
        roomSlider.setPaintLabels(true);
        JPanel subPanel2 = new JPanel(new GridLayout(1, 2));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        subPanel2.add(label2);
        subPanel2.add(roomSlider);

        JLabel label3 = new JLabel("  Set Temperature: ");
        label3.setFont(new Font("Dialog", Font.PLAIN, 20));
        SpinnerNumberModel snm = new SpinnerNumberModel(50,
                0, 100, 1);
        roomSpinner = new JSpinner(snm);
        roomSpinner.setFont(new Font("Dialog", Font.PLAIN, 30));
        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        subPanel3.setBackground(Color.LIGHT_GRAY);
        subPanel3.add(label3);
        subPanel3.add(roomSpinner);

        JPanel subPanel6 = new JPanel(new GridLayout(1, 2));
        subPanel6.setBackground(Color.LIGHT_GRAY);
        JLabel label6 = new JLabel("  Set Min/Max Temperature");
        label6.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel7 = new JPanel(new GridLayout(1, 2));
        subPanel7.setBackground(Color.LIGHT_GRAY);
        overrideRoomTempTextFieldLow = new JTextField();
        overrideRoomTempTextFieldLow.setFont(new Font("Dialog", Font.PLAIN, 30));
        overrideRoomTempTextFieldLow.setText("Min");
        overrideRoomTempTextFieldHigh = new JTextField();
        overrideRoomTempTextFieldHigh.setFont(new Font("Dialog", Font.PLAIN, 30));
        overrideRoomTempTextFieldHigh.setText("Max");
        subPanel7.add(overrideRoomTempTextFieldLow);
        subPanel7.add(overrideRoomTempTextFieldHigh);
        subPanel6.add(label6);
        subPanel6.add(subPanel7);

        overrideRoomCancelButton = new JButton("Return to the Previous Menu");
        overrideRoomCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideRoomSubmitButton = new JButton("Submit Changes");
        overrideRoomSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        overrideRoomCancelButton.addActionListener(new adminToolsetListener());
        overrideRoomSubmitButton.addActionListener(new adminToolsetListener());
        JPanel subPanel4 = new JPanel(new GridLayout(1, 2));
        subPanel4.setBackground(Color.LIGHT_GRAY);
        subPanel4.add(overrideRoomCancelButton);
        subPanel4.add(overrideRoomSubmitButton);

        overrideRoomPanel.add(header);
        overrideRoomPanel.add(labely);
        overrideRoomPanel.add(subPanel5);
        overrideRoomPanel.add(subPanel1);
        overrideRoomPanel.add(subPanel2);
        overrideRoomPanel.add(subPanel3);
        overrideRoomPanel.add(subPanel6);
        overrideRoomPanel.add(subPanel4);

        overrideRoomPanel.setVisible(false);
    }

    private void buildManagePrivilegesPanel() {
        managePrivilegesPanel = new JPanel(new GridLayout(7, 1));
        managePrivilegesPanel.setSize(620, 480);
        managePrivilegesPanel.setBackground(Color.LIGHT_GRAY);
        managePrivilegesPanel.setLocation(370, 10);

        JLabel header = new JLabel("Manage Privileges");
        header.setHorizontalAlignment(JButton.CENTER);
        header.setFont(new Font("Dialog", Font.BOLD, 50));

        JLabel label = new JLabel("Select a User ID");
        label.setHorizontalAlignment(JButton.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));

        JLabel label2 = new JLabel("Select a privilege group and press "
                + "Submit Changes");
        label2.setHorizontalAlignment(JButton.CENTER);
        label2.setFont(new Font("Dialog", Font.PLAIN, 20));

        JLabel label3 = new JLabel("");
        label3.setHorizontalAlignment(JButton.CENTER);
        label3.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel subPanel1 = new JPanel();
        subPanel1.setBackground(Color.LIGHT_GRAY);
        managePrivilegesComboBox = new JComboBox();
        managePrivilegesComboBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        managePrivilegesComboBox.addItem("TEST");
        managePrivilegesComboBox.setPreferredSize(new Dimension(200, 30));
        subPanel1.add(managePrivilegesComboBox);

        managePrivilegesButtonGroup = new ButtonGroup();
        managePrivilegesRadioButton1 = new JRadioButton("Administrator");
        managePrivilegesRadioButton3 = new JRadioButton("Adult User");
        managePrivilegesRadioButton2 = new JRadioButton("Child User");
        managePrivilegesRadioButton2.setSelected(true);
        managePrivilegesButtonGroup.add(managePrivilegesRadioButton1);
        managePrivilegesButtonGroup.add(managePrivilegesRadioButton2);
        managePrivilegesButtonGroup.add(managePrivilegesRadioButton3);
        JLabel lbl2 = new JLabel("  Privilege Group: ");
        lbl2.setFont(new Font("Dialog", Font.PLAIN, 14));

        JPanel subPanel2 = new JPanel(new GridLayout(1, 4));
        subPanel2.setBackground(Color.LIGHT_GRAY);
        subPanel2.add(lbl2);
        subPanel2.add(managePrivilegesRadioButton1);
        subPanel2.add(managePrivilegesRadioButton2);
        subPanel2.add(managePrivilegesRadioButton3);

        managePrivilegesCancelButton = new JButton("Return to the Previous Menu");
        managePrivilegesCancelButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        managePrivilegesSubmitButton = new JButton("Submit Changes");
        managePrivilegesSubmitButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        managePrivilegesCancelButton.addActionListener(new adminToolsetListener());
        managePrivilegesSubmitButton.addActionListener(new adminToolsetListener());
        JPanel subPanel3 = new JPanel(new GridLayout(1, 2));
        subPanel3.setBackground(Color.LIGHT_GRAY);
        subPanel3.add(managePrivilegesCancelButton);
        subPanel3.add(managePrivilegesSubmitButton);

        managePrivilegesPanel.add(header);
        managePrivilegesPanel.add(label);
        managePrivilegesPanel.add(subPanel1);
        managePrivilegesPanel.add(label2);
        managePrivilegesPanel.add(label3);
        managePrivilegesPanel.add(subPanel2);
        managePrivilegesPanel.add(subPanel3);

        managePrivilegesPanel.setVisible(false);

    }

    /**
     * Utility method for addUser, so that all combo boxes are updated.
     */
    private void updateComboBoxes(ArrayList<User> list) {
        managePrivilegesComboBox.removeAllItems();
        removeUserComboBox.removeAllItems();
        updateUserComboBox.removeAllItems();
        //viewUsersComboBox.removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            managePrivilegesComboBox.addItem(list.get(i).getName());
            removeUserComboBox.addItem(list.get(i).getName());
            updateUserComboBox.addItem(list.get(i).getName());
            //viewUsersComboBox.addItem(list.get(i).getName());
        }
    }

    /**
     * Utility for clockOverride
     *
     * @return
     */
    private String clockUtil() {
        StringBuilder b = new StringBuilder();
        if (clockCheckM.isSelected()) {
            b.append("M ");
        }
        if (clockCheckT.isSelected()) {
            b.append("Tue ");
        }
        if (clockCheckW.isSelected()) {
            b.append("W ");
        }
        if (clockCheckR.isSelected()) {
            b.append("Thu ");
        }
        if (clockCheckF.isSelected()) {
            b.append("F ");
        }
        if (clockCheckS.isSelected()) {
            b.append("Sat ");
        }
        if (clockCheckSu.isSelected()) {
            b.append("Sun");
        }
        return b.toString();
    }

    /**
     * Utility for clockOverride
     *
     * @return
     */
    private int clockUtil2() {
        if (clockButtonGroup2.getSelection() != null) {

            String x = clockButtonGroup2.getSelection().toString();
            if (clockRadioButton3.isSelected()) {
                return 2;
            }
            if (clockRadioButton4.isSelected()) {
                return 3;
            } else {
                return 1;
            }
        }
        return -1;
    }

    private String roomUtil1() {
        if (roomRadioButton1.isSelected()) {
            return "Bedroom1";
        }
        if (roomRadioButton2.isSelected()) {
            return "Bedroom2";
        } else {
            return "Living Room";
        }
    }

    /**
     * this returns the boolean value of alarm override
     *
     * @return
     */
    private boolean doorUtil1() {
        if (doorRadioButton1.isSelected()) {
            return false;
        } else {
            return true;
        }
    }

    private char doorUtil2() {
        if (doorRadioButton3.isSelected()) {
            return 'o';
        }
        if (doorRadioButton4.isSelected()) {
            return 's';
        } else {
            return 'a';
        }
    }

    private class adminToolsetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == validateButton) {
                //if (keypad.getValue().equals("123456")) {
                User usr = admin.findUser(keypad.getValue());
                if (admin.validateID(keypad.getValue()) && usr.getPrivileges().equals("admin")) {
                    currentUser = keypad.getValue();
                    adminMainMenuPanel.setVisible(true);
                    failPanel.setVisible(false);
                    keypad.setDisplayValueNull();
                    loginAttempt = 0;
                } else {
                    failPanel.setVisible(false);
                    failPanel.remove(failPanelLabel3);
                    failPanel.remove(validateButton);
                    failPanelLabel3 = new JLabel("Unsucessful Login Attempts:" + Integer.toString(++loginAttempt));
                    failPanelLabel3.setHorizontalAlignment(JLabel.CENTER);
                    failPanelLabel3.setVerticalAlignment(JLabel.CENTER);
                    failPanelLabel3.setFont(new Font("Dialog", Font.BOLD, 24));
                    failPanel.add(failPanelLabel3);
                    failPanel.add(validateButton);
                    failPanel.setVisible(true);

                }

            } else if (e.getSource() == logsMainSubmitButton) {
                logTextArea.setText("");
                if (logNameLabel != null) {
                    viewLogsPanel.remove(logNameLabel);
                }
                if (radioButton1.isSelected()) {
                    logNameLabel = new JLabel("Clock Log:");
                    String str = admin.getLog(1);
                    logTextArea.append(str);
                }
                if (radioButton2.isSelected()) {
                    logNameLabel = new JLabel("Door Log:");
                    String str = admin.getLog(2);
                    logTextArea.append(str);

                }
                if (radioButton3.isSelected()) {
                    logNameLabel = new JLabel("Room Log:");
                    String str = admin.getLog(3);
                    logTextArea.append(str);
                }

                logNameLabel.setFont(new Font("Dialog", Font.PLAIN, 50));
                logNameLabel.setHorizontalAlignment(JLabel.CENTER);
                logTextArea.update(logTextArea.getGraphics());

                viewLogsPanel.add(logNameLabel, BorderLayout.NORTH);

                logsMainPanel.setVisible(false);
                viewLogsPanel.setVisible(true);

            } else if (e.getSource() == logsMainCancelButton) {
                logsMainPanel.setVisible(false);
                adminMainMenuPanel.setVisible(true);
            } else if (e.getSource() == ViewLogsButton) {
                adminMainMenuPanel.setVisible(false);
                logsMainPanel.setVisible(true);
            } else if (e.getSource() == cancelViewLogsButton) {
                viewLogsPanel.setVisible(false);
                logsMainPanel.setVisible(true);
            } else if (e.getSource() == manageUserCodesButton) {
                adminMainMenuPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == returnFromUserCodesButton) {
                userCodesMainPanel.setVisible(false);
                adminMainMenuPanel.setVisible(true);
            } else if (e.getSource() == userCodesButton1) {
                userCodesMainPanel.setVisible(false);
                addUserPanel.setVisible(true);
            } else if (e.getSource() == addUserCancelButton) {
                addUserPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
                addUserTextField.setText("");
            } else if (e.getSource() == addUserSubmitButton) {
                if (addUserTextField.getText().length() == 4 && admin.validateID(addUserTextField.getText()) == false) {
                    String pgroup = "";
                    if (addUserRadioButton1.isSelected()) {
                        pgroup = "admin";
                        addUserRadioButton1.setSelected(false);
                    }
                    if (addUserRadioButton2.isSelected()) {
                        pgroup = "child";
                        addUserRadioButton2.setSelected(false);
                    }
                    if (addUserRadioButton3.isSelected()) {
                        pgroup = "adult";
                        addUserRadioButton3.setSelected(false);
                    }
                    admin.addUser(addUserTextField2.getText(), addUserTextField.getText(), pgroup);
                    updateComboBoxes(admin.getUserList());
                    addUserTextField2.setText("");
                    addUserTextField.setText("");
                }
                addUserPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == removeUsersButton) {

                userCodesMainPanel.setVisible(false);
                removeUserPanel.setVisible(true);
            } else if (e.getSource() == removeUserCancelButton) {
                removeUserPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == removeUserSubmitButton) {
                int x = removeUserComboBox.getSelectedIndex();
                String str = (String) removeUserComboBox.getItemAt(x);
                admin.removeUser(admin.findIndex(str));
                updateComboBoxes(admin.getUserList());
                removeUserPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == logoutButton) {
                adminMainMenuPanel.setVisible(false);
                failPanel.setVisible(true);
                loggedIn = false;
            } else if (e.getSource() == userCodesButton3) {
                userCodesMainPanel.setVisible(false);
                updateUserPanel.setVisible(true);
            } else if (e.getSource() == updateUserCancelButton) {
                updateUserPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == updateUserSubmitButton) {
                if ((updateUserTextField.getText().length() == 4 || updateUserTextField.getText().length() == 0)
                        && admin.validateID(updateUserTextField.getText()) == false) {
                    int x = updateUserComboBox.getSelectedIndex();
                    String str = (String) updateUserComboBox.getItemAt(x);
                    int usrLocal = admin.findIndex(str);
                    ArrayList arr = admin.getUserList();
                    User usr = (User) arr.get(usrLocal);
                    admin.removeUser(usrLocal);

                    if (!updateUserTextField.getText().isEmpty() && !updateUserTextField2.getText().isEmpty()) {
                        admin.addUser(updateUserTextField2.getText(), updateUserTextField.getText(), usr.getPrivileges());
                    } else {
                        if (!updateUserTextField.getText().isEmpty()) {
                            admin.addUser(usr.getName(), updateUserTextField.getText(), usr.getPrivileges());
                        }
                        if (!updateUserTextField2.getText().isEmpty()) {
                            admin.addUser(updateUserTextField2.getText(), usr.getID(), usr.getPrivileges());
                        }
                    }

                    updateComboBoxes(admin.getUserList());

                    updateUserTextField.setText("");
                    updateUserTextField2.setText("");
                    updateUserPanel.setVisible(false);
                    userCodesMainPanel.setVisible(true);
                }
            } else if (e.getSource() == viewUsersButton) {
                display.setText("");
                display.append(admin.toString());
                display.update(display.getGraphics());

                userCodesMainPanel.setVisible(false);
                viewUsersPanel.setVisible(true);
            } else if (e.getSource() == viewUsersCancelButton) {
                viewUsersPanel.setVisible(false);
                userCodesMainPanel.setVisible(true);
            } else if (e.getSource() == overrideCancelButton) {
                overrideMainPanel.setVisible(false);
                adminMainMenuPanel.setVisible(true);
            } else if (e.getSource() == remoteOverrideButton) {
                adminMainMenuPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideClockButton) {
                overrideMainPanel.setVisible(false);
                overrideClockPanel.setVisible(true);
            } else if (e.getSource() == overrideClockCancelButton) {
                overrideClockPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideClockSubmitButton) {
                //update values
                clockRadio.setDays(clockUtil());
                if (clockButtonGroup.getSelection() != null) {
                    if (clockRadioButton1.isSelected()) {
                        clockRadio.setSound(clockRadioButton1.getText());
                    } else {
                        clockRadio.setSound(clockRadioButton2.getText());
                    }
                }
                clockRadio.setRoom(clockUtil2());
                clockRadio.setAlarmHour(Integer.parseInt(overrideHoursTextField.getText()));
                clockRadio.setAlarmMin(Integer.parseInt(overrideMinutesTextField.getText()));
                clockRadio.setSnooze(Integer.parseInt(overrideSnoozeTextField.getText()));
                clockRadio.setUserID(currentUser);
                clockRadio.writeToLog();
                clockRadio.readFile(new File("ClockRadioLog.txt"));
                //then...
                overrideClockPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideDoorsButton) {
                overrideMainPanel.setVisible(false);
                overrideDoorPanel.setVisible(true);
            } else if (e.getSource() == overrideDoorCancelButton) {
                overrideDoorPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideDoorSubmitButton) {
                //update values
                door = new doorModel();
                door.setAlarm(doorUtil1());
                door.setAlarmStatus(doorUtil2());
                door.writeToLog();
                //then...
                overrideDoorPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideRoomButton) {
                overrideMainPanel.setVisible(false);
                overrideRoomPanel.setVisible(true);
            } else if (e.getSource() == overrideRoomCancelButton) {
                overrideRoomPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == overrideRoomSubmitButton) {
                //update values
                room.readFile(new File("RoomLog.txt"));
                room.setRoom(roomUtil1()); //set room
                room.setLights(roomSlider.getValue());
                room.setTemp(Integer.parseInt(roomSpinner.getValue().toString()));
                String str1 = overrideRoomTempTextFieldLow.getText();
                if (!str1.equals("Min")) {
                    room.setMinTemp(Integer.parseInt(overrideRoomTempTextFieldLow.getText()));
                }
                String str2 = overrideRoomTempTextFieldHigh.getText();
                if (!str2.equals("Max")) {
                    room.setMaxTemp(Integer.parseInt(overrideRoomTempTextFieldHigh.getText()));
                }
                room.setCurrentUser(currentUser);
                room.writeToLog();
                //then...
                //set everything back to the way it was
                overrideRoomTempTextFieldHigh.setText("Max");
                overrideRoomTempTextFieldLow.setText("Min");
                overrideRoomPanel.setVisible(false);
                overrideMainPanel.setVisible(true);
            } else if (e.getSource() == fanButtonOff) {
                room.setFan("off");
                room.setRoom(roomUtil1()); //set room
                room.setCurrentUser(currentUser);
                room.writeToLog();
            } else if (e.getSource() == fanButtonLow) {
                room.setFan("low");
                room.setRoom(roomUtil1()); //set room
                room.setCurrentUser(currentUser);
                room.writeToLog();
            } else if (e.getSource() == fanButtonHigh) {
                room.setFan("high");
                room.setRoom(roomUtil1()); //set room
                room.setCurrentUser(currentUser);
                room.writeToLog();
            } else if (e.getSource() == managePrivilegesButton) {
                adminMainMenuPanel.setVisible(false);
                managePrivilegesPanel.setVisible(true);
            } else if (e.getSource() == managePrivilegesCancelButton) {
                managePrivilegesPanel.setVisible(false);
                adminMainMenuPanel.setVisible(true);
            } else if (e.getSource() == managePrivilegesSubmitButton) {
                //update privileges
                String str = managePrivilegesComboBox.getSelectedItem().toString();
                int usrLocal = admin.findIndex(str);
                ArrayList arr = admin.getUserList();
                User usr = (User) arr.get(usrLocal);
                String id = usr.getID();
                String name = usr.getName();
                String priv = new String();

                if (managePrivilegesRadioButton1.isSelected()) {
                    priv = "admin";
                }
                if (managePrivilegesRadioButton2.isSelected()) {
                    priv = "child";
                }
                if (managePrivilegesRadioButton3.isSelected()) {
                    priv = "adult";
                }
                admin.removeUser(usr);
                admin.addUser(name, id, priv);
                //then...
                managePrivilegesPanel.setVisible(false);
                adminMainMenuPanel.setVisible(true);
            }
        }
    }
}
