/* SVN FILE: $Id: PsuIdReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing a GetPerson service call.
 * This data is returned as an array to the caller.
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
 * @package edu.psu.iam.cpr.core.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class PsuIdReturn {
	
	/** Penn State Id Number. */
	private String psuId;

	/** Contains the start date */
	private String startDate;
	
	/** Contains the end date for the record */
	private String endDate;
	
	/** Contains the last update person */
	private String lastUpdateBy;
	
	/** Contains the date the record was last updated on */
	private String lastUpdateOn;
	
	/** Contains the person who created the record */
	private String createdBy;
	
	/** Contains the date the record was created on */
	private String createdOn;
	
	/**
	 * Constructor
	 */
	public PsuIdReturn() {
		super();
	}
	
	/**
	 * Constructor
	 * @param psuId
	 */
	public PsuIdReturn(String psuId) {
		this(psuId, null, null, null, null, null, null);
	}
	
	/**
	 * Constructor
	 * @param psuId
	 * @param startDate
	 * @param endDate
	 * @param lastUpdateBy
	 * @param lastUpdateOn
	 * @param createdBy
	 * @param createdOn
	 */
	public PsuIdReturn(String psuId, String startDate, String endDate, String lastUpdateBy, String lastUpdateOn,
							String createdBy, String createdOn) {
		super();
		this.psuId = psuId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	
	/**
	 * @param psuId the psuId to set
	 */
	public void setPsuId(String psuId) {
		this.psuId = psuId;
	}

	/**
	 * @return the psuId
	 */
	public String getPsuId() {
		return psuId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the lastUpdateBy
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * @param lastUpdateBy the lastUpdateBy to set
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * @return the lastUpdateOn
	 */
	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	/**
	 * @param lastUpdateOn the lastUpdateOn to set
	 */
	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
