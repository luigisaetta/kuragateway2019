package org.eclipse.kura.example.IoTGateway;

import java.io.IOException;

import org.eclipse.kura.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author LSaetta
 *
 *         this class manage the attached LED
 *
 */
public class MyLED
{
	private static final Logger s_logger = LoggerFactory.getLogger(MyLED.class);

	private int pinNum;

	private GPIOService _gpioService = null;
	private KuraGPIOPin outputPin;

	public MyLED(int pinNum)
	{
		this.pinNum = pinNum;
	}

	public void activatePin()
	{
		info("Activate LED pin...");
		
		outputPin = getPin();
	}

	public void setGPIOService(GPIOService gpioService)
	{
		this._gpioService = gpioService;
	}

	public void blink(long delayMs)
	{
		try
		{
			if (outputPin != null)
			{
				info("Blinking LED...");
				
				outputPin.setValue(true);

				Thread.sleep(delayMs);

				outputPin.setValue(false);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * get the pin for LED operations (OUTPUT, ON|OFF)
	 * 
	 */
	private KuraGPIOPin getPin()
	{
		KuraGPIOPin pin = null;

		if (this._gpioService != null)
		{
			pin = this._gpioService.getPinByTerminal(this.pinNum, KuraGPIODirection.OUTPUT,
					KuraGPIOMode.OUTPUT_OPEN_DRAIN, KuraGPIOTrigger.NONE);
			if (pin != null)
			{
				try
				{
					// it is mandatory to open the pin !!!!
					info("Open the LED pin...");
					
					pin.open();
				} catch (KuraGPIODeviceException e)
				{
					e.printStackTrace();
				} catch (KuraUnavailableDeviceException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return pin;
	}

	/*
	 * utility methods for logging
	 * 
	 */
	private static void info(String msg)
	{
		s_logger.info(msg);
	}
}
