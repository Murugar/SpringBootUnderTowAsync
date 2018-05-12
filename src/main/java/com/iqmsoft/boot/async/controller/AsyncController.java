package com.iqmsoft.boot.async.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.iqmsoft.boot.async.log.AsyncLogger;
import com.iqmsoft.boot.async.service.AsyncService;

@RestController
public class AsyncController {
	
	
	
	
	@Autowired
	private AsyncService service;
	
	@RequestMapping("/syncTest")
	public String syncTest() {
		AsyncLogger.logger.info("sync start");
		service.sync();
		AsyncLogger.logger.info("sync end");		
		return "sync";
	}
	
	@RequestMapping("/asyncCompletable")
    public DeferredResult< String > getUsersAsync() {

        DeferredResult< String > result = new DeferredResult< String >();


        CompletableFuture.runAsync( () -> {

            System.out.println( " GetAsyncCompletable is running... " + Thread.currentThread().getName() );

            try {

                Thread.sleep( 1000 );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result.setResult( "Test2");
        });

        System.out.println( " Servlet thread freed... " );

        return result;
    }
	
	
	@RequestMapping("/asyncCompletableOther")
    public DeferredResult<String> getUsersAsyncOther() {

        DeferredResult< String > result = new DeferredResult< String >();

        CompletableFuture.supplyAsync( () -> {

            System.out.println( " GetAsyncCompletableOther is running... " + Thread.currentThread().getName() );

            try {

                Thread.sleep( 1000 );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Test3";

        }).whenCompleteAsync( ( value, throwable ) -> {

            System.out.println( " GetAsyncCompletableOther result is being returned... " + Thread.currentThread().getName() );
            result.setResult( value );
        } );

        System.out.println( " Servlet thread freed... " );

        return result;
    }
	
	@RequestMapping("/asyncTest")
    public Future<String> asyncTest(){
		
		AsyncLogger.logger.info("async start.");
		   
		Future<String> result = service.async();
				
		AsyncLogger.logger.info("async end.");
				
		return result;
        
    }
	
	@RequestMapping( "/asyncCallable" )
    public Callable<String> getUserCallable() {

        Callable<String> result = service::getName;
        System.out.println( " Servlet thread freed... " );

        return result;
    }


}
