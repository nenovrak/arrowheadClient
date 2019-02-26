package eu.arrowhead.client.provider;

class ServoMotor {
    
    public static void moveServo(int pos) {
        
        /*
         * Here we are controlling the servo. This is done by using the runTime.exec-command. What this command does, is that it
         * executing the inputted argument-string in a terminal.
         * 
         * For example, the runTime.exec("echo Hello world") command would basically outputting 'Hello world' in a terminal
        */
        
        try {			
            Runtime runTime = Runtime.getRuntime();
            runTime.exec("gpio mode 26 pwm"); //Powers on GPIO 26 (Pin 32)
            runTime.exec("gpio pwm-ms");
            runTime.exec("gpio pwmc 192"); 
            runTime.exec("gpio pwmr 2000"); 
            
            if((100 <= pos) && (pos <= 200)){           //If the position is a valid one, then move the servo to the given position
                runTime.exec("gpio pwm 26 " + pos);
            }
        } 
        catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }
}
