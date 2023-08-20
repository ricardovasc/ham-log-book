package br.com.ricardovasc.hamlogbook.test.builders;

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

    public TestCallsignDTOBuilder createObject() {
        this.callsign = TestRandomUtils.randomObject(CallsignDTO.class);
        this.callsign.setId(TestRandomUtils.randomInteger());
        return this;
    }

    public CallsignDTO build() {
        return callsign;
    }
}
