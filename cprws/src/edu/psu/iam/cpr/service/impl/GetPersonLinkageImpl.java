/* SVN FILE: $Id: GetPersonLinkageImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonLinkageTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PersonLinkageReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonLinkage;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.returns.PersonLinkageServiceReturn;

/**
 * This class provides an implementation for the get person linkage service.
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
 * @package edu.psu.iam.cpr.service.impl
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class GetPersonLinkageImpl extends GenericGetServiceImpl {
	
	/** Contains the index for the return history parameter */
	private static final int RETURN_HISTORY = 0;

    /**
     * This method is used to execute the core logic for a service.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the service core information.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     */
	@Override
	public Object runService(Database db, ServiceCoreReturn serviceCoreReturn,
			String updatedBy, Object[] otherParameters) throws CprException {
		
		final String returnHistory = (String) otherParameters[RETURN_HISTORY];
		
		// Do the Get.
		final PersonLinkageTable personLinkageTable = ValidatePersonLinkage.validateGetPersonLinkageParameters(db, 
										serviceCoreReturn.getPersonId(), updatedBy, returnHistory);
		
		final PersonLinkageReturn queryResults[] = personLinkageTable.getPersonLinkage(db, serviceCoreReturn.getPersonId());
		
		// Build the return class.
		return (Object) new PersonLinkageServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, queryResults.length, 
				queryResults);
	}

    /**
     * This routine is used to handle exceptions.
     * @param statusCode contains the status code associated with the exception.
     * @param statusMessage contains the error message text.
     * @return will return an service return containing the exception information.
     */
	@Override
	public Object handleException(int statusCode, String statusMessage) {
		return (Object) new PersonLinkageServiceReturn(statusCode, statusMessage);
	}
}
