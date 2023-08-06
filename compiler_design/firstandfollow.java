import java.util.*;

class terminalsandnonterminals {
    public Set<String> t; // terminals
    public Set<String> nt; // non-terminals

    terminalsandnonterminals() {
        t = new LinkedHashSet<String>();
        nt = new LinkedHashSet<String>();
    }
}

class rule {
    String lhs;
    String[] rhs;

    rule(String s1, String[] s2) {
        lhs = s1;
        rhs = s2;
    }
}

class firstpositions {
    char nt;
    Set<String> firstpos;

    firstpositions(char c, Set<String> pos) {
        nt = c;
        firstpos = pos;
    }
}

class followpositions {
    char nt;
    Set<String> followPos;

    followpositions(char c, Set<String> pos) {
        nt = c;
        followPos = pos;
    }
}

public class firstandfollow {

    static public terminalsandnonterminals t = new terminalsandnonterminals();
    static Set<firstpositions> firstPos = new LinkedHashSet<firstpositions>();    
    static Set<followpositions> followPos = new LinkedHashSet<followpositions>();
    static Set<String> retvalue = new LinkedHashSet<String>();

    public static void main(String[] args) {
        //
        int total_rules;
        Scanner sc = new Scanner(System.in);
        total_rules = sc.nextInt();
        //rule arrays
        rule[] rules = new rule[total_rules];
        for (int i = 0; i < total_rules; i++) {
            String inp;
            inp = sc.next();
            // a=bc {a,bc}
            String[] s1 = inp.split("=");
            // [0] lhs
            t.nt.add(s1[0]);
            //rhs split, abc = {a,b,c}
            String[] s2 = s1[1].split("");
            //rhs both nt and t
            for (String ch : s2) {
                //if uppercase nt
                if (Character.isUpperCase(ch.charAt(0))) {
                    t.nt.add(ch);
                } else { //t
                    t.t.add(ch);
                }
            }
            //lhs, and rhs
            rules[i] = new rule(s1[0], s2);
        }
    
        for (String term : t.nt) {
            retvalue.clear();
            Set<String> temp = new LinkedHashSet<String>();
            temp.addAll(firstpos(term, rules, retvalue));
            firstPos.add(new firstpositions(term.charAt(0),temp));
        }

        for (String term : t.nt) {
            retvalue.clear();
            Set<String> temp = new LinkedHashSet<String>();
            temp.addAll(followpos(term, rules, retvalue));
            if(temp.isEmpty()){
                temp.add("$");
            }
            followPos.add(new followpositions(term.charAt(0),temp));
        }
        sc.nextInt();
        sc.close();

    }
    

    public static Set<String> firstpos(String ch, rule[] rules, Set<String> retValue) {
        for (rule r : rules) {
            if (r.lhs.equals(ch)) { // nt match
                if (Character.isUpperCase(r.rhs[0].charAt(0))) {
                    firstpos(r.rhs[0], rules, retValue);
                } else {
                    retvalue.add(r.rhs[0]);
                }
            }
        }
        return retValue;
    }

    public static Set<String> followpos(String ch, rule[] rules, Set<String> retValue) {
        for (rule r : rules) {
            for (int i = 0; i < r.rhs.length; i++) {
                if (r.rhs[i].equals(ch)) { // nt match
                    if (i < r.rhs.length - 1) {
                        if(Character.isUpperCase(r.rhs[i+1].charAt(0))){ //nt next char
                            Set<String> temp = getFirstPos(r.rhs[i+1]);
                            retValue.addAll(temp);
                        }
                        else{ // terminal char
                            retValue.add(r.rhs[i+1]);
                        }
                    }
                    else{
                        Set<String> temp = followpos(r.lhs,rules,retValue);
                        retValue.addAll(temp);
                    }
                }
            }
        }
        return retValue;
    }

    private static Set<String> getFirstPos(String ch) {
        for (firstpositions p : firstPos) {
            if (p.nt == ch.charAt(0)) {
                return p.firstpos;
            }
        }
        return new LinkedHashSet<String>();
    }
}
