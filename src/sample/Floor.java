package sample;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * Created by rajat on 04-11-2017.
 */
public class Floor {
    int floorNummber;
    double height;
    Floor(int floorNummber, Group root){
        this.floorNummber=floorNummber;
        this.height=(1000-50*floorNummber);

        Rectangle floorRectangle= new Rectangle(GlobalVariables.marginLeft-20,1000-(floorNummber+1)*50+25,500,48);
        root.getChildren().add(floorRectangle);

    }
}
