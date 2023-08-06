public class swapnibble {
    public static void main(String[] args) {
        int n = 10;
        int l = n & 0xF0;
        int r = n & 0x0F;
        l = l>>4;
        r = r<<4;
        n = l|r;
        System.out.println(n);
    }   
}
