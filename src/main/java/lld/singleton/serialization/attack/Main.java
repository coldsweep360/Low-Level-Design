package lld.singleton.serialization.attack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Main {
    private Main() { }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableAttackSingleton original = SerializableAttackSingleton.getInstance();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(bytes)) {
            output.writeObject(original);
        }
        SerializableAttackSingleton restored;
        try (ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
            restored = (SerializableAttackSingleton) input.readObject();
        }
        System.out.println("same instance after deserialization: " + (original == restored));
    }
}
