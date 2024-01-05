package Task2;

import lombok.Data;

/**
 * @author LW
 * @date 2024/1/3 22:14
 */
@Data
public class Declaration {

    private String name = "";
    private String type = "";
    private int value = 0;

    private int startIndex = -1;

    public Declaration(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;

    }

    @Override
    public String toString()
    {
        return "Declaration = {" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';

    }
}
