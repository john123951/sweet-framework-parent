package sweet.framework.utility;


import junit.framework.TestCase;
import sweet.framework.utility.serialization.FormSerializer;

import java.math.BigDecimal;

public class FormSerializerTest
        extends TestCase {

    public void SerializeTest() {
        DemoDto demoDto = new DemoDto();
        demoDto.setAge(14);
        demoDto.setName("test");
        demoDto.setSalary(BigDecimal.valueOf(123.9));

        String form = FormSerializer.Serialize(demoDto);
        System.out.println(form);
    }
}
