package problemofdrunks.field.visualization;

import ru.spbau.se.main.field.core.Coordinate;
import ru.spbau.se.main.field.core.Field;
import ru.spbau.se.main.field.visualization.FieldPrinter;
import ru.spbau.se.main.objects.FieldObject;

import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 04.06.12
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class OptimisticFieldPrinter implements FieldPrinter {
    //===Fields============================================================
    private Field field;
    private PrintStream stream;
    //===/Fields===========================================================

    //===Constructors======================================================
    public OptimisticFieldPrinter(Field field, PrintStream stream) {
        this.field = field;
        this.stream = stream;
    }
    //===/Constructors=====================================================

    //===Methods===========================================================
    @Override
    public void print() {

        printTopLine();
        stream.println();
        for(int i = 0; i < field.getRowRange(); ++i) {
            printLeftSymbol(i);

            for(int j = 0; j < field.getColumnRange(); ++j)
                printCell(field.getFieldObject(new Coordinate(i, j)));

            printRightSymbol(i);
            stream.println();
        }

        printBottomLine();
        stream.println();

    }

    public void printTopLine() {
        for(int i = 0; i <= field.getColumnRange() + 1; ++i) {
            if(i == 9) stream.print('Т');
            else stream.print(' ');
        }
    }

    public void printLeftSymbol(int i) {
        stream.print(' ');
    }

    public void printRightSymbol(int i) {
        if(i == 3) stream.print('П');
        else if(i == 13) stream.print('M');
        else stream.print(' ');
    }

    public void printBottomLine() {
        for(int i = 0; i <= field.getColumnRange() + 1; ++i) {
            if(i == 4) stream.print('Т');
            else stream.print(' ');
        }
    }

    public void printCell( FieldObject object) {
        if(object == null) stream.print('0');
        else stream.print(object.toString().charAt(0));
    }
    //===/Methods==========================================================

    //===Setters and Getters===============================================
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public PrintStream getStream() {
        return stream;
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }
    //===/Setters and Getters==============================================
}

