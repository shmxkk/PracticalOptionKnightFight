public class MOB implements Attributes {


    protected int armor;    
    protected int maxHP;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    private final String name;

    MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie){
        this.name = name;
        this.maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public int getMaxHP() {
        return this.maxHP;
    }

    @Override
    public DiceType getDamageDie() {
        return this.damageDie;
    }

    @Override
    public int getHitModifier() {
        return this.hitModifier;
    }

    public int getDamage(){
        return this.damage;
    }
    
    public void addDamage(int damage){
        this.damage += damage;
    }

    public MOB copy(){
        return new MOB(this.name, this.maxHP, this.armor, this.hitModifier, this.damageDie);
    }

    public int getHP(){
        return this.maxHP - this.damage;
    }

    public String getName(){
        return this.name;
    }

    public void resetDamage(){
        this.damage = 0;
    }

    public String toString(){
        return 
        "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        "|                            |\n" +
        String.format("|         Health: %-10d |%n", getHP())  +
        String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
        "|                            |\n" +
        "+============================+";
    }

    public static void main(String[] args) {
        MOB m = new MOB("Mik", 10, 20, 30, DiceType.D4);
        System.out.println(m);
    }
}
