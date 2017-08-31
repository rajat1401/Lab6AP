import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
//CONCEPT OF GENERICS AND JAVA COLLECTIONS FRAMEWORK!

class BSTFilesBuilder {

	public static void createBSTFiles(int numStudents, int numTrees) {
		Random rand = new Random();
		for (int i = 1; i <= numTrees; i++) {
		    try {
				PrintWriter w = new PrintWriter("Desktop/Lab6AP" + i + ".txt", "UTF-8");
				int type = rand.nextInt(3) + 1;
				if(type == 1) {
					w.println("Integer");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextInt(1000));
						w.print(" ");
					}
				}
				else if(type == 2) {
					w.println("Float");
					w.println(numStudents);
					for (int j = 1; j <= numStudents; j++) {
						w.print(rand.nextFloat()*1000);
						w.print(" ");
					}
				}
				else {
					w.println("String");
					w.println(numStudents);
					String alphabet = "abcdefghijklmnopqrstuvwxyz";
					for (int j = 1; j <= numStudents; j++) {
						int len = rand.nextInt(10)+1;
						for (int k = 0; k < len; k++)
							w.print(alphabet.charAt(rand.nextInt(alphabet.length())));
						w.print(" ");
					}
				}
				w.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


class Node<T extends Comparable<T>>{//Generic class
	private Node<T> left;
	private Node<T> right;
	private T data;
	
	public Node(T data){
		this.data= data;
	}
	
	public Node(Node<T> left, T data, Node<T> right){
		this.left= left;
		this.data= data;
		this.right= right;
	}
	
	public void setLeft(Node<T> n){
		this.left= n;
	}
	
	public void setRight(Node<T> n){
		this.right= n;
	}
	
	public Node<T> getRight(){
		return this.right;
	}
	
	public Node<T> getLeft(){
		return this.left;
	}
	
	public T getData(){
		return this.data;
	}
}

class BST<T extends Comparable<T>>{//Another Generic class
	ArrayList<Object> list= new ArrayList<Object>();
	
	public Node<T> Insert(Node<T> root, T val){//Generic method
		if(root==null){
			root= new Node<T>(val);
		}else{
			if(val.compareTo((T)root.getData()) <=0){
				root.setLeft(Insert(root.getLeft(), val));
			}else{
				root.setRight(Insert(root.getRight(), val));
			}
		}
		return root;
	}
	
	public void Inorder(Node<T> root){//Another generic method
		if(root!= null){
			Inorder(root.getLeft());
			list.add(root.getData());
			Inorder(root.getRight());
		}
	}
}

public class Generic{
	static Node<String> root1= null;//static roots so that a new tree can be build everytime!
	static Node<Integer> root2= null;
	static Node<Float> root3= null;
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc= new Scanner(System.in);
		int trees= sc.nextInt();
		int students= sc.nextInt();
		Map<Integer, ArrayList<String>> papamap= new HashMap<Integer, ArrayList<String>>(students + 1);//JAVA COLLECTION FRAMEWORK USED!
		//BSTFilesBuilder.createBSTFiles(students, trees);
		for(int i=1; i<=trees; i++){
			Scanner in= new Scanner(new FileReader("./src/" + i + ".txt"));
			String s= in.next();
			int n= in.nextInt();
			if(s.equals("String")){
				String head = null;
				BST<String> stree= new BST<String>();
				for(int j=0; j<n; j++){
					String a= in.next();
					if(j==0){
						head= a;
					}
					root1= stree.Insert(root1, a);//builds tree
				}
				stree.Inorder(root1);//gets the inorder!
				String ans= (String)stree.list.get(0);
				for(int j=1; j<n; j++){
					ans= ans.concat((String)stree.list.get(j));//remember that sring concat returns a new string that needs to be collected!
				}
				for(int j=0; j<n; j++){
					if(stree.list.get(j).equals(head)){
						if(papamap.get(j)==null){
							//nayi bnao and then add
							ArrayList<String> listx= new ArrayList<String>();
							listx.add(ans);
							papamap.put(j, listx);
						}else{
							papamap.get(j).add(ans);
							break;
						}
					}
				}
				root1= null;
			}else if(s.equals("Integer")){
				int head = 0;
				BST<Integer> stree= new BST<Integer>();
				for(int j=0; j<n; j++){
					int a= in.nextInt();
					if(j==0){
						head= a;
					}
					root2= stree.Insert(root2, a);//builds tree
				}
				stree.Inorder(root2);//gets the inorder!
				int ans= 0;
				for(int j=0; j<n; j++){
					ans+= (int)stree.list.get(j);
				}
				for(int j=0; j<n; j++){
					if((int)stree.list.get(j)==head){
						if(papamap.get(j)==null){
							//nayi bnao and then add
							ArrayList<String> listx= new ArrayList<String>();
							listx.add(Integer.toString(ans));
							papamap.put(j, listx);
						}else{
							papamap.get(j).add(Integer.toString(ans));
							break;
						}
					}
				}
				root2= null;
			}else{
				float head = 0;
				BST<Float> stree= new BST<Float>();
				for(int j=0; j<n; j++){
					float a= in.nextFloat();
					if(j==0){
						head= a;
					}
					root3= stree.Insert(root3, a);//builds the tree
				}
				stree.Inorder(root3);//gets the inorder!
				float ans= 0;
				for(int j=0; j<n; j++){
					ans+= (float)stree.list.get(j);
				}
				for(int j=0; j<n; j++){
					if((float)stree.list.get(j)==head){
						if(papamap.get(j)==null){
							//nayi bnao and then add
							ArrayList<String> listx= new ArrayList<String>();
							listx.add(Float.toString(ans));
							papamap.put(j, listx);
							
						}else{
							papamap.get(j).add(Float.toString(ans));
							break;
						}
					}
				}
				root3= null;
			}
		}
		try {
			PrintWriter w = new PrintWriter("./src/" + "output.txt", "UTF-8");//creates an output.txt file
			int count= 0;
			for(int i=0; i<=students; i++){
				if(!(papamap.get(i)==null)){
					w.print(i+1 + ": ");
					for(int j=0; j<papamap.get(i).size(); j++){
						w.print(papamap.get(i).get(j));
						w.print(" ");
					}
					w.println();
				}else{
					count++;
				}
			}
			w.println(count-1);//number of chocolates
			w.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
