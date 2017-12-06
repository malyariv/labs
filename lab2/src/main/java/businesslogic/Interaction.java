package businesslogic;

import data.Marker;
import interfaces.Chemicals;

import java.util.*;

public class Interaction {
    private Scanner scanner=new Scanner(System.in);
    private Reactor<Chemicals> reactor=new Reactor<>();
    private ChemFactory factory=new ChemFactory();
    private List<Chemicals> chemicalsList;
    private boolean flag=false;
    private List<String> menu=new ArrayList<>();
    private Chemicals need;


    public void printReagents(){
        System.out.println("Choose one of the chemical from the list below:");
        for (int i=1;i<=chemicalsList.size();i++){
            System.out.print("\t"+i+") "+chemicalsList.get(i-1));
        }
        System.out.println();
    }
    public void read(){
        int i=scanner.nextInt()-1;
        analyze(i);
    }
    public void printMenu(){
        System.out.println("You can carry out the following operations. Choose one and enter its number.");
        for (int i=1;i<=menu.size();i++){
            System.out.print("\t"+i+") "+menu.get(i-1));
        }
        System.out.println();

    }
    public void taskGeneration(){
        need=factory.getSalt();
        System.out.println("You task is synthesis of "+need.getFormula());
        chemicalsList=factory.getReagents(need);
    }

    public boolean ends() {
        return flag;
    }

    public void printMsg() {
        logic();
        if (menu.size()==1) {
            addReagent();
            printMsg();
        }
        else printMenu();
    }
    private void logic(){
        menu.clear();
        int size=reactor.getReagentsAmount();
        switch (size){
            case 0: menu.add("add"); break;
            case 2: menuFiller2(); break;
            default: menuFiller1();
        }
    }
    private void analyze(int i){
        if (menu.size()==1) {
            reactor.add(chemicalsList.get(i));
            return;
        }
        switch (menu.get(i)){
            case "next task": flag=true; break;
            case "evaporation": reactor.evaporation(); break;
            case "mix": reactor.mix(); break;
            case "clear": reactor.clear();
            default: addReagent();
        }
        if (reactor.getReagents().size()==1
                &&reactor.getReagents().get(0).equals(need)) {
            System.out.println("Congratulations!\n");
            flag=true;
        }
    }
    private void addReagent(){
        printReagents();
        int i=scanner.nextInt()-1;
        reactor.add(chemicalsList.get(i));
    }

    private  void menuFiller1(){
        menu.add("add");
        menu.add("clear");
        Marker m=reactor.getReagents().get(0).getMarker();
        if (m==Marker.SOLUTION||m==Marker.WATER){
            menu.add("evaporation");
        }
        menu.add("next task");
    }

    private void menuFiller2(){
        menu.add("mix");
        menu.add("clear");
        List<Chemicals> list=reactor.getReagents();
        Set<Marker> set=new HashSet<>();
        set.add(list.get(0).getMarker());
        set.add(list.get(1).getMarker());
        if (set.contains(Marker.WATER)||set.contains(Marker.SOLUTION)){
            menu.add("evaporation");
        }
        menu.add("next task");
    }

}
