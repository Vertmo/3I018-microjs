package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KTabAccess extends KExpr {
    private KExpr tab;
    private KExpr index;

    public KTabAccess(KExpr tab, KExpr index, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.tab = tab;
        this.index = index;
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

}
