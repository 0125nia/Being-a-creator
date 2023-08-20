import com.nia.pojo.linkedlist.MLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {

        MLinkedList<String> list = new MLinkedList<>();
        list.addFirst("1");
        list.remove("1");
        list.addFirst("1");
        Iterator<String> iterator = list.iterator();

        for (String s : list) {
            System.out.println(s);
        }

//        for (String s : list) {
//            System.out.println(s);
//        }




        /*MHashMap<String,MLinkedList<String>> map = new MHashMap<>();
        MLinkedList<String> linkedList = new MLinkedList<>();

        map.put("1",linkedList);
        MLinkedList<String> remove = map.remove("2");
        linkedList.addFirst("111");
        System.out.println(remove);

        for (String s : linkedList) {
            System.out.println(s);
        }

        LinkedList<String> list = new LinkedList<>();*/


//        MHashMap<String, > map = new MHashMap<>();
//
//
//        MLinkedList<String> linkedList1 = map.get("1");
//        String s = linkedList1.get(0);
//        System.out.println(s);
//        linkedList1.addFirst("122");
//
//        MLinkedList<String> linkedList2 = map.get("1");
//        for (String s1 : linkedList2) {
//            System.out.println(s1);
//        }

//        String key = "Set";
//        MHashMap<String,MArrayList<String>> commandsList = new MHashMap<>();
//        MArrayList<String> list = commandsList.get(key);
//        list.add("cmd");
//        LogAppendingStrategy logAppendingStrategy = new LogAppendingStrategy();
//        logAppendingStrategy.appendCmd("set key iii", PersistentDataIdentifier.STRING_DATA);
//        logAppendingStrategy.appendCmd("hset set sss iii", PersistentDataIdentifier.HASHMAP_DATA);
//
//        for (MMap.MEntry<String, MArrayList<String>> entry : LogAppendingStrategy.commandsList) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//
//        logAppendingStrategy.save();
    }
}
