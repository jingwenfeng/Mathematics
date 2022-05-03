import java.util.ArrayList;

public class Approx {
    private double alpha;
    private int Q;
    int p;
    int q;


    public Approx(double alpha, int Q) {
        this.alpha = alpha;
        this.Q = Q;
    }

    public double pair() {
        double[] a = new double[Q + 1];
        double app = 0.0;
        for (int i = 0; i <= Q; i++) {
            a[i] =  alpha * i;
        }
        for (int i = 0; i <= Q - 1; i++) {
            double temp1 = (a[i] - ((int) a[i]));
            for (int j = i + 1; j <= Q; j++) {
                double temp2 =  (a[j] - ((int) a[j]));
                int tempindex = Math.abs(i - j);
                double b = Math.abs(temp1 - temp2);
                double check = (double) (1/ ((double) Q)) ;

                if (a[i] != a[j] && b <= check) {
                    double aa = Math.abs(temp1 - temp2);
                    int a1 = Math.abs((int) a[i] - (int) a[j]);
                    app = (double) a1/tempindex;
                    p = a1;
                    q = tempindex;
                    break;
                }
            }
            if (app != 0) {
                break;
            }
        }
        return app;
    }


    public static void main(String[] args) {
       // double n = Double.parseDouble(args[0]);
        //int Q = Integer.parseInt(args[1]);
        int Q = Integer.parseInt(args[0]);
        Approx ap1 = new Approx(Math.sqrt(2), Q);
        ap1.pair();
        System.out.printf("V2 = %d / %d \n", ap1.p, ap1.q);
        Approx ap2 = new Approx(Math.sqrt(3), Q);
        ap2.pair();
        System.out.printf("V3 = %d / %d \n", ap2.p, ap2.q);
        Approx ap3 = new Approx(Math.sqrt(7), Q);
        ap3.pair();
        System.out.printf("V7 = %d / %d \n", ap3.p, ap3.q);
        Approx ap4 = new Approx(Math.PI, Q);
        ap4.pair();
        System.out.printf("pi = %d / %d \n", ap4.p, ap4.q);
        Approx ap5 = new Approx(Math.E, Q);
        ap5.pair();
        System.out.printf("e = %d / %d \n", ap5.p, ap5.q);
        Approx ap6 = new Approx((1 + Math.sqrt(5))/ 2, Q);
        ap6.pair();
        System.out.printf("Golden Ratio = %d / %d \n", ap6.p, ap6.q);


    }


}
