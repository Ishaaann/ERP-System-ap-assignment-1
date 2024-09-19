public abstract class User {
    public String mail;
    public String password;

    public User(String _mail, String pass){
        this.mail = _mail;
        this.password = pass;
    }

    public abstract void mainMenu();
}
