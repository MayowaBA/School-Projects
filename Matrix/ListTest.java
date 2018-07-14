// Mayowa Borisade
// mborisad
// 05/03/18
// pa3
// CMPS 101
// This the test file for the List ADT.


public class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();

      for(int i=1; i<=10; i++){
         A.append(i);
         B.prepend(i);
      }
      
      
      System.out.println(A);
      System.out.println(B);
     
      for(A.moveFront(); A.index()>=0; A.moveNext()){
         System.out.print(A.get()+" ");
      }
      System.out.println();
      
      for(B.moveBack(); B.index()>=0; B.movePrev()){
         System.out.print(B.get()+" ");
      }
      System.out.println();
      
      for(A.moveFront(); A.index() < 3; A.moveNext()){
         
      }
      A.insertAfter(20);
      System.out.println(A);
      
      for(A.moveFront(); A.index() < 7; A.moveNext()){
         
      }
      A.insertBefore(52);
      System.out.println(A);
      
      
      for(A.moveFront(); A.index() < 7; A.moveNext()){
         
      }
      A.delete();
      System.out.println(A);
      
      for(A.moveFront(); A.index() <= 3; A.moveNext()){
         
      }
      A.delete();
      System.out.println(A);
      
      //To check index of the cursor
      A.moveBack();
      for(int j=0; j < 3; j++){
          A.movePrev();
      }
      System.out.println("This is the cursor's index: " + A.index());
      
      System.out.println("This is the new List:");
      System.out.println(A);

      
      
      for(int j=0; j<3; j++){
          A.moveBack();
          A.delete();
      }
      System.out.println("This is the new List:");
      System.out.println(A);
      
      System.out.println("This is the front of the list is: "+ A.front());
      System.out.println("This is the back of the list is: "+ A.back());
      
      A.moveBack();
      A.delete();
      
      System.out.println("This is the new List:");
      System.out.println(A);
      
      System.out.println("This is the new back is: "+ A.back());
     
     
     // To test the front and back deletion
     A.deleteFront();
     
     System.out.println("This is the new List A:");
     System.out.println(A);
     System.out.println("This is the new front is: "+ A.front());
      
     System.out.println();
     System.out.println("This is list A:");
     System.out.println(A);
     System.out.println("This is List B:");
     System.out.println(B);
     

      System.out.println("Does A equals B: " + A.equals(B));


      for(int j=0; j<4; j++){
          B.moveFront();
          B.delete();
      }
      B.moveBack();
      B.delete();
      
      System.out.println("This is now List B:");
      System.out.println(B);
      
      System.out.println("Does A equals B: " + A.equals(B));
      System.out.println("Does A equals A: " + A.equals(A));
      
      System.out.println("Lets Clear A");
      A.clear();
      System.out.println("Print A's length");
      System.out.println(A.length());
      B.clear();
      
   }
}