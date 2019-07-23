package Lesson_1.Marathon;

public class Team {
    private Competitor[] teamMembers;
    private String teamName;

    public Team (String teamName, Competitor ... teamMembers) {
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }

    public Competitor[] getTeam () {
        return this.teamMembers;
    }

    public void setCommandName(String commandName) {
        this.teamName = commandName;
    }
    public String getTeamName() {
        return teamName;
    }
}
