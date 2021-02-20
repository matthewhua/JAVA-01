package io.matthew.entity;

/**
 * @author Matthew
 * @date 2021-02-20 19:41
 */
public class Company {


    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
