package fileio.InputClass;

import java.util.List;

public class AnnualChanges {
    private final double NewSantaBudget;
    private final List<Gift> giftList;
    private final List<Child> newChildren;
    private final List<ChildUpdate> childrenUpdates;

    public AnnualChanges(double newSantaBudget, List<Gift> giftList,
                         List<Child> newChildren, List<ChildUpdate> childrenUpdates) {
        NewSantaBudget = newSantaBudget;
        this.giftList = giftList;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public double getNewSantaBudget() {
        return NewSantaBudget;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    @Override
    public String toString() {
        return "AnnualChanges{" +
                "NewSantaBudget=" + NewSantaBudget +
                ", giftList=" + giftList +
                ", newChildren=" + newChildren +
                ", childrenUpdates=" + childrenUpdates +
                '}';
    }
}
