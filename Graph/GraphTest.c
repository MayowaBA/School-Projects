//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa4
// 05/24/18
// GraphTest.c
// This is the test file for the Graph ADT
//----------------------------------------------------------------------------


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Graph.h"


int main(int argc, char* argv[]){
    
    Graph A = newGraph(6);
    
   
    addArc(A, 1, 2);

    addEdge(A, 1, 3);
    
    addEdge(A, 1, 4);
    
    
    addEdge(A, 2, 3);
    
    addEdge(A, 3, 4);
    
    addArc(A, 3, 5);
    
    addEdge(A, 3, 6);
    
    addEdge(A, 4, 6);
    
    addEdge(A, 6, 5);
    
    printGraph(stdout, A);
    
    BFS(A, 1);
    
    List L = newList();
    
    getPath(L, A, 5);
    
    printf("The shortest path from 1 to 5 is ");
    printList(stdout, L);
    
    printf("The source is: %d\n" , getSource(A));
    
    printf("The distance between 1 and 5 is %d edges\n", getDist(A, 5));
    
    printf("The parent of 1 is %d\n", getParent(A, 1));
    
    printf("The parent of 5 is %d\n", getParent(A, 5));
    
    clear(L);
    freeGraph(&A);
    
    A = newGraph(10);
    
    addEdge(A, 1, 2);

    addEdge(A, 2, 3);
    
    addEdge(A, 3, 4);
    
    addEdge(A, 4, 5);
    
    addEdge(A, 5, 6);
    
    addEdge(A, 6, 7);
    
    addEdge(A, 7, 8);
    
    addEdge(A, 9, 10);
    
    
    printGraph(stdout, A);
    
    BFS(A, 1);
    
    L = newList();
    
    getPath(L, A, 7);
    
    printf("The shortest path from 1 to 7 is ");
    printList(stdout, L);
    
    printf("The source is: %d\n" , getSource(A));
    
    printf("The distance between 1 and 7 is %d edges\n", getDist(A, 7));
    
    printf("The parent of 1 is %d\n", getParent(A, 1));
    
    printf("The parent of 5 is %d\n", getParent(A, 7));
 
        
    freeList(&L);
    freeGraph(&A);
    
    
    
    return(EXIT_SUCCESS);
}