package edu.psu.iam.cpr.core.api;

import java.text.ParseException;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.IdCardServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.tables.PersonPhotoTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCard;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn;
import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

/**
 * This class provides the implementation for the Get Id Card API.
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
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class GetIdCardApi extends ExtendedBaseApi {

	/** Contains the index for the id card type parameter */
	private static final int ID_CARD_TYPE = 0;
	
	/** Contins the index of the return history parameter */
	private static final int RETURN_HISTORY = 1;
	
   /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
 * @throws CprException will be thrown if there are any problems.
 * @throws JSONException will be thrown if there are any issues creating a JSON message.
 * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public Object runApi(String apiName, Database db, ServiceCoreReturn serviceCoreReturn,
			String updatedBy, Object[] otherParameters,
			boolean checkAuthorization) throws CprException, JSONException,
			ParseException, JMSException {
		
		final String idCardType 	= (String) otherParameters[ID_CARD_TYPE];
		final String returnHistory 	= (String) otherParameters[RETURN_HISTORY];
		final long personId = serviceCoreReturn.getPersonId();
		
		IdCardTable idCardTable = ValidateIdCard.validateGetIdCardParameters(db, personId, 
						updatedBy, idCardType, returnHistory);

		final PersonIdCardReturn queryResults[] = idCardTable.getIdCardForPersonId(db, personId); 
		final PhotoReturn photoReturn[] = new PersonPhotoTable().getPhoto(db, personId);

		// Build the return class.
		return (Object) new IdCardServiceReturn(ReturnType.SUCCESS.index(),
				ApiHelper.SUCCESS_MESSAGE,
				queryResults,
				queryResults.length,
				photoReturn);
	}

}
