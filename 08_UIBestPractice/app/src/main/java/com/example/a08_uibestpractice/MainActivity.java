package com.example.a08_uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMessages();

        initSubviews();
    }

    private void initSubviews() {
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecycleView = findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecycleView.smoothScrollToPosition(msgList.size() - 1);

                    inputText.setText("");
                }
            }
        });
    }

    private void initMessages() {
        Msg msg1 = new Msg("Hello", Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2 = new Msg("Hello swimming ", Msg.TYPE_SENT);
        msgList.add(msg2);

        Msg msg3 = new Msg("Hello", Msg.TYPE_RECEIVED);
        msgList.add(msg3);

        Msg msg4 = new Msg("Ok, that`s right", Msg.TYPE_RECEIVED);
        msgList.add(msg4);

        Msg msg5 = new Msg("Today is 5th, play basketball together!", Msg.TYPE_SENT);
        msgList.add(msg5);

        Msg msg6 = new Msg("Hello", Msg.TYPE_RECEIVED);
        msgList.add(msg6);

        Msg msg7 = new Msg("This is Tom", Msg.TYPE_RECEIVED);
        msgList.add(msg7);

        Msg msg8 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg8);
    }
}
