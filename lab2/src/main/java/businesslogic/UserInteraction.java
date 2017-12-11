package businesslogic;
/**
 * The class {@code UserInteraction} provides interaction between user
 * and application using console interface.
 */
import dataclasses.Marker;
import dataclasses.QuitException;
import interfaces.ChemicalObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {

    private boolean quit=false;
    private TaskGenerator taskGenerator=new TaskGenerator();
    private String currentTask;
    private List<String> elements;
    private Reactor reactor=new Reactor();
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


    public void hail() {
        System.out.println("Welcome to chemical lab. Here, you can carry out some reactions to synthesis new compounds.");
        System.out.println("==========================================================================================");
    }

    public boolean quit() {
        if (reactor.getReagents().size()==1){
            ChemicalObserver chem=(ChemicalObserver)reactor.getReagents().get(0);
            if (chem.getFormula().equals(currentTask)&&chem.getMarker()!= Marker.SOLUTION){
                System.out.println("You did it!");
                return true;
            }
        }
        return quit;
    }

    public void taskGeneration() {
        currentTask=taskGenerator.generateCompound();
        System.out.println("Your current task is synthesis of "+currentTask+".");
        elements=taskGenerator.generateElements(currentTask);

    }
    private void chooseReagents(){
        System.out.println("Choose one of the following reagents:");
        for (int i=0;i<elements.size();i++){
            System.out.print("\t\t"+(i+1)+") "+elements.get(i));
        }
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            int index = scanner.nextInt();
            if (index < 1 || index > elements.size()) {
                System.out.println("Try again");
            }
            else {
                reactor.add(elements.get(index-1));
                break;
            }
        }
    }
    public void action() throws QuitException {

        if (reactor.isEmpty()) chooseReagents();
        else chooseMenu();
    }

    private void chooseMenu() throws QuitException {
        System.out.println("Choose one of the following operations:");
        for (int i=0;i<menu.size();i++){
            System.out.print("\t\t"+(i+1)+") "+menu.get(i));
        }
        System.out.println();
        Scanner scanner=new Scanner(System.in);
        while (true) {
            int index = scanner.nextInt();
            switch (index){
                case 1: chooseReagents(); break;
                case 2: reactor.showReagents(); break;
                case 3: reactor.clear(); break;
                case 4:
                    System.out.println("Mixing");
                    reactor.mix(); break;
                case 5:
                    System.out.println("Heating");
                    reactor.heat();
                    break;
                case 6: quit=true; break;
                case 7: throw new QuitException();
                default:
                    System.out.println("Try again!");
                    continue;
            }
            break;
        }
    }

    public void cleaning(){
        quit=false;
        reactor.clear();
        System.out.println("-------------------------------------------------------------------");
    }
}
