package com.abe.backend.measurement;

import com.abe.backend.serial.SerialReader;
import com.fazecast.jSerialComm.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Measurement {
    SerialReader sr;
    // Synchronous Reading method
    public void syncPortReader(){
        sr = new SerialReader();
        SerialPort port = sr.getPortObj();

        try (
            InputStream in = port.getInputStream();
            Scanner sc = new Scanner(in);
            // Tutorial code included this but I think its assumign I want to write back through the port which I dont
            OutputStream out = port.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);


        ){
            while (true) {
                if(sc.hasNextLine()) {
                    String response = sc.nextLine();
                    System.out.println("Received: " + response);
                }

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            port.closePort();
            System.out.println("\nPort Closed");
        }
    }
    
}
