package edu.snhu.garrison.windwalker.Model;

/**
 * DTO containing Airlines and information about Airlines.
 */
public final class Airline {
    private final String name;
    private final String code;

    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
