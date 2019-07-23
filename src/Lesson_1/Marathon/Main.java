package Lesson_1.Marathon;

public class Main {
    public static void main(String[] args) {
        Team myTeam = new Team ("Моя команда", new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"), new Human("Женя"));

        //Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Cross cross = new Cross (new Wall(2), new Water(1), new Water(4));

        cross.doIt(myTeam);
        cross.showResult(myTeam);
    }
}