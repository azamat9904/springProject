package kz.one.lab.project.RandomGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@Getter
@Setter
public class RandomGeneratorImpl implements RandomGenerator {

    @Override
    public List<Object> generateValue(int size, String valueType)  {
        List<Object> values = new LinkedList<>();

        for(int i =0; i<size;i++){
            Random rnd = new Random();
            rnd.setSeed(124292*i+System.currentTimeMillis());
           if(valueType.equals("NAME"))
              values.add((char)rnd.nextInt(48));
           else if(valueType.equals("AGE"))
               values.add(rnd.nextInt(256));
           else {
               log.error("No such value type in generateValue method");
               throw new RuntimeException();
           }

        }
        return values;
    }
}

