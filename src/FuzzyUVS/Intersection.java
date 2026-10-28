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

public class Intersection {
    public static void main(String[] args){
        //flrs op declaration for additions
        FLRS flrs_plus = new FLRS(  new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM),new FV(FZ.ZR),
                                    new FV(FZ.NL), new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR),new FV(FZ.PM),
                                    new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM),new FV(FZ.PL),
                                    new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL),new FV(FZ.PL),
                                    new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL), new FV(FZ.PL),new FV(FZ.PL));

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri Intersection";
        pn.NetworkPort = 1080;

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

        DataFuzzy p_i4 = new DataFuzzy();
        p_i4.SetName("P_i4");
        pn.PlaceList.add(p_i4);

        //auxiliary place declarations
        DataFuzzy p_aux1 = new DataFuzzy();
        p_aux1.SetName("P_aux1");
        pn.PlaceList.add(p_aux1);

        DataFuzzy p_aux2 = new DataFuzzy();
        p_aux2.SetName("P_aux2");
        pn.PlaceList.add(p_aux2);

        DataFuzzy p_i = new DataFuzzy();
        p_i.SetName("P_i");
        pn.PlaceList.add(p_i);

        //output place declarations
        DataFuzzy p_o1 = new DataFuzzy();
        p_o1.SetName("P_o1");
        pn.PlaceList.add(p_o1);

        DataFuzzy p_o2 = new DataFuzzy();
        p_o2.SetName("P_o2");
        pn.PlaceList.add(p_o2);

        DataFuzzy p_o3 = new DataFuzzy();
        p_o3.SetName("P_o3");
        pn.PlaceList.add(p_o3);

        DataFuzzy p_o4 = new DataFuzzy();
        p_o4.SetName("P_o4");
        pn.PlaceList.add(p_o4);

        //Splitting places
        DataFuzzy fp1 = new DataFuzzy();
        fp1.SetName("Fp1");
        fp1.SetValue(new Fuzzy(0.25F));
        pn.ConstantPlaceList.add(fp1);

        DataFuzzy fp2 = new DataFuzzy();
        fp2.SetName("Fp2");
        fp2.SetValue(new Fuzzy(0.5F));
        pn.ConstantPlaceList.add(fp2);

        DataFuzzy fp3 = new DataFuzzy();
        fp3.SetName("Fp3");
        fp3.SetValue(new Fuzzy(0.12F));
        pn.ConstantPlaceList.add(fp3);

        DataFuzzy fp4 = new DataFuzzy();
        fp4.SetName("Fp4");
        fp4.SetValue(new Fuzzy(0.12F));
        pn.ConstantPlaceList.add(fp4);

        //Transitions
        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("P_i1");
        t1.InputPlaceName.add("P_i2");

        Condition T1Ct1 = new Condition(t1, "P_i1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "P_i2", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("P_i1", 1F));
        inputT1.add(new PlaceNameWithWeight("P_i2", 1F));
        ArrayList<String> outputT1 = new ArrayList<>();
        outputT1.add("P_aux1");
        grdT1.Activations.add(new Activation(t1, flrs_plus, inputT1, TransitionOperation.FLRS, outputT1));

        t1.GuardMappingList.add(grdT1);
        t1.Delay=0;
        pn.Transitions.add(t1);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("P_i3");
        t2.InputPlaceName.add("P_i4");

        Condition T2Ct1 = new Condition(t2, "P_i3", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "P_i4", TransitionCondition.NotNull);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("P_i3", 1F));
        inputT2.add(new PlaceNameWithWeight("P_i4", 1F));
        ArrayList<String> outputT2 = new ArrayList<>();
        outputT2.add("P_aux2");
        grdT2.Activations.add(new Activation(t2, flrs_plus, inputT2, TransitionOperation.FLRS, outputT2));

        t2.GuardMappingList.add(grdT2);
        t2.Delay=0;
        pn.Transitions.add(t2);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("P_aux1");
        t3.InputPlaceName.add("P_aux2");

        Condition T3Ct1 = new Condition(t3, "P_aux1", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "P_aux2", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        ArrayList<PlaceNameWithWeight> inputT3 = new ArrayList<>();
        inputT3.add(new PlaceNameWithWeight("P_aux1", 1F));
        inputT3.add(new PlaceNameWithWeight("P_aux2", 1F));
        ArrayList<String> outputT3 = new ArrayList<>();
        outputT3.add("P_i");
        grdT3.Activations.add(new Activation(t3, flrs_plus, inputT3, TransitionOperation.FLRS, outputT3));

        t3.GuardMappingList.add(grdT3);
        t3.Delay=0;
        pn.Transitions.add(t3);

        // t_s (Splitting transition) ------------------------------------------------
        PetriTransition t_s = new PetriTransition(pn);
        t_s.TransitionName = "t_s";
        t_s.InputPlaceName.add("P_i");

        Condition TsCt1 = new Condition(t_s, "P_i", TransitionCondition.NotNull);

        GuardMapping grdTs = new GuardMapping();
        grdTs.condition = TsCt1;

        ArrayList<PlaceNameWithWeight> inputTs1 = new ArrayList<>();
        inputTs1.add(new PlaceNameWithWeight("P_i", 1F));
        inputTs1.add(new PlaceNameWithWeight("Fp1", 1F));
        grdTs.Activations.add(new Activation(inputTs1, TransitionOperation.Prod_Fuzzy, "P_o1", t_s));

        ArrayList<PlaceNameWithWeight> inputTs2 = new ArrayList<>();
        inputTs2.add(new PlaceNameWithWeight("P_i", 1F));
        inputTs2.add(new PlaceNameWithWeight("Fp2", 1F));
        grdTs.Activations.add(new Activation(inputTs2, TransitionOperation.Prod_Fuzzy, "P_o2", t_s));

        ArrayList<PlaceNameWithWeight> inputTs3 = new ArrayList<>();
        inputTs3.add(new PlaceNameWithWeight("P_i", 1F));
        inputTs3.add(new PlaceNameWithWeight("Fp3", 1F));
        grdTs.Activations.add(new Activation(inputTs3, TransitionOperation.Prod_Fuzzy, "P_o3", t_s));

        ArrayList<PlaceNameWithWeight> inputTs4 = new ArrayList<>();
        inputTs4.add(new PlaceNameWithWeight("P_i", 1F));
        inputTs4.add(new PlaceNameWithWeight("Fp4", 1F));
        grdTs.Activations.add(new Activation(inputTs4, TransitionOperation.Prod_Fuzzy, "P_o4", t_s));

        t_s.GuardMappingList.add(grdTs);
        t_s.Delay = 0;
        pn.Transitions.add(t_s);

        // Network Output Places (DataTransfer to Exit Lanes)
        DataTransfer p_out1 = new DataTransfer();
        p_out1.SetName("P_out1");
        p_out1.Value = new TransferOperation("localhost", "1085", "P_e_i1");
        pn.PlaceList.add(p_out1);

        PetriTransition t_out1 = new PetriTransition(pn);
        t_out1.TransitionName = "t_out1";
        t_out1.InputPlaceName.add("P_o1");

        Condition TOut1Ct1 = new Condition(t_out1, "P_o1", TransitionCondition.NotNull);
        GuardMapping grdTOut1 = new GuardMapping();
        grdTOut1.condition = TOut1Ct1;
        grdTOut1.Activations.add(new Activation(t_out1, "P_o1", TransitionOperation.SendOverNetwork, "P_out1"));

        t_out1.GuardMappingList.add(grdTOut1);
        t_out1.Delay = 0;
        pn.Transitions.add(t_out1);

        DataTransfer p_out2 = new DataTransfer();
        p_out2.SetName("P_out2");
        p_out2.Value = new TransferOperation("localhost", "1086", "P_e_i2");
        pn.PlaceList.add(p_out2);

        PetriTransition t_out2 = new PetriTransition(pn);
        t_out2.TransitionName = "t_out2";
        t_out2.InputPlaceName.add("P_o2");

        Condition TOut2Ct1 = new Condition(t_out2, "P_o2", TransitionCondition.NotNull);
        GuardMapping grdTOut2 = new GuardMapping();
        grdTOut2.condition = TOut2Ct1;
        grdTOut2.Activations.add(new Activation(t_out2, "P_o2", TransitionOperation.SendOverNetwork, "P_out2"));

        t_out2.GuardMappingList.add(grdTOut2);
        t_out2.Delay = 0;
        pn.Transitions.add(t_out2);

        DataTransfer p_out3 = new DataTransfer();
        p_out3.SetName("P_out3");
        p_out3.Value = new TransferOperation("localhost", "1087", "P_e_i3");
        pn.PlaceList.add(p_out3);

        PetriTransition t_out3 = new PetriTransition(pn);
        t_out3.TransitionName = "t_out3";
        t_out3.InputPlaceName.add("P_o3");

        Condition TOut3Ct1 = new Condition(t_out3, "P_o3", TransitionCondition.NotNull);
        GuardMapping grdTOut3 = new GuardMapping();
        grdTOut3.condition = TOut3Ct1;
        grdTOut3.Activations.add(new Activation(t_out3, "P_o3", TransitionOperation.SendOverNetwork, "P_out3"));

        t_out3.GuardMappingList.add(grdTOut3);
        t_out3.Delay = 0;
        pn.Transitions.add(t_out3);

        DataTransfer p_out4 = new DataTransfer();
        p_out1.SetName("P_out4");
        p_out1.Value = new TransferOperation("localhost", "1088", "P_e_i4");
        pn.PlaceList.add(p_out4);

        PetriTransition t_out4 = new PetriTransition(pn);
        t_out4.TransitionName = "t_out4";
        t_out4.InputPlaceName.add("P_o4");

        Condition TOut4Ct1 = new Condition(t_out4, "P_o4", TransitionCondition.NotNull);
        GuardMapping grdTOut4 = new GuardMapping();
        grdTOut4.condition = TOut4Ct1;
        grdTOut4.Activations.add(new Activation(t_out4, "P_o4", TransitionOperation.SendOverNetwork, "P_out4"));

        t_out4.GuardMappingList.add(grdTOut4);
        t_out4.Delay = 0;
        pn.Transitions.add(t_out4);
    }
}
