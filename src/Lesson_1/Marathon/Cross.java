package Lesson_1.Marathon;

public class Cross  {
    private Obstacle obstacles[] = {new Wall(10), new Water(10)};

    public Cross(Obstacle[] obstacle) {        this.obstacles = obstacle;    }

    public void doIt(Competitor... competitor) {
        for (int c = 0; c < competitor.length && c.isOnDistance(); c++) {
            for (int o = 0; o < obstacles.length; o++) {
                o.doIt(c);
            }
            c.info();
            c++;
        }
    }
}