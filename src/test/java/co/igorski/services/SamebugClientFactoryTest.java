package co.igorski.services;

import co.igorski.clients.SamebugClient;
import co.igorski.configuration.PropertiesStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Igor Stojanovski.
 * Date: 7/20/2018
 * Time: 10:19 PM
 */
@ExtendWith(MockitoExtension.class)
class SamebugClientFactoryTest {

    @Mock
    private PropertiesStore propertiesStore;
    private SamebugClientFactory clientFactory;

    @BeforeEach
    public void beforeEach() {
        when(propertiesStore.getEndpoint()).thenReturn("http://localhost");
        clientFactory = new SamebugClientFactory(propertiesStore);
    }

    @Test
    public void shouldCreateClient() {
        SamebugClient instance = clientFactory.getInstance();
        assertThat(instance).isNotNull();
        assertThat(instance).isInstanceOf(SamebugClient.class);
    }

    @Test
    public void shouldReturnSameClient() {
        Assertions.assertThat(clientFactory.getInstance()).isEqualTo(clientFactory.getInstance());
    }

}