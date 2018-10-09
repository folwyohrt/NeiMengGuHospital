import java.util.ArrayList;

/**
 * @Auther: 李景然
 * @Date: 2018/7/31 16:49
 * @Description:
 */
public class Triangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        for (int i=0;i<triangle.size();i++){
            int min=Integer.MIN_VALUE;
            for (int j=0;j<triangle.get(i).size();j++){
                min=Math.min(min,triangle.get(i).get(j));

            }
        }
        return 0;
    }
}
