public class Cp2 {
    static double a = 1000;

    public static double sum(int k, double t, double x) {
        double first = t * (x - Math.PI);

        double coef2 = 16 / (a * Math.PI);
        double sum2 = 0;
        for (int i = 1; i <= k; i++) {
            double tmp = 16 / (Math.pow((2 * i + 1), 3)*Math.PI*1000) * Math.sin((2 * i + 1) * t * 500)
                    * Math.cos(x * (2 * i + 1) / 2);
//            System.out.println("\n1/y^3: " + 1 / Math.pow((2 * i + 1), 3));
//            System.out.println("sin: " + t * (2 * i + 1) / 2);
//            System.out.println("cos: " + Math.cos(x * (2 * i + 1) / 2));
//            System.out.println("x: " + x);
//            System.out.println("tmp: " + tmp);
            sum2 += tmp;
        }

        double sum3 = 0;
        for (int i = 1; i <= k; i++) {
            double coef3 = (1 - Math.cos(1 - Math.PI * i - Math.PI / 2)) / (1 - Math.PI * i - Math.PI / 2) +
                    (1 - Math.cos(1 + Math.PI * i + Math.PI / 2)) / (1 + Math.PI * i + Math.PI / 2);
            double tmp = coef3 * 4 * Math.cos((2 * i + 1) * x / 2) * (1 - Math.cos((2 * i + 1) * t * 500))
                    / ((2 * i + 1) * (2 * i + 1) * 1000000);
            sum3 += tmp;
        }
        double tmp = first + sum2 + sum3;
        //System.out.println("sum: " + tmp);
        return first + sum2 + sum3;
    }
}
