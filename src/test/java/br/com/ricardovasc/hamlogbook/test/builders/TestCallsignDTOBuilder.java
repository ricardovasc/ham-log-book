package br.com.ricardovasc.hamlogbook.test.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.test.utils.TestRandomUtils;

public class TestCallsignDTOBuilder {
    
    private CallsignDTO callsign;

    private TestCallsignDTOBuilder() {
        this.callsign = new CallsignDTO();
    }

    public static TestCallsignDTOBuilder builder() {
        return new TestCallsignDTOBuilder();
    }

    public static ListBuilder listBuilder() {
        return new ListBuilder();
    }

    public TestCallsignDTOBuilder createObject() {
        this.callsign = TestRandomUtils.randomObject(CallsignDTO.class);
        this.callsign.setId(TestRandomUtils.randomInteger());
        return this;
    }

    public CallsignDTO build() {
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

        private CallsignDTO build() {
            TestCallsignDTOBuilder builder = new TestCallsignDTOBuilder();

            if (shouldCreateObjects) {
                builder.createObject();
            }

            return builder.build();
        }

        public List<CallsignDTO> buildList() {
            return Stream.generate(this::build)
                    .limit(size > 0 ? size : TestRandomUtils.randomInteger(10))
                    .collect(Collectors.toList());
        }
    }
}
