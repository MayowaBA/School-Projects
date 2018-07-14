//-----------------------------------------------------------------------------
//
// GraphClient.c
// 
// This is a fancy test client that calculates the Radius and Diameter of 
// the graph defined on lines 41-49, along with its Central and Peripheral 
// vertices.  The definitions of these terms at:
// 
//    http://en.wikipedia.org/wiki/Distance_(graph_theory)
// 
// Place this file in a directory with copies of your List.c, List.h, Graph.c, 
// Graph.h and an appropriate Makefile, then compile and run. The output 
// is contained in the file GraphClientOut.
// 
// This program does not exercise all functions in your Graph ADT, but it 
// does a pretty good job of testing BFS().  If your output differs from 
// the above, you have a logical problem in either your Graph or List ADT.
// 
// Remember you are required to submit a file named GraphTest.c with pa4 that
// exercises your Graph functions.  Do not submit this file.
//
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"
//#include"List.c"
//#include"Graph.c"

int main(int argc, char* argv[]){
  /* int i, s, max, min, d, n=35;
    
   List  C = newList(); // central vertices
   List  P = newList(); // peripheral vertices  
   List  E = newList(); // eccentricities 
   Graph G = NULL;

   
   // Build graph G 
   G = newGraph(n);
    
   for(i=1; i<n; i++){
      if( i%7!=0 ){
          addEdge(G, i, i+1);
          //printf("First added edge1\n");
          //printf("First added edge %d , %d\n",i, i+1);
      } 
      
      
      if( i<=28  ){
          addEdge(G, i, i+7);
          //printf("Second added edge %d , %d\n",i, i+7);
      
      } 
   }
   
   addEdge(G, 9, 31);
   addEdge(G, 17, 13);
   addEdge(G, 14, 33);

   // Print adjacency list representation of G
   printGraph(stdout, G);

   // Calculate the eccentricity of each vertex 
   for(s=1; s<=n; s++){
      BFS(G, s);
      max = getDist(G, 1);
      for(i=2; i<=n; i++){
         d = getDist(G, i);
         max = ( max<d ? d : max );
      }
      append(E, max);
   }

   // Determine the Radius and Diameter of G, as well as the Central and 
   // Peripheral vertices.
   append(C, 1);
   append(P, 1);
   min = max = front(E);
   moveFront(E);
   moveNext(E);
   for(i=2; i<=n; i++){
      d = get(E);
      if( d==min ){
         append(C, i);
      }else if( d<min ){
         min = d;
         clear(C);
         append(C, i);
      }
      if( d==max ){
         append(P, i);
      }else if( d>max ){
         max = d;
         clear(P);
         append(P, i);
      }
      moveNext(E);
   }

   // Print results 
   printf("\n");
   printf("Radius = %d\n", min);
   printf("Central vert%s: ", length(C)==1?"ex":"ices");
   printList(stdout, C);
   printf("\n");
   printf("Diameter = %d\n", max);
   printf("Peripheral vert%s: ", length(P)==1?"ex":"ices");
   printList(stdout, P);
   printf("\n");

   // Free objects 
   freeList(&C);
   freeList(&P);
   freeList(&E);
   freeGraph(&G);

   return(0);
   
   
   */
   
        Graph A = newGraph(99);
        
        List L = newList();
        List C = newList();
        
        addArc(A, 64, 4);
        addArc(A, 64, 3);
        addArc(A, 42, 2);
        addArc(A, 2, 64);
        addArc(A, 4, 2);
        addArc(A, 3, 42);
        BFS(A, 3);
        getPath(L, A, 64);
        append(C, 3);
        append(C, 42);
        append(C, 2);
        append(C, 64);
        if (!equals(L, C)) printf("%d\n", 1);
        
        moveFront(L);
        BFS(A, 2);
        getPath(L, A, 2);
        append(C, 2);
        printf("This is L ");
        printList(stdout, L);
        printf("\n");
        printf("This is C ");
        printList(stdout, C);
        printf("\n");
        
        if (!equals(L, C)) printf("%d\n", 2);
        
        getPath(L, A, 99);
        
        if (equals(L, C)) printf("%d\n", 3);
        
        clear(L);
        clear(C);
        
        append(C, NIL);
        BFS(A, 99);
        getPath(L, A, 2);
        if (!equals(C, L)) printf("%d\n", 4);
        return 0;
}
