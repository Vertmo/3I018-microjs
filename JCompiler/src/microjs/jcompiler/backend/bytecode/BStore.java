package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class BStore extends BCInstr {
    public BStore() {}

    @Override
    public String getOpcodeName() {
        return "BSTORE";
    }

    @Override
    public int getOpcode() {
        return 12;
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
        return "  BSTORE";
    }
}
