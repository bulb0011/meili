package www.weimeng.com.meili.fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import www.weimeng.com.meili.bean.Text_Bean;


/**
 * Created by Administrator on 2017/6/1.
 */

public class Knack_Fragment extends BaseFragment {

    private Flowable<String> flowable;

    @Override
    public View initView() {
        TextView v=new TextView(getContext());

        v.setText("宝典");

        v.setTextSize(20);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*flowable = Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {

                        Log.i("dengpao","new");

                        e.onNext("dengpao0011");

                        e.onComplete();

                    }
                }, BackpressureStrategy.BUFFER);


                Subscriber subscriber =new Subscriber() {
                    @Override
                    public void onSubscribe(Subscription s) {

                        *//**
                         * 必须调用的方法
                         *//*
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Object o) {

                        Toast.makeText(getContext(),o.toString(),0).show();

                        Log.i("dengpao", "onNext: "+o.toString());

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };

                *//**
                 * 绑定在一起
                 *//*
                flowable.subscribe(subscriber);*/
            }
        });


        return v;
    }


    @Override
    public void initData() {

        /**
         * 普通的传入
         */
       // Rx_Android_String();

        /**
         * 传入map集合
         */
        //Rx_Android_Map();

        /**
         * 判断传进的值
         */
        //Rx_Android_is();

        /**
         * 获取前两位的值
         */
       //Rx_Aandroid_quzhi();

        /**
         * 在订阅者接收到数据前干点事情
         */
        //shshi();

        /**
         * 随意的切换线程
         */
        qiehuanxiancheng();

    }

    private void qiehuanxiancheng() {
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Object> e) throws Exception {

                List<Text_Bean> list = new ArrayList<>();

                list.add(new Text_Bean("mingz","aa","aa","aa"));
                list.add(new Text_Bean("mingz1","aa","aa","aa"));
                list.add(new Text_Bean("mingz2","aa","aa","aa"));

                e.onNext(list);

                SystemClock.sleep(3000);

                e.onNext(list);

                e.onComplete();

            }
        } , BackpressureStrategy.BUFFER)

         // 指定了发送数据是在io线程(某个子线程)
        .subscribeOn(Schedulers.io())
        //指定订阅者在主线程执行
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {


                List<Text_Bean> l=(List)o;

                for (Text_Bean b:l) {

                    Log.i("denpao", "accept: "+b.getName());

                }


            }
        });
    }

    private void shshi() {
        Flowable.just(1, 2, 3)
                /*.doOnRequest(new LongConsumer() {
                    @Override
                    public void accept(long t) throws Exception {

                    }
                })*/
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("保存:" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    private void Rx_Aandroid_quzhi() {
        Flowable.fromArray(1, 20, 5, 0, -1)
                .take(2)
                //.takeLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {

                        Log.i("dengpao", "accept: "+integer);

                    }
                });
    }

    private void Rx_Android_is() {
        Flowable.fromArray(1, 20, 5, 0, -1, 8)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {

                        return integer.intValue()>=5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("dengpoa", "accept: "+integer);
                    }
                });
    }

    private void Rx_Android_Map() {
        List<Text_Bean> list = new ArrayList<>();

        list.add(new Text_Bean("mingz","aa","aa","aa"));
        list.add(new Text_Bean("mingz1","aa","aa","aa"));
        list.add(new Text_Bean("mingz2","aa","aa","aa"));

        Flowable.just(list)
                .flatMap(new Function<List<Text_Bean>, Publisher<Text_Bean>>() {
                    @Override
                    public Publisher<Text_Bean> apply(@NonNull List<Text_Bean> text_been) throws Exception {

                        return Flowable.fromIterable(text_been);
                        //return Flowable.fromArray(text_been);
                    }
                })
                .subscribe(new Consumer<Text_Bean>() {
                    @Override
                    public void accept(@NonNull Text_Bean text_bean) throws Exception {

                        Log.i("dengpao", "accept: "+ text_bean.getName());
                    }
                });
    }


    private void Rx_Android_String() {

        Log.i("dengpao", "dianShi_Rx_Android:" +"进入");

        Flowable.just("String")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {

                        return s.hashCode();
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {

                        return integer.toString()+"我睡";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {

                        Log.i("dengpoa", "accept: "+s);

                    }
                });
    }


}
