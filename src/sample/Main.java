package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //private PathTransition pathTransition;
    //private PathTransition pathTransition1;
    Movement movement;
    Movement movement1;
    Movement movement3;
    Movement movement4;
    Movement movement5;

    private void init(Stage primaryStage) {
        Group root = GlobalVariables.root;
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, GlobalVariables.sceneWidth,GlobalVariables.sceneHeight));


        layFloors(root);
        createChannels(GlobalVariables.numberOfChannels);
        createCars(root);
        GlobalVariables.initiallizeThings();





    }

    private void createCars(Group root) {
        for(int i=0;i<GlobalVariables.numberOfBoxes;i++){

            //Initiallize all cars at random floors
            //Car liftbox = new Car(GlobalVariables.channels.get(i),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));

            //Initialize all cars at lowest floor
            Car liftbox = new Car(GlobalVariables.channels.get(i),GlobalVariables.floors.get(0));

            root.getChildren().add(liftbox);
            GlobalVariables.cars.put(i,liftbox);
            /*movement =new Movement(liftbox,GlobalVariables.floors.get((int)Math.random()*GlobalVariables.numberOfFloors));
            root.getChildren().add(movement.path);*/
        }
    }

    public void layFloors(Group root){
        for(int i=0;i<GlobalVariables.numberOfFloors;i++){
            Floor floor= new Floor(i,root);
            GlobalVariables.floors.put(i,floor);
        }
    }

    public void createChannels(int numberOfChannels){
        for(int i=0;i<numberOfChannels;i++){
            LiftChannel liftChannel = new LiftChannel(i);
            GlobalVariables.channels.put(i,liftChannel);
        }
    }

    public void play() {
       // movement.pathTransition.play();

       /* movement =new Movement(GlobalVariables.cars.get(1),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));
        GlobalVariables.root.getChildren().add(movement.path);

        movement1 =new Movement(GlobalVariables.cars.get(1),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));
        GlobalVariables.root.getChildren().add(movement1.path);

        movement3 =new Movement(GlobalVariables.cars.get(3),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));
        GlobalVariables.root.getChildren().add(movement3.path);

        movement4 =new Movement(GlobalVariables.cars.get(4),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));
        GlobalVariables.root.getChildren().add(movement4.path);

        movement5 =new Movement(GlobalVariables.cars.get(0),GlobalVariables.floors.get((int)(Math.random()*GlobalVariables.numberOfFloors)));
        GlobalVariables.root.getChildren().add(movement5.path);*/



    /*    movement1.pathTransition.play();
        movement3.pathTransition.play();
        movement5.pathTransition.play();
        movement.pathTransition.play();
        movement4.pathTransition.play();*/

        //System.out.print(movement.pathTransition.getStatus());

        PendingInstructionList p= GlobalVariables.pendingInstructionList;

        //Few button Presses from the floors
        p.add(new ButtonEvent(GlobalVariables.floors.get(4), ButtonEvent.Direction.UP));
        p.add(new ButtonEvent(GlobalVariables.floors.get(6), ButtonEvent.Direction.DOWN));


        //few Button presses from inside the elevator cars
        p.add(new ButtonEvent(GlobalVariables.floors.get(9), GlobalVariables.cars.get(0)));
        p.add(new ButtonEvent(GlobalVariables.floors.get(3), GlobalVariables.cars.get(1)));
        p.add(new ButtonEvent(GlobalVariables.floors.get(2), GlobalVariables.cars.get(1)));


        ProcessorCore processorCore= new ProcessorCore();
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            processorCore.settleEvents();
                        }
                    });
                    i++;
                    Thread.sleep(10);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();






        //movement.pathTransition.stop();

      //  movement1.pathTransition.play();
        //movement1.pathTransition.stop();

      //  movement3.pathTransition.play();
        //movement3.pathTransition.stop();
    }

    @Override public void stop() {

    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX
     * application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
