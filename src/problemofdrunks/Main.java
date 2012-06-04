package problemofdrunks;

import problemofdrunks.field.visualization.OptimisticFieldPrinter;
import problemofdrunks.manager.impl.OptimisticGameManager;
import ru.spbau.se.main.field.core.impl.CartesianField;
import ru.spbau.se.main.field.core.Field;
import ru.spbau.se.main.field.core.Coordinate;
import ru.spbau.se.main.field.core.impl.HexaconalField;
import ru.spbau.se.main.field.visualization.FieldPrinter;
import ru.spbau.se.main.field.visualization.impl.FieldPrinterImpl;
import ru.spbau.se.main.manager.GameManager;
import ru.spbau.se.main.manager.impl.GameManagerImpl;
import ru.spbau.se.main.manager.impl.ObjectManagerImpl;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 04.06.12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    static public void main(String[] args) {

        Field field = new CartesianField(15,15);

        OptimisticGameManager gameManager = new OptimisticGameManager(field, new ObjectManagerImpl());

        OptimisticFieldPrinter fieldPrinter = new OptimisticFieldPrinter(field, System.out);

        for (int i = 0; i < 800;  ++i) {

            if ((i % 100) == 0) {
                System.out.println("Xoд "+Integer.toString(i));
                fieldPrinter.print();
            }
            gameManager.step();
        }

        System.out.println("Нищий накопил:  "+Integer.toString(gameManager.getLazarFund()));
    }
}
