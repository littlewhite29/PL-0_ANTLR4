package Task1;

import lombok.Data;

/**
 * @author LW
 * @date 2023/12/29 13:52
 */
@Data
public class token {
    private tokenEnum tokenEnum;

    private String tokenValue;

    public token(Task1.tokenEnum tokenEnum, String tokenValue) {
        this.tokenEnum = tokenEnum;
        this.tokenValue = tokenValue;
    }

    @Override
    public String toString(){
        return "("+this.tokenEnum+","+this.tokenValue+")";
    }
}
