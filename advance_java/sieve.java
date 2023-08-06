public class sieve{

    public static void simpleSieve(int limit){
        boolean[] prime = new boolean[limit+1];
        for(int i=2; i<=limit; i++){
            prime[i] = true;
        }
        for(int p=2;p*p<=limit;p++){
            if(prime[p] == true){
                for(int j=p*p;j<=limit;j += p){
                    prime[j] = false;
                }
            }
        }
        for(int i=2;i<=limit;i++){
            if(prime[i]){
                System.out.println(i + " ");
            }
        }
    }
    public static void main(String[] args) {
        simpleSieve(33);
    }
}