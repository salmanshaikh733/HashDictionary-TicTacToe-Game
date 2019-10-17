//mshaikh52 student number 250959996

public class Node {

    //decalre instance variables
    private Node next;
    private Configuration dataItem;

    //default node constructor
    public Node(Configuration data){
        this.next=null;
        this.dataItem = data;
    }

    //another constructor
    public Node(Configuration data, Node nextItem){
        next=nextItem;
        dataItem=data;
    }

    //constructor for empty node with no data
    public Node()
    {
        next=null;
        dataItem=null;
    }

    //the following are getters and setters
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Configuration getDataItem() {
        return dataItem;
    }

    public void setDataItem(Configuration dataItem) {
        this.dataItem = dataItem;
    }

}
