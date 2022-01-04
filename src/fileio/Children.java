package fileio;

import java.util.List;

public final class Children {
    private final List<Child> children;

    public Children(List<Object> dataStore) {
        this.children = Utils.convertObject((List<Object>) dataStore.get(0));
    }

    public List<Child> getChildren() {
        return children;
    }
}
