package org.eclipse.kura.example.IoTGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger
{
	private static final Logger s_logger = LoggerFactory.getLogger(MyLogger.class);
	/*
	 * utility methods for logging
	 * 
	 */
	private static void info(String msg)
	{
		s_logger.info(msg);
	}

	private static void debug(String msg)
	{
		s_logger.debug(msg);
	}

	private static void error(String msg)
	{
		s_logger.error(msg);
	}
}
