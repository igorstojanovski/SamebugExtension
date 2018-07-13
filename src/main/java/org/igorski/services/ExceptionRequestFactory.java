package org.igorski.services;

import org.igorski.model.SamebugRequest;

public interface ExceptionRequestFactory {

    SamebugRequest createInstance(Throwable e);
}
