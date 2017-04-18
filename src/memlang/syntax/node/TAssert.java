/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.node;

import memlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class TAssert extends Token
{
    public TAssert()
    {
        super.setText("assert");
    }

    public TAssert(int line, int pos)
    {
        super.setText("assert");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TAssert(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTAssert(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TAssert text.");
    }
}
