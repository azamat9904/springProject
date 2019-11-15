package kz.one.lab.project.RandomGenerator;

import java.util.List;

public interface RandomGenerator {
    List<Object> generateValue(int size, String valueType);
}
