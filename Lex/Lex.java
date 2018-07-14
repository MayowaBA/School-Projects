// ---------------------------------------------------------------------------
// mborisad
// Mayowa Borisade
// CMPS101
// Pa1
// 04/09/18
// Lex.java
// This program creates List that sorts an String array in 
// Lexicological order based off its indices.
// ---------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;


class Lex{
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String[] token = null;
        String[] words;
        int n, lineNumber = 0;
      
      
        if(args.length != 2 ){
            System.err.println("Usage: FileIO infile outfile");
            System.exit(1);
        }
      
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
        
        
        while(in.hasNextLine()){
            lineNumber++;
            line = in.nextLine()+" ";  
        }
        
        in.close();        
        in = new Scanner(new File(args[0]));
        
        words = new String[lineNumber];
      
        for(int i = 0; i < lineNumber; i++){
            words[i] = in.nextLine();
        }
        
        
        
        
        // This is to put the indices in the write order into the List
        List A = new List();
        
        int c, cp;
        if((words[0]).compareTo(words[1])<0){
            A.append(0);
            A.append(1);
        }else{
            A.append(1);
            A.append(0);
        }
        
        for(int i = 2; i < words.length; i++){
            for(A.moveBack(); A.index() > 0; A.movePrev()){
                c = A.get();
                A.movePrev();
                cp = A.get();
                A.moveNext();
                                
                if((words[c]).compareTo(words[i])==0){
                    A.insertAfter(i);

                }else if(((words[c]).compareTo(words[i])>0) && ((words[cp]).compareTo(words[i]) < 0)){
                    A.insertBefore(i);
                    A.movePrev();
                }
            }
            if(((words[A.back()]).compareTo(words[i]) < 0)){
                A.append(i);
            }else if(((words[A.front()]).compareTo(words[i]) > 0)){
                A.prepend(i);
            }

        }
        
        
        // To print the list in order into the output file 
        for(A.moveFront(); A.index()>=0; A.moveNext()){
            out.println(words[A.get()]);
        }
            

        in.close();
        out.close();      
   
   }

}
