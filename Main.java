
public static void main(String[] args) {
  
    String[] inputs = {"a_example", "b_small", "c_medium", "d_big"};
    for (String in: inputs) {
        String fileName = "path/to/datasets/" + in;
        Simulator simulator = new Simulator(fileName);
        simulator.parseInput();
        simulator.simulate();
        simulator.printOutput();
    }