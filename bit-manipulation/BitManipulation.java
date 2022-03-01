
public class BitManipulation {

    public static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    public static int toggleBit(int num, int i) {
        return num ^ (1 << i);
    }
        
    public static int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    // clear all bits from the most significant bit through i (inclusive)
    public static int clearBitsMSBthroughI(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    // clear all bits from i through 0 (inclusive)
    public static int clearBitsIthrough0(int num, int i) {
        int mask = (-1 << (i + 1));
        return num & mask;
    }

    public static int updateBit(int num, int i, boolean bitIs1) {
        int value = bitIs1 ? 1 : 0;
        int mask = ~(1 << i);
        return (num & mask) | (value << i);
    }

    public static boolean isPowerOf2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static boolean isPowerOf4(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }

    // Brian Kernighan's way
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
    
    public static int countSetBits2(int n) {
        int v = 0; // count the number of bits set in v
        int c = 0; // c accumulates the total bits set in v

        for (c = 0; v > 0; v >>= 1) {
          c += v & 1;
        }
        
        return c;
    }
    
    // turn off the rightmost 1-bit
    public static int turnOffRightmost1Bit(int num) {
        return num & (num - 1);
    }
    
    // isolate the rightmost 1-bit
    public static int isolateRightmost1bit(int num) {
        return num & (-num);
    }
    
    // isolate the rightmost 0-bit
    public static int isolateRightmost0bit(int num) {
        return ~num & (num + 1);
    }
    
    // turn on the rightmost 0-bit
    public static int turnOnRightmost1Bit(int num) {
        return num | (num + 1);
    }
    
    public static int nextPowerOf2(int num) {
        int v = num; // compute the next highest power of 2 of 32-bit v

        v--;
        v |= v >> 1;
        v |= v >> 2;
        v |= v >> 4;
        v |= v >> 8;
        v |= v >> 16;
        v++;
        
        return v;
    }
	
	// count set bits for (n + 1) numbers from range [0, n]
	public int[] countSetBits(int n) {
        int[] ans = new int[n + 1];
        for (int k = 1; k <= n; k++) {
            ans[k] = ans[k & (k - 1)] + 1;
        }
        return ans;
    }
	
	// count set bits for (n + 1) numbers from range [0, n]
	public int[] countSetBits2(int n) {
        int[] ans = new int[n + 1];
        for (int k = 1; k <= n; k++) {
            // k / 2 is k >> 1 and k % 2 is k & 1
            ans[k] = ans[k >> 1] + (k & 1); 
        }
        return ans;
    }
}
