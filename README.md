# Interpreter
This is an Interpreter built as a homework assignment for the lecture of Rechner-Architektur (computer architecture) at Hochschule Düsseldorf.

There are two versions of the same program in this repository: The first, Interpreter.java, uses the same structure that is shown on page 102 in the lecture presentation. It uses the same functions used there with the same signatures. As it wasn't clear where operations like ADD got their data from, I assumed the data would be at the next possible address in memory.
As it seems, the origin of the example on page 102 ([see below](#see-also)) encodes the operation together with its used data together in one variable. One possible way to do that could be to use the first 32 bits of an integer to store the operation and the remaining 32 bits to store its data. You could then go and decode this integer with the get_instr_type and find_data functions.

The second version, Interpreter_min.java, gets rid of the decoding functions and flat-out assumes the data to be used is stored on the next memory address. The execute() function itself cares about getting the data from memory and setting the program counter up if it used data from memory.

In case you are interested, a friend of mine has written an Intel 8080 emulator which shows a more fleshed out implementation of the same idea. You can find it at [Github](https://github.com/BonsaiDen/I8080). You may take a look at the [execution function](https://github.com/BonsaiDen/I8080/blob/master/Intel8080/Cpu.c#L58) and the [opcode table](https://github.com/BonsaiDen/I8080/blob/master/Intel8080/table.c#L32) there.

## See also
  - Andrew S. Tanenbaum and Todd Austin: Rechnerarchitektur Von der digitalen Logik zum Parallelrechner (Pearson, 6th edition, 2014)
