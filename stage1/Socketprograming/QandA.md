## what's the difference between InputStream class and DataInputStream class?

The java.io.InputStream class is a base class for all byte-based input streams in the Java IO API. It provides methods for reading bytes from a stream.

The java.io.DataInputStream class is a subclass of InputStream that provides methods for reading Java data types (such as int, float, and String) from an underlying input stream in a machine-independent way. The DataInputStream class is often used when reading binary data that was written by a DataOutputStream.

In summary, InputStream is a base class that reads raw bytes, while DataInputStream is a subclass that can read Java data types in a machine-independent way.

### what do `finally` means in `try` `catch ` statement in java?

In Java, the finally block is used in conjunction with the try and catch blocks to specify a block of code that will always be executed, regardless of whether an exception was thrown and caught or not.

The try block is used to enclose a section of code that may throw an exception. The catch block is used to handle the exception that was thrown in the try block. The finally block is used to specify a block of code that will always be executed, whether an exception was thrown and caught or not.

Here is an example of how try, catch, and finally blocks are used in Java:

```java
try {
    // code that may throw an exception
} catch (ExceptionType e) {
    // code to handle the exception
} finally {
    // code that will always be executed
}

```

The finally block is useful for cleaning up resources that were acquired in the try block, such as file handles or database connections. For example, if you open a file in the try block, you can close it in the finally block to ensure that it is closed even if an exception is thrown.

Keep in mind that the finally block will execute even if there's a return statement, break, or continue statement in a try or catch block. But if the System.exit() method is called, the finally block won't execute.
