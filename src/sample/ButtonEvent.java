package sample;

/**
 * Created by rajat on 06-11-2017.
 * Represents one event of Button press by a passenger. It may be a button outside the lift or inside the lift
 */
public class ButtonEvent {
    public Floor fromfloor;
    public Floor toFloor;
    public Car car;
    public TypeOfCall typeOfCall;
    public StatusOfEvent status;
    public Direction direction;
    //int numberOfPassengers;

    enum TypeOfCall{
        OUTSIDEBUTTON,INSIDEBUTTON
    }
    enum StatusOfEvent{
        INSTRUCTED,CARSTARTED,CARREACHED,COMPLETED;
    }
    enum Direction{
        UP,DOWN,IDLE
    }

    ButtonEvent(Floor floor,Direction direction){
            this.typeOfCall= TypeOfCall.OUTSIDEBUTTON;
            this.fromfloor = floor;
            this.status=StatusOfEvent.INSTRUCTED;
            this.direction=direction;
    }

    ButtonEvent(Floor floor,Car car){
            this.typeOfCall=TypeOfCall.INSIDEBUTTON;
            this.toFloor = floor;
            this.status=StatusOfEvent.INSTRUCTED;
            this.car=car;
    }

}
