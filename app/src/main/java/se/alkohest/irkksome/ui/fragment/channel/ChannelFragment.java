package se.alkohest.irkksome.ui.fragment.channel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.alkohest.irkksome.R;
import se.alkohest.irkksome.model.entity.IrcChannel;
import se.alkohest.irkksome.model.entity.IrcMessage;
import se.alkohest.irkksome.model.impl.IrcChatMessageEB;

public class ChannelFragment extends Fragment {
    private static ArrayAdapter<ChannelChatItem> arrayAdapter;
    private static List<ChannelChatItem> messageList;
    private static IrcChannel ircChannel;
    private static ListView messageListView;

    public static ChannelFragment newInstance(IrcChannel ircChannel) {
        ChannelFragment fragment = new ChannelFragment();
        ChannelFragment.ircChannel = ircChannel;
        return fragment;
    }
    public ChannelFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(ircChannel.getName());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        messageList = new ArrayList<>(ircChannel.getMessages().size());
        for (IrcMessage message : ircChannel.getMessages()) {
            if (message instanceof IrcChatMessageEB) {
                messageList.add(new MessageItem((IrcChatMessageEB) message));
            }
        }
        ChannelFragment.arrayAdapter = new ChannelArrayAdapter(activity.getApplicationContext(), messageList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_channel, container, false);
        messageListView = (ListView) inflatedView.findViewById(R.id.listView);
        messageListView.setAdapter(arrayAdapter);
        EditText messageEditText = ((EditText) inflatedView.findViewById(R.id.input_field));
        messageEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent) {
                if (keyCode == EditorInfo.IME_ACTION_DONE) {
                    // send message
                    return true;
                }
                return false;
            }
        });
        scrollToBottom();
        return inflatedView;
    }

    public static void scrollWhenAtBottom() {
        if (!hasBacklog()) {
            scrollToBottom();
        }
    }

    private static boolean hasBacklog() {
        return messageListView.getLastVisiblePosition() < arrayAdapter.getCount()-1;
    }

    public static void scrollToBottom() {
        if (arrayAdapter != null) {
            messageListView.setSelection(arrayAdapter.getCount() - 1);
        }
    }

    public static void receiveMessage(IrcMessage message) {
        if (arrayAdapter != null) {
            arrayAdapter.add(new MessageItem((IrcChatMessageEB) message));
            arrayAdapter.notifyDataSetChanged();
            scrollToBottom();
        }
    }
}
