
package com.nchu.transaction.seata.twopc.bank2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class Bank2Server {
	
	public static void main(String[] args) {
		SpringApplication.run(Bank2Server.class, args);

	}

}
