/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode midParent = findMidParent(head);
        ListNode mid = midParent.next;
        midParent.next = null;
        
        ListNode lo = sortList(head);
        ListNode hi = sortList(mid);
        
        return merge(lo, hi);
    }
    
    private ListNode findMidParent(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode fakeHead = new ListNode();        
        ListNode tail = fakeHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            
            tail = tail.next;
        }
        
        tail.next = (list1 != null) ? list1 : list2;
        return fakeHead.next;
    }
}
