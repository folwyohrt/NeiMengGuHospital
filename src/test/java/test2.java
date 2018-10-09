import com.system.entity.SysSurgery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @Auther: 李景然
 * @Date: 2018/6/25 21:28
 * @Description:
 */
public class test2 {
    public static void main(String[] args) {
        test2 test2=new test2();
        //test2.longestConsecutive([1]);
    }

    public int longestConsecutive(int[] num) {
        HashSet<Integer> set=new HashSet<>();
        for (int item:num){
            set.add(item);
        }
        int max=1;
        for (int item:num){
            if(set.remove(item)){
                int val=item;
                int sum=1;
                int val_small=val-1;
                int val_big=val+1;
                while (set.remove(val_small)){
                    val_small--;
                    sum++;
                }
                while (set.remove(val_big)){
                    val_big++;
                    sum++;
                }
                max=Math.max(max,sum);
            }
        }
        return max;
    }




}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}