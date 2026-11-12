package FuzzyUVS;

import Components.*;
import DataObjects.DataFuzzy;
import DataObjects.DataInteger;
import DataObjects.DataTransfer;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.PlaceNameWithWeight;
import DataOnly.TransferOperation;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
        //Main petri net declaration for Controller
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Controller";
        pn.NetworkPort = 1087;

        FLRS flrs_move = new FLRS(new FV(FZ.NL), new FV(FZ.NM), new FV(FZ.ZR), new FV(FZ.PM), new FV(FZ.PL));

        //InitPlace
        DataFuzzy ini = new DataFuzzy();
        ini.SetName("ini");
        ini.SetValue(1.0F);
        pn.PlaceList.add(ini);

        //TraficLight places
        DataFuzzy r1r2r3r4 = new DataFuzzy();
        r1r2r3r4.SetName("r1r2r3r4");
        pn.PlaceList.add(r1r2r3r4);

        DataFuzzy g1r2r3r4 = new DataFuzzy();
        g1r2r3r4.SetName("g1r2r3r4");
        pn.PlaceList.add(g1r2r3r4);

        DataFuzzy y1r2r3r4 = new DataFuzzy();
        y1r2r3r4.SetName("y1r2r3r4");
        pn.PlaceList.add(y1r2r3r4);

        DataFuzzy r1g2r3r4 = new DataFuzzy();
        r1g2r3r4.SetName("r1g2r3r4");
        pn.PlaceList.add(r1g2r3r4);

        DataFuzzy r1y2r3r4 = new DataFuzzy();
        r1y2r3r4.SetName("r1y2r3r4");
        pn.PlaceList.add(r1y2r3r4);

        DataFuzzy r1r2g3r4 = new DataFuzzy();
        r1r2g3r4.SetName("r1r2g3r4");
        pn.PlaceList.add(r1r2g3r4);

        DataFuzzy r1r2y3r4 = new DataFuzzy();
        r1r2y3r4.SetName("r1r2y3r4");
        pn.PlaceList.add(r1r2y3r4);

        DataFuzzy r1r2r3g4 = new DataFuzzy();
        r1r2r3g4.SetName("r1r2r3g4");
        pn.PlaceList.add(r1r2r3g4);

        DataFuzzy r1r2r3y4 = new DataFuzzy();
        r1r2r3y4.SetName("r1r2r3y4");
        pn.PlaceList.add(r1r2r3y4);

        //Transfer places
        DataTransfer P_TL1 = new DataTransfer();
        P_TL1.SetName("P_TL1");
        P_TL1.Value = new TransferOperation("localhost", "1081", "P_TL");
        pn.PlaceList.add(P_TL1);

        DataTransfer P_TL2 = new DataTransfer();
        P_TL2.SetName("P_TL2");
        P_TL2.Value = new TransferOperation("localhost", "1082", "P_TL");
        pn.PlaceList.add(P_TL2);

        DataTransfer P_TL3 = new DataTransfer();
        P_TL3.SetName("P_TL3");
        P_TL3.Value = new TransferOperation("localhost", "1083", "P_TL");
        pn.PlaceList.add(P_TL3);

        DataTransfer P_TL4 = new DataTransfer();
        P_TL4.SetName("P_TL4");
        P_TL4.Value = new TransferOperation("localhost", "1084", "P_TL");
        pn.PlaceList.add(P_TL4);

        //Color places
        DataFuzzy red = new DataFuzzy();
        red.SetName("Red");
        red.SetValue(-1.0F);
        pn.ConstantPlaceList.add(red);

        DataFuzzy yellow = new DataFuzzy();
        yellow.SetName("Yellow");
        red.SetValue(0.0F);
        pn.ConstantPlaceList.add(yellow);

        DataFuzzy green = new DataFuzzy();
        green.SetName("Green");
        red.SetValue(1.0F);
        pn.ConstantPlaceList.add(green);

        //Integer Places
        DataInteger d1 = new DataInteger();
        d1.SetName("d1");
        d1.SetValue(5);
        pn.PlaceList.add(d1);

        DataInteger d2 = new DataInteger();
        d2.SetName("d2");
        d2.SetValue(5);
        pn.PlaceList.add(d2);

        DataInteger d3 = new DataInteger();
        d3.SetName("d3");
        d3.SetValue(5);
        pn.PlaceList.add(d3);

        DataInteger d4 = new DataInteger();
        d4.SetName("d4");
        d4.SetValue(5);
        pn.PlaceList.add(d4);

        //Transitions
        //Tini --------------------------------------------
        PetriTransition tini = new PetriTransition(pn);
        tini.TransitionName = "Tini";
        tini.InputPlaceName.add("ini");

        Condition TiniCt1 = new Condition(tini, "ini", TransitionCondition.NotNull);
        GuardMapping grdTini = new GuardMapping();
        grdTini.condition = TiniCt1;

        grdTini.Activations.add(new Activation(tini, "Red", TransitionOperation.SendOverNetwork, "P_TL1"));
        grdTini.Activations.add(new Activation(tini, "Red", TransitionOperation.SendOverNetwork, "P_TL2"));
        grdTini.Activations.add(new Activation(tini, "Red", TransitionOperation.SendOverNetwork, "P_TL3"));
        grdTini.Activations.add(new Activation(tini, "Red", TransitionOperation.SendOverNetwork, "P_TL4"));

        tini.GuardMappingList.add(grdTini);
        tini.Delay = 0;
        pn.Transitions.add(tini);

        System.out.println("Controller started \n ------------------------------");
        pn.Delay = 3000;

        //T1 --------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("r1r2r3r4");

        Condition T1Ct1 = new Condition(t1, "r1r2r3r4", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        ArrayList<PlaceNameWithWeight> inputT1 = new ArrayList<>();
        inputT1.add(new PlaceNameWithWeight("r1r2r3r4", 1F));
        ArrayList<String> outputT1 = new ArrayList<>();
        outputT1.add("g1r2r3r4");

        grdT1.Activations.add(new Activation(t1, flrs_move, inputT1, TransitionOperation.FLRS, outputT1));
        grdT1.Activations.add(new Activation(t1, "Green", TransitionOperation.SendOverNetwork, "P_TL1"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 5;
        pn.Transitions.add(t1);

        //T2 --------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("g1r2r3r4");

        Condition T2Ct1 = new Condition(t2, "g1r2r3r4", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "d1", TransitionCondition.NotNull);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("g1r2r3r4", 1F));
        ArrayList<String> outputT2 = new ArrayList<>();
        outputT1.add("y1r2r3r4");

        grdT2.Activations.add(new Activation(t2, flrs_move, inputT2, TransitionOperation.FLRS, outputT2));
        grdT2.Activations.add(new Activation(t2, "Green", TransitionOperation.SendOverNetwork, "P_TL1"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 5;
        pn.Transitions.add(t2);


        //T3 --------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("y1r2r3r4");

        Condition T3Ct1 = new Condition(t3, "y1r2r3r4", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "d2", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        ArrayList<PlaceNameWithWeight> inputT3 = new ArrayList<>();
        inputT3.add(new PlaceNameWithWeight("y1r2r3r4", 1F));
        ArrayList<String> outputT3 = new ArrayList<>();
        outputT1.add("r1g2r3r4");

        grdT3.Activations.add(new Activation(t3, flrs_move, inputT3, TransitionOperation.FLRS, outputT3));
        grdT3.Activations.add(new Activation(t3, "Green", TransitionOperation.SendOverNetwork, "P_TL2"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 5;
        pn.Transitions.add(t3);


        //TO DO: Finish this, not the whole transition is right
        //T4 --------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T4";
        t4.InputPlaceName.add("r1y2r3r4");

        Condition T4Ct1 = new Condition(t4, "r1y2r3r4", TransitionCondition.NotNull);
        Condition T4Ct2 = new Condition(t4, "d1", TransitionCondition.NotNull);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T4Ct1;

        ArrayList<PlaceNameWithWeight> inputT2 = new ArrayList<>();
        inputT2.add(new PlaceNameWithWeight("g1r2r3r4", 1F));
        ArrayList<String> outputT2 = new ArrayList<>();
        outputT1.add("r1y2r3r4");

        grdT2.Activations.add(new Activation(t4, flrs_move, inputT2, TransitionOperation.FLRS, outputT2));
        grdT2.Activations.add(new Activation(t4, "Green", TransitionOperation.SendOverNetwork, "P_TL1"));

        t4.GuardMappingList.add(grdT2);
        t4.Delay = 5;
        pn.Transitions.add(t4);

        System.out.println("Controller started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
