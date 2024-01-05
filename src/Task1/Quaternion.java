package Task2;

import lombok.Data;

/**
 * @author LW
 * @date 2024/1/3 22:04
 */
@Data
public class Quaternion {
    private int id;

    private String op;

    private String arg1;

    private String arg2;

    private String result;

    public Quaternion(int id, String op, String arg1, String arg2, String result) {
        this.id = id;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    public Quaternion() {
    }
}
