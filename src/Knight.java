public class Knight extends MOB {

    private Fortune activeFortune = null;

    protected final int id;

    protected int xp;

    public Knight(int id, String name, int hp, int armor, int hitmodifier, DiceType damageDie, int xp){
        super(name, hp, armor, hitmodifier, damageDie);
        this.id = id;
        this.xp = xp;
    }

    public void addXP(int xp){
        this.xp += xp;
    }

    /*
     * public Integer getId()
Gets the knights id as an Integer (not int). Integer is used, as toString is often used in other parts other program.
Returns:
the Integer value of the id.
     */

    public Integer getId(){
        return this.id;
    }

   public Fortune getActiveFortune(){
        return this.activeFortune;
    }

    public int getMaxHP(){
        if (getActiveFortune() == null){
            return super.getMaxHP();
        }
        else{
            return super.getMaxHP() + getActiveFortune().getMaxHP();
        }
    }

    public int getID(){
        return this.id;
    }

    public int getXP(){
        return this.xp;
    }

    public void setActiveFortune(Fortune activeFortune){
        this.activeFortune = activeFortune;
    }

    public void setXP(int xp){
        this.xp = xp;
    }


    /*
     * The split for the spacing is:
30 characters wide
Name uses %-27s
id uses %-23d (as a space is required between id: and the value
health, Power - both %-6d
XP %-7d
Armor: %-4d
     */ 
    public String toString(){
        return 
        "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        String.format("| id: %-23d|%n", getID()) +
        "|                            |\n" +
        String.format("| Health: %-6d  XP: %-7d|%n", getHP(), getXP()) +
        String.format("|  Power: %-6d  Armor: %-4d|%n", getHitModifier(), getArmor()) +
        "|                            |\n" +
        "+============================+";
    }


    public int getArmor(){
        if (getActiveFortune() == null){
            return super.getArmor();
        }
        else{
            return super.getArmor() + getActiveFortune().getArmor();
        }
    }

    public DiceType getDamageDie(){
        if (getActiveFortune() == null){
            return super.getDamageDie();
        }
        else{
            return getActiveFortune().getDamageDie();
        }

        
    }

    public int getHitModifier(){
        if (getActiveFortune() == null){
            return super.getHitModifier();
        }
        else{
            return super.getHitModifier() + getActiveFortune().getHitModifier();
        }
    }

    public String toCSV(){
        String formattedString = "";
        formattedString += this.id + ",";
        formattedString += this.getName() + ",";
        formattedString += this.getHP() + ",";
        formattedString += this.getArmor() + ",";
        formattedString += this.getHitModifier() + ",";
        formattedString += this.getDamageDie() + ",";
        formattedString += this.getXP() + ",";
        if (this.getActiveFortune() == null){
            formattedString += "null";
        }
        else{
            formattedString += this.getActiveFortune().getName();
        }
        return formattedString;
    }

    public static void main(String[] args) {
        Knight kn = new Knight(1, "Mik", 10, 20, 30, DiceType.D12, 40);
        System.out.println("TESTING Knight print \n" + kn.toString());
        
    }
    
}
