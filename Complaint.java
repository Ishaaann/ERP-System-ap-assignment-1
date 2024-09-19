    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Random;

    public class Complaint {
        private String status;
        public int id;
        public String desc;
        public String studentID;
        public HashMap<Integer, Complaint> complaintData;

        private static Random rand = new Random();

        public Complaint(String _desc, Student s){
            this.desc = _desc;
            this.status = "Pending";
            this.id = rand.nextInt(100);
            this.studentID = s.mail;
            this.complaintData = new HashMap<>();
            complaintData.put(this.id, this);
            Main.database.complaints.put(id,this);
        }

        public String getStatus(){
            return status;
        }

        public void setStatus(String status){
            this.status = status;
//            this.complaintData.put(2,status);
        }

        public Integer getId() {
            return this.id;
        }
    }

