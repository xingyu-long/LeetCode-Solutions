from leetcode.common.py_utils import TreeNode


class Solution:
    def lowestCommonAncestor(
        self, root: "TreeNode", p: "TreeNode", q: "TreeNode"
    ) -> "TreeNode":
        def find_lca(curr, p, q):
            if curr is None:
                return None
            if curr == p or curr == q:
                return curr

            left = find_lca(curr.left, p, q)
            right = find_lca(curr.right, p, q)
            if left and right:
                return curr
            elif left:
                return left
            return right

        res = find_lca(root, p, q)
        if res == p:
            # we found p, let's search q
            return res if find_lca(p, q, q) else None
        elif res == q:
            # we found q, let's search p
            return res if find_lca(q, p, p) else None
        else:
            return res


class Solution2:
    def lowestCommonAncestor(
        self, root: "TreeNode", p: "TreeNode", q: "TreeNode"
    ) -> "TreeNode":
        p_found = q_found = False

        def lca(root, p, q):
            nonlocal p_found, q_found

            if not root:
                return None

            left = lca(root.left, p, q)
            right = lca(root.right, p, q)
            # NOTE: move the comparison here so that
            # we can traversal the whole tree to avoid earlier return
            if root == p:
                p_found = True
                return root
            if root == q:
                q_found = True
                return root

            if left != None and right != None:
                return root
            if left:
                return left
            return right

        res = lca(root, p, q)
        return res if p_found and q_found else None
