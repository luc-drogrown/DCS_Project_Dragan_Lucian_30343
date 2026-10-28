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

public class ExitLane3 {
    public static void main(String[] args) {
        FLRS flrs_move = new FLRS(
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL)
        );

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Exit Lane 3";
        pn.NetworkPort = 1087;

        DataFuzzy p_e_i3 = new DataFuzzy();
        p_e_i3.SetName("P_e_i3");
        pn.PlaceList.add(p_e_i3);

        DataFuzzy p_end3 = new DataFuzzy();
        p_end3.SetName("P_End3");
        pn.PlaceList.add(p_end3);

        // Transition t_e1 ------------------------------------------------
        PetriTransition t_e3 = new PetriTransition(pn);
        t_e3.TransitionName = "t_e3";
        t_e3.InputPlaceName.add("P_e_i3");

        Condition Te3Ct1 = new Condition(t_e3, "P_e_i3", TransitionCondition.NotNull);

        GuardMapping grdTe3 = new GuardMapping();
        grdTe3.condition = Te3Ct1;

        ArrayList<PlaceNameWithWeight> inputTe3 = new ArrayList<>();
        inputTe3.add(new PlaceNameWithWeight("P_e_i3", 1F));

        ArrayList<String> outputTe3 = new ArrayList<>();
        outputTe3.add("P_End3");

        grdTe3.Activations.add(new Activation(t_e3, flrs_move, inputTe3, TransitionOperation.FLRS, outputTe3));
        t_e3.GuardMappingList.add(grdTe3);

        t_e3.Delay = 0;
        pn.Transitions.add(t_e3);

        // Start GUI Window -----------------------------------------------
        System.out.println("Exit Lane 3 started \n ------------------------------");
        pn.Delay = 1000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
