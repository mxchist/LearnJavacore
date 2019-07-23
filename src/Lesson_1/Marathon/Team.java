package Lesson_1.Marathon;

public class Team {
    private Competitor[] teamMembers;
    private String commandName;

    public Team (String teamName; String[] ... name) {
        for (int i = 0; i < name.length; i++) {
            if (name[i][0].equals("Кот") ) {
                teamMembers[i] = new Cat(name[i][1].toString() );
            }
            else if (name[i][0].equals("Пес") ) {
                teamMembers[i] = new Cat(name[i][1].toString());
            }
            else if (name [i][0].equals("Хуман")) {
                teamMembers[i] = new Human(name[i][1].toString());
            }
        }
    }

    public Competitor[] GetTeamMember () {
        return this.teamMembers;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public String getCommandName() {
        return commandName;
    }
}
