import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled("Тест временно отключен.")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main() throws Exception {
        String[] args=null;
        Main.main(args);
    }
}