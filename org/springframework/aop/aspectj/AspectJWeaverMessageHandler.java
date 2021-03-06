/*
 * Copyright 2002-2006 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.aop.aspectj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.bridge.AbortException;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.IMessageHandler;
import org.aspectj.bridge.IMessage.Kind;

/**
 * Implementation of AspectJ's IMessageHandler
 * interface that routes AspectJ weaving messages
 * through the same Logging system as the regular
 * Spring messages.
 * 
 * Pass the option 
 * "-XmessageHandlerClass:org.springframework.aop.aspectj.AspectJWeaverMessageHandler"
 * to the weaver (for example, using
 * &lt;weaver options="..."/&gt in a META-INF/aop.xml
 * file).
 */
public class AspectJWeaverMessageHandler implements IMessageHandler {

	private static final String AJ_ID = "[AspectJ] ";
	
	private final Log logger = LogFactory.getLog("AspectJ Weaver");
	
	public boolean handleMessage(IMessage message) throws AbortException {
		Kind messageKind = message.getKind();

		if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
			if (messageKind == IMessage.DEBUG) {
				logger.debug(makeMessageFor(message));
				return true;
			}
		} 
		
		if (logger.isInfoEnabled()) {
			if ((messageKind == IMessage.INFO) || (messageKind == IMessage.WEAVEINFO)) {
				logger.info(makeMessageFor(message));
				return true;
			}
		} 
		
		if (logger.isWarnEnabled()) {
			if (messageKind == IMessage.WARNING) {
				logger.warn(makeMessageFor(message));
				return true;
			}
		}
		
		if (logger.isErrorEnabled()) {
			if (messageKind == IMessage.ERROR) {
				logger.error(makeMessageFor(message));
				return true;
			}
		}
		
		if (logger.isFatalEnabled()) {
			if (messageKind == IMessage.ABORT) {
				logger.fatal(makeMessageFor(message));
				return true;
			}
		}
		
		return false;
	}
	
	private String makeMessageFor(IMessage aMessage) {
		return AJ_ID + aMessage.getMessage();
	}

	public boolean isIgnoring(Kind messageKind) {
		// we want to see everything, and allow configuration of log levels
		// dynamically
		return false;
	}

	public void dontIgnore(Kind messageKind) {
		// we weren't ignoring anything anyway...
	}
	
}
