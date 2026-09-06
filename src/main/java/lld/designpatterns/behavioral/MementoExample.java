package lld.designpatterns.behavioral;

/**
 * Saves editor text as a private snapshot so undo can restore it later.
 */
public class MementoExample {
    static class Editor {
        private String text = "";

        void write(String text) {
            this.text = text;
        }

        Memento save() {
            return new Memento(text);
        }

        void restore(Memento memento) {
            text = memento.text;
        }

        String text() {
            return text;
        }

        // Memento is immutable; only the editor knows how to use its saved state.
        private static class Memento {
            private final String text;

            Memento(String text) {
                this.text = text;
            }
        }
    }

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.write("first draft");
        Editor.Memento saved = editor.save();
        editor.write("changed draft");
        editor.restore(saved);
        System.out.println(editor.text());
    }
}
