package memlang.interpretor;

import memlang.syntax.node.Node;

import java.util.TimerTask;

/**
 * Created by Philippe on 2017-04-13.
 */
public class ExecuteThread extends TimerTask{
    Node currentNode;
    public ExecuteThread(Node node){
        this.currentNode = node;
    }

    public void run(){

    }
}
