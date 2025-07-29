package exalt.com;

public class User implements EventSubscriber {
    private int id;
    private String fName;
    private String lName;
    private boolean isAdmin;

    public User(){}

    public User(int id, String fName, String lName, boolean isAdmin){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User Id : " + this.id + " Name : " + this.fName + " " + this.lName;
    }


    @Override
    public void update(){
        System.out.println("User with " + this.toString() + " has been notified");
    }
    
}
