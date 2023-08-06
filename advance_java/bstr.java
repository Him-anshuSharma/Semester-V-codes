public class bstr {
    public static void main(String[] args) {
        int n=3;
        String num = Integer.toBinaryString(n);
        int l=0;int r=num.length()-1;
        boolean flag = true;
        while(l<=r){
            if(num.charAt(l++) != num.charAt(r--)){
                flag = false;
            }
        }
        if(flag){
            System.out.println("IS");
        }
    }
    
}
