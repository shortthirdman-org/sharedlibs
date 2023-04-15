package com.shortthirdman.sharedlibs.util;

import com.shortthirdman.sharedlibs.common.CounterNode;
import com.shortthirdman.sharedlibs.common.ListNode;
import com.shortthirdman.sharedlibs.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Utility helper class for manipulating binary tree <br>
 * <a href="https://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/">Validate BST</a>
 */
public class BinaryTreeUtils {

    private static ListNode h;

    /**
     * Post-order traversal of a binary tree
     * @param root the root node
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode prev = null;
        while (!stack.empty()) {
            TreeNode curr = stack.peek();

            if (prev == null || prev.getLeftNode() == curr || prev.getRightNode() == curr) {
                if (curr.getLeftNode() != null) {
                    stack.push(curr.getLeftNode());
                } else if (curr.getRightNode() != null) {
                    stack.push(curr.getRightNode());
                } else {
                    stack.pop();
                    list.add(curr.getValue());
                }
            } else if (curr.getLeftNode() == prev) {
                if (curr.getRightNode() != null) {
                    stack.push(curr.getRightNode());
                } else {
                    stack.pop();
                    list.add(curr.getValue());
                }
            } else if (curr.getRightNode() == prev) {
                stack.pop();
                list.add(curr.getValue());
            }

            prev = curr;
        }

        return list;
    }

    /**
     * Pre-order traversal of a binary tree
     * @param root the root node
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode n = stack.pop();
            list.add(n.getValue());

            if (n.getRightNode() != null) {
                stack.push(n.getRightNode());
            }

            if (n.getLeftNode() != null) {
                stack.push(n.getLeftNode());
            }
        }

        return list;
    }

    /**
     * In-order traversal of binary tree
     * @param root the root node of binary tree
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.getLeftNode();
        }

        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            result.add(t.getValue());

            t = t.getRightNode();
            while (t != null) {
                stack.push(t);
                t = t.getLeftNode();
            }
        }

        return result;
    }

    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values
     * @param root the root node of binary tree
     * @return
     */
    public static List<ArrayList<Integer>> bottomUplevelOrderTraversal(TreeNode root) {
        List<ArrayList<Integer>> result = new ArrayList<>();

        if(root == null){
            return result;
        }

        LinkedList<TreeNode> current = new LinkedList<>();
        LinkedList<TreeNode> next = new LinkedList<>();
        current.offer(root);

        ArrayList<Integer> numberList = new ArrayList<>();

        while (!current.isEmpty()) {
            TreeNode head = current.poll();

            numberList.add(head.getValue());

            if (head.getLeftNode() != null) {
                next.offer(head.getLeftNode());
            }

            if (head.getRightNode() != null) {
                next.offer(head.getRightNode());
            }

            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<>();
                result.add(numberList);
                numberList = new ArrayList<>();
            }
        }

        List<ArrayList<Integer>> reversedResult = new ArrayList<>();

        for (int i = result.size() - 1; i >= 0; i--) {
            reversedResult.add(result.get(i));
        }

        return reversedResult;
    }

    /**
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     * @param root the root node of binary tree
     * @return
     */
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();

        nodeQueue.offer(root);
        levelQueue.offer(1);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();

            List<Integer> list;

            if (result.size() < level) {
                list = new ArrayList<>();
                result.add(list);
            } else {
                list = result.get(level - 1);
            }

            list.add(node.getValue());

            if (node.getLeftNode() != null) {
                nodeQueue.offer(node.getLeftNode());
                levelQueue.offer(level + 1);
            }

            if (node.getRightNode() != null) {
                nodeQueue.offer(node.getRightNode());
                levelQueue.offer(level + 1);
            }
        }

        return result;
    }

    /**
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
     * @param root
     * @return
     */
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();
        q1.offer(root);
        q2.offer(0);

        while (!q1.isEmpty()) {
            TreeNode node = q1.poll();
            int order = q2.poll();

            //add to map
            ArrayList<Integer> list = map.computeIfAbsent(order, k -> new ArrayList<>());
            list.add(node.getValue());

            if (node.getLeftNode() != null) {
                q1.offer(node.getLeftNode());
                q2.offer(order - 1);
            }

            if (node.getRightNode() != null) {
                q1.offer(node.getRightNode());
                q2.offer(order + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>(map.values());

        return result;
    }

    /**
     * @see @verticalOrderTraversal
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        int[] mm = new int[2];
        getMinMax(root, mm, 0);

        int len = mm[1]-mm[0]+1;

        for (int i = 0; i < len; i++) {
            result.add(new ArrayList<Integer>());
        }

        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<Integer> q2 = new LinkedList<>();

        q1.offer(root);
        q2.offer(0);

        while (!q1.isEmpty()) {
            TreeNode h = q1.poll();
            int order = q2.poll();

            result.get(order - mm[0]).add(h.getValue());

            if (h.getLeftNode() != null) {
                q1.offer(h.getLeftNode());
                q2.offer(order - 1);
            }

            if (h.getRightNode() != null) {
                q1.offer(h.getRightNode());
                q2.offer(order + 1);
            }
        }

        return result;
    }

    private static void getMinMax(TreeNode node, int[] mm, int order){
        if (node == null) {
            return;
        }

        mm[0] = Math.min(mm[0], order);
        mm[1] = Math.max(mm[1], order);

        getMinMax(node.getLeftNode(), mm, order - 1);
        getMinMax(node.getRightNode(), mm, order + 1);
    }

    /**
     * Builds a binary tree
     * @param inorder the in-order array
     * @param postorder the post-order array
     * @return tree node
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) throws Exception {
        if (Objects.isNull(inorder) || Objects.isNull(postorder)) {
            throw new Exception("In-order or post-order arrays are null or empty");
        }
        int inStart = 0;
        int inEnd = inorder.length - 1;
        int postStart = 0;
        int postEnd = postorder.length - 1;

        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }

    /**
     * Builds a binary tree
     * @param inorder the in-order array
     * @param inStart start index of in-order
     * @param inEnd end index of in-order
     * @param postorder the post-order array
     * @param postStart start index of post-order
     * @param postEnd end index of post-order
     * @return tree node
     */
    public static TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) throws Exception {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }

        root.setLeftNode(buildTree(inorder, inStart, k - 1, postorder, postStart, postStart + k - (inStart + 1)));
        root.setRightNode(buildTree(inorder, k + 1, inEnd, postorder, postStart + k- inStart, postEnd - 1));

        return root;
    }

    /**
     * Given a binary tree, collect a tree's nodes. Collect and remove all leaves, repeat until the tree is empty.
     * @param root the root node of binary tree
     * @return
     */
    public static List<List<Integer>> findLeaves(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        getOrder(root, map);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, HashSet<TreeNode>> reversed = new HashMap<>();

        for (Map.Entry<TreeNode, Integer> entry : map.entrySet()) {
            min = Math.min(min, entry.getValue());
            max = Math.max(max, entry.getValue());

            HashSet<TreeNode> set = reversed.getOrDefault(entry.getValue(), new HashSet<>());
            set.add(entry.getKey());
            reversed.put(entry.getValue(), set);
        }


        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            HashSet<TreeNode> set = reversed.get(i);
            ArrayList<Integer> l = new ArrayList<>();
            for (TreeNode td : set) {
                l.add(td.getValue());
            }
            result.add(l);
        }

        return result;
    }

    private static int getOrder(TreeNode root, HashMap<TreeNode, Integer> map){
        if (root == null) {
            return 0;
        }

        int left = getOrder(root.getLeftNode(), map);
        int right = getOrder(root.getRightNode(), map);

        int order = Math.max(left, right) + 1;
        map.put(root, order);

        return order;
    }

    /**
     * Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.
     * @param root the root node of binary tree
     * @return the maximum path sum
     */
    public static int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public static int calculateSum(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left = calculateSum(root.getLeftNode(), max);
        int right = calculateSum(root.getRightNode(), max);

        int current = Math.max(root.getValue(), Math.max(root.getValue() + left, root.getValue() + right));

        max[0] = Math.max(max[0], Math.max(current, left + root.getValue() + right));

        return current;
    }

    /**
     * @param root the root node of binary tree
     * @return true, if tree is height-balanced
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return getHeight(root) != -1;
    }

    public static int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        int left = getHeight(root.getLeftNode());
        int right = getHeight(root.getRightNode());

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }

    /**
     * Given a binary tree, find its maximum depth.<br>
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * @param root the root node os bincry tree
     * @return the maximum depth
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.getLeftNode());
        int rightDepth = maxDepth(root.getRightNode());

        int bigger = Math.max(leftDepth, rightDepth);

        return bigger + 1;
    }

    /**
     * Given a binary tree, find its minimum depth.<br>
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * @param root the root node of binary tree
     * @return minimum depth of binary tree
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> counts = new LinkedList<>();

        nodes.add(root);
        counts.add(1);

        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.remove();
            int count = counts.remove();

            if (curr.getLeftNode() == null && curr.getRightNode() == null) {
                return count;
            }

            if (curr.getLeftNode() != null) {
                nodes.add(curr.getLeftNode());
                counts.add(count + 1);
            }

            if (curr.getRightNode() != null) {
                nodes.add(curr.getRightNode());
                counts.add(count + 1);
            }
        }

        return 0;
    }

    /**
     * Given a binary tree, return all root-to-leaf paths.
     *
     * @param root the root node of binary tree
     * @return the list of all binary tree path
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> finalResult = new ArrayList<>();

        if (root == null) {
            return finalResult;
        }

        ArrayList<String> curr = new ArrayList<>();
        ArrayList<ArrayList<String>> results = new ArrayList<>();

        dfs(root, results, curr);

        for (ArrayList<String> al : results) {
            StringBuilder sb = new StringBuilder();
            sb.append(al.get(0));
            for (int i = 1; i < al.size(); i++) {
                sb.append("->").append(al.get(i));
            }

            finalResult.add(sb.toString());
        }

        return finalResult;
    }

    private static void dfs(TreeNode root, ArrayList<ArrayList<String>> list, ArrayList<String> curr){
        curr.add(String.valueOf(root.getValue()));

        if (root.getLeftNode() == null && root.getRightNode() == null) {
            list.add(curr);
            return;
        }

        if (root.getLeftNode() != null) {
            ArrayList<String> temp = new ArrayList<>(curr);
            dfs(root.getLeftNode(), list, temp);
        }

        if (root.getRightNode() != null) {
            ArrayList<String> temp = new ArrayList<>(curr);
            dfs(root.getRightNode(), list, temp);
        }
    }

    /**
     * @param root the root node of binary tree
     * @param p the left node
     * @param q the right node
     * @return the lowest common ancestor node
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        CounterNode n = buildCounterNode(root, p, q);
        return n.getNode();
    }

    private static CounterNode buildCounterNode(TreeNode root, TreeNode p, TreeNode q){
        if (root == null) {
            return new CounterNode(null, 0);
        }

        CounterNode left = buildCounterNode(root.getLeftNode(), p, q);
        if (left.getCount() == 2) {
            return left;
        }

        CounterNode right = buildCounterNode(root.getRightNode(), p, q);
        if (right.getCount() == 2) {
            return right;
        }

        int c = left.getCount() + right.getCount() + (root == p ? 1 : 0) + (root == q ? 1 : 0);

        return new CounterNode(root, c);
    }

    /**
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode prev = head;
        ListNode p = head.getNext();

        while (p != null) {
            if (p.getValue() == prev.getValue()) {
                prev.setNext(p.getNext());
            } else {
                prev = p;
            }
            p = p.getNext();
        }

        return head;
    }

    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * @param root the root node of binary tree
     */
    public void flattenTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;

        while (p != null || !stack.empty()) {
            if (p!= null) {
                if (p.getRightNode() != null) {
                    stack.push(p.getRightNode());
                }

                if (p.getLeftNode() != null) {
                    p.setRightNode(p.getLeftNode());
                    p.setLeftNode(null);
                } else if (!stack.empty()) {
                    TreeNode temp = stack.pop();
                    p.setRightNode(temp);
                }
                p = p.getRightNode();
            }
        }
    }

    /**
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        h = head;
        int len = getLength(head);
        return buildTreeBottomUp(0, len - 1);
    }

    private static int getLength(ListNode head) {
        int len = 0;
        ListNode p = head;

        while (p != null) {
            len++;
            p = p.getNext();
        }
        return len;
    }

    private static TreeNode buildTreeBottomUp(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        TreeNode left = buildTreeBottomUp(start, mid - 1);
        TreeNode root = new TreeNode(h.getValue());
        h = h.getNext();
        TreeNode right = buildTreeBottomUp(mid + 1, end);

        root.setLeftNode(left);
        root.setRightNode(right);

        return root;
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static boolean isValidBST(TreeNode p, double min, double max){
        if (p == null) {
            return true;
        }

        if (p.getValue() <= min || p.getValue() >= max) {
            return false;
        }

        return isValidBST(p.getLeftNode(), min, p.getValue()) && isValidBST(p.getRightNode(), p.getValue(), max);
    }
}
