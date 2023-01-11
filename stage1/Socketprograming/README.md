## what's the difference between InputStream class and DataInputStream class?

The java.io.InputStream class is a base class for all byte-based input streams in the Java IO API. It provides methods for reading bytes from a stream.

The java.io.DataInputStream class is a subclass of InputStream that provides methods for reading Java data types (such as int, float, and String) from an underlying input stream in a machine-independent way. The DataInputStream class is often used when reading binary data that was written by a DataOutputStream.

In summary, InputStream is a base class that reads raw bytes, while DataInputStream is a subclass that can read Java data types in a machine-independent way.
