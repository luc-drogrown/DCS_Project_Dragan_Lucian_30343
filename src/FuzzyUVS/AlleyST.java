package FuzzyUVS;

import Components.*;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.PlaceNameWithWeight;
import DataOnly.TransferOperation;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class AlleyST {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Alley street Petri Net";
        pn.NetworkPort = 1085;

        /* Places */
        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        pn.PlaceList.add(p_i);

        DataTransfer p_out = new DataTransfer();
        p_out.SetName("P_out");
        p_out.Value = new TransferOperation("localhost", "1082", "P_st");
        pn.PlaceList.add(p_out);

        /* Transitions */
        // Tsend ------------------------------------------------
        PetriTransition tsend = new PetriTransition(pn);
        tsend.TransitionName = "T_send";
        tsend.InputPlaceName.add("P_i");

        Condition TsendCt1 = new Condition(tsend, "P_i", TransitionCondition.NotNull);

        GuardMapping grdTsend = new GuardMapping();
        grdTsend.condition = TsendCt1;

        grdTsend.Activations.add(new Activation(tsend, "P_i", TransitionOperation.SendOverNetwork, "P_out"));

        tsend.GuardMappingList.add(grdTsend);
        tsend.Delay=0;
        pn.Transitions.add(tsend);

        System.out.println("AlleyST started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }

}
