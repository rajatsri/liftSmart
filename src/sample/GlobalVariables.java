package sample;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rajat on 05-11-2017.
 */
public class GlobalVariables {

    public static Group root = new Group();

    public static int sceneWidth=2000;
    public static int sceneHeight=1000;

    public static int marginLeft=100;
    public static int margintop=100;

    public static int numberOfFloors=20;
    public static int numberOfChannels=5;
    public static int numberOfBoxes=5;

    public static HashMap<Integer,Floor> floors= new HashMap<Integer,Floor>();
    public static HashMap<Integer,LiftChannel> channels= new HashMap<Integer,LiftChannel>();
    public static HashMap<Integer, Car> cars= new HashMap<Integer, Car>();

    public static PendingInstructionList pendingInstructionList = new PendingInstructionList();

    public static HashMap<Car,ArrayList<Movement>> pendingActionListPerCar= new HashMap<Car,ArrayList<Movement>>();

    //public static

}
