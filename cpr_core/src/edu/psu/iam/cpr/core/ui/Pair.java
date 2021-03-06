/* SVN FILE: $Id: Pair.java 5659 2012-11-19 17:24:59Z slk24 $ */
package edu.psu.iam.cpr.core.ui;


/**
 * Pair is an object class for transporting the necessary key/value data from the database to the UI application
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: slk24 $
 * @version $Rev: 5659 $
 * @lastrevision $Date: 2012-11-19 12:24:59 -0500 (Mon, 19 Nov 2012) $
 */

public class Pair<K, V> {
	
	private K key;
	private V value;
	
	public Pair() {
		
	}

	/**
	* @param key key to be set
	* @param value value to be set
	*/
	public Pair(final K key, final V value) {

		this.key = key;
		this.value = value;
	}

	/**
	* @param key key to be set
	* @param value value to be set
	* @return this
	*/
	public Pair<K, V> setPair(final K key, final V value) {
		this.key = key;
		this.value = value;
		return this;
	}

	/**
	* @return key
	*/
	public K getKey() {
		return key;
	}

	/**
	* @param key key to be set
	* @return this
	*/
	public Pair<K, V> setKey(final K key) {
		this.key = key;
		return this;
	}

	/**
	* @return value
	*/
	public V getValue() {
		return value;
	}

	/**
	* @param value value to be set
	* @return this
	*/
	public Pair<K, V> setValue(final V value) {
		this.value = value;
		return this;
	}
	
}
