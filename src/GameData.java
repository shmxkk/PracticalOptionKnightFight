public abstract class GameData extends OBject{

    protected final List<Knight> activeKnights;
    //List of the active knights, they are references, not copies.

    protected final List<Fortune> fortunes;
    //List of fortunes.

    protected final List<Knight> knights;
    //List of all the knights available

    private static final int MAX_ACTIVE;
    //the max number of active knights (defined by specifications to be 4)

    protected final List<MOB> monsters;
    //List of MOBs/Monsters

    private static final Random random;
    //Random number generator, used for grabbing random items for the structures.

    public GameData(){
        this.activeKnights = new ArrayList<Knight>();
        this.fortunes = new ArrayList<Fortune>();
        this.knights = new ArrayList<Knight>();
        this.monsters = new ArrayList<MOB>();
    }

 
    public List<Knight> getKnights(){
        return this.knights;
    }

    public List<Knight> getActiveKnights(){
        return this.activeKnights;
    }

    public Knight getActive(String nameOrId){
        return findKnight(nameOrId, getActiveKnights());
    }

    /*
     * public Knight getKnight(String nameOrId)
Gets an knight from the all knights list based on a string or id. The string can be word in the knights name, and will return the first knight that it comes across that matches that string. The id is supposed to be unique, and will find the knight with that idea, immediately returning the knight. Uses findKnight to accomplish the task.
Parameters:
nameOrId - string or ID as a string
Returns:
the knight if it exists, or null if it is not found
See Also:
findKnight(String, List)
     */

    public Knight getKnight(String nameOrId){
        return findKnight(nameOrId, getKnights());
    }
   

    /*
     * protected Knight findKnight(String nameOrId,
 List<Knight> list)
Finds a knight based on nameOrId based on the a List of knights passed into it. The name can be any part of the name (contains), but the ID must exactly match. Case does not matter for names. Note for students: getId() returns an Integer (not int), so you can call toString, and just compare Strings. That is valid, no need to parse.
Parameters:
nameOrId - a name or id string
list - the list of knights to search - often knights or activeKnights
Returns:
the single knight if found, or null if not found.
See Also:
Knight.getId() MOB.getName()
     */

    protected Knight findKnight(String nameOrId, List<Knight> list){
        for (Knight knight : list){
            if (knight.getName().toLowerCase().contains(nameOrId.toLowerCase())){
                return knight;
            }
            else if (knight.getId().toString().equals(nameOrId)){
                return knight;
            }
        }
        return null;
    }

    public boolean setActive(Knight kt){
        if (getActiveKnights().size() < MAX_ACTIVE){
            getActiveKnights().add(kt);
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * public void removeActive(Knight kt)
Removes a knight from the activeKnights list and resets the damage on the knight! Remember, list.remove returns true if the remove was successful.
Parameters:
kt - knight to remove
See Also:
MOB.resetDamage()
     */
    /**/
    public void removeActive(Knight kt){
        if (getActiveKnights().remove(kt)){
            kt.resetDamage();
        }
    }

    /*
     * public Fortune getRandomFortune()
Gets a random fortune from fortunes Since fortunes.size() gives you the total fortunes, and random.nextInt(N) gives you a random number between 0-(N-1), combine them!
Returns:
a Fortune from the fortunes list
     */

    public Fortune getRandomFortune(){
        return fortunes.get(random.nextInt(fortunes.size()));
    }
        /*
         * public List<MOB> getRandomMonsters()
Gets a random monster from monsters assuming the max number of monsters is less than or equal to activeKnights.size(). Careful about an OB1 error here!
Returns:
a list of MOBs no greater than activeKnights.size()
         */

    public List<MOB> getRandomMonsters(){
        List<MOB> mobs = new ArrayList<MOB>();
        for (int i = 0; i < activeKnights.size(); i++){
            mobs.add(monsters.get(random.nextInt(monsters.size())));
        }
        return mobs;
    }

    /*
     * public List<MOB> getRandomMonsters(int number)
Builds a list of random monsters of size number. Note, that monsters should be copied into the return List, so they can be modified individually.
Parameters:
number - the number of monsters to randomly grab and copy
Returns:
a list of MOB/monsters (copies)
See Also:
MOB.copy()
     */

    public List<MOB> getRandomMonsters(int number){
        List<MOB> mobs = new ArrayList<MOB>();
        for (int i = 0; i < number; i++){
            mobs.add(monsters.get(random.nextInt(monsters.size())).copy());
        }
        return mobs;
    }

    /*
     * public abstract void save(String filename)
Required for the implementing class to be able to save the file
Parameters:
filename - name of file to save
     */

    public abstract void save(String filename);










}