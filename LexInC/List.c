//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa2
// 04/17/18
// List.h
// This is the implementation file for the List ADT.
//
// This program creates a bi-directional queue, stores
// integers in each Node. Allowing access to each one
// by special access functions as well as manipulting
// them with manipulation functions.
//----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"List.h"

typedef struct NodeObj{
    int data;
    struct NodeObj* next;
    struct NodeObj* prev;
}NodeObj;

typedef NodeObj* Node;

// Come back and make sure that this function definition is correct
Node newNode(int data){
    Node N = malloc(sizeof(NodeObj));
    assert(N != NULL);
    N->data = data;
    N->next = NULL;
    N->prev = NULL;
    return N;
}

//Freeing Node 
void freeNode(Node* pN){
    if(pN != NULL && *pN != NULL){
        free(*pN);
        *pN = NULL;
    }
}



// ListObj
typedef struct ListObj{
    Node front;
    Node back;
    Node cursor;
    int length;
    int indx;
}ListObj;


// newList()
List newList(void){
    List L  = malloc(sizeof(ListObj));
    assert(L != NULL);
    L->front = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->length = 0; 
    L->indx = -1;
    return L;
}


// freeList()
void freeList(List *pL){
    if(pL != NULL && *pL != NULL){
        if(!isEmpty(*pL)) makeEmpty(*pL);
        free(*pL);
        *pL = NULL;
    }

}


// isEmpty()
int isEmpty(List L){
    if(L == NULL){
        fprintf(stderr, "List Error: Calling isEmpty on NULL List.\n");
        exit(EXIT_FAILURE);
    }
    return (L->length==0);
}



// makeEmpty()
void makeEmpty(List L){
    if(L == NULL){
        fprintf(stderr, "List Error: Calling makeEmpty on NULL List.\n");
        exit(EXIT_FAILURE);
    }
    if(L->length == 0){
        fprintf(stderr, "List Error: Calling makeEmpty on NULL List.\n");
        exit(EXIT_FAILURE);
    }
    if(L != NULL){
        Node N = L->front;
        while(N->next != NULL){
            Node T = N;
            N = N->next;
            freeNode(&T);
        }

        // if there is still leaks after calling makeEmpty free cursor and rest
        Node G = N;
        freeNode(&G);

    }
    L->length = 0;
    L->front = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->indx = -1;

}


// ---------------------------------------------------------------------------
// Access Functions
// ---------------------------------------------------------------------------

// length()
int length(List L){
    return(L->length);
}


// index()
int index(List L){
    return L->indx;
}



// front()
// You could throw an exception here
int front(List L){
    if(L->length>0){
        return (L->front)->data;
    }else{
        return -1;
    }
}



// back()
int back(List L){
    if(L->length>0){
        return (L->back)->data;
    }else{
        return -1;
    }
}



// get()
int get(List L){
    if(L->length>0 && index(L)>=0){
        return (L->cursor)->data; 
    }else{
        return -1;
    }
}


// equals()
int equals(List A, List B){
    int eq = 1;
    Node N = A->front;
    Node T = B->front;
    if(A != NULL && B != NULL){
        if(A->length != B->length){
            return 0;
        }else{
            while(eq && (N != NULL) && (T != NULL)){
                if((N->data) == (T->data)){
                    eq = 1;
				}else{
                    eq = 0;
                    break;
				}
                N = N->next;
               T = T->next;
            }
        }
    }else if( A == NULL && B != NULL ){
        return 0;
    }else if(A != NULL && B == NULL){
        return 0;
    }
    return eq;
}



// ---------------------------------------------------------------------------
// Manipulation Procedures
// ---------------------------------------------------------------------------


//clear()
void clear(List L){
    if(L->front != NULL){
        makeEmpty(L);
    }
}


// moveFront()
void moveFront(List L){
    Node N = L->front;
    if(N != NULL && L->length > 0){
        L->cursor = L->front;
    }
    L->indx = 0;
}



// moveBack()
void moveBack(List L){
    Node N = L->front;
    if(N != NULL && L-> length > 0){
        L->cursor = L->back;
    }
    L->indx = L->length-1;
}



// movePrev()
void movePrev(List L){
    if(L->cursor == L->front){
        L->cursor = NULL;
        L->indx = -1;
    }else if(L->cursor != L->front && L->cursor != NULL){
        L->cursor = (L->cursor)->prev;
        L->indx = L->indx-1;
    }

}



// moveNext
void moveNext(List L){
    if(L->cursor == L->back){
        L->cursor = NULL;
        L->indx = -1;
    }else if(L->cursor != L->back && L->cursor != NULL){
        L->cursor = (L->cursor)->next;
        L->indx = L->indx+1;
    }
}



//prepend
void prepend(List L, int data){
    Node N = newNode(data);
    Node F = L->front;
    if(F == NULL){
        L->front = N;
        L->back = N;
    }else if(F != NULL){
        F->prev = N;
        N->next = L->front;
        L->front = N;
        L->indx++;
    }
    L->length++;
}



//append ()
void append(List L, int data){
    Node N = newNode(data);
    Node F = L->front;
    if(F == NULL){
        L->front = N;
        L->back = N;
    }else{
        (L->back)->next = N;
        N->prev = L->back;
        L->back = N;
    }
    L->length++;
}



// insertBefore()
void insertBefore(List L, int data){
    Node N = newNode(data);
    Node F = L->front;
    if(L->length > 0 && (index(L)>= 0) && F != NULL){
        if(L->cursor == L->front){
            (L->cursor)->prev = N;
            N->next = L->cursor;
            N->prev = NULL;
            L->front = N;
        }else if(L->cursor == L->back){
            Node T = (L->cursor)->prev;
            T->next = N;
            N->prev = T;
            N->next = L->cursor;
            (L->cursor)->prev = N;
        }else{
            Node T = (L->cursor)->prev;
            T->next = N;
            N->prev = T;
            (L->cursor)->prev = N;
            N->next = L->cursor;
        }
        L->length++;
        L->indx = L->indx + 1;
    }
}



// insertAfter()
void insertAfter(List L, int data){
    Node N = newNode(data);
    Node F = L->front;
    if(L->length > 1 && (index(L)) >= 0 && F != NULL){
        if(L->cursor == L->front){
            Node T = (L->cursor)->next;
            (L->cursor)->next = N;
            N->prev = L->cursor;
            T->prev = N;
            N->next = T;
        }else if(L->cursor == L->back){
            (L->cursor)->next = N;
            N->prev = L->cursor;
            N->next = NULL;
            L->back = N;
        }else{
            Node T = (L->cursor)->next;
            (L->cursor)->next = N;
            N->prev = L->cursor;
            N->next = T;
            T->prev = N;
        }
        L->length++;
    }else if(L->length == 1 && (index(L))>=0 && F!= NULL){
        (L->front)->next = N;
        N->prev = L->front;
        L->back = N;
        L->length++;
    }
}


// deleteFront()
void deleteFront(List L){
    if(L->length > 1){
        if(L->cursor == L->front){
            Node Q = L->front;
            Node T = (L->front)->next;
            L->front = T;
            freeNode(&Q);
            Q = NULL;
            (L->front)->prev = NULL;
            L->cursor = NULL;
            L->length--;
            L->indx = -1;
        }else{
            Node T = (L->front)->next;
            L->front = T;
            Node W = (L->front)->prev;
            freeNode(&W);
            W = NULL;
            (L->front)->prev = NULL;
            L->length--;
            L->indx--;
        }
    }else if(L->length == 1){
        makeEmpty(L);
    }
}



// deleteBack()
void deleteBack(List L){
    if(L->length > 1){
        if(L->cursor == L->back){
            Node T = (L->back)->prev;
            Node Q = L->back;
            freeNode(&Q);
            Q = NULL;
            L->back = NULL;
            L->back = T;
            T->next = NULL;
            L->cursor = NULL;
            L->length--;
            L->indx = -1;
        }else{
            Node T = (L->back)->prev;
            Node W = L->back;
            freeNode(&W);
            W = NULL;
            L->back = NULL;
            L->back = T;
            T->next = NULL;
            L->length--;
        }
    }else if(L->length == 1){
        makeEmpty(L);
    }    
}



// delete()
void delete(List L){
    Node F = L->front;
    if(F != NULL){
        if(L->cursor == L->front){
            Node T = (L->front)->next;
            Node Q = L->front;
            freeNode(&Q);
            L->front = T;
            Q = NULL;
            (L->front)->prev = NULL;
            L->cursor = NULL;
        }else if(L->cursor == L->back){
            Node W = (L->back)->prev;
            Node T = L->back;
            freeNode(&T);
            L->back = W;
            T = NULL;
            (L->back)->next = NULL;
            L->cursor = NULL;
        }else{
            Node B = L->cursor;
            Node A = (L->cursor)->next;
            Node N = (L->cursor)->prev;
            N->next = A;
            A->prev = N;
            freeNode(&B);
            B = NULL;
            L->cursor = NULL;
            (L->cursor)->next = NULL;
            (L->cursor)->prev = NULL;
        }
        L->length--;
        L->indx = -1;
    }
}



// ---------------------------------------------------------------------------
// Other Functions
// ---------------------------------------------------------------------------

//printList ()
void printList(FILE* out, List L){
    Node N = L->front;
    if( L == NULL){
        fprintf(stderr, "List Error: Calling printList on Null List.\n");
        exit(EXIT_FAILURE);
    }

    for(N = L->front; N != NULL; N = N->next){
        fprintf(out, "%d ", N->data);        // Not sure if this is right
    }
    fprintf(out, "\n");

}



// copyList()
// Returns a new List representing the same integer sequence as this
// List. The cursor in the new list is undefined, regardless of the
// state of the cursor in this List. This List is unchanged.
List copyList(List L){
    List C = newList();
    Node N = L->front;
    while(N != NULL){
        append(C, N->data);
        N = N->next;
    }
    return C;
}


// concatList()
// Returns a new List which is the concatenation of
// this list followed by L. The cursor in the new List
// is undefined, regardless of the states of the cursors
// in this List and L. The states of this List and L are
// unchanged.
List concatList(List A, List B){
    List C = newList();
    Node N = A->front;
    Node M = B->front;
    while(N != NULL){
        append(C, N->data);
        N = N->next;
    }
    while(M != NULL){
        append(C, M->data);
        M = M->next;
    }
    return C;
}





