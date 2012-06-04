package problemofdrunks.objects.moving;

import ru.spbau.se.main.field.core.Coordinate;
import ru.spbau.se.main.field.core.Field;
import ru.spbau.se.main.objects.impl.movable.Lazar;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 04.06.12
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
public class OptimisticLazar extends Lazar {
    //===Fields==========================================================
    private int fund;
    //===/Fields==========================================================

    //===Constructors==========================================================
    public OptimisticLazar(Field f, Coordinate lazarsBungalow, int drinkingTime, int vigilance) {
        super(f, lazarsBungalow, drinkingTime, vigilance);
        fund = 0;
    }

    public OptimisticLazar(Field f, Coordinate lazarsBungalow, int drinkingTime, int vigilance, int fund) {
        super(f, lazarsBungalow, drinkingTime, vigilance);
        this.fund = fund;
    }
    //===/Constructors==========================================================

    //===Methods==========================================================
    public void clearCurrentPosition() {
        super.clearCurrentPosition();
        ++fund;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }
    //===Methods==========================================================
}
