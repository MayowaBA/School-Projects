// ---------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// 05/03/18
// pa3
// CMPS 101
// This is the List ADT to be used to create Matrix.java
// It uses a bi-directional queue.
// ---------------------------------------------------------------------------


class List{
    private class Node{

        //Fields
        Object data;
        Node next;
        Node prev;

        //Constructor
        Node(Object data){
            this.data = data;
            next = null;
            prev = null;
        }

        //Overiding toString function
        public String toString(){
            return String.valueOf(data);
        }

        // equals(): overrides Object's equals() method
        public boolean equals(Object x){
            boolean eq = false;
            Node that;
            if(x instanceof Node){
                that = (Node) x;
                eq = (this.data==that.data);
            }
            return eq;
        }

    }
    //Fields
    private Node front;
    private Node back;
    private Node cursor;
    private int length;
    private int indx;

    //Constructor
    List(){
        front = null;
        back = null;
        cursor = null;
        length = 0;
        indx = -1;
    }



    //----------------------------------------------------------------------
    //Access Functions
    //----------------------------------------------------------------------


    // length()
    // Returns the number of elements in this List.
    int length(){
        return length;
    }


    // index()
    // If cursor is defined returns the index, else returns -1;
    int index(){
        return indx;
    }


    // front()
    // Pre: length>0
    // Returns the front element
    Object front(){
        if(length>0){
            return front.data;
        }else{
            return -1;
        }
        
    }


    // back()
    // Pre: length>0
    // Returns back element
    Object back(){
        if(length>0){
            return back.data;
        }else{
            return -1;
        }
        
    }


    // get()
    // Pre: length>0 index>=0;
    // Returns cursor element
    Object get(){
        if(length>0 && index()>=0){
            return cursor.data;
        }else{
            return null;
        }
            
    }


    // equals()
    // Returns true if and only if this List and L are same integer sequence
    public boolean equals(Object x){
        boolean eq = false;
        List L;
        
        
        if(x instanceof List){
            
            L = (List)x;
            eq = true;
            Node N = this.front;
            Node T = L.front;
            if(this != null && L != null){
                if(this.length != L.length){
                    return false;
                }else{
                    while(eq && (N != null) && (T != null)){
                        
                        if((N.data).equals(T.data)){   //What do I replace this with interms of Objects .compareTo?
                            eq = true;
                        }else{
                            eq = false;
                            break;
                        }
                        N = N.next;
                        T = T.next;
                    }
                }

            }else if(this == null && L != null){
                return false;
            }else if(this != null && L == null){
                return false;
            }
            return eq;
        }
        return eq;
    
    }


    //----------------------------------------------------------------------
    //Manipulation Procedures
    //----------------------------------------------------------------------


    // clear()
    // Resets List to original state
    void clear(){
        front = null;
        back = null;
        cursor = null;
        length = 0;
        indx = -1;
    }

    // moveFront()
    // If not-empty, places cursor under the front element, else do nothing
    void moveFront(){
        Node L = front;
        if(L != null && length > 0){
            cursor = front;
            indx = 0;
        }
    }

    // moveBack()
    // If not-empty, places cursor under the back element, else do nothing
    void moveBack(){
        Node L = front;
        if(L != null && length > 0){
            cursor = back;
            indx = length - 1;
        }
    }


    // movePrev()
    // If cursor is defined and not at front, moves cursor one step toward
    // front of this List, if cursor is defined and at front, cursor becomes
    // undefined, if cursor is undefined does nothing.
    void movePrev(){
        Node L = this.front;
        if(cursor == front){
            cursor = null;
            indx = -1;
        }else if(cursor != front && cursor != null){
            cursor = cursor.prev;
            indx--;
        }
    }


    // moveNext()
    // If cursor is defined and not at back, moves cursor one step toward
    // back of this List, if cursor is defined and at back, cursor becomes
    // undefined, if cursor is undefined does nothing.
    void moveNext(){
        Node L = this.back;
        if(cursor == back){
            cursor = null;
            indx = -1;
        }else if(cursor != back && cursor != null){
            cursor = cursor.next;
            indx++;
        }
    }


    // prepend()
    // Insert new element into this List. If List is non-empty,
    // insertion takes place before front element.
    void prepend(Object data){
        Node N = new Node(data);
        Node F = front;
        if(F == null){
            front = N;
            back = N;   
        }else if(F != null){
            F.prev = N;
            N.next = front;
            front = N;
            if(cursor != null){
                indx++;
            }
        }
        length++;
    }


    // append()
    // Insert new element into this List. If List is non-empty,
    // insertion takes place after back element.
    void append(Object data){
        Node N =  new Node(data);
        Node F = front;
        Node B = back;
        if(F == null){
            front = N;
            back = N;
        }else{
            back.next = N;
            N.prev = back;
            back = N;
            //back.next = null;
        }
        length++;
    }


    // insertBefore()
    // Insert new element before cursor.
    // Pre: length()>0, index()>=0
    void insertBefore(Object data){
        Node N = new Node(data);
        Node F = front;
        if(length > 0 && (index() >= 0) && F != null){
            if(cursor == front){
                cursor.prev = N;
                N.next = cursor;
                N.prev = null;
                front = N;
                indx++;
            }else if(cursor == back){
                Node T = cursor.prev;
                T.next = N;
                N.prev = T;
                N.next = cursor;
                cursor.prev = N;
                indx++;
            }else{
                Node T = cursor.prev;
                T.next = N;
                N.prev = T;
                cursor.prev = N;
                N.next = cursor;
                indx++;
            }
                        
            length++;
        }
        
    }


    // insertAfter()
    // Inserts new element after cursor.
    // Pre: length()>0, index()>=0
    void insertAfter(Object data){
        Node N  = new Node(data);
        Node F = front;
        if(length > 1 && (index() >= 0) && F != null){   //List of larger than 1
            if(cursor == front){
                Node T = cursor.next;
                cursor.next = N;
                N.prev = cursor;
                T.prev = N;
                N.next = T;
            }else if(cursor == back){
                cursor.next = N;
                N.prev = cursor;
                N.next = null;
                back = N;
            }else{
                Node T = cursor.next;
                cursor.next = N;
                N.prev = cursor;
                N.next = T;
                T.prev = N;
            }
            length++;
        }else if(length == 1 && (index() >= 0) && F != null){ //List of 1
            front.next = N;
            N.prev = front;
            back = N;
            length++;
        }
        
    }

    // deleteFront()
    // Deletes the front element.
    // Pre: length()>0
    void deleteFront(){
        if(length > 1){
            if(cursor == front){
                Node T = front.next;
                front = T;
                front.prev = null;
                cursor = null;
                length--;
                indx = -1;
            }else{
                Node T = front.next;
                front = T;
                front.prev = null;
                length--;
                if(cursor != null){
                    indx--;
                }
            }
           
        }else if(length == 1){
             front = null;
             back = null;
             cursor = null;
             length--;
             indx = -1;
        }
       
    }


    // deleteBack()
    // Deletes the back element.
    // Pre: length()>0
    void deleteBack(){
        if(length > 1){
            if(cursor == back){
                Node T = back.prev;
                back = null;
                back = T;
                T.next = null;
                cursor = null;
                indx = -1;
                length--;
            }else{
                Node T = back.prev;
                back = null;
                back = T;
                T.next = null;
                length--;
            }
           
        }else if(length == 1){
            front = null;
            back = null;
            cursor = null;
            length--;
            indx = -1;
        }
        
    }


    // delete()
    // Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0
    void delete(){
        Node F = front;
        if(F != null){
            if(cursor == front){
                Node T = front.next;
                front = T;
                front.prev = null;
                cursor.next = null;
                cursor = null;
                indx = -1;
            }else if(cursor == back){
                Node Q = back.prev;
                back = Q;
                back.next = null;
                cursor.prev = null;
                cursor = null;
                indx = -1; 
            
            }else{
                Node W = cursor.next;
                Node N = cursor.prev;
                N.next = W;
                W.prev = N;
                cursor = null;
                indx = -1;
            }        
            length--;
        }
        
    }



    //----------------------------------------------------------------------
    //Other Functions
    //----------------------------------------------------------------------


    // Overrides Object's toString method. Returns a String
    // representation of this List consisting of a space
    // separated sequence of integers, with front on left.
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N = front;
        while(N!=null){

            sb.append(N.toString());
            sb.append(" ");
            N = N.next;
        }

        return new String(sb);

    }


}
