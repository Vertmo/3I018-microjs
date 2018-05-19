package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KTabAccess extends KExpr {
    private String tab;
    private KExpr index;

    public KTabAccess(String tab, KExpr index, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.tab = tab;
        this.index = index;
    }

    @Override
    public void accept(KASTVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return tab;
    }

    public KExpr getIndex() {
        return index;
    }

}
