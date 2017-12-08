package com.lzh.storm.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.TextView;

import com.lzh.storm.R;
import com.lzh.storm.action.bean.http.IterationInfo;
import com.lzh.storm.module.http.OkhttpApply;
import com.lzh.storm.utils.json.JsonCoder;
import com.lzh.storm.utils.system.SystemUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lzh on 2017/11/30.
 */

public class LoadingActivity extends Activity {

    @BindView(R.id.progress)
    TextView tv_progress;
    OkhttpApply okhttpApply;
    private String url = "http://10.20.175.146:9060/IntercomServer/iteration/iterationInfo";
    private String address = Environment.getExternalStorageDirectory() + "/Download/";
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private int READ_EXTERNAL_STORAGE_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        requestPermission();

    }

    private void CheckUpdate() {
        okhttpApply = new OkhttpApply(1000);
        Observable.create(new ObservableOnSubscribe<IterationInfo>() {
            @Override
            public void subscribe(ObservableEmitter<IterationInfo> e) {
                try {
                    String info = okhttpApply.get(url);
                    IterationInfo iterationInfo = JsonCoder.parseJson(info, IterationInfo.class);
                    e.onNext(iterationInfo);
                }catch (Exception e1){
                    e.onError(e1);
                }

            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<IterationInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IterationInfo iterationInfo) {
                        if (iterationInfo.isStatus()) {
                            iterationInfo.getVersion();
                            int versionCode = SystemUtils.getAppInfo(LoadingActivity.this).versionCode;
                            Log.e("lzh", "versionCode:" + versionCode + "," + Integer.valueOf(iterationInfo.getVersion()));

                            if (versionCode < Integer.valueOf(iterationInfo.getVersion())) {

                                update(iterationInfo);

                            } else {
                                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void update(final IterationInfo iterationInfo) {
        try {
            Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(final ObservableEmitter<Integer> e) throws Exception {
//                    Log.e("lzh", "寻找下载地址" + iterationInfo.getUrl());
                    OkhttpApply okhttpApply = new OkhttpApply();
                    okhttpApply.setCallBack(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            // 储存下载文件的目录
                            try {

//                                getPermission();
                                InputStream inputStream = response.body().byteStream();
                                byte[] buf = new byte[2048];
                                int len = 0;
                                long total = response.body().contentLength();
                                FileOutputStream fos = new FileOutputStream(address + iterationInfo.getName());
                                long sum = 0;
                                while ((len = inputStream.read(buf)) != -1) {
//                                    Log.e("lzh", "下载中4:"+len);
                                    fos.write(buf, 0, len);
                                    sum += len;
                                    int progress = (int) (sum * 1.0f / total * 100);
//                                    Log.e("lzh", "下载中5:"+progress);
                                    // 下载中
                                    e.onNext(progress);
                                }
                                fos.flush();
                                install(address + iterationInfo.getName());
                                fos.close();
                                inputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).doGet(iterationInfo.getUrl());
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(Integer integer) {
                    tv_progress.setText("开始下载：" + (integer + "%"));
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                    tv_progress.setText("结束下载");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.e("lzh","pack:"+getApplicationContext().getPackageName()+".fileProvider"+",path:"+path);
            Uri contentUri = FileProvider.getUriForFile(this,"com.lzh.storm.fileProvider", file);


            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

        }
        this.startActivity(intent);
    }


    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            CheckUpdate();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE || requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            CheckUpdate();
        } else {

        }
    }
}
