package project.jssc;

import jssc.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import project.jssc.model.CommandList;
import project.jssc.model.TaskPojo;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.concurrent.*;

@Service
@EnableAsync
public class JsscManagement {

    @Autowired
    CommandList commandList;

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    private SerialPort serialPort;
    private Comparator<TaskPojo> idComparator;
    public static CopyOnWriteArrayList<String> activeChannels = new CopyOnWriteArrayList<>();
    public static PriorityBlockingQueue<TaskPojo> queue;

    @PostConstruct
    public void postconstruct(){
        idComparator = (o1, o2) -> Integer.signum(o2.getPriority() - o1.getPriority());
        queue = new PriorityBlockingQueue<>(5, idComparator);
    }


    public boolean configure(String portName) {
        if (cyclicBarrier.getNumberWaiting() == 1) {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        if (connect(portName)) {
            run();
            return true;
        }
        return false;

    }




    public static void getPortNames() {
        String[] a = SerialPortList.getPortNames();
        for (int i = 0; i < a.length; i++) {
            System.out.println("Avaible ports: " + a[i]);
        }

    }


    public boolean disconnect() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return !serialPort.isOpened();
    }


    public boolean connect(String portName) {
        serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            Thread.sleep(500);
            serialPort.setParams(
                    SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE,
                    true,
                    false
            );
            Thread.currentThread().sleep(500);

            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

            cleanPort();
            Thread.currentThread().sleep(500);
            System.out.println("port opened");
            serialPort.writeBytes(CommandList.CLEAN_CPU);
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Thread.currentThread().sleep(500);

            serialPort.writeBytes(CommandList.getTime());
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Thread.currentThread().sleep(500);


            serialPort.writeBytes(CommandList.destroyBuffer(1));
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Thread.currentThread().sleep(500);

            serialPort.writeBytes(CommandList.destroyBuffer(0));
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Thread.currentThread().sleep(500);

        } catch (SerialPortException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serialPort.isOpened();
    }

    private void cleanPort() throws SerialPortException {
        serialPort.purgePort(
                SerialPort.PURGE_RXCLEAR
                        | SerialPort.PURGE_TXCLEAR
                        | SerialPort.PURGE_RXABORT
                        | SerialPort.PURGE_TXABORT
        );
    }

    static void removeBlankSpace(StringBuffer sb) {
        int j = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
    }

    public void startChannel(String channel) {
        if (!activeChannels.stream()
                .anyMatch(t -> t.equals(channel)))
            activeChannels.add(channel);
        System.out.println(JsscManagement.queue.size());
        JsscManagement.queue.add(new TaskPojo(CommandList.destroyBuffer(Integer.valueOf(channel)), 5));
        System.out.println("Channel added: " + channel);
    }

    public void stopChannel(String channel) {
        activeChannels.removeIf(x -> x.equals(channel));
        System.out.println("Channel removed: " + channel);
        System.out.println("Current channels: ");
        activeChannels.forEach(x -> System.out.print(x + " "));
    }


    @Async
    public void run() {

        Thread.currentThread().setName("JSSC");
        System.out.println("JSSC started");
        while (serialPort.isOpened()) {
            try {
                if (queue.size() == 0){
                    System.out.println("sending command CommandList.GETDATA");
                    serialPort.writeBytes(CommandList.GETDATA);}
                else {
                    System.out.println("sending custom task");
                    serialPort.writeBytes(queue.poll().getTask());
                }
                Thread.currentThread().sleep(600);
                cyclicBarrier.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                try {
                    e.printStackTrace();
                    serialPort.closePort();
                } catch (SerialPortException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }


    private class PortReader implements SerialPortEventListener {

        StringBuffer buffer = new StringBuffer();
        StringBuffer result = new StringBuffer();

        @Override
        public void serialEvent(SerialPortEvent event) {

            Thread.currentThread().setName("PortListener");
            try {
                if (event.isRXCHAR() && event.getEventValue() > 0) {
                    buffer.append(serialPort.readHexString());
                    if (buffer.indexOf("1A") != -1) {
                        result.append(buffer).setLength(buffer.indexOf("1A") + 2);
                        removeBlankSpace(result);

                        if (validateCheckSumm(result)) {
                            ByteParse.queue.add(new StringBuffer(result));
                        }
                        result.setLength(0);
                        buffer.delete(0, buffer.indexOf("1A") + 2);
                        try {
                            cyclicBarrier.await(1, TimeUnit.SECONDS);
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("broken array");
                }
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public boolean validateCheckSumm(StringBuffer buffer) {
            int sum2 = 0;
            int sum1 = Integer.parseInt(buffer.substring(9, 10) + buffer.substring(7, 8) + buffer.substring(5, 6) + buffer.substring(3, 4), 16);
            for (int i = 10; i < buffer.length(); i = i + 2) {
                sum2 += Integer.parseInt(buffer.substring(i, i + 2), 16);
            }
            System.out.println("Valid package received?    >>>" + (sum2 == sum1));
            return sum2 == sum1;
        }
    }

}
