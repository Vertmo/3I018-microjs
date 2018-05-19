package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Block extends BCValue {
    private int size;

    public Block(int size) {
        this.size = size;
    }

    @Override
    public int getOpcode() {
        return 5;
    }

    @Override
    public String getOpcodeName() {
        return "BLOCK";
    }

    @Override
    public void genBytecode(Serializer gen) {
        gen.encode(getOpcode());
        gen.encode(size);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String toString() {
        return "BLOCK " + size;
    }

}
