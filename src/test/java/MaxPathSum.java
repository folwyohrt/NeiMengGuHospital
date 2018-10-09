/**
 * @Auther: 李景然
 * @Date: 2018/7/31 10:24
 * @Description:
 */
public class MaxPathSum {
    int maxValue=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return maxValue;
    }

    int maxPathDown(TreeNode node){
        if(node==null)
            return 0;
        int left=Math.max(0,maxPathDown(node.left));
        int right=Math.max(0,maxPathDown(node.right));
        maxValue=Math.max(maxValue,left+right+node.val);
        return Math.max(left,right)+node.val;
    }
}
