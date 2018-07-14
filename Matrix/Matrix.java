// ---------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// 05/03/18
// pa3
// Matrix.java
// This program creates an Array of List of Entries
// and performs various Matrix operation on the
// give input formatted in Matrix form.
// ---------------------------------------------------------------------------


class Matrix{
    
    private class Entry{
        //Fields
        int column;
        double value;
        
        //Constructor
        Entry(int c, double v){
            column = c;
            value = v;
        
        }
        
        //Not sure if this works but we'll see
        //Overriding toString()
        public String toString(){
            String format;
            String col = String.valueOf(column);
            String val = String.valueOf(value);
            
            format = ("(" + col + ", " + val + ")");
            
            return format;
        }
        
        // Don't know if this works either 
        // equals(): overrides Object's equals() method
        public boolean equals(Object x){
            boolean eq = false;
            Entry that;
            if(x instanceof Entry){
                that = (Entry)x;
                eq = ((this.column==that.column) && (this.value==that.value));
                
            }
            return eq;
        }
    }
    
    
    // Fields
    private List[] array;
    private int size;
    private int NNZ;
    private int highestRow;
    
    // Constructor
    // Makes a new n x n zero Matrix. pre: n>=1
    Matrix(int n){
        if(n >= 1){
            array = new List[n+1];
            size = n;
        }
        for(int i = 0; i <= n; i++){
            array[i] = new List();
        }
    }
    
    // -----------------------------------------------------------------------
    // Access functions 
    // -----------------------------------------------------------------------
    
    
    // Returns n, the number of rows and columns of this Matrix
    int getSize(){
        return size;
    }
    
    //Check this function to make sure it returns the right thing
    // Returns the number of non-zero entries in this Matrix
    int getNNZ(){
        return NNZ;
    }
    
    
    // This might not work but my Idea behind it is that 
    // cast the indices of the array in to a List to call the 
    // List equals function
    // overrides Object's equals() method
    public boolean equals(Object x){
        boolean eq = false;
        Matrix that;
        if(x instanceof Matrix){
            that = (Matrix)x;
            if(this.getSize() != that.getSize()){
                eq = false;
            }else if(this.NNZ == 0 && that.NNZ == 0){
                eq = true;
            }else{ 
                 
                for(int i = 1; i <= size; i++){
                    List A = (List)(this.array[i]);
                    List B = (List)(that.array[i]);
                    
                    if(A.equals(B)){   
                        eq = true;
                    }else{
                        eq = false;
                        break;
                    }
                }      
            }
            
        }
        return eq;
    }
    
    
    // -----------------------------------------------------------------------
    // Manipulation procedures
    // -----------------------------------------------------------------------
    
    // sets this Matrix to the zero state 
    // So if this.array[i] is one whole list then clear it
    // using the list clear operation
    void makeZero(){
        List A;
        
        for(int i = 1; i <= size; i++){
            A = (List)(this.array[i]);
            if(A != null){
                A.clear();
                A = null;
            }
        }
        NNZ = 0;
        highestRow = 0;
    } 
    
    
    // returns a new Matrix having the same entries as this Matrix
    // Before doing this let me work on other functions
    Matrix copy(){
        Matrix B = new Matrix(size);
        
        
        for(int i = 1; i <= size; i++){
            List A = (List)array[i];
        
            A.moveFront();
            
            while((A.get()) != null){
                Entry T = (Entry)(A.get());
                B.changeEntry(i, T.column, T.value);
                A.moveNext();
            }
            
       }
        
        return B;
        
    }
    
    
    // changes ith row, jth column of this Matrix to x
    // pre: 1<=i<=getSize(), 1<=j<=getSize()
    void changeEntry(int i, int j, double x){
        List A = (List)array[i];
        Entry B = new Entry(j, x);
        boolean isZero = false;
        boolean inserted = false;
        
        
        
        if(x == 0){
            isZero = true;
        }
        else if(highestRow < i){
            highestRow = i;
        }
        
        if(A.length() == 0){     //If new List
            if(isZero){
                return;
            }else{
                A.append(B);
                NNZ++;
                return;
            }
        }
        
        if(search(A, B)){
            
            A.moveFront();
            while(A.get() != null){
                Entry C = (Entry)A.get();
                if(B.column == C.column){
                    if(isZero){
                        if(A.length() == 1){
                            A.clear();
                            NNZ--;
                            highestRow--;
                            break;
                        }else{
                            A.delete();
                            NNZ--;
                            break;
                        }
                        
                    }
                    C.value = B.value;
                    break;
                }
                
                A.moveNext();
            }
            
        }else{
            
            A.moveFront();
            while(A.get() != null){
                Entry D = (Entry)A.get();
                if(D.column < B.column){
                    //Do Nothing
                }
                else if(D.column > B.column){
                    
                    if(isZero){
                        break;
                    }
                    A.insertBefore(B);
                    inserted = true;
                    NNZ++;
                    break;
                }
                A.moveNext();
            }
            if(!inserted){
                if(isZero){
                    return;  //Might take this out
                    //Do not insert
                }else{
                    A.append(B);
                    NNZ++;
                }
            }
            
       }
        
       if((highestRow < i) && (!isZero)){
            highestRow = i;
       }
        
        
        
        
        
    }
    
    
    
    // Helper function for changeEntry
    // Traverses the List to find if entry already present
    private boolean search(List A, Entry B){
        
        boolean exist = false;
        A.moveFront();
        while(A.get() != null){
            
            Entry C = (Entry)(A.get());
            if(B.column == C.column){
                exist = true;
                break;
            }
            A.moveNext();
        }
        return exist;
    }
    
    
    
    // returns a new Matrix that is the scalar product of this Matrix with x
    Matrix scalarMult(double x){
        
        Matrix B = new Matrix(size);
        double tot;
        
        for(int i = 1; i <= size; i++){  //Used to have Size
            List A = (List)array[i];
            //List C = (List)(B.array[i]);
        
            A.moveFront();
            //C.moveFront();
            
            while((A.get()) != null){
                Entry T = (Entry)(A.get());
                tot = (T.value) * x;
                B.changeEntry(i, T.column, tot);
                A.moveNext();
            }
            
       }
       return B;
    }
 
 
    
    // returns a new Matrix that is the sum of this Matrix with M
    // pre: getSize()==M.getSize() 
    Matrix add(Matrix M){
        
        //if(this.getSize() != M.getSize()){
           // throw new RuntimeException("Cannot do matrix addition on different size matrices");
       // }
            
        Matrix C = new Matrix(size);
        double tot;
        boolean same = false;
        
        if(this == M){
            same = true;
        }
        
        //System.out.println("Gets Here " + i + " " + P.column);
        for(int i = 1; i<= size; i++){  //USED TO HAVE SIZE
            List A = (List)this.array[i];
            List B = (List)(M.array[i]);
        
            
            A.moveFront();
            B.moveFront();
            
            
            while(A.get() != null && B.get() != null ){ 
                Entry P = (Entry)(A.get());
                Entry Q = (Entry)(B.get());
                
                
                if( P.column == Q.column && same){
                    tot = (P.value) + (Q.value);
                    C.changeEntry(i, P.column, tot);
                    A.moveNext();
                }else if(P.column == Q.column){
                    tot = (P.value) + (Q.value);
                    C.changeEntry(i, P.column, tot);
                    A.moveNext();
                    B.moveNext();
                }else if(P.column < Q.column){
                    C.changeEntry(i, P.column, P.value);
                    A.moveNext();
                }else if(P.column > Q.column){
                    C.changeEntry(i, Q.column, Q.value);
                    B.moveNext();
                }
                
                
                
            }
            while(A.get() != null){
                Entry P = (Entry)(A.get());
                C.changeEntry(i, P.column, P.value);
                A.moveNext();
            }
            
            while(B.get() != null){
                Entry Q = (Entry)(B.get());
                C.changeEntry(i, Q.column, Q.value);
                B.moveNext();
            }
        
        }
        
       return C;
       
    
    }
    
    
    
    
    
    // returns a new Matrix that is the difference of this Matrix with M
    // pre: getSize()==M.getSize()
    Matrix sub(Matrix M){
        
        Matrix C = new Matrix(size);
        double tot;
        boolean same = false;
        
        if(this == M){
            same = true;
        }
        
        for(int i = 1; i<= size; i++){  //Used to have size
            List A = (List)array[i];
            List B = (List)(M.array[i]);
            
            A.moveFront();
            B.moveFront();
            
            while(A.get() != null  && B.get() != null){
                Entry P = (Entry)(A.get());
                Entry Q = (Entry)(B.get());
                
                
                
                //System.out.println(search(A,Q));
                //System.out.println("Gets Here "+ Q.column+ " " + Q.value);
                if( P.column == Q.column && same){
                    tot = ((P.value) - (Q.value));
                    C.changeEntry(i, P.column, tot);
                    A.moveNext();
                }else if(P.column == Q.column){
                    tot = (P.value) - (Q.value);
                    C.changeEntry(i, P.column, tot);
                    A.moveNext();
                    B.moveNext();
                }else if(P.column < Q.column){
                    C.changeEntry(i, P.column, P.value);
                    A.moveNext();
                }else if(P.column > Q.column){
                    C.changeEntry(i, Q.column, -Q.value);
                    B.moveNext();
                }
                
                
            }    
            
            while(A.get() != null){
                Entry P = (Entry)(A.get());
                C.changeEntry(i, P.column, P.value);   //I NEGATED THEM CUZ 0-X = -X
                A.moveNext();
            }
            
            while(B.get() != null){
                Entry Q = (Entry)(B.get());
                C.changeEntry(i, Q.column, -Q.value);    //I NEGATED THEM CUZ 0-X = -X
                B.moveNext();
            }
            
       }
            
            
       return C;
    }
    
    
  
    // returns a new Matrix that is the transpose of this Matrix
    Matrix transpose(){
        Matrix M = new Matrix(size);
        
        for(int i = 1; i <= highestRow; i++){  //Used to have size
            
            List A = (List)array[i];
            A.moveFront();
            
            while(A.get() != null){
                
                Entry P = (Entry)(A.get());
                M.changeEntry(P.column, i, P.value);
                A.moveNext();
            }
            
        
        }
        return M;
    }
    
    
    
    
    // returns a new Matrix that is the product of this Matrix with M
    // pre: getSize()==M.getSize()
    Matrix mult(Matrix M){
        Matrix B = new Matrix(size);
        Matrix C = M.transpose();
        double ent;
        
        
        
        
        for(int i = 1; i <=size; i++){  //Used to be size here
        
            List A = (List)array[i];
            
        
            for(int j = 1; j<=size; j++){  //Used to be size here
               List D = (List)(C.array[j]);
               //Entry T = (Entry)A.get();
               ent = dot(A, D);
               if(ent != 0){
                   B.changeEntry(i, j, ent);
               }
               
            
            }
            
        }
        return B;
    }
    
    
    private static double dot(List P, List Q){
        double tot = 0;
        boolean status = false;
        boolean same = false;
        
        if(P == Q){
            same = true;
        }
        
        P.moveFront();
        Q.moveFront();
        while((P.get() != null) && (Q.get() != null)){
            
            Entry T = (Entry)(P.get());
            Entry S = (Entry)(Q.get());
            if(T.column == S.column && same){
                tot = tot + (T.value * S.value); 
                P.moveNext();
            }else if(T.column == S.column){
                tot = tot + (T.value * S.value); 
                P.moveNext();
                Q.moveNext(); 
            }else if(T.column < S.column){
                P.moveNext();  //This b/c that column times 0
            }else{
                Q.moveNext(); //Same thing^^
            }
               
        }
        
        return tot;
    }
    
    
    // -----------------------------------------------------------------------
    // Other functions
    // -----------------------------------------------------------------------

    // overrides Object's toString() method
    public String toString(){
       StringBuffer sb = new StringBuffer();
       
       
       for(int i = 1; i <= highestRow; i++){
            List A = (List)array[i];
            
            if(A.length() != 0){
                sb.append(i);
                sb.append(": ");
                sb.append(A.toString());
                sb.append("\n");
            } 
            
            
       }
       return new String(sb);
    
    }
 
 
 





    



}