public class eulerphi {

    static int count=0;

    static int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    public static void main(String[] args) {
        int n=123;
        for(int i=1;i<n;i++){
            if(gcd(n,i) == 1){
                count+=1;
            }
        } 
        System.out.println(count); 
    }
}
