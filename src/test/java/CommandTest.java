import com.nia.dao.conf.ConfigLoader;

import java.io.BufferedReader;
import java.io.FileReader;

public class CommandTest {
    public static void main(String[] args) throws Exception {

        String aof = ConfigLoader.getString("AOF");

        FileReader fr = new FileReader(aof);

        BufferedReader reader = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }


        System.out.println(sb.toString());
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
