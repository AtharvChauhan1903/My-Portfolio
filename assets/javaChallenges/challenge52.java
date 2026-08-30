public class challenge52 {
    static void main() {
        course.setMaxCapacity(10);
        course java=new course();
        course python=new course();
        python.enrollStudent("Atharv");
        java.enrollStudent("ChatGpt");
        python.enrollStudent("Me");
        java.enrollStudent("I");
        python.unerollStudent("Atharv");
        java.unerollStudent("I");

    }
}
