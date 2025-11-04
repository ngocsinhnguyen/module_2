package s12_java_collection_framework.vd_tree;

// Lớp mô tả một nút trong cây
class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
        element = e;
    }
}

// Lớp BinarySearchTree (BST)
class BinarySearchTree<E extends Comparable<E>> {
    protected TreeNode<E> root;

    // Hàm thêm node vào BST
    public void insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
            return;
        }

        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            parent = current;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return; // phần tử đã tồn tại, không thêm
            }
        }

        if (e.compareTo(parent.element) < 0) {
            parent.left = new TreeNode<>(e);
        } else {
            parent.right = new TreeNode<>(e);
        }
    }

    // ✅ Hàm tìm kiếm trên BST
    public boolean search(E element) {
        TreeNode<E> current = root;

        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true; // tìm thấy
            }
        }
        return false; // không tìm thấy
    }
}

// Hàm main để chạy thử
class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Thêm phần tử
        bst.insert(60);
        bst.insert(55);
        bst.insert(100);

        // Tìm kiếm
        System.out.println("Tìm 55: " + bst.search(55)); // true
        System.out.println("Tìm 75: " + bst.search(100)); // false
    }
}
