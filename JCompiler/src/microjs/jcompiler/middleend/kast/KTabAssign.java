package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KTabAssign extends KExpr {
    private KExpr tab;
    private KExpr index;
    private KExpr e;

    public KTabAssign(KExpr tab, KExpr index, KExpr e, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.tab = tab;
        this.index = index;
        this.e = e;
    }

    @Override
    public void accept(KASTVisitor visitor) {
        visitor.visit(this);
    }

    public KExpr getTab() {
        return tab;
    }

    public KExpr getIndex() {
        return index;
    }

    public KExpr getExpr() {
        return e;
    }

}
