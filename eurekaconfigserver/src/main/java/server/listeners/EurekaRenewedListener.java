package server.listeners;


import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

import java.util.List;

@Component
public class EurekaRenewedListener implements ApplicationListener<EurekaInstanceRegisteredEvent>{

 private List<String> device = new ArrayList();

    public void onApplicationEvent(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {

        if(eurekaInstanceRegisteredEvent.isReplication()) {
            if(eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName().startsWith("VCH"))
            {
                device.add(eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName());
            }
        }
    }

    public synchronized List<String> getDevice() {
        return device;
    }
}
