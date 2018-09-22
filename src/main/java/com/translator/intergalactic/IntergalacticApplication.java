package com.translator.intergalactic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.translator.intergalactic.IntergalacticApplication;
import com.translator.intergalactic.services.Translator;

@SpringBootApplication
public class IntergalacticApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IntergalacticApplication.class, args);
        Translator translator = (Translator) context.getBean(Translator.class);

//        System.out.println(translator.translate());
        
        String ret = "";
        do{
        	ret = translator.translate();
        	System.out.println(ret);
        }while(!ret.equals("exit"));
        System.exit(0);
	}
}
