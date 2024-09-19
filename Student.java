import java.util.*;

public class Student extends User {
    public String name;
    public ArrayList<Course> courseList;
    public ArrayList<Course> prevCourses;
    public double gpa;
    public String roll;
    public int semester = 1;
    public ArrayList<String> studentData;
    public HashMap<Course, Double> completedCourses;
    public HashMap<Course, Double> availableCourses;
    public HashMap<Integer, Double> semesterPerformance; // semester -> GPA

    public Student(String _roll, String _name, String mail, String pass) {
        super(mail, pass);
        this.name = _name;
        this.roll = _roll;
        this.courseList = new ArrayList<>();
        this.gpa = 0.0;
        this.studentData = new ArrayList<>(Arrays.asList(_roll, _name, mail, pass, Double.toString(gpa)));
        Main.database.studentList.add(studentData);
        Main.database.students.put(mail, this);
        this.completedCourses = new HashMap<>();
        this.semesterPerformance = new HashMap<>();
    }

    @Override
    public void mainMenu() {
        System.out.println("Student Menu:");
        System.out.println("1. View Available Courses");
        System.out.println("2. Register for Courses");
        System.out.println("3. View Schedule");
        System.out.println("4. Track Academic Progress");
        System.out.println("5. Drop Course");
        System.out.println("6. Submit Complaint");
        System.out.println("7. View Complaints");
        System.out.println("8. Logout");

        int choice = Main.scn.nextInt();
        switch (choice) {
            case 1:
                viewAvailableCourses();
                break;
            case 2:
                registerCourses();
                break;
            case 3:
                viewSchedule();
                break;
            case 4:
                trackAcademicProgress();
                break;
            case 5:
                dropCourse();
                break;
            case 6:
                submitComplaint();
                break;
            case 7:
                viewComplaintStatus();
                break;
            case 8:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void studentLogin() {
        System.out.println("Enter email: ");
        String email = Main.scn.next();
        System.out.println("Enter password: ");
        String pass = Main.scn.next();
        if (Main.database.students.containsKey(email)) {
            Student student = Main.database.students.get(email);
            if (student.password.equals(pass)) {
                System.out.println("Welcome to the Student Menu");
                boolean loggedIn = true;
                while (loggedIn) {
                    loggedIn = false;
                    student.mainMenu();
                }
            } else {
                System.out.println("Incorrect password.");
            }
        } else {
            System.out.println("Student not found. Sign Up instead? y/n");
            String ans = Main.scn.next();
            if(ans.equals("y")) {
                System.out.println("Enter Email: ");
                String mail = Main.scn.next();
                System.out.println("Enter Password: ");
                String p = Main.scn.next();
                registerStudent(mail,p);
            } else {
                return;
            }
        }
    }

    public static void registerStudent(String email, String password) {
        System.out.println("Registering new account.");
        System.out.println("Enter your name: ");
        String name = Main.scn.next();
        System.out.println("Enter your roll number: ");
        String roll = Main.scn.next();
        Main.scn.nextLine();

        Student newStudent = new Student(roll, name, email, password);
        Main.database.students.put(email, newStudent);

        System.out.println("Registration successful! Welcome, " + name);
        boolean loggedIn = true;
        while (loggedIn) {
            newStudent.mainMenu();
            loggedIn = false;
        }
    }

    public void getCourses() {
        for (Course c : courseList) {
            System.out.println("->" + c.courseCode);
        }
    }

    public void viewAvailableCourses() {
        boolean found = false;
        for (ArrayList<String> data : Main.database.courseCatalog) {
            if (data.get(3).equals(Integer.toString(this.semester))) {
                found = true;
                for (String temp : data) {
                    System.out.print(temp + " ");
                }
                System.out.println();
            }
        }
        if (!found) {
            System.out.println("No courses available.");
        }
        mainMenu();
    }

    public void registerCourses() {
        System.out.println("Enter the Course code to be registered: ");
        String code = Main.scn.next();
        Course course = Main.database.courses.get(code);

        if (course != null && arePrerequisitesMet(course)) {
            int totalCredits = courseList.stream().mapToInt(c -> c.credits).sum();
            if (totalCredits + course.credits <= 20) {
                courseList.add(course);
                System.out.println("Registered for course: " + course.courseCode);
                course.enrolledStudent.add(this);
            } else {
                System.out.println("Credit limit exceeded. You cannot register for more than 20 credits.");
            }
        } else {
            System.out.println("Course not found or prerequisites not met.");
        }

        mainMenu();
    }

    private boolean arePrerequisitesMet(Course course) {
        for (Course pre : course.prereq) {
            boolean prereqMet = completedCourses.containsKey(pre);
            if (!prereqMet) return false;
        }
        return true;
    }

    public void viewSchedule() {
        for (Course c : courseList) {
            System.out.println("Course " + c.courseCode + " has timings " + c.timings);
        }
        mainMenu();
    }

    public void dropCourse() {
        System.out.print("Enter course code to be dropped: ");
        String code = Main.scn.next();
        Course course = Main.database.courses.get(code);
        if (courseList.contains(course)) {
            courseList.remove(course);
            System.out.println("Dropped course: " + course.courseCode);
        } else {
            System.out.println("Course not found in your schedule.");
        }
        mainMenu();
    }

    public void viewGrades() {
        System.out.println("Grades for completed courses:");
        for (Map.Entry<Course, Double> entry : completedCourses.entrySet()) {
            System.out.println("Course: " + entry.getKey().courseCode + ", Grade: " + entry.getValue());
        }
        System.out.println("View previous courses? y/n");
//        String ans = Main.scn.next();
//        if(ans.equals("y")){
//            for (Course c: prevCourses) {
//                System.out.println("Course: " + c.courseCode + ", Grade: " + c.gpa);
//            }
//        }
    }

    public double calculateSGPA(int semester) {
        double totalCredits = 0;
        double totalPoints = 0;
        for (Map.Entry<Course, Double> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            double grade = entry.getValue();
            if (course.semester == semester) {
                totalCredits += course.credits;
                totalPoints += grade * course.credits;
            }
        }
        if (totalCredits == 0) {
            System.out.println("No courses found for the given semester.");
            return 0;
        }
        double sgpa = totalPoints / totalCredits;
        semesterPerformance.put(semester, sgpa);
        return sgpa;
    }

    public double calculateCGPA() {
        double totalCredits = 0;
        double totalPoints = 0;
        for (Map.Entry<Course, Double> entry : completedCourses.entrySet()) {
            Course course = entry.getKey();
            double grade = entry.getValue();
            totalCredits += course.credits;
            totalPoints += grade * course.credits;
        }
        if (totalCredits == 0) {
            System.out.println("No completed courses available.");
            return 0;
        }
        return totalPoints / totalCredits;
    }

    public boolean areAllGradesAssigned(int semester) {
        for (Course course : courseList) {
            if (course.semester == semester && !completedCourses.containsKey(course)) {
                return false;
            }
        }
        return true;
    }

    public void updateGPA(int semester) {
        if (areAllGradesAssigned(semester)) {
            double sgpa = calculateSGPA(semester);
            System.out.println("SGPA for semester " + semester + ": " + sgpa);
        } else {
            System.out.println("Not all grades for the semester have been assigned.");
        }
    }

    public void trackAcademicProgress() {
        System.out.println("Tracking academic progress:");
        for (int sem : semesterPerformance.keySet()) {
            System.out.println("Semester " + sem + ": SGPA = " + semesterPerformance.get(sem));
        }
        double cgpa = calculateCGPA();
        System.out.println("Cumulative Performance:");
        System.out.println("CGPA: " + cgpa);
        viewGrades();
        mainMenu();
    }

    public void submitComplaint() {
        System.out.println("Enter description for complaint: ");
        Main.scn.nextLine();
        String desc = Main.scn.nextLine();

        Complaint complaint = new Complaint(desc, this);
        System.out.println("Complaint has been filed. Your complaint id is " + complaint.id);
        mainMenu();
    }

    public void viewComplaintStatus() {
        System.out.print("Enter complaint id: ");
        int id = Main.scn.nextInt();
        Complaint comp = Main.database.complaints.get(id);
        if (comp != null) {
            System.out.println("Your complaint status is " + comp.getStatus());
            System.out.println("DESCRIPTION: "+comp.desc);
            System.out.println();
        } else {
            System.out.println("Complaint not found.");
        }
        mainMenu();
    }

    public String getRollNumber() {
        return this.roll;
    }

    public void hasPassedSem(){
        if(completedCourses.size()>=5){
            System.out.println("Student has passed the semester.");
            semester=semester+1;
            completedCourses.clear();
            courseList.clear();
            prevCourses = (ArrayList<Course>) courseList.clone();
        }
    }

}
