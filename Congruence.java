public class Congruence {
    private int n;
    private int x0;
    private int y0;

    public Congruence(int n) {
        this.n = n;
    }

    public int self(int k) {
        int f = (k + Math.abs(k) * this.n) % this.n;
        return f;
    }

    public int gcd1(int k) {
        int g = 1;
        if (k < n) {
            for (int i = 1; i <= k; i++) {
                if (k % i == 0) {
                    if (n % i == 0) {
                        g = i;
                    }
                }
            }
        }
        if (k > n) {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    if (k % i == 0) {
                        g = i;
                    }
                }
            }
        }

        return g;

    }

    public int gcd2(int n, int k) {
        int g = 1;
        if (k < n) {
            for (int i = 1; i <= k; i++) {
                if (k % i == 0) {
                    if (n % i == 0) {
                        g = i;
                    }
                }
            }
        }
        if (k > n) {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    if (k % i == 0) {
                        g = i;
                    }
                }
            }
        }

        return g;

    }

    public int add(int a, int b) {
        int c = (a + b) % n;
        while (c<0) {
            c += n;
        }
        return c;

    }

    public int mul(int a, int b) {
        int c = (a * b) % this.n;
        while (c<0) {
            c += n;
        }
        return c;

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

    public int inverse(int k) {
        Congruence con = new Congruence(n);
        con.Euclidean(n, k);
        int r = con.self(con.y0);
        return r;
    }

    public int LinearCon(int a, int b) {
        Congruence con = new Congruence(n);
        int k = con.inverse(a);
        int temp = con.mul(k, b);
        return temp;
    }

    public void LinearCon2(int a, int b) {
        Congruence con1 = new Congruence(n);
        int temp_a = a/ con1.gcd1(a);
        int temp_b = b / con1.gcd1(a);
        int temp_n = n / con1.gcd1(a);
        Congruence con = new Congruence(temp_n);
        int k = con.inverse(temp_a);
        int temp = con.mul(k, temp_b);
        for (int i = 0; i < con1.gcd1(a); i++) {
            if (temp_b % temp_n == 0) {
                System.out.print("x in Z_n\n");
                break;
            } else {
                int t = temp + i * temp_n;
                System.out.printf("x%d = %d\n", i+1, t);
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);



        Congruence con = new Congruence(n);

        System.out.printf("least residue = %d\n", con.self(1492));
        if (con.gcd1(a) != 1) {
            if (b % con.gcd1(a) != 0) {
                System.out.print("No Solution!\n");
            } else {
                con.LinearCon2(a, b);
            }
        } else {
            System.out.printf("x = %d\n", con.LinearCon(a, b));
            System.out.printf("Inverse of a = %d\n", con.inverse(a));


        }

    }



}
