import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class Graph1 extends Application {
    static double step = 0.005;
    static double scale_x = 5000;
    static double scale_y = 2.7;
    static int times = 1000;
    double width = 800;
    double height = 1000;
    static double x_center, y_center;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        x_center = width / 8;
        y_center = height / 8;
        Canvas canvas = new Canvas(width, height);
        Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.2);
        gc.strokeLine(0, y_center, width, y_center);
        gc.strokeLine(x_center, 0, x_center, height);

        gc.strokeLine(x_center + scale_x * 0.1, y_center + 5, x_center + scale_x * 0.1, y_center - 5);
        /*
        gc.strokeLine(x_center + scale_x * 3, y_center + 5, x_center + scale_x * 3, y_center - 5);
        gc.strokeLine(x_center + scale_x * 2, y_center + 5, x_center + scale_x * 2, y_center - 5);
        gc.strokeLine(x_center + scale_x * 1, y_center + 5, x_center + scale_x * 1, y_center - 5);
        */

/*
        gc.strokeLine(x_center - 5, scale_y * 8 + y_center, x_center + 5, scale_y * 8 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 7 + y_center, x_center + 5, scale_y * 7 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 6 + y_center, x_center + 5, scale_y * 6 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 5 + y_center, x_center + 5, scale_y * 5 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 4 + y_center, x_center + 5, scale_y * 4 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 3 + y_center, x_center + 5, scale_y * 3 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 2 + y_center, x_center + 5, scale_y * 2 + y_center);
        gc.strokeLine(x_center - 5, scale_y + y_center, x_center + 5, scale_y + y_center);
*/
        gc.strokeLine(x_center - 5, scale_y * 100 + y_center, x_center + 5, scale_y * 100 + y_center);
        gc.strokeLine(x_center - 5, scale_y * 200 + y_center, x_center + 5, scale_y * 200 + y_center);

        gc.setLineWidth(2);

        NavigableMap<Double, Double> points = new TreeMap<>();
        gc.setStroke(Color.DARKBLUE);
        draw(gc, points, 100);
        //System.out.println("u(x,0): " + Cp1.sum(100, 0, 0.05));

        gc.setStroke(Color.BLUEVIOLET);
        draw(gc, points, 200);

        gc.setStroke(Color.MEDIUMVIOLETRED);
        draw(gc, points, 300);

        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    static void draw(GraphicsContext gc, NavigableMap<Double, Double> points, double t) {
        double x = 0.001;
        while (x < Cp1.l) {
            points.put(x, Cp1.sum(times, t, x));
            x += step;
        }
        double cnt = 0;
        double x1 = new Double(0);
        double y1 = new Double(0);
        for (Map.Entry<Double, Double> entry : points.entrySet()) {
            double x2 = entry.getKey();
            double y2 = entry.getValue();
            System.out.println(x1 + " => " + y1);
            if (cnt != 0) {
                gc.strokeLine(scale_x * x1 + x_center, -scale_y * y1 + y_center, scale_x * x2 + x_center, -scale_y * y2 + y_center);
            }
            cnt++;
            x1 = x2;
            y1 = y2;
        }
    }
}