/* SVN FILE: $Id: EmailAddressTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
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
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class EmailAddressTable {
	
	/** Email address bean */
	private EmailAddress emailAddressBean;
	
	/** Contains the enumerated type representation of the email address. */	
	private EmailAddressType emailAddressType;
	
	/** Contains a boolean flag that for a GET indicates whether to return history or not */
	private boolean returnHistoryFlag;

	/**
	 * Empty constructor.
	 */
	public EmailAddressTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId Contains the person's primary identifier in the CPR.
	 * @param emailAddressType Contains the email address type represented as a string.
	 * @param emailAddress Contains the email address.
	 * @param updatedBy Contains the system identifier or userid that updated the record.
	 * 
	 * @throws Exception will be thrown if there are any problems. */
	public EmailAddressTable(long personId, String emailAddressType, String emailAddress, String updatedBy) throws Exception {
		
		setEmailAddressType(emailAddressType);
		
		final EmailAddress bean = new EmailAddress();
		setEmailAddressBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getEmailAddressType().index());
		bean.setEmailAddress(emailAddress);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
	}

	/**
	 * Constructor.
	 * @param personId
	 * @param emailAddressType
	 * @param updatedBy
	 * @throws Exception */
	public EmailAddressTable(long personId, String emailAddressType, String updatedBy) throws Exception {
		this(personId, emailAddressType, null, updatedBy);
	}
	
	/**
	 * @param returnHistoryFlag the returnHistoryFlag to set
	 */
	public void setReturnHistoryFlag(boolean returnHistoryFlag) {
		this.returnHistoryFlag = returnHistoryFlag;
	}

	/**
	 * @return the returnHistoryFlag
	 */
	public boolean isReturnHistoryFlag() {
		return returnHistoryFlag;
	}

	/**
	 * @param emailAddressType the emailAddressType to set
	 */
	public final void setEmailAddressType(EmailAddressType emailAddressType) {
		this.emailAddressType = emailAddressType;
	}
	
	/**
	 * @param emailAddressType to be set.
	 */
	public final void setEmailAddressType(String emailAddressType) {
		setEmailAddressType(EmailAddressType.valueOf(emailAddressType.toUpperCase().trim())); 
	}

	/**
	 * @return the emailAddressType */
	public EmailAddressType getEmailAddressType() {
		return emailAddressType;
	}

	/**
	 * @param emailAddressBean the emailAddressBean to set
	 */
	public final void setEmailAddressBean(EmailAddress emailAddressBean) {
		this.emailAddressBean = emailAddressBean;
	}

	/**
	 * @return the emailAddressBean */
	public EmailAddress getEmailAddressBean() {
		return emailAddressBean;
	}

	
	/**
	 * The purpose of this routine is to interface with the database with JDBC to 
	 * call a stored function to add an email address to a user's record.  The 
	 * information necessary to add the return is passed in the EmailAddress class.
	 * 
	 * @param db Database
	 * 
	 * @throws GeneralDatabaseException exception indicates that a general database failure was encountered. 
	 * @throws CprException  */
	public void addAddress(Database db) throws GeneralDatabaseException, CprException {
		
		boolean matchFound = false;
		try {
			final Session session = db.getSession();
			final EmailAddress bean = getEmailAddressBean();
			
			// Expire the existing one.
			final String sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key AND endDate is NULL";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
				EmailAddress dbBean = (EmailAddress) it.next();
				if ( db.areStringFieldsEqual(dbBean.getEmailAddress(), bean.getEmailAddress())) {
					matchFound = true;
				}
				else {
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
			// If we did not find a match, we can add the record.
			if (! matchFound) {
				session.save(bean);
				session.flush();
			}			

		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "email address");
		}
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "email address");
		}		
	}
	
	/**
	 * The purpose of this routine is to interface with the database with JDBC to 
	 * call a stored function to update an email address to a user's record.  The 
	 * information necessary to update the record is passed in the EmailAddress class.
	 * @param db Database
	 * 
	 * @throws GeneralDatabaseException exception indicates that a general database error was encountered. 
	 * @throws CprException  */
	public void updateAddress(Database db) throws GeneralDatabaseException, CprException {
		
		boolean matchFound = false;
		try {
			final Session session = db.getSession();
			final EmailAddress bean = getEmailAddressBean();
			
			// Expire the existing one.
			final String sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key AND endDate is NULL";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
				EmailAddress dbBean = (EmailAddress) it.next();
				if ( db.areStringFieldsEqual(dbBean.getEmailAddress(), bean.getEmailAddress())) {
					matchFound = true;
				}
				else {
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
			// If we did not find a match, we can add the record.
			if (! matchFound) {
				session.save(bean);
				session.flush();
			}			

		}
		catch (Exception e) {
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, "email address");
		}
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "email address");
		}
		
	}	
	
	/**
	 * The purpose of this routine is to delete (archive) an email address for a user.
	 * @param db Database
	 * 
	 * @throws GeneralDatabaseException exception will be thrown if their is a general database failure. 
 	 * @throws CprException  */
	public void archiveEmailAddress(Database db) throws GeneralDatabaseException, CprException {

		boolean recordNotFound = false;
		boolean holdNotActive = false;
		try {
			final Session session = db.getSession();
			final EmailAddress bean = getEmailAddressBean();
			
			String sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				
				sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key and endDate is NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());

				it = query.list().iterator();
				
				// Active email address exists, so we expire it.
				if (it.hasNext()) {
					EmailAddress dbBean = (EmailAddress) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
				else {
					holdNotActive = true;
				}
			}
			else {
				recordNotFound = true;
			}
			
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "email address");
		}
		
		// Handle the error cases.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "email address");
		}
		
		if (holdNotActive) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "email address");
		}		

	}
	
	/**
	 * This routine is used to retrieve the list of email addresses for a person.
	 * @param db contains the database connection.
	 * @param personId contains the person id.
	 * @return will return a list of email addresses.
	 * @throws GeneralDatabaseException 
	 * @throws CprException  
	 */
	public EmailAddressReturn[] getEmailAddressForPersonId(Database db, long personId) throws GeneralDatabaseException, CprException {
		
		try {
			final ArrayList<EmailAddressReturn> results = new ArrayList<EmailAddressReturn>();
			final Session session = db.getSession();
			
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, email_address, " );
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM email_address ");
			sb.append("WHERE person_id=:person_id ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id", personId);
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("email_address", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
		
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
				emailAddressReturn.setEmailAddressType(EmailAddressType.get((Long) res[0]).toString());
				emailAddressReturn.setEmailAddress((String) res[1]);
				emailAddressReturn.setStartDate(Utility.convertTimestampToString((Date) res[2]));
				emailAddressReturn.setEndDate(Utility.convertTimestampToString((Date) res[3]));
				emailAddressReturn.setLastUpdateBy((String) res[4]);
				emailAddressReturn.setLastUpdateOn(Utility.convertTimestampToString((Date) res[5]));
				emailAddressReturn.setCreatedBy((String) res[6]);
				emailAddressReturn.setCreatedOn(Utility.convertTimestampToString((Date) res[7]));
				results.add(emailAddressReturn);
			}
			
			return results.toArray(new EmailAddressReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve email addresses for person identifier = " + personId);
		}
	}
	
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "EmailAddressTable";
	}
}
