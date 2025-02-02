package stand;

public class IllegalStandArgumentException extends IllegalArgumentException {

    public IllegalStandArgumentException(String standEnv) {
        super (String.format("Wrong \"STAND\" environment argument. Actual: \"%s\"", standEnv));
    }

}
