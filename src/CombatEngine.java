public class CombatEngine extends Object{


    private final GameData data;
    //The data for the game, passed in as part of the constructor.
    private final DiceSet dice;
    //A DiceSet to be used for the Combat engine
    private final Random rnd;
    //Used in runCombat() to select who is fighting who.
    private final GameView view;
    //The View used for the game, passed in as part of the constructor.

    /*
     * public CombatEngine(GameData data,
 GameView view)
To run the combat, a GameData and GameView is essential for it to work. It is designed to use classes that implement the GameView and GameData interfaces
Parameters:
data - a game dataset
view - a game view
     */

    public CombatEngine(GameData data, GameView view){
        this.data = data;
        this.view = view;
        this.dice = new DiceSet();
        this.rnd = new Random();
    }

    /*
     * public void initialize()
Before every quest, active knights are assigned random fortunes (GameData.getRandomFortune(). Once a fortune is assigned to each active knight, call GameView.printFortunes(List) Hints: Loops through getActiveKnights() and setActiveFortune using a getRandomFortune() from data.
     */

    public void initialize(){
        for(Knight knight : data.getActiveKnights()){
            knight.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(data.getActiveKnights());
    }

    /*
     * public void runCombat()
Runs the combat simulation (optional).
This method is NOT graded - and is purely a "challenge" for you to implement.

Combat will continue to run as long as there are either knights or monsters/MOBs. If MOBs are reduced to zero, the player will be promoted to see if they wish to continue exploring GameView.checkContinue(). If they respond yes, more random monsters will be generated, and combat begins again. At the start of each battle:

Generates a random list of MOBs, no larger than the total number of knights GameData.getRandomMonsters()
Prints the battle text, on who the fight is between GameView.printBattleText(List, List)
Runs through the combat

The combat order itself is undefined on order of actions, but the following must happen
When knights are defeated (MOB.getHP() <= 0), they are removed from active knights
When MOBs are defeated, every active knight earns 1 XP point (Knight.addXP(int))
While combat order is undefined, a common implementation is cycle through the knights having them attack a random monster. We then cycle through the MOBs having them each attack a random knight.
When a knight or mob is defeated, we print that they were defeated GameView.printBattleText(MOB)
If all knights are defeated, we notify the player using GameView.printDefeated().

Calculating Hits
To calculate a successful hit, you roll a D20 (DiceSet.roll(DiceType) take that value, add the MOBs/Knights toHitModifier. If the value is greater than the armor value, they score a hit, and the damage die is rolled.

     D20 + hitModifier > armor  (successful hit formula)
 

Upon a successful strike, the damage die is rolled to determine the amount of damage the opponent takes Hint to students: private helper methods are extremely helpful here. As is helps break up the above steps. Make sure to take it in small parts, printing out in each step.
See Also:
DiceSet.roll(DiceType)
     */

    public void runCombat(){
        while(data.getActiveKnights().size() > 0 && data.getActiveMonsters().size() > 0){
            List<MOB> monsters = data.getRandomMonsters();
            view.printBattleText(data.getActiveKnights(), monsters);
            for(Knight knight : data.getActiveKnights()){
                MOB mob = monsters.get(rnd.nextInt(monsters.size()));
                if(knight.attack(mob, dice)){
                    view.printBattleText(mob);
                }
            }
            for(MOB mob : monsters){
                Knight knight = data.getActiveKnights().get(rnd.nextInt(data.getActiveKnights().size()));
                if(mob.attack(knight, dice)){
                    view.printBattleText(knight);
                }
            }
            for(Knight knight : data.getActiveKnights()){
                if(knight.getHP() <= 0){
                    data.removeActive(knight);
                    view.printBattleText(knight);
                }
            }
            for(MOB mob : monsters){
                if(mob.getHP() <= 0){
                    for(Knight knight : data.getActiveKnights()){
                        knight.addXP(1);
                    }
                    view.printBattleText(mob);
                }
            }
        }
        if(data.getActiveKnights().size() == 0){
            view.printDefeated();
        }
    }


    /*
     * private int doBattle(List<MOB> attackers,
 List<MOB> defenders)
Helper method. May not be needed depending on how you implement runCombat().
Parameters:
attackers - - can be either the knights or the monsters (depending on who is attacking who)
defenders - - can be either the knights or the monsters
Returns:
number of victories the 'attackers' have over the defenders
     */

    private int doBattle(List<MOB> attackers, List<MOB> defenders){
        int victories = 0;
        for(MOB attacker : attackers){
            for(MOB defender : defenders){
                if(attacker.attack(defender, dice)){
                    victories++;
                }
            }
        }
        return victories;

    }


    /*
     * public void clear()
Sets all fortunes to *null* across all knights. It is easier to just loop through all Knights setting the fortune to null, simply because activeKnights can be harder to track after combat is done.
See Also:
Knight.setActiveFortune(Fortune)
GameData.getKnights()
     */

    public void clear(){
        for(Knight knight : data.getKnights()){
            knight.setActiveFortune(null);
        }
    }

    


}