import java.util.*;

public class operatorgrammar {

    public static int getOperatorPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/' || operator == '%') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else if (Character.isLetter(operator)) {
            return 4;
        } else if (operator == '$') {
            return 0;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Set<Character> non_terminals = new LinkedHashSet<>();
        boolean flag = true;
        int no_of_rules;
        String rule;
        Scanner sc = new Scanner(System.in);
        no_of_rules = sc.nextInt();
        for (int i = 0; i < no_of_rules; i++) {
            rule = sc.next();
            for (int j = 2; j < rule.length() - 1; j++) {
                if (Character.isUpperCase(rule.charAt(j))) {
                    if (Character.isUpperCase(rule.charAt(j + 1))) {
                        flag = false;
                        break;
                    }
                } else {
                    non_terminals.add(rule.charAt(j));
                }
            }
            if (!Character.isUpperCase(rule.charAt(rule.length() - 1))) {
                non_terminals.add(rule.charAt(rule.length() - 1));
            }

        }
        non_terminals.add('$');
        if (flag) {
            System.out.println("Operator Grammer");
            System.out.println(non_terminals.toString());
        } else {
            System.out.println("Not operator grammer");
        }
        char[][] operatortable = new char[non_terminals.size()][non_terminals.size()];
        int i = 0, j = 0;
        for (char term : non_terminals) {
            j=0;
            for (char term2 : non_terminals) {
                if (getOperatorPrecedence(term) > getOperatorPrecedence(term2)) {
                    operatortable[i][j] = '>';
                } else if (getOperatorPrecedence(term) < getOperatorPrecedence(term2)) {
                    operatortable[i][j] = '<';
                } else if (getOperatorPrecedence(term) == getOperatorPrecedence(term2)) {
                    if(getOperatorPrecedence(term)>=1 &&  getOperatorPrecedence(term)<=2){
                        operatortable[i][j] = '>';
                    }
                    else {
                    operatortable[i][j] = '-';
                }
                } else {
                    operatortable[i][j] = '-';
                }
                j++;
            }
            i++;
        }
        for (char nt: non_terminals){
            System.out.print( nt + " ");
        }
        System.out.println();
        for(i=0;i<non_terminals.size();i++){
            for(j=0;j<non_terminals.size();j++){
                System.out.print(operatortable[i][j]+" ");
            }
            System.out.println();
        }
        sc.nextInt();
    }
}
