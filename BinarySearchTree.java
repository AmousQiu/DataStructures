//Binary Search Tree class
//uses the Binary Tree class
public class BinarySearchTree<T extends Comparable<T>>
//you are using the compareTo method on objects of type T; hence T should extend Comparable<T>
{
    private BinaryTree<T> tree;
    private int size;

    public BinarySearchTree() {
        tree = new BinaryTree<T>();
        size = 0;
    }

    public BinaryTree<T> getTree() {
        return tree;
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return size;
    }

    public BinaryTree<T> search(T key) {
        BinaryTree<T> t = tree;
        if (isEmpty()) return null;
        while (t != null) {
            if (key.compareTo(t.getData()) < 0)
                t = t.getLeft();
            else if (key.compareTo(t.getData()) > 0)
                t = t.getRight();
            else // key is found
                return t;
        }
        return null;
    }


    public void insert(T item) {
        BinaryTree<T> newNode = new BinaryTree<T>(); //sets left, right, parent and data to null
        newNode.setData(item);

        if (size == 0) {
            tree = newNode;
            size++;
            return;
        }

        BinaryTree<T> t = tree;
        boolean done = false;
        while (!done) {
            int c = item.compareTo(t.getData());
            if (c == 0) {
                System.out.println("Duplicate key. Can't insert");
                return;
            } else if (c < 0) //need to go left
            {
                if (t.getLeft() == null)    //place to insert found
                {
                    t.setLeft(newNode);
                    newNode.setParent(t);
                    size++;
                    done = true;
                } else
                    t = t.getLeft(); //otherwise go left one branch
            } else //c>0; need to go right
            {
                if (t.getRight() == null) //place to insert found
                {
                    t.setRight(newNode);
                    newNode.setParent(t);
                    size++;
                    done = true;
                } else
                    t = t.getRight();
            }
        }
    }

    public BinaryTree<T> findPredecessor(BinaryTree<T> node) {
        if (node == null) return null;
        if (node.getLeft() == null) return null;
        BinaryTree<T> pred = node.getLeft();
        while (pred.getRight() != null)
            pred = pred.getRight();
        return pred;
    }

    public void deleteHere(BinaryTree<T> deleteNode, BinaryTree<T> attach) {
        if (deleteNode == null) return;
        BinaryTree<T> parent = deleteNode.getParent();

        if (parent == null) return;
        if (attach == null) {
            if (parent.getLeft() == deleteNode)
                parent.setLeft(null);
            else
                parent.setRight(null);
            return;
        }
        if (deleteNode == parent.getRight()) {
            parent.detachRight();
            deleteNode.setParent(null);
            //attach.setParent(parent);
            attach.setParent(null);
            parent.attachRight(attach);
            attach.setParent(parent);
        } else {
            parent.detachLeft();
            deleteNode.setParent(null);

            //attach.setParent(parent);
            attach.setParent(null);
            parent.attachLeft(attach);
            attach.setParent(parent);
        }
        deleteNode.clear();
    }

    public void delete(T key) {
        if (size == 0) {
            System.out.println("Can't delete. Empty tree");
            return;
        }
        BinaryTree<T> deleteNode = search(key);
        if (deleteNode == null) {
            System.out.println("Key not found. Can't delete");
            return;
        }

        BinaryTree<T> hold = null;
        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
            deleteHere(deleteNode, null);
        } else if (deleteNode.getLeft() == null) {
            hold = deleteNode.getRight();
            deleteHere(deleteNode, hold);
        } else if (deleteNode.getRight() == null) {
            hold = deleteNode.getLeft();
            deleteHere(deleteNode, hold);
        } else {
            hold = findPredecessor(deleteNode);
            deleteNode.setData(hold.getData());
            deleteNode = hold;
            deleteHere(deleteNode, deleteNode.getLeft());
        }
        size--;
    }

    public T findMax(BinaryTree<T> root) {
        if (root.getRight() == null) {
            return root.getData();
        } else {
            return findMax(root.getRight());
        }
    }

    public T findMin(BinaryTree<T> root) {
        if (root.getLeft() == null) {
            return root.getData();
        } else {
            return findMin(root.getLeft());
        }
    }

    //recursive search method
    public BinaryTree<T> recursiveSearch(BinaryTree<T> t, T key) {
        if (t == null) {
            System.out.println("Fail to find it!");
            return t;
        } else if (t.getData() == key) {
            return t;
        } else if (key.compareTo(t.getData()) < 0) {
            return recursiveSearch(t.getLeft(), key);
        } else {
            return recursiveSearch(t.getRight(), key);
        }
    }

    public static <T> void preorder(BinaryTree<T> t) {
        if (t != null) {
            System.out.print(t.getData() + "\t");
            preorder(t.getLeft());
            preorder(t.getRight());
        }
    }

    public static <T> void Inorder(BinaryTree<T> t) {
        if (t != null) {
            Inorder(t.getLeft());
            System.out.print(t.getData() + "\t");
            Inorder(t.getRight());
        }
    }

    public static <T> void Postorder(BinaryTree<T> t) {
        if (t != null) {
            Postorder(t.getLeft());
            Postorder(t.getRight());
            System.out.print(t.getData() + "\t");
        }
    }

    public int height(BinaryTree<T> t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
        }
    }

    public boolean isHeightBalance(BinaryTree<T> t) {
        if (t.isEmpty()) {
            return true;
        } else {
            return (Math.abs(height(t.getLeft())) - height(t.getRight()) <= 1);
        }
    }
}
