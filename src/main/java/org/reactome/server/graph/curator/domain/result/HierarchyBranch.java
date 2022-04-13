package org.reactome.server.graph.curator.domain.result;

import java.util.List;

public class HierarchyBranch {

    private List<HierarchyTreeItem> branch;

    public List<HierarchyTreeItem> getPathways(){
        return branch;
    }

    public HierarchyBranch(List<HierarchyTreeItem> branch) {
        this.branch = branch;
    }

}
