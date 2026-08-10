package com.abe.backend.measurement;

import com.abe.backend.databaseman.*;
import com.abe.backend.serial.SerialReader;
import com.fazecast.jSerialComm.*;



import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;

import org.springframework.stereotype.*;

@Component
public class Measurement {
    private final PlantReadingService prs;
    SerialReader sr;

    public Measurement(PlantReadingService prs){
        this.prs = prs;
    }



    // Synchronous Reading method
    public void readMeasurements(){
        sr = new SerialReader();
        SerialPort port = sr.getPortObj();

        try (
            InputStream in = port.getInputStream();
            Scanner sc = new Scanner(in);
            // Tutorial code included this but I think its assumign I want to write back through the port which I dont
            OutputStream out = port.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);


        ){

            double humidity;
            double tempF;
            double tempC;

           while(sc.hasNextLine()) {
                String line = sc.nextLine();

                if (!line.startsWith("Data:")) {
                    System.out.println("Ignoring invalid line: " + line);
                    continue;
                }
                line = line.substring(5); // get rid of the 'Data:', using it so we make sure we are only reading complete lines of data not half lines
                String[] response = line.split(",");

                try {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String recorded_at = now.format(formatter);

                    humidity = Double.parseDouble(response[0]);
                    tempF = Double.parseDouble(response[1]);
                    tempC = Double.parseDouble(response[2]);
    
                    prs.saveReading(recorded_at, humidity, tempF, tempC);

                } catch(NumberFormatException e) {
                    System.out.println("Ignoring invalid line: " + line);
                    e.printStackTrace();
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
