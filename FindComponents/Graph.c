//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa5
// 05/30/18
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
#include<assert.h>
#include"Graph.h"


typedef struct GraphObj{

    List* adj;
    List qBackup;
    char* color;
    int* parent;
    int* discover;
    int* finish;
    int order; //Number of vertices
    int size;  //Number of Edges
    int source; 
    int time;
    
}GraphObj;



/*** Constructors-Destructors ***/
Graph newGraph(int n){
    Graph G = malloc(sizeof(GraphObj));  
    assert(G != NULL);
    G->adj = calloc(n+1, sizeof(List)); 
    assert(G->adj != NULL);
    G->color = calloc(n+1, sizeof(char));
    assert(G->color != NULL);
    G->parent = calloc(n+1, sizeof(int));
    assert(G->parent != NULL);
    G->discover = calloc(n+1, sizeof(int));
    assert(G->discover != NULL);
    G->finish = calloc(n+1, sizeof(int));
    assert(G->finish != NULL);
    G->order = n;
    G->size = 0;  
    G->source = NIL;
    G->qBackup = newList();
    
    for(int i = 0; i <= n; i++){
        G->adj[i] = newList();
    }
    
    for(int i = 0; i <= n; i++){
        G->discover[i] = UNDEF;
        G->parent[i] = NIL;
        G->finish[i] = UNDEF;
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





int getParent(Graph G, int u){

    if(!(u >= 1 && u <= G->order)){
        return NIL;
    }
    return G->parent[u];

}




int getDiscover(Graph G, int u){

    if(!(u >= 1 && u <= G->order)){
       return UNDEF;
    }
    
    return G->discover[u];   
    
    

}

int getFinish(Graph G, int u){

    if(!(u >= 1 && u <= G->order)){
        return UNDEF;
    }
    return G->finish[u];   

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
        free(G->discover);
        G->discover = NULL;
        free(G->finish);
        G->finish = NULL;
        G->order = G->order;   
        G->size  = 0;
        G->time = 0;
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
    
    List A = (G->adj[u]);
    edgeSetter(A, v);
    G->size++;
    
    
}




void DFS(Graph G, List S){
    for(int i = 0; i <= G->order; i++){
        G->color[i] = 'W';
        G->discover[i] = UNDEF;
        G->parent[i] = NIL;
        G->finish[i] = UNDEF;
    }
    List T = newList();
    G->time = 0;
    
    for(moveFront(S); index(S)>=0; moveNext(S)){
        int i = get(S);
        if(G->color[i] == 'W'){
        
            visit(G, i, G->time, T);
        
        }
    
    }
    
    
    makeEmpty(S);
    
    
    for(moveFront(T); index(T)>=0; moveNext(T)){
        append(S, get(T));
    }
    
    freeList(&T);
    
    
    T = NULL;
    
    

}

void visit(Graph G, int u, int time, List T){

    G->time = G->time + 1;
    G->discover[u] = G->time;
    G->color[u] = 'G';
    
    List A = G->adj[u];
        
    for(moveFront(A); index(A) >= 0; moveNext(A)){
        int v = (int)get(A);
        if(v <= 0){
            continue;
        }
        if((G->color[v]) == 'W'){
    
            G->parent[v] = u;
            
            visit(G, v, G->time, T);
            
        }
        
    }
    
    G->color[u] = 'B';
    G->time = G->time + 1;
    G->finish[u] = G->time;
    
    if(u != 0){
        prepend(T, u);
    }
    
    
}






/*** Other operations ***/

Graph transpose(Graph G){
    Graph N = newGraph(G->order);
    
    
    for(int i = 1; i <= G->order; i++){
        List A = G->adj[i];
        
        for(moveFront(A); index(A) >= 0; moveNext(A)){
            int v = (int)get(A);
            if(v <= 0){
                continue;
            }
            addArc(N, v, i);
        }
    }
    
    return N;

}

Graph copyGraph(Graph G){
    Graph N = newGraph(G->order);
    for(int i = 1; i <= G->order; i++){
        List A = G->adj[i];
        
        for(moveFront(A); index(A) >= 0; moveNext(A)){
            addArc(N, i, get(A));
        }
    }
    
    return N;

}



void printGraph(FILE* out, Graph G){

    for(int i =1; i <= G->order; i++){
    
        List A = G->adj[i];
        fprintf(out, "%d: ", i);
        printList(out, A);
        
    }
    fprintf(out, "\n");

}



