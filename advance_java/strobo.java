import java.util.*;

public class strobo {
    static Map<Character,Character> strobNum = new LinkedHashMap<Character,Character>();
    public static void main(String[] args) {
        strobNum.put('6', '9');
        strobNum.put('1', '1');
        strobNum.put('9', '6');
        strobNum.put('8', '8');
        strobNum.put('0', '0');


        String input = "1671";
        int size = input.length();
        for(int i = 0; i<size-i-1;i++ ){
            if(!strobNum.containsValue(input.charAt(i))){
                System.out.println("Not");
                break;
            }
            if(strobNum.get(input.charAt(i)) != input.charAt(size-i-1)){
                System.out.println("NOT");
                break;
            }

        }
    }
}
