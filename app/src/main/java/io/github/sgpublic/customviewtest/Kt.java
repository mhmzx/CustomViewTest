package io.github.sgpublic.customviewtest;

/**
 * @author Madray Haven
 * @date 2022/11/17 17:01
 */
public class Kt {
    public static <T> void let(T value, Function<T> block) {
        if (value != null) block.invoke(value);
    }

    public interface Function<T> {
        void invoke(T value);
    }
}
