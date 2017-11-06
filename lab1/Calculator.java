package lab1;

import java.util.*;

public class Calculator {

	
	public static void main(String[] args) {
		new Calculator().run();
	}
	private void run() {
		Tree tree;
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("Enter your expression");
			String s=scanner.nextLine();
			if(s.equals("exit")) return;
			
			tree=new Tree(s);
			if (!tree.check()) {
				System.out.println("Wrong expression");
				continue;
			}
			System.out.println("="+tree.calculate());
		}
	}

}
