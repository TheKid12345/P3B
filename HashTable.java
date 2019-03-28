//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:          	HASHTABLE
// Files:           hashtable.java, hashtabletest.java
// Course:          cs400
//
// Author:          Mathew Barnard
// Email:           Mbarnard3@wisc.edu
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  StackOverflow, YouTube tutorial, geekforgeeks.com
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//

/*
 * represents a hashtable with insert remove and other functions
 *
 * Bugs: none known
 *
 * @author mathew barnard
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	/*
	 * represents a node list used by the hashtable to store keys and values
	 *
	 * Bugs: none known
	 *
	 * @author mathew barnard
	 */
	class Node<K,V> {
		//variable field
		Node<K,V> next;
		K key;
		V value;
		/*
		 * default constructor for node class
		 * 
		 * @param none
		 */
		Node(){
			this.key = key;
			this.value = value;
			
		}
	}
	
	/*
	 * default constructor for hashtable
	 * 
	 *@param takes none 
	 */
	public HashTable() {

	}
	
	//ArrayList table is being used to represent the hash table
	ArrayList<Node<K,V>> table = new ArrayList<Node<K, V>>();
	
	//table size to be adjusted as keys are added
	int tableSize;
	
	//load threshold for table
	double loadThreshold;
	
	//initial capacity for table
	int initialTableCapacity;
	
	
	/*
     * this is a constructor for the hashtable when an int and double are passed in
     *
     * @param initial capacity, loadfactorthreshold
     *
     */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		loadThreshold = loadFactorThreshold;
		initialTableCapacity = initialCapacity;
		//adds a null instance for every spot on the arraylist until capacity is met
		for(int i = 0; i < initialCapacity; i++) {
			table.add(null);
		}
	}
	
	/*
     * Returns the index after the key is hashed
     *
     * @param key
     *
     * @return the int value of the index
     */
	int index(K key){
		int hash = key.hashCode();
		return hash % getCapacity();
	}
	
	/*
     * Insert method that inserts the key value pair into the hashtable
     * will throw excpetion if the key is null
     *
     * @param key value
     *
     * @return void
     */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		//checks to see if the k key is null
		if(key == null) {
			throw new IllegalNullKeyException();
		}
		//local variables
		int hashIndex = index(key);
		Node<K,V> pointer = table.get(hashIndex);
		Node<K,V> newNode = new Node<>();
		
		newNode.key = key;
		newNode.value = value;
		
		//if pointer is null will set the new node to index
		if(pointer==null) {
			table.set(hashIndex, newNode);
			tableSize++;
		}else {
			throw new DuplicateKeyException();
		}
		
		
		
	}
	
	/*
     * Returns a boolean value if the key is or isnt in the hashtable
     * if it is it will remove the key value pair and decrease tablesize
     *
     * @param key
     *
     * @return false if the key isnt in the table true if it is
     */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		//if the key passed in is null exception is thrown
		if(key == null) {
			throw new IllegalNullKeyException();
		}
		
		int hashIndex = index(key);
		
		//if the index holding that key is null then the key does not exist
		if(table.get(hashIndex) == null) {
			return false;
		//else the key will already be in that index and we will remove it
		}else {	
			table.set(hashIndex, null);
			tableSize--;
			return true;
			
		}
	}
	
	/*
     * This method gets the value associated with the key
     *
     * @param key
     *
     * @return value associated with key
     */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		//if the key is null throw exception
		if(key == null) {
			throw new IllegalNullKeyException();
		}
		int hashIndex = index(key);
		Node<K,V> pointer = table.get(hashIndex);
		
		//while the pointer is not null will search the index to find match for key
		while(pointer != null) {
			if(pointer.key.equals(key)) {
				return pointer.value;
			}
		}
		
		return null;
		
	}

	/*
     * Returns numkeys
     *
     * @param none
     *
     * @return int number of keys in table
     */
	
	@Override
	public int numKeys() {
		return tableSize;
	}

	/*
     * Returns loadthreshold of table
     *
     * @param none
     *
     * @return double loadthreshold of table
     */
	@Override
	public double getLoadFactorThreshold() {
		return loadThreshold;
	}
	
	/*
     * Returns the load factor
     *
     * @param none
     *
     * @return double loadfactor of table
     */
	@Override
	public double getLoadFactor() {
		return (double)(numKeys()/getCapacity());
	}
	
	/*
     * Returns capacity of table
     *
     * @param none
     *
     * @return int capacity of table
     */
	@Override
	public int getCapacity() {
		return initialTableCapacity;
	}

	/*
     * Returns collision resolution number
     *
     * @param none
     *
     * @return int associated with the collision resolution
     */
	@Override
	public int getCollisionResolution() {
		return 5;
	}


}
