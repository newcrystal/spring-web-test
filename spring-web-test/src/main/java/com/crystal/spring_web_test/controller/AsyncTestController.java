package com.crystal.spring_web_test.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AsyncTestController {
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		executor.execute(new SlowExecution());
		
        model.addAttribute("name", name);
        return "index";
    }
	
	public class SlowExecution implements Runnable {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.warn("slow executed");
		}
		
	}

}
