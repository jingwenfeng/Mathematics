public class Mods {
    private int n;
    public Mods(int n) {
        this.n = n;
    }

    public int add(int k, int a) {
        int c = (k + a) % this.n;
        while (c<0) {
            c += n;
        }
        return c;
    }

    public int mul(int k, int a) {
        int c = (k * a) % this.n;
        while (c<0) {
            c += n;
        }
        return c;
    }

    public int inverse(int z) {
        int s = 0;
        int a = z;
        // Set integer b to be the smaller number
        int b = n;
        if (z < n) {
            a = n;
            b = z;
        }
        else if (z > n) {
            a = z;
            b = n;
        }
        else if (z == n) {
            a = z;
            b = n;
        }
        // quotient q
        int q = 0;
        // reminder r1 (final reminder)
        int r1 = a;
        // reminder r2 (temp reminder)
        int r2 = b;
        // coefficient for a, s1
        int s1 = 1;
        int s2 = 0;
        // coefficient for b, t1
        int t1 = 0;
        int t2 = 1;
        while (r2 > 0) {
            q = r1/r2;
            int temp_r = r2;
            r2 = r1 - q * temp_r;
            r1 = temp_r;
            int temp_s = s2;
            s2 = s1 - q * temp_s;
            s1 = temp_s;
            int temp_t = t2;
            t2 = t1 - q * temp_t;
            t1 = temp_t;
        }
        if (r1 != 0) {
            System.out.print("Zero Divisor!");
        } else {
            s = s1;
        }

        return s;
    }

    public int self(int k) {
        int f = (k + Math.abs(k) * this.n) % this.n;
        return f;
    }

    public int power(int n, int x, int p) {
        Mods m = new Mods(n);
        int f = x;
        for (int i = 0; i < p - 1; i++) {
            f = m.mul(f, x);
        }
        f = f % n;

        return f;
    }

    public int temp1(int n, int x) {
        Mods m = new Mods(n);
        int t1 = m.add(x, (-3));
        int t2 = m.mul(x, t1);
        int t3 = m.add(x, 1);
        int f = m.mul(t2, t3);
        return f;
    }

    public int temp2(int n, int x) {
        Mods m = new Mods(n);
        int f = m.mul(3, x);
        return f;
    }

    public int temp3(int n, int x) {
        Mods m = new Mods(n);
        int t = m.add(x, 1);
        int f = m.mul(t, t);
        return f;
    }

    public int temp4(int n, int x) {
        Mods m = new Mods(n);
        int t = m.mul(x, x);
        int t2 = m.mul(t, x);
        return t2;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Mods newMod = new Mods(n);


        System.out.print(newMod.mul(-11, 27));
        System.out.print("\n");
        for (int i = 0; i < n; i++) {
            if (newMod.power(n ,i, 7) == i) {
                System.out.printf("%d ", i);
            }
        }
    }

}
