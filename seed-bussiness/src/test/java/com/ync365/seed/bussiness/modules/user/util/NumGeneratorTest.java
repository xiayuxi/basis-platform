package com.ync365.seed.bussiness.modules.user.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ync365.seed.utils.MD5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context-bussiness.xml", "classpath:spring-context-redis.xml" })
public class NumGeneratorTest {

	@Test
	public void test() {
		StopWatch stop = new StopWatch();
		stop.start();
		for (int i = 0; i < 100; i++) {
			String num = NumGenerator.getUserNum();
			System.out.println(i + " >>>>>>>> " + num + " >>>>>>>>> " + Thread.currentThread().getId());
		}
		stop.stop();
		System.out.println("time  >>>>>>>>>>> " + stop.toString());
		stop.reset();
		stop.start();
		for (int i = 0; i < 100; i++) {
			String num = NumGenerator.getPoPNum();
			System.out.println(i + " >>>>>>>> " + num + " >>>>>>>>> " + Thread.currentThread().getId());
		}
		stop.stop();
		System.out.println("time  >>>>>>>>>>> " + stop.toString());
	}

	@Test
	public void concurrentTest() {
		int threadcount=100;
		ExecutorService executor = Executors.newCachedThreadPool();
		final List<Exception> listerrors=Collections.synchronizedList(new ArrayList<Exception>());
		final CyclicBarrier barr = new CyclicBarrier(threadcount, new Runnable() {

			@Override
			public void run() {
				System.out.println("begin test");
			}
		});
		final CyclicBarrier barrend = new CyclicBarrier(threadcount+1, new Runnable() {

			@Override
			public void run() {
				System.out.println("end test");
			}
		});
		for (int i = 0; i < threadcount; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						barr.await();
						test();
					} catch (Exception e) {
						listerrors.add(e);
					} finally {
						try {
							barrend.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
					}

				}
			});
		}
		try {
			barrend.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println(listerrors.size());
		for (Exception exception : listerrors) {
			exception.printStackTrace();
		}
	}
	@Test
	public void testMD5(){
		System.out.println(MD5Utils.getMD5Str("123456"));
	}
}
