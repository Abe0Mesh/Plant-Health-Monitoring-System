package com.abe.backend.serial;

import com.fazecast.jSerialComm.SerialPort; 

public class SerialReader {
    
    private SerialPort port; 

    public SerialReader() {
        this.port = initializePortConnection("/dev/cu.usbmodem1101");// add comm name when finding it 
    }

    public SerialPort getPortObj(){
        return this.port;
    }

    SerialPort initializePortConnection(String portName) {
        SerialPort port = SerialPort.getCommPort(portName); 
        port.setBaudRate(9600);
        port.setNumDataBits(8);
        port.setNumStopBits(SerialPort.ONE_STOP_BIT);
        port.setParity(SerialPort.NO_PARITY);

        port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0); // Yeahhhh so I originally skipped this line and I stopped getting data after 5 lines....
        if (port.openPort()) {
            System.out.println("Port opened: " + port.getSystemPortName());
        }
        else {
            System.out.println("Port opening was not successful");
            System.exit(420);
        }
        return port;
    }
}
