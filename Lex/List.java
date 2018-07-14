// ---------------------------------------------------------------------------
// mborisad
// Mayowa Borisade
// CMPS101
// Pa1
// 04/09/18
// List.java
// This program creates a bi-directional queue, stores
// integers in each Node. Allowing access to each one
// by special access functions as well as manipulting
// them with manipulation functions.
// ---------------------------------------------------------------------------


class List{
    private class Node{

        //Fields
        int data;
        Node next;
        Node prev;

        //Constructor
        Node(int data){
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

    //Constructor
    List(){
        front = null;
        back = null;
        cursor = null;
        length = 0;
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
        Node F = front;
        int indx = -1;
        if(cursor != null){
            while(F != null){
                indx++;
                if(F == cursor){
                    break;
                }

                F = F.next;
            }
        }

        return indx;
    }


    // front()
    // Pre: length>0
    // Returns the front element
    int front(){
        if(length>0){
            return front.data;
        }else{
            return -1;
        }
        
    }


    // back()
    // Pre: length>0
    // Returns back element
    int back(){
        if(length>0){
            return back.data;
        }else{
            return -1;
        }
        
    }


    // get()
    // Pre: length>0 index>=0;
    // Returns cursor element
    int get(){
        if(length>0 && index()>=0){
            return cursor.data;
        }else{
            return -1;
        }
            
    }


    // equals()
    // Returns true if and only if this List and L are same integer sequence
    boolean equals(List L){
        boolean eq = true;
        Node N = this.front;
        Node T = L.front;
        if(this != null && L != null){
            if(this.length != L.length){
                return false;
            }else{
               while(eq && (N != null) && (T != null)){
                    if((N.data) == (T.data)){
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
    }

    // moveFront()
    // If not-empty, places cursor under the front element, else do nothing
    void moveFront(){
        Node L = front;
        if(L != null && length > 0){
            cursor = front;
        }
    }

    // moveBack()
    // If not-empty, places cursor under the back element, else do nothing
    void moveBack(){
        Node L = front;
        if(L != null && length > 0){
            cursor = back;
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
        }else if(cursor != front && cursor != null){
            cursor = cursor.prev;
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
        }else if(cursor != back && cursor != null){
            cursor = cursor.next;
        }
    }


    // prepend()
    // Insert new element into this List. If List is non-empty,
    // insertion takes place before front element.
    void prepend(int data){
        Node N = new Node(data);
        Node F = front;
        if(F == null){
            front = N;
            back = N;   
        }else if(F != null){
            F.prev = N;
            N.next = front;
            front = N;
        }
        length++;
    }


    // append()
    // Insert new element into this List. If List is non-empty,
    // insertion takes place after back element.
    void append(int data){
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
    void insertBefore(int data){
        Node N = new Node(data);
        Node F = front;
        if(length > 0 && (index() >= 0) && F != null){
            if(cursor == front){
                cursor.prev = N;
                N.next = cursor;
                N.prev = null;
                front = N;
            }else if(cursor == back){
                Node T = cursor.prev;
                T.next = N;
                N.prev = T;
                N.next = cursor;
                cursor.prev = N;
            }else{
                Node T = cursor.prev;
                T.next = N;
                N.prev = T;
                cursor.prev = N;
                N.next = cursor;
            }
                        
            length++;
        }
        
    }


    // insertAfter()
    // Inserts new element after cursor.
    // Pre: length()>0, index()>=0
    void insertAfter(int data){
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
            }else{
                Node T = front.next;
                front = T;
                front.prev = null;
                length--;
            }
           
        }else if(length == 1){
             front = null;
             back = null;
             cursor = null;
             length--;
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
            }else if(cursor == back){
                Node Q = back.prev;
                back = Q;
                back.next = null;
                cursor.prev = null;
                cursor = null;
            
            }else{
                Node W = cursor.next;
                Node N = cursor.prev;
                N.next = W;
                W.prev = N;
                cursor = null;
                cursor.next = null;
                cursor.prev = null;
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


    // copy()
    // Returns a new List representing the same integer sequence as this
    // List. The cursor in the new list is undefined, regardless of the
    // state of the cursor in this List. This List is unchanged.
    List copy(){
        List C = new List();
        Node N = front;
        while(N != null){
            C.append(N.data);
            N = N.next;
        }
        return C;
    }
    
    
    // concat()
    // Returns a new List which is the concatenation of
    // this list followed by L. The cursor in the new List
    // is undefined, regardless of the states of the cursors
    // in this List and L. The states of this List and L are
    // unchanged.
    List concat(List L){
        List C = new List();
        Node N = this.front;
        Node M = L.front;
        while(N != null){
            C.append(N.data);
            N = N.next;
        }
        while(M != null){
            C.append(M.data);
            M = M.next;
        }
        return C;
    }



}
