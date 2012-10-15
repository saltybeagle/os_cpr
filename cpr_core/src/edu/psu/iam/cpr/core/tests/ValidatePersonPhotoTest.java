package edu.psu.iam.cpr.core.tests;

/**
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
 */


import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidatePersonPhoto;

public class ValidatePersonPhotoTest {

	private static Database db = new Database();

	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters1() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateAddPhotoParameters(null, null, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters2() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters3() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters4() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/2011", null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddPhotoParameters5() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/", "jvuccolo");
	}
	
	@Test
	public final void testValidateAddPhotoParameters6() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonPhoto.validateAddPhotoParameters(db, 1L, new byte[1], "1/1/2011", "jvuccolo");
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters1() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateGetPhotoParameters(null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters2() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetPhotoParameters3() throws CprException, GeneralDatabaseException {
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, "");
	}
	
	@Test
	public final void testValidateGetPhotoParameters4() throws CprException, GeneralDatabaseException {
		openDbConnection();
		ValidatePersonPhoto.validateGetPhotoParameters(db, 1L, "jvuccolo");
		db.closeSession();
	}

}