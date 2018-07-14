//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa4
// 05/24/18
// Graph.h
// This is the header file for the Graph ADT 
//----------------------------------------------------------------------------

#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_
#define INF -20
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


int getSource(Graph G);


int getParent(Graph G, int u);


int getDist(Graph G, int u);


void getPath(List L, Graph G, int u);


/*** Manipulation procedures ***/
void makeNull(Graph G);


void addEdge(Graph G, int u, int v);


void edgeSetter(List L, int u);


void addArc(Graph G, int u, int v);


void BFS(Graph G, int s);


/*** Other operations ***/
void printGraph(FILE* out, Graph G);



#endif