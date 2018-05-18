package microjs.jcompiler.middleend.kast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KWhile extends KStatement {
    private KExpr kcond;
    private KStatement kcorps;
    
    public KWhile(KExpr kcond, KStatement kcorps,
		  Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.kcond = kcond;
    	this.kcorps = kcorps;
    }
    
    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

	public KExpr getCond() {
		return kcond;
	}
	
	public KStatement getCorps() {
		return kcorps;
	}
}
