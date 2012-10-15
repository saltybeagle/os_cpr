/* SVN FILE: $Id: KnownFact.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.rules.engine;

/**
 * Provides the implementation of the KnownFact class.
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
 * @package edu.psu.iam.cpr.core.rules.engine
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class KnownFact {
	
	/** Contains the known fact value */
	private String fact;

	/**
	 * Constructor
	 * @param fact
	 */
	public KnownFact(String fact) {
		super();
		this.setFact(fact);
	}

	/**
	 * Constructor
	 */
	public KnownFact() {
		super();
	}

	/**
	 * @param fact the fact to set
	 */
	public void setFact(String fact) {
		this.fact = fact;
	}

	/**
	 * @return the fact
	 */
	public String getFact() {
		return fact;
	}
}