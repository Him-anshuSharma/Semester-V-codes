public class blckswp {
    public static void main(String[] args) {
        int[] arr = {1,2,4,5,6};
        int n=2;
        while(n!=0){
            for(int i=0;i<arr.length-1;i++){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
            n-=1;
        }
        for(int k:arr){
            System.out.print(k +" ");
        }
    }   
}
