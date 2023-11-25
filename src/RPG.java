public class RPG {
    public static class Character {
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

        public Character(String Name) {
            this.Name = Name;
            HP = 100;
            MaxHP = 100;
            Mana = 50;
            MaxMana = 50;
            Sword_Base_Damage = 10;
            Shield_Base_Defense = 10;
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
            Sword_Base_Damage = Sword_Base_Damage * (1 + (0.1 * Level));
            Shield_Base_Defense = Shield_Base_Defense * (1 + 0.05 * Level);
            Max_RunSpeed = Base_RunSpeed * (0.1 + 0.03 * Level);
            Sword_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.04 * Level);
            Shield_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.08 * Level);
            Level = Level + 1;
        }

        public void equipSword(Sword sword) {
            equippedSword = sword;
            Sword_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.04 * Level);
        }

        public void equipShield(Shield shield) {
            equippedShield = shield;
            Shield_RunSpeed_Decreased = Base_RunSpeed * (0.1 + 0.08 * Level);
        }

        public void unequipSword() {
            equippedSword = null;
            Sword_RunSpeed_Decreased = 0;
        }

        public void unequipShield() {
            equippedShield = null;
            Shield_RunSpeed_Decreased = 0;
        }

        public void printCharacterStats() {
            // Display character stats
            System.out.println("Character Stats after Level Up:");
            System.out.println("Max HP: " + MaxHP);
            System.out.println("Max Mana: " + MaxMana);
            System.out.println("Sword Damage: " + (Sword_Base_Damage + equippedSword.baseDamage));
            System.out.println("Shield Defense: " + (Shield_Base_Defense + equippedShield.baseDefense));
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
            baseDamage = baseDamage * (1 + 0.1 * level);
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
            baseDefense = baseDefense * (1 + 0.05 * level);
        }
    }

    public static void main(String[] args) {
        Character SCKagura = new Character("SCKagura");
        Sword sword = new Sword(1, 10);
        Shield shield = new Shield(1, 10);

        SCKagura.equipSword(sword);
        SCKagura.equipShield(shield);

        // Perform level up
        SCKagura.levelup();
        SCKagura.printCharacterStats();

    }
}
