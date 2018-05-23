package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.utils.DotGraph;
import microjs.jcompiler.middleend.kast.KTabAssign;

public class TabAssign extends Expr {
    private Expr tab;
    private Expr index;
    private Expr e;

    public TabAssign(Expr tab, Expr index, Expr e, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.tab = tab;
        this.index = index;
        this.e = e;
    }

    @Override
    public KTabAssign expand() {
        return new KTabAssign(tab.expand(), index.expand(), e.expand(), getStartPos(), getEndPos());
    }

    @Override
    protected String buildDotGraph(DotGraph graph) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {
        tab.prettyPrint(buf);
        buf.append("[");
        index.prettyPrint(buf);
        buf.append("] = ");
        e.prettyPrint(buf);
    }
}
