import java.util.*;
import java.io.*;

class ContextFreeGrammar {
    Vector<ContextFreeRule> _rules;

    public ContextFreeGrammar() {
         this._rules = new Vector<ContextFreeRule>();
    }

    public ContextFreeGrammar(String filename) throws FileNotFoundException {
        this._rules = new Vector<ContextFreeRule>();
        Scanner fs = new Scanner(new File(filename));
        while (fs.hasNextLine()) {
            String[] line = fs.nextLine().split(" ");
            if (line.length > 2)  {
                this.FromLine(line);
            }
        }
    }
    
    public void AddRule(ContextFreeRule rule) {
        this._rules.add(rule); 
    }
    
    public Vector<ContextFreeRule> GetRules() {
        return this._rules;
    } 

    void FromLine(String[] line) {
        int i = 2;
        while (i < line.length) {
            ContextFreeRule rule = new ContextFreeRule(line[0]);
            while (i < line.length && !line[i].equals("|")) {
                if (line[i].equals(".space")) {
                    rule.AppendRight(" ");
                } else if (line[i].equals(".tab")) {
                    rule.AppendRight("\t");
                } else if (line[i].equals(".newline")) {
                    rule.AppendRight("\n");
                } else if (line[i].equals(".guard")) {
                    rule.AppendRight("|");
                } else if (line[i].equals(".e")) {
                    rule.AppendRight("");
                } else {
                    rule.AppendRight(line[i]);
                }
                i++;
            }
            this.AddRule(rule);
            i++;
        }
    }

    public Set<String> FromTerminal(String terminal) {
        Set<String> s = new HashSet<String>();
        for (ContextFreeRule rule : this._rules) {
            if (rule.IsTerminal() && rule.GetTerminal().equals(terminal)) {
                s.add(rule.GetLeft());
            }
        }
        return s;
    }
    
    public Set<String> FromNonTerminal(String nonterm1, String nonterm2) {
        Set<String> s = new HashSet<String>();
        for (ContextFreeRule rule : this._rules) {
            if (!rule.IsTerminal()) {
                if (rule.GetRight(0).equals(nonterm1) && rule.GetRight(1).equals(nonterm2)) {
                    s.add(rule.GetLeft());
                }
            }
        }
        return s;
    }

    public void PrintGrammar() {
        for (ContextFreeRule rule : _rules) {
            rule.PrintRule();
        }
    }
   
    public boolean IsTerminal(String str) {
        boolean check = true;
        for (ContextFreeRule rule : _rules) {
            if (check) {
                check = !str.equals(rule.GetLeft());
            }
        }
        return check;
    }

    public Set<String> CheckNullable() {
        Set<String> check = new HashSet<String>();
        boolean addnull = false;
        do {
        addnull = false;
        for (ContextFreeRule rule : _rules) {
            String l = rule.GetLeft();
            if (!check.contains(l)) {
            if (rule.GetRightSize() == 1) {
                String a = rule.GetRight(0);
                if(a.equals("")) {
                    check.add(l);
                    addnull = true; 
                } else if (check.contains(a)) {
                    check.add(l);
                    addnull = true;
                }
            } else {
                String a = rule.GetRight(0);
                String b = rule.GetRight(1);
                if (a.equals(l))
                    if (check.contains(b)) {
                        check.add(l);
                        addnull = true;
                    }
                else if (b.equals(l))
                    if (check.contains(a)) {
                        check.add(l);
                        addnull = true;
                    }
                else if (check.contains(a) && check.contains(b)) {
                    check.add(l);
                    addnull = true;
                }
            }
            }
        }
        } while (addnull);
        return check;
    }
    public boolean IsNullable (String Symbl){
        return CheckNullable().contains(Symbl);
    }

    ContextFreeGrammar ToCNF() {
        ContextFreeGrammar cnf = new ContextFreeGrammar();
        return cnf;
    } 
    
}
