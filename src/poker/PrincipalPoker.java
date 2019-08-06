package poker;

import javax.swing.*;
import java.awt.*;

public class PrincipalPoker {

    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        }
        catch (Exception e) {}
        EventQueue.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        GuiJuego myWindow = new GuiJuego();
                    }
                });
    }

}