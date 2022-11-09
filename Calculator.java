import java.util.*;
public class Calculator {
    public static int solve(String input) {
        char[] vlue = input.toCharArray();
        Stack<Integer> v = new Stack<Integer>();
        Stack<Character> oper = new Stack<Character>();
        for (int i = 0; i < vlue.length; i++) {
            if (vlue[i] == ' ') continue;
            if (vlue[i] >= '0' && vlue[i] <= '9' && (vlue.length-1 == i || vlue[i + 1] == ' ')) {
                StringBuffer temp = new StringBuffer();
                while (i < vlue.length && vlue[i] >= '0' && vlue[i] <= '9') temp.append(vlue[i++]);
                v.push(Integer.parseInt(temp.toString()));
            }
            else if (vlue[i] == '(')
                oper.push(vlue[i]);

            else if (vlue[i] == ')') {
                while (oper.peek() != '(')
                    v.push(calculate(oper.pop(), v.pop(), v.pop()));
                oper.pop();
            }

            else if (vlue[i] == '%' || vlue[i] == '+' || vlue[i] == '-' || vlue[i] == '*' || vlue[i] == '/') {
                while (!oper.empty() && checkPrecedence(vlue[i], oper.peek()))
                    v.push(calculate(oper.pop(), v.pop(), v.pop()));
                oper.push(vlue[i]);
            }
            else  {
                throw new UnsupportedOperationException("Con not require");
            }
        }
        while (!oper.empty()) v.push(calculate(oper.pop(), v.pop(), v.pop()));
        return v.pop();
    }
    public static boolean checkPrecedence(char oper1, char oper2) {
        if (oper2 == '(' || oper2 == ')') return false;
        if (oper2 == '*' || oper2 == '/' || oper2 == '+' || oper2 == '-' || oper2 == '%') return false;
        if ((oper1 == '*' || oper1 == '/') && (oper2 == '+' || oper2 == '-')) return false;
        else return true;
    }

    public static int calculate(char op, int b, int a) {
        switch (op) {
            case '%': return a % b;
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero"); return a / b;
            case '^': return (int)Math.pow(b,a);
        }
        return 0;
    }
    public static void main(String[] args) {
        while (true){
            String in;
            Scanner sc = new Scanner(System.in);
            System.out.println("Input");
            in = sc.nextLine();
            System.out.println("output"+ "\n" + Calculator.solve(in));

        }
    }
}