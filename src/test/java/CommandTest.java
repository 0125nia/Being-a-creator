import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

public class CommandTest {
    public static void main(String[] args) throws Exception {

        MLinkedList<String> linkedList = new MLinkedList<>();
        MHashMap<String, MLinkedList<String>> map = new MHashMap<>();
        MLinkedList<String> linkedList1 = map.get("1");
        System.out.println(linkedList1);

//        Data data = new Data();
//        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
//        linkedListData.put("1",new MLinkedList<>());
//        linkedListData.get("1").addFirst("111");
//        Command command = new LPushCommand();
//        command.execute(new String[4],"",data);

//        String aof = ConfigLoader.getString("AOF");
//
//        FileReader fr = new FileReader(aof);
//
//        BufferedReader reader = new BufferedReader(fr);
//
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }


//        System.out.println(sb.toString());
//        Class<SetCommand> clazz = SetCommand.class;
//
//        String name = clazz.getName();
//        System.out.println(name);
//
//
//
//        MHashMap<String, Command> map = new MHashMap<>();
//
//        CommandFactory factory = new CommandFactory();
//
//        Invoker invoker = new Invoker();
//        invoker.executeCommand("SET iii lll");


    }


}
