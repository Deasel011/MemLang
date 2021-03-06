/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.analysis;

import java.util.*;
import memlang.syntax.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgram().apply(this);
        outStart(node);
    }

    public void inAInstsProgram(AInstsProgram node)
    {
        defaultIn(node);
    }

    public void outAInstsProgram(AInstsProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstsProgram(AInstsProgram node)
    {
        inAInstsProgram(node);
        if(node.getExecute() != null)
        {
            node.getExecute().apply(this);
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInst());
            Collections.reverse(copy);
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PPrecondition> copy = new ArrayList<PPrecondition>(node.getPrecondition());
            Collections.reverse(copy);
            for(PPrecondition e : copy)
            {
                e.apply(this);
            }
        }
        outAInstsProgram(node);
    }

    public void inATargetPrecondition(ATargetPrecondition node)
    {
        defaultIn(node);
    }

    public void outATargetPrecondition(ATargetPrecondition node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATargetPrecondition(ATargetPrecondition node)
    {
        inATargetPrecondition(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getTarget() != null)
        {
            node.getTarget().apply(this);
        }
        outATargetPrecondition(node);
    }

    public void inASizePrecondition(ASizePrecondition node)
    {
        defaultIn(node);
    }

    public void outASizePrecondition(ASizePrecondition node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASizePrecondition(ASizePrecondition node)
    {
        inASizePrecondition(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getSize() != null)
        {
            node.getSize().apply(this);
        }
        outASizePrecondition(node);
    }

    public void inADeclarationInst(ADeclarationInst node)
    {
        defaultIn(node);
    }

    public void outADeclarationInst(ADeclarationInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationInst(ADeclarationInst node)
    {
        inADeclarationInst(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getField() != null)
        {
            node.getField().apply(this);
        }
        outADeclarationInst(node);
    }

    public void inAAssignInst(AAssignInst node)
    {
        defaultIn(node);
    }

    public void outAAssignInst(AAssignInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignInst(AAssignInst node)
    {
        inAAssignInst(node);
        if(node.getOper() != null)
        {
            node.getOper().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAssignInst(node);
    }

    public void inAWaitInst(AWaitInst node)
    {
        defaultIn(node);
    }

    public void outAWaitInst(AWaitInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWaitInst(AWaitInst node)
    {
        inAWaitInst(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getWait() != null)
        {
            node.getWait().apply(this);
        }
        outAWaitInst(node);
    }

    public void inASetInst(ASetInst node)
    {
        defaultIn(node);
    }

    public void outASetInst(ASetInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASetInst(ASetInst node)
    {
        inASetInst(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
        }
        outASetInst(node);
    }

    public void inAPrintInst(APrintInst node)
    {
        defaultIn(node);
    }

    public void outAPrintInst(APrintInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrintInst(APrintInst node)
    {
        inAPrintInst(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getPrint() != null)
        {
            node.getPrint().apply(this);
        }
        outAPrintInst(node);
    }

    public void inAAssertInst(AAssertInst node)
    {
        defaultIn(node);
    }

    public void outAAssertInst(AAssertInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssertInst(AAssertInst node)
    {
        inAAssertInst(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PMultipleIds> copy = new ArrayList<PMultipleIds>(node.getIds());
            Collections.reverse(copy);
            for(PMultipleIds e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        outAAssertInst(node);
    }

    public void inAStopInst(AStopInst node)
    {
        defaultIn(node);
    }

    public void outAStopInst(AStopInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStopInst(AStopInst node)
    {
        inAStopInst(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getStop() != null)
        {
            node.getStop().apply(this);
        }
        outAStopInst(node);
    }

    public void inAMultipleIds(AMultipleIds node)
    {
        defaultIn(node);
    }

    public void outAMultipleIds(AMultipleIds node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleIds(AMultipleIds node)
    {
        inAMultipleIds(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAMultipleIds(node);
    }

    public void inAFindOper(AFindOper node)
    {
        defaultIn(node);
    }

    public void outAFindOper(AFindOper node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFindOper(AFindOper node)
    {
        inAFindOper(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getFind() != null)
        {
            node.getFind().apply(this);
        }
        outAFindOper(node);
    }

    public void inANarrowOper(ANarrowOper node)
    {
        defaultIn(node);
    }

    public void outANarrowOper(ANarrowOper node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANarrowOper(ANarrowOper node)
    {
        inANarrowOper(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getSign() != null)
        {
            node.getSign().apply(this);
        }
        if(node.getNarrow() != null)
        {
            node.getNarrow().apply(this);
        }
        outANarrowOper(node);
    }

    public void inALtSign(ALtSign node)
    {
        defaultIn(node);
    }

    public void outALtSign(ALtSign node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALtSign(ALtSign node)
    {
        inALtSign(node);
        if(node.getLt() != null)
        {
            node.getLt().apply(this);
        }
        outALtSign(node);
    }

    public void inAGtSign(AGtSign node)
    {
        defaultIn(node);
    }

    public void outAGtSign(AGtSign node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtSign(AGtSign node)
    {
        inAGtSign(node);
        if(node.getGt() != null)
        {
            node.getGt().apply(this);
        }
        outAGtSign(node);
    }

    public void inAGeSign(AGeSign node)
    {
        defaultIn(node);
    }

    public void outAGeSign(AGeSign node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGeSign(AGeSign node)
    {
        inAGeSign(node);
        if(node.getGe() != null)
        {
            node.getGe().apply(this);
        }
        outAGeSign(node);
    }

    public void inALeSign(ALeSign node)
    {
        defaultIn(node);
    }

    public void outALeSign(ALeSign node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALeSign(ALeSign node)
    {
        inALeSign(node);
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        outALeSign(node);
    }

    public void inAEqualsSign(AEqualsSign node)
    {
        defaultIn(node);
    }

    public void outAEqualsSign(AEqualsSign node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualsSign(AEqualsSign node)
    {
        inAEqualsSign(node);
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        outAEqualsSign(node);
    }

    public void inAExecute(AExecute node)
    {
        defaultIn(node);
    }

    public void outAExecute(AExecute node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExecute(AExecute node)
    {
        inAExecute(node);
        if(node.getRBrace() != null)
        {
            node.getRBrace().apply(this);
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInst());
            Collections.reverse(copy);
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getLBrace() != null)
        {
            node.getLBrace().apply(this);
        }
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getExec() != null)
        {
            node.getExec().apply(this);
        }
        outAExecute(node);
    }
}
