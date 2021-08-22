class Solution {
    public long mulmod(long a, long b, long mod) {                 
        long res = 0; 
        a %= mod;
 
        while (b > 0) {                         
            if ((b & 1) > 0) {
                res = (res + a) % mod;
            }
            
            a = (a << 1) % mod; 
            b >>= 1;
        }
        
        return res;
    }
    
    public long powmod(long x, long y, long p) {
        long res = 1;

        x = x % p;

        if (x == 0) {
            return 0;
        }

        while (y > 0) {          
            if ((y & 1) != 0) {
                res = (res * x) % p;
            }
          
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }
}