package LexicAnalyse;

import java.util.ArrayList;
import java.util.List;

public class AnalyseList<T> {
    private List<T> list = new ArrayList<T>();

    public void add(T analyser) {
        list.add(analyser);
    }

    public List<T> getList()
    {
        return this.list;
    }
}

