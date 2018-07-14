// ---------------------------------------------------------------------------
// mborisad
// Mayowa Borisade
// CMPS101
// Pa2
// 04/23/18
// Lex.c
// This program creates List that sorts an String array in 
// Lexicological order based off its indices.
// ---------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"List.h"
#define MAX_LEN 1100


int main(int argc, char * argv[]){
    int i, count=0;
    FILE *in, *out;
    char line[MAX_LEN];
    
    
    // check command line for correct number of arguments
    if( argc != 3 ){
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    // open files for reading and writing 
    in = fopen(argv[1], "r");
    out = fopen(argv[2], "w");
    if( in==NULL ){
        printf("Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
    if( out==NULL ){
        printf("Unable to open file %s for writing\n", argv[2]);
        exit(1);
    }
   
   
   
    /* read each line of input file, then count and print tokens */
    while( fgets(line, MAX_LEN, in) != NULL)  {
        count = count + 1;
    }
    
    fclose(in);
   
    //Declaration of string array   
    char* words[MAX_LEN];
   
   
    // open files for reading and writing 
    in = fopen(argv[1], "r");
   
    if( in==NULL ){
        printf("Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
   
   
    i = 0;
    /* read each line of input file, then count and print tokens */
    // Not sure if this works
    while(fgets(line, MAX_LEN, in) != NULL)  {
        words[i] = calloc(MAX_LEN, sizeof(char)); 
        strcpy(words[i], line);
        i++;
    }
   
   
   
    // Start ----------------------------------------------------
    // This is to put the indices in the write order into the List
    List A = newList();
        
    int c = -1;
    int a = -1;
	int cp = -1;
	int b = -1;
	int f = -1;
	int g = -1;
        
    for(int k = 0; k < count; k++){
        for(moveBack(A); index(A) > 0; movePrev(A)){
            c = get(A);
            movePrev(A);
            cp = get(A);
            moveNext(A);
                 
            a = strcmp(words[c],words[k]);
            b = strcmp((words[cp]), (words[k]));  
            f = strcmp((words[back(A)]), (words[k]));
            g = strcmp((words[front(A)]), (words[k]));  
            if(a == 0){
                insertAfter(A, k);
            }else if(a > 0 && b < 0){
                insertBefore(A, k);
                movePrev(A);
            }
        }
        if(f < 0){
            append(A, k);
        }else if(g > 0){
            prepend(A, k);
        }
    }
            
    // To print the list in order into the output file 
    for(moveFront(A); index(A)>= 0; moveNext(A)){
        fprintf(out, "%s", words[get(A)]);
    }
        
    
    //To free array
    for(int j = 0; j < count; j++){
        free(words[j]);
        words[j] = NULL;
    }
    
    freeList(&A);
    fclose(in);
    fclose(out);      


}
