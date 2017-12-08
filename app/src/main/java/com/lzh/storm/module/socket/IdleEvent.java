package com.lzh.storm.module.socket;

import android.os.SystemClock;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lzh on 2017/11/29.
 */

public abstract class IdleEvent {

    private int READIDLE = -1;
    private int WRITEIDLE = -1;
    private int ALLIDLE = -1;

    private long readIdleTime = 0;
    private long writeIdleTime = 0;
    private String IDLE_STATUS = "NOIDLE";

    private boolean isIdle = false;

    public IdleEvent() {
        isIdle = true;

    }

    public void start() {
        callIdle(Event.ALL_IDLE);
//        idleThread();
        writeIdleRuning(WRITEIDLE);
        readIdleRuning(READIDLE);
        allIdleRuning(ALLIDLE);
    }

    public void setIdle(int readIdle, int writeIdle, int allIdle) {
        this.READIDLE = readIdle;
        this.WRITEIDLE = writeIdle;
        this.ALLIDLE = allIdle;
    }

    private String event;

    public String Event() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void callIdle(String idle) {
        switch (idle) {
            case Event.READ_IDLE:
                this.readIdleTime = System.currentTimeMillis();
                break;
            case Event.WRITE_IDLE:
                this.writeIdleTime = System.currentTimeMillis();
                break;
            case Event.ALL_IDLE:
                this.readIdleTime = System.currentTimeMillis();
                this.writeIdleTime = System.currentTimeMillis();
                break;
        }

    }

    private void idleThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                while (isIdle && (READIDLE >= 0 || WRITEIDLE >= 0 || ALLIDLE >= 0)) {
                    if (ALLIDLE >= 0 && (System.currentTimeMillis() - ((readIdleTime > writeIdleTime ? readIdleTime : writeIdleTime)) % ALLIDLE <= 30)) {
                        e.onNext(Event.ALL_IDLE);
                    }
                    if (READIDLE >= 0 && ((System.currentTimeMillis() - readIdleTime) > READIDLE) && ((System.currentTimeMillis() - readIdleTime) % READIDLE <= 30)) {
                        e.onNext(Event.READ_IDLE);
                    }
//                    Log.d("idle","write:"+System.currentTimeMillis()+","+writeIdleTime+","+WRITEIDLE+","+((System.currentTimeMillis() - writeIdleTime) % WRITEIDLE));
                    if (WRITEIDLE >= 0 && ((System.currentTimeMillis() - writeIdleTime) > READIDLE) && ((System.currentTimeMillis() - writeIdleTime) % WRITEIDLE <= 30)) {
//                        Log.e("idle","write:"+System.currentTimeMillis()+","+writeIdleTime+","+WRITEIDLE+","+((System.currentTimeMillis() - writeIdleTime) % WRITEIDLE));
                        e.onNext(Event.WRITE_IDLE);
                    }
                    SystemClock.sleep(30);
                }
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String str) {
                onIdle(new Event(str));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    protected void writeIdleRuning(final long delay) {
        if (delay <= 0)
            return;
//        Log.e("lzh","write111:"+delay);
        Observable.timer(delay, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        long current = (System.currentTimeMillis() -writeIdleTime)%WRITEIDLE;
//                        Log.d("lzh","idle:"+current);
                        if (current >= 0) {
//                            Log.d("lzh","idle:"+Event.WRITE_IDLE);
                            onIdle(new Event(Event.WRITE_IDLE));
                        }
//                        Log.e("lzh","write222:current:"+current+",writeIdleTime"+writeIdleTime+",current-time:"+(current-writeIdleTime)+","+(WRITEIDLE - ((current-writeIdleTime)%WRITEIDLE)));
                        writeIdleRuning(WRITEIDLE - current);
                    }
                });
    }

    protected void readIdleRuning(final long delay) {
        if (delay <= 0)
            return;

        Observable.timer(delay, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        long current =  (System.currentTimeMillis() -readIdleTime)%READIDLE;
//                        Log.e("lzh","read333:"+delay+","+current+","+idleTime+","+readIdleTime);
                        if (current  >= 0) {
//                            Log.d("lzh","idle:"+Event.READ_IDLE);
                            onIdle(new Event(Event.READ_IDLE));
                        }
//                        Log.e("lzh","read444:"+current+","+readIdleTime+","+(WRITEIDLE - ((current-readIdleTime)%WRITEIDLE)));
                        readIdleRuning(READIDLE - current);
                    }
                });
    }

    protected void allIdleRuning(final long delay) {
        if (delay <= 0)
            return;
        Observable.timer(delay, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        long current = (System.currentTimeMillis() - Math.max(readIdleTime, writeIdleTime))%ALLIDLE;
//                        long time = Math.max(readIdleTime, writeIdleTime);
                        if (current  >= 0) {
//                            Log.e("lzh","idle:"+Event.ALL_IDLE);
                            onIdle(new Event(Event.ALL_IDLE));
                        }
//                        Log.e("lzh","all666:"+current+","+time+","+(WRITEIDLE - ((current-time)%WRITEIDLE)));
                        allIdleRuning(ALLIDLE - current);
                    }
                });
    }

    public abstract void onIdle(Event e);

    public void release() {
        isIdle = false;
        this.READIDLE = -1;
        this.WRITEIDLE = -1;
        this.ALLIDLE = -1;

        this.readIdleTime = 0;
        this.writeIdleTime = 0;
    }

    public static class Event{
        public final static String READ_IDLE = "READ_IDLE";
        public final static String WRITE_IDLE = "WRITE_IDLE";
        public final static String ALL_IDLE = "ALL_IDLE";
        public String eventState = "";
        public Event(@NonNull String event){
            this.eventState = event;
        }
    }
}
