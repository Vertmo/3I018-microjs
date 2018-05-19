package microjs.jcompiler.middleend.kast;


import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class KTab extends KExpr {
    private List<KExpr> content;

    public KTab(List<KExpr> content, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.content = content;
    }

    @Override
    public void accept(KASTVisitor visitor) {
        visitor.visit(this);
    }

    public List<KExpr> getContent() {
        return content;
    }
}
