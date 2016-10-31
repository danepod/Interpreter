package de.hsd.rechar.interpreter;

public class Interpreter {
    private static final int NO_DATA = -1;
    private static final int OP_HALT = 0;
    private static final int OP_ADD = 1;
    private static final int OP_NEGATE = 2;

    private int PC = 0;                  // Program counter holds address of next instruction
    private int AC = 0;                  // Accumulator, a register for doing arithmetic
    private int instr = 0;               // A holding register for the current instruction
    private int instr_type = 0;          // Instruction type (opcode)
    private int data_loc = 0;            // Address of the data, -1 if none
    private int data = 0;                // Current operand
    private boolean run_bit = true;      // Bit, that can be turned off to halt the machine

    public static void main(String[] args) {
        int[] mem = {OP_ADD, 1, OP_ADD, 2, OP_NEGATE, OP_HALT}; // Last Operation MUST BE OP_HALT, otherwise Buffer Overflow
        int start = 0;

        new Interpreter(mem, start);
    }

    public Interpreter(int memory[], int starting_address) {
        PC = starting_address;
        interpret(memory, starting_address);
    }

    /* This procedure interprets programs for a simple machine with
     * instructions having one memory operand. The machine has a register
     * AC (accumulator), used for arithmetic. The ADD instruction adds an
     * integer in memory to the AC, for example. The interpreter keeps
     * running until the run bit is turned off by the HALT instruction.
     * The state of a process running on this machine consists of the
     * memory , the program counter , the run bit , and the AC. The input
     * parameters consist of the memory image and the starting address.
     */
    private void interpret(int memory[], int starting_address) {
        while(run_bit) {
            instr = memory[PC];                         // fetch next instruction into instr
            PC += 1;                                    // increase program counter
            instr_type = get_instr_type(instr);         // determine instruction type
            data_loc = find_data(instr, instr_type);    // locate data (-1 if none)

            if(data_loc != NO_DATA) {                   // if data_loc is -1, there's no operand
                data = memory[data_loc];                // fetch the data
            } else {
                data = NO_DATA;                         // clear data if the operation doesn't use it
            }

            execute(instr_type, data);                  // execute instruction
        }
    }

    private int get_instr_type(int addr) {
        int[] result = {OP_HALT, OP_ADD, OP_NEGATE};

        return result[addr];
    }

    private int find_data(int instr, int type) {
        if(instr_type == OP_ADD) {
            return PC;
        }

        return -1;
    }

    private void execute(int type, int data) {
        if(data != NO_DATA) {
            PC += 1;                                    // jump over data memory for next instruction
        }

        if(type == OP_HALT) {
            run_bit = false;
        } else if(type == OP_ADD) {
            AC += data;                                 // add data onto accumulator
        } else if(type == OP_NEGATE) {
            AC = ~AC ;                                  // bitwise NOT on AC
        }
    }
}
