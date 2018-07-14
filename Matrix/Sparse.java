// ---------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// 05/03/18
// pa3
// Sparse.java
// This program creates an Array of List of Entries
// and performs various Matrix operation on the
// give input formatted in Matrix form.
// ---------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Sparse{
    public static void main(String[] args) throws IOException{
    
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] token = null;
        String[] words = null;
        int n = 0, a = 0, b = 0;
    
    
        if(args.length != 2){
            System.err.println("Usage: FileIO infile outfile");
            System.exit(1);
        }
    
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
    
    
        while(in.hasNextLine()){
            n = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();
            break;
        }  
    
        
        Matrix A = new Matrix(n);
        Matrix B = new Matrix(n);
        int i = 0, j = 0;
        double v = 0;
        
        for(int k = 1; k <= a; k++){
            i = in.nextInt();
            j = in.nextInt();
            v = in.nextDouble();
            A.changeEntry(i, j, v);
        }
        
        for(int k = 1; k <= b; k++){
            i = in.nextInt();
            j = in.nextInt();
            v = in.nextDouble();
            B.changeEntry(i, j, v);
        }
        
       
        
        
        out.println("A has " + A.getNNZ() + " non-zero entries:");
        out.println(A);
        
        out.println("B has " + B.getNNZ() + " non-zero entries:");
        out.println(B);
        
        out.println("(1.5)*A =");
        out.println(A.scalarMult(1.5));
        
        out.println("A+B =");
        out.println(A.add(B));
        
        out.println("A+A =");
        out.println(A.add(A));
        
        out.println("B-A =");
        out.println(B.sub(A));
        
        out.println("A-A =");
        out.println(A.sub(A));
        
        out.println("Transpose(A) =");
        out.println(A.transpose());
        
        out.println("A*B =");
        out.println(A.mult(B));
        
        out.println("B*B =");
        out.println(B.mult(B));
        

        in.close();
        out.close();
    
    
    
    
    
    }
}
