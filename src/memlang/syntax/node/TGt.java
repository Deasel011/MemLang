/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.node;

import memlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class TGt extends Token
{
    public TGt()
    {
        super.setText(">");
    }

    public TGt(int line, int pos)
    {
        super.setText(">");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TGt(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTGt(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TGt text.");
    }
}