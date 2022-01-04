package fileio;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChildren {
    private final List<Children> annualChildren;

    public AnnualChildren(Integer numberOfYears, Children children) {
        this.annualChildren = new ArrayList<>(numberOfYears + 1);
        this.annualChildren.add(0, children);
    }

    public void addAnnualChildren(Integer year, Children children){
        this.annualChildren.add(year, children);
    }

    public List<Children> getAnnualChildren() {
        return annualChildren;
    }
}
