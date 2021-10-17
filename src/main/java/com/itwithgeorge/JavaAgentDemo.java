package main.java.com.itwithgeorge;

import com.itwithgeorge.CustomClassTransformer;
import java.lang.instrument.Instrumentation;

// 1. create Java project
// 2. add agent methods
// 3. add framework support for Maven (projectName -> add framework support -> maven)
// 4. edit pom.xml
// 5. manually add META-INF dir + MANIFEST.MF, add premain/agemnain classes respectively
// 6. from terminal mvn package
// 7. see result in target dir jar + manifest
public class JavaAgentDemo {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("JavaAgentDemo premain from thread: " + Thread.currentThread().getName());
        inst.addTransformer(new CustomClassTransformer());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(2000);
                System.out.println("JavaAgentDemo agentmain from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
