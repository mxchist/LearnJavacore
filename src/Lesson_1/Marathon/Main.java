package Lesson_1.Marathon;

public class Main {
    public static void main(String[] args) {
        Team myTeam = new Team ("Имя команды", new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"), new Human("Женя"));

        //Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Cross course = new Cross (new Wall(2), new Water(1));
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        for (Competitor c : competitors) {
            c.info();
        }
    }
}