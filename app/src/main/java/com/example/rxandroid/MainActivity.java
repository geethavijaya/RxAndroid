package com.example.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Unit;


public class MainActivity extends AppCompatActivity {

    private String[] greeting = {"Hello A", "Hello B", "Hello c"};
    private String greeting2 = "welcome";
    private Observable<Student> myObservables;
    private DisposableObserver<Student> myObserver;
    private DisposableObserver<Student> myObserver2;
    private TextView textview;
    private EditText editText;
    private Button btn;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    //private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textview);
        editText = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button) ;
        textview.setText("harikrishna kv");
        createRxBindingDependencies();
       // asyncSubjectDemo1();


    }

    private void createRxBindingDependencies() {
        Disposable disposable1 = RxTextView.textChanges(editText).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                textview.setText("");
                textview.setText(charSequence);
            }
        });
        Disposable disposable2 = RxView.clicks(btn).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("Please enter something");
                }else {
                    textview.setText("");
                    editText.setText("");
                }

            }
        });
    }

}
