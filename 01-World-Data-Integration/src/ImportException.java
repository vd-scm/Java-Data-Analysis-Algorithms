// exception class extends IOException; used when there's errors on importing, tracks failed imports

import java.io.IOException;

public class ImportException extends IOException {
    private int failCount;

    public ImportException(int failCount) {
        super(failCount + " non-matching or failed imports");
        this.failCount = failCount;
    }
}
