package io.github.rest.entity;

public class MapTest {

    private String key;
    private String val;

    public MapTest(final String key, final String val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(final String val) {
        this.val = val;
    }

}
