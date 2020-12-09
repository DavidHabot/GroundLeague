import com.davidhabot.groundleague.render.screen.Screen;
import com.davidhabot.groundleague.render.screen.ScreenController;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class ScreenControllerTest {
    @Test
    public void testScreenController() {
        ScreenController screen = new ScreenController("#Test", new Screen(1920, 1080));
        screen.setVisible(true);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setPreferredSize(new Dimension(screen.getScreen().getWidth(), screen.getScreen().getHeight()));
        screen.pack();
        while (true);
    }
}
