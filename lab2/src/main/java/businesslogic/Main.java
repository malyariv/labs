package businesslogic;
/**
 * The class {@code Main} is a console application simulating chemical laboratory
 * where user can some operations with chemical reagents.
 */
import dataclasses.ConnectionProblemException;
import dataclasses.QuitException;

public class Main {
    /**
     * Runs the application. I
     */
    public static void main(String[] args) {
        new Main().run();
    }
    /**
     * Runs the application by generation of {@code UserInteraction} object
     * and using its methods. I
     */
    private void run() {
        UserInteraction userInteraction=new UserInteraction();
        userInteraction.hail();
        try {
            while (true) {
                userInteraction.taskGeneration();
                while (!userInteraction.quit()) {
                    userInteraction.action();
                }
                userInteraction.cleaning();
            }
        }catch (QuitException e){
            System.out.println("Bye!");
        }catch (ConnectionProblemException e){}
    }
}
