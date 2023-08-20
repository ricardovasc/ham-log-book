package br.com.ricardovasc.hamlogbook.test.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestRandomUtils {

    private final EasyRandom EASY_RANDOM = new EasyRandom(
        new EasyRandomParameters()
                .charset(StandardCharsets.UTF_8)
                .stringLengthRange(1, 50)
                .objectPoolSize(50)
    );

    public Long randomLong() {
        return EASY_RANDOM.nextLong(1L, 1000L);
    }

    public Integer randomInteger() {
        return EASY_RANDOM.nextInt(1, 1000);
    }

    public <T> T randomObject(Class<T> classType) {
        return EASY_RANDOM.nextObject(classType);
    }

    public <T> List<T> randomList(Class<T> classType, int size) {
        return EASY_RANDOM.objects(classType, size).collect(Collectors.toList());
    }

    public<T> List<T> randomList(Class<T> classType) {
        return randomList(classType, EASY_RANDOM.nextInt(1, 10));
    }
}
