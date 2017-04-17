package memlang.interpretor;

import memlang.syntax.node.Token;

/**
 * Created by Philippe on 2017-04-17.
 */
public class MlException extends RuntimeException {
    private final String message;
    private final Token token;

    public MlException(
            String message,
            Token token) {

        this.message = message;
        this.token = token;
    }

    public MlException(
            String message) {

        this.message = message;
        this.token = null;
    }

    @Override
    public String getMessage() {
        if (this.token != null)
            return this.message + " at line " + this.token.getLine() + " position "
                    + this.token.getPos();
        else
            return this.message;
    }
}
