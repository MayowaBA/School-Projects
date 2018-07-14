//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa5
// 05/30/18
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
#include<assert.h>
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
    int order = i; 
    Graph A = newGraph(order);
    

    while(!feof (in)){
        fscanf (in, "%d %d", &i, &j);
        if((i == 0) && j == 0){
            break;
        }
        addArc(A, i, j);
    }
    fprintf(out, "Adjacency list representation of G:\n");
    printGraph(out, A);
    
    
    
    List R = newList();
    for(int z = 1; z <= order; z++){
        append(R, z);
    }
    
    
    DFS(A, R);
    
    
    
    
    Graph N = transpose(A);
    
    
    
    char * visited = calloc(getOrder(A)+1, sizeof(char));
    assert(visited != NULL);
    
    int count = 0;
    
    for(int i = 1; i <= getOrder(A); i++){
        visited[i] = 'U';
    }
    
    
    
    moveFront(R);
    while(index(R)>=0){
    
        if(visited[get(R)] != 'V'){
            List H = newList();
            append(H, get(R));
            DFS(N,H);
            count++;
        
            
            for(moveFront(H); index(H)>=0; moveNext(H)){
                for(moveFront(R); index(R)>=0; moveNext(R)){
                    if(get(H) == get(R)){
                        visited[get(R)] = 'V';
                    }
                }
            }
            moveFront(R);
            freeList(&H);
        }
        moveNext(R);
        
    }
    
    fprintf(out,"G contains %d strongly connected components:\n", count);
    
    
    count = 0;
    
    
    
    for(int i = 1; i <= getOrder(A); i++){
        visited[i] = 'U';
    }

    moveFront(R);
    while(index(R)>=0){
    
        if(visited[get(R)] != 'V'){
            List H = newList();
            append(H, get(R));
            DFS(N,H);
            count++;
            fprintf(out, "Component %d: ", count);
            
            
            moveFront(H);
            while(index(H)>=0){
                if(visited[get(H)] != 'V'){
                    fprintf(out, "%d ", get(H));   
                    
                }
                moveNext(H);
            }
            fprintf(out, "\n");
            
            for(moveFront(H); index(H)>=0; moveNext(H)){
                for(moveFront(R); index(R)>=0; moveNext(R)){
                    if(get(H) == get(R)){
                        visited[get(R)] = 'V';
                    }
                }
            }
            moveFront(R);
            freeList(&H);
        } 
        
        moveNext(R);
        
    }
        

    
    free((visited));
    visited = NULL;
    
    freeList(&R);
    freeGraph(&N);
    
    fclose(in);
    fclose(out);   
    freeGraph(&A);
    
}
