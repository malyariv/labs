package businesslogic;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run(){
        System.out.println("Welcome to chemical lab. We will study neutralisation. " +
                "You need to obtain different salts using alkalies and acids.");
        while (true){
            Interaction interaction=new Interaction();
            interaction.taskGeneration();
            while(!interaction.ends()){
                interaction.printMsg();
                interaction.read();
            }
        }
    }

}
