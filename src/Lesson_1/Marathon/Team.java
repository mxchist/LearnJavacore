package Lesson_1.Marathon;

public class Team {
    private Competitor[] teamMembers;
    public Team (String[][] ... name) {
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
}
