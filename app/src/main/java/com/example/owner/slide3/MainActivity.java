package com.example.owner.slide3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ドラッグ対象Viewとイベント処理クラスを紐付ける
        ImageView dragView = (ImageView) findViewById(R.id.imageView1);
        ImageView dragView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView dragView3 = (ImageView) findViewById(R.id.imageView3);
        DragViewListener listener = new DragViewListener(dragView);
        DragViewListener listener2 = new DragViewListener(dragView2);
        DragViewListener listener3 = new DragViewListener(dragView3);
        dragView.setOnTouchListener(listener);
        dragView2.setOnTouchListener(listener2);
        dragView3.setOnTouchListener(listener3);

        /*RelativeLayout parent = (RelativeLayout) findViewById(R.id.imageView1);
        TextView textView = (TextView) findViewById(R.id.textView);

        //TextView textView = (TextView) findViewById(R.id.textView);
        // テキストビューのテキストを設定します
        textView.setText("テスト");
        // テキストビューのテキストを取得します
        //String text = textView.getText().toString();
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(parent.getLayoutParams());
        params.setMargins(0, 0, 0, 0);
        textView.setLayoutParams(params);*/
    }

    public class DragViewListener implements View.OnTouchListener {
        // ドラッグ対象のView
        private ImageView dragView;
        // ドラッグ中に移動量を取得するための変数
        private int oldx;
        private int oldy;
        

        public DragViewListener(ImageView dragView) {
            this.dragView = dragView;
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // タッチしている位置取得
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    // 今回イベントでのView移動先の位置
                    int left = dragView.getLeft() + (x - oldx);
                    int top = dragView.getTop() + (y - oldy);
                    // Viewを移動する
                    dragView.layout(left, top, left + dragView.getWidth(), top
                            + dragView.getHeight());
                    break;
            }

            // 今回のタッチ位置を保持
            oldx = x;
            oldy = y;
            // イベント処理完了
            return true;
        }
    }
}

