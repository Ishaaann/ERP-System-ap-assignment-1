This is a simple ERP (Enterprise Resource Planning) system implemented in Java which makes use of OOPs core concepts, designed for managing students, professors, courses, and complaints at a university. The system allows users to log in based on their roles (Student, Professor, or Administrator) and perform various operations such as course registration, grade tracking, complaint submission, and academic progress review.

##Core OOPs concepts are used such as:
#Encapsulation
To protect data, I employ private fields like roll, courseList, and status. To ensure data control, these fields are only accessible through public methods such as setStatus() and getStatus().

#Inheritance
Student, Professor and Admin inherit from the User class, which provides shared attributes and behavior, like mail and password. This reuse permits each subclass, such as mainMenu(), to have specific functionality while avoiding redundancy.

#Polymorphism
I modify the Professor's and Student's mainMenu() methods. This allows the same method to behave differently depending on the object that invokes it.

#Abstraction
The User class hides internal complexity such as login procedures by abstracting common features. Users' interactions are made simpler by the Complaint class, which isolates the complaints processing procedure.

#Composition
Course objects are contained in a student through completedCourses and courseList. This partnership guarantees effective administration by integrating academic tracking and course registration with the student.

#Association and Interface
I've presented a new interface called Person, which the professor and students use. This ensures flexibility for future user types while enabling shared behavior such as viewAvailableCourses().

#Dependency
The existence of Course and Complaint objects is required for methods like registerCourses() and submitComplaint(), which show how various classes work together to complete tasks.

The program is pretabulated with some data:

                                     Professor      Code    Credits  Semester
        Course course1 = new Course("Avi",         "CSE101",   4,       1);
        Course course2 = new Course("Arpit Singh", "MTH201",   4,       1);
        Course course3 = new Course("Tarun Ahuja", "PHY101",   4,       1);
        Course course4 = new Course("Ishaan",      "SSH221",   4,       1);
        Course course5 = new Course("Dhruv",       "CSE201",   4,       1);
        
                                        Roll    Name      Email ID        password
        Student student1 = new Student("1001", "Shivam", "shivam@iiitd", "shivam");
        Student student2 = new Student("1002", "Aditya", "aditya@iiitd", "aditya");
        Student student3 = new Student("1003", "Shreya", "shreya@iiitd", "shreya");

                                        Name           Email          Paaword  Department
        Professor prof1 = new Professor("Avi",         "avi@iiitd",   "avi",   "CSE");
        Professor prof2 = new Professor("Tarun Ahuja", "tarun@iiitd", "tarun", "ECE");
        Professor prof3 = new Professor("Dhruv",       "dhruv@iiitd", "dhruv", "BIO");

To login as the Adminstrator, email and id are predefined on the assumption that exists only one admin.
    admin email id : admin@iiitd.in
    admin password : admin123

#Assumption
1. There exists only one admin.
2. Admin can only assign courses to professor if they exist in the professor list.
3. Professor can view enrolled student only for the courses they teach.

    
