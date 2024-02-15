import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.9));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.9));
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @Test
    void constructorNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -2.9));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -2.9));
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }

    @Test
    void constructorNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 2.9, -1.0));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 2.9, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "\n"})
    void parameterizedConstructor(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 2.9));
        ;
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("Bucephalus", 2.4);
        assertEquals("Bucephalus", horse.getName());

    }

    @Test
    void getSpeed() {
        double speed = 2.4;
        Horse horse = new Horse("Bucephalus", speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double distance = 5.4;
        Horse horse = new Horse("Bucephalus", 2.4, distance);
        assertEquals(distance, horse.getDistance());
        Horse horse1 = new Horse("Bucephalus", 2.4);
        assertEquals(0, horse1.getDistance());
    }

    @Test
    void move() {

        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            //Horse.getRandomDouble(0.2,0.9);
            Horse horse = new Horse("Bucephalus", 2.4);
            horse.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.2,0.9,1.5"})
    void getRandomDouble(double min, double max, double rVal) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            //Horse.getRandomDouble(0.2,0.9);
            Horse horse = new Horse("Bucephalus", 2.4);
            mockedHorse.when(() -> Horse.getRandomDouble(min, max)).thenReturn(rVal);
            double result = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(min, max);
            horse.move();
            double newResult = horse.getDistance();
            //assertEquals(1.5,Horse.getRandomDouble(min,max));
            assertEquals(result, newResult);
        }
    }
}