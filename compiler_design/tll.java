import java.util.*;

public class tll {
    static Stack<String> operators = new Stack<>();
    static Stack<String> operands = new Stack<>();
    static int t = 0;

    static int generateT() {
        return t++;
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");

    }

    static int getPref(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 2;
        } else if (s.equals("+") || s.equals("-")) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        String inp;
        Scanner sc = new Scanner(System.in);
        inp = sc.next();
        String[] expression = inp.split("");
        for (String token : expression) {
            if (isOperator(token)) { // is opr
                if (operators.empty()) {
                    operators.push(token);
                } else if (getPref(operators.peek()) < getPref(token)) {
                    operators.push(token);
                } else {
                    while (!operators.isEmpty() && (getPref(operators.peek()) > getPref(token))) {
                        operands.push(operators.pop());
                    }
                    operators.push(token);
                }
            } else { // is operand
                operands.push(token);
            }
        }
        while (!operators.isEmpty()) {
            operands.push(operators.pop());
        }
        String[] arr = new String[operands.size()];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = operands.pop();
        }
        for(String s:arr){
            System.out.print(s + " ");
        }
        System.out.println();
        operators.clear();
        for (String s : arr) {
            if (isOperator(s)) {
                String s1 = operators.pop();
                String s2 = operators.pop();
                System.out.printf("t%d = %s %s %s\n", generateT(), s2, s, s1);
                operators.push(String.valueOf(String.format("t%d", t - 1)));
            } else {
                operators.push(s);
            }
        }
        sc.close();
    }
}
