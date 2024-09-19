

import java.util.*;

public class Professor extends User {
    private String name;
    public ArrayList<Course> courseList;
    private String officeHours;
    public String expertise;
    public ArrayList<String> profData;

    // Constructor
    public Professor(String _name, String contact, String pass, String dept) {
        super(contact, pass); // Initializes User with contact and password
        this.name = _name;
        this.expertise = dept;
        this.profData = new ArrayList<>(Arrays.asList(_name, contact, pass, dept));
        this.courseList = new ArrayList<>();
        Main.database.profList.add(profData);
        Main.database.professors.put(contact, this);
    }

    Scanner scn = new Scanner(System.in);

    // Main menu for professors
    @Override
    public void mainMenu() {
        System.out.println("Welcome to ERP:");
        System.out.println("1. Manage Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. View my courses");
        System.out.println("4. Logout");
        int choice = scn.nextInt();
        scn.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                manageCoursesMenu();
                profMenu(this);
                break;
            case 2:
                System.out.print("Enter the course code to view enrolled students: ");
                String courseCode = scn.nextLine();
                Course course = Main.database.courses.get(courseCode);
                if (course != null) {
                    viewEnrolledStudents(course);
                } else {
                    System.out.println("Course not found.");
                }
                profMenu(this);
                break;
            case 3:
                viewMyCourses();
                profMenu(this);
                break;
            case 4:
                System.out.println("Logging out...");
//                profMenu(this);
                break; // Exit from mainMenu to return to the caller
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    // Manage courses
    public void manageCoursesMenu() {
        if (courseList.isEmpty()) {
            System.out.println("No courses to manage.");
            return;
        }
        System.out.print("Enter the course code to manage: ");
        String courseCode = scn.next();
        Course course = Main.database.courses.get(courseCode);
        if (course != null) {
            manageCourse(course);
        } else {
            System.out.println("Course not found.");
        }
        mainMenu();
    }

    // Method to manage a specific course
    public void manageCourse(Course _course) {
        System.out.println("What do you want to manage?");
        System.out.println("1. Syllabus");
        System.out.println("2. Timings");
        System.out.println("3. Credits");
        System.out.println("4. Prerequisites");
        System.out.println("5. Enrollment Limits");
        System.out.println("6. Exit");

        int choice = scn.nextInt();
        scn.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.print("Enter new syllabus: ");
                _course.syllabus = scn.nextLine();
                break;
            case 2:
                System.out.print("Enter new timings: ");
                _course.timings = scn.nextLine();
                break;
            case 3:
                System.out.print("Enter new credits: ");
                _course.credits = scn.nextInt();
                break;
            case 4:
                System.out.print("Enter new prerequisites: ");
                String code = scn.nextLine();
                Course course = Main.database.courses.get(code);
                setPrereq(course);
                break;
            case 5:
                System.out.print("Enter new enrollment limit: ");
                _course.enrollmentLimit = scn.nextInt();
                break;
            default:
                System.out.println("Exiting.");
        }
    }

    // View enrolled students in a course
    public void viewEnrolledStudents(Course _course) {
        System.out.println("Students enrolled in " + _course.courseCode + ":");
        if(_course.enrolledStudent.isEmpty()){
            System.out.println("No students enrolled yet.");
            return;
        }
        for (Student student : _course.enrolledStudent) {
            System.out.println(student.name);
        }
    }

    // Menu for logged-in professor
    public static void profMenu(Professor professor) {
        boolean loggedIn = true;
        while (loggedIn) {
            professor.mainMenu();
            // Control is returned to the main menu after logout
            // This loop will exit if mainMenu returns due to logout
            loggedIn = false; // Ensures the loop exits after logout
        }
    }

    // Login method for professors
    public static void profLogin() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = scn.nextLine();
        System.out.println("Enter password: ");
        String pass = scn.nextLine();
        if (Main.database.professors.containsKey(email)) {
            Professor professor = Main.database.professors.get(email);
            if (professor != null) {
                if (pass.equals(professor.password)) {
                    System.out.println("Welcome to the Professor Menu");
                    profMenu(professor); // Pass the logged-in professor
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("Professor not found.");
            }
        } else {
            System.out.println("Professor not found. Sign Up instead? y/n");
            String ans = scn.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter your name: ");
                String name = scn.nextLine();
                System.out.println("Enter your email: ");
                String mail = scn.nextLine();
                System.out.println("Enter your department: ");
                String dept = scn.nextLine();
                System.out.println("Enter your password: ");
                String p = scn.nextLine();
                registerProf(mail, p, name, dept);
            }
        }
    }

    public static void registerProf(String email, String password, String name, String dept) {
        System.out.println("Registering new professor....");
        Professor newProf = new Professor(name, email, password, dept);
        Main.database.professors.put(email, newProf);
        System.out.println("Registration successful! Welcome, " + name);
        profMenu(newProf);
    }

    public void addCourse(Course course) {
        this.courseList.add(course);
        System.out.println(course.title + " has been added to " + this.name + "'s course list.");
    }

    public static void setPrereq(Course course) {
        System.out.print("Enter the course to be set as prerequisite: ");
        String code = Main.scn.nextLine();
        Course c = Main.database.courses.get(code);
        if (c != null) {
            course.prereq.add(c);
            System.out.println("Course added as prerequisite.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void viewMyCourses(){
        boolean found = false;
        for(Course course: courseList) {
            System.out.println(course.courseCode);
            found = true;
        }
        if(!found){
            System.out.println("No courses yet");
        }
    }

    public String getContact() {
        return this.mail;
    }
}
