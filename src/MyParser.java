

import java.io.*;

public class MyParser {
    public static void main(String[] args) throws IOException {
        String inName = "", outName = "";

        if (args.length == 2) {
            inName = args[0];
            outName = args[1];
        } else {
            System.err.println("Usage: MyParser <input file> <output file>");
            System.exit(-1);
        }

        // open input file
        FileReader inFile = null;
        try {
            inFile = new FileReader(inName);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + inName + " not found.");
            System.exit(-1);
        }

        SimpleCCup P = new SimpleCCup(new Scanner(inFile));
        Program root = null;

        try {
            root = (Program) P.parse().value;
            System.out.println("Parsed Successfully!");
        } catch (Exception ex) {
            System.err.println("Exception occured during parse: " + ex);
            System.exit(-1);
        }
        
        // print tree
        PrintWriter p = null;
        try {
            File outFile = new File(outName);
            p = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
            
            //p.println("Haven't implemented yet!");
            //root.printTree(p,0);
            root.interpret(p);
        } catch (IOException ex) {
            System.err.println("File " + outName + " could not be opened.");
            System.exit(-1);
        } catch (Exception ex){
            System.err.println("Exception occured during print tree: " + ex);
            System.exit(-1);
        } finally {
            p.close();
        }
        
    }
}
