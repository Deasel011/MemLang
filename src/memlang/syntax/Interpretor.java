package memlang.syntax;

import MemManip.MemManip;
import com.sun.jna.Pointer;
import memlang.syntax.analysis.DepthFirstAdapter;
import memlang.syntax.node.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.sun.jna.Memory;

/**
 * Created by pdesl on 2017-02-21.
 */
public class Interpretor extends DepthFirstAdapter {

    private Value result;

    private String target;

    private int size;

    private MemManip manipulator = new MemManip();

    private HashMap<String, Integer> adressResult;

    private HashMap<String,HashMap<String,Integer>> fieldDictionnary = new HashMap<>();

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
        this.fieldDictionnary.put(id,new HashMap<String, Integer>());
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
        HashMap<String, Integer> addresses = this.fieldDictionnary.get(node.getId().getText());
        HashSet<Integer> set=new HashSet<>();
        System.out.println(this.fieldDictionnary.size());
        for(Map.Entry<String, Integer> entry:addresses.entrySet()){
            set.add(entry.getValue());
        }
        if(set.size()==1){
            for(Integer number:set){
                System.out.println("Value of "+node.getId().getText()+" is "+number);
            }
        }else if(set.size()>1){
            System.out.println("Multiple values found for "+node.getId().getText());
            for(Integer number:set){
                System.out.println(number);
            }
        }else{
            System.out.println(node.getId().getText()+" is Empty");
        }

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
