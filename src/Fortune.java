public class Fortune implements Attributes {
    private int armor;
    private DiceType dtype;
    private int hitModifier;
    private int hpBonus;
    private String name;
    

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.dtype = type;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        // return the amount of bonus to the knights maxHP
        return this.hpBonus;
    }

    @Override
    public DiceType getDamageDie() {
        // DiceType of the knight damage die
        return this.dtype;
    }

    @Override
    public int getHitModifier() {
        // Returns the bonus to the knights hit modifier.
        return this.hitModifier;
    }

    
    public String getName() {
        return this.name;
    }

   


    public String toString() {
        return
    "+======================+\n" +
    String.format("|%-22s|%n", getName()) +
    String.format("|HP Bonus: %+12d|%n", getMaxHP()) +
    String.format("|AC Bonus: %+12d|%n", getArmor()) +
    String.format("|Hit Bonus: %+11d|%n", getHitModifier()) +
    String.format("|Damage Adj: %10s|%n", getDamageDie() == null ? "-" : getDamageDie().toString()) +
    "+======================+";
    }



 

    public static void main(String[] args) {
        Fortune ftn = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        System.out.println("TESTING Armor in fortune " + ftn.getArmor());
        System.out.println("TESTING Fortune print \n" + ftn.toString());
    }
    
}
