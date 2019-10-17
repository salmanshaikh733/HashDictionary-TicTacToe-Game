//mshaikh52 student number 250959996


import javax.xml.datatype.DatatypeConfigurationException;

public class HashDictionary implements DictionaryADT {

    //create a hash table of type node, the node class stores the configuration object.
    private Node[] hashTable;

    //constructor
    public HashDictionary(int Size) {
        //this.seperateChain = new Configuration[Size];
        this.hashTable = new Node[Size];

    }

    //method to put data object in hashData
    public int put(Configuration data) throws DictionaryException {

        //use the hash function to calculate the hashCode for the new entry
        int hashCode = hashFunction(data.getStringConfiguration());
        //store the new entry in Node
        Node newEntry = new Node(data);
        //store the current entry in a node
        Node currentEntry = hashTable[hashCode];
        int returnValue = 0;
        //if spot is empty
        if (currentEntry == null) {
            hashTable[hashCode] = newEntry;
            return 0;
        }

        //if first node matches newEntry node
        else if (currentEntry.getDataItem().getStringConfiguration().equals(newEntry.getDataItem().getStringConfiguration())) {
            throw new DictionaryException();
        }

        //if the current spot is not empty traverse a linked list and add to that
        else {
            while (currentEntry.getNext() != null) {
                //if current node matches dataEntry node
                if (currentEntry.getNext().getDataItem().getStringConfiguration().equals(newEntry.getDataItem().getStringConfiguration())) {
                    throw new DictionaryException();
                }
                //move one over.
                currentEntry = currentEntry.getNext();
            }
            currentEntry.setNext(newEntry);
            return 1;
        }


    }

    //method to remove node from hashTable that contains the config value thrown
    public void remove(String config) throws DictionaryException {
        int hashCode = hashFunction(config);
        Node currentEntry = hashTable[hashCode];
        //if the current node is empty
        if (currentEntry == null) {

            throw new DictionaryException();

        }
        //if not empty make a linked list
        else {
            while (currentEntry != null) {
                if (currentEntry.getDataItem().getStringConfiguration().equals(config)) {
                    currentEntry = null;
                } else if (currentEntry.getNext() == null) {
                    throw new DictionaryException();
                } else {
                    currentEntry = currentEntry.getNext();
                }

            }

        }

    }


    //get the score for the specific configuration.
    public int getScore(String config) {
        int hashCode = hashFunction(config);
        Node currentEntry = hashTable[hashCode];
        if (currentEntry == (null)) {
            return -1;

        }
        //find the entry with score
        while (currentEntry.getNext() != null) {
            if (currentEntry.getDataItem().getStringConfiguration() == (config)) {
                return currentEntry.getDataItem().getScore();
            } else if (currentEntry.getDataItem() == null) {
                return -1;
            } else {
                currentEntry = currentEntry.getNext();
            }
        }

        return currentEntry.getDataItem().getScore();


    }

    //generate keys for hash table
    private int hashFunction(String configKey) {
        int val = 0;
        int x = 13;
        for (int i = 0; i < configKey.length(); i++) {
            val = (((val * x) + ((int) configKey.charAt(i))) % hashTable.length);
        }
        return val % hashTable.length;
    }

}
