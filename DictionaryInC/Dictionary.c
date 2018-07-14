//-----------------------------------------------------------------------------
// Dictionary.c
// Mayowa Borisade
// mborisad
// CMPS 12B
// 03/13/18
//
// This program creates a dictionary based on Hash tables.
// It has several ADT functions that perform actions like 
// inserting a new keys and words into the Dictionary, remove
// keys and values, or remove all values. It also keeps track
// of the amount of keys and values in the Dictionary.
// 
// Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
const int tableSize=101;


typedef struct NodeObj{
        char* key;
        char* value;
        struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node;

//Creating a Node
Node newNode(char* k, char* v){
    Node N = calloc(tableSize,sizeof(NodeObj));
    assert(N != NULL);
	N->key = k;
	N->value = v;
    N->next = NULL;
	return N;
}


//Freeing the Node
void freeNode(Node* pN){
	if(pN != NULL && *pN != NULL){
		free(*pN);
        *pN = NULL;
    }
}         

    
//Declaring the Dictionary Object
typedef struct DictionaryObj{
    Node array[101]; 
    int numItems;
} DictionaryObj;

             

      
//Creating a New Dictionary
Dictionary newDictionary(void){
	Dictionary D = calloc(tableSize,sizeof(DictionaryObj));
    assert(D != NULL);
    D->numItems = 0; 
    return D;
}   

    
//Deleting the Dictionary
void freeDictionary(Dictionary* pD){
	if( pD != NULL && *pD != NULL){
		if(!isEmpty(*pD)){
			makeEmpty(*pD);
		}
    	free(*pD);
		*pD = NULL;
    }
}


// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift){
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
		return value;
    return (value << shift) | (value >> (sizeInBits - shift));
 }


// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input){
	unsigned int result = 0xBAE86554;
	while (*input){
		result ^= *input++;
		result = rotate_left(result, 5);
	}
    return result;
}


// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%tableSize;
}




// This function returns the Node that
// Points to address holding the key.
Node findKey(Node N, char *key){
        Node T = N;
        while(T!=NULL){
        	if(strcmp(T->key, key)==0){
				return T;
			}
        	T = T->next;
        }
        return NULL;
}


// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
	if(D == NULL){
		fprintf(stderr, "Dictionary Error: calling isEmpty on NULL Dictionary reference.\n");
        exit(EXIT_FAILURE);
	}
	return(D->numItems==0);
}


// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	return(D->numItems);    //Do I need to check if it is null before return
}


// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
	if(D == NULL){
        fprintf(stderr, "Dictionary Error: calling lookup on NULL Dictionary reference.\n");
        exit(EXIT_FAILURE);
	}

	int index;
	index = hash(k);
	Node N = (D->array[index]);
    Node B;

	if((D->array[index]) != NULL){
		while(N != NULL){
            if(strcmp(N->key, k)==0){
            	return N->value;
            }
            N = N->next;
        }
    }else{ //strcmp(N->key, k)!=0
		B = findKey(N, k);
		if(B != NULL){
			return B->value;
		}else{  //if(B == NULL)
			return NULL;
		}
	}
	return NULL;
}


// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	if(lookup(D, k) != NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on non-existent key.\n");
		exit(EXIT_FAILURE);
	}

	int index;
	index = hash(k);
	if(lookup(D, k) == NULL){
		if((D->array[index]) == NULL){
			(D->array[index]) = newNode(k, v);
        }else{
			Node T = newNode(k, v);
        	Node B = (D->array[index]);
			(D->array[index]) = T;
			(D->array[index])->next = B;
        }
	}
	D->numItems++;
}



// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
	if(lookup(D, k) == NULL){
    	fprintf(stderr, "Dictionary Error: calling delete() on non-existent key.\n");
      	exit(EXIT_FAILURE);
   }else{
   		
		int index;
   		index = hash(k);
   		
   		//If Node only has a head
   		if((D->array[index])->next == NULL){
   			freeNode(&(D->array[index]));
   		}else{  //If head has other nodes attached
   		
            //If head is the one being deleted
   			if((D->array[index]) == findKey((D->array[index]), k)){   
         		Node T = (D->array[index]);
         		Node C = (D->array[index])->next;
         		(D->array[index]) = C;
         		freeNode(&T);
         		//T = NULL;
         
      		}else{ //If head isnt the one trying to be deleted
      			Node N = (D->array[index]);
      			Node G = findKey((D->array[index]), k);
      			while(N->next != NULL){
      				if(N->next == G){
      					N->next = G->next; 
      					freeNode(&G);    //Free G and remove R from the equation
      					break;
      				}
      				N = N->next;
      			}
      		}
   		}
   }
    
	D->numItems--;
}




// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
	 if( D==NULL ){
      	fprintf(stderr, "Dictionary Error: calling makeEmpty on NULL Dictionary reference.\n");
      	exit(EXIT_FAILURE);
   	}
   	
   	for(int i = 0; i < tableSize; i++){
        if((D->array[i]) != NULL){
            //If Node only has a head
            if((D->array[i])->next == NULL){
                freeNode(&(D->array[i]));
            }else{
                Node W = (D->array[i]);
                while(W != NULL){
                	
                    (D->array[i]) = (D->array[i])->next;
                    freeNode(&W);
                    W = NULL;
                    
                    W = (D->array[i]);
                }
            }
        }
        
   	}
   	D->numItems = 0;
}



// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	if( D==NULL ){
      	fprintf(stderr, 
              "Dictionary Error: calling printDictionary on NULL Dictionary reference.\n");
      	exit(EXIT_FAILURE);
   	}
   
   
   	Node N;
   	
   	for(int i = 0; i < tableSize; i++){
   		N = (D->array[i]);
   		if((D->array[i]) != NULL){
   			
   			while(N->next != NULL){
   				fprintf(out, "%s %s\n", N->key, N->value);
   				N = N->next;
   			}
   			fprintf(out, "%s %s\n", N->key, N->value);
   		}
		
   	}
}
