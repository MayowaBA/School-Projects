//-----------------------------------------------------------------------------
// Mayowa Borisade
// mborisad
// Dictionary.Java
// 03/05/18
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface{
	
	//Private inner Node Class
	private class Node{
		String key;
		String value;
		Node left;
		Node right;
		
		Node(String k, String v){
			key = k;
			value = v;
			left = null;
			right = null;
		}
	
	}
	
	//Private Fields 
	private Node root;
	private int numPairs;
	
	
	public Dictionary(){
		root = null;
		numPairs = 0;
	}
	
	
	
	//Private helper function -------------------------------------------------
	
	
	// findKey()
	// returns the Node containing key k in the subtree rooted at root, or returns
	// NULL if no such Node exists
	private Node findKey(Node T, String k){
		Node N = T;
		if(N == null || k.equals(N.key)){
			return N;
		}
		if((N.key).compareTo(k) > 0){   //Should this be N.key
			return (findKey((N.left),k));   //Idk if this is right format
		}else if((N.key).compareTo(k) < 0){
			return (findKey((N.right),k));
		}	
		return null;
	}
	
	
	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns NULL 
	// if N is equal to R. (pre: root != NULL)
	private Node findParent(Node N, Node R){
		Node P = null;
		if(N != R){    //Could I just do N != R.root
			P = R;
			while(P.left != N && P.right != N){
				if((N.key).compareTo(P.key) < 0){
					P = P.left;
				}else{
					P = P.right;
				}
			}
		}
		
		return P;
	
	}
	
	
	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
	private Node findLeftmost(Node T){
		Node N = T;
		if(N != null){
			for(N=T; N.left != null; N = N.left);
		}
		return N;
	}


	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to file pointed to by out.
	private void printInOrder(Node N){  //Figure out what the arguments will be
		//StringBuffer sb = new StringBuffer();
		if(N != null){
			
			printInOrder(N.left);
			System.out.println(N.key + " " + N.value);
			printInOrder(N.right);
			
			/*sb.append(printInOrder(N.left));//(N.left).printInOrder();
			sb.append(N.key).append(" ").append(N.value).append("\n");
			sb.append(printInOrder(N.right));//(N.right).printInOrder();
			*/
		}
		//return new String (sb);
	}
	

	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	private void deleteAll(){
		Node N = root;
		if(N != null){
			N = null;
		}
	}
	
	
	// public functions -----------------------------------------------------------

	
	// isEmpty()
	// returns 1 (true) if D is empty, 0 (false) otherwise
	// pre: none
	public boolean isEmpty(){
		return(numPairs == 0);
	}
	
	
	
	// size()
	// returns the number of (key, value) pairs in D
	// pre: none
	public int size(){
		return(numPairs);
	}
	
	
	// lookup()
	// returns the value v such that (k, v) is in D, or returns NULL if no 
	// such value v exists.
	// pre: none
	public String lookup(String k){

		Node N;
		Node R = root;
		N = findKey(R, k);
		if(N != null){
			return N.value;
		}
		return null;
	}
	
	
	// insert()
	// inserts new (key,value) pair into D
	// pre: lookup(D, k)==NULL
	public void insert(String k, String v)throws DuplicateKeyException{
		if(lookup(k) != null){
            		throw new DuplicateKeyException(
                		"cannot insert duplicate keys");
        	}
		Node N, A, B;
		
		N = new Node(k, v);
		B = null;
		A = root;
		while(A != null){
			B = A;
			if(k.compareTo(A.key) < 0){
				A = A.left;
			}else{
				A = A.right;
			}
		}
		if(B == null){
			root = N;
		}else if(k.compareTo(B.key) < 0){
			B.left = N;
		}else{
			B.right = N;
		}
		numPairs++;
	}
	
	
	
	// delete()
	// deletes pair with the key k
	// pre: lookup(D, k)!=NULL
	public void delete(String k)throws KeyNotFoundException{
		if(lookup(k) == null){
            		throw new KeyNotFoundException(
                		"cannot delete non-existent key");
        	}
		Node N, P, S;
   		N = findKey(root, k);
   		
   		if( N.left == null && N.right == null ){  // case 1 (no children)
      			if( N == root ){
         			root = null;
      			}else{
         			P = findParent(N,root);
         			if( P.right == N ){ 
         				P.right = null;
         			}else{ 	
         				P.left = null;
         			}
      			}
   		}else if( N.right == null ){  // case 2 (left but no right child)
      			if( N == root ){
         			root = N.left;
      			}else{
         			P = findParent(N,root);
         			if( P.right == N ){
         				P.right = N.left;
         			}else{ 
         				P.left = N.left;
         			}
      			}
   		}else if( N.left == null ){  // case 2 (right but no left child)
      			if( N == root ){
         			root = N.right;
      			}else{
         			P = findParent(N,root);
         			if( P.right == N ){ 
         				P.right = N.right;
         			}else {
         				P.left = N.right;
         			}
      			}
   		}else{                     // case3: (two children: N->left!=NULL && N->right!=NULL)
      			S = findLeftmost(N.right);//(N.right).findLeftmost();
      			N.key = S.key;
      			N.value = S.value;
      			P = findParent(S,N); //N.findParent(S);
      			if(P.right == S){
      		 		P.right = S.right;
      			}else {
      				P.left = S.right;
      			}
		}
   		numPairs--;

	}
	
	
	// makeEmpty()
	// re-sets D to the empty state.
	// pre: none
	public void makeEmpty(){
		root = null;
		numPairs = 0;
	}
	
	
	// toString()
	// returns a String representation of this Dictionary
	// overrides Object's toString() method
	// pre: none
	public String toString(){
   		printInOrder(root);
   		return "";
   	}

}
