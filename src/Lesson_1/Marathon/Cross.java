package Lesson_1.Marathon;

public class Cross  {
    private Obstacle[] obstacles; // = {new Wall(10), new Water(10)};

    public Cross(Obstacle... obstacle) {        this.obstacles = obstacle;    }

    public void doIt(Team team) {
        Competitor[] competitor = team.getTeam();
        for (int c = 0; c < competitor.length && competitor[c].isOnDistance(); c++) {
            for (int o = 0; o < obstacles.length; o++) {
                obstacles[o].doIt( competitor[c] );
            }
            competitor[c].info();
            c++;
        }
    }

    public void showResult(Team team) {
        System.out.println("Название команды: " + team.getCommandName());
        Competitor[] competitor = team.getTeam();
        for (int c = 0; c < competitor.length && competitor[c].isOnDistance(); c++) {
            competitor[c].info();
            c++;
        }
    }
}