package memlang.interpretor;

import MemManip.MemManip;
import memlang.syntax.analysis.DepthFirstAdapter;
import memlang.syntax.node.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by pdesl on 2017-02-21.
 */
public class Interpretor extends DepthFirstAdapter {

    private Value result;

    private String target;

    private int size;

    private MemManip manipulator = new MemManip();

    private HashMap<String, Integer> adressResult;

    private HashMap<String,LinkedHashMap<String,Integer>> fieldDictionnary = new HashMap<>();

    private HashMap<String, ExecuteThread> executeDict = new HashMap<>();

    private HashMap<String, Timer> timerDict = new HashMap<>();

    @Override
    public void caseATargetPrecondition(ATargetPrecondition node) {
        this.target = node.getString().getText().substring(1,node.getString().getText().length()-1);
        manipulator.PID = manipulator.FindProcessId(this.target);
        manipulator.OpenProcess();
        manipulator.loadPageRanges();
        if(!manipulator.hasProcessId()){
            throw new RuntimeException("Process could not be opened at ["+node.getTarget().getLine()+"]["+node.getTarget().getPos()+"].");
        }
    }

    @Override
    public void caseASizePrecondition(ASizePrecondition node) {
        this.size = Integer.parseInt(node.getNumber().getText());
    }

    /**
     * Verification des preconditions avant d'entre dans le programme,
     * Size et Target doivent etre declares
     * @param node
     */
    @Override
    public void caseAInstsProgram(AInstsProgram node) {
        visit(node.getPrecondition());
        checkForTarget();
        visit(node.getInst());
        visit(node.getExecute());
    }

    @Override
    public void caseADeclarationInst(ADeclarationInst node) {
        String id = node.getId().getText();
        this.fieldDictionnary.put(id,new LinkedHashMap<String, Integer>());
    }

    @Override
    public void caseAAssignInst(AAssignInst node) {
        visit(node.getOper());
        String id = node.getId().getText();
        assert this.fieldDictionnary.containsKey(id);
        this.fieldDictionnary.get(id).clear();
        this.fieldDictionnary.get(id).putAll(this.adressResult);
    }

    @Override
    public void caseAFindOper(AFindOper node) {
        try {
            manipulator.searchFor(Integer.parseInt(node.getNumber().getText()), this.size);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Process is not accessible at ["+node.getNumber().getLine()+"]["+node.getNumber().getPos()+"].");
        }
        this.adressResult = manipulator.valueContainer;
    }

    @Override
    public void caseAWaitInst(AWaitInst node) {
        try {
            TimeUnit.SECONDS.sleep(Integer.parseInt(node.getNumber().getText()));
        } catch (InterruptedException e) {
            throw new RuntimeException("Wait command was interrupted at ["+node.getWait().getLine()+"]["+node.getWait().getPos()+"].");
        }

    }

    @Override
    public void caseANarrowOper(ANarrowOper node) {
        try {
            manipulator.narrow(Integer.parseInt(node.getNumber().getText()), this.size);
        } catch (Exception e) {
            throw new RuntimeException("Process is not accessible at ["+node.getNumber().getLine()+"]["+node.getNumber().getPos()+"].");
        }
        this.adressResult = manipulator.valueContainer;
    }

    @Override
    public void caseAPrintInst(APrintInst node) {
        LinkedHashMap<String, Integer> addresses = this.fieldDictionnary.get(node.getId().getText());
        manipulator.valueContainer = addresses;
        int number = manipulator.intAtSingleEntry(this.size);
        System.out.println("Value of "+node.getId().getText()+" is "+number);
    }

    @Override
    public void caseASetInst(ASetInst node) {
        String id = node.getId().getText();
        manipulator.valueContainer = this.fieldDictionnary.get(id);
        boolean res = manipulator.set(Integer.parseInt(node.getNumber().getText()),this.size);
        if (!res){
            System.out.println("Value could not be set to "+node.getNumber().getText());
        }
    }

    @Override
    public void caseAExecute(AExecute node) {
        executeDict.put(node.getId().getText(),new ExecuteThread(node){
            public void run(){
                visit(((AExecute) currentNode).getInst());
            }
        });
        Timer timer = new Timer(true);
        timerDict.put(node.getId().getText(),timer);
        timer.schedule(executeDict.get(node.getId().getText()),0,Integer.parseInt(node.getNumber().getText()));
    }

    @Override
    public void caseAStopInst(AStopInst node) {
        Timer timer = timerDict.get(node.getId().getText());
        if (timer == null){
            throw new RuntimeException("This timer thread does not exist");
        }
        timer.cancel();
        executeDict.remove(node.getId().getText());
        System.out.println("exec "+node.getId().getText()+" has stopped.");
    }

    /** visit node, if not null */
    public void visit(
            Node node) {

        if (node != null) {
            node.apply(this);
        }
    }

    /** visit node list, if not null */
    public void visit(
            List<? extends Node> nodes) {

        if (nodes != null) {
            for (Node n : nodes) {
                visit(n);
            }
        }
    }

    /** evaluate an expression and return its result */
    private Value eval(
            Node node) {

        // pour s'assurer qu'on n'a pas précédemment oublié d'utiliser une
        // valeur
        assert this.result == null;

        // visite du noeud pour évaluer l'expression
        visit(node);

        // pour s'assurer qu'une valeur a effectivement été calculée
        assert this.result != null;

        // retourne la valeur en remettant this.result à null
        Value result = this.result;
        this.result = null;
        return result;
    }

    private void checkForTarget(){
        if (this.target == null){
            throw new RuntimeException("Target process is not defined before program calls.");
        }
        if(this.size == 0){
            throw new RuntimeException("Data size has not been defined before program calls.");
        }
    }
}
