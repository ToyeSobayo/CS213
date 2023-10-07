package P2;


public enum Campus {

    NEW_BRUNSWICK(0),
    NEWARK(1),

    CAMDEN(2);

    private final int code;
    // Constructor for campus codes
    private Campus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
