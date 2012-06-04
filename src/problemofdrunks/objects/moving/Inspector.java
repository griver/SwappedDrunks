package problemofdrunks.objects.moving;

import ru.spbau.se.main.field.core.Coordinate;
import ru.spbau.se.main.field.core.Field;
import ru.spbau.se.main.manager.ObjectManager;
import ru.spbau.se.main.objects.FieldObject;
import ru.spbau.se.main.objects.InteractiveObject;
import ru.spbau.se.main.objects.impl.FieldObjectImpl;
import ru.spbau.se.main.objects.impl.movable.DrunkMan;
import ru.spbau.se.main.objects.impl.movable.algorithms.move.NextPositionProducer;
import ru.spbau.se.main.objects.impl.movable.algorithms.move.PathFounder;
import ru.spbau.se.main.objects.impl.movable.algorithms.move.RandomStep;
import ru.spbau.se.main.objects.impl.statical.Pub;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 04.06.12
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class Inspector extends FieldObjectImpl implements InteractiveObject {
    //===Fields==========================================================
    private int photos;
    private int needPhotos;
    private int range;
    private Set<DrunkMan> drunkMans;

    private Coordinate home;
    private ObjectManager manager;
    //===Fields==========================================================

    //===Constructors==========================================================
    public Inspector(Field field, Coordinate home, ObjectManager manager, int range, int needPhotos) {
        super(home);
        drunkMans = new HashSet<DrunkMan>();

        this.range = range;
        this.needPhotos = needPhotos;
        this.photos = 0;
        this.home = home;
        this.field = field;

        setConsoleView("^");
        field.setFieldObject(this);
    }
    //===/Constructors==========================================================

    //===Methods==========================================================
    @Override
    public void step() {
        if(needPhotos > photos) {
            randomStep();
            scanForDrunks();
        } else if(isArrived()) {
            afterArriving();
        } else {
            goHome();
        }
    }

    @Override
    public void onMeet(FieldObject t) {
        if (t == null) {

            clearCurrentPosition();
            moveToNewPosition();
        }
    }

    @Override
    public String toString() {
        return "^";
    }

    //===/Methods==========================================================

    //===Internal Methods==========================================================
    private void randomStep() {
        NextPositionProducer nextPositionProducer = new RandomStep(field, getPosition());
        Coordinate t = nextPositionProducer.getNextPosition();
        setNewPosition(t);
        onMeet(field.getFieldObject(t));
    }

    private void whenSeesDrunkMan(DrunkMan drunkMan) {
        if(drunkMans.contains(drunkMan))
            return;
        if(drunkMan.toString() == "&" || drunkMan.toString() == "1") {
            ++photos;
            drunkMans.add(drunkMan);
        }
    }

    private void scanForDrunks() {
        int r = getRaw();
        int c = getColumn();

        LinkedList<Coordinate> bottleList = new LinkedList<Coordinate>();

        for (int x = r - range; x <= r + range; ++x) {
            for (int y = c - range; y <= c + range; ++y) {
                Coordinate coord = new Coordinate(x, y);

                if (field.isAvaliable(coord)) {
                    FieldObject object = field.getFieldObject(coord);

                    if (object instanceof DrunkMan)
                        whenSeesDrunkMan((DrunkMan) object);
                }
            }
        }

    }

    private boolean isArrived() {
        return (getPosition().getRaw() == home.getRaw()) && (getPosition().getColumn() == home.getColumn());
    }

    private void  afterArriving() {
        clearCurrentPosition();
        List<InteractiveObject> objects = manager.getInteractiveObjectsList();
        for(InteractiveObject obj : manager.getInteractiveObjectsList()) {
            if(obj instanceof Pub)
                objects.remove(obj);
        }
    }

    private void goHome() {
        PathFounder pathFounder = new PathFounder(field, getPosition(), home);
        Coordinate newPos = pathFounder.getNextPosition();
        if (newPos != null) {
            setNewPosition(newPos);
            onMeet(field.getFieldObject(newPos));
        }
    }
    //===/Internal Methods==========================================================
}
