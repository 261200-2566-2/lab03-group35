public class RPG {
    public  class Characters {
        private String Name;
        private double HP;
        private double MaxHP;
        private double Mana;
        private double MaxMana;
        private double Sword_Base_Damage;
        private double Shield_Base_Defense;
        private double Defense;
        private double Base_RunSpeed;
        private double Max_RunSpeed;
        private double Sword_RunSpeed_Decreased;
        private double Shield_RunSpeed_Decreased;
        private int Level;
        private boolean IsHoldSwordable;
        Sword equippedSword;
        Shield equippedShield;

        Characters(String Name) {
            this.Name = Name;
            HP = 100;
            MaxHP = 100;
            Mana = 50;
            MaxMana = 50;
            Sword_Base_Damage = 2;
            Shield_Base_Defense = 2;
            Defense = 10;
            Base_RunSpeed = 10;
            Max_RunSpeed = 10;
            Sword_RunSpeed_Decreased = 0;
            Shield_RunSpeed_Decreased = 0;
            Level = 1;
            IsHoldSwordable = false;
            equippedSword = null;
            equippedShield = null;
            System.out.println("Hello Newbie " + Name);
        }

        public void levelup() {
            MaxHP = 100 + 10 * Level;
            MaxMana = 50 + (2 * Level);
            Max_RunSpeed = Base_RunSpeed * (0.1 + 0.03 * Level);
            Level = Level + 1;
        }

        public void equipSword(Sword sword) {
            equippedSword = sword;
            Sword_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.04 * Level);
            UpdateRunSpped();
        }

        public void equipShield(Shield shield) {
            equippedShield = shield;
            Shield_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.08 * Level);
            UpdateRunSpped();

        }

        public void unequipSword() {
            equippedSword = null;
            Sword_RunSpeed_Decreased = 0;
            UpdateRunSpped();

        }

        public void unequipShield() {
            equippedShield = null;
            Shield_RunSpeed_Decreased = 0;
            UpdateRunSpped();

        }

        public void UpdateRunSpped() {
            Base_RunSpeed = Base_RunSpeed - Shield_RunSpeed_Decreased - Sword_RunSpeed_Decreased;
        }

        public void printCharacterStats() {
            // Display Characters stats
            System.out.println("Characters Stats after Level Up:");
            System.out.println("Max HP: " + MaxHP);
            System.out.println("Max Mana: " + MaxMana);
            System.out.println("Sword Damage: " + (Sword_Base_Damage + (equippedSword != null ? equippedSword.baseDamage : 0)));
            System.out.println("Shield Defense: " + (Shield_Base_Defense + (equippedShield != null ? equippedShield.baseDefense : 0)));
            System.out.println("Max Run Speed: " + Max_RunSpeed);
        }

        public double calculateDamage(double damage) {
            // If there is an equipped shield, reduce damage based on shield defense
            if (equippedShield != null) {
                double effectiveDamage = damage - equippedShield.baseDefense;
                // Ensure the effective damage is non-negative
                return Math.max(effectiveDamage, 0);
            } else {
                return damage; // No shield, no reduction
            }
        }
    }





    public static void main(String[] args) {
        RPG rpg = new RPG();
        RPG.Characters SCKagura = rpg.new Characters("SCKagura");
        Sword sword = new Sword(1, 5);
        Shield shield = new Shield(1, 5);

        // Initial Characters stats
        SCKagura.printCharacterStats();

        // Equip sword and shield
        SCKagura.equipSword(sword);
        SCKagura.equipShield(shield);

        // Level up and print stats
        SCKagura.levelup();
        SCKagura.printCharacterStats();
        sword.levelUp();
        shield.levelUp();
        sword.printSwordStats();
        shield.printShieldStats();

        // Test damage calculation with shield
        double incomingDamage = 15;
        double effectiveDamage = SCKagura.calculateDamage(incomingDamage);
        System.out.println("Effective Damage after Shield: " + effectiveDamage);

        // Print HP before and after taking damage
        System.out.println("Current HP: " + SCKagura.HP);
        System.out.println("Taking Damage: " + incomingDamage);
        SCKagura.HP -= effectiveDamage;
        System.out.println("Effective HP after Damage: " + SCKagura.HP);

        // Unequip shield and test damage calculation
        SCKagura.unequipShield();
        effectiveDamage = SCKagura.calculateDamage(incomingDamage);
        System.out.println("Effective Damage without Shield: " + effectiveDamage);

        // Print HP before and after taking damage without shield
        System.out.println("Current HP: " + SCKagura.HP);
        System.out.println("Taking Damage: " + incomingDamage);
        SCKagura.HP -= effectiveDamage;
        System.out.println("Effective HP after Damage without Shield: " + SCKagura.HP);

        // Unequip sword and shield and print stats
        SCKagura.unequipSword();
        SCKagura.unequipShield();
        SCKagura.printCharacterStats();
        sword.printSwordStats();
        shield.printShieldStats();
    }

}

class Sword {
    protected int level;
    protected double baseDamage;

    public Sword(int level, double baseDamage) {
        this.level = level;
        this.baseDamage = baseDamage;
    }

    public void levelUp() {
        baseDamage = baseDamage * (1 + (0.1 * level));
    }

    public void printSwordStats() {
        System.out.println("Sword Stats  ");
        System.out.println("Sword Level = " + level);
        System.out.println("Sword baseDamage = " + baseDamage);
    }
}

class Shield {
    protected int level;
    protected double baseDefense;

    public Shield(int level, double baseDefense) {
        this.level = level;
        this.baseDefense = baseDefense;
    }

    public void levelUp() {
        baseDefense = baseDefense * (1 + (0.05 * level));
    }

    public void printShieldStats() {
        System.out.println("Shield Stats");
        System.out.println("Shield Level : " + level);
        System.out.println("Shield baseDefense : " + baseDefense);
    }


}


