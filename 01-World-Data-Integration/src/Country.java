// Country abstract class implemented by subclass (CountryData)

abstract class Country {
    // private instance fields
    private String code;
    private String name;

    // default constructor
    public Country() {}

    // getters
    public String getCode() { return code; }
    public String getName() { return name; }

    // setters
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }

    // abstract method also implemented by subclass
    public abstract String toString();
}
