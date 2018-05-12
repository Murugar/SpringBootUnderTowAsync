package com.iqmsoft.boot.async.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.iqmsoft.boot.async.log.AsyncLogger;

@Service
public class AsyncService {
	
	public void sync() {
		AsyncLogger.logger.info("sync service start");
		try {
			Thread.sleep(10000);
			AsyncLogger.logger.info("sync sleep done");
		} catch (InterruptedException e) {
			AsyncLogger.logger.error(e.getMessage(), e);
			
		}
		AsyncLogger.logger.info("sync service end");
	}
	
	public String getName() {
		
        System.out.println( " GetName in service is running... " + Thread.currentThread().getName() );

        return "Test4 Callable";
    }
	
	@Async("taskExecutorAsyncPool")
	public  Future< String > async() {
		
		AsyncLogger.logger.info("async service start");
		try {
			Thread.sleep(10000);
			AsyncLogger.logger.info("async sleep done");
		} catch (InterruptedException e) {
			AsyncLogger.logger.error(e.getMessage(), e);
			
		}
		
		AsyncLogger.logger.info("async service end");
		
		AsyncResult< String > result = new AsyncResult<>( "Test1" );
	    return result;
		
	}
}
