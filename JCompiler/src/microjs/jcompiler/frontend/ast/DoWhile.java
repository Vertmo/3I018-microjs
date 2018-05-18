package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KDoWhile;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.utils.DotGraph;

public class DoWhile extends Statement {
    private Expr cond;
    private List<Statement> corps;
    
    public DoWhile(Expr cond, List<Statement> corps,
		 Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.cond  = cond;
    	this.corps = corps;
    }
    
    @Override
    public KDoWhile expand() {
    	Location whileStartPos  = getStartPos();
    	Location corpsEndPos    = getEndPos();
    	List<KStatement> kcorps = Statement.expandStatements(corps);
    	KStatement kcorps_s = KSeq.buildKSeq(kcorps,
					     whileStartPos, corpsEndPos);
    	return new KDoWhile(cond.expand(), kcorps_s, getStartPos(), getEndPos());
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String whileNode = graph.addNode("While");
		String condNode = cond.buildDotGraph(graph);
		graph.addEdge(whileNode, condNode, "cond");
		String corpsNode = cond.buildDotGraph(graph);
		graph.addEdge(whileNode, corpsNode, "corps");
	
		return whileNode;
	}

    
    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
        buf.append("do ");
    	buf.append("{\n");
    	Statement.prettyPrintStatements(buf, corps, indent_level + 1);
    	indent(buf, indent_level);
    	buf.append("}");
    	buf.append(" while (");
    	cond.prettyPrint(buf);
        buf.append(")");
    }
}
