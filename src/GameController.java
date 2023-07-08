public class GameController extends Object{

    private final GameData data;
    //Game data passed into the constructor.
    private final CombatEngine engine;
    //combat engine, passed into the constructor
    private final GameView view;
    //game view passed into the constructor

    /*
     * public GameController(GameData data,
 GameView view,
 CombatEngine engine)
The GameController needs all major components of the game to work - view, data, and combat
Parameters:
data - GameData - does not care the type (CSV, JSON, etc), just the abstract class
view - GameView - calls the various methods based on feedback from data
engine - runs combat between an active knight group and MOBs
     */

    public GameController(GameData data, GameView view, CombatEngine engine){
        this.data = data;
        this.view = view;
        this.engine = engine;

    }

    /*
     * public void start()
Starts the game, causing it to run until a client exits. Starts with splashScreen, loops as long as processCommand returns true, prints endgame when loop is done.
See Also:
GameView.displayMainMenu()
     */

    public void start(){
        view.splashScreen();
        while(processCommand(view.displayMainMenu())){}
        view.endGame();

    }


    /*
     * protected boolean processCommand(String command)
Setup as a helper method for start() }, processes commands the client passes in through GameView.displayMainMenu(). The following command combinations are allowed:
exit or bye (contains) - causes processCommand to return false
ls or list all - list all knights. For example, view.listKnights(data.getKnights())
list active - list active knights via GameData.getActiveKnights()
show (name or id) - if the command starts with show, take the remainder and show the knight card
set active (name or id) - if command starts with set active, grabs the remainder of the line, and try to add the knight to the active knight list.
remove (name or id) - if command starts with remove, grabs the remainder of the line, attempts to remove the knight from active status.
save (filename - optional) - saves the game. If a file name is provided saves the knights to the file. If a file name is left off, saves out to saveData.csv
explore, adventure or quest - if any three of these words are used, it starts a combat sequence. For example.
 
         engine.initialize();
         engine.runCombat();
         engine.clear();
     }
any other input, print the help menu.
Parameters:
command - command to process.
Returns:
true unless exit or bye is used.
     */

    protected boolean processCommand(String command){
        if(command.contains("exit") || command.contains("bye")){
            return false;
        }
        else if(command.contains("ls") || command.contains("list all")){
            view.listKnights(data.getKnights());
        }
        else if(command.contains("list active")){
            view.listKnights(data.getActiveKnights());
        }
        else if(command.contains("show")){
            String[] parts = command.split(" ");
            if(parts.length == 2){
                try{
                    //int id = Integer.parseInt(parts[1]);
                    view.showKnight(data.getKnight(parts[1]));
                }
                catch(NumberFormatException e){
                    view.showKnight(data.getKnight(parts[1]));
                }
            }
        }
        else if(command.contains("set active")){
            String[] parts = command.split(" ");
            if(parts.length == 3){
                try{
                    //int id = Integer.parseInt(parts[2]);
                    data.setActive(data.getKnight(parts[2]));
                }
                catch(NumberFormatException e){
                    data.setActive(data.getKnight(parts[2]));
                }
            }
        }
        else if(command.contains("remove")){
            String[] parts = command.split(" ");
            if(parts.length == 2){
                try{
                    //int id = Integer.parseInt(parts[1]);
                    data.removeActive(data.getKnight(parts[1]));
                }
                catch(NumberFormatException e){
                    data.removeActive(data.getKnight(parts[1]));
                }
            }
        }
        else if(command.contains("save")){
            String[] parts = command.split(" ");
            if(parts.length == 2){
                data.save(parts[1]);
            }
            else{
                data.save("saveData.csv");
            }
        }
        else if(command.contains("explore") || command.contains("adventure") || command.contains("quest")){
            engine.initialize();
            engine.runCombat();
            engine.clear();
        }
        else{
            view.displayMainMenu();
        }
        return true;

    }

    /*
     * private void processRemoveActive(String remove)
Optional helper method that helped keep processCommand(String) cleaner. removes the night by calling .getActive(), and then removeActive() if the knight exists Prints knightNotFound() if an invalid knight is given.
Parameters:
remove - the id or name of knight to remove
     */

    private void processRemoveActive(String remove){
        Knight knight = data.getActive(remove);
        if(knight != null){
            data.removeActive(knight);
        }
        else{
            view.knightNotFound();
        }
    }


    /*
     * private void processSetActive(String active)
Optional helper method that helped keep processCommand(String) cleaner. sets a knight to 'active' by calling .getKnight(String), and then setActive(Knight) if the knight exists Prints knightNotFound() if an invalid knight is given, and setActiveFailed() if it can't add the knight.
Parameters:
active -
     */

    private void processSetActive(String active){
        Knight knight = data.getKnight(active);
        if(knight != null){
            if(!data.setActive(knight)){
                view.setActiveFailed();
            }
        }
        else{
            view.knightNotFound();
        }
    }


    /*
     * private void processShowKnight(String nameOrId)
Optional helper method that helped keep processCommand(String) cleaner. Uses data.getKnight(nameOrId) to ge ta knight, and then call the showKnight(knight) method in GameView. If the knight wasn't found, print knightNotFound()
Parameters:
nameOrId -
     */

    private void processShowKnight(String nameOrId){
        Knight knight = data.getKnight(nameOrId);
        if(knight != null){
            view.showKnight(knight);
        }
        else{
            view.knightNotFound();
        }
    }

    //test
    public static void main(String[] args){
        GameData data = new CSVGameData("gamedata.csv", "knights.csv");
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }


}