package Node;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Node {

    private File File;

    public Node(File File) {
        this.File = File;
    }

    public static ArrayList<Node> GetNodes(File[] inputs) {
        ArrayList<Node> results = new ArrayList<>();
        if(inputs != null)
        {
            for (File f : inputs) {
                results.add(new Node(f));
            }
        }
       return results;
    }

    public File getFile() {
        return File;
    }

    public void setFile(File File) {
        this.File = File;
    }

    @Override
    public boolean equals(Object o) {
       if((o instanceof Node)) {
           return File.equals(((Node)o).getFile());
       }
       return false;
    }


}
