package sample;

/**
 * Created by rajat on 06-11-2017.
 */
public class ProcessorCore {

    public void settleEvents() {
        settleInstructions();
        settleActions();
    }

    public void settleInstructions(){
        //TODO If lift already stopping on that floor then dont do anything

        while (GlobalVariables.pendingInstructionList.size() > 0) {
            ButtonEvent buttonEvent = GlobalVariables.pendingInstructionList.get(0);

            if(buttonEvent.typeOfCall== ButtonEvent.TypeOfCall.OUTSIDEBUTTON){
                if(buttonEvent.status==ButtonEvent.StatusOfEvent.INSTRUCTED){
                    //Find the best Car
                    Car bestCar = findClosestCarPreferablyMovingInSameDirection(buttonEvent.fromfloor,buttonEvent.direction);
                    System.out.println(bestCar.currentChannel.channelNumber);

                    //Create a movement plan of that Car
                    Movement movement =new Movement(bestCar,buttonEvent.fromfloor);
                    GlobalVariables.root.getChildren().add(movement.path);

                    //Add the movement to the pending Movements
                    GlobalVariables.pendingActionListPerCar.get(bestCar).add(movement);

                }
            }
            GlobalVariables.pendingInstructionList.remove(0);

        }
    }

    public void settleActions() {
        //For each car, if the current action is complete, then start its next pending action (and remove the completed one)
        for(Car currentCar: GlobalVariables.cars.values()){
            if(GlobalVariables.pendingActionListPerCar.get(currentCar).size()>0) {
                Movement currentMovementForThisCar = GlobalVariables.pendingActionListPerCar.get(currentCar).get(0);
                if (currentMovementForThisCar.isComplete()) {
                    GlobalVariables.pendingActionListPerCar.remove(0);
                    GlobalVariables.pendingActionListPerCar.get(currentCar).get(0).pathTransition.play();
                }
            }
        }
    }


    public Car findClosestCarPreferablyMovingInSameDirection(Floor fromfloor,ButtonEvent.Direction direction){

        //Select the first car
        Car selectedCar=GlobalVariables.cars.get(0);
        ButtonEvent.Direction selectedDirection= ButtonEvent.Direction.IDLE;
        int bestTravelCost=99999;

        //Find one that's currently moving in the right direction( or not moving)
        for(Car currentCar : GlobalVariables.cars.values()){
            int currentCost=99999;
            if(currentCar.direction== ButtonEvent.Direction.UP || currentCar.direction== ButtonEvent.Direction.IDLE){
                currentCost=fromfloor.floorNummber-currentCar.currentFloor.floorNummber;
                selectedDirection= ButtonEvent.Direction.UP;
            }
            else if(currentCar.direction== ButtonEvent.Direction.DOWN || currentCar.direction== ButtonEvent.Direction.IDLE){
                currentCost=currentCar.currentFloor.floorNummber-fromfloor.floorNummber;
                selectedDirection= ButtonEvent.Direction.DOWN;
            }
            if(currentCost>=0){
                if(currentCost<bestTravelCost){
                    bestTravelCost=currentCost;
                    selectedCar=currentCar;
                }
            }
        }
        selectedCar.direction=selectedDirection;
        return selectedCar;
    }

}
