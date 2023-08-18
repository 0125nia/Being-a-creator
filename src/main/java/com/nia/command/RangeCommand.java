package com.nia.command;

import com.nia.pojo.Data;
import com.nia.pojo.ResponseMsg;
import com.nia.pojo.hashmap.MHashMap;
import com.nia.pojo.linkedlist.MLinkedList;

/**
 * range [key] [start] [end]
 */
public class RangeCommand implements AbstractLinkedListCommand {
    private static final int RANGE_CMD_NUM = 4;//链表len的指令数

    @Override
    public String execute(String[] cmd, String cmdStr, Data data) {
        if (!isCorrectCmd(cmd, RANGE_CMD_NUM)) {//判断指令数
            return new ErrorCommand().execute(cmd, cmdStr, data);
        }
        String key = cmd[1];
        String start = cmd[2];
        String end = cmd[3];
        MHashMap<String, MLinkedList<String>> linkedListData = data.getLinkedListData();
        MLinkedList<String> linkedList = getLinkedList(linkedListData, key);//获取key对应的链表
        return getRange(linkedList, Integer.parseInt(start), Integer.parseInt(end));//返回结果
    }

    private String getRange(MLinkedList<String> linkedList, int start, int end){
        int size = linkedList.size();//链表的实际长度
        if (linkedList.size() < start || end < start){
            return ResponseMsg.INDEX_ERROR;
        }
        //使用StringBuilder拼接遍历结果
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i < size){
                String s = linkedList.get(i);
                sb.append(s).append(" ");
            }else {//若超过链表长度直接退出遍历
                break;
            }
        }
        return sb.toString();
    }

}
