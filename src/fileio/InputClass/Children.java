package fileio.InputClass;

import fileio.Utils;

import java.util.ArrayList;
import java.util.List;

public final class Children {
    private List<Child> children;

    public Children(Children children) {
        this.children = new ArrayList<>();
        for (Child child : children.getChildren()) {
            if (child.getAge() < 18) {
                this.children.add(new Child(child));
            }
        }
    }

    public Children(List<Object> dataStore) {
        this.children = Utils.convertObject((List<Object>) dataStore.get(0));
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Children{" +
                "children=" + children +
                "}\n";
    }
}
