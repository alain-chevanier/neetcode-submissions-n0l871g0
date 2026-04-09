class LRUCache {
    MyList accessList;
    Map<Integer, ListNode> keyToNode;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.accessList = new MyList();
        this.keyToNode = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        if (this.keyToNode.containsKey(key)) {
            ListNode node = this.keyToNode.get(key);
            markAccessed(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (this.keyToNode.containsKey(key)) {
            ListNode node = this.keyToNode.get(key);
            node.val = value;
            markAccessed(node);
            return;
        }

        if (this.size == this.capacity) {
            // evict one node;
            ListNode last = this.accessList.last();
            this.accessList.remove(last);
            this.keyToNode.remove(last.key);
            this.size--;
        }

        ListNode node = new ListNode(key, value);
        keyToNode.put(key, node);
        accessList.addFirst(node);
        this.size++;
    }

    private void markAccessed(ListNode node) {
        this.accessList.remove(node);
        this.accessList.addFirst(node);
    }
}

class MyList {
    ListNode head;
    ListNode tail;

    MyList() {
        this.head = new ListNode(0, 0);
        this.tail = new ListNode(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    void remove(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;

    }

    void addFirst(ListNode node) {
        ListNode headNext = this.head.next;
        this.head.next = node;
        node.next = headNext;
        headNext.prev = node;
        node.prev = this.head;
    }

    ListNode first() {
        return this.head.next;
    }

    ListNode last() {
        return this.tail.prev;
    }
}

class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
