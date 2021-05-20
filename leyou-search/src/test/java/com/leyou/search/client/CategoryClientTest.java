package com.leyou.search.client;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {

    static int arr[] = new int[5];

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsRepository goodsRepository;


    @Test
    public void createIndex() {
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Goods.class);
        Integer page = 1;
        Integer rows = 100;

        do {
            // 分批查询spuBo
            PageResult<SpuBo> pageResult = this.goodsClient.querySpuBoByPage(null, null, page, rows);
            List<SpuBo> items = pageResult.getItems();
            // 遍历spubo集合转化为List<Goods>
            List<Goods> goodsList = items.stream().map(spuBo -> {
                try {
                    return searchService.buildGoods(spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsList);

            // 获取当前页的数据条数，如果是最后一页，没有100条
            rows = items.size();
            // 每次循环页码加1
            page++;
        } while (rows == 100);
    }

    public static String hh(String a) {
        a = "hhhhh";
        return a;

    }

    public static void main(String[] args) {
//        System.out.println(arr[0]);
//        float x=0.0f;
//        String s = "hhhhh";
//        String s1=new String("hhhhh");
//        System.out.println(s.equals(s1));
//        boolean b=3>5;

        String s = "祝你考出好成绩！";
        System.out.println(0%2==0);
       //test.replaceSpaces("ds sdfs afs sdfa dfssf asdf             ",27);
//        for (EnumTest enumTest : EnumTest.values()) {
//
//            System.out.println(enumTest + ":" + enumTest.getValue());
//        }
//        System.out.println("---------------我是分割线------------");
//        EnumTest test1 = EnumTest.SUN;
//        EnumTest test2=EnumTest.valueOf("SUN");
//        switch (test2) {
//            case MON:
//                System.out.println("今天是星期一");
//                break;
//            case TUE:
//                System.out.println("今天是星期二");
//                break;
//            case WED:
//                System.out.println("今天是星期三");
//                break;
//            case THU:
//                System.out.println("今天是星期四");
//                break;
//            case FRI:
//                System.out.println("今天是星期五");
//                break;
//            case SAT:
//                System.out.println("今天是星期六");
//                break;
//            case SUN:
//                System.out.println("今天是星期日");
//                break;
//            default:
//                System.out.println(test);
//                break;
//        }

}
        // Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            int count = sc.nextInt();
//            int[] scores = new int[count];
//            for (int i = 0; sc.hasNextInt() && i < scores.length; i++) {
//                int num = sc.nextInt();
//                scores[i] = num;
//                if (i + 1 == scores.length) {
//                    break;
//                }
//                //System.out.println(scores[i]);
//            }
//            List<Integer> list = new ArrayList<Integer>();
//            for (int i = 0; i < scores.length; i++) {
//                // System.out.println(scores[i]);
//                if (!list.contains(scores[i])) {
//                    //System.out.println(scores[i]);
//                    list.add(scores[i]);
//                }
//            }
//            Collections.sort(list);
//            for (int a : list) {
//                System.out.println(a);
//            }
//
//        }


    public String replaceSpaces(String S, int length) {
        S=S.substring(0,length);
        S.replace(" ","%20");
        //先把字符串转化为字符数组
        char[] chars = S.toCharArray();
        System.out.println(chars.length);
        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            //如果遇到空格就把他转化为"%20"
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
                //System.out.println(index);
            } else {
                chars[index--] = chars[i];
            }
        }
        System.out.println(index + 1+"-----------"+(chars.length - index - 1));
        String s2 = new String(chars, index + 1, chars.length - index - 1);

        return s2;


    }

    public static  int commonBinarySearch(int[] arr,int key,int low,int high){

        if(arr[low]>key||arr[high]<key||low>high){
            return -1;
        }
       int middle = (low+high)/2;
       if(key>arr[middle]){
           low=middle+1;
           return commonBinarySearch(arr,key,low,high);
       }else if(key<arr[middle]){
           high=middle-1;
           return  commonBinarySearch(arr,key,low,high);
       }else {
           return middle;
       }


    }

    public static int[] bubbling(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
               if(arr[j]>arr[j+1]){
                  int temp =arr[j];
                  arr[j]=arr[j+1];
                  arr[j+1]=temp;
               }
            }
        }
        return arr;
    }

}