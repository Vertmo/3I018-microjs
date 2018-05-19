package microjs.jcompiler.middleend.kast;

import java.util.LinkedHashSet;

public class KVariablesLibres implements KASTVisitor {
    private LinkedHashSet<String> variables_libres;

    public KVariablesLibres() {
        variables_libres = new LinkedHashSet<>();
    }

    public void visit(KProg prog) {
        prog.getBody().accept(this);
    }

    public void visit(KVoidExpr stmt) {
        stmt.getExpr().accept(this);
    }

    public void visit(KVar stmt) {
        stmt.getExpr().accept(this);
        variables_libres.add(stmt.getName());
    }

    public void visit(KIf stmt) {
        LinkedHashSet<String> old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getCond().accept(this);
        this.variables_libres = old;
        old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getThen().accept(this);
        this.variables_libres = old;
        old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getElse().accept(this);
        this.variables_libres = old;
    }

    public void visit(KSeq stmt) {
        for(KStatement st : stmt.getStatements()) {
            st.accept(this);
        }
    }

    public void visit(KAssign stmt) {}

    public void visit(KReturn stmt) {
        stmt.getExpr().accept(this);
    }

    public void visit(KInt expr) {}

    public void visit(KTrue expr) {}

    public void visit(KFalse expr) {}

    public void visit(KEVar expr) {}

    public void visit(KCall expr) {
        expr.getFun().accept(this);   
    }

    public void visit(KClosure expr) {
        expr.setVariablesLibres((LinkedHashSet<String>) variables_libres.clone());
        variables_libres.addAll(expr.getParams());
        expr.getBody().accept(this);
        variables_libres.removeAll(expr.getParams());
    }

    public void visit(KEchange stmt) {}

    public void visit(KWhile stmt) {
        LinkedHashSet<String> old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getCond().accept(this);
        this.variables_libres = old;
        old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getCorps().accept(this);
        this.variables_libres = old;
    }

    public void visit(KDoWhile stmt) {
        LinkedHashSet<String> old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getCorps().accept(this);
        this.variables_libres = old;
        old = (LinkedHashSet<String>) this.variables_libres.clone();
        stmt.getCond().accept(this);
        this.variables_libres = old;
    }

    public void visit(KTab expr) {
        for(KExpr e: expr.getContent()) {
            e.accept(this);
        }
    }

    public void visit(KTabAccess expr) {
        expr.getIndex().accept(this);
    }

    public void visit(KTabAssign stmt) {
        stmt.getIndex().accept(this);
        stmt.getExpr().accept(this);
    }
}
