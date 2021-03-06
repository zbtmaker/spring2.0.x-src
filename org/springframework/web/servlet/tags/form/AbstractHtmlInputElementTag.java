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

package org.springframework.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

/**
 * Base class for databinding-aware JSP tags that render HTML form input element.
 * 
 * <p>Provides a set of properties corresponding to the set of HTML attributes
 * that are common across form input elements.
 * 
 * @author Rob Harrop
 * @since 2.0
 */
public abstract class AbstractHtmlInputElementTag extends AbstractHtmlElementTag {

	/**
	 * The name of the '<code>onfocus</code>' attribute.
	 */
	public static final String ONFOCUS_ATTRIBUTE = "onfocus";

	/**
	 * The name of the '<code>onblur</code>' attribute.
	 */
	public static final String ONBLUR_ATTRIBUTE = "onblur";

	/**
	 * The name of the '<code>onchange</code>' attribute.
	 */
	public static final String ONCHANGE_ATTRIBUTE = "onchange";

	/**
	 * The name of the '<code>accesskey</code>' attribute.
	 */
	public static final String ACCESSKEY_ATTRIBUTE = "accesskey";

	/**
	 * The name of the '<code>disabled</code>' attribute.
	 */
	public static final String DISABLED_ATTRIBUTE = "disabled";


	/**
	 * The value of the '<code>onfocus</code>' attribute.
	 */
	private String onfocus;

	/**
	 * The value of the '<code>onblur</code>' attribute.
	 */
	private String onblur;

	/**
	 * The value of the '<code>onchange</code>' attribute.
	 */
	private String onchange;

	/**
	 * The value of the '<code>accesskey</code>' attribute.
	 */
	private String accesskey;

	/**
	 * The value of the '<code>disabled</code>' attribute.
	 */
	protected String disabled;


	/**
	 * Sets the value of the '<code>onfocus</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	/**
	 * Gets the value of the '<code>onfocus</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnfocus() {
		return this.onfocus;
	}

	/**
	 * Sets the value of the '<code>onblur</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}


	/**
	 * Gets the value of the '<code>onblur</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnblur() {
		return this.onblur;
	}

	/**
	 * Sets the value of the '<code>onchange</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	/**
	 * Gets the value of the '<code>onchange</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getOnchange() {
		return this.onchange;
	}

	/**
	 * Sets the value of the '<code>accesskey</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	/**
	 * Gets the value of the '<code>accesskey</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getAccesskey() {
		return this.accesskey;
	}

	/**
	 * Sets the value of the '<code>disabled</code>' attribute.
	 * May be a runtime expression.
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * Gets the value of the '<code>disabled</code>' attribute.
	 * May be a runtime expression.
	 */
	protected String getDisabled() {
		return disabled;
	}


	/**
	 * Writes the default attributes configured via this base class to the supplied {@link TagWriter}.
	 * Subclasses should call this when they want the base attribute set to be written to the output.
	 */
	protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
		super.writeDefaultAttributes(tagWriter);
		writeOptionalAttribute(tagWriter, ONFOCUS_ATTRIBUTE, getOnfocus());
		writeOptionalAttribute(tagWriter, ONBLUR_ATTRIBUTE, getOnblur());
		writeOptionalAttribute(tagWriter, ONCHANGE_ATTRIBUTE, getOnchange());
		writeOptionalAttribute(tagWriter, ACCESSKEY_ATTRIBUTE, getAccesskey());
		if(isDisabled()) {
			tagWriter.writeAttribute(DISABLED_ATTRIBUTE, "disabled");
		}
	}

	/**
	 * Is the current HTML tag disabled?
	 */
	protected boolean isDisabled() {
		return "true".equals(getDisabled());
	}

}
