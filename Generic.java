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
				PrintWriter w = new PrintWriter("./src/" + i + ".txt", "UTF-8");
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


class Node<T extends Comparable<T>>{
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

class BST<T extends Comparable<T>>{
	ArrayList<Object> list= new ArrayList<Object>();
	
	public Node<T> Insert(Node<T> root, T val){
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
	
	public void Inorder(Node<T> root){
		if(root!= null){
			Inorder(root.getLeft());
			list.add(root.getData());
			Inorder(root.getRight());
		}
	}
}

public class Generic{
	static Node<String> root1= null;
	static Node<Integer> root2= null;
	static Node<Float> root3= null;
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc= new Scanner(System.in);
		int trees= sc.nextInt();
		int students= sc.nextInt();
		Map<Integer, ArrayList<String>> papamap= new HashMap<Integer, ArrayList<String>>(students + 1);//JAVA COLLECTION FRAMEWORK USED!
		BSTFilesBuilder.createBSTFiles(students, trees);
		for(int i=0; i<trees; i++){
			Scanner in= new Scanner(new FileReader("./src/" + i + ".txt"));
			String s= in.next();
			int n= in.nextInt();
			if(s.equals("String")){
				String head = null;
				BST<String> stree= new BST<String>();
				for(int j=0; j<n; j++){
					String a= sc.next();
					if(j==0){
						head= a;
					}
					root1= stree.Insert(root1, a);
				}
				stree.Inorder(root1);
				String ans= "";
				for(int j=0; j<n; j++){
					ans.concat((String)stree.list.get(j));
				}
				for(int j=0; j<n; j++){
					if(stree.list.get(j).equals(head)){
						papamap.get(j+1).add(ans);
						break;
					}
				}
				root1= null;
			}else if(s.equals("Integer")){
				int head = 0;
				BST<Integer> stree= new BST<Integer>();
				for(int j=0; j<n; j++){
					int a= sc.nextInt();
					if(j==0){
						head= a;
					}
					root2= stree.Insert(root2, a);
				}
				stree.Inorder(root2);
				int ans= 0;
				for(int j=0; j<n; j++){
					ans+= (int)stree.list.get(j);
				}
				for(int j=0; j<n; j++){
					if((int)stree.list.get(j)==head){
						papamap.get(j+1).add(Integer.toString(ans));
						break;
					}
				}
				root2= null;
			}else{
				float head = 0;
				BST<Float> stree= new BST<Float>();
				for(int j=0; j<n; j++){
					float a= sc.nextFloat();
					if(j==0){
						head= a;
					}
					root3= stree.Insert(root3, a);
				}
				stree.Inorder(root3);
				float ans= 0;
				for(int j=0; j<n; j++){
					ans+= (float)stree.list.get(j);
				}
				for(int j=0; j<n; j++){
					if((float)stree.list.get(j)==head){
						papamap.get(j+1).add(Float.toString(ans));
						break;
					}
				}
				root3= null;
			}
		}
		try {
			PrintWriter w = new PrintWriter("./src/" + "output.txt", "UTF-8");
			int count= 0;
			for(int i=0; i<=students; i++){
				if(!(papamap.get(i+1).isEmpty())){
					w.print(i+1 + ": ");
					for(int j=0; j<papamap.get(i+1).size(); j++){
						w.print(papamap.get(i+1).get(j));
						w.print(" ");
					}
					w.println();
				}else{
					count++;
				}
			}
			w.println(count);
			w.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
