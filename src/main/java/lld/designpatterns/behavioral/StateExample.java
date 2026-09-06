package lld.designpatterns.behavioral;

/**
 * A music player behaves differently when it is playing and when it is paused.
 */
public class StateExample {
    interface PlayerState {
        void pressPlay(Player player);
    }

    static class Playing implements PlayerState {
        public void pressPlay(Player player) {
            System.out.println("Paused");
            player.setState(new Paused());
        }
    }

    static class Paused implements PlayerState {
        public void pressPlay(Player player) {
            System.out.println("Playing");
            player.setState(new Playing());
        }
    }

    static class Player {
        private PlayerState state = new Paused();

        void setState(PlayerState state) {
            this.state = state;
        }

        void pressPlay() {
            state.pressPlay(this);
        }
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.pressPlay();
        player.pressPlay();
    }
}
