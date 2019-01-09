package eu.arrowhead.client.provider;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;


public class TemperatureReader{
  
  public double readTemperature(){
    W1Master w1Master = new W1Master();
    for(TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)){
	if(device.getName().contains("28-0000093012c1"))
		return device.getTemperature(TemperatureScale.CELSIUS);
    }
    return 0;
  }

}
