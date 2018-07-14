//Roots.java
//Mayowa Borisade
//mborisad
//pa4
//This program is supposed to find the roots of the polynomials given within the endpoints

import java.util.Scanner;
import java.text.DecimalFormat;

class Roots{
    public static void main( String [] arg ){
        Scanner sc = new Scanner(System.in);
	
        double E1, E2, polyA;
        double resolution = Math.pow(10,-2);
        double tolerance = Math.pow(10,-12);
        double threshold = Math.pow(10,-5);
        int Deg = 0;
        int i;
	
        System.out.print("Enter the degree: ");
        Deg = sc.nextInt();
        int L = Deg+1;
	
        if(Deg == 0){
            System.out.println("No roots were found in the specified range.");
            System.exit(0);
        }
        double [] C = new double[L];
        System.out.print("Enter " +L+ " coefficients: ");
        for(i = 0; i < L; i++){
            C[i] = sc.nextDouble();
        }
        System.out.print("Enter the left and right endpoints: ");
        E1 = sc.nextDouble();
        E2 = sc.nextDouble();
        System.out.println();
       
	
        //Important variables declared
        double b;
        double [] D = new double[L];
        D = diff(C);
        double a = E1;
        double oddR = 0;
        double evenR = 0;
        double Abs = 0;
        int check = 0;
    
        while( a < E2){
            b = a + resolution;
       
        //Checks for roots(odd) in the equation given 
            if(poly(C,a) * poly(C,b) < 0){
                check = 1;
                oddR = findRoot(C, a, b, tolerance);
                String tenP = String.format("%.10f", oddR);
                System.out.println("Odd root found at: " +tenP);
            }

       //Checks for the new roots(even) in the newly derived equation
            if(poly(D,a) * poly(D, b) <0){
                check = 2;
                evenR = findRoot(D, a, b, tolerance);
                Abs = Math.abs(poly(C, evenR));
            
                if(Abs < threshold){
                    String tenP = String.format("%.10f", evenR); 
                    System.out.println("Even root found at: "+tenP);
                }
             } 
            a = b;
        }
      //This checks to see if both conditions are met for the checking of odd and even roots
        if(check == 0){ 
            System.out.println("No roots were found in the specified range.");
        }

    }

    //This function assigns the powers of the variables and multiply it to the coefficients 
    static double poly(double[] C, double X){
        double fx = 0;
        for(int i = 0;i < C.length; i++){   
        fx += C[i]*Math.pow(X,i); 
        }
        return fx;
    }
   //This function takes the derivative of the equation we have.
    static double[] diff(double[] C ){
        double[] D = new double[C.length-1];
        for(int i = 0; i < C.length-1; i++){
            D[i] = ( C[i+1]*(i+1) );      
        }	
        return D;
    
    }

    //This function prints out the array, this was used to check if i had the right values in there
    static void printArray(double[] A){
        System.out.print("(");
        for(int i = 0; i < A.length; i++){
            System.out.print(A[i]+" ");
        }
        System.out.println(")");
    }

    //This function checks where the polynomial crosses of touches the x-axis
    static double findRoot(double[] C, double a, double b, double tolerance){
        double mid, width;
        
        width = b-a;
        mid = (a+b)/2;
        while ( width>tolerance ){
            if( poly(C,a)*poly(C,mid)<=0 ){  // if root is in [a, mid]
                b = mid;                        //    move b left
            } else {                           // else root is in (mid, b] 
                a = mid;                        //    move a right
            }
            width = b-a;
            mid = (a+b)/2;
      }
      return mid;
    }
}
