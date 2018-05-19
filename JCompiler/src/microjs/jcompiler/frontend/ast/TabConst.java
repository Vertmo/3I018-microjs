package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.stream.Collectors;
import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KTab;
import microjs.jcompiler.utils.DotGraph;

public class TabConst extends Expr {
    private List<Expr> content;

    public TabConst(List<Expr> content, Location startPos, Location endPos) {
        super(startPos, endPos);
        this.content = content;
    }

    @Override
    public KTab expand() {
        return new KTab(content.stream().map(e -> e.expand()).collect(Collectors.toList()), getStartPos(), getEndPos());
    }

    @Override
    protected String buildDotGraph(DotGraph graph) {
        String tabNode = graph.addNode("Tab[" + content + "]");
        return tabNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {
        buf.append(content);
    }
}
