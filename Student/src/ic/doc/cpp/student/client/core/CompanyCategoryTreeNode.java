package ic.doc.cpp.student.client.core;

import com.smartgwt.client.widgets.tree.TreeNode;

public class CompanyCategoryTreeNode extends TreeNode {
	public CompanyCategoryTreeNode (Long categoryId, String displayNameValue, String acturalNameValue, Long parentId) {
		setAttribute("categoryId", categoryId);
		setAttribute("categoryName", displayNameValue);
		setAttribute("categoryActuralName", acturalNameValue);
		setAttribute("parentId", parentId);
	}
}
