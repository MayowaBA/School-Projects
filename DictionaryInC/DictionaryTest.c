//-----------------------------------------------------------------------------
// Dictionary.c
// Mayowa Borisade
// mborisad
// CMPS 12B
// 03/13/18
//
// This program tests a dictionary based on Hash tables.
// It has several ADT functions that perform actions like 
// inserting a new keys and words into the Dictionary, remove
// keys and values, or remove all values. It also keeps track
// of the amount of keys and values in the Dictionary.
// 
// Test file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
  	Dictionary A = newDictionary();
   
  	 
   	char* keys[] = {"1","2","3","4","5","6",};
   	char* values[] = {"life","death","success","Disappoint","Achieve","believe"};
   	
   	//Testing my insert function
   	for(int i = 0; i < 6; i++){
		insert(A, keys[i], values[i]);
   	}
   	
   	//Testing my Print function
   	printDictionary(stdout, A);
	
	//Testing my lookup Function
	printf("This the value at 3: %s\n", lookup(A, keys[2]));
	printf("This the value at 6: %s\n", lookup(A, keys[5]));
	printf("This the value at 1: %s\n", lookup(A, keys[0]));
	
	
	//Testing my Size function
	printf("This the size of A is %d\n", size(A));
	
	//Testing my isEmpty function
	int x = isEmpty(A);
	printf("Is the function empty? %s \n", x ? "true" : "false" );
	
	//Testing my delete function
	delete(A, keys[5]);
	
	printf("This the value at 3: %s\n", lookup(A, keys[2]));
	printf("This the value at 6: %s\n", lookup(A, keys[5]));
	printf("This the value at 1: %s\n", lookup(A, keys[0]));
	
	printf("This the size of A is %d\n", size(A));
	
	delete(A, keys[4]);
	
	printf("This the value at 5: %s\n", lookup(A, keys[4]));
	printf("This the size of A is %d\n", size(A));
	
	//Testing my Make empty
	makeEmpty(A);
	
	printf("This the size of A is %d\n", size(A));
	
	for(int i = 0; i < 6; i++){
		printf("This the value at %d: %s\n", (i+1), lookup(A, keys[i]));
   	}

}
