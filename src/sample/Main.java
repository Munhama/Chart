package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    // Разобраться с количеством переменных (все ли нужны?)
    public static float f_plot = 1000000.0f;      // 1M
    public static float f_filter = 20000.0f;      // 20k
//    public static float f_sample = 20000.0f;      // 20k
    public static int Nplot = 250 * 300;
    public static int Ncalc = 404; // Nplot / otn;
    public static int otn = (int) ((f_plot / f_filter) + 0.5f);
//    public static int Nsample = Nplot / otn;
//    public static int dt = 25;
    public static int Nplot_start = 0;
    public static int Nplot_end = 404;
    public static int Ntap = 16;
    public static int Ntap2 = 98;
    public static int Ncalc_start = 102;
    public static int Ncalc_end = 404;

    // Для чтения файла данных INPUT_SIGNAL
    public static final ArrayList<Double> INPUT_SIGNAL_ARRAY = new ArrayList<>();
    public static final String INPUT_SIGNAL_FILE_NAME = "./input_signal.txt";

    // Для чтения файла данных STATES
    public static final ArrayList<Double> STATES_ARRAY = new ArrayList<>();
    public static final String STATES_FILE_NAME = "./states.txt";

    // Для чтения файла данных ZNUM
    public static final ArrayList<Double> ZNUM_ARRAY = new ArrayList<>();
    public static final String ZNUM_FILE_NAME = "./znum.txt";


    // Для чтения файла данных STATES2
    public static final ArrayList<Double> STATES2_ARRAY = new ArrayList<>();
    public static final String STATES2_FILE_NAME = "./states2.txt";

    // Для чтения файла данных ZNUM2
    public static final ArrayList<Double> ZNUM2_ARRAY = new ArrayList<>();
    public static final String ZNUM2_FILE_NAME = "./znum2.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Chart Filter");
//        final NumberAxis xAxis = new NumberAxis("Time", Nplot_start/f_plot, Nplot_end/f_plot, 0.0001);
        final NumberAxis xAxis = new NumberAxis(Nplot_start, Nplot_end, 1);
        xAxis.setLabel("cnt");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amplitude");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series inputSignalChart = new XYChart.Series();
        inputSignalChart.setName("Before Filter");

        XYChart.Series filteredSignalChart = new XYChart.Series();
        filteredSignalChart.setName("After Filter");

        XYChart.Series filtered2SignalChart = new XYChart.Series();
        filtered2SignalChart.setName("After Filter2");

        lineChart.setCreateSymbols(true);

        List<Double> filter = new ArrayList<>();
        List<Double> filter2 = new ArrayList<>();

        // Чтение файлов данных
        readFile(INPUT_SIGNAL_ARRAY, INPUT_SIGNAL_FILE_NAME);
        readFile(ZNUM_ARRAY, ZNUM_FILE_NAME);
        readFile(STATES_ARRAY, STATES_FILE_NAME);

        readFile(ZNUM2_ARRAY, ZNUM2_FILE_NAME);
        readFile(STATES2_ARRAY, STATES2_FILE_NAME);

        // Вроде больше не нужно
        /*
        for (int i = 0; i < Nplot; i++, a += 1 / f_sample, time += 1 / f_plot) {
            x.add(a);
            t.add(time);
            Ampl = (10);
            s = (float) (Ampl * Math.sin(2 * Math.PI * 5000 * t.get(i)));
            s2 = (float) (10 * Math.sin(2 * Math.PI * 2000 * t.get(i)));
//            s3 = s + s2;
            signal.add(s);
            noise.add(s2);
//            y.add(s3);
        }
        */

        // Рассчет точек для построения фильтра
//        for (int i = 0; i < Ncalc; i++) {
//            System.out.println(" --> " + Nplot / otn);
//            filter.add(filt(INPUT_SIGNAL_ARRAY.get(i * otn)));  // 500 точек
//            filter2.add(filt2(INPUT_SIGNAL_ARRAY.get(i * otn)));  // 500 точек
//        }
        // Рассчет точек для построения фильтра
        for (int i = 0; i < Ncalc; i++) {
           // System.out.println(" --> " + Nplot / otn);
            filter.add(filt(INPUT_SIGNAL_ARRAY.get(i)));  // 500 точек
            filter2.add(filt2(INPUT_SIGNAL_ARRAY.get(i)));  // 500 точек
        }

//**************************************
// Фильтр с habr'a
//        float[] out = new float[y.size()];
//        for (int i=0; i< y.size()/otn; i++)
//        {
//            out[i]= (float) 0.0;
//            for (int j=0; j<znum.length-1; j++)
//                if(i-j>=0)
//                    out[i]+= znum[j]*y.get(otn*i-j);
//        }
//**************************************
// Рисовалка
        System.out.println(filter.size());
        System.out.println(filter2.size());
/*
        for (int i = Nplot_start; i < Nplot_end; i++) {
            series1.getData().add(new XYChart.Data(t.get(i), signal.get(i)));
            series2.getData().add(new XYChart.Data(t.get(i), noise.get(i)));
            series3.getData().add(new XYChart.Data(t.get(i), y.get(i)));
        }


 */
        /*
        for (int i = Nplot_start; i < Nplot_end; i++) {
//            series4.getData().add(new XYChart.Data(t.get(i), (out[i])));
            series5.getData().add(new XYChart.Data(t.get(i), (filter.get(i/otn))));
        }
        */
        for (int i = Nplot_start; i < Nplot_end; i++) {
//            inputSignalChart.getData().add(new XYChart.Data(i, INPUT_SIGNAL_ARRAY.get(i * otn)));
            inputSignalChart.getData().add(new XYChart.Data(i, INPUT_SIGNAL_ARRAY.get(i)));
            filteredSignalChart.getData().add(new XYChart.Data(i, (filter.get(i))));
            filtered2SignalChart.getData().add(new XYChart.Data(i, (filter2.get(i))));
        }


        double Ampl1, Ampl2, Ampl3, Ampl4, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum11 = 0, sum22 = 0, sum33 = 0, sum44 = 0;
        double[] a1 = {0.0, 0.0, 0.0, 0.0};
        double[] a2 = {0.0, 0.0, 0.0, 0.0};
//        double[] a3 = {0.0, 0.0, 0.0, 0.0};
        double[] a4 = {0.0, 0.0, 0.0, 0.0};
        for (int i = Ncalc_start+2; i < Ncalc_end-2; i++) {
            a1[0] = ( filter.get(i-2) + filter.get(i-1) + filter.get(i) + filter.get(i+1) ) / 4;
            a1[1] = ( filter.get(i-1) + filter.get(i) + filter.get(i+1) + filter.get(i+2) ) / 4;
            a1[0] = filter.get(i) - a1[0];
            a1[1] = filter.get(i+1) - a1[1];
            sum1 += a1[0]*a1[0] + a1[1]*a1[1];
            sum11 += filter.get(i);

            a2[0] = ( filter2.get(i-2) + filter2.get(i-1) + filter2.get(i) + filter2.get(i+1) ) / 4;
            a2[1] = ( filter2.get(i-1) + filter2.get(i) + filter2.get(i+1) + filter2.get(i+2) ) / 4;
            a2[0] = filter2.get(i) - a2[0];
            a2[1] = filter2.get(i+1) - a2[1];
            sum2 += a2[0]*a2[0] + a2[1]*a2[1];
            sum22 += filter2.get(i);

            a4[0] = ( INPUT_SIGNAL_ARRAY.get(i-2) + INPUT_SIGNAL_ARRAY.get(i-1) + INPUT_SIGNAL_ARRAY.get(i) + INPUT_SIGNAL_ARRAY.get(i+1) ) / 4;
            a4[1] = ( INPUT_SIGNAL_ARRAY.get(i-1) + INPUT_SIGNAL_ARRAY.get(i) + INPUT_SIGNAL_ARRAY.get(i+1) + INPUT_SIGNAL_ARRAY.get(i+2) ) / 4;
            a4[0] = INPUT_SIGNAL_ARRAY.get(i) - a4[0];
            a4[1] = INPUT_SIGNAL_ARRAY.get(i+1) - a4[1];
            sum4 += a4[0]*a4[0] + a4[1]*a4[1];
            sum44 += INPUT_SIGNAL_ARRAY.get(i);

//            series5.getData().add(new XYChart.Data(i, (filter.get(i))));
//            series4.getData().add(new XYChart.Data(i, (filter2.get(i))));
//            series1.getData().add(new XYChart.Data(i, signal.get(i * otn)));
//            series3.getData().add(new XYChart.Data(i, INPUT_SIGNAL_ARRAY.get(i * otn)));
        }
        Ampl1 = Math.sqrt( sum1/ (Ncalc_end-Ncalc_start) );
        Ampl2 = Math.sqrt( sum2/ (Ncalc_end-Ncalc_start) );
//        Ampl3 = Math.sqrt(a3[0] * a3[0] + a3[1] * a3[1]);
        Ampl4 = Math.sqrt(sum4/ (Ncalc_end-Ncalc_start));

        System.out.println(STATES_ARRAY.size());
        System.out.println(STATES2_ARRAY.size());
        System.out.println(Ampl1 + " Ampl 1 filter");
        System.out.println(Ampl2 + " Ampl 2 filter");
//        System.out.println(Ampl3 + " signal");
        System.out.println(Ampl4 + " Ampl s+n");
        System.out.println(sum11 + " sum 1 filter");
        System.out.println(sum22 + " sum 2 filter");
//        System.out.println(sum3 + " signal");
        System.out.println(sum44 + " sum s+n");
//**************************************

        final StackPane pane = new StackPane();
        pane.getChildren().add(lineChart);

        final Scene scene = new Scene(pane, 1920, 1010);

        new ZoomManager(pane, lineChart, inputSignalChart, filteredSignalChart, filtered2SignalChart);

        stage.setScene(scene);
        stage.show();
    }

    // Фильтр из FilterSolutions
    /* Filter Solutions Version 2015                 Nuhertz Technologies, L.L.C. */
    /*                                                            www.nuhertz.com */
    /*                                                            +1 602-279-2448 */
    /* 16 Tap Band Pass Hamming                                                   */
    /* Finite Impulse Response                                                    */
    /* Sample Frequency = 20.00 KHz                                               */
    /* Standard Form                                                              */
    /* Arithmetic Precision = 4 Digits                                            */
    /*                                                                            */
    /* Center Frequency = 5.000 KHz                                               */
    /* Pass Band Width = 200.0 Hz                                                 */
    /*                                                                            */
    public double filt(Double dataArray) {
        float sumnum = 0;

        for (int i = 0; i < Ntap - 1; i++) {
            sumnum += STATES_ARRAY.get(i) * ZNUM_ARRAY.get(i);
            if (i < Ntap - 2)
                STATES_ARRAY.set(i, STATES_ARRAY.get(i + 1));
        }
        STATES_ARRAY.set(Ntap - 2, dataArray);
        sumnum += STATES_ARRAY.get(Ntap - 2) * ZNUM_ARRAY.get(Ntap - 1);
        return sumnum;
    }


    /* Filter Solutions Version 2015                 Nuhertz Technologies, L.L.C. */
    /*                                                            www.nuhertz.com */
    /*                                                            +1 602-279-2448 */
    /* 98 Tap Band Pass Hamming                                                   */
    /* Finite Impulse Response                                                    */
    /* Sample Frequency = 20.00 KHz                                               */
    /* Standard Form                                                              */
    /* Arithmetic Precision = 4 Digits                                            */
    /*                                                                            */
    /* Center Frequency = 5.000 KHz                                               */
    /* Pass Band Width = 200.0 Hz                                                 */
    /*                                                                            */
    public double filt2(Double dataArray) {
        float sumnum = 0;

        for (int i = 0; i < Ntap2 - 1; i++) {
            sumnum += STATES2_ARRAY.get(i) * ZNUM2_ARRAY.get(i);
            if (i < Ntap2 - 2)
                STATES2_ARRAY.set(i, STATES2_ARRAY.get(i + 1));
        }
        STATES2_ARRAY.set(Ntap2 - 2, dataArray);
        sumnum += STATES2_ARRAY.get(Ntap2 - 2) * ZNUM2_ARRAY.get(Ntap2 - 1);
        return sumnum;
    }
/*
    public static double[] states2 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static double[] znum2 = {
            1.335e-02, -2.1132e-02, -4.0594e-02, 6.8266e-02, .10123, -.13358, -.15825, .1707, .1707, -.15825,
            -.13358, .10123, 6.8266e-02, -4.0594e-02, -2.1132e-02, 1.335e-02
    };
    public double filt2(Double Y) {
        float sumnum = 0;

        for (int i = 0; i < Ntap2 - 1; i++) {
            sumnum += states2[i] * znum2[i];
            if (i < Ntap2 - 2)
                states2[i] = states2[i + 1];
        }
        states2[Ntap2 - 2] = Y;
        sumnum += states2[Ntap2 - 2] * znum2[Ntap2 - 1];
        return sumnum;
    }
 */

    // Чтение из файла и запись в коллекцию
    public void readFile(ArrayList<Double> fileArray, String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            String[] splitString = temp.split(", ");

            ArrayList<Double> tempList = new ArrayList<>();
            for (int i = 0; i < splitString.length; i++) {
                tempList.add(Double.parseDouble(splitString[i]));
                fileArray.add(tempList.get(i));
            }

            System.out.println("tempSize " + tempList.size());
        }
        scanner.close();
        System.out.println("ySize " + fileArray.size());
    }

}
