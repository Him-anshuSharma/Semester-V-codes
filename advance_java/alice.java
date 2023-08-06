public class alice {
    public static void main(String[] args) {
        int n=120;
        int i=0,x=0;
        while(x<n){
            x = x+i*i;
            ++i;
        }
        System.out.println((i-1)*8);
    }
}
