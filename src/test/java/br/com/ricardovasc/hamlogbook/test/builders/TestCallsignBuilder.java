package br.com.ricardovasc.hamlogbook.test.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.test.utils.TestRandomUtils;

public class TestCallsignBuilder {
    
    private Callsign callsign;

    private TestCallsignBuilder() {
        this.callsign = new Callsign();
    }

    public static TestCallsignBuilder builder() {
        return new TestCallsignBuilder();
    }

    public static ListBuilder listBuilder() {
        return new ListBuilder();
    }

    public TestCallsignBuilder createObject() {
        this.callsign = TestRandomUtils.randomObject(Callsign.class);
        this.callsign.setId(TestRandomUtils.randomInteger());
        return this;
    }

    public Callsign build() {
        return callsign;
    }

    public static class ListBuilder {
        private int size;
        private boolean shouldCreateObjects;

        private ListBuilder() {
        }

        public ListBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        public ListBuilder createObjects() {
            this.shouldCreateObjects = true;
            return this;
        }

        private Callsign build() {
            TestCallsignBuilder builder = new TestCallsignBuilder();

            if (shouldCreateObjects) {
                builder.createObject();
            }

            return builder.build();
        }

        public List<Callsign> buildList() {
            return Stream.generate(this::build)
                    .limit(size > 0 ? size : TestRandomUtils.randomInteger(10))
                    .collect(Collectors.toList());
        }
    }
}
