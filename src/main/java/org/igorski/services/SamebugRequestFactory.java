package org.igorski.services;

import org.igorski.model.Cause;
import org.igorski.model.Frame;
import org.igorski.model.Method;
import org.igorski.model.SamebugRequest;

import java.util.ArrayList;
import java.util.List;

class SamebugRequestFactory implements ExceptionRequestFactory {

    @Override
    public SamebugRequest createInstance(Throwable e) {
        SamebugRequest samebugRequest = new SamebugRequest();
        samebugRequest.setMessage(e.getMessage());
        samebugRequest.setTypeName(e.getClass().getTypeName());
        samebugRequest.setFrames(getFrames(e));

        Throwable realCause = e.getCause();
        if (realCause != null) {
            Cause cause = new Cause();
            cause.setMessage(e.getMessage());
            cause.setTypeName(e.getClass().getTypeName());
            cause.setFrames(getFrames(realCause));
            samebugRequest.setCause(cause);
        }

        return samebugRequest;
    }

    private List<Frame> getFrames(Throwable e) {
        List<Frame> frames = new ArrayList<>();
        StackTraceElement[] stackTraceElements = e.getStackTrace();

        for (StackTraceElement element : stackTraceElements) {
            Frame frame = new Frame();

            String fullClassName = element.getClassName();
            String[] parts = fullClassName.split("\\.");

            var className = parts[parts.length - 1];
            var packageName = fullClassName.substring(0, fullClassName.indexOf(className) - 1);

            var method = new Method();
            method.setPackageName(packageName);
            method.setClassName(className);
            method.setMethodName(element.getMethodName());

            frame.setMethod(method);

            frame.setLocation(element.getFileName() + ":" + element.getLineNumber());
            frames.add(frame);
        }

        return frames;
    }

}
