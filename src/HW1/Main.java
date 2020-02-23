package HW1;

import java.util.Random;

public class Main {
    private static final int notJustANumber = 100;

    public static void main(String[] args) {
        Random random = new Random();
        IActions[] runners = new IActions[notJustANumber];
        IObstacle[] obstacles = new IObstacle[notJustANumber];

        /**
         * Инициализируем массивы препядствий и бегунов
         */
        for (int i = 0; i < notJustANumber; i++) {
            if (random.nextInt(2) == 1) {
                obstacles[i] = new RunningTrack(random.nextInt(80));
            } else {
                obstacles[i] = new Wall(random.nextInt(80));
            }

            switch (random.nextInt(3)){
                case 0:
                    runners[i] = new Human("Человек");
                    break;
                case 1:
                    runners[i] = new Cat("Кот");
                    break;
                case 2:
                    runners[i] = new Robot("Я робот");
                    break;
            }
        }

        /**
         * Каждый учасник пытается пройти препядствия
         */
        for (int i = 0; i < notJustANumber; i++) {
            for (int j = 0; j < notJustANumber; j++) {
                if (!runners[j].isRetired()){
                    switch (obstacles[i].getType()){
                        case Wall:
                            System.out.print(runners[j] + " ");
                            runners[j].jump(obstacles[i].getValue());
                            break;
                        case RunningTrack:
                            System.out.print(runners[j] + " ");
                            runners[j].running(obstacles[i].getValue());
                            break;
                    }
                }
            }
        }

        /**
         * Выводим учасников, которые справились с препядствиями
         */
        for (int i = 0; i < notJustANumber; i++) {
            if ((!runners[i].isRetired())){
                System.out.println(runners[i] + " финишировал!");
            }
        }
    }
}
