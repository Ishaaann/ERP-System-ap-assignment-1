import java.util.*;

public class Database implements DataStorage{
//    private HashMap<String, Professor> profList;
    public ArrayList<ArrayList<String>> courseCatalog;
    public ArrayList<ArrayList<String>> studentList;
    public ArrayList<ArrayList<String>> profList;
    public ArrayList<ArrayList<String>> complaintList;
    public HashMap<String, Student> students = new HashMap<>();
    public HashMap<String, Professor> professors = new HashMap<>();
    public HashMap<String, Course> courses = new HashMap<>();
    public HashMap<Integer, Complaint> complaints = new HashMap<>();

    public Database(){
        this.studentList = new ArrayList<ArrayList<String>>();
        this.profList = new ArrayList<ArrayList<String>>();
        this.courseCatalog = new ArrayList<ArrayList<String>>();
        this.complaintList = new ArrayList<ArrayList<String>>();
        this.courses = new HashMap<>();
    }

    @Override
    public void addStudent(Student student) {
        students.put(student.getRollNumber(), student);
    }

    @Override
    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    @Override
    public void addProfessor(Professor professor) {
        professors.put(professor.getContact(), professor);
    }

    @Override
    public void addComplaint(Complaint complaint) {
        complaints.put(complaint.getId(), complaint);
    }
}
