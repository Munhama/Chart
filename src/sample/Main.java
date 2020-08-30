package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private final Random random = new Random();
    private final int minRandomValue = 1;
    private final int maxRandomValue = 100;
    private int randomValue;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Line Chart");

        Scene scene = new Scene(createChart(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private Parent createChart() {

        // Создание и задание имен осей координат
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        // Отключение прокрутки осей
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        // Создание линейной диаграммы, заголовок, отключение легенды
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Chart");
        lineChart.setLegendVisible(false);

        // Для добавления данных
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        /*ScrollPane scrollPane = new ScrollPane(lineChart);
        scrollPane.setMinSize(1000, 600);

        lineChart.setMinSize(scrollPane.getMinWidth(), scrollPane.getMinHeight() - 20);*/

        // Добавление данных в диаграмму
        lineChart.getData().add(series);

        //scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //scrollPane.setPrefViewportHeight(150);
        //scrollPane.setPrefViewportWidth(200);

        //FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10, series, scrollPane);

        // Обновление сцены в фоновом потоке
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                for (int index = 0; ; index++) {
                    int finalI = index;

                    // Рандом данных
                    randomValue = random.nextInt(maxRandomValue - minRandomValue + 1);
                    randomValue += minRandomValue;

                    // Добавление на сцене
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(finalI, randomValue)));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return lineChart;


    }


}
