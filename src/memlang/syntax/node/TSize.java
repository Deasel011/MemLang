/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.node;

import memlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class TSize extends Token
{
    public TSize()
    {
        super.setText("Size");
    }

    public TSize(int line, int pos)
    {
        super.setText("Size");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TSize(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTSize(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TSize text.");
    }
}
