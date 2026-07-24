package lld.singleton.serialization.prevention;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Main {
    private Main() { }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableSafeSingleton original = SerializableSafeSingleton.getInstance();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(original);
        }
        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            SerializableSafeSingleton restored = (SerializableSafeSingleton) input.readObject();
            System.out.println("same instance after deserialization: "
                    + (original == restored));
        }
    }
}
