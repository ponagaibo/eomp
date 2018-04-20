import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleFunction;
import java.util.function.Function;

public class Cp1 {
    private static double precision = 0.0001;
    static double l = 0.1;

    private static final DoubleFunction<Double> ddfun = (x) ->
            (0.02 * Math.sin(0.1 * x) / (Math.cos(0.1 * x) * Math.cos(0.1 * x) * Math.cos(0.1 * x)));

    private static final DoubleFunction<Double> dfun = (x) ->
            (0.1 / (Math.cos(0.1 * x) * Math.cos(0.1 * x)) - 2);

    private static final Function<Double, Double> fun = (x) ->
            (Math.tan(0.1 * x) - 2 * x);

    static double findRoot(boolean left, double a, double b) {
        double x0 = b;
        if (left) {
            x0 = a;
        }
        double root = x0 - fun.apply(x0) / dfun.apply(x0);
//        System.out.println("f(a)f(b): " + fun.apply(a) * fun.apply(b));
//        System.out.println("f(x0)f''(x0): " + fun.apply(x0) * ddfun.apply(x0));
        while (Math.abs(root - x0) > precision) {
            x0 = root;
            root = x0 - fun.apply(x0) / dfun.apply(x0);
        }
        return root;
    }


    public static void main(String[] args) {
        List<Double> roots = new LinkedList<>();
        int times = 10;
        for (int i = 0; i < times; i++) {
            roots.add(findRoot(false, (10 * i + 4) * Math.PI, (10 * i + 5) * Math.PI));
            roots.add(findRoot(true, -(10 * i + 5) * Math.PI, -(10 * i + 4) * Math.PI));
        }
        for (double root : roots) {
            System.out.println(root);
        }
    }

    public static double sum(int k, double t, double x) {
        double coef = -1600 * Math.exp(-0.5 * x - 0.25 * t / 1000000) / l;
        double sum = 0;
        List<Double> roots = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            roots.add(findRoot(false, (10 * i + 4) * Math.PI, (10 * i + 5) * Math.PI));
        }
        for (int i = 0; i < k; i++) {
            double lam = roots.get(i);
            double tmp = lam * Math.sin(lam * x) * Math.exp(-lam * lam * t / 1000000) / (4 * lam * lam + 1);
            sum += tmp;
        }
        return coef * sum;
    }
}
