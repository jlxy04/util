package com.yifeng.util.collection;

import java.util.*;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-18 16:50
 */
public class CollectionUtils {

    private static Integer INTEGER_ONE = new Integer(1);

    private static final String EMPTY = "";

    private CollectionUtils() {}

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }

    public static Collection union(final Collection a, final Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            for(int i=0,m=Math.max(getFreq(obj,mapa),getFreq(obj,mapb));i<m;i++) {
                list.add(obj);
            }
        }
        return list;
    }

    public static Collection intersection(final Collection a, final Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            for(int i=0,m=Math.min(getFreq(obj,mapa),getFreq(obj,mapb));i<m;i++) {
                list.add(obj);
            }
        }
        return list;
    }

    public static Collection disjunction(final Collection a, final Collection b) {
        ArrayList list = new ArrayList();
        Map mapa = getCardinalityMap(a);
        Map mapb = getCardinalityMap(b);
        Set elts = new HashSet(a);
        elts.addAll(b);
        Iterator it = elts.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            for(int i=0,m=((Math.max(getFreq(obj,mapa),getFreq(obj,mapb)))-(Math.min(getFreq(obj,mapa),getFreq(obj,mapb))));i<m;i++) {
                list.add(obj);
            }
        }
        return list;
    }

    public static Collection subtract(final Collection a, final Collection b) {
        ArrayList list = new ArrayList( a );
        for (Iterator it = b.iterator(); it.hasNext();) {
            list.remove(it.next());
        }
        return list;
    }

    public static void addAll(Collection collection, Object[] elements) {
        for (int i = 0, size = elements.length; i < size; i++) {
            collection.add(elements[i]);
        }
    }

    public static void addAll(Collection collection, Iterator iterator) {
        while (iterator.hasNext()) {
            collection.add(iterator.next());
        }
    }

    public static Map getCardinalityMap(final Collection coll) {
        Map count = new HashMap();
        for (Iterator it = coll.iterator(); it.hasNext();) {
            Object obj = it.next();
            Integer c = (Integer) (count.get(obj));
            if (c == null) {
                count.put(obj, INTEGER_ONE);
            } else {
                count.put(obj,new Integer(c.intValue() + 1));
            }
        }
        return count;
    }

    private static final int getFreq(final Object obj, final Map freqMap) {
        Integer count = (Integer) freqMap.get(obj);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }

    public static <T> String join(final Collection<T> coll) {
        return join(coll, null);
    }

    public static <T> String join(final Collection<T> coll, String separate) {
        if(coll == null) {
            return null;
        }

        if(coll.isEmpty()) {
            return EMPTY;
        }

        if(separate == null) {
            separate = EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (T t : coll) {
            sb.append(t);
            if((i+1) < coll.size()) {
                sb.append(separate);
            }
            i++;
        }

        return sb.toString();
    }

    public static <T> void filter(Collection<T> collection, Filter<T> predicate) {
        if(isNotEmpty(collection)) {
            for (Iterator<T> iterator = collection.iterator(); iterator.hasNext(); ) {
                T t =  iterator.next();
                if(!predicate.evaluate(t)) {
                    iterator.remove();
                }
            }
        }
    }

    public static <T> List<T> newList(T t) {
        List<T> list = new ArrayList<>();

        if(t == null) {
            return list;
        }

        list.add(t);
        return list;
    }

    public static void main(String[] args) {
        List<String> ss = newList("xxx");
        System.out.println(ss);
    }
}
