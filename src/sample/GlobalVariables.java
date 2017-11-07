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

    public static int numberOfFloors=10;
    public static int numberOfChannels=2;
    public static int numberOfBoxes=2;

    public static HashMap<Integer,Floor> floors= new HashMap<Integer,Floor>();
    public static HashMap<Integer,LiftChannel> channels= new HashMap<Integer,LiftChannel>();
    public static HashMap<Integer, Car> cars= new HashMap<Integer, Car>();

    public static PendingInstructionList pendingInstructionList = new PendingInstructionList();

    public static HashMap<Car,ArrayList<Movement>> pendingActionListPerCar= new HashMap<Car,ArrayList<Movement>>();

    public static void initiallizeThings(){
        //create a blank list of pending actions for each car
        for(Car c:cars.values()){
            ArrayList<Movement> thisCarsList = new ArrayList<Movement>();
            pendingActionListPerCar.put(c,thisCarsList);
        }
    }

    //public static

}
