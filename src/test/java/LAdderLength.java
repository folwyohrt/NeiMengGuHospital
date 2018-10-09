import java.util.*;

/**
 * @Auther: 李景然
 * @Date: 2018/7/29 16:46
 * @Description:
 */
public class LAdderLength {
    public static void main(String[] args) {
        System.out.println(new Date());
        LAdderLength lAdderLength=new LAdderLength();
        String start="hit";
        String end="cog";
        HashSet<String> dict=new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        int result=lAdderLength.ladderLength(start,end,dict);
    }

    //使用bfs(广度优先搜素)，其中res用于记录结果，size用于记录每一层的元素个数，遍历完一层res++
    //注意遍历过的要从字典中删除。
    public int ladderLength(String start, String end, HashSet<String> dict) {
        LinkedList<String> squeue=new LinkedList<String>();//队列
        squeue.offer(start);//入队
        dict.remove(start);
        dict.remove(end);
        int level=0;
        while (!squeue.isEmpty()){
            level++;
            int size=squeue.size();
            for(int i=0;i<size;i++){//取出当前层的所有元素
                String s=squeue.poll();//出队
                if (isDiffOne(s,end)){
                    return level+1;
                }
                for(Iterator<String> it=dict.iterator();it.hasNext();){//获取下一层所有可能的元素
                    String ss=it.next();
                    if (isDiffOne(s,ss)){//下一个
                        squeue.offer(ss);
                        it.remove();//删除已入元素
                    }
                }
            }
        }
        return 0;
    }

    public boolean isDiffOne(String str1, String str2) {
        int count=0;
        for (int i=0;i<str1.length();i++){
            if(str1.charAt(i)!=str2.charAt(i)){
                count++;
            }
            if(count>1)
                return false;
        }
        return true;
    }
}
