package smarthouse;

/**
 * Used to perform Administrator commands.
 *
 * @author stevenpatton
 */
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {

    private static ArrayList userList;

    private User user;

    public Admin() {
        this.userList = new ArrayList<>();
        fileManagement(1, null, -1);

    }

    public void addUser(String name, String userID, String priv) {
        user = new User(name, userID, priv);
        userList.add(user);
        fileManagement(2, user, -1);
    }

    public void removeUser(User u) {
        int a = userList.indexOf(u);
        fileManagement(3, u, a);
        userList.remove(u);
    }

    public void removeUser(int u) {
        fileManagement(3, null, u);
        userList.remove(u);
    }

    public boolean getPrivileges(String userID, String action) {
        if (findUser(userID) != null) {
            String priv = user.getPrivileges();
            if (priv.equals("child") && action.equals("lights")) {
                return true;
            } else if (priv.equals("child") && action.equals("temperature")) {
                return true;
            } else if (priv.equals("adult")) {
                return true;
            } else if (priv.equals("admin")) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateID(String userID) {
        for (int i = 0; i < userList.size(); i++) {
            User x = (User) userList.get(i);
            if (x.getID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User findUser(String userID) {
        for (int i = 0; i < userList.size(); i++) {
            user = (User) userList.get(i);
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public int findIndex(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            user = (User) userList.get(i);
            if (user.getName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pass 1 to read in the users from the user File. Pass 2 and a user to add
     * the user to the user File. Pass 3 to update a user File.
     */
    private void fileManagement(int i, User u, int a) {
        Scanner reader = new Scanner("");
        try {
            reader = new Scanner(new File("Users.usr"));
        } catch (FileNotFoundException ex) {
        }
        if (!new File("Users.usr").exists()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Users.usr")));
                out.println("0000|administrator|admin.");
                out.close();
            } catch (IOException ex) {
            }
            user = new User("Administrator", "0000", "admin");
            userList.add(user);
        } else {
            if (i == 1) { //read in the users from the user file
                String ID = "", uName = "", privileges = "";
                int z = 0;
                int p = 0;
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    for (; z < line.length(); z++) {
                        if (line.charAt(z) == '|') {
                            ID = line.substring(0, z);
                            z++;
                            p = z;
                            break;

                        }
                    }
                    for (; z < line.length(); z++) {
                        if (line.charAt(z) == '|') {
                            uName = line.substring(p, z);
                            z++;
                            p = z;
                            break;
                        }
                    }
                    for (; z < line.length(); z++) {
                        if (line.charAt(z) == '.') {
                            privileges = line.substring(p, z);
                            break;
                        }
                    }

                    user = new User(uName, ID, privileges);
                    userList.add(user);
                    z = 0;
                    p = 0;

                }
            }
            if (i == 2) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Users.usr", true)));
                    String uid = u.getID();
                    String usrname = u.getName();
                    String priv = u.getPrivileges();
                    out.println(uid + "|" + usrname + "|" + priv + ".");
                    out.close();
                } catch (IOException ex) {
                }
            }
            if (i == 3) {
                User user;
                PrintWriter out = null;
                File original = new File("Users.usr");
                File file = new File("Temp.usr");
                try {
                    out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                } catch (IOException ex) {
                }
                int n = 0;
                User usrA = (User) userList.get(n);
                for (; n < userList.size(); n++) {
                    if (n == a) {
                    } else {
                        user = (User) userList.get(n);
                        out.println(user.getID() + "|" + user.getName() + "|" + user.getPrivileges() + ".");
                    }
                }
                out.close();
                original.delete();
                file.renameTo(original);
            }
        }
    }

    /**
     * pass a 1 for clock, 2 for door, 3 for room
     *
     * @param i
     * @return
     */
    public String getLog(int i) {
        Scanner reader = new Scanner("");
        if (i == 1) {
            try {
                reader = new Scanner(new File("ClockRadioLog.txt"));
            } catch (FileNotFoundException ex) {
            }
            StringBuilder sb = new StringBuilder();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                sb.append("  ");
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
        if (i == 2) {
            try {
                reader = new Scanner(new File("doorLog.txt"));
            } catch (FileNotFoundException ex) {
            }
            StringBuilder sb = new StringBuilder();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                sb.append("  ");
                sb.append(line);
                sb.append('\n');

            }
            return sb.toString();
        }
        if (i == 3) {
            try {
                reader = new Scanner(new File("RoomLog.txt"));
            } catch (FileNotFoundException ex) {
            }
            StringBuilder sb = new StringBuilder();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                sb.append("  ");
                sb.append(line);
                sb.append('\n');

            }
            return sb.toString();
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userList.size(); i++) {
            user = (User) userList.get(i);
            sb.append("\n");
            sb.append(user.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
