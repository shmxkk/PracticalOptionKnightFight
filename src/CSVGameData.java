public class CSVGameData extends GameData{


/*
 * public CSVGameData(String gamedata,
 String saveData)
Constructs the CSVGameData object, by loading CSV files passed into it. Use the protected methods (yes, this can be a two line constructor).
 
   loadGameData(gamedata);
   loadSaveData(saveData);
 
 
For testing, we will often construct CSVGameData with different files than we provided - same format, just different numbers of knights, mobs, fortunes, etc.
Parameters:
gamedata - A game data file containing fortunes and MOBS
saveData - A data file containing knights
 */

    public CSVGameData(String gamedata, String saveData){
        loadGameData(gamedata);
        loadSaveData(saveData);

    }


/*
 * void loadSaveData(String saveData)
Loads in the data from a knights CSV file. Constructs a new Knight and adds it to the Knight List GameData.knights. Starts a counter for the IDs, with each new knight being assigned an ID in order of which they are read from the file While there are many ways to implement this method, but with that said, we wanted provide a skeleton of what we used.

        int counter = 0;
        Scanner file = readFile(saveData);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(","); 
            Knight kt = new Knight(
                    ++counter,
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()),
                    line.nextInt());
            knights.add(kt);
        }
 
DiceType.valueOf was a method that was automatically generated for you as an enum. It takes in a string that looks *EXACTLY* like the enum, and converts it to the enum, so "D4" becomes DiceType.D4
Parameters:
saveData - a file containing knight information
 */

    void loadSaveData(String saveData){
        int counter = 0;
        Scanner file = readFile(saveData);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(","); 
            Knight kt = new Knight(
                    ++counter,
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()),
                    line.nextInt());
            knights.add(kt);
        }
    }

    /*
     * private Scanner readFile(String fileName)
Optional method, but we used it so that we could isolate our try/catch statements to a single method.
Parameters:
fileName - name of file to be read into a scanner
Returns:
A scanner loaded with a FileInputStream
     */

    private Scanner readFile(String fileName){
        try{
            return new Scanner(new FileInputStream(fileName));
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: " + fileName);
            return null;
        }

    }


    /*
     * void loadGameData(String gamedata)
Loads game data based on fortunes or MOBs. first line of the CSV file determines type, the rest loads directly into a MOB or Fortune object. Stores MOBs in GameData.monsters and fortunes into GameData.fortunes. Any line that doesn't start with MOB or FORTUNE is ignored. As a reminder, a gamedata.csv file could look like the following:
        MOB,Kobold,25,9,-1,D4
        MOB,Umber Hulk,55,13,1,D6
        FORTUNE,Prowess,0,0,2,D12
        FORTUNE,Valor,10,0,0,-
 
With the pattern matching name,hp,armor,hitModifier,damage.

Make sure to examine the code provided for loadSaveData()

Parameters:
gamedata - a game data CSV file with MOBs and Fortunes
     */

    void loadGameData(String gamedata){
        Scanner file = readFile(gamedata);
        if(file == null) return;
        while(file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(","); 
            String type = line.next().trim();
            if(type.equals("MOB")) {
                Monster mob = new Monster(
                        line.next().trim(),
                        line.nextInt(),
                        line.nextInt(),
                        line.nextInt(),
                        DiceType.valueOf(line.next()));
                monsters.add(mob);
            } else if(type.equals("FORTUNE")) {
                Fortune ftn = new Fortune(
                        line.next().trim(),
                        line.nextInt(),
                        line.nextInt(),
                        line.nextInt(),
                        DiceType.valueOf(line.next()));
                fortunes.add(ftn);
            }
        }
    }


    /*
     * private void parseGameDataLine(Scanner line)
Optional helper method. We used this method to handle the actual parsing of each line of the gamedata.csv. You may also further break this up into two private methods, one for MOB and one for FORTUNE lines. That could help keep your code concise.
     */

    private void parseGameDataLine(Scanner line){
        String type = line.next().trim();
        if(type.equals("MOB")) {
            Monster mob = new Monster(
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()));
            monsters.add(mob);
        } else if(type.equals("FORTUNE")) {
            Fortune ftn = new Fortune(
                    line.next().trim(),
                    line.nextInt(),
                    line.nextInt(),
                    line.nextInt(),
                    DiceType.valueOf(line.next()));
            fortunes.add(ftn);
        }
    }

    /*
     * public void save(String filename)
Saves out the knight data as a CSV to the given filename. Simply loop through every knight, and print out the result of toCSV() for each knight!
Specified by:
save in class GameData
Parameters:
filename - name of file to save knights out to
See Also:
Knight.toCSV()
     */

    public void save(String filename){
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(filename));
            for(Knight knight : knights) {
                out.println(knight.toCSV());
            }
            out.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: " + filename);
        }
    }

}