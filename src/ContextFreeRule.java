import java.util.*;

class ContextFreeRule {
    String _left;
    Vector<String> _right;

    public ContextFreeRule(String left) {
        this._left = left;
        this._right = new Vector<String>();
    }
    public ContextFreeRule(String left, String right) {
        this._left = left;
        this._right = new Vector<String>();
        this._right.add(right);
    }
    public ContextFreeRule(String left, String right1, String right2) {
        this._left = left;
        this._right = new Vector<String>();
        this._right.add(right1);
        this._right.add(right2);
    }
    public ContextFreeRule(String left, Vector<String> right) {
        this._left = left;
        this._right = new Vector<String>();
        for (int i = 0; i < right.size(); i++) {
            this._right.add(right.get(i));
        }
    }
    public boolean IsTerminal() {
        return this._right.size() == 1;
    }
    public String GetTerminal() {
        return this._right.get(0);
    }
    public String GetLeft() {
        return this._left;
    }
    public Vector<String> GetRights() {
        return this._right;
    }
    public String GetRight(int i) {
        return this._right.get(i);
    }
    public int GetRightSize() {
        return this._right.size();
    }

    public void AppendRight(String symbol) {
        this._right.add(symbol);
    }

    public void PrintRule() {
        System.out.print(this._left + " -> ");
        for (String r : _right) {
            System.out.println(r + " ");
        }
    }
}
