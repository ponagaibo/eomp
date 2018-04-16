import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Graph2 extends Application {
    static double step = 0.05;
    static double scale = 65;
    static int times = 400;
    double width = 800;
    double height = 700;
    static double x_center, y_center;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        x_center = width / 4;
        y_center = height / 10;
        Canvas canvas = new Canvas(width, height);
        Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.2);
        gc.strokeLine(0, y_center, width, y_center);
        gc.strokeLine(x_center, 0, x_center, height);
        gc.strokeLine(x_center + scale * 4, y_center + 5, x_center + scale * 4, y_center - 5);
        gc.strokeLine(x_center + scale * 3, y_center + 5, x_center + scale * 3, y_center - 5);
        gc.strokeLine(x_center + scale * 2, y_center + 5, x_center + scale * 2, y_center - 5);
        gc.strokeLine(x_center + scale * 1, y_center + 5, x_center + scale * 1, y_center - 5);
        gc.strokeLine(x_center - 5, scale * 8 + y_center, x_center + Math.PI, scale * 8 + y_center);
        gc.strokeLine(x_center - 5, scale * 7 + y_center, x_center + Math.PI, scale * 7 + y_center);
        gc.strokeLine(x_center - 5, scale * 6 + y_center, x_center + Math.PI, scale * 6 + y_center);
        gc.strokeLine(x_center - 5, scale * 5 + y_center, x_center + Math.PI, scale * 5 + y_center);
        gc.strokeLine(x_center - 5, scale * 4 + y_center, x_center + Math.PI, scale * 4 + y_center);
        gc.strokeLine(x_center - 5, scale * 3 + y_center, x_center + Math.PI, scale * 3 + y_center);
        gc.strokeLine(x_center - 5, scale * 2 + y_center, x_center + Math.PI, scale * 2 + y_center);
        gc.strokeLine(x_center - 5, scale + y_center, x_center + Math.PI, scale + y_center);
        gc.setLineWidth(2);

        NavigableMap<Double, Double> points = new TreeMap<>();
        gc.setStroke(Color.DARKBLUE);
        draw(gc, points, 1);

        gc.setStroke(Color.BLUEVIOLET);
        draw(gc, points, 2);

        gc.setStroke(Color.MEDIUMVIOLETRED);
        draw(gc, points, 3);

        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    static void draw(GraphicsContext gc, NavigableMap<Double, Double> points, double t) {
        double x = 0;
        while (x < Math.PI) {
            points.put(x, Cp2.sum(times, t, x));
            x += step;
        }
        double cnt = 0;
        double x1 = new Double(0);
        double y1 = new Double(0);
        for (Map.Entry<Double, Double> entry : points.entrySet()) {
            double x2 = entry.getKey();
            double y2 = entry.getValue();
            //System.out.println(x1 + " => " + y1);
            if (cnt != 0) {
                gc.strokeLine(scale * x1 + x_center, -scale * y1 + y_center, scale * x2 + x_center, -scale * y2 + y_center);
            }
            cnt++;
            x1 = x2;
            y1 = y2;
        }
    }
}
