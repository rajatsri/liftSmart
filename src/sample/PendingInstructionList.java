package sample;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;

/**
 * Created by rajat on 06-11-2017.
 */
public class PendingInstructionList extends ArrayList<ButtonEvent> {

    int currentPointer=-1;

    ButtonEvent getNext(){
        if(currentPointer==this.size()-1)
            currentPointer=0;
        else
            currentPointer++;
        return this.get(currentPointer);
    }

}
