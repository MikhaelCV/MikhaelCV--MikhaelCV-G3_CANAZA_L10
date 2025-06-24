public class Test {
	public static void main(String[] args) {
		BNode<Integer> n = new BNode<>(5);
        
        n.add(10, 5);
        n.add(20, 5);
        System.out.println(n.nodeFull(5));    // false
        System.out.println(n.nodeEmpty(5));   // true
        
        n.add(30, 5);
        System.out.println(n.nodeFull(5));    // false
        System.out.println(n.nodeEmpty(5));   // false
        
        n.add(40, 5);
        System.out.println(n.nodeFull(5));    // true
        System.out.println(n.nodeEmpty(5));   // false
        
        int pos[] = new int[1];
        
        System.out.println("20?" + n.searchNode(20, pos));
        System.out.println("\tpos: " + pos[0]); //true, 1
        System.out.println("4?" + n.searchNode(4, pos));
        System.out.println("\tpos: " + pos[0]); //false, 0
        System.out.println("25?" + n.searchNode(25, pos));
        System.out.println("\tpos: " + pos[0]); //false, 2
        System.out.println("50?" + n.searchNode(50, pos));
        System.out.println("\tpos: " + pos[0]); //false, 4
        
        System.out.println(n);
        
        //////////////////////////////////////////
        BTree<Integer> b = new BTree<>(5);
        
        b.insert(20);
        b.insert(5);
        b.insert(10);
        System.out.println(b);
        
        b.insert(12);
        b.insert(4);
        b.insert(23);
        b.insert(29);
        b.insert(33);
        b.insert(45);
        System.out.println(b);
        
        b.insert(17);
        b.insert(12);
        System.out.println(b);
        
        b.insert(28);
        System.out.println(b);
        
        b.insert(15);
        System.out.println(b);
        
	}
}
