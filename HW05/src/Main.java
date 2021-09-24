public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ReadFile object = new ReadFile(args[0]);
        object.addHundred(args[0]);
    }
}
