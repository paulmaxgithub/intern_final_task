package config.AWS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CookieModel {

    private String name;
    private String value;

    /// GETTERS
    public String getName() { return name; }
    public String getValue() { return value; }

    @JsonIgnore public String getDomain() { return ".amazon.com"; }
    @JsonIgnore public String getPath() { return "/"; }

    /// SETTERS
    public void setName(String name) { this.name = name; }
    public void setValue(String value) { this.value = value; }
}