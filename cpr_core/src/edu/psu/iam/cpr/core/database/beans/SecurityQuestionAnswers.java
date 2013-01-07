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
@Table(name="security_question_answers")
public class SecurityQuestionAnswers implements Serializable {

        /** Contains the serialized UID */
        private static final long serialVersionUID = 1L;

        /** Contains a unique number that identifies a security question and answer for a Penn State access user.  It is populated by the seq_sec_quest_answer sequence.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Id
        @Column(name="sec_quest_answer_key", nullable=false)
        @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_security_question_answers")
        @SequenceGenerator(name="seq_security_question_answers", sequenceName="seq_security_question_answers", allocationSize = 1, initialValue= 1)
        private Long secQuestAnswerKey;

        /** Contains a unique number that identifies a group of security questions.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="sec_quest_group_key", nullable=false)
        private Long secQuestGroupKey;

        /** Contains the date and time that the record was created.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="created_on", nullable=false)
        private Date createdOn;

        /** Contains a unique number that identifies a person. */
        @Column(name="person_id", nullable=false)
        private Long personId;

        /** Contains a unique number that identifies a security question.   These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="sec_quest_key", nullable=false)
        private Long secQuestKey;

        /** Contains a user's answer to a security question.  These values were initially populated from the password tables in the Central Accounts Coordination Tracking of User Services (CACTUS) application. */
        @Column(name="answer", nullable=false, length=100)
        private String answer;

        /** Contains a user id for a person. */
        @Column(name="userid", nullable=false, length=30)
        private String userid;

        /**
         * Constructor
         */
        public SecurityQuestionAnswers() {
            super();
        }

        /**
         * @return the secQuestAnswerKey
         */
        public Long getSecQuestAnswerKey() {
                return secQuestAnswerKey;
        }

        /**
         * @param secQuestAnswerKey the secQuestAnswerKey to set.
         */
        public void setSecQuestAnswerKey(Long secQuestAnswerKey) {
                this.secQuestAnswerKey = secQuestAnswerKey;
        }

        /**
         * @return the secQuestGroupKey
         */
        public Long getSecQuestGroupKey() {
                return secQuestGroupKey;
        }

        /**
         * @param secQuestGroupKey the secQuestGroupKey to set.
         */
        public void setSecQuestGroupKey(Long secQuestGroupKey) {
                this.secQuestGroupKey = secQuestGroupKey;
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
         * @return the personId
         */
        public Long getPersonId() {
                return personId;
        }

        /**
         * @param personId the personId to set.
         */
        public void setPersonId(Long personId) {
                this.personId = personId;
        }

        /**
         * @return the secQuestKey
         */
        public Long getSecQuestKey() {
                return secQuestKey;
        }

        /**
         * @param secQuestKey the secQuestKey to set.
         */
        public void setSecQuestKey(Long secQuestKey) {
                this.secQuestKey = secQuestKey;
        }

        /**
         * @return the answer
         */
        public String getAnswer() {
                return answer;
        }

        /**
         * @param answer the answer to set.
         */
        public void setAnswer(String answer) {
                this.answer = answer;
        }

        /**
         * @return the userid
         */
        public String getUserid() {
                return userid;
        }

        /**
         * @param userid the userid to set.
         */
        public void setUserid(String userid) {
                this.userid = userid;
        }

}
