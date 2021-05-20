package com.leyou.search.client;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class NameList
{

    private static int x = 100;
    final int i;
    private List names = new ArrayList();


    public NameList() {
        i=0;
    }

    public synchronized void add(String name)
    {
        names.add(name);
    }
    public synchronized void printAll() {
        for (int i = 0; i < names.size(); i++)
        {
            System.out.print(names.get(i) + "");
        }
    }

    public static void main(String[]args) {

//        Scanner sc = new Scanner(System.in);
//        int num =sc.nextInt();
//
//       ArrayList<String> list = new ArrayList<String>();
//        String s1 = String.valueOf(num);
//        char[] chars = s1.toCharArray();
//
//        for(int i=chars.length-1;i>=0;i--){
//            String s = String.valueOf(chars[i]);
//           if(!list.contains(s)){
//             list.add(s);
//           }
//        }
//      String s2 = String.join("",list);
//       int result = Integer.valueOf(s2);
//        System.out.println(result);


        //冒泡排序
//        int[] nums = new int[]{5,4,87,36,2,75,4,15};
//        for(int i=0;i<nums.length;i++){
//           for(int j=0;j<nums.length-i-1;j++){
//               if(nums[j]>nums[j+1]){
//                  int temp = nums[j];
//                  nums[j]=nums[j+1];
//                  nums[j+1]=temp;
//               }
//           }
//        }
//        System.out.println("从小到大排序后的结果是:");
//        for(int num:nums){
//            System.out.println(num+" ");
//        }

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生的数量(范围:0 < N <= 30000)：");
        int student_num = sc.nextInt();
        System.out.println("请输入操作的数目(范围:0 < M < 5000)：");
        int oper_num = sc.nextInt();
        System.out.println("请输入学生的成绩(学生ID编号从1编到N)：");
        Scanner sc1 = new Scanner(System.in);
        String score = sc1.nextLine();
        String a = StringUtils.deleteWhitespace(score);
        char[] chars1 = a.toCharArray();
        int[] array = new int[chars1.length];
        for(int i=0;i<chars1.length;i++){
              array[i]=Integer.parseInt(String.valueOf(chars1[i]));
        }
        //int[] array = Arrays.stream(scores).mapToInt(Integer::parseInt).toArray();
        int count=0;

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        while (count<oper_num){
             count++;
            Scanner scanner = new Scanner(System.in);

            HashMap<String,Object> map = new HashMap<String, Object>();
            System.out.println("请输入操作语句（只取‘Q’或‘U’）");
            String oper = scanner.nextLine();
            String s = StringUtils.deleteWhitespace(oper);
            char[] chars = s.toCharArray();
            String id_ent = String.valueOf(chars[0]);
            String start = String.valueOf(chars[1]);
            String end = String.valueOf(chars[2]);
           map.put("id_ent",id_ent);
           map.put("start",start);
           map.put("end",end);
           list.add(map);
        }

        String result = "";
        for(int i=0;i<list.size();i++){
            Map<String, Object> map = list.get(i);
            int start = Integer.parseInt((String) map.get("start"));
            int end = Integer.parseInt((String) map.get("end"));
            int max=0;
            if(map.get("id_ent").equals("Q")){
                for(int j = start-1;j<end-1;j++){
                     if(array[j]>array[j+1]){
                       max=array[j];
                     }else if(array[j]<array[j+1]){
                       max=array[j+1];
                     }else {
                         max = array[j];
                     }
                }
            }else if(map.get("id_ent").equals("U")){
                 array[start-1]=end;
            }
            if(max>0){
                result=result+max;
            }
        }
        char[] results = result.toCharArray();
        for(int i=0;i<results.length;i++){
            System.out.println(results[i]);
        }
    }

}
