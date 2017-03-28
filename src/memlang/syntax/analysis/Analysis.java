/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.analysis;

import memlang.syntax.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAInstsProgram(AInstsProgram node);
    void caseATargetPrecondition(ATargetPrecondition node);
    void caseASizePrecondition(ASizePrecondition node);
    void caseADeclarationInst(ADeclarationInst node);
    void caseAAssignInst(AAssignInst node);
    void caseAWaitInst(AWaitInst node);
    void caseASetInst(ASetInst node);
    void caseAPrintInst(APrintInst node);
    void caseAAssertInst(AAssertInst node);
    void caseAMultipleIds(AMultipleIds node);
    void caseAFindOper(AFindOper node);
    void caseANarrowOper(ANarrowOper node);
    void caseALtSign(ALtSign node);
    void caseAGtSign(AGtSign node);
    void caseAGeSign(AGeSign node);
    void caseALeSign(ALeSign node);
    void caseAEqualsSign(AEqualsSign node);
    void caseAExecute(AExecute node);

    void caseTSemi(TSemi node);
    void caseTComma(TComma node);
    void caseTEquals(TEquals node);
    void caseTNEquals(TNEquals node);
    void caseTWAssign(TWAssign node);
    void caseTAssign(TAssign node);
    void caseTLe(TLe node);
    void caseTLt(TLt node);
    void caseTGe(TGe node);
    void caseTGt(TGt node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTStar(TStar node);
    void caseTSlash(TSlash node);
    void caseTPercent(TPercent node);
    void caseTNot(TNot node);
    void caseTLBrace(TLBrace node);
    void caseTRBrace(TRBrace node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTOr(TOr node);
    void caseTAnd(TAnd node);
    void caseTEol(TEol node);
    void caseTWhile(TWhile node);
    void caseTFind(TFind node);
    void caseTField(TField node);
    void caseTSet(TSet node);
    void caseTWait(TWait node);
    void caseTAssert(TAssert node);
    void caseTNarrow(TNarrow node);
    void caseTPrint(TPrint node);
    void caseTTarget(TTarget node);
    void caseTSize(TSize node);
    void caseTString(TString node);
    void caseTNumber(TNumber node);
    void caseTComment(TComment node);
    void caseTBlank(TBlank node);
    void caseTId(TId node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
