package co.igorski.services;

import co.igorski.model.SamebugRequest;

public interface ExceptionRequestFactory {

    SamebugRequest createInstance(Throwable e);
}
