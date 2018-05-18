package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KEchange;
import microjs.jcompiler.utils.DotGraph;

public class Echange extends Statement {
    private String name_g;
    private String name_d;
    
    public Echange(String name_g, String name_d,
		   Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name_g = name_g;
	this.name_d = name_d;
    }
    
    @Override
    public KEchange expand() {
    	return new KEchange(name_g, name_d, getStartPos(), getEndPos());
    }

    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append(name_g);
    	buf.append(" <-> ");
    	buf.append(name_d);
    }
    
    @Override
    protected String buildDotGraph(DotGraph graph) {
	String echangeNode = graph.addNode("Echange[" + name_g + " , " +
					   name_d + "]");
	
	return echangeNode;
    }

    
    
}
