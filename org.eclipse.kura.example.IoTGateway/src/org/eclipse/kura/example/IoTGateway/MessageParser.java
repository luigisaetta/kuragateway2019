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

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageParser
{
	private static final Logger s_logger = LoggerFactory.getLogger(MessageParser.class);
	
	protected int MIN_LENGTH = 0;
	
	public Message parse(String mess)
	{
		return new Message();
	}
	
	/*
	 * some common utility functions
	 * 
	 */
	/*
	 * does a minimal control on the payload
	 */
	protected boolean isPayloadOK(String s)
	{
		if (s != null && s.length() >= MIN_LENGTH)
			return true;
		else
			return false;
	}
	
	protected String encodeUTF8(String sInput)
	{
		String sOutput = null;

		try
		{
			sOutput = new String(sInput.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return sOutput;
	}
	
	protected static void info(String msg)
	{
		s_logger.info(msg);
	}
	
	protected static void error(String msg)
	{
		s_logger.error(msg);
	}
}
