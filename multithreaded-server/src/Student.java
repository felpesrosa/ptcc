public class Student {
    static long requestNumber;
    static String name;
    static long studentId;
    static String[] studentScores;

    public Student(long requestNumber, String name, long studentId, String[] scores) {
        this.requestNumber = requestNumber;
        this.name = name;
        this.studentId = studentId;
        this.studentScores = scores;
    }

    public static boolean isStudentApproved() {
        float soma = 0;
        for (String f : studentScores) {
            soma += Float.parseFloat(f);
        }

        return soma / studentScores.length >= 7.0;
    }

    private String scoresToString() {
        String s = "{ ";
        for (String f : studentScores) {
            s += f + " ";
        }
        s += " }";

        return s;
    }

    @Override
    public String toString() {
        return requestNumber + ", " + studentId + ", " + name + ", " + isStudentApproved() + ", " + scoresToString();
    }

}
