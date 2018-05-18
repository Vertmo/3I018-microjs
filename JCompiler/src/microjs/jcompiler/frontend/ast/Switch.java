package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KIf;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.utils.DotGraph;

public class Switch extends Statement {
    private Expr cond;
    private Map<Integer, List<Statement>> cases;
    
    public Switch(Expr cond, Map<Integer, List<Statement>> cases, Location startPos, Location endPos) {
    	super(startPos, endPos);		
        this.cond = cond;
    	this.cases = cases;
    }
    
    @Override
    public KStatement expand() {
    	// then part
        KStatement st = KSeq.buildKSeq(new ArrayList<KStatement>(), getStartPos(), getEndPos());
        for(Integer k : cases.keySet()) {
            KCall op = (new BinOp("==", cond, new IntConst(k, getStartPos(), getEndPos()), cond.getStartPos(), cond.getEndPos())).expand();
            st = new KIf(op, KSeq.buildKSeq(Statement.expandStatements(cases.get(k)), getStartPos(), getEndPos()), st, getStartPos(), getEndPos());
        }
    	/*Location thenStartPos = getStartPos(); // XXX: good approximation ?
    	Location thenEndPos = getStartPos();
    	List<KStatement> kthens = Statement.expandStatements(thens);
    	KStatement kthen = KSeq.buildKSeq(kthens, thenStartPos, thenEndPos);
    	
    	// else part
    	Location elseStartPos = thenEndPos; // XXX: good approximation ?
    	Location elseEndPos = thenEndPos;
    	List<KStatement> kelses = Statement.expandStatements(elses);
    	KStatement kelse = KSeq.buildKSeq(kelses, elseStartPos, elseEndPos);
    	return new KIf(cond.expand(), kthen, kelse, getStartPos(), getEndPos());*/
        return st;
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String switchNode = graph.addNode("Switch");
		String condNode = cond.buildDotGraph(graph);
		graph.addEdge(switchNode, condNode, "cond");
		/*String thenNode = cond.buildDotGraph(graph);
		graph.addEdge(ifNode, thenNode, "then");
		String elseNode = cond.buildDotGraph(graph);
		graph.addEdge(ifNode, elseNode, "else");*/
	
		return switchNode;
	}

    
    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append("switch (");
    	cond.prettyPrint(buf);
    	buf.append(") {\n");
        for(Integer k : cases.keySet()) {
            indent(buf, indent_level + 1);
            buf.append("case " + k + ":\n");
            Statement.prettyPrintStatements(buf, cases.get(k), indent_level + 2);
            indent(buf, indent_level + 2);
            buf.append("break;\n");
        }
    	indent(buf, indent_level);
    	buf.append("}");
    }
}
