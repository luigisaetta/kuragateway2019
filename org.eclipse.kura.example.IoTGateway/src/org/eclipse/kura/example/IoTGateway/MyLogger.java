/*
 * Luigi Saetta
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Dicembre 2019
 * 
 */
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
