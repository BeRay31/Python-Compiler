import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            ContextFreeGrammar cfgtest = new ContextFreeGrammar("pygrm.txt");
            cfgtest.PrintGrammar();
            System.out.println();
            CNF cnf = new CNF(cfgtest);
            cnf.PrintGrammar();
            //Vector<String> s = PyParse.ParseFile("sample.py");
            //System.out.println(s);
            //CYK.CheckGrammar(cnf, s, "PyFile");
        } catch (Exception E) {
        
        }

    }

}
