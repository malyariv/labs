package lab2;

import java.io.*;
import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.util.*;
import javax.tools.*;

public class Runner {

	public static void main(String[] args) {
		System.out.println("Welcome to my lab. Here you can synthesize different compounds.Enjoy!\n"
				+ "Enter exit to quit.");
		Scanner scanner=new Scanner(System.in);
		String s=null;
		while (true) {
			s=scanner.nextLine();
			if (s.equals("exit")) {
				break;
			}
			Reaction reaction=ReactionLib.getReaction(s);
			IFPreparation preparation=new Preparation();
			IFReactor reactor=new Reactor();
			reactor.execute(reaction,preparation.getReagents(reaction));
		}


	}
/*	private void classGenerator(String s) {
		StringBuilder sb=new StringBuilder(s);
		sb.append(".java");
		String filename=sb.toString();
		sb=new StringBuilder();
		sb.append("package lab2;\npublic class ");
		sb.append(s);
		sb.append("{\n}");
		try(FileWriter fw=new FileWriter(filename)){
			fw.write(sb.toString());
		}catch(Exception e) {}
		
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
	}
*/
}
