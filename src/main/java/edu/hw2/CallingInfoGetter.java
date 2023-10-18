package edu.hw2;

public final class CallingInfoGetter {
    private CallingInfoGetter() {
    }

    public static CallingInfo callingInfo() {
        Exception e = new Exception();
        StackTraceElement[] stackTrace = e.getStackTrace();
        return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
    }

    public record CallingInfo(String className, String methodName) { }
}
