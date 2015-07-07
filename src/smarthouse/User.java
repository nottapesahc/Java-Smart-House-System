package smarthouse;


public class User {

    private String name, privileges;
    private String userID;

    public User(String name, String userID, String priv) {
        this.name = name;
        this.userID = userID;
        this.privileges = priv;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return userID;
    }

    public void setUserID(String x) {
        this.userID = x;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setUsername(String x) {
        this.name = x;
    }

    public void setPrivileges(String x) {
        this.privileges = x;
    }

    @Override
    public String toString() {

        return "  User name: " + name + "\n"
                + "  UserID: " + userID + "\n"
                + "  House Privilege Group: " + privileges + "\n";
    }
}
