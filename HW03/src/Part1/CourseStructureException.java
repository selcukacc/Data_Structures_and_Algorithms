package Part1;
/**
 * @author Islam Goktan Selcuk
 * Number: 141044071
 * */
public class CourseStructureException extends Exception {
    public String message;
    public CourseStructureException() {}
    public CourseStructureException(String theMessage) {
        message = theMessage;
    }
    public String getMessage() { return message; }
}
