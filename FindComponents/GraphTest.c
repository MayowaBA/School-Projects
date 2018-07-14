//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa5
// 05/30/18
// GraphTest.c
// This is the test file for the Graph ADT
//----------------------------------------------------------------------------



#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include"List.h"
#include"Graph.h"



int main(int argc, char* argv[]){
    
    Graph A = newGraph(11);
    
    addArc(A, 1, 2);
    
    addArc(A, 2, 5);
    
    addArc(A, 3, 2);
    
    addArc(A, 3, 5);
    
    addArc(A, 4, 1);
    
    addArc(A, 5, 4);
    
    addArc(A, 5, 6);
    
    addArc(A, 6, 3);
    
    addArc(A, 6, 9);
    
    addArc(A, 6, 10);
    
    addArc(A, 7, 3);
    
    addArc(A, 7, 6);
    
    addArc(A, 8, 4);
    
    addArc(A, 9, 4);
    
    addArc(A, 9, 5);
    
    addArc(A, 9, 8);
    
    addArc(A, 10, 9);
    
    addArc(A, 10, 11);
    
    addArc(A, 11, 7);
    
    
    printGraph(stdout, A);
    
    
    List L = newList();
    List Q;
    
    for(int i = 1; i < 12; i++){
        append(L, i);
    }
    printf("This is the List: ");
    printList(stdout, L);
    printf("\n");
    
    DFS(A, L);
    
    printf("After DFS is called this is the List T again:\n");
    printList(stdout, L);
    printf("\n");
    
    Q = copyList(L);
    
    Graph T = transpose(A);
    
    
    printf("After transpose is called this is the new Graph is:\n");
    printGraph(stdout, T);
    printf("\n");
    
    
    DFS(T,Q);
    printf("After DFS of transpose of G the List Q is\n");
    printList(stdout, Q);
    printf("\n");
    //printGraph(stdout, T);
    
    freeList(&L);
    freeList(&Q);
    freeGraph(&A);
    freeGraph(&T);
    
    
    //----------------------------------------------------------------
    printf("Starting afresh with new Graph with strong components to test\n");
    printf("----------------------------------------------------------------\n");
    
    Graph B = newGraph(8);
    
    addArc(B, 1, 2);
    
    addArc(B, 2, 3);
    
    addArc(B, 2, 5);
    
    addArc(B, 2, 6);
    
    addArc(B, 3, 4);
    
    addArc(B, 3, 7);
    
    addArc(B, 4, 3);
    
    addArc(B, 4, 8);
    
    addArc(B, 5, 1);
    
    addArc(B, 5, 6);
    
    addArc(B, 6, 7);
    
    addArc(B, 7, 6);
    
    addArc(B, 7, 8);
    
    addArc(B, 8, 8);
    
    printf("This is Graph B: \n");
    printGraph(stdout, B);
    
    
    
    printf("Finding strong points \n");
    List R = newList();
    for(int i = 1; i <= 8; i++){
        append(R, i);
    }
    printf("This is List R at first: \n");
    printList(stdout, R);
    printf("\n");
    DFS(B, R);
    
    printf("This is List R after first DFS: \n");
    printList(stdout, R);
    printf("\n");
    
    for(int i = 1; i <= 8; i++){
       int t = getParent(B, i);
       printf("This is the parent for %d: %d \n", i, t);
    }
    
    for(int i = 1; i <= 8; i++){
       int t = getDiscover(B, i);
       int r = getFinish(B, i);
       printf("This is the discover and finish times for %d: %d, %d\n", i, t, r);
    }
    
    
    
    Graph N = transpose(B);
    
    char * visited = calloc(getOrder(B)+1, sizeof(char));
    assert(visited != NULL);
    
    int count = 0;
    int temp = 0;
    //List H = newList();
    
    for(int i = 1; i <= getOrder(B); i++){
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
    
    printf("G contains %d strongly connected components:\n", count);
    
    
    
    
    count = 0;
    
    
    
    for(int i = 1; i <= getOrder(B); i++){
        visited[i] = 'U';
    }
    int run = 0;
    moveFront(R);
    while(index(R)>=0){
    
        if(visited[get(R)] != 'V'){
            List H = newList();
            append(H, get(R));
            DFS(N,H);
            count++;
            printf("Component %d: ", count);
            
            moveFront(H);
            while(index(H)>=0){
                if(visited[get(H)] != 'V'){
                    printf("%d ", get(H));   
                }
                moveNext(H);
            }
            printf("\n");
            
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
    freeGraph(&B);
    freeGraph(&N);
    
    return(EXIT_SUCCESS);
}
