import java.util.Scanner;

public class Main {
    public static Scanner scn = new Scanner(System.in);
    public static Database database = new Database();
    public static void main(String[] args){

        Course course1 = new Course("Avi", "CSE101", 4, 1);
        Course course2 = new Course("Arpit Singh", "MTH201", 4, 1);
        Course course3 = new Course("Tarun Ahuja", "PHY101", 4, 1);
        Course course4 = new Course("Ishaan", "SSH221", 4, 1);
        Course course5 = new Course("Dhruv", "CSE201", 4, 1);

        Student student1 = new Student("1001", "Shivam", "shivam@iiitd", "shivam");
        Student student2 = new Student("1002", "Aditya", "aditya@iiitd", "aditya");
        Student student3 = new Student("1003", "Shreya", "shreya@iiitd", "shreya");

        Professor prof1 = new Professor("Avi", "avi@iiitd", "avi", "CSE");
        Professor prof2 = new Professor("Tarun Ahuja", "tarun@iiitd", "tarun", "ECE");
        Professor prof3 = new Professor("Dhruv", "dhruv@iiitd", "dhruv", "BIO");

        while(true){
            System.out.println("Welcome to ERP. Enter your role:");
            System.out.println("1. Student ");
            System.out.println("2. Professor ");
            System.out.println("3. Admin ");
            System.out.println("4. Exit");

            int choice = scn.nextInt();
            if(choice == 1){
                boolean flag = true;
                while(flag) {
                System.out.println("1. Login");
                System.out.println("2. Register");
                int c = scn.nextInt();
                    if (c == 1) {
                        Student.studentLogin();
                        flag = false;
                    } else if (c == 2) {
                        System.out.println("Enter Email: ");
                        String mail = Main.scn.next();
                        System.out.println("Enter Password: ");
                        String p = Main.scn.next();
                        Student.registerStudent(mail, p);
                        flag = false;
                    } else {
                        System.out.println("Invalid Choice");
                        break;
                    }
                }
            }
            else if(choice == 2){
                boolean flag = true;
                while(flag) {
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    int c = scn.nextInt();
                    if (c == 1) {
                        Professor.profLogin();
                        flag = false;
                    } else if (c == 2) {
                        System.out.println("Enter Email: ");
                        String mail = Main.scn.next();
                        System.out.println("Enter Password: ");
                        String p = Main.scn.next();
                        System.out.println("Enter name: ");
                        String name = Main.scn.next();
                        System.out.println("Enter department: ");
                        String dept = Main.scn.next();
                        Professor.registerProf(mail, p, name, dept);
                        flag = false;
                    } else {
                        System.out.println("Invalid Choice");
                        break;
                    }
                }
            }
            else if(choice == 3){
                Adminstrator.adminLogin();
            }
            else{
                System.out.println("Exiting the program.");
                break;
            }
        }

    }

}
