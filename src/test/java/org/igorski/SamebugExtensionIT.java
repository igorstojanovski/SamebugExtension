package org.igorski;

import org.igorski.extension.SamebugExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SamebugExtension.class)
public class SamebugExtensionIT {

    @Test
    public void test() {

        assertThat(3).isEqualTo(3);

    }


}
