package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import jssc.SerialPort;
import jssc.SerialPortList;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acceptButton;

    @FXML
    private ComboBox<String> COMNameComboBox;

    @FXML
    private Button refreshButton;

    @FXML
    private ComboBox<String> BaudrateComboBox;

    @FXML
    private ComboBox<String> ParityComboBox;

    @FXML
    private ComboBox<String> StopBitComboBox;

    private ObservableList<String> baudList =
            FXCollections.observableArrayList(
                    "110", "300", "600", "1200", "4800", "9600", "14400",
                    "19200", "38400", "57600", "115200", "128000", "256000"
            );

    private ObservableList<String> parityList =
            FXCollections.observableArrayList(
                    "none", "odd","even", "mark", "space"
            );

    private ObservableList<String> stopBitList =
            FXCollections.observableArrayList("1", "1.5","2" );

    @FXML
    void initialize() {
        assert acceptButton != null : "fx:id=\"acceptButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert COMNameComboBox != null : "fx:id=\"COMNameComboBox\" was not injected: check your FXML file 'sample.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert BaudrateComboBox != null : "fx:id=\"BaudrateComboBox\" was not injected: check your FXML file 'sample.fxml'.";
        assert ParityComboBox != null : "fx:id=\"ParityComboBox\" was not injected: check your FXML file 'sample.fxml'.";
        assert StopBitComboBox != null : "fx:id=\"StopBitComboBox\" was not injected: check your FXML file 'sample.fxml'.";

        // Добавление имен COM поротов в ComboBOX
        AddComboBoxData();

        // Обработка нажатия кнопки Refresh
        refreshButton.setOnAction(actionEvent -> {
            // Добавление имен COM поротов в ComboBOX
            AddComboBoxData();
        });

        // Обработка нажатия кнопки Accept
        acceptButton.setOnAction(actionEvent -> {

            Connection connection = new Connection(getPortName(),
                    getBaudrate(),
                    getParity(),
                    getStopBit());

            System.out.println(getPortName());
            System.out.println(getBaudrate());
            System.out.println(getParity());
            System.out.println(getStopBit());
        });

    }

    // Получение подключенных COM портов
    public String[] COMPortName() {

        return SerialPortList.getPortNames();
    }

    // Добавление имен COM поротов в ComboBOX
    public void AddComboBoxData() {

        COMNameComboBox.setItems(FXCollections.observableArrayList(COMPortName()));
        if(COMNameComboBox.getItems().isEmpty())
            COMNameComboBox.setValue("No COM port");
        else
            COMNameComboBox.setValue(COMPortName()[0]);

        BaudrateComboBox.setItems(baudList);
        BaudrateComboBox.setValue("19200");

        ParityComboBox.setItems(parityList);
        ParityComboBox.setValue("none");

        StopBitComboBox.setItems(stopBitList);
        StopBitComboBox.setValue("1");
    }

    public String getPortName() {

        return COMNameComboBox.getSelectionModel().getSelectedItem();
    }

    public int getBaudrate() {

        return Integer.parseInt(BaudrateComboBox.getSelectionModel().getSelectedItem());
    }

    public int getParity() {

        return ParityComboBox.getSelectionModel().getSelectedIndex();
    }

    public int getStopBit() {

        return Integer.parseInt(StopBitComboBox.getSelectionModel().getSelectedItem());
    }
}
