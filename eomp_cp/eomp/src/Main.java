import java.util.*;

public class Main {
    public static void main(String[] args) {
        double step = 0.01;
        double x = 0;
        NavigableMap<Double, Double> points = new TreeMap<>();
        //List<Double> points_x = new ArrayList();
        int cnt = 0;
        while (x <= Math.PI) {
            points.put(x, Cp2.sum(5, 1, x));
            //points_x.add(Cp2.sum(5, 1, x));
            x += step;
        }

        for (Map.Entry<Double, Double> entry : points.entrySet()) {
            Double xx = entry.getKey();
            Double yy = entry.getValue();
            System.out.println(xx + " => " + yy);
        }
    }
}