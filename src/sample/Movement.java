package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.PathTransitionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.util.Duration;

/**
 * Created by rajat on 04-11-2017.
 */
public class Movement {
    Path path;
    PathTransition pathTransition;

    Movement(Car car, Floor endFloor){
        path=PathBuilder.create()
                .elements(
                        new MoveTo(GlobalVariables.marginLeft+ car.boxXPos, car.boxYPos), new LineTo(GlobalVariables.marginLeft+ car.boxXPos,endFloor.height)
                ).build();
        path.setStroke(Color.DODGERBLUE);
        //path.getStrokeDashArray().setAll(5d,5d);

        int floorsToMove=Math.abs(car.currentFloor.floorNummber-endFloor.floorNummber);
        System.out.println(floorsToMove);

        pathTransition = PathTransitionBuilder.create()
                .duration(Duration.seconds(floorsToMove))
                .path(path)
                .node(car)
                .orientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT)
                .cycleCount(1)//Timeline.INDEFINITE)
                .autoReverse(false)
                .build();
    }


    public Boolean isComplete(){
        if(pathTransition.getStatus()== Animation.Status.STOPPED)
            return true;
        return false;
    }
}
