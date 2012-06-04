package problemofdrunks.manager.impl;

import problemofdrunks.objects.moving.Inspector;
import problemofdrunks.objects.moving.OptimisticLazar;
import ru.spbau.se.main.field.core.Coordinate;
import ru.spbau.se.main.field.core.Field;
import ru.spbau.se.main.manager.GameManager;
import ru.spbau.se.main.manager.ObjectManager;
import ru.spbau.se.main.manager.impl.GameManagerImpl;
import ru.spbau.se.main.manager.impl.ObjectManagerImpl;
import ru.spbau.se.main.objects.InteractiveObject;
import ru.spbau.se.main.objects.impl.movable.Lazar;
import ru.spbau.se.main.objects.impl.movable.PoliceOfficer;
import ru.spbau.se.main.objects.impl.statical.Lamp;
import ru.spbau.se.main.objects.impl.statical.Pivot;
import ru.spbau.se.main.objects.impl.statical.Pub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 04.06.12
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class OptimisticGameManager implements GameManager {
    //===Fields============================================================
    private ObjectManager objectManager;
    private Field field;
    private OptimisticLazar lazar;
    private Pub pub;
    private int lazarHasWhenPubIsLocked = 0;
    //===/Fields===========================================================

    //===Constructors======================================================
    public OptimisticGameManager(Field field, ObjectManager objectManager) {
        this.objectManager = objectManager;
        this.field = field;

        init();
    }
    //===/Constructors=====================================================

    //===Methods===========================================================
    public void step() {
        List<InteractiveObject> objects = new ArrayList<InteractiveObject>(objectManager.getInteractiveObjectsList());
        for (InteractiveObject object: objects) {
            object.step();
        }

        boolean pubIsLocked = true;
        for (InteractiveObject object: objects) {
            if(pub == object)
                pubIsLocked = false;
        }

        if(pubIsLocked == false)
            lazarHasWhenPubIsLocked = lazar.getFund();
    }
    //===/Methods==========================================================

    //===Internal Methods==================================================
    private void init() {
        Coordinate pubDoor = new Coordinate(0, 9);
        int drunkManFrequency = 20;
        pub = new Pub(field, pubDoor, drunkManFrequency, objectManager);
        objectManager.addToInteractiveList(pub);


        Coordinate pivotPos = new Coordinate(7,7);
        field.setFieldObject(new Pivot(pivotPos));

        Coordinate lampPos = new Coordinate(3, 10);
        Lamp lamp = new Lamp(field, lampPos, 3);
        field.setFieldObject(lamp);

        Coordinate policeDepartmentDoor = new Coordinate(3,14);
        objectManager.addToInteractiveList(new PoliceOfficer(field, lamp, policeDepartmentDoor));


        Coordinate lazarHomePos = new Coordinate(14, 4);
        int partingTime = 40;
        int superBottleVisionRad = 5;
        lazar = new OptimisticLazar(field, lazarHomePos, partingTime, superBottleVisionRad);
        objectManager.addToInteractiveList(lazar);


        Coordinate healthMinistryEntrance = new Coordinate(13, 14);
        Inspector inspector = new Inspector(field, healthMinistryEntrance, objectManager, 2, 40);
        objectManager.addToInteractiveList(inspector);
    }
    //===/Internal Methods=================================================

    //===Setters and Getters===============================================
    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getLazarFund() {
        return lazarHasWhenPubIsLocked;
    }
    //===/Setters and Getters==============================================
}