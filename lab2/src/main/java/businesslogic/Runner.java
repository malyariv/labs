package businesslogic;

import Interfaces.*;
import dataclasses.Reaction;

import java.util.*;

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
}

