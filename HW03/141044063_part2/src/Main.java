import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {

        String filename = args[0];
        Expression exp = new Expression();
        exp.LetsCalculateThis(args[0]);

    }
}