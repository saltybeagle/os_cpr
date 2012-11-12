/* SVN FILE: $Id: SetPrimaryPhoneByTypeImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidatePhone;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.ServiceReturn;

/**
 * This class provides an implementation for the set phone by type service.
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
public class SetPrimaryPhoneByTypeImpl implements ServiceInterface {

	private static final Logger LOG4J_LOGGER = Logger.getLogger(SetPrimaryPhoneByTypeImpl.class);
	private static final int BUFFER_SIZE = 2048;
	private static final int PHONE_TYPE = 0;
	private static final int GROUP_ID = 1;
	
	/**
	 * This method provides the implementation for a service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the ip address of the caller. 
	 * @param principalId contains the principal identifier that is used to authenticate the service.
	 * @param password contains the password associated with the principal.
	 * @param updatedBy contains the user that either updated or requested information.
	 * @param identifierType contains the identifier type used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param otherParameters contains an array of Objects that are additional parameters to the service implementation.
	 * @return Object will return an object will needed to be casted to obtain the real return.
	 */

	public Object implementService(String serviceName, String ipAddress,
			String principalId, String password, String updatedBy,
			String identifierType, String identifier, Object[] otherParameters) {
		
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		LOG4J_LOGGER.info("SetPrimaryByType: Start of service.");
		try {
			
			final String phoneType = (String) otherParameters[PHONE_TYPE];
			final Long groupId = (Long) otherParameters[GROUP_ID];
			
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("phoneType=[").append(phoneType).append("] ");
			parameters.append("groupId=[").append(groupId).append("] "); 
			LOG4J_LOGGER.info("SetPrimaryByType: Input Parameters = " + parameters.toString());			
			
			// Init the service.
			serviceCoreReturn = serviceHelper.initializeService(serviceName, 
					ipAddress,
					principalId,
					password,
					updatedBy,
					identifierType, 
					identifier,
					serviceCore, 
					db, 
					parameters);
			LOG4J_LOGGER.info("SetPrimaryByType: Found Person Id = " + serviceCoreReturn.getPersonId());
			
			// Validate the data passed to the service
			final PhonesTable phonesTableRecord = ValidatePhone.validateArchiveAndSetPrimaryPhonesParameters(db, 
					serviceCoreReturn.getPersonId(), phoneType,  groupId, updatedBy);
			
			phonesTableRecord.setPrimaryByType(db);
			
			// Create a new json message.
			final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName,updatedBy);
			jsonMessage.setPhone(phonesTableRecord);
			
			serviceHelper.sendMessagesToServiceProviders(serviceName, db, jsonMessage); 
			
			// Log Success
			LOG4J_LOGGER.info("SetPrimaryByType: Status = SUCCESS, primary flag set");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		} 
		catch (JSONException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JSON_EXCEPTION.index(), e.getMessage());
		} 
		catch (JMSException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JMS_EXCEPTION.index(), e.getMessage());
		}
		catch (RuntimeException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}
		
		LOG4J_LOGGER.info("SetPrimaryByType: End of service.");

		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE);
	}

}
