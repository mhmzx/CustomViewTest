package io.github.sgpublic.customviewtest;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class ExampleUnitTest {
    @Test
    public void test() {
        new ClassB();
    }

    public static abstract class ClassA {
        public ClassA() {
            method();
        }

        protected abstract void method();
    }

    public static class ClassB extends ClassA {
        @SuppressWarnings("FieldCanBeLocal")
        private final Integer integer = 114514; // print null
//        private final int integer = 114514; // print 114514

        @Override
        protected void method() {
            System.out.println(integer);
        }
    }
}