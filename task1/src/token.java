
/**
 * @author LW
 * @date 2023/12/29 13:52
 */

public class token {
    private tokenEnum tokenEnum;

    private String tokenValue;

    public token(tokenEnum tokenEnum, String tokenValue) {
        this.tokenEnum = tokenEnum;
        this.tokenValue = tokenValue;
    }

    public tokenEnum getTokenEnum(){
        return this.tokenEnum;
    }

    public String getTokenValue(){
        return this.tokenValue;
    }


    @Override
    public String toString(){
        return "("+this.tokenEnum+","+this.tokenValue+")";
    }
}
