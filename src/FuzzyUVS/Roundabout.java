package FuzzyUVS;

import Components.*;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.*;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Roundabout {
    public static void main(String[] args){
        FLRS flrs_move= new FLRS( new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),new FV(FZ.PL));
        FLRS flrs_plus = new FLRS(  new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM),new FV(FZ.ZR),
                                    new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR),new FV(FZ.PM),
                                    new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),new FV(FZ.PL),
                                    new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL),new FV(FZ.PL),
                                    new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL),new FV(FZ.PL));

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Roundabout";
        pn.NetworkPort = 1086;

        //input place declaration
        DataFuzzy p_i1 = new DataFuzzy();
        p_i1.SetName("P_i1");
        pn.PlaceList.add(p_i1);

        DataFuzzy p_i2 = new DataFuzzy();
        p_i2.SetName("P_i2");
        pn.PlaceList.add(p_i2);

        DataFuzzy p_i3 = new DataFuzzy();
        p_i3.SetName("P_i3");
        pn.PlaceList.add(p_i3);

        //auxiliary place declarations
        DataFuzzy L1 = new DataFuzzy();
        L1.SetName("L1");
        pn.PlaceList.add(L1);

        DataFuzzy L2 = new DataFuzzy();
        L2.SetName("L2");
        pn.PlaceList.add(L2);

        DataFuzzy L3 = new DataFuzzy();
        L3.SetName("L3");
        pn.PlaceList.add(L3);

        DataFuzzy L1L2 = new DataFuzzy();
        L1L2.SetName("L1L2");
        pn.PlaceList.add(L1L2);

        DataFuzzy L1L2L3 = new DataFuzzy();
        L1L2L3.SetName("L1L2L3");
        pn.PlaceList.add(L1L2L3);

        DataFuzzy E1 = new DataFuzzy();
        E1.SetName("E1");
        pn.PlaceList.add(E1);

        DataFuzzy E2 = new DataFuzzy();
        E2.SetName("E2");
        pn.PlaceList.add(E2);

        DataFuzzy E3 = new DataFuzzy();
        E3.SetName("E3");
        pn.PlaceList.add(E3);

        //Splitting place
        DataFuzzy sp = new DataFuzzy();
        sp.SetName("SP");
        sp.SetValue(new Fuzzy(0.33F));
        pn.ConstantPlaceList.add(sp);

        //output place declarations
        DataFuzzy p_o1 = new DataFuzzy();
        p_o1.SetName("P_out1");
        pn.PlaceList.add(p_o1);

        DataFuzzy p_o2 = new DataFuzzy();
        p_o2.SetName("P_out2");
        pn.PlaceList.add(p_o2);

        DataTransfer p_o3 = new DataTransfer();
        p_o3.SetName("P_out3");
        p_o3.Value = new TransferOperation("localhost","1082","P_R");
        pn.PlaceList.add(p_o3);

        //Transitions
        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P_i1");

        Condition T1Ct1 = new Condition(t1, "P_i1", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("P_i1", 1F));

        ArrayList<String> OutputT1 = new ArrayList<>();
        OutputT1.add("L1");

        grdT1.Activations.add(new Activation(t1, flrs_move, inputT1, TransitionOperation.FLRS, OutputT1));
        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P_i2");

        Condition T2Ct1 = new Condition(t2, "P_i2", TransitionCondition.NotNull);
        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("P_i2", 1F));

        ArrayList<String> OutputT2 = new ArrayList<>();
        OutputT2.add("L2");

        grdT2.Activations.add(new Activation(t2, flrs_move, inputT2, TransitionOperation.FLRS, OutputT2));
        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("P_i3");

        Condition T3Ct1 = new Condition(t3, "P_i3", TransitionCondition.NotNull);
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        ArrayList<PlaceNameWithWeight> inputT3 = new ArrayList<>();
        inputT3.add(new PlaceNameWithWeight("P_i3", 1F));

        ArrayList<String> OutputT3 = new ArrayList<>();
        OutputT3.add("L3");

        grdT3.Activations.add(new Activation(t3, flrs_move, inputT3, TransitionOperation.FLRS, OutputT3));
        t3.GuardMappingList.add(grdT3);

        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4 ------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T4";
        t4.InputPlaceName.add("L1");
        t4.InputPlaceName.add("L2");

        Condition T4Ct1 = new Condition(t4, "L1", TransitionCondition.NotNull);
        Condition T4Ct2 = new Condition(t4, "L2", TransitionCondition.NotNull);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;

        ArrayList<PlaceNameWithWeight> inputT4 = new ArrayList<>();
        inputT4.add(new PlaceNameWithWeight("L1", 1F));
        inputT4.add(new PlaceNameWithWeight("L2", 1F));

        ArrayList<String> OutputT4 = new ArrayList<>();
        OutputT4.add("L1L2");

        grdT4.Activations.add(new Activation(t4, flrs_plus, inputT4, TransitionOperation.FLRS, OutputT4));
        t4.GuardMappingList.add(grdT4);

        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5 ------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T5";
        t5.InputPlaceName.add("L3");
        t5.InputPlaceName.add("L1L2");

        Condition T5Ct1 = new Condition(t5, "L3", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "L1L2", TransitionCondition.NotNull);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;

        ArrayList<PlaceNameWithWeight> inputT5 = new ArrayList<>();
        inputT5.add(new PlaceNameWithWeight("L3", 1F));
        inputT5.add(new PlaceNameWithWeight("L1L2", 1F));

        ArrayList<String> OutputT5 = new ArrayList<>();
        OutputT5.add("L1L2L3");

        grdT5.Activations.add(new Activation(t5, flrs_plus, inputT5, TransitionOperation.FLRS, OutputT5));
        t5.GuardMappingList.add(grdT5);

        t5.Delay = 0;
        pn.Transitions.add(t5);

        // TS ------------------------------------------------
        PetriTransition ts = new PetriTransition(pn);
        ts.TransitionName = "TS";
        ts.InputPlaceName.add("L1L2L3");

        Condition TSCt1 = new Condition(ts, "L1L2L3", TransitionCondition.NotNull);
        GuardMapping grdTS = new GuardMapping();
        grdTS.condition = TSCt1;

        ArrayList<PlaceNameWithWeight> inputTsE1 = new ArrayList<>();
        inputTsE1.add(new PlaceNameWithWeight("L1L2L3", 1F));
        inputTsE1.add(new PlaceNameWithWeight("SP", 1F));
        grdTS.Activations.add(new Activation(inputTsE1, TransitionOperation.Prod_Fuzzy, "E1", ts));

        ArrayList<PlaceNameWithWeight> inputTsE2 = new ArrayList<>();
        inputTsE2.add(new PlaceNameWithWeight("L1L2L3", 1F));
        inputTsE2.add(new PlaceNameWithWeight("SP", 1F));
        grdTS.Activations.add(new Activation(inputTsE2, TransitionOperation.Prod_Fuzzy, "E2", ts));

        ArrayList<PlaceNameWithWeight> inputTsE3 = new ArrayList<>();
        inputTsE3.add(new PlaceNameWithWeight("L1L2L3", 1F));
        inputTsE3.add(new PlaceNameWithWeight("SP", 1F));
        grdTS.Activations.add(new Activation(inputTsE3, TransitionOperation.Prod_Fuzzy, "E3", ts));

        ts.GuardMappingList.add(grdTS);
        ts.Delay = 0;
        pn.Transitions.add(ts);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T6";
        t6.InputPlaceName.add("E1");

        Condition T6Ct1 = new Condition(t6, "E1", TransitionCondition.NotNull);
        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;

        ArrayList<PlaceNameWithWeight> inputT6 = new ArrayList<>();
        inputT6.add(new PlaceNameWithWeight("E1", 1F));

        ArrayList<String> OutputT6 = new ArrayList<>();
        OutputT6.add("P_out1");

        grdT6.Activations.add(new Activation(t6, flrs_move, inputT6, TransitionOperation.FLRS, OutputT6));
        t6.GuardMappingList.add(grdT6);

        t6.Delay = 0;
        pn.Transitions.add(t6);

        // T7 ------------------------------------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "T7";
        t7.InputPlaceName.add("E2");

        Condition T7Ct1 = new Condition(t7, "E2", TransitionCondition.NotNull);
        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;

        ArrayList<PlaceNameWithWeight> inputT7 = new ArrayList<>();
        inputT7.add(new PlaceNameWithWeight("E2", 1F));

        ArrayList<String> OutputT7 = new ArrayList<>();
        OutputT7.add("P_out2");

        grdT7.Activations.add(new Activation(t7, flrs_move, inputT7, TransitionOperation.FLRS, OutputT7));
        t7.GuardMappingList.add(grdT7);

        t7.Delay = 0;
        pn.Transitions.add(t7);

        // T8 (Data Transfer to Lane 2) -----------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "T8";
        t8.InputPlaceName.add("E3");

        Condition T8Ct1 = new Condition(t8, "E3", TransitionCondition.NotNull);
        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;

        grdT8.Activations.add(new Activation(t8, "E3", TransitionOperation.SendOverNetwork, "P_out3"));

        t8.GuardMappingList.add(grdT8);
        t8.Delay = 0;
        pn.Transitions.add(t8);

        // Start Petri Net Window -----------------------------
        System.out.println("Roundabout started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
