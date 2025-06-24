

public class Test2 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(4);

        int[] valores = {
            3, 10, 13, 16, 12, 19, 22, 25, 28, 33, 38, 40, 49, 52, 55, 60, 62, 67, 70, 72
        };
        for (int v : valores) {
            tree.insert(v);
        }

        System.out.println(tree.toString());
    }
}
