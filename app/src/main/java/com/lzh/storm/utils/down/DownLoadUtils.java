package com.lzh.storm.utils.down;

import android.util.Log;

import com.lzh.storm.module.http.OkhttpApply;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lzh on 2017/12/2.
 */

public class DownLoadUtils {

    private OnDownloadListener onDownloadListener;

    public DownLoadUtils(OnDownloadListener onDownloadListener){
        this.onDownloadListener = onDownloadListener;

    }
    public DownLoadUtils download(final String url, final String filePath){

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> e) throws Exception {
                OkhttpApply okhttpApply = new OkhttpApply();
                okhttpApply.setCallBack(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        onDownloadListener.onListener(true);
                        // 储存下载文件的目录
                        try {
                            Log.e("lzh","开始下载");
                            InputStream inputStream = response.body().byteStream();
                            byte[] buf = new byte[2048];
                            int len = 0;
                            long total = response.body().contentLength();
                            FileOutputStream fos = new FileOutputStream(filePath);
                            long sum = 0;
                            while ((len = inputStream.read(buf)) != -1) {
                                Log.e("lzh","下载中");
                                fos.write(buf, 0, len);
                                sum += len;
                                int progress = (int) (sum * 1.0f / total * 100);
                                // 下载中
                                e.onNext(progress);
                            }
                            fos.flush();
                            e.onComplete();
                        }catch (Exception e){
                            onDownloadListener.onListener(false);
                        }
                    }
                }).doGet(url);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                onDownloadListener.onProgress(integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                onDownloadListener.onSuccess();
            }
        });
        return  this;
    }

    public interface OnDownloadListener{
        void onListener(boolean status);

        void onProgress(int progress);

        void onSuccess();
    }
}
