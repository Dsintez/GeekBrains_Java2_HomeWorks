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
                if (!runners[i].isRetired()){
                    String RunnerAndObstacles = runners[i] + " " + obstacles[j] + " ";
                    switch (obstacles[j].getType()){
                        case WALL:
                            System.out.print(RunnerAndObstacles);
                            runners[i].jump(obstacles[j].getValue());
                            break;
                        case RUNNING_TRACK:
                            System.out.print(RunnerAndObstacles);
                            runners[i].running(obstacles[j].getValue());
                            break;
                    }
                } else {
                    break;
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
