//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa4
// 05/24/18
// Graph.c
// This is the implementation file for the Graph ADT.
//
// This program creates an array of Lists, stores the
// adjacent vertices in each Node corresponding to the main vertex. 
// Allowing access to each one by special access 
// functions as well as manipulating them with manipulation functions.
//----------------------------------------------------------------------------


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Graph.h"


typedef struct GraphObj{

    List* adj;
    List qBackup;
    char* color;
    int* parent;
    int* distance;
    int order; //Number of vertices
    int size;  //Number of Edges
    int source; 
    
}GraphObj;



/*** Constructors-Destructors ***/
Graph newGraph(int n){
    Graph G = malloc(sizeof(GraphObj));  //calloc((n+1), sizeof(GraphObj));
    assert(G != NULL);
    G->adj = calloc(n+1, sizeof(List)); //malloc((n+1)*sizeof(ListObj));
    assert(G->adj != NULL);
    G->color = calloc(n+1, sizeof(char));
    assert(G->color != NULL);
    G->parent = calloc(n+1, sizeof(int));
    assert(G->parent != NULL);
    G->distance = calloc(n+1, sizeof(int));
    assert(G->distance != NULL);
    G->order = n;
    G->size = 0;    //No edges?
    G->source = NIL;
    G->qBackup = newList();
    
    for(int i = 0; i <= n; i++){
        G->adj[i] = newList();
    }
    
    for(int i = 0; i <= n; i++){
        G->distance[i] = INF;
        G->parent[i] = NIL;
    }
    
    return G;

}    
    
    


// freeGraph()
// Destructor for the Graph type
void freeGraph(Graph* pG){

    if( pG != NULL && *pG != NULL){
    
        makeNull(*pG);
        free(*pG);
        *pG = NULL;
        
    }

}




/*** Access functions ***/

int getOrder(Graph G){
 
    return G->order;
 
}




int getSize(Graph G){

    return G->size;

}





int getSource(Graph G){

    return G->source;   //No check BFS is calld bcz it either Nil or val
    
}





int getParent(Graph G, int u){

    if(!(u >= 1 && u <= G->order)){
        return NIL;
    }
    if(getSource(G)!= NIL){
        return G->parent[u];
    }
    return NIL;
}




int getDist(Graph G, int u){

    if(!(u >= 1 && u <= G->order)){
        return INF;
    }
    if(getSource(G)!= NIL){
        return G->distance[u];   
    }
    return INF;

}




void getPath(List L, Graph G, int u){

    List A = newList();
    
    if(!(u >= 1 && u <= G->order)){
        return;
    }


    if(getSource(G)!= NIL){
        
        int t = u;
        for(int i = 0; i < G->order; i++){
            if(t == G->source){
                prepend(A, t);  
                for(moveFront(A); index(A) >= 0; moveNext(A)){
                    append(L, get(A));   
                }
                freeList(&A);
                return;
            }
            prepend(A, t);
            t = G->parent[t];
            
        }
        
        
    }
    
    freeList(&A);
    prepend(L, NIL);
}





/*** Manipulation procedures ***/

void makeNull(Graph G){
    if(G != NULL){
        
        for(int i = 0; i <= G->order; i++){
        
            List B = (G->adj[i]);
            freeList(&B);
            B = NULL;
            
        }
        free(G->adj);
        G->adj = NULL;
        free((G->color));
        G->color = NULL;
        free((G->parent));
        G->parent = NULL;
        free(G->distance);
        G->distance = NULL;
        G->order = G->order;   // Might change this later to 0
        G->size  = 0;
        G->source = NIL;
        freeList(&(G->qBackup));
        G->qBackup = NULL;
    }
    
   

}




void addEdge(Graph G, int u, int v){


    if(!(u >= 1 && u <= G->order) && !(v >= 1 && v <= G->order) ){
        return;
    
    }
    List A = G->adj[u];
    edgeSetter(A, v);
    List B = G->adj[v];
    edgeSetter(B, u);
    G->size++; 
    
    
    
}
 
 
void edgeSetter(List L, int u){

    //Put duplicate checker here
    
    
    if(length(L) == 0){
        append(L, u);
    }else if(length(L) == 1){
        if(front(L) < u){
            append(L, u);
        }else{
            prepend(L, u);
        }
    }else if(length(L) > 1){
        moveFront(L);
        while(index(L) > -1){
            if(u < ((int)get(L))){
                insertBefore(L, u);
                return;   
            }
            moveNext(L);
        }
        //If it gets here that means u is the biggest value
        append(L, u);
    }

}




void addArc(Graph G, int u, int v){

    if(!(u >= 1 && u <= G->order) && !(v >= 1 && v <= G->order) ){
        return;
    
    }

    
    int entered = 0; 
    
    List A = (G->adj[u]);
    
    if(duplicateChecker(A, u) == 1){    //Recently added
        return;
    }
    
    
    if(length(A) == 0){
        //printf("Oh My\n");
        append(A, v);
    }
    
    else if(length(A) == 1){
        
        moveFront(A);
        if(v > front(A)){
            append(A, v);
        }else{
            prepend(A, v);
        }
    }else if(length(A) > 1){
        for(moveFront(A); index(A) >= 0; moveNext(A)){
            if(v > get(A)){
                //Do nothing
            }else if(v < get(A)){
                insertBefore(A, v);
                entered = 1;
                break;
            }
        }
        
        if(!entered){
            append(A, v);
        }
        
    }
    
    G->size++;
    
}



void BFS(Graph G, int s){
    for(int i = 0; i <= G->order; i++){
        G->color[i] = 'W';
        G->distance[i] = INF;
        G->parent[i] = NIL;
    }
    
    
    G->color[s] = 'G';
    G->distance[s] = 0;
    G->parent[s] = NIL;
    
    
    List Q = newList();
    
    append(Q, s);
    
    
    moveFront(Q);    
    
    
   while(!isEmpty(Q)){

        int u = front(Q);
        
        deleteFront(Q);
        
        List A = G->adj[u];
        append((G->qBackup), u);
        
        
        for(moveFront(A); index(A) >= 0; moveNext(A)){
            int v = (int)get(A);
            if(v <= 0){
                continue;
            }
            if((G->color[v]) == 'W'){
                G->color[v] = 'G';
                G->distance[v] = G->distance[u]+1;
                G->parent[v] = u;
                append(Q, v);
            }
        
        }
        
        
        
        G->color[u] = 'B';
        
       
        
    }
    G->source = s;

    freeList(&Q);

}




/*** Other operations ***/

void printGraph(FILE* out, Graph G){

    for(int i =1; i <= G->order; i++){
    
        List A = G->adj[i];
        fprintf(out, "%d: ", i);
        printList(out, A);
        
    }
    fprintf(out, "\n");

}


