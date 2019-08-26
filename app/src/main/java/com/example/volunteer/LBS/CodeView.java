package com.example.volunteer.LBS;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class CodeView extends LinearLayout implements View.OnFocusChangeListener{
    private static final int codeCount = 6;
    private List<EditText> editTextList;
    private int currentIndex;
    private InputListener listener;
 
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        currentIndex = (int) v.getTag();
    }
 
    public interface InputListener {
        void onLastOneInputed(String text);
    }
 
    public void setInputListener(InputListener listener) {
        this.listener = listener;
    }
 
    public CodeView(Context context) {
        this(context, null);
    }
 
    public CodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public CodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
 
    private void init() {
        setBackgroundColor(Color.WHITE);
        editTextList = new ArrayList<>();
        post(new Runnable() {
            @Override
            public void run() {
 
                int size = getMeasuredWidth() / codeCount;
                LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(0, size, 1);
                LinearLayout.LayoutParams lineParam = new LinearLayout.LayoutParams(1, size);
                for(int i = 0; i < codeCount; i++) {
                    EditText editText = new EditText(getContext());
 
                    editText.addTextChangedListener(watcher);
                    editTextList.add(editText);
                    editText.setText("");
 
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                    editText.setMaxLines(1);
                    editText.setBackgroundColor(Color.parseColor("#91c26a"));
                    editText.setTextColor(Color.parseColor("#ffffff"));
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    editText.setGravity(Gravity.CENTER);
 
                    addView(editText, editTextParams);
 
                    if(i != codeCount - 1) {
                        View line = new View(getContext());
                        line.setBackgroundColor(Color.parseColor("#ffffff"));
                        addView(line, lineParam);
                    }
 
                    editText.setTag(i);
                    editText.setOnFocusChangeListener(CodeView.this);
                }
                editTextList.get(0).requestFocus();
            }
        });
    }
 
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
 
        }
 
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
 
        }
 
        @Override
        public void afterTextChanged(Editable s) {
            if(isFinished()) {
//                Toast.makeText(this,getText().toString(),Toast.LENGTH_SHORT).show();
                if(listener != null) {
                    listener.onLastOneInputed(getText());
                }
            } else {
                if (s.length() == 1) {
                    if (currentIndex != editTextList.size() - 1) {
                        currentIndex++;
                        EditText editText = editTextList.get(currentIndex);
                        editText.requestFocus();
                    }
                }
            }
        }
 
 
    };
 
    private boolean isFinished() {
        for(EditText editText : editTextList) {
            if(TextUtils.isEmpty(editText.getText().toString())) {
                return false;
            }
        }
        return true;
    }
 
    public String getText() {
        StringBuffer sb = new StringBuffer();
        for(EditText editText : editTextList) {
            sb.append(editText.getText().toString());
        }
        return sb.toString();
    }
 
    public void clear() {
        for(EditText editText : editTextList) {
            editText.setText("");
        }
    }
}