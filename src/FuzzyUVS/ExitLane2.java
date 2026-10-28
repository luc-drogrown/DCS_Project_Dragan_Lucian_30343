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

public class ExitLane2 {
    public static void main(String[] args) {
        FLRS flrs_move = new FLRS(
                new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL)
        );

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Exit Lane 2";
        pn.NetworkPort = 1086;

        DataFuzzy p_e_i2 = new DataFuzzy();
        p_e_i2.SetName("P_e_i2");
        pn.PlaceList.add(p_e_i2);

        DataFuzzy p_end2 = new DataFuzzy();
        p_end2.SetName("P_End2");
        pn.PlaceList.add(p_end2);

        // Transition t_e1 ------------------------------------------------
        PetriTransition t_e2 = new PetriTransition(pn);
        t_e2.TransitionName = "t_e2";
        t_e2.InputPlaceName.add("P_e_i2");

        Condition Te2Ct1 = new Condition(t_e2, "P_e_i2", TransitionCondition.NotNull);

        GuardMapping grdTe2 = new GuardMapping();
        grdTe2.condition = Te2Ct1;

        ArrayList<PlaceNameWithWeight> inputTe2 = new ArrayList<>();
        inputTe2.add(new PlaceNameWithWeight("P_e_i2", 1F));

        ArrayList<String> outputTe2 = new ArrayList<>();
        outputTe2.add("P_End2");

        grdTe2.Activations.add(new Activation(t_e2, flrs_move, inputTe2, TransitionOperation.FLRS, outputTe2));
        t_e2.GuardMappingList.add(grdTe2);

        t_e2.Delay = 0;
        pn.Transitions.add(t_e2);

        // Start GUI Window -----------------------------------------------
        System.out.println("Exit Lane 2 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
