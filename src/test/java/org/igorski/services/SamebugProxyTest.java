package org.igorski.services;

import org.igorski.configuration.PropertiesStore;
import org.igorski.model.SamebugRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class SamebugProxyTest {

    @Mock
    private PropertiesStore propertiesStoreMock;
    @Mock
    private ExceptionRequestFactory requestFactoryMock;
    @Mock
    private Throwable throwableMock;

    @Test
    public void shouldUseRequestFactory() {
        when(propertiesStoreMock.getApiKey()).thenReturn("api");
        when(propertiesStoreMock.getEndpoint()).thenReturn("http://localhost:8283");
        when(requestFactoryMock.createInstance(throwableMock)).thenReturn(getRequestStub());

        SamebugProxy proxy = new SamebugProxy(propertiesStoreMock, requestFactoryMock);

        proxy.getSamebugRequest(throwableMock);
        verify(requestFactoryMock).createInstance(throwableMock);
    }

    private SamebugRequest getRequestStub() {
        SamebugRequest samebugRequest = new SamebugRequest();

        samebugRequest.setTypeName("typeName");
        samebugRequest.setMessage("SomeMessage");
        samebugRequest.setFrames(Collections.emptyList());

        return samebugRequest;
    }
}