/* SVN FILE: $Id: ServiceProvisioner.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * @package edu.psu.iam.cpr.core.database.beans
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

@Entity
@Table(name="service_provisioner")
public class ServiceProvisioner implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains the serviceProvisionerEmail. */
        @Column(name="service_provisioner_email", nullable=true, length=256)
        private String serviceProvisionerEmail;

        /** Contains the serviceProvisionerKey. */
        @Id
        @Column(name="service_provisioner_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_service_provisioner")
        @SequenceGenerator(name="seq_service_provisioner", sequenceName="seq_service_provisioner", allocationSize = 1, initialValue= 1)
        private Long serviceProvisionerKey;

        /** Contains the serviceProvisionerQueue. */
        @Column(name="service_provisioner_queue", nullable=true, length=128)
        private String serviceProvisionerQueue;

        /** Contains the retryInterval. */
        @Column(name="retry_interval", nullable=true)
        private Long retryInterval;

        /** Contains the lastUpdateBy. */
        @Column(name="last_update_by", nullable=false, length=30)
        private String lastUpdateBy;

        /** Contains the endDate. */
        @Column(name="end_date", nullable=true)
        private Date endDate;

        /** Contains the startDate. */
        @Column(name="start_date", nullable=false)
        private Date startDate;

        /** Contains the createdBy. */
        @Column(name="created_by", nullable=false, length=30)
        private String createdBy;

        /** Contains the lastUpdateOn. */
        @Column(name="last_update_on", nullable=false)
        private Date lastUpdateOn;

        /** Contains the createdOn. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains the maximumFailure. */
        @Column(name="maximum_failure", nullable=true)
        private Long maximumFailure;

        /** Contains the serviceProvisionerRegDate. */
        @Column(name="service_provisioner_reg_date", nullable=false)
        private Date serviceProvisionerRegDate;

        /** Contains the serviceProvisioner. */
        @Column(name="service_provisioner", nullable=false, length=50)
        private String serviceProvisioner;

        /** Contains the suspendFlag. */
        @Column(name="suspend_flag", nullable=false, length=1)
        private String suspendFlag;

        /** Contains the maximumRetries. */
        @Column(name="maximum_retries", nullable=true)
        private Long maximumRetries;

        /** Contains the maximumQueueSize. */
        @Column(name="maximum_queue_size", nullable=true)
        private Long maximumQueueSize;

        /**
         * Constructor
         */
        public ServiceProvisioner() {
            super();
        }

        /**
         * @return the serviceProvisionerEmail
         */
        public String getServiceProvisionerEmail() {
                return serviceProvisionerEmail;
        }

        /**
         * @param serviceProvisionerEmail the serviceProvisionerEmail to set.
         */
        public void setServiceProvisionerEmail(String serviceProvisionerEmail) {
                this.serviceProvisionerEmail = serviceProvisionerEmail;
        }

        /**
         * @return the serviceProvisionerKey
         */
        public Long getServiceProvisionerKey() {
                return serviceProvisionerKey;
        }

        /**
         * @param serviceProvisionerKey the serviceProvisionerKey to set.
         */
        public void setServiceProvisionerKey(Long serviceProvisionerKey) {
                this.serviceProvisionerKey = serviceProvisionerKey;
        }

        /**
         * @return the serviceProvisionerQueue
         */
        public String getServiceProvisionerQueue() {
                return serviceProvisionerQueue;
        }

        /**
         * @param serviceProvisionerQueue the serviceProvisionerQueue to set.
         */
        public void setServiceProvisionerQueue(String serviceProvisionerQueue) {
                this.serviceProvisionerQueue = serviceProvisionerQueue;
        }

        /**
         * @return the retryInterval
         */
        public Long getRetryInterval() {
                return retryInterval;
        }

        /**
         * @param retryInterval the retryInterval to set.
         */
        public void setRetryInterval(Long retryInterval) {
                this.retryInterval = retryInterval;
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
         * @return the endDate
         */
        public Date getEndDate() {
                return endDate;
        }

        /**
         * @param endDate the endDate to set.
         */
        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        /**
         * @return the startDate
         */
        public Date getStartDate() {
                return startDate;
        }

        /**
         * @param startDate the startDate to set.
         */
        public void setStartDate(Date startDate) {
                this.startDate = startDate;
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
         * @return the maximumFailure
         */
        public Long getMaximumFailure() {
                return maximumFailure;
        }

        /**
         * @param maximumFailure the maximumFailure to set.
         */
        public void setMaximumFailure(Long maximumFailure) {
                this.maximumFailure = maximumFailure;
        }

        /**
         * @return the serviceProvisionerRegDate
         */
        public Date getServiceProvisionerRegDate() {
                return serviceProvisionerRegDate;
        }

        /**
         * @param serviceProvisionerRegDate the serviceProvisionerRegDate to set.
         */
        public void setServiceProvisionerRegDate(Date serviceProvisionerRegDate) {
                this.serviceProvisionerRegDate = serviceProvisionerRegDate;
        }

        /**
         * @return the serviceProvisioner
         */
        public String getServiceProvisioner() {
                return serviceProvisioner;
        }

        /**
         * @param serviceProvisioner the serviceProvisioner to set.
         */
        public void setServiceProvisioner(String serviceProvisioner) {
                this.serviceProvisioner = serviceProvisioner;
        }

        /**
         * @return the suspendFlag
         */
        public String getSuspendFlag() {
                return suspendFlag;
        }

        /**
         * @param suspendFlag the suspendFlag to set.
         */
        public void setSuspendFlag(String suspendFlag) {
                this.suspendFlag = suspendFlag;
        }

        /**
         * @return the maximumRetries
         */
        public Long getMaximumRetries() {
                return maximumRetries;
        }

        /**
         * @param maximumRetries the maximumRetries to set.
         */
        public void setMaximumRetries(Long maximumRetries) {
                this.maximumRetries = maximumRetries;
        }

        /**
         * @return the maximumQueueSize
         */
        public Long getMaximumQueueSize() {
                return maximumQueueSize;
        }

        /**
         * @param maximumQueueSize the maximumQueueSize to set.
         */
        public void setMaximumQueueSize(Long maximumQueueSize) {
                this.maximumQueueSize = maximumQueueSize;
        }

}
