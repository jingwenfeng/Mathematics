import java.util.Random;

public class RSA {

    final private int n;
    private int temp_prime;
    private int prime;
    boolean b = false;
    int e;
    int d;
    private int x0;
    private int y0;
    private int N;
    private int phi_N;

    public Prime(int n) {
        this.n = n;
    }

    public void nBitRandom() {
        Random rand = new Random();

        int min = (int) Math.pow(2, n-1) + 1;
        int max = (int) Math.pow(2, n) - 1;
        int c = rand.nextInt(max - min);
        int ran = min + c;
        // System.out.println("Random = ");
        // System.out.println(ran);
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67,
                71, 73, 79, 83, 89, 97, 101, 103,
                107, 109, 113, 127, 131, 137, 139,
                149, 151, 157, 163, 167, 173, 179,
                181, 191, 193, 197, 199, 211, 223,
                227, 229, 233, 239, 241, 251, 257,
                263, 269, 271, 277, 281, 283, 293,
                307, 311, 313, 317, 331, 337, 347, 349};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] * arr[i] > ran) {
                break;
            }
            if (ran % arr[i] == 0) {
                temp_prime = 0;
                break;
            }
            temp_prime = ran;
        }
        /*
        if (temp_prime == 0) {
            nBitRandom();
        }

        if (ran % 2 != 0 && ran % 3 != 0 && ran % 7 != 0) {
            temp_prime = ran;
        } else {
            nBitRandom();
        }

         */

    }

    public void euler() {
        Prime w = new Prime(n);
        w.nBitRandom();
        int t = w.temp_prime;
        for (int i = 2; i < t; i++) {
            int k = t - 1;
            int t2 = 1;
            for (int j = 0; j < k; j++) {
                t2 *= i;
                t2 %= t;
            }
            if ((t2 - (t2/t) * t) == 1) {
                prime = t;
            } else {
                prime = 0;
                break;
            }
        }

    }

    public int gcd1(int k1, int k2) {
        int g = 1;
        if (k1 < k2) {
            for (int i = 1; i <= k1; i++) {
                if (k1 % i == 0) {
                    if (k2 % i == 0) {
                        g = i;
                    }
                }
            }
        }
        if (k1 > k2) {
            for (int i = 1; i <= k2; i++) {
                if (k2 % i == 0) {
                    if (k1 % i == 0) {
                        g = i;
                    }
                }
            }
        }

        return g;

    }

    public void Euclidean(int k1, int k2) {
        int a2 = k1;
        // Set integer b to be the smaller number
        int b2 = k2;
        if (k1 < k2) {
            a2 = k2;
            b2 = k1;
        }
        // quotient q
        int q2;
        // reminder r1 (final reminder)
        int r1 = a2;
        // reminder r2 (temp reminder)
        int r2 = b2;
        // coefficient for a, s1
        int s1 = 1;
        int s2 = 0;
        // coefficient for b, t1
        int t1 = 0;
        int t2 = 1;
        while (r2 > 0) {
            q2 = r1/r2;
            int temp_r = r2;
            r2 = r1 - q2 * temp_r;
            r1 = temp_r;
            int temp_s = s2;
            s2 = s1 - q2 * temp_s;
            s1 = temp_s;
            int temp_t = t2;
            t2 = t1 - q2 * temp_t;
            t1 = temp_t;
        }
        if (k1 < k2) {
            int tem = s1;
            s1 = t1;
            t1 = tem;
        }
        x0 = s1;
        y0 = t1;

    }


    public int self(int k1, int k2) {
        int f = (int) (k1 + Math.pow(Math.abs(k1), n % 10) ) % k2;
        return f;
    }

    public void getN(int p, int q) {
        N = p * q;
        phi_N = (p-1) * (q - 1);
    }


    public void getE(int p, int q) {
        Prime temp = new Prime(n);
        Random rand = new Random();
        temp.getN(p, q);
        int k = rand.nextInt(temp.phi_N);
        if (temp.gcd1(k, temp.phi_N) == 1) {
            e = k;
        } else {
            getE(p, q);
        }
    }

    public void getD(int p1, int q1) {
        Prime temp = new Prime(n);
        temp.getN(p1, q1);
        temp.Euclidean(e, temp.phi_N);

        int r = temp.self(temp.x0, temp.phi_N);
        d = r;
    }




    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int p = 0;
        int q = 0;
        Prime pp = new Prime(n);
        Prime qq = new Prime(n);
        while (p == 0) {
            pp.euler();
            p = pp.prime;
        }

        while (q == 0 || q == p) {
            qq.euler();
            q = qq.prime;
        }
        System.out.printf("p = %d\n", p);
        System.out.printf("q = %d\n", q);

        System.out.println("-------------------------");





        pp.getE(p, q);
        pp.getD(p, q);
        pp.getN(p, q);

        System.out.printf("Public Key = (%d, %d) \n", pp.N, pp.e);
        System.out.printf("Private Key = (%d, %d) \n", pp.N, pp.d);
    }

}
