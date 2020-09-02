package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Line Chart");
        // Вывод подключенных COM портов в ComboBox
/*
        // Создание объектов ComboBox и Label
        ComboBox<String> comboBox = new ComboBox<String>();
        Label label = new Label();

        // Подсказка в ComboBox
        comboBox.setPromptText("COM Ports");

        // Получение подключенных COM портов
        String[] portNames = SerialPortList.getPortNames();

        // Добавление имен COM поротов в ComboBOX
        comboBox.setItems(FXCollections.observableArrayList(portNames));

        // Получаем выбранный элемент
        comboBox.setOnAction(event -> label.setText(comboBox.getSelectionModel().getSelectedItem().toString()));
        //System.out.println(label.textProperty().getValue().toString());

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(5));
        root.setHgap(25);

        // Добавление на сцену ComboBOX и Label
        root.getChildren().add(comboBox);
        root.getChildren().add(label);
*/
        // Попытка отправить строку в COM порт (НЕ РАБОТАЕТ)
/*
        String[] portNames = SerialPortList.getPortNames();

        for(String portName : portNames)
            System.out.println(portName);

        SerialPort serialPort = new SerialPort("COM4");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.writeString("Hello");
            //serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
*/
        //Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    // Диаграмма
//    private Parent createChart() {
//
//        // Создание и задание имен осей координат
//        NumberAxis xAxis = new NumberAxis();
//        xAxis.setLabel("Time");
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("Value");
//
//        // Отключение прокрутки осей
//        xAxis.setAutoRanging(false);
//        yAxis.setAutoRanging(false);
//
//        // Создание линейной диаграммы, заголовок, отключение легенды
//        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
//        lineChart.setTitle("Chart");
//        lineChart.setLegendVisible(false);
//
//        // Для добавления данных
//        XYChart.Series<Number, Number> series = new XYChart.Series<>();
//
//        /*ScrollPane scrollPane = new ScrollPane(lineChart);
//        scrollPane.setMinSize(1000, 600);
//
//        lineChart.setMinSize(scrollPane.getMinWidth(), scrollPane.getMinHeight() - 20);*/
//
//        // Добавление данных в диаграмму
//        lineChart.getData().add(series);
//
//        //scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        //scrollPane.setPrefViewportHeight(150);
//        //scrollPane.setPrefViewportWidth(200);
//
//        //FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10, series, scrollPane);
//
//        // Обновление сцены в фоновом потоке
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                for (int index = 0; ; index++) {
//                    int finalI = index;
//
//                    // Рандом данных
//                    randomValue = random.nextInt(maxRandomValue - minRandomValue + 1);
//                    randomValue += minRandomValue;
//
//                    // Добавление на сцене
//                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(finalI, randomValue)));
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        return lineChart;
//
//
//    }


}
