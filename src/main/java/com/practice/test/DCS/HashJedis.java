//package com.practice.test.DCS;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 封装了hash取余算法的类，使用JedisPool来连接
// */
//public class HashJedis {
//    /**
//     * set,get,hset,hget等，所有jedis底层操作，都可以重写
//     */
//    private int N;
//    private List<JedisPool> poolList=new ArrayList<>();
//
//    public HashJedis(){
//
//    }
//
//    public HashJedis(List<String> nodes){
//        N=nodes.size();
//        for (String node : nodes) {
//            System.out.println(node);
//            String host=node.split(":")[0];
//            int port=Integer.parseInt(node.split(":")[1]);
//            //构造一个连接池对象
//            JedisPool pool=new JedisPool(host,port);
//            poolList.add(pool);
//        }
//    }
//
//    //set、get方法的案例，完成分片
//    public void set(String key,String value){
//        //获取hash取余计算后的节点
//        JedisPool jedisPool= hashKeyToNode(key);
//        Jedis jedis=jedisPool.getResource();
//        //对获取的节点进行get ，set操作等。
//        try{
//            jedis.set(key,value);
//            System.out.println("分片set成功");
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("分片set失败");
//        }finally {
//            /*if(jedis!=null){
//                jedis.close();
//            }else{
//                jedis=null;
//            }*/
//            //将jedis归还到jedispool
//            jedisPool.returnResource(jedis);
//        }
//    }
//
//    public Object get(String key){
//        //获取hash取余计算后的节点
//        JedisPool jedisPool = hashKeyToNode(key);
//        Jedis jedis=jedisPool.getResource();
//        //对获取的节点进行get ，set操作等。
//        try{
//            String s = jedis.get(key);
//            System.out.println("分片get成功");
//            return s;
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("分片get失败");
//            return "";
//        }finally {
//            /*if(jedis!=null){
//                jedis.close();
//            }else{
//                jedis=null;
//            }*/
//            jedisPool.returnResource(jedis);
//        }
//    }
//
//    //自定义获取节点的方法
//    public JedisPool hashKeyToNode(String key){
//        int result=(key.hashCode()&Integer.MAX_VALUE)%N;
//        //从上面保存的集合中取出节点
//        JedisPool jedisPool = poolList.get(result);
//        //Jedis jedis = jedisPool.getResource();
//        //return jedis;
//        return jedisPool;
//    }
//
//    //jedis本身也有上述封装的方法,叫做SharedJedis，底层使用的是hash一致性
//}
