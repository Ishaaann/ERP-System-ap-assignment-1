import java.util.ArrayList;

public class Adminstrator extends User{
    private static final String email = "admin@iiitd.in";
    private static final String pass = "admin123";

    public Adminstrator(){
        super(email,pass);
    }

    @Override
    public void mainMenu(){
        System.out.println("Administrator Menu:");
        System.out.println("1. Manage Course Catalog");
        System.out.println("2. Manage Student Records");
        System.out.println("3. Assign Professors to Courses");
        System.out.println("4. View Complaints");
        System.out.println("5. Handle Complaints");
        System.out.println("6. Logout");
    }


    public void getCatalog(){
//        for(int i = 0;i< Main.database.courseCatalog.size();i++){
//            for(int j=0; j<Main.database.courseCatalog.get(i).size(); j++){
//                System.out.print(Main.database.courseCatalog.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
        for(String code: Main.database.courses.keySet()){
            Course course = Main.database.courses.get(code);
            System.out.println("Professor: "+Main.database.courses.get(code).prof);
            System.out.println("Course Code: "+ code);
            System.out.println("Credits: "+Main.database.courses.get(code).credits);
            System.out.println("Semester: "+course.semester);
//            System.out.println("CGPA: "+Main.database.students.get(code).gpa);
            for(Course pre: course.prereq){
                System.out.print(pre.courseCode+" ");
            }
            System.out.println();
        }
    }

    public void catalogMenu(){
        System.out.println("1. Add course");
        System.out.println("2. Remove course");
        System.out.println("3. View Courses");
        System.out.println("4. Exit");

        int choice = Main.scn.nextInt();
        switch (choice){
            case 1:
//                String code = Main.scn.nextLine();
//                Course course = Main.database.courses.get(code);
                addCourse();
                break;

            case 2:
                System.out.println("Enter course code of course to be removed: ");
                String c = Main.scn.next();
                Course cour = Main.database.courses.get(c);
                if(cour!=null) {
                    deleteCourse(cour);
                }
                else{
                    System.out.println("No such course.");
                }
                break;

            case 3:
                getCatalog();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid Choice");
                catalogMenu();
                break;
        }
    }

    public void addCourse(){
        System.out.print("Enter Course code: ");
        String code = Main.scn.next();
        System.out.print("Enter Professor name: ");
        String name = Main.scn.next();
        System.out.print("Enter credits: ");
        int credits = Main.scn.nextInt();
        System.out.print("Enter Semester: ");
        int sem = Main.scn.nextInt();
        Course course = new Course(name,code,credits,sem);
//        Main.database.courseCatalog.add(course.courseData);
//        Main.database.courses.put(code,course);
    }

    public void deleteCourse(Course course){
        boolean removed = false;
        String code = course.courseCode;
        if(Main.database.courses.containsKey(code)) {
            Main.database.courses.remove(code);
            removed = true;
        }
//        for(int i=0;i<Main.database.courseCatalog.size();i++){
//            if(course.courseCode.equals(Main.database.courseCatalog.get(i).get(1))){
//                Main.database.courseCatalog.remove(i);
//                removed = true;
//                break;
//            }
//        }

        if(removed){
            System.out.println("Course Removed.");
        }
        else{
            System.out.println("No such course in catalog.");
        }
    }

    public void manageStudentRecords(){
        System.out.println("1. Update data");
//        System.out.println("2. Remove course");
        System.out.println("2. View Students");
        System.out.println("3. Exit");

        int choice = Main.scn.nextInt();
//        if(choice==1){
//            int roll = Main.scn.nextInt();
//            Student stud = Main.database.students.get(roll);
//            updateStudentData(stud);
//            return;
//        }
//        else if(choice==2){
//            getStudentList(Main.database);
//            return;
//        }
//        else{
//            System.out.println("Invalid choice");
//        }
        switch (choice) {
            case 1:
//                System.out.println("Enter Roll: ");
//                String roll = Main.scn.next();
//                Student s = Main.database.students.get(roll);
                System.out.println("Updating student data...");
                    updateStudentData();
                break;
            case 2:
                getStudentList(Main.database);
                break;
            case 3:
                System.out.println("Exiting.");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }

    }

//    public void updateStudentData(){
//        System.out.print("Enter Roll number: ");
//        String roll = Main.scn.next();
//        Student student = Main.database.students.get(roll);
//        System.out.print("Enter New Roll number: ");
//        student.roll = Main.scn.next();
//        System.out.print("Enter new name: ");
//        student.name = Main.scn.next();
//        System.out.print("Do you want to assign GPA? y/n");
//        String ans = Main.scn.next();
//        if(ans.equals("y")){
//            for(Course c: student.courseList){
//                System.out.print("Enter new GPA for "+c.courseCode+": ");
//                Double gpa = Main.scn.nextDouble();
//                if(gpa<0.0 || gpa>10.0){
//                    System.out.println("Invalid GPA. Enter a value between 0.0 and 10.0");
//                }
//                else{
//                    c.gpa = gpa;
//                }
//            }
//        }
//    }

    public void updateStudentData() {
        System.out.print("Enter student Email: ");
        String email = Main.scn.next();
        Student student = Main.database.students.get(email);

        if (student == null) {
            System.out.println("No student found.");
            return;
        }

        System.out.print("Enter New Roll number: ");
        student.roll = Main.scn.next();

        System.out.print("Enter new name: ");
        student.name = Main.scn.next();

        System.out.print("Do you want to assign GPA? y/n ");
        String ans = Main.scn.next();
        if (ans.equals("y")) {
            for (Course c : student.courseList) {
                System.out.print("Enter new GPA for " + c.courseCode + ": ");
                double gpa = Main.scn.nextDouble();
                if (gpa < 0.0 || gpa > 10.0) {
                    System.out.println("Invalid GPA. Enter a value between 0.0 and 10.0");
                } else {
                    c.gpa = gpa;
                    student.completedCourses.put(c,gpa);
//                    student.hasPassedSem();
                    
                }
            }
        }
    }

    private void updateGPA(Student student, double newGPA){
        student.gpa = newGPA;
        student.studentData.set(4,Double.toString(newGPA));
    }

    public void getStudentList(Database database){
//        for(int i = 0;i< Main.database.studentList.size();i++){
//            for(int j=0; j<Main.database.studentList.get(i).size(); j++){
//                System.out.print(Main.database.studentList.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
        for(String mail: Main.database.students.keySet()){
            System.out.println("Roll Number: "+Main.database.students.get(mail).roll);
            System.out.println("Name: "+Main.database.students.get(mail).name);
            System.out.println("Email: "+Main.database.students.get(mail).mail);
            System.out.println("Password: "+Main.database.students.get(mail).password);
            System.out.println("CGPA: "+Main.database.students.get(mail).gpa);
            System.out.println();
        }
    }

    public static void getProfList(){
        for(int i = 0;i< Main.database.profList.size();i++){
            for(int j=0; j<Main.database.profList.get(i).size(); j++){
                System.out.print(Main.database.profList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    public static void assignProf(Course course){
        getProfList();
//        System.out.println("Enter Professor name: ");
//        String name = Main.scn.nextLine();
        System.out.println("Enter Professor Mail ID: ");
        String mail = Main.scn.nextLine();

        Professor assignedProf = Main.database.professors.get(mail);
//        course.prof = assignedProf.
        boolean found = false;
        for(ArrayList<String> data: Main.database.profList){
            if(data.get(1).equals(mail)){
//                if(data.get(3).equals(course.dept)){
                    course.prof = data.get(0);
                    assignedProf.courseList.add(course);
                    System.out.println("Course assigned to "+data.get(0)+".");
                    found = true;
                    break;
//                }
            }
        }
        if(!found) {
            System.out.println("No such professor.");
        }
    }

    public void getComplaintStatus(Complaint complaint) {
        System.out.println("Complaint ID: " + complaint.id);
        System.out.println("Description: " + complaint.desc);
        System.out.println("Status: " + complaint.getStatus());
    }

    public static void updateComplaintStatus(Complaint complaint, String newStatus) {
        complaint.setStatus(newStatus);
        System.out.println("Complaint status updated to: " + newStatus);
    }

    public void getComplaints(){
        if(Main.database.complaints.isEmpty()){
            System.out.println("No complaints yet.");
        }
        else{
            for(Integer id: Main.database.complaints.keySet()){
                System.out.println("Complaint raised by "+Main.database.complaints.get(id).studentID);
                System.out.println("ID: "+id);
                System.out.println("DESCRIPTION: "+Main.database.complaints.get(id).desc);
                System.out.println("STATUS: "+Main.database.complaints.get(id).getStatus());
            }
        }
    }

    public static void adminLogin() {
        System.out.println("Enter admin email: ");
        String mail = Main.scn.next();
        System.out.println("Enter admin password: ");
        String p = Main.scn.next();

        if(email.equals(mail) && pass.equals(p)) {
                Adminstrator admin = new Adminstrator();
//                admin.mainMenu();

                adminMenu(admin);
        }else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void adminMenu(Adminstrator admin) {
        boolean loggedIn = true;
        while (loggedIn) {
            admin.mainMenu();

            int choice = Main.scn.nextInt();
            Main.scn.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    admin.catalogMenu(); // Add course catalog management functionality
                    break;
                case 2:
                    admin.manageStudentRecords(); // Add student records management functionality
                    break;
                case 3:
                    // Assign professors to courses
                    System.out.println("Enter Course code");
                    String code = Main.scn.nextLine();
                    Course course = Main.database.courses.get(code);
                    if(course!=null) {
                        assignProf(course);
                    }
                    else{
                        System.out.println("No such course.");
                    }
                    break;
                case 4:
                    admin.getComplaints();
                    break;
                case 5:
                    System.out.println("Enter Complaint ID: ");
                    int id = Main.scn.nextInt();
                    Complaint complaint = Main.database.complaints.get(id);
                    if(complaint!=null) {
                        System.out.println("Current status is "+complaint.getStatus());
                        System.out.println("Enter new status: ");
                        String newStatus = Main.scn.next();
                        updateComplaintStatus(complaint, newStatus);
                    }
                    else{
                        System.out.println("Incorrect ID or no complaint with this ID registered.");
                        System.out.println();
                    }
                    break;
                case 6:
                    loggedIn = false;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
