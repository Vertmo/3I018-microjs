package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class BFetch extends BCInstr {
    public BFetch() {}

    @Override
    public String getOpcodeName() {
        return "BFETCH";
    }

    @Override
    public int getOpcode() {
        return 11;
    }

    @Override
    public void genBytecode(Serializer gen) {
        gen.encode(getOpcode());
    }

    @Override
    public int getSize() {
        return 1;
    }

    public String toString() {
        return "  BFETCH";
    }
}
