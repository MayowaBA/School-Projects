//-----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// CMPS 12M
// 02/25/18
//
// This java file test the List ADT and its functions.
//-----------------------------------------------------------------------------

class ListTest{
   public static void main(String[] args){
      List<String> ls1 = new List<String>();
      List<String> ls2 = new List<String>();
      
      //Testing my add function
      ls1.add(1, "butter");
      ls1.add(2, "tenders");
      ls1.add(3, "Chicken");
      ls1.add(4, "MAC");
      ls2.add(1, "finger");
      ls2.add(2, "Licking");
      ls2.add(3, "good");
      ls2.add(4, "KFC");
      
      
      
      
      //Testing my get function
      System.out.println();
      System.out.println(ls1.get(1) + " " + ls2.get(1));
      System.out.println(ls2.get(3) + " " + ls1.get(3));
      System.out.println(ls1.get(3) + " " + ls1.get(2));
      
      
      
      //Testing my equals function
      System.out.println();
      System.out.println("ls1 equals to ls2? " + ls1.equals(ls2));
      
      
      //Testing my Size function
      System.out.println();
      System.out.println("ls1 is " +ls1.size()+ " items long.");
      System.out.println("ls2 is " +ls2.size()+ " items long.");
   
            
      // To test my toString Function
      System.out.println();
      System.out.println(ls1);
      System.out.println(ls2);
      
      
      //Testing my remove function
      System.out.println();  
      ls1.remove(1);
      ls2.remove(4);
      System.out.println(ls1);
      System.out.println(ls2);
      
      //Testing my removeAll and isEmpty function
      System.out.println();
      ls1.removeAll();
      System.out.println("ls1 is empty? " + ls1.isEmpty());
      
      
      
      
   }
}
