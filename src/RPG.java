public class RPG {
    public static   class Characters {
        String Name;
        double HP;
        double MaxHP;
        double Mana;
        double MaxMana;
        double Sword_Base_Damage;
        double Shield_Base_Defense;
        double Defense;
        double Base_RunSpeed;
        double Max_RunSpeed;
        double Sword_RunSpeed_Decreased;
        double Shield_RunSpeed_Decreased;
        int Level;
        boolean IsHoldSwordable;
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
            UpdateRunSpped() ;
        }

        public void equipShield(Shield shield) {
            equippedShield = shield;
            Shield_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.08 * Level);
            UpdateRunSpped() ;

        }

        public void unequipSword() {
            equippedSword = null;
            Sword_RunSpeed_Decreased = 0;
            UpdateRunSpped() ;

        }

        public void unequipShield() {
            equippedShield = null;
            Shield_RunSpeed_Decreased = 0;
            UpdateRunSpped() ;

        }
        public void UpdateRunSpped() {
            Base_RunSpeed =     Base_RunSpeed - Shield_RunSpeed_Decreased - Sword_RunSpeed_Decreased ;
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
    }


    public static class Sword {
        int level;
        double baseDamage;

        public Sword(int level, double baseDamage) {
            this.level = level;
            this.baseDamage = baseDamage;
        }

        public void levelUp() {
            baseDamage = baseDamage * (1 + (0.1 * level));
        }
        public void printSwordStats(Characters Characters) {
            if (Characters.equippedSword != null) {
                System.out.println("Sword Stats:");
                System.out.println("Level: " + Characters.equippedSword.level);
                System.out.println("Base Damage: " + Characters.equippedSword.baseDamage);
            } else {
                System.out.println("No sword equipped.");
            }
        }
    }

    public static class Shield {
        int level;
        double baseDefense;

        public Shield(int level, double baseDefense) {
            this.level = level;
            this.baseDefense = baseDefense;
        }

        public void levelUp() {
            baseDefense = baseDefense * (1 + (0.05 * level));
        }
        public void printShieldStats(Characters Characters) {
            if (Characters.equippedShield != null) {
                System.out.println("Shield Stats:");
                System.out.println("Level: " + Characters.equippedShield.level);
                System.out.println("Base Defense: " + Characters.equippedShield.baseDefense);
            } else {
                System.out.println("No shield equipped.");
            }
        }
    }


    public static void main(String[] args) {
        Characters SCKagura = new Characters("SCKagura");
        Sword sword = new Sword(1, 10);
        Shield shield = new Shield(1, 10);

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
        sword.printSwordStats(SCKagura);
        shield.printShieldStats(SCKagura);

        // Unequip sword and shield and print stats
        SCKagura.unequipSword();
        SCKagura.unequipShield();
        SCKagura.printCharacterStats();
        sword.printSwordStats(SCKagura);
        shield.printShieldStats(SCKagura);
    }
}

