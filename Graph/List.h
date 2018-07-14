//----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// pa4
// 05/24/18
// List.h
// This is the header file for the List ADT
//----------------------------------------------------------------------------

#ifndef _DICTIONARY_H_INCLUDE_
#define _DICTIONARY_H_INCLUDE_

// List
// Exported reference type
typedef struct ListObj* List;

// newList()
// constructor for the List type 
List newList(void);

// freeList()
// destructor for the List type
void freeList(List *pL);

// isEmpty()
// helperfunction
int isEmpty(List L);

// makeEmpty()
// Helper Function
void makeEmpty(List L);

// length()
// Returns the number of elements in this List.
int length(List L);

// index()
// If cursor is defined returns the index, else returns -1;
int index(List L);

// front()
// Pre: length>0
// Returns the front element
int front(List L);

// back()
// Pre: length>0
// Returns back element
int back(List L);

// get()
// Pre: length>0 index>=0;
// Returns cursor element
int get(List L);

// equals()
// Returns true if and only if this List and L are same integer sequence
int equals(List A, List B);

// clear()
// Resets List to original state
void clear(List L);

// moveFront()
// If not-empty, places cursor under the front element, else do nothing
void moveFront(List L);

// moveBack()
// If not-empty, places cursor under the back element, else do nothing
void moveBack(List L);

// movePrev()
// If cursor is defined and not at front, moves cursor one step toward
// front of this List, if cursor is defined and at front, cursor becomes
// undefined, if cursor is undefined does nothing.
void movePrev(List L);

// moveNext()
// If cursor is defined and not at back, moves cursor one step toward
// back of this List, if cursor is defined and at back, cursor becomes
// undefined, if cursor is undefined does nothing.
void moveNext(List L);

// prepend()
// Insert new element into this List. If List is non-empty,
// insertion takes place before front element.
void prepend(List L, int data);

// append()
// Insert new element into this List. If List is non-empty,
// insertion takes place after back element.
void append(List L, int data);


//Checks for Duplicates
//Added at pa4
int duplicateChecker();

// insertBefore()
// Insert new element before cursor.
// Pre: length()>0, index()>=0
void insertBefore(List L, int data);

// insertAfter()
// Inserts new element after cursor.
// Pre: length()>0, index()>=0
void insertAfter(List L, int data);

// deleteFront()
// Deletes the front element.
// Pre: length()>0
void deleteFront(List L);

// deleteBack()
// Deletes the back element.
// Pre: length()>0
void deleteBack(List L);

// delete()
// Deletes cursor element, making cursor undefined.
// Pre: length()>0, index()>=0
void delete(List L);

// Overrides Object's print method. Returns a String
// representation of this List consisting of a space
// separated sequence of integers, with front on left.
void printList(FILE* out, List L);

// copy()
// Returns a new List representing the same integer sequence as this
// List. The cursor in the new list is undefined, regardless of the
// state of the cursor in this List. This List is unchanged.
List copyList(List L);

// concatList()
// Returns a new List which is the concatenation of
// this list followed by L. The cursor in the new List
// is undefined, regardless of the states of the cursors
// in this List and L. The states of this List and L are
// unchanged. 
List concatList(List A, List B);



#endif
