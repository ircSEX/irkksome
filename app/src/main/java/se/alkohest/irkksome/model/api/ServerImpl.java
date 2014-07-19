package se.alkohest.irkksome.model.api;

import java.util.List;

import se.alkohest.irkksome.irc.IrcProtocol;
import se.alkohest.irkksome.irc.IrcProtocolFactory;
import se.alkohest.irkksome.irc.IrcProtocolListener;
import se.alkohest.irkksome.model.entity.IrcServer;

public class ServerImpl implements Server, IrcProtocolListener {
    private IrcProtocol ircProtocol;
    private IrcServer ircServer;

    public ServerImpl(IrcServer ircServer) {
        this.ircServer = ircServer;
        ircProtocol = IrcProtocolFactory.getIrcProtocol(ircServer.getUrl(), 6667);
        ircProtocol.connect("fest", "banned", "banned");
    }

    @Override
    public void joinChannel(String channelName) {
        ircProtocol.joinChannel(channelName);
    }

    @Override
    public void serverConnected(String server, String nick) {

    }

    @Override
    public void nickChanged(String oldNick, String newNick) {

    }

    @Override
    public void usersInChannel(String channelName, List<String> users) {

    }

    @Override
    public void userJoined(String channelName, String nick) {

    }

    @Override
    public void userParted(String channelName, String nick) {

    }

    @Override
    public void userQuit(String nick, String quitMessage) {

    }

    @Override
    public void channelMessageReceived(String channel, String user, String message) {

    }

    @Override
    public void whoisChannels(String nick, List<String> channels) {

    }

    @Override
    public void whoisRealname(String nick, String realname) {

    }

    @Override
    public void whoisIdleTime(String nick, int seconds) {

    }

    @Override
    public void channelListResponse(String name, String topic, String users) {

    }

    @Override
    public void serverDisconnected() {

    }

    @Override
    public void ircError(String errorCode, String message) {

    }
}
