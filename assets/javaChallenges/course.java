public class course {
    String [] enrolledStudent;
    String courseName;
    static int maxCapacity;
    int nofstudents;

    void enrollStudent(String enrolledStudent ) {
        if (nofstudents < course.maxCapacity) {
            System.out.println("The student is enrolled successfully");
            nofstudents++;
        }else {
            System.out.println("Sorry the classes are full");
        }
    }
    void unerollStudent(String enrolledStudent){
        if (nofstudents<=course.maxCapacity&&nofstudents>0) {
            System.out.println("The student has been unenrolled");
            nofstudents--;
        }else {
            System.out.println("There is no enrolled student yet");
        }
    }
    static int setMaxCapacity(int maxCapacity){
        course.maxCapacity =maxCapacity;
        return course.maxCapacity;
    }
}
