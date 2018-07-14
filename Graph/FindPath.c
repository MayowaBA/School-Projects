//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa4
// 05/24/18
// FindPath.c
// This is a Client file for the Graph ADT.
//
// This program reads in inputs for the graph, stores them in a
// adjacency List to represent the graph. Then tries to find the
// shortest path for each listed vertices in the file to each other. 
// Then prints out each vertices and their distances from each other.
//----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Graph.h"
#define MAX_LEN 1100 


int main(int argc, char* argv[]){

    FILE *in, *out;
    
    // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }
   
   
   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   
   
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }
   
   
    // read each line of input file.
    int i = 0, j = 0;

    
    fscanf(in, "%d", &i);
    Graph A = newGraph(i);
    while(!feof (in)){
        fscanf (in, "%d %d", &i, &j);
        if((i == 0) && j == 0){
            break;
        }
        addEdge(A, i, j);
    }
    
    printGraph(out, A);
    
    

    int s, d;
    while(!feof (in)){
        List L = newList();
        fscanf (in, "%d %d", &s, &d);
        if((s == 0) && (d == 0)){
            freeList(&L);
            break;
        }
        BFS(A, s);
        fprintf(out, "The distance from %d to %d is ", s, d);
        
        int dist = getDist(A, d);
        if(dist != INF){
            fprintf(out, "%d", dist);
            fprintf(out, "\n");
        }else{
            fprintf(out, "infinity \n");
        }
        
        
        getPath(L, A, d);
        if(front(L) != NIL){
            
            fprintf(out, "A shortest %d-%d path is: ", s, d);
            printList(out, L);
            fprintf(out, "\n");
        }else{
            fprintf(out, "No %d-%d path exists\n", s, d);
            fprintf(out, "\n");
        }
        
        
        freeList(&L);
    }
    
    
    fclose(in);
    fclose(out);   
    freeGraph(&A);
    
}