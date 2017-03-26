/* This file was generated by SableCC (http://www.sablecc.org/). */

package memlang.syntax.node;

import memlang.syntax.analysis.*;

@SuppressWarnings("nls")
public final class AFindInterpretor extends PInterpretor
{
    private TFind _find_;
    private PValue _value_;
    private TEol _eol_;

    public AFindInterpretor()
    {
        // Constructor
    }

    public AFindInterpretor(
        @SuppressWarnings("hiding") TFind _find_,
        @SuppressWarnings("hiding") PValue _value_,
        @SuppressWarnings("hiding") TEol _eol_)
    {
        // Constructor
        setFind(_find_);

        setValue(_value_);

        setEol(_eol_);

    }

    @Override
    public Object clone()
    {
        return new AFindInterpretor(
            cloneNode(this._find_),
            cloneNode(this._value_),
            cloneNode(this._eol_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFindInterpretor(this);
    }

    public TFind getFind()
    {
        return this._find_;
    }

    public void setFind(TFind node)
    {
        if(this._find_ != null)
        {
            this._find_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._find_ = node;
    }

    public PValue getValue()
    {
        return this._value_;
    }

    public void setValue(PValue node)
    {
        if(this._value_ != null)
        {
            this._value_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._value_ = node;
    }

    public TEol getEol()
    {
        return this._eol_;
    }

    public void setEol(TEol node)
    {
        if(this._eol_ != null)
        {
            this._eol_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._eol_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._find_)
            + toString(this._value_)
            + toString(this._eol_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._find_ == child)
        {
            this._find_ = null;
            return;
        }

        if(this._value_ == child)
        {
            this._value_ = null;
            return;
        }

        if(this._eol_ == child)
        {
            this._eol_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._find_ == oldChild)
        {
            setFind((TFind) newChild);
            return;
        }

        if(this._value_ == oldChild)
        {
            setValue((PValue) newChild);
            return;
        }

        if(this._eol_ == oldChild)
        {
            setEol((TEol) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
