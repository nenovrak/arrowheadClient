package eu.arrowhead.client.provider;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
import java.io.*;


public class TemperatureReader{
  
  public double readTemperature(){
      
      try{
        Runtime.getRuntime().exec("sudo modprobe w1-gpio");
        Runtime.getRuntime().exec("sudo modprobe w1-therm");
      }catch(Exception e){
        System.out.println("You need to run: 'sudo modprobe w1-gpio' AND 'sudo modprobe w1-therm' manually before running startUp.sh");
      }
   
      W1Master w1Master = new W1Master();
    
      for(TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)){	      
          
          if(device.getName().contains("28-")){  //Makes sure the device is the temperature sensor
              return device.getTemperature(TemperatureScale.CELSIUS);
          }
          
      }
      
      return 0;
  }

}
