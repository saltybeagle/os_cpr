/* SVN FILE: $Id: DBTypesHelper.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * DBTypesHelper is a singleton class that is used to load database types.
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
 * @package edu.psu.iam.cpr.core.database.helpers
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date $
 */
public class DBTypesHelper {
	
	/** Contains the instance object for the singleton class */
	private static DBTypesHelper instance =  null;
	
	/** Contains the array of database tables for the various types */
	private static final String[] typeTableNames = { "IdentifierType" };
		
	/** Contains the index values that correspond to the table names from above. */
	public static final int IDENTIFIER_TYPE = 0;
	
	/** Contains the array of hash maps of database types */
	private static final ArrayList<HashMap<String,Object>> typeMaps = new ArrayList<HashMap<String,Object>>();
	
	/** 
	 * This method is used to initialize the singleton class.
	 * @param db contains the current database instance.
	 * @return will return the instance of the singleton.
	 * @throws GeneralDatabaseException will be thrown if there are any database issues.
	 */
	public static synchronized DBTypesHelper getInstance(Database db) throws GeneralDatabaseException {
		
		if (instance == null) {
			instance = new DBTypesHelper();
			try {
				instance.loadDatabaseTypes(db);
			}
			catch (Exception e) {
				throw new GeneralDatabaseException("Unable to load database types.");
			}
		}
		return instance;
	}
	
	/**
	 * This method will return a particular hashmap.
	 * @param index contains the index of the map to be returned.
	 * @return will contain the returned hash map.
	 */
	public synchronized HashMap<String,Object> getTypeMaps(int index) {
		return typeMaps.get(index);
	}
	
	/**
	 * This method is used to load all of the various database types.
	 * @param db contains the database instance.
	 * @throws GeneralDatabaseException will be thrown if there are any problems.
	 */
	private synchronized void loadDatabaseTypes(Database db) throws GeneralDatabaseException {
		
		for (int i = 0; i < typeTableNames.length; ++i) {
			try {
				
				HashMap<String,Object> map = new HashMap<String,Object>();
				final Session session = db.getSession();
				final String sqlQuery = "from " + typeTableNames[i];
				final Query query = session.createQuery(sqlQuery);
				
				switch (i) {
				case IDENTIFIER_TYPE:
					for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
						Object obj = it.next();
						map.put(((IdentifierType) obj).getTypeName(), obj);
					}
					break;
				}
				typeMaps.add(map);
			}
			catch (Exception e) {
				throw new GeneralDatabaseException("Unable to load database types.");
			}
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}
}