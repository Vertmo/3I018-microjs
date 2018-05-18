package microjs.jcompiler.middleend.kast;

import java.util.List;		
import java.util.LinkedHashSet;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KClosure extends KExpr {
    private List<String> params;
    private LinkedHashSet<String> variables_libres;
    private KStatement body;
    
    public KClosure(List<String> params, KStatement body, Location startPos, Location endPos) {
    	super(startPos, endPos);
    	this.params = params;
    	this.body = body;
        this.variables_libres = new LinkedHashSet<String>();
    }
    
    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }
    
    public List<String> getParams() {
    	return params;
    }
    
    public KStatement getBody() {
    	return body;
    }

    public LinkedHashSet<String> getVariablesLibres() {
        return variables_libres;
    }

    public void setVariablesLibres(LinkedHashSet<String> variables_libres) {
        this.variables_libres = variables_libres;
    }
}
