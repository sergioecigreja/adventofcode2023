package pt.sergioigreja.day15;

public class ListNode {
    private String label;
    private int value;
    private ListNode prev;
    private ListNode next;

    public ListNode(String label, int value, ListNode prev, ListNode next) {
        this.label = label;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

}
