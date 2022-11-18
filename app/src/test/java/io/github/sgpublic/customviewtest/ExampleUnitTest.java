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

    @SuppressWarnings("FieldCanBeLocal")
    public static class ClassB extends ClassA {
        private final Integer i1 = 114514;
        private final int i2 = 114514;

        @Override
        protected void method() {
            System.out.println(i1); // print null
            System.out.println(i2); // print 114514
        }
    }
}