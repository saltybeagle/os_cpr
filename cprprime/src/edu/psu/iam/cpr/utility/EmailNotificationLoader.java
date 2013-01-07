package edu.psu.iam.cpr.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.utility.beans.EmailNotification;

public class EmailNotificationLoader implements BeanLoader {

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
			
			// mail_notification_key|email_subject|text_body|html_body|notification_process
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("[|]");
				
				EmailNotification bean = new EmailNotification();
				bean.setCreatedBy(requestor);
				bean.setCreatedOn(d);
				bean.setLastUpdateBy(requestor);
				bean.setLastUpdateOn(d);
				
				for (int i = 0; i < columns.length; ++i) {
					if (columns[i].equals("mail_notification_key")) {
						bean.setMailNotificationKey(new Long(fields[i]));
					}
					else if (columns[i].equals("email_subject")) {
						bean.setEmailSubject(fields[i]);
					}
					else if (columns[i].equals("text_body")) {
						bean.setTextBody(fields[i]);
					}
					else if (columns[i].equals("html_body")) {
						bean.setHtmlBody(fields[i]);
					}
					else if (columns[i].equals("notification_process")) {
						bean.setNotificationProcess(fields[i]);
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