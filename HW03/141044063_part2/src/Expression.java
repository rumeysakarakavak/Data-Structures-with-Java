import java.io.*;
import java.util.Stack;

public class Expression {


    //region InstanceVariables
    private int maxSize;
    private char[]allElements;
    private int[][]allVariables;
    private int[][] arrayOfIntegerss;
    private String[] arrayAllElementsOrganized;
    private RumusStack operatorStack;
    private RumusStack operandStack;
    //endregion InstanceVariables

    //region Getters - Setters
    public int getMaxSize() {
        return maxSize;
    }

    public char[] getAllElements() {
        return allElements;
    }

    public int[][] getAllVariables() {
        return allVariables;
    }

    public int[][] getArrayOfIntegerss() {
        return arrayOfIntegerss;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public RumusStack getOperandStack() {
        return operandStack;
    }

    public RumusStack getOperatorStack() {
        return operatorStack;
    }

    public String[] getArrayAllElementsOrganized() {
        return arrayAllElementsOrganized;
    }

    public void setAllElements(char[] allElements) {
        this.allElements = allElements;
    }

    public void setAllVariables(int[][] allVariables) {
        this.allVariables = allVariables;
    }

    public void setArrayOfIntegerss(int[][] arrayOfIntegerss) {
        this.arrayOfIntegerss = arrayOfIntegerss;
    }

    public void setOperandStack(RumusStack operandStack) {
        this.operandStack = operandStack;
    }

    public void setOperatorStack(RumusStack operatorStack) {
        this.operatorStack = operatorStack;
    }

    public void setArrayAllElementsOrganized(String[] arrayAllElementsOrganized) {
        this.arrayAllElementsOrganized = arrayAllElementsOrganized;
    }
    //endregion Getters - Setters

    //region Constructor
    public Expression(){
        allElements = new char[1000];
        allVariables = new int[10][2];
        arrayAllElementsOrganized = new String[1000];
    }
    //endregion Constructor

    //region Methods
    public void ReadFromFile(String fileName) throws IOException {

        File file = new File(fileName);

        InputStream in = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(in);
        // buffer for efficiency
        Reader buffer = new BufferedReader(reader) ;
        char[] allElementsTemp = new char[100];
        int[][]allVariablesTemp = new int[100][100];
        String[] arrayAllElementsOrganizedTemp = new String[100];
        int r;
        int i  = 0;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;
            allElementsTemp[i] = ch;
            //System.out.println(ch);
            ++i;

        }
        allElementsTemp[i] = '#';
        setAllElements(allElementsTemp);
        i  = 0;
        int j = 0;
        int value = 0;
        int multiply = 1;
        int mult = 1;
        while (allElementsTemp[i] != '#'){
            //System.out.println(allElements[i]);
            if(allElementsTemp[i] == '=')
            {
                allVariablesTemp[j][0] = allElementsTemp[i - 1];
                int k = i+1;
                mult = 1;
                while (allElementsTemp[k + 1] != '\n' && allElementsTemp[k + 1] != '\r'){
                    ++k;
                    mult = mult * 10;
                }
                multiply = mult;
                while (allElementsTemp[i + 1] != '\n' && allElementsTemp[i + 1] != '\r'){

                    ++i;

                        value += (allElementsTemp[i] - 48) * multiply;

                    multiply = multiply / 10;

                }

                allVariablesTemp[j][1] = value;
                value = 0;
                multiply = 1;
                ++j;
            }
            ++i;
        }
        allVariablesTemp[j][0] = '#';
        setAllVariables(allVariablesTemp);
        i = 0;
        while (allElementsTemp[i] != '('){
            ++i;
        }

        j = 0;
        int k = 1;
        while (allElementsTemp[i] != '#'){

            if (allElementsTemp[i] == 'c' && allElementsTemp[i+1] == 'o'
                    && allElementsTemp[i+2] == 's'){

                arrayAllElementsOrganizedTemp[j] = (String.valueOf(allElementsTemp[i])
                                                + String.valueOf(allElementsTemp[i+1])
                                                + String.valueOf(allElementsTemp[i+2]));
                i = i+3;
                ++j;
            }
            else if (allElementsTemp[i] == 's' && allElementsTemp[i+1] == 'i'
                    && allElementsTemp[i+2] == 'n'){

                arrayAllElementsOrganizedTemp[j] = (String.valueOf(allElementsTemp[i])
                        + String.valueOf(allElementsTemp[i+1])
                        + String.valueOf(allElementsTemp[i+2]));
                i = i+3;
                ++j;
            }
            else if (allElementsTemp[i] == 'a' && allElementsTemp[i+1] == 'b'
                    && allElementsTemp[i+2] == 's'){

                arrayAllElementsOrganizedTemp[j] = (String.valueOf(allElementsTemp[i])
                        + String.valueOf(allElementsTemp[i+1])
                        + String.valueOf(allElementsTemp[i+2])
                        );
                i = i+3;
                ++j;
            }
            else if((Character.getNumericValue(allElementsTemp[i]) >= 0 &&
                    Character.getNumericValue(allElementsTemp[i])<= 9) || allElementsTemp[i] == '-')
            {
                arrayAllElementsOrganizedTemp[j] = String.valueOf(allElementsTemp[i]);
                ++i;
                while (allElementsTemp[i] != ' '){
                    arrayAllElementsOrganizedTemp[j] += String.valueOf(allElementsTemp[i]);
                    ++i;
                }
                ++j;
            }
            else{
                arrayAllElementsOrganizedTemp[j] = String.valueOf(allElementsTemp[i]);
                ++j;
                ++i;
            }
        }
        arrayAllElementsOrganizedTemp[j] = String.valueOf('#');
        setArrayAllElementsOrganized(arrayAllElementsOrganizedTemp);
        i = 0 ;
        /*while (!arrayAllElementsOrganized[i].equals("#")){
            System.out.println(arrayAllElementsOrganized[i]);
            ++i;
        }*/
        in.close();
        reader.close();
        buffer.close();


    }

    public String[] FillStack(String filename) throws IOException {
        Expression elem = new Expression();
        elem.ReadFromFile(filename);
        int i = 0;
        while (elem.getAllVariables()[i][0] != '#'){
            /*System.out.print((char)elem.getAllVariables()[i][0] + "=");
            System.out.print(elem.getAllVariables()[i][1]);
            System.out.println();*/
            ++i;
        }
        //int i  = 0;
       /* while(elem.allElements[i] != '#'){
            if(elem.allElements[i] == '('){
                System.out.println();
            }
        }*/
       /* while (!elem.getArrayAllElementsOrganized()[i].equals("#")){
            System.out.println(elem.getArrayAllElementsOrganized()[i]);
            ++i;
        }*/
        return elem.getArrayAllElementsOrganized();
    }
    static int priority(String operator)
    {
        switch (operator)
        {
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;

            case "^":
                return 3;
        }
        return -1;
    }

    public  String infixToPostfix(String filename) throws IOException {
        Expression rum = new Expression();
        String[] organisedElements = new String[1000];
        organisedElements = rum.FillStack(filename);

        // initializing empty String for result
        String result = new String("");
        // initializing empty stack
        RumusStack infixStack = new RumusStack();
        int i  = 0;
        while (!organisedElements[i].equals("#")){
            String element = organisedElements[i];

            // If the scanned character is an operand, add it to output.
            if (!element.equals("(") && !element.equals(")") && !element.equals("+")
                    && !element.equals("-") && !element.equals("*") && !element.equals("/")
                    && !element.equals("cos(")) {
                result += element;
                result += " ";
            }
                // If the scanned character is an '(', push it to the stack.
            else if (element.equals("("))
                infixStack.push(element);

                //  If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (element.equals(")"))
            {

                while (!infixStack.isEmpty() && !infixStack.peek().equals("("))
                    result += infixStack.pop();
                    result += " ";
                if (!infixStack.isEmpty() && !infixStack.peek().equals("("))
                    return "Invalid Expression"; // invalid expression
                else if(!infixStack.isEmpty())
                    infixStack.pop();

            }
            else // an operator is encountered
            {
                while (!infixStack.isEmpty() && priority(element) <= priority(infixStack.peek()))
                    result += infixStack.pop();
                    result += " ";
                infixStack.push(element);
            }
            ++i;
        }

        // pop all the operators from the stack
        while (!infixStack.isEmpty())
            result += infixStack.pop();
            result += " ";

        System.out.println("Postfix notation  = " + result.replaceAll("    "," "));
        return result;
    }

    public String[] InitializeTheVariables(String filename) throws IOException {
        Expression object  = new Expression();

        String exp = object.infixToPostfix(filename);
        int i = 0;
        String[] splited = exp.split("\\s+");
        Expression objectVariable  = new Expression();
        objectVariable.ReadFromFile(filename);

        for (i = 0; i<splited.length ; ++i){
            for (int j = 0; j< object.getAllVariables().length; ++j){
                if(splited[i].equals(String.valueOf((char)objectVariable.getAllVariables()[j][0]))) {
                    Integer myInt = (objectVariable.getAllVariables()[j][1]);
                    splited[i] = myInt.toString();
                }
            }

        }
        return splited;

    }

    //region LetsCalculateThis
    public void LetsCalculateThis(String filename) throws IOException {
        float result = 0;
        Expression exp = new Expression();

        String[] expression = exp.InitializeTheVariables(filename);
        IntegerStack calc = new IntegerStack();
        int j = 1;
        while(!expression[j].isEmpty() ){
            float x = 0;
            float y = 0;
            float r = 0;
                if(!expression[j].equals("+") && !expression[j].equals("-") && !expression[j].equals("*")
                        && !expression[j].equals("/") && !expression[j].equals("cos") && !expression[j].equals("sin")
                        && !expression[j].equals("abs") && !expression[j].equals(" ")){

                    calc.push(Float.valueOf(expression[j]));
                    ++j;
                }
                else if(expression[j].equals("+")){
                    x = calc.pop();
                    y = calc.pop();
                    r = x+y;
                    calc.push(r);
                    if(j + 1  == expression.length){
                        System.out.println( "Result is = " + calc.pop());
                    }
                    else
                        ++j;
                }
                else if(expression[j].equals("-")){
                    x = calc.pop();
                    y = calc.pop();
                    r = y-x;
                    calc.push(r);
                    if(j + 1  == expression.length){
                        System.out.println( "Result is = " + calc.pop());
                    }
                    else
                        ++j;
                }
                else if(expression[j].equals("*")){
                    x = calc.pop();
                    y = calc.pop();
                    r = x*y;
                    calc.push(r);
                    if(j + 1  == expression.length){
                        System.out.println( "Result is = " + calc.pop());
                    }
                    else
                        ++j;
                }
                else if(expression[j].equals("/")){
                    x = calc.pop();
                    y = calc.pop();
                    r = y/x;
                    calc.push(r);
                    if(j + 1  == expression.length){
                        System.out.println( "Result is = " + calc.pop());
                    }
                    else
                        ++j;
                }
                else if(expression[j].equals("cos")) {
                    MathFunctions function = new MathFunctions();
                    if(expression[j + 3].equals("+")){
                        calc.push(function.cos(Integer.valueOf(expression[j + 1]) + Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("-")){
                        calc.push(function.cos(Integer.valueOf(expression[j + 1]) - Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("*")){
                        calc.push(function.cos(Integer.valueOf(expression[j + 1]) * Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("/")){
                        calc.push(function.cos(Integer.valueOf(expression[j + 1]) / Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else{
                        calc.push(function.cos(Integer.valueOf(expression[j + 1])));
                        ++j;
                    }

                }
                else if(expression[j].equals("sin")) {
                    MathFunctions function = new MathFunctions();
                    if(expression[j + 3].equals("+")){
                        calc.push(function.sin(Integer.valueOf(expression[j + 1]) + Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("-")){
                        calc.push(function.sin(Integer.valueOf(expression[j + 1]) - Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("*")){
                        calc.push(function.sin(Integer.valueOf(expression[j + 1]) * Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else if(expression[j + 3].equals("/")){
                        calc.push(function.sin(Integer.valueOf(expression[j + 1]) / Integer.valueOf(expression[j + 2])));
                        j += 4;
                    }
                    else{
                        calc.push(function.sin(Integer.valueOf(expression[j + 1])));
                        ++j;
                    }
                }
                else if(expression[j].equals("abs")) {
                    MathFunctions function = new MathFunctions();
                    calc.push(function.abs(Float.valueOf(expression[j + 1])));
                    j+=2;
                }

            }

    }


    //endregion Methods
}