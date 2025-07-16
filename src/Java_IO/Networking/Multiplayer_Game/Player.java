package Java_IO.Networking.Multiplayer_Game;
import java.io.Serializable;

/*
* Scenario:
You are building a real-time game lobby server that allows multiple players
to join and chat while waiting for the game to begin.
* Each client sends their username and chat messages.
* The server broadcasts all messages to every connected client in real time.
*
*
*
* */


/*
  Represents a player in the game lobby.
  Includes a unique username.
 */
public class Player implements Serializable {
    private String username;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
