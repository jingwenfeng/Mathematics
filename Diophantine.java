public class Diophantine {
    private int a;
    private int b;
    private int c;
    private int x0;
    private int y0;
    private boolean check = true;
    private int gcd;
    private int po_x0;
    private int po_y0;
    private boolean check2 = true;
    public Diophantine(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public int gcd() {
        int g = 1;
        if (a < b) {
            for (int i = 1; i <= this.a; i++) {
                if (this.a % i == 0) {
                    if (this.b % i == 0) {
                        g = i;
                    }
                }
            }
        }
        if (a > b) {
            for (int i = 1; i <= this.b; i++) {
                if (this.b % i == 0) {
                    if (this.a % i == 0) {
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

    public int solution_x0() {
        return x0;
    }

    public int solution_y0() {
        return y0;
    }



    public void solution() {
        Diophantine di2 = new Diophantine(a, b, c);
        gcd = di2.gcd();
        if (c == 0) {
            this.y0 = a;
            this.x0 = -b;
        }
        else if (di2.gcd() != 1) {
            if (c % di2.gcd() != 0) {
                check = false;
            } else {
                a = di2.a/di2.gcd();
                b = di2.b/ di2.gcd();
                c = di2.c / di2.gcd();
                Diophantine di3 = new Diophantine(a, b, c);
                di3.Euclidean(Math.abs(a), Math.abs(b));
                if (a < 0) {
                    di3.x0 *= -1;
                }
                if (b < 0) {
                    di3.y0 *= -1;
                }
                this.x0 = di3.x0 * c;
                this.y0 = di3.y0 * c;
            }
        } else {
            a = di2.a;
            b = di2.b;
            c = di2.c ;
            di2.Euclidean(Math.abs(a), Math.abs(b));
            if (a < 0) {
                di2.x0 *= -1;
            }
            if (b < 0) {
                di2.y0 *= -1;
            }
            x0 = di2.x0 * c;
            y0 = di2.y0 * c;
        }
    }

    public boolean ch() {
        return check;
    }

    public void general() {
        Diophantine temp = new Diophantine(a, b, c);
        int temp1 = b / temp.gcd();
        int temp2 = a / temp.gcd();
        a = a * gcd;
        b = b * gcd;
        System.out.print("General Solution: \n");
        System.out.printf("((%d) + (%d) * t) * (%d) + ((%d) - (%d) * t) * (%d) = (%d)\n", x0,temp1, a, y0,temp2, b, c);
    }

    public void positive() {
        int t = 0;
        int temp_x0 = x0;
        int temp_y0 = y0;
        int a_tem = a/gcd;
        int b_tem = b/gcd;
        while (temp_x0 < 0) {
            if (b < 0)
            {

                temp_x0 += (b_tem);
                temp_y0 -= (a_tem);
            } else if (b > 0) {
                temp_x0 += b_tem;
                temp_y0 -= a_tem;
            }
            if (temp_y0 < 0) {
                check2 = false;
                break;
            }
            po_x0 = temp_x0;
            po_y0 = temp_y0;
        }

        while (temp_y0 < 0) {
            if (a < 0)
            {
                temp_x0 -= b_tem;
                temp_y0 += a_tem;
            } else if (a > 0) {
                temp_x0 -= (b_tem);
                temp_y0 += (a_tem);
            }
            if (temp_x0 < 0) {
                check2 = false;
                break;
            }
            po_x0 = temp_x0;
            po_y0 = temp_y0;
        }

    }




    public static void main(String[] args) {
        int k1 = Integer.parseInt(args[0]);
        int k2 = Integer.parseInt(args[1]);
        int k3 = Integer.parseInt(args[2]);
        Diophantine di = new Diophantine(k1, k2, k3);
        di.solution();
        if (!di.ch()) {
            System.out.print("No Solution!\n");
        } else {
            System.out.print("One Solution: \n");
            System.out.printf("(%d) * (%d) + (%d) * (%d) = (%d)\n", di.x0, k1, di.y0, k2, k3 );
            di.general();
            di.positive();
            if (!di.check2) {
                System.out.print("No Positive Solutions!\n");
            } else {
                System.out.print("Positive Solution: \n");
                System.out.printf("(%d) * (%d) + (%d) * (%d) = (%d)\n", di.po_x0, k1, di.po_y0, k2, k3 );
            }
        }



    }
}
