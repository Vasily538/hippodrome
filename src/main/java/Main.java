import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info(String.format("Начало скачек. Количество участников: %s",horses.size()));
        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");
        logger.info(String.format("Окончание скачек. Победитель: %s",winnerName));
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
