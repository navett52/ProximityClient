package navett52.proximity.client;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("<-- Proximity Cient ... Started -->");
		
		//creating the gpio controller.
		final GpioController gpio = GpioFactory.getInstance();
		
		//Provision gpio pin #14 as an input pin with it's internal pull down resistor enabled.
		final GpioPinDigitalInput sensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14, PinPullResistance.PULL_DOWN);
		
		//Set shutdown state for this pin.
		sensor.setShutdownOptions(true);
		
		//Create and register gpio pin listener
		sensor.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event){
				//Display pin state on console.
				System.out.println("--> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
			}
		});
		
		System.out.println("... complete the GPIO #14 circuit and see the listener for feedback here in console.");
		
		//Keep program running until user aborts.
		while(true)
		{
			Thread.sleep(500);
		}
		
	}

}
