package Lesson_1.Marathon;

public class Team {
    private Competitor[] teamMembers;
    private String commandName;

    public Team (String commandName, Competitor ... teamMembers) {
        this.commandName = commandName;
        this.teamMembers = teamMembers;
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
