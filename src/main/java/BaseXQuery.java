import java.io.Closeable;
import java.io.IOException;

/**
 * Inner class for iterative query execution.
 */
public abstract class BaseXQuery implements Closeable {

    public boolean more() throws IOException {
        return true;
    }
    public String next() throws IOException {
        return "";
    }


    }
