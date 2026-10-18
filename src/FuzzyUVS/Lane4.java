package FuzzyUVS;

import Components.*;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.*;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Lane4 {
    public static void main(String[] args) throws FileNotFoundException {
        //Main petri net declaration for Lane 1
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri Lane 4";
        pn.SetInputFile("D:/petri.txt");


        //FLRS move operation
        FLRS flrs_move= new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),new FV(FZ.PL));

        //Input place to lane
        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        pn.PlaceList.add(p_i);

        //lane places
        DataFuzzy p_l = new DataFuzzy();
        p_l.SetName("P_l");
        pn.PlaceList.add(p_l);

        //lane 2 place
        DataFuzzy p_l2 = new DataFuzzy();
        p_l2.SetName("P_l2");
        pn.PlaceList.add(p_l2);

        //Controller place
        DataFuzzy p_tl = new DataFuzzy();
        p_tl.SetName("P_TL");
        pn.PlaceList.add(p_tl);

        //Green place
        DataFuzzy green = new DataFuzzy();
        green.SetName("Green");
        green.SetValue(new Fuzzy(0.0F));
        pn.ConstantPlaceList.add(green);

        //Output place to intersection
        DataTransfer p_o = new DataTransfer();
        p_o.SetName("Pout");
        p_o.Value = new TransferOperation("localhost","1080","P_i4");
        pn.PlaceList.add(p_o);

        //Transitions
        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P_i");

        Condition T1Ct1 = new Condition(t1, "P_i", TransitionCondition.NotNull);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("P_i", 1F));

        ArrayList<String> OutputT1 = new ArrayList<>();
        OutputT1.add("P_l");

        grdT1.Activations.add(new Activation(t1, flrs_move, inputT1, TransitionOperation.FLRS, OutputT1));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P_l");

        Condition T2Ct1 = new Condition(t2, "P_l", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("P_l", 1F));

        ArrayList<String> OutputT2 = new ArrayList<>();
        OutputT2.add("P_l2");

        grdT2.Activations.add(new Activation(t2, flrs_move, inputT2, TransitionOperation.FLRS, OutputT2));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T_out ------------------------------------------------
        PetriTransition t_out = new PetriTransition(pn);
        t_out.TransitionName = "T_out";
        t_out.InputPlaceName.add("P_l2");
        t_out.InputPlaceName.add("P_TL");

        Condition TOutCt1 = new Condition(t_out, "P_l2", TransitionCondition.NotNull);
        Condition TOutCt2 = new Condition(t_out, "P_TL", TransitionCondition.Equal, "Green");
        TOutCt1.SetNextCondition(LogicConnector.AND, TOutCt2);

        GuardMapping grdTOut = new GuardMapping();
        grdTOut.condition = TOutCt1;

        grdTOut.Activations.add(new Activation(t_out, "P_l2", TransitionOperation.SendOverNetwork, "Pout"));

        ArrayList<PlaceNameWithWeight> inputTL = new ArrayList<>();
        inputTL.add(new PlaceNameWithWeight("P_TL", 1F));
        ArrayList<String> outputTL = new ArrayList<>();
        outputTL.add("P_TL");
        grdTOut.Activations.add(new Activation(t_out, flrs_move, inputTL, TransitionOperation.FLRS, outputTL));

        t_out.GuardMappingList.add(grdTOut);
        t_out.Delay = 0;
        pn.Transitions.add(t_out);
        // end of transition declaration

        System.out.println("Lane4 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
