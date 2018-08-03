package com.downloadapp;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */

public class NumKeyBoardView extends LinearLayout {

    private EditText mEditText;

    public NumKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.lyt_keyboard, this, true);

        KeyboardView kv = (KeyboardView) findViewById(R.id.kv_lyt_keyboard);
        // 加载上面的qwer.xml键盘布局，new出KeyBoard对象
        Keyboard kb = new Keyboard(context, R.xml.qwer);

        kv.setKeyboard(kb);
        kv.setEnabled(true);
        // 设置是否显示预览，就是某个键时，上面弹出小框显示你按下的键，称为预览框
        kv.setPreviewEnabled(true);
        // 设置监听器
        kv.setOnKeyboardActionListener(mListener);
    }

    // 设置接受字符的EditText
    public void setStrReceiver(EditText et) {
        mEditText = et;
    }

    private KeyboardView.OnKeyboardActionListener mListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
            // 手指离开该键（抬起手指或手指移动到其它键）时回调
        }

        @Override
        public void onPress(int primaryCode) {
            // 当某个键被按下时回调，只有press_down时会触发，长按不会多次触发
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            // 键被按下时回调，在onPress后面。如果isRepeat属性设置为true，长按时会连续回调

            Editable editable = mEditText.getText();
            int selectionPosition = mEditText.getSelectionStart();
            // 完成
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {
                setVisibility(GONE);
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
                // 如果按下的是delete键，就删除EditText中的str
                if (editable != null && editable.length() > 0) {
                    if (selectionPosition > 0) {
                        editable.delete(selectionPosition - 1, selectionPosition);
                    }
                }
            } else {
                // 把该键对应的string值设置到EditText中
                editable.insert(selectionPosition, Character.toString((char) primaryCode));
            }
            // 其实还有很多code的判断，比如“完成”键、“Shift”键等等，这里就不一一列出了
            // 对于Shift键被按下，需要做两件事，一件是把键盘显示字符全部切换为大写，调用setShifted()方法就可以了；
            // 另一件是把Shift状态下接收到的正常字符（Shift、完成、Delete等除外）的code值-32再转换成相应str，插入到EidtText中
        }
    };
}
