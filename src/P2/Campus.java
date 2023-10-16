package P2;

/**
 * Represents different campus options with their corresponding codes.
 * Provides methods to retrieve the campus code associated with each campus.
 * Enum values include New Brunswick, Newark, and Camden.
 *
 * @author [Toye Sobayo]
 */
public enum Campus {

    NEW_BRUNSWICK(0),
    NEWARK(1),

    CAMDEN(2);

    private final int code;

    /**
     * Constructs a campus with the specified code.
     *
     * @param code the code associated with the campus
     */
    private Campus(int code) {
        this.code = code;
    }

    /**
     * Retrieves the code associated with the campus.
     *
     * @return the campus code
     */
    public int getCode() {
        return this.code;
    }

}
