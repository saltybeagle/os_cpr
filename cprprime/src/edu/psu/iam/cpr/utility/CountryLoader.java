package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.Country;

public class CountryLoader implements BeanLoader {

	@Override
	public void loadTable(Database db, String primeDirectory, String tableName) {
		BufferedReader bufferedReader = null;
		try {
			Date d = new Date();
			String requestor = "SYSTEM";
			
			db.openSession(SessionFactoryUtil.getSessionFactory());
			Session session = db.getSession();
			
			// Remove all of the records from the database table.
			String sqlQuery = "delete from " + tableName;
			Query query = session.createQuery(sqlQuery);
			query.executeUpdate();

			// Read in the first record containing the column headers.
			bufferedReader = new BufferedReader(new FileReader(primeDirectory + System.getProperty("file.separator") + tableName));
			String[] columns = bufferedReader.readLine().split("[|]");
			String line;
			
			// Read and process the file.
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				Country bean = new Country();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				bean.setStartDate(d);
				bean.setEndDate(null);
				
				// country_full_name|country|us_territory_flag|country_numeric_code|country_key|country_code_three|country_code_two

				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("country_full_name")) {
						bean.setCountryFullName(fields[i]);
					}
					else if (columns[i].equals("country")) {
						bean.setCountry(fields[i]);
					}
					else if (columns[i].equals("us_territory_flag")) {
						bean.setUsTerritoryFlag(fields[i]);
					}
					else if (columns[i].equals("country_numeric_code")) {
						bean.setCountryNumericCode(fields[i]);
					}
					else if (columns[i].equals("country_key")) {
						bean.setCountryKey(new Long(fields[i]));
					}
					else if (columns[i].equals("country_code_three")) {
						bean.setCountryCodeThree(fields[i]);
					}
					else if (columns[i].equals("country_code_two")) {
						bean.setCountryCodeTwo(fields[i]);
					}
				}
				
				session.save(bean);
			}
			
			db.closeSession();
		}
		catch (Exception e) {
			db.rollbackSession();
			e.printStackTrace();
		}
		finally {
			try {
				bufferedReader.close();
			}
			catch (Exception e) {
			}
		}

	}

}
