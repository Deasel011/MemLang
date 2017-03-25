package memlang.syntax;

import com.sun.jna.Pointer;
import memlang.syntax.analysis.DepthFirstAdapter;
import memlang.syntax.node.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import com.sun.jna.Memory;

/**
 * Created by pdesl on 2017-02-21.
 */
public class Interpretor extends DepthFirstAdapter {

    public int integerResult;

    public String stringResult;

    public Value result;

    private HashMap<BigInteger,BigInteger> addresses = new HashMap<>();

    private Pointer targetProcess;

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



    @Override
    public void caseATargetInterpretor(ATargetInterpretor node) {
        String processName = ((StringValue)eval(node.getValue())).toString();
        System.out.println(processName);
    }

    @Override
    public void caseAFindInterpretor(AFindInterpretor node) {

        super.caseAFindInterpretor(node);
    }

    @Override
    public void caseANumValue(ANumValue node) {

        this.integerResult = Integer.parseInt(node.getNumber().getText());
    }

    @Override
    public void caseAStringValue(AStringValue node) {
        this.stringResult = node.getString().getText();
    }
}
