package sample;

/**
 * Created by rajat on 06-11-2017.
 */
public class ProcessorThread extends Thread {
    @Override
    public void run() {
        super.run();
        //Keep settling events forever
        while(true) {
            settleEvents();
        }
    }

    public void settleEvents() {
        while (GlobalVariables.pendingInstructionList.size() > 0) {
            ButtonEvent buttonEvent = GlobalVariables.pendingInstructionList.getNext();

            if(buttonEvent.typeOfCall== ButtonEvent.TypeOfCall.OUTSIDEBUTTON){
                if(buttonEvent.status==ButtonEvent.StatusOfEvent.INSTRUCTED){
                    //Find the best Car
                    Car bestCar = findClosestCarPreferablyMovingInSameDirection(buttonEvent.fromfloor,buttonEvent.direction);

                    //Create a movement plan of that Car
                    Movement movement =new Movement(bestCar,buttonEvent.fromfloor);
                    GlobalVariables.root.getChildren().add(movement.path);

                    //Add the movement to the pending Movements
                    GlobalVariables.pendingActionListPerCar.get(bestCar).add(movement);

                }
            }
        }
    }

    public Car findClosestCarPreferablyMovingInSameDirection(Floor fromfloor,ButtonEvent.Direction direction){

        //Select the first car
        Car selectedCar=GlobalVariables.cars.get(0);
        int bestTravelCost=99999;

        //Find one that's currently moving in the right direction( or not moving)
        for(Car currentCar : GlobalVariables.cars.values()){
            int currentCost=99999;
            if(currentCar.direction== ButtonEvent.Direction.UP || currentCar.direction== ButtonEvent.Direction.IDLE){
                currentCost=fromfloor.floorNummber-currentCar.currentFloor.floorNummber;
            }
            else if(currentCar.direction== ButtonEvent.Direction.DOWN || currentCar.direction== ButtonEvent.Direction.IDLE){
                currentCost=currentCar.currentFloor.floorNummber-fromfloor.floorNummber;
            }
            if(currentCost>=0){
                if(currentCost<bestTravelCost){
                    bestTravelCost=currentCost;
                    selectedCar=currentCar;
                }
            }
        }
        return selectedCar;
    }

}
