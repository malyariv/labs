package businesslogic;
/**
 * The class {@code UserInteraction} provides interaction between user
 * and application using console interface.
 */
import dataclasses.ConnectionProblemException;
import dataclasses.Marker;
import dataclasses.QuitException;
import interfaces.IChemicalIObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    /** flag to quit the current task*/
    private boolean quit=false;
    /** a {@code TaskGenerator} object to generate tasks*/
    private TaskGenerator taskGenerator=new TaskGenerator();
    /** a formula of a chemical to be synthesized*/
    private String currentTask;
    /** a list of available chemicals*/
    private List<String> elements;
    /** a {@code Reactor} object where chemical interactions occurs*/
    private Reactor reactor=new Reactor();
    /** a menu for interactions with user */
    private List<String> menu=new ArrayList<>();
    {
        menu.add("add component");
        menu.add("show reactor content");
        menu.add("clear");
        menu.add("mix");
        menu.add("heat");
        menu.add("next task");
        menu.add("quit");
    }

    /**
     * Prints welcome words.
     */
    public void hail() {
        System.out.println("Welcome to chemical lab. Here, you can carry out some reactions to synthesis new compounds.");
        System.out.println("==========================================================================================");
    }
    /**
     * Checks if the current task is completed.
     */
    public boolean quit() {
        if (reactor.getReagents().size()==1){
            IChemicalIObserver chem=(IChemicalIObserver)reactor.getReagents().get(0);
            if (chem.getFormula().equals(currentTask)&&chem.getMarker()!= Marker.SOLUTION){
                System.out.println("You did it!");
                return true;
            }
        }
        return quit;
    }
    /**
     * Prints the generated task.
     */
    public void taskGeneration() {
        currentTask=taskGenerator.generateCompound();
        System.out.println("Your current task is synthesis of "+currentTask+".");
        elements=taskGenerator.generateElements(currentTask);

    }
    /**
     * Prints the available chemicals and reads the chosen one.
     */
    private void chooseReagents() throws ConnectionProblemException {
        System.out.println("Choose one of the following reagents:");
        for (int i=0;i<elements.size();i++){
            System.out.print("\t\t"+(i+1)+") "+elements.get(i));
        }
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            int index=0;
            String string=scanner.nextLine();
            try{
                index = Integer.valueOf(string);
            }catch (NumberFormatException e){
                System.out.println("Wrong input. Try again!");
                continue;
            }
            if (index < 1 || index > elements.size()) {
                System.out.println("Wrong input. Try again!");
            }
            else {
                try{
                    reactor.add(elements.get(index-1));
                }catch (ConnectionProblemException e){
                    System.out.println("Sorry");
                    throw new ConnectionProblemException();
                }
                break;
            }
        }
    }
    /**
     * Decides to print the menu or the list of available reagents.
     * It also throws {@code QuitException } to quit the program.
     */
    public void action() throws QuitException, ConnectionProblemException {
        if (reactor.isEmpty()) chooseReagents();
        else chooseMenu();
    }
    /**
     * Prints the menu and reads the chosen action.
     */
    private void chooseMenu() throws QuitException, ConnectionProblemException {
        System.out.println("Choose one of the following operations:");
        for (int i=0;i<menu.size();i++){
            System.out.print("\t\t"+(i+1)+") "+menu.get(i));
        }
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            int index = 0;
            String string=scanner.nextLine();
            try{
                index = Integer.valueOf(string);
            }catch (NumberFormatException e){
                System.out.println("Wrong input. Try again!");
                continue;
            }
            switch (index) {
                case 1:
                    chooseReagents();
                    break;
                case 2:
                    reactor.showReagents();
                    break;
                case 3:
                    reactor.clear();
                    break;
                case 4:
                    System.out.println("Mixing");
                    reactor.mix();
                    break;
                case 5:
                    System.out.println("Heating");
                    reactor.heat();
                    break;
                case 6:
                    quit = true;
                    break;
                case 7:
                    throw new QuitException();
                default:
                    System.out.println("Wrong input. Try again!");
                    continue;
            }
            break;
        }
    }
    /**
     * Removes all reagents from reactor and resets the flag.
     */
    public void cleaning(){
        quit=false;
        reactor.clear();
        System.out.println("-------------------------------------------------------------------");
    }
}
