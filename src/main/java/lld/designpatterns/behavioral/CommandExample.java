package lld.designpatterns.behavioral;

/**
 * Stores a light action in an object, making a remote button independent of the light.
 */
public class CommandExample {
    interface Command {
        void execute();
    }

    static class Light {
        void switchOn() {
            System.out.println("Light is on");
        }
    }

    static class TurnOnLight implements Command {
        private final Light light;

        TurnOnLight(Light light) {
            this.light = light;
        }

        public void execute() {
            light.switchOn();
        }
    }

    // The invoker only knows it has a command, not what device it controls.
    static class RemoteButton {
        private final Command command;

        RemoteButton(Command command) {
            this.command = command;
        }

        void press() {
            command.execute();
        }
    }

    public static void main(String[] args) {
        new RemoteButton(new TurnOnLight(new Light())).press();
    }
}
