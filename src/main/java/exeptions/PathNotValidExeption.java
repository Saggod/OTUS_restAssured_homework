package exeptions;

public class PathNotValidExeption extends RuntimeException {

    public PathNotValidExeption() {
        super("Path not set to class");
    }
}
