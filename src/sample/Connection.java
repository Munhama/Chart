package sample;

public class Connection {

    private String nameCOM;
    private int baudrate;
    private int parity;
    private int stopBit;

    public Connection (String nameCOM,
                       int baudrate,
                       int parity,
                       int stopBit) {
        this.nameCOM = nameCOM;
        this.baudrate = baudrate;
        this.parity = parity;
        this.stopBit = stopBit;


    }

}
