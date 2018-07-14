// ---------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// 05/03/18
// pa3
// MatrixTest.java
// This is the test file for my Matrix.java
// ---------------------------------------------------------------------------

class MatrixTest{
    public static void main(String[] args){
       Matrix A = new Matrix(10);
        
        
        A.changeEntry(1,1,1);
        A.changeEntry(1,2,2);
        A.changeEntry(1,3,3); 
        A.changeEntry(2,1,4);
        A.changeEntry(2,2,5);
        A.changeEntry(2,3,6);
        A.changeEntry(3,1,7);
        A.changeEntry(3,2,8);
        A.changeEntry(3,3,9);
        
        System.out.println(A);
        
        Matrix B = A.scalarMult(2);
        
        System.out.println(B);
        
        Matrix C = A.copy();
        
        System.out.println(C);
        
        System.out.println("What is the NNZ of matrix C:" + C.getNNZ());
        
        System.out.println("What is the size of matrix A:" + A.getSize());
        
        System.out.println("The addition of A and B is: ");
        System.out.println(A.add(B));
        
        Matrix D = new Matrix(10);
        
        D.changeEntry(1,1,1);
        D.changeEntry(1,2,3);
        D.changeEntry(1,3,2); 
        D.changeEntry(2,1,5);
        D.changeEntry(2,3,7);
        D.changeEntry(3,1,1);
        D.changeEntry(3,2,8);
        
        
        System.out.println("This is A: ");
        System.out.println(A);
        System.out.println("This is D: ");
        System.out.println(D);
        System.out.println("The addition of A and D is: ");
        System.out.println(A.add(D));
        
        System.out.println("The subtraction of A and D is: ");
        System.out.println(A.sub(D));
        
        System.out.println("This is A: ");
        System.out.println(A);
        
        System.out.println("This is the transpose of A: ");
        System.out.println(A.transpose());
        
        System.out.println("This is D: ");
        System.out.println(D);
        
        System.out.println("This is the transpose of D: ");
        System.out.println(D.transpose());
        
        System.out.println("This is A: ");
        System.out.println(A);
        
        System.out.println("This is A*A: ");
        System.out.println(A.mult(A));
        
        
        Matrix Q = new Matrix(3);
        
        Q.changeEntry(1,1,1);
        Q.changeEntry(1,2,2);
        Q.changeEntry(1,3,3); 
        Q.changeEntry(2,2,1);
        Q.changeEntry(2,3,3);
        Q.changeEntry(3,1,4);
        Q.changeEntry(3,2,2);
        
        Matrix T = new Matrix(3);
        
        
        T.changeEntry(1,2,4);
        T.changeEntry(1,3,2); 
        T.changeEntry(2,1,1);
        T.changeEntry(2,2,3);
        T.changeEntry(2,3,6);
        T.changeEntry(3,1,2);
        T.changeEntry(3,2,4);
        
        
        System.out.println("This is Q: ");
        System.out.println(Q);
        
        
        System.out.println("This is T: ");
        System.out.println(T);
        
        System.out.println("This is Q*T: ");
        System.out.println(Q.mult(T));
        
        
        Matrix G = new Matrix(1);
        G.changeEntry(1,1,2);
        
        System.out.println("This is G: ");
        System.out.println(G);
        
        System.out.println("This is G*G: ");
        System.out.println(G.mult(G));
        
        
        System.out.print("Comparing Matrix A to A, is it T/F? ");
        System.out.println(A.equals(A));
        
        
        System.out.print("Comparing Matrix A to T, is it T/F? ");
        System.out.println(A.equals(T));
        
        System.out.print("Comparing Matrix A to G, is it T/F? ");
        System.out.println(A.equals(G));
        
        Matrix Z = new Matrix(10);
        
        Z.changeEntry(1,1,1); 
        Z.changeEntry(1,2,2); 
        Z.changeEntry(1,3,3); 
        Z.changeEntry(2,1,4);
        Z.changeEntry(2,2,5); 
        Z.changeEntry(2,3,6); 
        Z.changeEntry(3,1,7); 
        Z.changeEntry(3,2,8); 
        Z.changeEntry(3,3,9);
        
        
        System.out.println("This is Z: ");
        System.out.println(Z);
        
        
        System.out.println("The addition of Z and Z is: ");
        System.out.println(Z.add(Z));
        
        
        
    }


}