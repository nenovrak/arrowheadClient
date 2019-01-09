package eu.arrowhead.client.provider;

class ServoMotor {
    public static void moveServo(int pos) {
        try {			
            Runtime runTime = Runtime.getRuntime();
            runTime.exec("gpio mode 26 pwm");
            runTime.exec("gpio pwm-ms");
            runTime.exec("gpio pwmc 192"); 
            runTime.exec("gpio pwmr 2000"); 
            
            if((100 <= pos) && (pos <= 200)){
                runTime.exec("gpio pwm 26 " + pos);
            }


			
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }
}
