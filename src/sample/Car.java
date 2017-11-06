package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by rajat on 04-11-2017.
 */
public class Car extends Rectangle {

    LiftChannel currentChannel;
    Floor currentFloor;
    ButtonEvent.Direction direction;

    int boxXPos;
    int boxYPos;

    Car(LiftChannel liftChannel, Floor floor){
        super(GlobalVariables.marginLeft, 0, 30, 20);

        this.currentChannel=liftChannel;
        this.currentFloor=floor;

        this.boxXPos=currentChannel.channelNumber*30;
        this.boxYPos=(int)currentFloor.height;

        this.direction= ButtonEvent.Direction.IDLE;

        setX(boxXPos);
        setY(boxYPos);

        setArcHeight(10);
        setArcWidth(10);
        setFill(Color.ORANGE);
    }
}
