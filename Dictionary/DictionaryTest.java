// Mayowa Borisade
// mborisad
// CMPS 12B
// 02/08/18
//
// This java file test my Dictionary Program
// to see if there are any bugs. 





class DictionaryTest{

    public static void main(String[] args){
    
      
     /*
      * This was used to test if my insert, findKey and
      * toString were working. I found out there was an 
      * error with my insert function

        -----------------------------------
        String v;
        Dictionary A = new Dictionary();
        A.insert("1","a");
        A.insert("2","b");
        A.insert("3","c");
        System.out.println(A + "works1");
        System.out.println("Works");

       */    

      /*
       * This was used to test if my insert, findKey and
       * toString were working. I found out there was an 
       * error with my insert function
 
        String v;
        Dictionary A = new Dictionary();
        A.insert("1","a");
        A.insert("2","b");
        A.insert("3","c"); 
        System.out.println(A + "works1");
	System.out.println("Works");
        System.out.println("Key 1 has " + A.lookup("1")); 
        System.out.println("Key 2 has " + A.lookup("2"));
        System.out.println("Key 3 has " + A.lookup("3"));    
        A.delete("1");
        System.out.println("Key 1 has " + A.lookup("1"));
        A.delete("2");
        System.out.println("Key 2 has " + A.lookup("2")); 
       
       */

      /* -------------------------------------------------------------
       * This was to test if my isEmpty function is working properly.

        Dictionary B = new Dictionary();
        System.out.println("Is this dictionary Empty? " + B.isEmpty());
        B.insert("2","t");
        B.insert("4","f");
        System.out.println("Is this dictionary Empty? " + B.isEmpty());

       */



      /* ---------------------------------------------------------------
       * This was to test my size function to see if it worked properly.
        Dictionary C = new Dictionary();
        System.out.println("How many pairs are in this Dictionary " + C.size());
        C.insert("1","a");
        C.insert("2","b");
        C.insert("3","c");
        System.out.println("How many pairs are in this Dictionary " + C.size()); 

       */

      /* --------------------------------------------------------------
       * This was to check my lookup fuction to see if it was working. 
        Dictionary A = new Dictionary();
        A.insert("1","a");
        A.insert("2","b");
        A.insert("3","c"); 
        System.out.println("Key 3 has " + A.lookup("3"));
        System.out.println("Key 4 has " + A.lookup("4"));

      */

      
      /*
       * This was to check the if my delete function works effectively
       
        Dictionary B = new Dictionary();
        B.insert("2","t");
        B.insert("4","f");
        B.insert("3","c");
        System.out.println("How many pairs are in this Dictionary " + B.size());
        B.delete("3");
        System.out.println("How many pairs are in this Dictionary " + B.size());
        System.out.println(B);
        B.insert("3","c");
        B.delete("4");
        System.out.println("How many pairs are in this Dictionary " + B.size()); 
        System.out.println(B);

      */
                
      /*
       * This is to test my execeptions

        Dictionary C = new Dictionary();
        String j = "1";
        C.insert(j,"apple");
        C.insert("2","banana");
        C.insert("3","Carrot");
        C.insert("7", "Daniel");
        C.insert("4", "java");
        C.insert("2", "jammies");
    
       */

      /*
       * This is to test my other exception and makeEmpty

        Dictionary C = new Dictionary();
        String j = "1";
        C.insert(j,"apple");
        C.insert("2","banana");
        C.insert("3","Carrot");
        C.insert("7", "Daniel");
        C.insert("4", "java");
        C.makeEmpty();
        C.delete("4");
       */

/*
      
        Dictionary C = new Dictionary();
        String j = "1";
        C.insert(j,"apple");
        C.insert("5","apple");
        C.insert("2","banana");
        C.insert("3","Carrot");
        C.insert("7", "Daniel");
        C.insert("4", "java");
        System.out.println(C.lookup("1"));
        C.delete("1");
        System.out.println(C.lookup("1"));
*/


        // This is my final test that test for my Dictionary program. 
        Dictionary C = new Dictionary();
        String j = "1";
        C.insert(j,"apple");
        C.insert("2","banana");
        C.insert("3","Carrot");
        C.insert("7", "Daniel");
        C.insert("4", "java");
        System.out.print(C);
        System.out.println("How many pairs are in this Dictionary " + C.size());
        System.out.println("Is this dictionary Empty? " + C.isEmpty());
        System.out.println("Key 3 has " + C.lookup("3"));
	C.insert("5", "banana");
        System.out.print(C);
        C.delete("2");
        C.delete("4");
        System.out.print(C);
        System.out.println("How many pairs are in this Dictionary " + C.size());
        System.out.println("Is this dictionary Empty? " + C.isEmpty());
        C.makeEmpty();
        System.out.print(C);
        System.out.println("How many pairs are in this Dictionary " + C.size());
        System.out.println("Is this dictionary Empty? " + C.isEmpty());
        System.out.println("Key 3 has " + C.lookup("3"));
        C.delete("4");




    }
}
