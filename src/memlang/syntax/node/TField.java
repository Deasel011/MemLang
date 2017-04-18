/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.node;

import memlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class TField extends Token
{
    public TField()
    {
        super.setText("field");
    }

    public TField(int line, int pos)
    {
        super.setText("field");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TField(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTField(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TField text.");
    }
}
