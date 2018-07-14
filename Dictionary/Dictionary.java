// Mayowa Borisade
// mborisad
// CMPS 12B
// 02/08/18
//
// This program creates a dictionary based on linked lists.
// It has several ADT functions that perform actions like 
// inserting a new keys and words into the Dictionary, remove
// keys and values, or remove all values. It also keeps track
// of the amount of keys and values in the Dictionary. 

public class Dictionary implements DictionaryInterface{
    
    // private inner Node class
    private class Node {
        String key;
        String value;
        Node next;

        Node(String newKey, String newValue){
            key = newKey;
            value = newValue;
            next = null;
        }
    }


    // Fields for the Dictionary class
    private Node head;     // reference to first Node in List
    private int numItems;  // number of items in this Dictionary


    // Dictionary()
    // constructor for the Dictionary class
    public Dictionary(){
        head = null;
        numItems = 0;
    }




    // private helper function -------------------------------------------------
   
    // findKey()
    // returns a reference to the Node at Keys position in this Dictionary
    private Node findKey(String key){
        Node N = head;
        
        while(N != null){
            
            String temp = N.key;
            if(temp.equals(key)){
                    
                return N;
            }
           N = N.next;
        }
          
        return null;
    
    }


    // ADT operations ----------------------------------------------------------
   
    // isEmpty()
    // Returns true or false depending on empty or full condition of the Dicitonary
    public boolean isEmpty(){
        return(numItems == 0);
    }




    // size()
    // pre: none
    // post: returns the number of elements in this Dictionary
    public int size() {
        return numItems;
    }





    // lookup()
    // This function looks up a key in the dictionary and returns the value
    // attached to it
    public String lookup(String key){
      
        Node N = findKey(key);
        if(N == null){
            return null;   
        }else if((N.key).equals(key)){
            return N.value;     

        }else{
        
            return null;
        }

    }




    // insert()
    // inserts a new key and Value into this Dictionary
    public void insert(String key, String value) 
        throws DuplicateKeyException{
	
        if(lookup(key) != null){
            throw new DuplicateKeyException(
                "cannot insert duplicate keys");
        }

        if(lookup(key) == null){
            Node input = new Node(key, value);
            Node N = head;
            if(head == null){
      
                head = input;
       
            }else{
                while(N.next != null){
        
                    N = N.next;
                }
               N.next = input;
          
            }
            
        }

        numItems++;
    }



    // delete()
    // deletes item at keys position from this Dictionary
    public void delete(String key)
        throws KeyNotFoundException{
        if(lookup(key) == null){
            throw new KeyNotFoundException(
                "cannot delete non-existent key");
        }
    
        if(head == null){
            return;
        }
        
        if(lookup(key) != null){
            if(key.equals(head.key)){
                head = head.next;
            }else{
                Node N = head;
                Node temp = findKey(key);
                Node T;
                while(N.next != temp){
                    N = N.next;

                }
            
                T = N.next.next;
                N.next = T;
            }

        }
           
        numItems--;
    }


    // makeEmpty()
    // pre: none
    // post: isEmpty()
    public void makeEmpty(){
        head = null;
        numItems = 0;
    }

    // toString()
    // pre: none
    // post:  prints current state to stdout
    // Overrides Object's toString() method
    public String toString(){
      	
        StringBuffer sb = new StringBuffer();
        Node N = head;

        for( ; N!=null; N=N.next){
            sb.append(N.key).append(" ").append(N.value).append("\n");
        }
        return new String(sb);
    }
}
