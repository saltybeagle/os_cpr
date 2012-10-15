/* SVN FILE: $Id: ValidatePersonAffiliation.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
  This class provides an implementation of functions that perform affiliation information validation. 
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
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
package edu.psu.iam.cpr.core.util;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * This class is responsible for the validation of the Person Affiliation data.
 * @author llg5
 *
 */
public class ValidatePersonAffiliation {


	/**
	 * This routine is used to validate the parameters to the AddAffiliation, SetPrimaryAffiliation and UpdateAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param affiliation contains the name type to be deleted.
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @return PersonAffiliationTable class.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateAddAffiliationParameters(Database db, long personId, String affiliation,  String updatedBy) throws GeneralDatabaseException, CprException {
		
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		if (affiliation == null || affiliation.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Affiliation");
		}
		
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
	
		db.getAllTableColumns("PERSON_AFFILIATION");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}

		PersonAffiliationTable pARTable = null;
		try {
			pARTable = new PersonAffiliationTable(personId, affiliation, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Affiliation");
		}
		return pARTable;
	}

	/**
	 * This routine is used to validate the parameters to the ArchiveAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param affiliation contains the name type to be deleted
	 * @param updatedBy contains the userid or server identifier of the user who is requesting the delete.
	 * @return PersonAffilationTable class.
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateArchiveAffiliationParameters(Database db, long personId, String affiliation,  String updatedBy) throws GeneralDatabaseException, CprException {
		
		updatedBy = (updatedBy != null) ? updatedBy.trim() : null;
		
		if (affiliation == null || affiliation.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Affiliation");
		}
		
		
		if (updatedBy == null || updatedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Updated by");
		}
	
		db.getAllTableColumns("PERSON_AFFILIATION");
		if (updatedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Updated by");
		}

		PersonAffiliationTable pARTable = null;
		try {
			pARTable = new PersonAffiliationTable(personId, affiliation, updatedBy);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Affiliation");
		}
		return pARTable;
	}
	
	/**
	 * This routine is used to validate the parameters to the GetInternalAffiliation  service
	 * @param db contains the reference to the database connection
	 * @param personId contains the user's person identifier.
	 * @param requestedBy contains the userid or server identifier of the user who is requesting the delete.'
	 * @param returnHistory Y/N flag that indicates whether to return history or not.
	 * @return PersonAffiliationTable
	 * @throws GeneralDatabaseException 
	 * @throws CprException 
	 * 
	 */
	public static PersonAffiliationTable validateGetAffiliationsForPersonIdParameters(Database db, long personId,  String requestedBy, String returnHistory) throws  GeneralDatabaseException, CprException {
		
		requestedBy = (requestedBy != null) ? requestedBy.trim() : null;
		returnHistory = (returnHistory != null) ? returnHistory.trim() : null;
		
		if (requestedBy == null || requestedBy.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Requested by");
		}
	
		db.getAllTableColumns("PERSON_AFFILIATION");
		if (requestedBy.length() > db.getColumn("LAST_UPDATE_BY").getColumnSize()) {
			throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, "Requested by");
		}

		final PersonAffiliationTable personAffiliationTable = new PersonAffiliationTable();
		
		if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		personAffiliationTable.setReturnHistoryFlag((returnHistory.equals("Y")) ? true : false);
		
		return personAffiliationTable;
		
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode());
	}
}