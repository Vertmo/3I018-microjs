package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.utils.DotGraph;
import microjs.jcompiler.middleend.kast.KTabAccess;

public class TabAccess extends Expr {
    private Expr tab;
    private Expr index;

    public TabAccess(Expr tab, Expr index, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.tab = tab;
        this.index = index;
    }

    @Override
    public KTabAccess expand() {
        return new KTabAccess(tab.expand(), index.expand(), getStartPos(), getEndPos());
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
      buf.append("]");
	}
}
