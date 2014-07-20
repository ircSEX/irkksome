package se.alkohest.irkksome.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import se.alkohest.irkksome.R;
import se.alkohest.irkksome.irc.Log;
import se.alkohest.irkksome.model.api.Server;
import se.alkohest.irkksome.model.api.ServerManager;

public class ChatActivity extends Activity implements ServerConnectFragment.OnFragmentInteractionListener {
    private static final Log LOG = Log.getInstance(ChatActivity.class);
    private ServerManager serverManager;
    private Server activeServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawers);
        serverManager = new ServerManager();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ServerConnectFragment connectFragment = ServerConnectFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, connectFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_join_channel:
                showJoinChannel();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showJoinChannel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText popup = new EditText(this);
        builder.setTitle("Join channel on " + activeServer.getBackingBean().getUrl());
        builder.setView(popup);
        builder.setPositiveButton(R.string.join_channel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activeServer.joinChannel(popup.getText().toString());
            }
        });
        builder.create().show();
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.input_field);
        activeServer.sendMessage(activeServer.getActiveChannel(), editText.getText().toString());
        editText.getText().clear();
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {
        String hostName = bundle.getString(ServerConnectFragment.ARG_HOSTNAME);
        String nickname = bundle.getString(ServerConnectFragment.ARG_NICKNAME);
        LOG.i("Host: " + hostName + " Nickname: " + nickname);
        activeServer = serverManager.addServer(hostName);
        activeServer.setListener(new CallbackHandler(this));
    }
}
