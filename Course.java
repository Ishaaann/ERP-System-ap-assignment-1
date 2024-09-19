import java.util.*;

public class Course {
    public String prof;
    public String title;
    public String courseCode;
    public int semester;
    public int credits;
    public ArrayList<Course> prereq = new ArrayList<>();
    public String timings;
    public String syllabus;
    public int enrollmentLimit;
    public String dept;
    public Double gpa;
    public ArrayList<Student> enrolledStudent = new ArrayList<>();
    public ArrayList<String> courseData;

    public Course(String _prof, String _courseCode, int _credits, int _sem) {
        this.prof = _prof;
        this.courseCode = _courseCode;
        this.credits = _credits;
        this.semester = _sem;
        this.courseData = new ArrayList<>(Arrays.asList(_prof, _courseCode, Integer.toString(_credits), Integer.toString(_sem), dept));
        Main.database.courseCatalog.add(courseData);
        Main.database.courses.put(_courseCode,this);
    }

    public String getCourseCode() {
        return this.courseCode;
    }
}
