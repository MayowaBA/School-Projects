//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa5
// 05/30/18
// Graph.h
// This is the header file for the Graph ADT 
//----------------------------------------------------------------------------

#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_



#define UNDEF -20
#define NIL 0

#include"List.h"


// Graph
// Exported reference type
typedef struct GraphObj* Graph;

/*** Constructors-Destructors ***/
Graph newGraph(int n);


void freeGraph(Graph* pG);


/*** Access functions ***/
int getOrder(Graph G);


int getSize(Graph G);



int getParent(Graph G, int u);


int getDiscover(Graph G, int u);


int getFinish(Graph G, int u);


/*** Manipulation procedures ***/
void makeNull(Graph G);


void addEdge(Graph G, int u, int v);


void edgeSetter(List L, int u);


void addArc(Graph G, int u, int v);


void DFS(Graph G, List S);


void visit(Graph G, int u, int time, List T);


/*** Other operations ***/
Graph transpose(Graph G);

Graph copyGraph(Graph G);


void printGraph(FILE* out, Graph G);



#endif
