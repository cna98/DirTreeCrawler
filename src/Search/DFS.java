package Search;

import DTO.DFSTraversalDto;
import Node.Node;

import java.io.File;
import java.util.ArrayList;

public class DFS {

    private final Node rootParent;
    private final Node root;

    public DFS(String src) {
        File file = new File(src);
        this.root = new Node(file);
        this.rootParent = new Node(file.getParentFile());
    }

    public String Traverse(DFSTraversalDto filters) {
        Node currentRoot = root;
        Node lastVisited = null;
        StringBuilder results = new StringBuilder();
        do {
            Node temp = JumpIn(lastVisited, currentRoot);
            if (temp != null) {
                currentRoot = temp;
            } else {
                lastVisited = currentRoot;
                CheckSubFiles(filters, currentRoot, results);
                currentRoot = new Node(lastVisited.getFile().getParentFile());
            }
        }
        while (rootParent.getFile() == null ? currentRoot.getFile() != null : !currentRoot.equals(rootParent));
        return results.toString();
    }

    private void CheckSubFiles(DFSTraversalDto filters, Node currentRoot, StringBuilder previousResults) {
        ArrayList<Node> subFiles = Node.GetNodes(currentRoot.getFile().listFiles(File::isFile));
        if (!subFiles.isEmpty()) {
            for (Node n : subFiles) {
                if (CheckFile(n, filters)) {
                    previousResults.append(n.getFile().getPath())
                            .append("\t")
                            .append(DFSTraversalDto.GetKB(n.getFile().length()))
                            .append(" KB")
                            .append("\n");
                }
            }
        }
    }

    private Node JumpIn(Node lastVisited, Node currentRoot) {
        ArrayList<Node> subDirs = Node.GetNodes(currentRoot.getFile().listFiles(File::isDirectory));
        if (!subDirs.isEmpty()) {
            if (lastVisited == null) {
                return subDirs.get(0);
            }
            try {
                return subDirs.get(subDirs.indexOf(lastVisited) + 1);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return null;
    }

    public boolean CheckFile(Node n, DFSTraversalDto filters) {
        if (!CheckMin(n, filters.getMin())) {
            return false;
        }
        if (!CheckMax(n, filters.getMax())) {
            return false;
        }
        if (!CheckName(n, filters.getName())) {
            return false;
        }
        return CheckFormat(n, filters.getFormat());
    }

    private boolean CheckName(Node n, String name) {
        if (name != null) {
            if (!name.isEmpty()) {
                return n.getFile().getName().contains(name);
            }
        }
        return true;
    }

    private boolean CheckFormat(Node n, String format) {
        if (format != null) {
            if (!format.isEmpty()) {
                return n.getFile().getPath().endsWith(format);
            }
        }
        return true;
    }

    private boolean CheckMin(Node n, long min) {
        if (min != 0) {
            return n.getFile().length() >= min;
        }
        return true;
    }

    private boolean CheckMax(Node n, long max) {
        if (max != 0) {
            return n.getFile().length() <= max;
        }
        return true;
    }

}
