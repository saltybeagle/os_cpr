/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */

@Entity
@Table(name="ra_screens")
public class RaScreens implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the user id or system identifier that created the record. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the date and time that the record was last updated. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the date and time that the record was created. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the screen order chosen by the registration authority for the application */
        @Column(name="ra_screen_order", nullable=false)
        private Long raScreenOrder;

        /** Contains the user id or system identifier that last updated the record. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the name of a user interface screen. */
        @Column(name="ui_screen_name", nullable=false, length=30)
        private String uiScreenName;

        /** Contains a unique number that identifies a registration authority screen record.  It is populated by the seq_ra_screen sequence. */
        @Id
        @Column(name="ra_screen_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_ra_screens")
        @SequenceGenerator(name="seq_ra_screens", sequenceName="seq_ra_screens", allocationSize = 1, initialValue= 1)
        private Long raScreenKey;

        /** Contains a unique number that identifies a registration authority application. */
        @Column(name="ra_application_key", nullable=false)
        private Long raApplicationKey;

        /**
         * Constructor
         */
        public RaScreens() {
            super();
        }

        /**
         * @return the createdBy
         */
        public String getCreatedBy() {
                return createdBy;
        }

        /**
         * @param createdBy the createdBy to set.
         */
        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        /**
         * @return the lastUpdateOn
         */
        public Date getLastUpdateOn() {
                return lastUpdateOn;
        }

        /**
         * @param lastUpdateOn the lastUpdateOn to set.
         */
        public void setLastUpdateOn(Date lastUpdateOn) {
                this.lastUpdateOn = lastUpdateOn;
        }

        /**
         * @return the createdOn
         */
        public Date getCreatedOn() {
                return createdOn;
        }

        /**
         * @param createdOn the createdOn to set.
         */
        public void setCreatedOn(Date createdOn) {
                this.createdOn = createdOn;
        }

        /**
         * @return the raScreenOrder
         */
        public Long getRaScreenOrder() {
                return raScreenOrder;
        }

        /**
         * @param raScreenOrder the raScreenOrder to set.
         */
        public void setRaScreenOrder(Long raScreenOrder) {
                this.raScreenOrder = raScreenOrder;
        }

        /**
         * @return the lastUpdateBy
         */
        public String getLastUpdateBy() {
                return lastUpdateBy;
        }

        /**
         * @param lastUpdateBy the lastUpdateBy to set.
         */
        public void setLastUpdateBy(String lastUpdateBy) {
                this.lastUpdateBy = lastUpdateBy;
        }

        /**
         * @return the uiScreenName
         */
        public String getUiScreenName() {
                return uiScreenName;
        }

        /**
         * @param uiScreenName the uiScreenName to set.
         */
        public void setUiScreenName(String uiScreenName) {
                this.uiScreenName = uiScreenName;
        }

        /**
         * @return the raScreenKey
         */
        public Long getRaScreenKey() {
                return raScreenKey;
        }

        /**
         * @param raScreenKey the raScreenKey to set.
         */
        public void setRaScreenKey(Long raScreenKey) {
                this.raScreenKey = raScreenKey;
        }

        /**
         * @return the raApplicationKey
         */
        public Long getRaApplicationKey() {
                return raApplicationKey;
        }

        /**
         * @param raApplicationKey the raApplicationKey to set.
         */
        public void setRaApplicationKey(Long raApplicationKey) {
                this.raApplicationKey = raApplicationKey;
        }

}
