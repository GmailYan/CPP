package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.tree.TreeNode;

public class CompanyCategoryTreeNode extends TreeNode {
	public CompanyCategoryTreeNode (Long categoryId, String categoryName, Long parentId) {
		setAttribute("categoryId", categoryId);
		setAttribute("categoryName", categoryName);
		setAttribute("parentId", parentId);
	}
}
