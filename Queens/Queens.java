//Queens.java
//Mayowa Borisade
//mborisad
//pa5
// This program finds all the possible combinations of Queens that could be
// placed on the board.

import java.util.Scanner;


class Queens{
    public static void main(String[] args){
        int num = 0;
        int solution = 0;
        boolean verb = false;

        //Check to see if the user types -v to be in verbose mode
        if(args.length == 2){
            if(args[0].equals("-v")){
                verb = true;
                try{
                    num = Integer.parseInt(args[1]);
                }
                catch(Exception e){
                    messageErr();
                    return;
                }
                if(num <= 0){
                    messageErr();
                    return;
                }
             }else{
                 messageErr();
                 return;
             }
        }else if(args.length == 1){
             try{
                 num = Integer.parseInt(args[0]);
             }
             catch(Exception e){
                 messageErr();
                 return;
             }
             if(num <= 0){
                 messageErr();
                 return;
             }
        }else{
             messageErr();
             return;
        }

        //Creating an Array C to length of user input
        int [] C = new int[num + 1]; 
        
        //Assign values to the spots in the array location
        for(int i = 0; i < C.length; i++){
            C[i] = i;
        }

        //In order to check all possible iterations
        int len = factorial(num);

        //Check all the permutations to see if they don't attack each other
        for (int i = 0; i <= len; i++){
            nextPermutation(C);
            if(isSolution(C)){
                solution = solution + 1;
                    if(verb){
                        printArray(C);
                    }
            } 
        }
        System.out.println(num+"-Queens has "+solution+" solutions");    
        
    }

    //The function for producing all permutations
    static void nextPermutation(int A[]){   
        int pivot = 0;
        boolean pivotFound = true;
        int successor = 0;    
        int j = A.length-1;

        //This scan the array and checks for a pivot
        for(int i = A.length-2; i > 0; i--){  
            if(A[i] < A[j]){
                pivot = i;
                pivotFound = false;
                break;
                
            }
            j--;
        }
     
        //If no Pivot reverse array
        if(pivotFound){
            reverse(A,1);
            return;
        }
        
        //Scan array again
        for(int i = A.length-1; i > pivot; i--){
            if(A[i] > A[pivot]){
                successor = i;
                break;
            }
        }
        swap(A, pivot, successor);
        reverse(A, pivot+1);
    

    }
   
    //This calculates the factorial of the user input
    static int factorial(int x){
        int sum = 1;
        for(int i = 1; i <= x; i++){
            sum *= i;
        }
        return sum;
    }

    //This swaps the variables in the array
    static void swap(int []B, int a, int b){
        int temp = B[a];
        B[a] = B[b];
        B[b] = temp;
    }

    //This reverse the array
    static void reverse(int []D, int index){
        int w = index; 
        int k = D.length-1;  
        while(w < k){
            swap(D, w, k);
            w++;
            k--;
        }

    }

    //This prints the Array
    static void printArray(int[] P){
        System.out.print("(");
        for(int i=1; i<P.length; i++){
            if(i == P.length-1){
                System.out.print(P[i]+")");
            }else{
                System.out.print(P[i]+", ");
            }
        }
        System.out.println();
    }

    //This checks to see if the queens are attacking each other
    static boolean isSolution(int [] A){
        for(int i = 1; i < A.length; i++){
            for(int j = 1; j < Math.min(A.length - i, A.length-A[i]); j++){ 
                if((A[i] + j) == A[i+j]){
                    return false;
                }
            }
            for(int j = 1; j < Math.min(A.length - i, A[i]); j++){
                if((A[i] - j) == A[i+j]){
                    return false;
                }
            }
        }
        return true;

      
    }     

    //This prints out the error message if the user doesn't enter the right input
    static void messageErr(){
        System.out.println("Usage: Queens [-v] number");
        System.out.println("Option: -v   verbose output, print all solutions");
        System.exit(0);
    } 
}
