public class Knight extends MOB {

    private Fortune activeFortune;

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

   public Fortune getActiveFortune(){
        return this.activeFortune;
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

    public String toString(){
        String formattedString = "";
        formattedString += "+======================+\n";
        formattedString += "|"+getName()+"             |\n";
        formattedString += "|HP:"+getHP()+"             |\n";
        formattedString += "|AC:"+getArmor()+"             |\n";
        formattedString += "|Hit:"+getHitModifier()+"             |\n";
        formattedString += "|Damage:"+getDamageDie()+"             |\n";
        formattedString += "|XP:"+getXP()+"             |\n";
        formattedString += "+======================+\n";
        return formattedString;
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
        
    }
    
}
