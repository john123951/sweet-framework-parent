package sweet.framework.utility;

import java.math.BigDecimal;

/**
 * Created by sweet on 5/8/17.
 */
public class DemoDto {
    private String name;
    private Integer age;
    private BigDecimal salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
