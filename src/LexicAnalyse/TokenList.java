package LexicAnalyse;

import java.util.ArrayList;
import java.util.List;

public class TokenList<T> {
    private List<T> list = new ArrayList<T>();

    public void add(T token) {
        list.add(token);
    }

    public List<T> getList() {
        return this.list;
    }
}
