class Main {
    /*
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expecting argument");
            return;
        }

        Simulation.play(args[0]);
    }
    */
    public static void main(String[] args) {
        String[] examples = {"a_example", "b_should_be_easy", "c_no_hurry", "d_metropolis", "e_high_bonus"};
        int total = 0;
        for (String filename : examples) {
            Simulation sim = new GreedySimulation();
            sim.read("inputs/" + filename + ".in");
            total += sim.simulate();
            sim.write("outputs/" + filename + ".out");
        }
        System.out.println(total);
    }
}
