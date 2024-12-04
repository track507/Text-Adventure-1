import java.util.HashMap;
import java.util.Map;
// Made by Chibuikem

class Skill {
    private String name;
    private String description;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

class Skills {
    private Map<String, Skill> skills;

    public Skills() {
        skills = new HashMap<>();
        // Start with some basic skills
        skills.put("swing", new Skill("Swing", "A basic slash attack with your sword"));
        skills.put("block", new Skill("Block", "Brace yourself to reduce damage"));
        skills.put("parry", new Skill("Parry", "Deflect an incoming attack"));
        skills.put("slash", new Skill("Slash", "A powerful slash attack"));
        skills.put("thrust", new Skill("Thrust", "A powerful thrust attack with your sword"));
        skills.put("overhead", new Skill("Overhead", "A powerful downward attack"));
    }

    public String showSkills() {
        StringBuilder skillsList = new StringBuilder("Available Skills\n");
        for (Skill skill : skills.values()) {
            skillsList.append("- ").append(skill.getName()).append(": ").append(skill.getDescription()).append("\n");
        }
        return skillsList.toString();
    }

    public void listSkills() {
        // List all available skills
        System.out.println(showSkills());
    }

    public void learnSkill(String skillName, String description) {
        // Add a new skill to the skill set
        String skillKey = skillName.toLowerCase();
        if (skills.containsKey(skillKey)) {
            System.out.println("You already know the skill: " + skillName);
        } else {
            skills.put(skillKey, new Skill(skillName, description));
            System.out.println("You have learned a new skill: " + skillName);
        }
    }

    public void useSkill(String skillName) {
        // Attempt to use a skill
        String skillKey = skillName.toLowerCase();
        if (skills.containsKey(skillKey)) {
            System.out.println("You use " + skillName + ": " + skills.get(skillKey).getDescription());
        } else {
            System.out.println("You haven't learned the skill: " + skillName);
        }
    }
}

// public class Attack {
//     public static void main(String[] args) {
//         Skills playerSkills = new Skills();

//         // List starting skills
//         playerSkills.listSkills();

//         // Learn a new skill
//         playerSkills.learnSkill("Thrust", "A piercing attack aimed at the enemy's weak spot");

//         // List skills after learning a new one
//         playerSkills.listSkills();

//         // Use a skill
//         playerSkills.useSkill("Swing");

//         // Attempt to use a skill not learned yet
//         playerSkills.useSkill("Overhead");
//     }
// }
