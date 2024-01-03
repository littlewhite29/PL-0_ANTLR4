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
    private int level = 0;
    private int address = 0;
    private int startIndex = -1;

    public Declaration(String name, String type, int value, int level, int address) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.level = level;
        this.address = address;
    }

    @Override
    public String toString()
    {
        switch (type) {
            case "CONSTANT":
                return "Declaration = {" +
                        "name='" + name + '\'' +
                        ", type='" + type + '\'' +
                        ", value='" + value + '\'' +
                        '}';
            case "VARIABLE":
            default:
                return "Declaration = {" +
                        "name='" + name + '\'' +
                        ", type='" + type + '\'' +
                        ", value='" + value + '\'' +
                        ", level='" + level + '\'' +
                        ", address='" + address + '\'' +
                        '}';
        }
    }
}
