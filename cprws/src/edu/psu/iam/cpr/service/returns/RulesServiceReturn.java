/* SVN FILE: $Id: RulesServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

/**
 * Provides the implementation of the RulesServiceReturn class.
 * 
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class RulesServiceReturn {

	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/**  Status message returned as the result of executing this service. */
	private String statusMessage;

	/**  Contains the number of facts returned from the service. */
	private int numberOfFacts = 0;
	
	/** Contains the facts return data. */
	private String facts[] = null;

	/**
	 * Constructor
	 */
	public RulesServiceReturn() {
		super();
	}

	/**
	 * Constructor
	 * @param statusCode return status that is the result of executing this service.
	 * @param statusMessage return message that is the result of executing this service.
	 * @param numberOfFacts contains the number of facts that are returned to the caller.
	 * @param factsArray contains the facts array.
	 */
	public RulesServiceReturn(int statusCode, String statusMessage,
			int numberOfFacts, String[] factsArray) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberOfFacts = numberOfFacts;
		this.facts = Arrays.copyOf(factsArray, factsArray.length);
	}

	/**
	 * Constructor
	 * @param statusCode return status that is the result of executing this service.
	 * @param statusMessage return message that is the result of executing this service.
	 */
	public RulesServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the numberOfFacts
	 */
	public int getNumberOfFacts() {
		return numberOfFacts;
	}

	/**
	 * @param numberOfFacts the numberOfFacts to set
	 */
	public void setNumberOfFacts(int numberOfFacts) {
		this.numberOfFacts = numberOfFacts;
	}

	/**
	 * @return the facts
	 */
	public String[] getFacts() {
		return facts;
	}

	/**
	 * @param factsArray the facts to set
	 */
	public void setFacts(String[] factsArray) {
		this.facts = Arrays.copyOf(factsArray, factsArray.length);
	}
	
}
