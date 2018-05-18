package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KEchange extends KStatement {
    private String name_g;
    private String name_d;
    
    public KEchange(String name_g, String name_d,
		    Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name_g = name_g;
	this.name_d = name_d;
    }

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

	public String getVarNameG() {
		return name_g;
	}
	
	public String getVarNameD() {
		return name_d;
	}
}
