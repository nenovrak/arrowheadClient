package eu.arrowhead.client.provider;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;


public class TemperatureReader{
  
  public double readTemperature(){
   
      W1Master w1Master = new W1Master();
    
      for(TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)){	      
          
          if(device.getName().contains("28-")){  //Makes sure the device is the temperature sensor
              return device.getTemperature(TemperatureScale.CELSIUS);
          }
          
      }
      
      return 0;
  }

}
