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

public class ExitLane1 {
    public static void main(String[] args) {
        FLRS flrs_move = new FLRS(
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL)
        );

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Exit Lane 1";
        pn.NetworkPort = 1085;

        DataFuzzy p_e_i1 = new DataFuzzy();
        p_e_i1.SetName("P_e_i1");
        pn.PlaceList.add(p_e_i1);

        DataFuzzy p_end1 = new DataFuzzy();
        p_end1.SetName("P_End1");
        pn.PlaceList.add(p_end1);

        // Transition t_e1 ------------------------------------------------
        PetriTransition t_e1 = new PetriTransition(pn);
        t_e1.TransitionName = "t_e1";
        t_e1.InputPlaceName.add("P_e_i1");

        Condition Te1Ct1 = new Condition(t_e1, "P_e_i1", TransitionCondition.NotNull);

        GuardMapping grdTe1 = new GuardMapping();
        grdTe1.condition = Te1Ct1;

        ArrayList<PlaceNameWithWeight> inputTe1 = new ArrayList<>();
        inputTe1.add(new PlaceNameWithWeight("P_e_i1", 1F));

        ArrayList<String> outputTe1 = new ArrayList<>();
        outputTe1.add("P_End1");

        grdTe1.Activations.add(new Activation(t_e1, flrs_move, inputTe1, TransitionOperation.FLRS, outputTe1));
        t_e1.GuardMappingList.add(grdTe1);

        t_e1.Delay = 0;
        pn.Transitions.add(t_e1);

        System.out.println("Exit Lane 1 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
