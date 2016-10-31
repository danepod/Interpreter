package de.hsd.rechar.interpreter;

public class Interpreter_min {
    private static final int OP_HALT = 0;
    private static final int OP_ADD = 1;
    private static final int OP_NEGATE = 2;

    private int PC = 0;
    private int AC = 0;
    private int instr = 0;
    private boolean running = true;
    private int[] memory = {OP_HALT};

    public static void main(String[] args) {
        int[] mem = {OP_ADD, 1, OP_ADD, 2, OP_NEGATE, OP_HALT};

        new Interpreter_min(mem);
    }

    public Interpreter_min(int mem[]) {
        this.memory = mem;
        run(memory);
    }

    private void run(int[] memory) {
        while (running) {
            instr = memory[PC];
            PC++;

            execute(instr);
        }
    }

    private void execute(int instr) {
        switch (instr) {
            case OP_HALT:
                running = false;
                break;
            case OP_ADD:
                int immediate = memory[PC];
                PC++;
                AC += immediate;
                break;
            case OP_NEGATE:
                AC = ~AC;
                break;
        }
    }
}

/*
run() {

    while running {

        instr = memory[pc];
        pc++;

        execute(instr)

    }

}

execute(instr) {

    switch instr {
        case OP_HALT;
            running = false;
            break

        case OP_ADD;
            imemdiate = memory[pc] ;
            pc++;
            ac = ac + imemdiate;
            break

        case OP_NEGATE:
            ac = ~ac;
            break;

    }

}
 */