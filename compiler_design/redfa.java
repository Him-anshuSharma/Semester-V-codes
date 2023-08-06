import java.util.*;

class TreeNode {

    char data;
    TreeNode left;
    TreeNode right;
    Set<Integer> first;
    Set<Integer> follow;

    TreeNode(char data) {
        first = new LinkedHashSet<>();
        follow = new LinkedHashSet<>();
        this.data = data;
        left = null;
        right = null;
    }
}

public class redfa {

    static TreeNode head = null;
    static int node_val = 1;

    public static int getOperatorPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/' || operator == '%') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else if (Character.isLetter(operator)) {
            return 4;
        } else if (operator == '$' || operator == '.') {
            return 0;
        } else {
            return -1;
        }
    }



    public static void main(String[] args) {
        String input = "a*.b+";
        Stack<TreeNode> charStack = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        Stack<Character> temp2 = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                TreeNode node = new TreeNode(c);
                node.first.add(node_val);
                node.follow.add(node_val++);
                charStack.add(node);
            } else if (getOperatorPrecedence(c) > -1) {
                operatorStack.push(c);
            }
        }
        while (!charStack.empty()) {
            temp.push(charStack.pop());
        }
        charStack = temp;
        while (!operatorStack.empty()) {
            temp2.push(operatorStack.pop());
        }
        operatorStack = temp2;
        while (!operatorStack.isEmpty()) {
            char opr = operatorStack.pop();
            if (opr != '*' && opr != '+' ) {
                TreeNode temp3 = new TreeNode(opr);
                temp3.left = charStack.pop();
                temp3.right = charStack.pop();
                charStack.push(temp3);
                head = temp3;
            } else {
                TreeNode temp3 = new TreeNode(opr);
                temp3.left = charStack.pop();
                charStack.push(temp3);
                head = temp3;
            }
        }
        System.out.println();
    }
}
