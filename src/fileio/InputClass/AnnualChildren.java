package fileio.InputClass;

import main.SingleRun;

import java.util.*;

import static main.SingleRun.calculateData;

public final class AnnualChildren {
    private final List<Children> annualChildren;

    public AnnualChildren(Integer numberOfYears, Children children) {
        this.annualChildren = new ArrayList<>(numberOfYears + 1);
        this.annualChildren.add(0, children);
    }

    public void updateChild(Children children, List<Child> newChildren) {
        int ok;
        for(Child newChild: newChildren) {
            ok = 0;
            for(Child child : children.getChildren()) {
                if (child.getLastName().equals(newChild.getLastName())
                        && child.getFirstName().equals(newChild.getFirstName())
                        && child.getAge().equals(newChild.getAge())) {
                    ok = 1;
                }
                if(newChild.getAge() > 18) {
                    ok = 1;
                }
            }
            if (ok == 0) {
                children.getChildren().add(newChild);
            }
        }
        children.getChildren().sort(Comparator.comparingInt(Child::getId));
    }

    public void updateGift(HashMap<String, ArrayList<Gift>> listGift, List<Gift> newGifts) {
        for(Gift newGift: newGifts) {
            if(listGift.containsKey(newGift.getCategory())) {
                listGift.get(newGift.getCategory()).add(newGift);
                listGift.get(newGift.getCategory()).sort(Comparator.comparingDouble(Gift::getPrice));
            } else {
                ArrayList<Gift> newCategory = new ArrayList<>();
                newCategory.add(newGift);
                listGift.put(newGift.getCategory(), newCategory);
            }
        }
    }

    public void updateDataChild(List<ChildUpdate> childUpdates, Children children){
        for (ChildUpdate childUpdate: childUpdates) {
            for(Child child: children.getChildren()) {
                if(child.getId().equals(childUpdate.getId())) {
                    child.updateGiftPreferences(childUpdate.getGiftsPreference());
                    child.addNiceScore(childUpdate.getNiceScore());
                }
            }
        }
    }

    public void annualUpdate(List<AnnualChanges> annualChanges, AnnualChildren annualChildren,
                             HashMap<String, ArrayList<Gift>> listGift, int numberOfYears) {
        double santaBudget;
        for (int i = 0; i < numberOfYears; ++i) {
            santaBudget = annualChanges.get(i).getNewSantaBudget();

            Children children1 = new Children(annualChildren.getAnnualChildren().get(i));

            updateGift(listGift, annualChanges.get(i).getGiftList());
            updateChild(children1, annualChanges.get(i).getNewChildren());
            updateDataChild(annualChanges.get(i).getChildrenUpdates(), children1);
            calculateData(children1, santaBudget, listGift);

            annualChildren.getAnnualChildren().add(children1);
        }
    }

    public List<Children> getAnnualChildren() {
        return annualChildren;
    }

    @Override
    public String toString() {
        return "AnnualChildren{" +
                "annualChildren=" + annualChildren +
                "}\n";
    }
}
