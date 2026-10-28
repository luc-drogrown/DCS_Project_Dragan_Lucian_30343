package FuzzyUVS;

import Components.*;
import DataObjects.DataFuzzy;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.PlaceNameWithWeight;
import Enumerations.FZ;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class ExitLane4 {
    public static void main(String[] args) {
        FLRS flrs_move = new FLRS(
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL)
        );

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Exit Lane 4";
        pn.NetworkPort = 1087;

        DataFuzzy p_e_i4 = new DataFuzzy();
        p_e_i4.SetName("P_e_i4");
        pn.PlaceList.add(p_e_i4);

        DataFuzzy p_end4 = new DataFuzzy();
        p_end4.SetName("P_End4");
        pn.PlaceList.add(p_end4);

        // Transition t_e1 ------------------------------------------------
        PetriTransition t_e4 = new PetriTransition(pn);
        t_e4.TransitionName = "t_e4";
        t_e4.InputPlaceName.add("P_e_i4");

        Condition Te4Ct1 = new Condition(t_e4, "P_e_i4", TransitionCondition.NotNull);

        GuardMapping grdTe4 = new GuardMapping();
        grdTe4.condition = Te4Ct1;

        ArrayList<PlaceNameWithWeight> inputTe4 = new ArrayList<>();
        inputTe4.add(new PlaceNameWithWeight("P_e_i4", 1F));

        ArrayList<String> outputTe4 = new ArrayList<>();
        outputTe4.add("P_End4");

        grdTe4.Activations.add(new Activation(t_e4, flrs_move, inputTe4, TransitionOperation.FLRS, outputTe4));
        t_e4.GuardMappingList.add(grdTe4);

        t_e4.Delay = 0;
        pn.Transitions.add(t_e4);

        // Start GUI Window -----------------------------------------------
        System.out.println("Exit Lane 4 started \n ------------------------------");
        pn.Delay = 1000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
