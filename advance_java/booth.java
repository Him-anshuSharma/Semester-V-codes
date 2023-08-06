public class booth {
    public static void main(String[] args) {
        int n=4;
        int a=0;int m = 5;int q=2;int q1=0;
        System.out.println(q&1);
        System.out.println(q>>a);
        while(n!=0){
            if((q&1) == 1 && (q1) ==0 ){
                a = a-m;
            }
            else if((q&1) == 0 && (q1) ==1 ){
                a = a+m;
            }
            else{
                int temp = 1;
                q1 = q&1;
                a = q >> a;
                q = a >> temp;
            }
            n = n-1;
        }
        System.out.println(a*10+q);
    }
    
}
