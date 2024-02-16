import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        Throwable exceptionEmptyList = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exceptionEmptyList.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3),
                new Horse("Pegasus1", 2.9),
                new Horse("Pegasus2", 2.9),
                new Horse("Pegasus3", 2.9),
                new Horse("Pegasus4", 2.9),
                new Horse("Pegasus5", 2.9),
                new Horse("Pegasus6", 2.9),
                new Horse("Pegasus7", 2.9),
                new Horse("Pegasus8", 2.9),
                new Horse("Pegasus9", 2.9),
                new Horse("Pegasus10", 2.9),
                new Horse("Pegasus11", 2.9),
                new Horse("Pegasus12", 2.9),
                new Horse("Pegasus13", 2.9),
                new Horse("Pegasus14", 2.9),
                new Horse("Pegasus15", 2.9),
                new Horse("Pegasus16", 2.9),
                new Horse("Pegasus17", 2.9),
                new Horse("Pegasus18", 2.9),
                new Horse("Pegasus19", 2.9),
                new Horse("Pegasus20", 2.9),
                new Horse("Pegasus21", 2.9),
                new Horse("Pegasus22", 2.9),
                new Horse("Pegasus23", 2.9)

        );
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> horsesNew = hippodrome.getHorses();
        Assertions.assertEquals(horses, horsesNew);
        Assertions.assertNotNull(horsesNew);
    }

    @Test
    void move() {
        List<Horse> list = new ArrayList<>();
        Horse mockHorse = Mockito.mock(Horse.class);

        for (int i = 0; i < 50; i++) {
            list.add(mockHorse);
        }
        Mockito.doNothing().when(mockHorse).move();
        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();
        Mockito.verify(mockHorse, Mockito.times(50)).move();

    }

    @Test
    void getWinner() throws InterruptedException {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3),
                new Horse("Pegasus1", 2.9),
                new Horse("Pegasus2", 2.9),
                new Horse("Pegasus3", 2.9),
                new Horse("Pegasus4", 2.9),
                new Horse("Pegasus5", 2.9),
                new Horse("Pegasus6", 2.9),
                new Horse("Pegasus7", 2.9),
                new Horse("Pegasus8", 2.9),
                new Horse("Pegasus9", 2.9),
                new Horse("Pegasus10", 2.9),
                new Horse("Pegasus11", 2.9),
                new Horse("Pegasus12", 2.9),
                new Horse("Pegasus13", 2.9),
                new Horse("Pegasus14", 2.9),
                new Horse("Pegasus15", 2.9),
                new Horse("Pegasus16", 2.9),
                new Horse("Pegasus17", 2.9),
                new Horse("Pegasus18", 2.9),
                new Horse("Pegasus19", 2.9),
                new Horse("Pegasus20", 2.9),
                new Horse("Pegasus21", 2.9),
                new Horse("Pegasus22", 2.9),
                new Horse("Pegasus23", 5.9)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            TimeUnit.MILLISECONDS.sleep(200);
        }
        Horse winner = hippodrome.getWinner();
        double maxDistance = hippodrome.getHorses().stream()
                .max((h1, h2) -> (int) (h1.getDistance() - h2.getDistance()))
                .get()
                .getDistance();
        Assertions.assertEquals(maxDistance, winner.getDistance());

    }
}