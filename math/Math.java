public class Math {
    public static List<Integer> factorize(int k) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i * i <= k; i++) {
            if (k % i == 0) {
                factors.add(i);
                if (k / i != i) {
                    factors.add(k / i);
                }
            }
        }

        return factors;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int gcd(int... a) {
        int t = a[0];
        for (int i = 1; i < a.length; i++) {
            t = gcd(t, a[i]);
        }
        return t;
    }

    public static long gcd2(int a, int b) {
        int t;
        if (a == 0) return b;
        if (b == 0) return a;
        while (a % b > 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return b;
    }

    public static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}